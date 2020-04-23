package br.com.integrationofcamel.util;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.integrationofcamel.dto.endpointgithub.DtoEndPointGitHub;
import br.com.integrationofcamel.dto.resquestpost.DtoRequestPost;
import br.com.integrationofcamel.dto.usersgithub.DtoEndPointGitHubUsersProfile;

public class ProcessExchange {

	public static ArrayList<String> languages(DtoRequestPost object)
			throws JsonMappingException, JsonProcessingException, InterruptedException {

		String UriLanguages;
		int total_count;
		int page_control_paginable = 2;

		UriLanguages = ProcessURI.ProcessURIGitHubLanguages(object);

		ConsomerGit consomerGit = ConsomerGit.getInstance();

		DtoEndPointGitHubUsersProfile obj = consomerGit.RequestGetProfileGitHub(UriLanguages);

		total_count = obj.getTotalCount();

		ArrayList<String> candidates = new ArrayList<>();

		candidates.addAll(SelectAllCandidates.ProcessUserProfile(obj));

		if (total_count > 30) {

			for (int i = 30; i <= total_count; i += 30) {

				obj = consomerGit.RequestGetProfileGitHub(UriLanguages + "&page=" + page_control_paginable);

				candidates.addAll(SelectAllCandidates.ProcessUserProfile(obj));
				
				page_control_paginable += 1;
				
				try {
					Thread.sleep(10000);
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					continue;
				}
			}
		}
		return candidates;
	}

	public static ArrayList<String> frameworks(DtoRequestPost object, ArrayList<String> candidates)
			throws JsonMappingException, JsonProcessingException, InterruptedException {

		String UriFrameworks;
		
		UriFrameworks = ProcessURI.ProcessURIGitHubFrameworks(object);
		
		ConsomerGit consomerGit = ConsomerGit.getInstance();
		
		DtoEndPointGitHub obj = new DtoEndPointGitHub();
		
		String userList = "";
		
		int controlList = 0;
		int controlIndexList=10;
		ArrayList<String> finalCandidates = new ArrayList<>();

		for (String element : candidates) {
			
			if (controlList ==controlIndexList) {
				userList =userList+";+user:" + element;
				controlIndexList=controlIndexList+10;
				controlList++;
			}else {
			userList =userList+"+user:" + element;
			controlList++;
			}
		
     	}
		String[] textoSeparado = userList.split(";");
		
		for (String users : textoSeparado) {
			 obj = consomerGit.RequestGetRepositoriesGitHub(UriFrameworks+users);
			 finalCandidates.addAll(SelectAllCandidates.ProcessRepositories(obj));
				try {
					Thread.sleep(10000);
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					continue;
				}				
		}
		
		System.out.println(finalCandidates.toString());
		
		return finalCandidates;
	}	

}