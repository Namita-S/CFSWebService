package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerid")
public class CustomerBean {
	private int customerid;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String addrline1;
	private String addrline2;
	private String city;
	private String state;
	private String zip;
	private double cash;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int i) {
		customerid = i;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String s) {
		username = s;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String s) {
		password = s;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String s) {
		firstname = s;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String s) {
		lastname = s;
	}

	public String getAddrline1() {
		return addrline1;
	}

	public void setAddrline1(String s) {
		addrline1 = s;
	}

	public String getAddrline2() {
		return addrline2;
	}

	public void setAddrline2(String s) {
		addrline2 = s;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String s) {
		city = s;
	}

	public String getState() {
		return state;
	}

	public void setState(String s) {
		state = s;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String s) {
		zip = s;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double i) {
		cash = i;
	}
}