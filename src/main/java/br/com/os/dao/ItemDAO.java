package br.com.os.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.util.HibernateUtil;

public class ItemDAO {

	

	public void salvar(Item item ) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(item);
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
	public List<Item> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Item> listaItem= null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Item.listar");
			listaItem =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaItem;
	}
	
	public Item buscarPorCodigo(Integer codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Item item  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Item.buscarPorCodigo");
			consulta.setInteger("codigo",codigo);
			item =  (Item) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return item;
	}
	
	public void excluir(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(item);
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
	
	public void editar(Item item) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(item);
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
