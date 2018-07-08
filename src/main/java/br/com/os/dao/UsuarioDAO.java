package br.com.os.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.os.domain.Usuario;
import br.com.os.util.HibernateUtil;

public class UsuarioDAO {

	public void salvar(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(usuario);
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
	
	
	public Usuario autenticar(String nome, String senha) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("usuario","u");
			
			consulta.add(Restrictions.eq("u.nome", nome));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex() ));
			
			Usuario usuario =  (Usuario) consulta.uniqueResult();
			
			return usuario;
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
	}
	
	public Usuario login(String nome, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Usuario.login");
			consulta.setString("nome", nome);
			
			SimpleHash hash = new SimpleHash("md5", senha);
			
			consulta.setString("senha", hash.toHex() );
			
			usuario =  (Usuario) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return usuario;
	}
	
	
	
	public void merge(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		
		try {
			transacao = sessao.beginTransaction();
			sessao.merge(usuario);
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
	
	public void excluir(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao =  null ;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(usuario);
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
	public List<Usuario> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> usuarios = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Usuario.listar");
			usuarios =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return usuarios;
	}
	
	public Usuario buscarPorCodigo(Integer codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("Usuario.buscarPorCodigo");
			consulta.setInteger("codigo",codigo);
			usuario =  (Usuario) consulta.uniqueResult();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return usuario;
	}
	
	
}
