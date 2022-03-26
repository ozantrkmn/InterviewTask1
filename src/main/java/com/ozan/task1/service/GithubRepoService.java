package com.ozan.task1.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ozan.task1.client.IGithubRepoClient;
import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;
import com.ozan.task1.utils.Utils;

@Service
public class GithubRepoService implements IGithubRepoService{
	
	
	@Autowired
	IGithubRepoClient githubRepoClient;
	
	
	@Override
	public List<Contributor> listAllContributors(GithubForm githubForm) {
		
		List<Contributor> contributorsList = githubRepoClient.getAllContributors(githubForm);
		
		if(CollectionUtils.isEmpty(contributorsList)) {
			return null;
		}
		
		githubRepoClient.getContributorDetail(contributorsList, githubForm.getPersonelToken());
		
		// sort and limit to 10
		List<Contributor> responseList = contributorsList.stream()
			.sorted(Comparator.comparing(Contributor::getContributions).reversed())
			.limit(10)
			.collect(Collectors.toList());
		
		
		Utils.saveToTextFile(githubForm, responseList);
		
		
		return responseList;
		
	}

}
