package br.com.integrationofcamel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.istack.logging.Logger;

import br.com.integrationofcamel.dto.endpointgithub.DtoEndPointGitHub;
import br.com.integrationofcamel.dto.finalcandidates.FinalCandidatesEvaluated;
import br.com.integrationofcamel.dto.finalcandidates.Finalcandidate;
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
				} catch (Exception ex) {
					Logger.getLogger(ConsomerGit.class.getName(), null).log(Level.SEVERE, null, ex);
				} finally {
					continue;
				}
			}
		}
		return candidates;
	}

	public static FinalCandidatesEvaluated frameworks(DtoRequestPost object, ArrayList<String> candidatesLogin)
			throws JsonMappingException, JsonProcessingException, InterruptedException {

		String UriFrameworks;

		UriFrameworks = ProcessURI.ProcessURIGitHubFrameworks(object);

		ConsomerGit consomerGit = ConsomerGit.getInstance();

		DtoEndPointGitHub obj = new DtoEndPointGitHub();

		String userList = "";

		int controlList = 0;
		int controlIndexList = 10;

		for (String element : candidatesLogin) {

			if (controlList == controlIndexList) {
				userList = userList + ";+user:" + element;
				controlIndexList = controlIndexList + 10;
				controlList++;
			} else {
				userList = userList + "+user:" + element;
				controlList++;
			}

		}
		String[] userListConcatenad = userList.split(";");

		FinalCandidatesEvaluated allCandidatesList = new FinalCandidatesEvaluated();
		ArrayList<Finalcandidate> candidateIterator = new ArrayList<Finalcandidate>();

		for (String users : userListConcatenad) {
			obj = consomerGit.RequestGetRepositoriesGitHub(UriFrameworks + users);
			candidateIterator.addAll(SelectAllCandidates.ProcessRepositories(obj));
			try {
				Thread.sleep(10000);
			} catch (Exception ex) {
				Logger.getLogger(ConsomerGit.class.getName(), null).log(Level.SEVERE, null, ex);
			} finally {
				continue;
			}
		}

		allCandidatesList.setFinalcandidates(candidateIterator);
		return allCandidatesList;
	}

}