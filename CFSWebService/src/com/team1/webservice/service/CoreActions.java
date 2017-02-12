package com.team1.webservice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.json.JSONException;

import com.team1.webservice.databean.UserBean;
import com.team1.webservice.jsonbean.CreateCustomerBean;
import com.team1.webservice.jsonbean.DepositBean;
import com.team1.webservice.jsonbean.LoginBean;
import com.team1.webservice.jsonbean.MessageBean;
import com.team1.webservice.model.FundDAO;
import com.team1.webservice.model.Model;
import com.team1.webservice.model.TransactionDAO;
import com.team1.webservice.model.UserDAO;

@Path("/")
public class CoreActions {
	private Model model;
	private UserDAO userDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private MessageBean message;
	
	public CoreActions() {
		model = new Model();
		userDAO = model.getUserDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		message = new MessageBean();
	}
	
	@POST
	@Path("createCustomerAccount")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MessageBean createCustomerAccount(CreateCustomerBean ccb,
			@Context HttpServletRequest request) {
		// check if user has logged in
		if (request.getSession().getAttribute("user") == null) {
			message.setMessage("You are not currently logged in");
			return message;
		}
		
		// check if user is a valid employee
		if (!isValidEmployee(request)) {
			return message;
		}
		
		// check if all fields are provided;
		if (!ccb.isValidInput()) {
			message.setMessage("The input you provided is not valid");
			return message;
		}
		
		try {
			// check if about-to-be registered account already exists
			String username = ccb.getUsername();
			if (userDAO.getUserByUsername(username) != null) {
				message.setMessage("The input you provided is not valid");
				return message;
			}
			
			UserBean newUser = new UserBean();
			newUser.setAddress(ccb.getAddress());
			newUser.setCash(Double.parseDouble(ccb.getCash()));
			newUser.setCity(ccb.getCity());
			newUser.setEmail(ccb.getEmail());
			newUser.setFirstName(ccb.getFirstName());
			newUser.setLastName(ccb.getLastName());
			newUser.setPassword(ccb.getPassword());
			newUser.setState(ccb.getState());
			newUser.setZip(ccb.getZip());
			newUser.setUsername(ccb.getUsername());
			Transaction.begin();
			userDAO.create(newUser);
			Transaction.commit();
			
			message.setMessage(ccb.getFirstName() + " was registered successfully");
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
			return message;
		}
		
		return message;
	}
	
	@POST
	@Path("depositCheck")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean depositCheck(DepositBean db, @Context HttpServletRequest request) {
		if (!isValidEmployee(request)) {
			return message;
		} 
		
		if (!db.isValidInput()) {
			message.setMessage("The input you provided is not valid");
			return message;
		}
		
		try {
			String username = db.getUsername();
			
			// lock the db from this point
			Transaction.begin();
			UserBean user = userDAO.getUserByUsername(username);
			if (user == null) {
				message.setMessage("The input you provided is not valid");
				return message;
			}
			
			user.setCash(user.getCash() + Double.parseDouble(db.getCash()));
			userDAO.update(user);
			// release the lock on db
			Transaction.commit();
			message.setMessage("The check was successfully deposited");
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
		}
		
		return message;
	}
	
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean Login(LoginBean loginBean, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			String username = loginBean.getUsername();
			String password = loginBean.getPassword();
			UserBean user = userDAO.getUserByUsername(username);
			
			boolean isVerified = userDAO.verifyUser(username, password);
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
	
	private boolean isValidEmployee(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			message.setMessage("You are not currently logged in");
			return false;
		} else if (!user.getRole().equals("Employee")) {
			message.setMessage("You must be an employee to perform this action");
			return false;
		}
		return true;
	}
	
	private void clearSession(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
	}
}
