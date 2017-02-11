package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class MessageBean {
	@JsonProperty("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
