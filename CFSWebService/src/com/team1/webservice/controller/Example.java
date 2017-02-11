package com.team1.webservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("UserService")
public class Example {
	
	@Path("user")
	@GET
	@Produces("application/json")
	public Response getUsers() throws JSONException {
		JSONObject jb = new JSONObject();
		
		jb.put("name", "username");
		jb.put("password", "password");
		
		String result = jb.toString();
		return Response.status(200).entity(result).build();
	}
}
