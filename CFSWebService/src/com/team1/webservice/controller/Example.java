package com.team1.webservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.team1.webservice.jsonbean.LoginBean;

@Path("/UserService")
public class Example {
	
	@Path("user")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getUsers() throws JSONException {
		LoginBean lb = new LoginBean();
		lb.setUsername("Jianan");
		lb.setPassword("123");
		
		return Response.status(200).entity(lb).build();
	}
	
	@Path("user")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Response createUser(LoginBean lb) {
		String result = "User created: " + lb;
		
		return Response.status(200).entity(result).build();
	}
}
