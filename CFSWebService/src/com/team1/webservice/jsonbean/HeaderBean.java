package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class HeaderBean {
	@JsonProperty("user-agent")
	private String userAgent;

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
