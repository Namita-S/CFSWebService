package com.team1.webservice.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.team1.webservice.databean.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
    public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserBean.class, tableName, cp);
    }
    
    public UserBean getUserByUsername(String username) throws RollbackException {
        UserBean[] users = match(MatchArg.equals("username", username));
        if (users.length == 0) {
        	return null;
        }
        UserBean user = users[0];    // since the username is unique, this guarantees that this is
        										 // the employee we are looking for 
        return user;
    }
    
    public boolean verifyUser(String username, String password) throws RollbackException {
        UserBean[] users = match(MatchArg.equals("username", username),
        						 MatchArg.equals("password", password));
        if (users.length == 0) {
        	return false;
        } else {
        	return true;
        }
    }
}

