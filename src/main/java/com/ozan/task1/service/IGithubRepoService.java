package com.ozan.task1.service;

import java.util.List;

import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;

public interface IGithubRepoService {

	/**
	 * fetch all contributors and their details
	 * sorts and limits the list 10
	 * calls the function to create text file
	 * 
	 * @param githubForm form from UI
	 * @return list of repository contributors
	 */
	public List<Contributor> listAllContributors(GithubForm githubForm);

}
