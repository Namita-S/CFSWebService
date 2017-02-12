package com.team1.webservice.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.team1.webservice.databean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	public PositionDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
    
    public PositionBean[] getPositionsOfCustomer(int customerID) throws RollbackException {
        PositionBean[] positions= match(MatchArg.equals("customerID", customerID));
        if (positions.length == 0) {
        	return null;
        }
        return positions;
    }
    
    public PositionBean getPositionById(int customerID, int fundID) throws RollbackException {
    	PositionBean[] positions = match(MatchArg.equals("customerID", customerID),
    			MatchArg.equals("fundID", fundID));
    	if (positions.length == 0) {
    		return null;
    	}
    	return positions[0];
    }
}