package com.ozan.task1.client;

import java.util.List;

import com.ozan.task1.model.Contributor;
import com.ozan.task1.model.GithubForm;

public interface IGithubRepoClient {

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
