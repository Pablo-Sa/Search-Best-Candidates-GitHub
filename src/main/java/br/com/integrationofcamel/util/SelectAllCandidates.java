package br.com.integrationofcamel.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.com.integrationofcamel.dto.endpointgithub.DtoEndPointGitHub;
import br.com.integrationofcamel.dto.usersgithub.DtoEndPointGitHubUsersProfile;
import br.com.integrationofcamel.dto.usersgithub.Item;

public class SelectAllCandidates {

	public static ArrayList<String> ProcessUserProfile(DtoEndPointGitHubUsersProfile users) {

		ArrayList<String> allCandidates = new ArrayList<>();
		for (Item element : users.getItems()) {
			allCandidates.add(element.getLogin());
		}
		return allCandidates;
	}

	public static ArrayList<String> ProcessRepositories(DtoEndPointGitHub users) {

		Set<String> set = new HashSet<String>();
		for (br.com.integrationofcamel.dto.endpointgithub.Item element : users.getItems()) {
			set.add(element.getOwner().getLogin());
		}

		ArrayList<String> allCandidates = new ArrayList<String>(set);
		return allCandidates;
	}
}
