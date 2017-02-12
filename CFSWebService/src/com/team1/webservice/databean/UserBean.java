package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userID")
public class UserBean {
	private int userID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	
	private String city;
	private String state;
	private String zip;
	private String role;
	private double cash;
	
	public int getUserID() {
		return userID;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
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
	public String getRole() {
		return role;
	}
	public double getCash() {
		return cash;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setEmail(String email) {
		this.email = email;
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
	public void setRole(String role) {
		this.role = role;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
}