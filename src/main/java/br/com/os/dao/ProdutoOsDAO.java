package br.com.os.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.os.domain.ProdutoOS;
import br.com.os.util.HibernateUtil;

public class ProdutoOsDAO {

	
	public void salvar(ProdutoOS produtoOS) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(produtoOS);
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
	
	public void merge(ProdutoOS produtoOS) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(produtoOS);
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
	
	public void excluir(ProdutoOS produtoOS) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(produtoOS);
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
	public List<ProdutoOS> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<ProdutoOS> produtos = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("ProdutoOS.listar");
			produtos =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return produtos;
	}
	
	public ProdutoOS buscarPorCodigo(Integer codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		ProdutoOS produtoOS  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("ProdutoOS.buscarPorCodigo");
			consulta.setInteger("codigo",codigo);
			produtoOS =  (ProdutoOS) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return produtoOS;
	}
	
	
}
