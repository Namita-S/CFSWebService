package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoginBean {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
