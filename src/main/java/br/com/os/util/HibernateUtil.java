package br.com.os.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration().configure();
		
		Map<String,String> jdbcUrlSettings = new HashMap<String, String>();
		String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
		if (null != jdbcDbUrl) {
		  jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
		}

		ServiceRegistry registry = new StandardServiceRegistryBuilder().
		    configure("hibernate.cfg.xml").
		    applySettings(jdbcUrlSettings).build();
		
		SessionFactory fabrica = configuration.buildSessionFactory(registry);
    	return fabrica;
	}
	
    private static SessionFactory buildSessionFactory_OLD() {
        try {
            Configuration configuration = new Configuration().configure();
        	ServiceRegistry registry = new StandardServiceRegistryBuilder()
        			.applySettings( configuration.getProperties() ).build();
        	
        	
        	SessionFactory fabrica = configuration.buildSessionFactory(registry);
        	return fabrica;
        	
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Necessária para o Jasper que só aceita JDBC
     * @return Connection
     */
    public static Connection getConexao() {
    	Session sessao = getSessionFactory().openSession();
    	Connection conexao =  sessao.doReturningWork(new ReturningWork<Connection>() {
    		public Connection execute(Connection connection) throws SQLException {

    			return connection;
    		}
    		
		});
    	return conexao;
    }
    
    
	
}
