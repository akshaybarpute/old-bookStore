package com.freetests4u.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.dao.SellerRequestDao;
import com.freetests4u.model.Book;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.model.User;

@Component
public class SellerRequestDaoImpl implements SellerRequestDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void registerRequest(SellerRequest sr) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		try {
			session.save(sr);
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			session.close();
		}
	}

	@Override
	public SellerRequest getSellerRequestById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		try {
		
		String hql = "from SellerRequest where id=:Id";
		
		SellerRequest sr = (SellerRequest) session.createQuery(hql)
		.setParameter("Id", id)
		.uniqueResult();
		
		User user = sr.getUser();
		Book book = sr.getBook();
		
		System.out.println("user:"+ user.getId());
		System.out.println("book: "+book.getId());
		return sr;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SellerRequest> getSellerRequestForBookId(int id, int limit, int offset) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "from SellerRequest where bookId=:bookId";
			
			return (List<SellerRequest>)session.createQuery(hql)
					.setParameter("bookId", id)
					.setFirstResult(offset)
					.setFetchSize(limit)
					.list();
					
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SellerRequest> getSellerRequestForBookName(String name, int limit, int offset) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "select sr from SellerRequest sr where sr.book.title=:name";
			
			return (List<SellerRequest>)session.createQuery(hql)
					.setParameter("name", name)
					.setFirstResult(offset)
					.setFetchSize(limit)
					.list();
					
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
