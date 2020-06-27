package com.freetests4u.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.freetests4u.dao.StoreDao;
import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Store;

@Component
public class StoreDaoImpl implements StoreDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void updateStore(int bookId, StoreAction action) {
		// TODO Auto-generated method stub
				
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql ="";
			if(action==StoreAction.INCREMENT) {
			hql = "UPDATE Store s SET s.bookCount=s.bookCount+1";
			}
			else if(action==StoreAction.DECREMENT){
				hql = "UPDATE Store s SET s.bookCount=s.bookCount-1";
			}
			
			int count = session.createQuery(hql).executeUpdate();
			System.out.println("updated count: "+count);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Store getBookCountByBookId(int bookId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql ="from Store where bookId=:bookId";
			
			return (Store) session.createQuery(hql)
					.setParameter("bookId", bookId)
					.uniqueResult();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public void createStoreEntry(int bookId) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();
		
	      TransactionStatus status=null;
	      try {
	         status = TransactionAspectSupport.currentTransactionStatus();
	      } catch (NoTransactionException e) {
	          System.err.println(e);
	      }
	      System.out.println("from createStoreEntry: "+status!=null? "active transaction": "no transaction");

		
			Store store = new Store();
			store.setBookId(bookId);
			store.setBookCount(0);
			store.setDeleted(false);
			session.save(store);
			
	}
	
}
