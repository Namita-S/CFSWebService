package com.team1.webservice.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionid")
public class TransactionBean {
	private int transactionid;
	private int customerid;
	private int fundid;
	private Date executedate;
	private double shares;
	private String transtype;
	private double amount;
	/**
	 * transaction status could be: pending/processed/rejected
	 */
	private String status;

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int i) {
		transactionid = i;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int i) {
		customerid = i;
	}

	public int getFundid() {
		return fundid;
	}

	public void setFundid(int i) {
		fundid = i;
	}

	public Date getExecutedate() {
		return executedate;
	}

	public void setExecutedate(Date s) {
		executedate = s;
	}

	public double getShares() {
		return shares;
	}

	public void setShares(double i) {
		shares = i;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String s) {
		transtype = s;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double i) {
		amount = i;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
