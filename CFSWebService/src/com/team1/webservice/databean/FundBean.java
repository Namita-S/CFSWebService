package com.team1.webservice.databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundID")
public class FundBean {
	private int fundID;
	private String name;
	private String symbol;
	private Date dateCreated;
	private double price;
	
	public int getFundID() {
		return fundID;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setFundID(int fundid) {
		this.fundID = fundid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setPrice(double initValue) {
		this.price = initValue;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
