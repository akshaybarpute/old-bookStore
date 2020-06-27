package com.freetests4u.dao.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.annotation.Transactional;

import com.freetests4u.dao.BookDao;
import com.freetests4u.model.Book;


@Component
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		 
		
	      TransactionStatus status=null;
	      try {
	         status = TransactionAspectSupport.currentTransactionStatus();
	      } catch (NoTransactionException e) {
	          System.err.println(e);
	      }
	      System.out.println("from addBook: "+status!=null? "active transaction": "no transaction");

		
		Session session = sessionFactory.getCurrentSession();
		
			session.save(book);
			return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBookList(int limit, int offset) {
		// TODO Auto-generated method stub
						
		int finalLimit = limit!=0?limit:10;
		int finalOffset = offset!=0?offset:0;
				
		return (List<Book>) sessionFactory.openSession().createCriteria(Book.class)
//				.createAlias("Book.id", "BookId")
				.addOrder(Order.desc("id"))
				.setFirstResult(finalOffset)
				.setMaxResults(finalLimit)
				.list();
	}

	@Override
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		String hqlQuery = "from Book Where id=:id";
		return (Book) session.createQuery(hqlQuery).setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBook(String name) {
		// TODO Auto-generated method stub
		
		String hqlQuery = "from Book Where title=:title";
		return (List<Book>) sessionFactory.openSession().createQuery(hqlQuery).setParameter("title", name).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks(String writer, String language, String category, int limit, int offset) {
		// TODO Auto-generated method stub

		Book b = new Book();		
		b.setWriter(writer);
		b.setLanguage(language);
		b.setCategory(category);
		
		boolean flag =false;
		
		String hqlQuery = " from Book WHERE ";
		
		if(writer!=null && !writer.isEmpty()) {
			hqlQuery = hqlQuery + "writer=:writer ";
			flag=true;
		}
		
		if(language!=null && !language.isEmpty()) {
			hqlQuery = flag==true?  hqlQuery + "AND language=:language ":hqlQuery + "language=:language";
			flag=true;
		}

		if(category!=null && !category.isEmpty()) {
			hqlQuery = flag==true?  hqlQuery + "AND category=:category ":hqlQuery + "category=:category ";
			flag=true;
		}
		
		hqlQuery = hqlQuery + " Order By id desc";
		
		return (List<Book>) sessionFactory.openSession().createQuery(hqlQuery).setProperties(b)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.list();
		
	}

}
