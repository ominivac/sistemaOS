package br.com.os.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.os.domain.Item;
import br.com.os.domain.OS;

import br.com.os.util.HibernateUtil;

public class OsDAO {

	@Deprecated
	public void salvar(OS os) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(os);
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
	
	public void salvar(OS os, List<Item> itens) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(os);
			
			for(int posicao = 0 ; posicao < itens.size() ; posicao++) {
				Item itemCorrente = itens.get(posicao);
				itemCorrente.setOs(os);

				sessao.save(itemCorrente);
			}
			
			
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
	
	
	public void editar(OS os) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(os);
			
			ItemDAO idao = new ItemDAO();
			
			for(int posicao = 0 ; posicao < os.getItensOs().size() ; posicao++) {
				Item itemCorrente = os.getItensOs().get(posicao);
				//se for item novo deve salvar
				
				
				if(itemCorrente.getCodigo() == null) {
					idao.salvar(itemCorrente);
				}else {
					idao.update(os.getItensOs().get(posicao) );
				}
				
			}
			
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
	
	/**
	 * @param os
	 * @param itens
	 */
	public void merge(OS os, List<Item> itens) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(os);
			
			for(int posicao = 0 ; posicao < itens.size() ; posicao++) {
				Item itemCorrente = itens.get(posicao);
				itemCorrente.setOs(os);
				
				sessao.merge(itemCorrente);
			}
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
	public List<OS> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<OS> listaOs = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("OS.listar");
			listaOs =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaOs;
	}
	
	@SuppressWarnings("unchecked")
	public List<OS> listarbYDate(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<OS> listaOs = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("OS.listarByDateDesc");
			listaOs =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaOs;
	}
	
	public OS buscarPorCodigo(Integer codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		OS os  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("OS.buscarPorCodigo");
			consulta.setInteger("codigo",codigo);
			os =  (OS) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return os;
	}
	
	public void excluir(OS os) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(os);
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
	
	public void merge(OS os) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(os);
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
	
	
}
