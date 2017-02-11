package com.team1.webservice.model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.team1.webservice.databean.EmployeeBean;

public class Model implements ServletContextListener {
	private EmployeeDAO employeeDAO;
	private CusDAO customerDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			String jdbcDriver = "com.mysql.jdbc.Driver";
			String jdbcURL = "jdbc:mysql:///test?useSSL=false";
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			employeeDAO  = new EmployeeDAO(pool, "employee");
			customerDAO = new CusDAO(pool, "customer");
			fundDAO = new FundDAO(pool, "fund");
			positionDAO = new PositionDAO(pool, "position");
			transactionDAO = new TransactionDAO(pool, "transaction");
			
			generateInitEmployee();
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		} 
		System.out.println("Connection with MySQL has been established");
	}
	
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public CusDAO getCustomerDAO() {
		return customerDAO;
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
			EmployeeBean[] employees = employeeDAO.match();
			if (employees.length == 0) {
				EmployeeBean initEmployee = new EmployeeBean();
				initEmployee.setFirstname("Admin");
				initEmployee.setLastname("Root");
				initEmployee.setUsername("admin");
				initEmployee.setPassword("admin");
				employeeDAO.create(initEmployee);
			}
		} catch (RollbackException e) {
			
		}
	}
}
