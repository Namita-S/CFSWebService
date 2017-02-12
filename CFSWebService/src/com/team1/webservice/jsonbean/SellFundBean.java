package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class SellFundBean {
	@JsonProperty("numShares")
	private String shares;
	
	@JsonProperty("symbol")
	private String symbol;

	public String getShares() {
		return shares;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public boolean isValidInput() {
		if (   symbol == null || symbol.length() == 0
			|| shares == null || shares.length() == 0) {
			return false;
		}
		
		try {
			int amount = Integer.parseInt(shares);
			if (amount <= 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
