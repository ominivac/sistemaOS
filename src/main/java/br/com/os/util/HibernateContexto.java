package br.com.os.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getSessionFactory();
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
		
	}

}
