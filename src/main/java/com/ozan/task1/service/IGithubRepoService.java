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

	/**
	 * Response from list of contributors has not enough data,
	 * iterates over contributors and fetchs more information about contributor
	 * 
	 * @param Contributors
	 */
	public void getContributorDetail(List<Contributor> contributors, String authorization);

	/**
	 * Returns all of the contributors of the given org-repo
	 * 
	 * @param githubForm
	 * @return
	 */
	List<Contributor> getAllContributors(GithubForm githubForm);

}
