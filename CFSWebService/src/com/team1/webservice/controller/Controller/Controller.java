package com.team1.webservice.controller.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/UserService")
public class Controller {
	
	@Path("/user")
	@GET
	@Produces("application/json")
	public Response getUsers() throws JSONException {
		JSONObject jb = new JSONObject();
		jb.put("name", "Jianan Ding");
		jb.put("Age", "25");
		
		String result = jb.toString();
		return Response.status(200).entity(result).build();
	}
}
