package br.com.integrationofcamel.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.integrationofcamel.dto.endpointgithub.DtoEndPointGitHub;
import br.com.integrationofcamel.dto.finalcandidates.Finalcandidate;
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

	public static ArrayList<Finalcandidate> ProcessRepositories(DtoEndPointGitHub users) {

		Finalcandidate candidates = new Finalcandidate();

		Set<Finalcandidate> allCandidatenoRepeatforRepository = new HashSet<Finalcandidate>();

		for (br.com.integrationofcamel.dto.endpointgithub.Item element : users.getItems()) {
			candidates.setAvatarUrl(element.getOwner().getAvatarUrl());
			candidates.setCandidate(element.getOwner().getLogin());
			allCandidatenoRepeatforRepository.add(candidates);
		}

		ArrayList<Finalcandidate> allcandidates = new ArrayList<Finalcandidate>(allCandidatenoRepeatforRepository);
		return allcandidates;
	}
}
