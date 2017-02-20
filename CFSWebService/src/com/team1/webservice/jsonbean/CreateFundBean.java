package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateFundBean {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("symbol")
	private String symbol;
	
	@JsonProperty("initial_value")
	private String initValue;

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getInitValue() {
		return initValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}
	
	public boolean isValidInput() {
		if (   name == null || name.length() == 0
			|| initValue == null || initValue.length() == 0) {
				return false;
			}
			
		try {
			double amount = Double.parseDouble(initValue);
			if (amount < 0.0) {
				return false;
			}

			String[] s = initValue.split("\\.");
			if (s.length > 2) {
				return false;
			}
			String decimals = s[s.length - 1];
			if (decimals.length() > 2) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
