package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class JacksonFund {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("shares")
	private String shares;
	
	@JsonProperty("price")
	private String price;

	public String getName() {
		return name;
	}

	public String getShares() {
		return shares;
	}

	public String getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
