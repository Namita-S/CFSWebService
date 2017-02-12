package com.team1.webservice.jsonbean;

import org.codehaus.jackson.annotate.JsonProperty;

public class CreateCustomerBean {
	@JsonProperty("fname")
	private String firstName;
	
	@JsonProperty("lname")
	private String lastName;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("zip")
	private String zip;
	
	@JsonProperty("cash")
	private String cash = "0";
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("email")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCash() {
		return cash;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isValidInput() {
		if (   firstName == null || firstName.length() == 0
			|| lastName == null || lastName.length() == 0
			|| address == null || address.length() == 0
			|| state == null || state.length() == 0
			|| city == null || city.length() == 0
			|| zip == null || zip.length() == 0
			|| email == null || email.length() == 0
			|| username == null || username.length() == 0
			|| password == null || password.length() == 0) {
			return false;
		} 
		
		try {
			double amount = Double.parseDouble(cash);
			if (amount < 0.0) {
				return false;
			}
			String[] s = cash.split("\\.");
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
