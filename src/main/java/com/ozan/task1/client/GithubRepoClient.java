package com.ozan.task1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;

@Repository
public class GithubRepoClient implements IGithubRepoClient{
	
	@Override
	public List<Contributor> getAllContributors(GithubForm githubForm) {
		
		String url;
		
		url = "https://api.github.com/repos/" + githubForm.getOrganisation().toLowerCase() + "/" + githubForm.getRepository().toLowerCase() + "/contributors";
		

		URL apiUrl;
		
		try {
			apiUrl = new URL (url);
			HttpURLConnection con = (HttpURLConnection)apiUrl.openConnection();
			con.setRequestMethod("GET");
			
			if(!StringUtils.isEmptyOrWhitespace(githubForm.getPersonelToken()))
				con.setRequestProperty("Authorization", "Bearer " + githubForm.getPersonelToken());
			
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			int responseCode = con.getResponseCode();
			
		    if(responseCode == HttpURLConnection.HTTP_OK) {
			    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			    String inputLine;
			    StringBuffer response = new StringBuffer();
			    
			    while ((inputLine = in.readLine()) != null) {
			    	response.append(inputLine);
			    }
			    
			    ObjectMapper mapper = new ObjectMapper();
			    List<Contributor> list = mapper.readValue(response.toString(), 
			    	    new TypeReference<ArrayList<Contributor>>() {});
				
				
				return list;
		    }else {
		    	return null;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public void getContributorDetail(List<Contributor>  contributors, String authorization) {
		

		for(Contributor contributor : contributors) {
			
			if(StringUtils.isEmptyOrWhitespace(contributor.getUrl())) {
				continue;
			}
			
			URL apiUrl;
			
			try {
				apiUrl = new URL (contributor.getUrl());
				HttpURLConnection con = (HttpURLConnection)apiUrl.openConnection();
				con.setRequestMethod("GET");
				
				if(!StringUtils.isEmptyOrWhitespace(authorization))
					con.setRequestProperty("Authorization", "Bearer " + authorization);
				
				con.setRequestProperty("Content-Type", "application/json; utf-8");
				con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(true);

				int responseCode = con.getResponseCode();
				
			    if(responseCode == HttpURLConnection.HTTP_OK) {
				    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				    String inputLine;
				    StringBuffer response = new StringBuffer();
				    
				    while ((inputLine = in.readLine()) != null) {
				    	response.append(inputLine);
				    }
				    
				    ObjectMapper mapper = new ObjectMapper();
				    JsonNode tempCont = mapper.readValue(response.toString(), JsonNode.class);

				    contributor.setCompany(tempCont.get("company").textValue());
				    contributor.setLocation(tempCont.get("location").textValue());

			    }else {
			    	continue;
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
