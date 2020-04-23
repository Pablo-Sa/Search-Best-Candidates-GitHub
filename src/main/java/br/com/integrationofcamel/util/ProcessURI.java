package br.com.integrationofcamel.util;

import br.com.integrationofcamel.dto.resquestpost.DtoRequestPost;

public class ProcessURI {

	public static String ProcessURIGitHubLanguages(DtoRequestPost dto) {

		String languages = "";
		String locationRequest=dto.getPerfil().getlocation();
		String location=locationRequest.replaceAll(" ", "%20");
		
		for (String element : dto.getPerfil().getLanguages()) {
			if (languages.isEmpty())
				languages = "language:" + element;
			else
				languages = languages + "+language:"+ element;
		}

		String URI = languages + "+location:"+location;
		return URI;
	}

	public static String ProcessURIGitHubFrameworks(DtoRequestPost dto) {

		String frameworks = "";

		for (String element : dto.getPerfil().getFrameworks()) {
			if (frameworks.isEmpty())
				frameworks = element;
			else
				frameworks = frameworks + "," + element;
		}

		String URI = frameworks + "?in:readme,description";
		return URI;
	}

}
