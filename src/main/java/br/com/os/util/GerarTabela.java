package br.com.os.util;

public class GerarTabela {
	
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
