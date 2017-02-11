package com.team1.webservice.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("username")
public class EmployeeBean {
 private String username;
 private String password;
 private String firstname;
 private String lastname;
 
 
 public String getUsername()        { return username; }
 public void setUsername(String s)  { username = s;    }

 public String getPassword()        { return password; }
 public void setPassword(String s)  {  password = s;    }
 
 public String getFirstname()        { return firstname; }
 public void setFirstname(String s)  {  firstname = s;    }
 
 public String getLastname()        { return lastname; }
 public void setLastname(String s)  { lastname = s;    }
 
}