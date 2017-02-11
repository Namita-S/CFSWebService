package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerID,fundID")
public class PositionBean {
	private int customerID;
	private int fundID;
	private double shares;
	
	public int getCustomerID() {
		return customerID;
	}
	public int getFundID() {
		return fundID;
	}
	public double getShares() {
		return shares;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void setFundID(int fundID) {
		this.fundID = fundID;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	
	
}
