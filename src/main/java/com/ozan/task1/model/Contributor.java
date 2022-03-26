package com.ozan.task1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contributor {
	
	@JsonProperty("login")
	private String login;
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("url")
	private String url;

	private String company;
	

	private String location;
	
	@JsonProperty("contributions")
	private int contributions;
	
	public Contributor() {
		
	}

	public Contributor(String login, long id, String url, String company, String location, int contributions) {
		super();
		this.login = login;
		this.id = id;
		this.url = url;
		this.company = company;
		this.location = location;
		this.contributions = contributions;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getContributions() {
		return contributions;
	}

	public void setContributions(int contributions) {
		this.contributions = contributions;
	}
	
	

}
