package br.com.os.main;

import org.hibernate.Session;

import br.com.os.util.HibernateUtil;

public class HibernateUtilTest {

	public static void main(String[] args) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.close();
		
		//HibernateUtil.getSessionFactory().close();
		
		
	}
	
}
