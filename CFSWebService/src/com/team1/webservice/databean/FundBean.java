package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundID")
public class FundBean {
	private int fundID;
	private String name;
	private String symbol;
	private double initValue;
	
	public int getFundID() {
		return fundID;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public double getInitValue() {
		return initValue;
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
	public void setInitValue(double initValue) {
		this.initValue = initValue;
	}
}
