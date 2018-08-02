package br.com.os.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.os.domain.ResponsavelOS;

import br.com.os.util.HibernateUtil;

public class ResponsavelOsDAO {
	
	public void salvar(ResponsavelOS responsavelOS) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(responsavelOS);
			transacao.commit();
			
		}catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally {
			sessao.close();
		}
	}
	
	public void merge(ResponsavelOS responsavelOS) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(responsavelOS);
			transacao.commit();
			
		}catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally {
			sessao.close();
		}	
	}
	
	public void excluir(ResponsavelOS responsavelOS) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(responsavelOS);
			transacao.commit();
			
		}catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		}finally {
			sessao.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ResponsavelOS> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ResponsavelOS> responsaveis = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("ResponsavelOs.listar");
			responsaveis =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return responsaveis;
	}
	
	public ResponsavelOS buscarPorCodigo(Integer codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ResponsavelOS responsavel = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("ResponsavelOs.buscarPorCodigo");
			consulta.setInteger("codigo",codigo);
			responsavel =  (ResponsavelOS) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return responsavel;
	}
	
	
	

}
