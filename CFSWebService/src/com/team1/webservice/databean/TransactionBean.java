package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionID")
public class TransactionBean {
	private int transactionID;
	private int customerID;
	private int fundID;
	private double shares;
	private String transType;
	private double amount;
	public int getTransactionID() {
		return transactionID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public int getFundID() {
		return fundID;
	}
	public double getShares() {
		return shares;
	}
	public String getTransType() {
		return transType;
	}
	public double getAmount() {
		return amount;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
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
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
