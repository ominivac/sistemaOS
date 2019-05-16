package br.com.os.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.filter.OSFilter;
import br.com.os.util.HibernateUtil;

public class OsDAO {

	//@Deprecated
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
				
				
				if(itemCorrente.getCodigoItem() == null) {
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
	
	private static Timestamp localToTimeStamp(LocalDate date){
	      return Timestamp.from(date.atStartOfDay().toInstant(ZoneOffset.UTC));
	}
	
	@SuppressWarnings("unchecked")
	public List<OS> buscarPorData(String dinicial, String dfinal){
		List<OS> listaOS = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT os FROM OS os ");
		
		if(dinicial != null && dfinal !=null) {
			sql.append("WHERE os.dataLancamento >= ");
			sql.append(":'dataInicial' AND os.dataLancamento <= :'dataFinal' ");
			System.out.println("entrou nao null " + dinicial +" " + dfinal);
			
		}
		
		sql.append("ORDER BY os.dataLancamento");
		
		
		try {
			Query consulta = sessao.createQuery(sql.toString());
			
            consulta.setParameter("dataInicial", dinicial );
			consulta.setParameter("dataFinal", dfinal );
			
			listaOS = consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaOS;
	}
	
	public List<OS> buscarEntreData(){
		List<OS> listaOS = null;
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT os FROM OS os ");
		sql.append("WHERE os.dataLancamento >= '01/08/2018' AND os.dataLancamento <= '9/8/2018' ");
		sql.append("ORDER BY os.dataLancamento");
		
		try {
			Query consulta = sessao.createQuery(sql.toString());
			
			listaOS = consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaOS;
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
	
	@SuppressWarnings("unchecked")
	public List<OS> buscarPorAtividade(String atividade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<OS> listaOS  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("OS.buscarPorServico");
			consulta.setString("atividade", "%" +atividade + "%");
			listaOS =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaOS;
	}
	
	public List<OS> buscarPorAtividadeItem(String atividade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<OS> listaOS  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("OS.buscarPorServicoItem");
			consulta.setString("atividade", "%" +atividade + "%");
			listaOS =  consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		}finally {
			sessao.close();
		}
		return listaOS;
	}
	
	
	
	
	public List<OS> buscarEntreDatas(String data_inicial, String data_final){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<OS> listaOS  = null;
		Query consulta = null;
		
		
		try {
			consulta = sessao.getNamedQuery("OS.buscarEntreDatas");
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dinicial =  null;
			Date dfinal =  null;
			
			dinicial = inputFormat.parse(data_inicial);
			dfinal = inputFormat.parse(data_final);
			
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			consulta.setTimestamp("data_inicial",dinicial );
			consulta.setTimestamp("data_final",dfinal);
			
			listaOS = consulta.list();
			
		}catch (RuntimeException ex) {

			throw ex;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sessao.close();
		}
		return listaOS;
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
