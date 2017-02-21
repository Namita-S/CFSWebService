package com.team1.webservice.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.team1.webservice.databean.UserBean;

public class Model {
	private UserDAO userDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public Model() {
		try {
			String jdbcDriver = "com.mysql.jdbc.Driver";
			String jdbcURL = "jdbc:mysql:///test?useSSL=false";
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			userDAO  = new UserDAO(pool, "user");
			fundDAO = new FundDAO(pool, "fund");
			positionDAO = new PositionDAO(pool, "position");
			transactionDAO = new TransactionDAO(pool, "transaction");

			generateInitEmployee();
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		} 
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
	
	private void generateInitEmployee() {
		try {
			Transaction.begin();
			UserBean[] users = userDAO.match();
			if (users.length == 0) {
				UserBean initEmployee = new UserBean();
				initEmployee.setFirstName("Jane");
				initEmployee.setLastName("Admin");
				initEmployee.setUsername("jadmin");
				initEmployee.setPassword("admin");
				initEmployee.setAddress("123 Main street");
				initEmployee.setCity("Pittsburgh");
				initEmployee.setState("Pa");
				initEmployee.setZip("15143");
				initEmployee.setRole("Employee");
				userDAO.create(initEmployee);
			}
			Transaction.commit();
		} catch (RollbackException e) {
			
		}
	}
}
