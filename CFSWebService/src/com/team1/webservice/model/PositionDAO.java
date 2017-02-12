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
    
    public PositionBean[] getPositionsOfCustomer(int customerid) throws RollbackException {
        PositionBean[] positions= match(MatchArg.equals("customerID", customerid));
        if (positions.length == 0) {
        	return null;
        }
        return positions;
    }
    
    public PositionBean getPositionById(int customerid, int fundid) throws RollbackException {
    	PositionBean[] positions = match(MatchArg.equals("customerID", customerid),
    			MatchArg.equals("fundID", fundid));
    	if (positions.length == 0) {
    		return null;
    	}
    	return positions[0];
    }
}