package com.ozan.task1.model;

public class GithubForm {

    private String organisation;
    
    private String repository;
    
    private String personelToken;
    
    public GithubForm() {
    	
    }

	public GithubForm(String organisation, String repository, String personelToken) {
		super();
		this.organisation = organisation;
		this.repository = repository;
		this.personelToken = personelToken;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getPersonelToken() {
		return personelToken;
	}

	public void setPersonelToken(String personelToken) {
		this.personelToken = personelToken;
	}

}
