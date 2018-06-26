package br.com.os.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.os.domain.Item;
import br.com.os.domain.OS;
import br.com.os.util.HibernateUtil;


public class OsDAO extends GenericDAO<OS>{
	
	public void save(OS os, List<Item> itensOs) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(os);
			
			for(int pos=0 ; pos < itensOs.size() ; pos++) {
				Item item = itensOs.get(pos);
				item.setOs(os);
				
				session.save(item);
				
			}
			
			
			transaction.commit();
			
		}catch (RuntimeException e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
		
	}

}
