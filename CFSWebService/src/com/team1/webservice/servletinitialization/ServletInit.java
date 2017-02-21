package com.team1.webservice.servletinitialization;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.team1.webservice.model.Model;

public class ServletInit implements ServletContextListener {
    private Model model;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
			model = new Model();
			
	}
}
