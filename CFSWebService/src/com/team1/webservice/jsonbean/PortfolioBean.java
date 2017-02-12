package com.team1.webservice.jsonbean;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class PortfolioBean extends MessageBean {
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("cash")
	private String cash;
	
	@JsonProperty("Funds")
	private ArrayList<JacksonFund> funds;

	public String getMessage() {
		return message;
	}

	public ArrayList<JacksonFund> getFunds() {
		return funds;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFunds(ArrayList<JacksonFund> funds) {
		this.funds = funds;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}
}
