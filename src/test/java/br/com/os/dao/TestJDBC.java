package br.com.os.dao;

import java.sql.Connection;

import br.com.os.util.HibernateUtil;

public class TestJDBC {

	public static void main(String[] args) {
	
		
		Connection conexao  = HibernateUtil.getConexao();
		if(conexao.equals(null) ) {
			System.out.println("conexao nula");
		}else {
			System.out.println("conexao ok");
		}
		
		

	}

}
