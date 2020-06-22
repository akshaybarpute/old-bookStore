package com.freetests4u.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.dao.BuyerRequestDao;
import com.freetests4u.model.Book;
import com.freetests4u.model.BuyerRequest;
import com.freetests4u.model.User;

@Component
public class BuyerRequestDaoImpl implements BuyerRequestDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void createBuyerRequest(BuyerRequest br) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		session.save(br);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuyerRequest> getBuyerRequest(int id) {
		// TODO Auto-generated method stub
		
		String hql = "from BuyerRequest where id=1";
		Session session = sessionFactory.openSession();
		return (List<BuyerRequest>)session.createQuery(hql)
//		.setParameter("id",id)
		.list();
	}

	@Override
	public BuyerRequest getBuyerRequestDetailsById(int id) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		
		String hql = "from BuyerRequest where id=:Id";
		BuyerRequest br = (BuyerRequest) session.createQuery(hql)
				.setParameter("Id", id)
				.uniqueResult();
		
		User user = br.getUser();
		Book book =  br.getBook();
		
		System.out.println("user: "+user.getName()+"book: "+book.getTitle());
		return br;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuyerRequest> getBuyerRequestListByBookId(int bookId,int limit, int offset) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		
		String hql = "from BuyerRequest where bookId=:bookId order by id desc";
//		String hql = "from BuyerRequest where bookId="+bookId +" order by id desc";
		
//		return 
		List<BuyerRequest> brList=session.createQuery(hql)
		.setParameter("bookId", bookId)
		.setFirstResult(offset)
		.setMaxResults(limit)
		.list();
		
		for(BuyerRequest br:brList) {
			System.out.println(br.getBidPrice());
		}
		return brList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuyerRequest> getBuyerRequestListByBookId(String bookName, int offset, int limit) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		String hql = "select b from BuyerRequest b"
				+ " INNER JOIN b.book where b.book.title=:bookName order by b.id desc";
		
		return session.createQuery(hql)
				.setParameter("bookName", bookName)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.list();
	}

}
