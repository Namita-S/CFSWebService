package com.team1.webservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.genericdao.RollbackException;
import org.json.JSONException;
import org.json.JSONObject;

import com.team1.webservice.databean.UserBean;
import com.team1.webservice.jsonbean.LoginBean;
import com.team1.webservice.model.Model;
import com.team1.webservice.model.UserDAO;

@Path("/Login")
public class LoginAction {
	private Model model;
	private UserDAO userDAO;
	
	public LoginAction() {
		model = new Model();
		userDAO = model.getUserDAO();
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Login(LoginBean loginBean, @Context HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		JSONObject result = new JSONObject();
		try {
			String userName = loginBean.getUsername();
			String password = loginBean.getPassword();
			UserBean user = userDAO.getUserByUserName(userName);
			
			boolean isVerified = userDAO.verifyUser(userName, password);
			if (!isVerified) {
				result.put("message", "There seems to be an issue with" + 
						"the username/password combination that you entered");
				return Response.status(200).entity(result.toString()).build();
			} else if (isVerified && user.getRole() != null) {
				clearSession(request);
				session.setAttribute("employee", user);
			} else if (isVerified && user.getRole() == null) {
				clearSession(request);
				session.setAttribute("customer", user);
			}
			String firstName = user.getFirstName();
			result.put("message", "Welcome " + firstName);
			
		} catch (JSONException e) {
			
		} catch (RollbackException e) {
			
		}
		
		return Response.status(200).entity(result.toString()).build();
	}
	
	private void clearSession(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("customer", null);
		session.setAttribute("employee", null);
	}
}
