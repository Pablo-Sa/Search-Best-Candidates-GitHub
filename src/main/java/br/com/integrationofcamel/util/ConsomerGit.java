package br.com.integrationofcamel.util;

import java.io.IOException;
import java.util.logging.Level;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.logging.Logger;

import br.com.integrationofcamel.dto.endpointgithub.DtoEndPointGitHub;
import br.com.integrationofcamel.dto.usersgithub.DtoEndPointGitHubUsersProfile;

public class ConsomerGit {

	private static String URIBaseUsersGit = "https://api.github.com/search/users?q=";
	private static String URIBaseRepositoreGit = "https://api.github.com/search/repositories?q=";
	private static ConsomerGit instance;
	private CloseableHttpClient clienteHTTP;

	private ConsomerGit() {
		this.clienteHTTP = HttpClients.createDefault();
	}

	public static ConsomerGit getInstance() {
		if (instance == null)
			instance = new ConsomerGit();
		return instance;
	}

	
	
	public DtoEndPointGitHubUsersProfile 
	RequestGetProfileGitHub(String path) throws JsonMappingException, JsonProcessingException {
		String responseBody = null;

	try {
		HttpGet httpGet = new HttpGet(ConsomerGit.URIBaseUsersGit + path);
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			@Override
			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		};
		responseBody = this.clienteHTTP.execute(httpGet, responseHandler);

	} catch (Exception ex) {
		Logger.getLogger(ConsomerGit.class.getName(), null).log(Level.SEVERE, null, ex);
	}
	
	ObjectMapper mapper = new ObjectMapper();
	DtoEndPointGitHubUsersProfile obj = mapper.readValue(responseBody, DtoEndPointGitHubUsersProfile.class);
	return obj;
}
	
	
	public DtoEndPointGitHub 
	RequestGetRepositoriesGitHub(String path) throws JsonMappingException, JsonProcessingException {
		String responseBody = null;

	try {
		HttpGet httpGet = new HttpGet(ConsomerGit.URIBaseRepositoreGit + path);
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			@Override
			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		};
		responseBody = this.clienteHTTP.execute(httpGet, responseHandler);

	} catch (Exception ex) {
		Logger.getLogger(ConsomerGit.class.getName(), null).log(Level.SEVERE, null, ex);
	}
	
	ObjectMapper mapper = new ObjectMapper();
	DtoEndPointGitHub obj = mapper.readValue(responseBody, DtoEndPointGitHub.class);
	return obj;
}
	
}
