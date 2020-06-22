package com.freetests4u.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.dao.SellerRequestDao;
import com.freetests4u.exceptions.InvalidSellerRequestIdException;
import com.freetests4u.model.Book;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.model.User;

@Component
public class SellerRequestDaoImpl implements SellerRequestDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void registerRequest(SellerRequest sr) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
			session.save(sr);
	}

	@Override
	public SellerRequest getSellerRequestById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		
		String hql = "from SellerRequest where id=:Id AND isActive=true";
		
		SellerRequest sr = (SellerRequest) session.createQuery(hql)
		.setParameter("Id", id)
		.uniqueResult();
		
//		if(sr==null) {
//			throw new InvalidSellerRequestIdException("Seller Request Id not found in db");
//		}
		
		if(sr!=null) {
		User user = sr.getUser();
		Book book = sr.getBook();
		
		System.out.println("user:"+ user.getId());
		System.out.println("book: "+book.getId());
		}
		return sr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SellerRequest> getSellerRequestForBookId(int id, int limit, int offset) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "from SellerRequest where bookId=:bookId AND isActive=true";
			
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SellerRequest> getSellerRequestForBookName(String name, int limit, int offset) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			String hql = "select sr from SellerRequest sr where sr.book.title=:name AND sr.isActive=true";
			
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

	@Transactional
	@Override
	public void markRequestInActive(int id) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
		String hql ="update SellerRequest s set s.isActive=false where s.id=:Id";
		
		session.createQuery(hql)
		.setParameter("Id", id)
		.executeUpdate();
	}

}
