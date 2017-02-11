package com.team1.webservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.genericdao.RollbackException;
import org.json.JSONException;

import com.team1.webservice.databean.UserBean;
import com.team1.webservice.jsonbean.LoginBean;
import com.team1.webservice.jsonbean.MessageBean;
import com.team1.webservice.model.Model;
import com.team1.webservice.model.UserDAO;

@Path("/")
public class ManageSession {
	private Model model;
	private UserDAO userDAO;
	private MessageBean message;
	
	public ManageSession() {
		model = new Model();
		userDAO = model.getUserDAO();
		message = new MessageBean();
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean Login(LoginBean loginBean, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			String userName = loginBean.getUsername();
			String password = loginBean.getPassword();
			UserBean user = userDAO.getUserByUserName(userName);
			
			boolean isVerified = userDAO.verifyUser(userName, password);
			if (!isVerified) {
				message.setMessage("There seems to be an issue with" + 
						"the username/password combination that you entered");
				return message;
			} else {
				clearSession(request);
				session.setAttribute("user", user);
			} 
			String firstName = user.getFirstName();
			message.setMessage("Welcome " + firstName);
		} catch (JSONException e) {
			message.setMessage(e.getMessage());
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
		}
		
		return message;
	}
	
	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean logout(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			clearSession(request);
			message.setMessage("You have been successfully logged out");
		} else {
			message.setMessage("You are not currently logged in");
		}
		return message;
	}
	
	private void clearSession(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
	}
}