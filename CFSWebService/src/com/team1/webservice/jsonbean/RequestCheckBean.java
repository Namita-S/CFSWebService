package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestCheckBean {
	
	@JsonProperty("cashValue")
	private String cashValue;

	public String getCashValue() {
		return cashValue;
	}

	public void setCashValue(String cashValue) {
		this.cashValue = cashValue;
	}
	
	public boolean isValidInput() {
		if (cashValue == null || cashValue.length() == 0) {
			return false;
		}

		try {
			double amount = Double.parseDouble(cashValue);
			if (amount < 0.0) {
				return false;
			}
			String[] s = cashValue.split("\\.");
			if (s.length != 2) {
				return false;
			}
			String decimals = s[s.length - 1];
			if (decimals.length() != 2) {
				return false;
			}
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
