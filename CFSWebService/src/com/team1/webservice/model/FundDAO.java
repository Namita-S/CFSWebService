package com.team1.webservice.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.team1.webservice.databean.FundBean;

public class FundDAO extends GenericDAO<FundBean> {
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}

	public FundBean getFundById(int fundid) throws RollbackException {
		FundBean[] fund = match(MatchArg.equals("fundid", fundid));
		if (fund.length == 0) {
			return null;
		}
		return fund[0];
	}

	public FundBean getFundBySymbol(String symbol) throws RollbackException {
		FundBean[] fund = match(MatchArg.equals("symbol", symbol));
		if (fund.length == 0) {
			return null;
		}
		return fund[0];
	}

	public FundBean getFundByName(String name) throws RollbackException {
		FundBean[] fund = match(MatchArg.equals("name", name));
		if (fund.length == 0) {
			return null;
		}
		return fund[0];
	}

	public String getFundsSymbol(int fundid) throws RollbackException {
		FundBean[] fundsymbol = match(MatchArg.equals("fundid", fundid));
		if (fundsymbol.length == 0) {
			return null;
		}
		return fundsymbol[0].getSymbol();
	}

	public FundBean[] getAllFunds() throws RollbackException {
		FundBean[] fb = match();
		if (fb.length == 0) {
			return null;
		}
		return fb;
	}
}
