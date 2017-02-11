package com.team1.webservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.team1.webservice.jsonbean.LoginBean;
import com.team1.webservice.jsonbean.MessageBean;

@Path("/UserService")
public class Example {
	
	@Path("user")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public LoginBean getUsers() throws JSONException {
		LoginBean lb = new LoginBean();
		lb.setUsername("Jianan");
		lb.setPassword("123");
		
		return lb;
	}
	
	@Path("user2")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public MessageBean createUser(LoginBean lb) {
		MessageBean m = new MessageBean();
		m.setMessage("hello world");
		return m;
	}
}
