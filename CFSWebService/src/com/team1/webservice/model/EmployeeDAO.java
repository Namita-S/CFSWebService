package com.team1.webservice.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.team1.webservice.databean.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
    public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(EmployeeBean.class, tableName, cp);
    }
    
    public EmployeeBean[] getEmployees() throws RollbackException {
        EmployeeBean[] employees = match();
        return employees;
    }
    public EmployeeBean getEmployeeByUserName(String username) throws RollbackException {
        EmployeeBean[] employees = match(MatchArg.equals("username", username));
        if (employees.length == 0) {
        	return null;
        }
        EmployeeBean employee = employees[0];    // since the username is unique, this guarantees that this is
        										 // the employee we are looking for 
        return employee;
    }
}

