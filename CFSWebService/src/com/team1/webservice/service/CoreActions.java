package com.team1.webservice.service;

import java.util.Calendar;

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

import com.team1.webservice.databean.FundBean;
import com.team1.webservice.databean.PositionBean;
import com.team1.webservice.databean.UserBean;
import com.team1.webservice.jsonbean.BuyFundBean;
import com.team1.webservice.jsonbean.CreateCustomerBean;
import com.team1.webservice.jsonbean.CreateFundBean;
import com.team1.webservice.jsonbean.DepositBean;
import com.team1.webservice.jsonbean.LoginBean;
import com.team1.webservice.jsonbean.MessageBean;
import com.team1.webservice.jsonbean.RequestCheckBean;
import com.team1.webservice.jsonbean.SellFundBean;
import com.team1.webservice.model.FundDAO;
import com.team1.webservice.model.Model;
import com.team1.webservice.model.PositionDAO;
import com.team1.webservice.model.UserDAO;

@Path("/")
public class CoreActions {
	private Model model;
	private UserDAO userDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private MessageBean message;
	
	public CoreActions() {
		model = new Model();
		userDAO = model.getUserDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		message = new MessageBean();
	}
	
	@POST
	@Path("buyFund")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean buyFund(BuyFundBean bfb, @Context HttpServletRequest request) {
		if (!isValidCustomer(request)) {
			return message;
		}
		
		if (!bfb.isValidInput()) {
			message.setMessage("The input you provided is not valid");
			return message;
		}
		
		try {
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			Transaction.begin();
			double balance = user.getCash();
			double buyAmount = Double.parseDouble(bfb.getCashValue());
			
			// check if the customer can afford at least one share of the fund
			String symbol = bfb.getSymbol();
			FundBean fb = fundDAO.getFundBySymbol(symbol);
			if (fb == null) { // check if the fund exists
				message.setMessage("The input you provided is not valid");
				Transaction.commit();
				return message;
			}
			double price = fb.getPrice();
			if (buyAmount / price < 1.0) {
				message.setMessage("You don't have sufficient funds in your account "
						+ "to make this purchase");
				Transaction.commit();
				return message;
			}
			
			// calculate the whole number of shares the customer can buy
			int shares = (int) Math.floor(buyAmount / price);
			user.setCash(balance - shares * price);
			userDAO.update(user);
			
			// update position db or create a new position for the customer
			PositionBean pb = positionDAO.getPositionById(user.getUserID(), fb.getFundID());
			if (pb != null) {
				pb.setShares(pb.getShares() + shares);
				positionDAO.update(pb);
			} else {
				PositionBean newPb = new PositionBean();
				newPb.setCustomerID(user.getUserID());
				newPb.setShares(shares);
				newPb.setFundID(fb.getFundID());
				positionDAO.create(newPb);
			}
			
			Transaction.commit();
			message.setMessage("The fund has been successfully purchased");
			
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
			return message;
		} catch (JSONException e) {
			message.setMessage(e.getMessage());
		}
		
		return message;
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
		} catch (JSONException e) {
			message.setMessage(e.getMessage());
		}
		
		return message;
	}
	
	@POST
	@Path("createFund")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean createFund(CreateFundBean cfb, @Context HttpServletRequest request) {
		if (!isValidEmployee(request)) {
			return message;
		} 
		
		if (!cfb.isValidInput()) {
			message.setMessage("The input you provided is not valid");
			return message;
		}
		
		try {
			if (fundDAO.getFundBySymbol(cfb.getSymbol()) != null) {
				message.setMessage("The input you provided is not valid");
				return message;
			}
			
			FundBean fb = new FundBean();
			fb.setName(cfb.getName());
			fb.setSymbol(cfb.getSymbol());
			fb.setPrice(Double.parseDouble(cfb.getInitValue()));
			fb.setDateCreated(getSystemTime());
			Transaction.begin();
			fundDAO.create(fb);
			Transaction.commit();
			
			message.setMessage("The fund was successfully created");
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
			return message;
		} catch (JSONException e) {
			message.setMessage(e.getMessage());
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
				Transaction.commit();
				return message;
			}
			
			user.setCash(user.getCash() + Double.parseDouble(db.getCash()));
			userDAO.update(user);
			// release the lock on db
			Transaction.commit();
			message.setMessage("The check was successfully deposited");
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
		} catch (JSONException e) {
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
				message.setMessage("There seems to be an issue with " + 
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
	
	@POST
	@Path("requestCheck")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean requestCheck(RequestCheckBean rcb, @Context HttpServletRequest request) {
		if(!isValidCustomer(request)) {
			return message;
		}
		
		if (!rcb.isValidInput()) {
			message.setMessage("The input you provided is not valid");
			return message;
		}
		
		try {
			Transaction.begin();
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			double balance = user.getCash();
			double requestAmount = Double.parseDouble(rcb.getCashValue());
			if (balance < requestAmount) {
				message.setMessage("You don't have sufficient funds in your balance "
						+ "to cover the requested check");
				Transaction.commit();
				return message;
			}
			
			user.setCash(balance - requestAmount);
			userDAO.update(user);
			Transaction.commit();
			message.setMessage("The check has been successfully requested");
			
		} catch (RollbackException e) {
			
		} catch (JSONException e) {
			message.setMessage(e.getMessage());
		}
		
		return message;
	}
	
	@POST
	@Path("sellFund")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MessageBean sellFund(SellFundBean sfb, @Context HttpServletRequest request) {
		if (!isValidCustomer(request)) {
			return message;
		}
		
		if (!sfb.isValidInput()) {
			message.setMessage("The input you provided is not valid");
			return message;
		}
		
		try {
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			Transaction.begin();
			FundBean fb = fundDAO.getFundBySymbol(sfb.getSymbol());
			if (fb == null) {
				message.setMessage("The input you provided is not valid");
				Transaction.commit();
				return message;
			}
			
			PositionBean pb = positionDAO.getPositionById(user.getUserID(), fb.getFundID());
			int shares = (int) Integer.parseInt(sfb.getShares());
			if (pb == null || pb.getShares() < shares) {
				message.setMessage("You don't have that many shares in your portfolio");
				Transaction.commit();
				return message;
			}
			
			double moneyEarned = shares * fb.getPrice();
			user.setCash(user.getCash() + moneyEarned);
			userDAO.update(user);
			pb.setShares(pb.getShares() - shares);
			positionDAO.update(pb);
			Transaction.commit();
			
			message.setMessage("The shares have been successfully sold");
			
		} catch (RollbackException e) {
			message.setMessage(e.getMessage());
			return message;
		} catch (JSONException e) {
			message.setMessage(e.getMessage());
		}
		
		return message;
	}
	private boolean isValidEmployee(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			message.setMessage("You are not currently logged in");
			return false;
		} else if (user.getRole() == null) {
			message.setMessage("You must be an employee to perform this action");
			return false;
		}
		return true;
	}
	
	private boolean isValidCustomer(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			message.setMessage("You are not currently logged in");
			return false;
		} else if (user.getRole() != null) {
			message.setMessage("You must be a customer to perform this action");
			return false;
		}
		return true;
	}
	
	private void clearSession(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
	}
	
	private java.sql.Date getSystemTime() {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = cal.getTime();
		java.sql.Date finalDate = new java.sql.Date(date.getTime());
		return finalDate;
	}
}
