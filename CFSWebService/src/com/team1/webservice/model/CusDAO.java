package com.team1.webservice.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.team1.webservice.databean.CustomerBean;

public class CusDAO extends GenericDAO<CustomerBean> {
    public CusDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(CustomerBean.class, tableName, cp);
    }
    
    public CustomerBean[] getCustomers() throws RollbackException {
        CustomerBean[] customers = match();
        if (customers.length == 0) {
        	return null;
        }
        return customers;
    }
    
    public CustomerBean getCustomerByUserName(String username) throws RollbackException {
        CustomerBean[] customers = match(MatchArg.equals("username", username));
        if (customers.length == 0) {
        	return null;
        }
        CustomerBean customer = customers[0];    // since the username is unique, this guarantees that this is
        										 // the customer we are looking for 
        return customer;
    }
    
    public CustomerBean getCustomerById(int customerid) throws RollbackException {
        CustomerBean[] customers = match(MatchArg.equals("customerid", customerid));
        if (customers.length == 0) {
        	return null;
        }
        CustomerBean customer = customers[0];
        return customer;
    }
}
