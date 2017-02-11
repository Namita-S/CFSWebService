package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundid")
public class FundBean {
 private int fundid;
 private String name;
 private String symbol;
 
 public int getFundid() {
     return fundid;
 }
 public void setFundid(int i) {
     fundid = i;
 }
 
 public String getName()        { return name; }
 public void setName(String s)  { name = s;    }

 public String getSymbol()        { return symbol; }
 public void setSymbol(String s)  {  symbol = s;    }
 
}
