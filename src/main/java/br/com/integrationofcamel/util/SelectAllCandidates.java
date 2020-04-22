package br.com.integrationofcamel.util;

import java.util.ArrayList;

import br.com.integrationofcamel.dto.usersgithub.DtoEndPointGitHubUsersProfile;
import br.com.integrationofcamel.dto.usersgithub.Item;

public class SelectAllCandidates {
	
	public static ArrayList<String> Process (DtoEndPointGitHubUsersProfile users){
		
		ArrayList<String> allCandidates = new ArrayList<>();
		for (Item element : users.getItems()) {
			allCandidates.add(element.getLogin());
		}
		return allCandidates;
	}

}
