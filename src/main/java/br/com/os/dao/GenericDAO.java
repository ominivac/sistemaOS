package br.com.os.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.os.util.HibernateUtil;

public class GenericDAO<Entidade>{
	
	private Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType)getClass().getGenericSuperclass() )
				.getActualTypeArguments()[0];
	}
	
	
	public void salvar(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(entidade);
			transaction.commit();
			
		} catch (RuntimeException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	public void merge(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.merge(entidade);
			transaction.commit();
			
		} catch (RuntimeException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	
	public void editar(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(entidade);
			transaction.commit();
			
		} catch (RuntimeException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	
	public void excluir(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.delete(entidade);
			transaction.commit();
			
		} catch (RuntimeException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			
			return resultado;
			
		}catch (RuntimeException e) {
			throw e;
		}finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscarPorNome(String nome){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.like("nome", nome));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			
			return resultado;
			
		}catch (RuntimeException e) {
			throw e;
		}finally {
			sessao.close();
		}
	}
	
	
	
	
	
}
