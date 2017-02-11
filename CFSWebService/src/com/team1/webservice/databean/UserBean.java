package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class UserBean {
	private int userID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address;

	private String city;
	private String state;
	private String zip;
	private String role;
	private double cash;
	
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public int getUserID() {
		return userID;
	}
	public String getUserName() {
		return userName;
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
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
}