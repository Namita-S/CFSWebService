package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerid,fundid")
public class PositionBean {
	private int customerid;
	private int fundid;
	private double shares;
	
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
	 
	 public double getShares() {
	     return shares;
	 }
	 public void setShares(double i) {
	     shares = i;
	 }

}
