package com.freetests4u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.annotation.Transactional;
import com.freetests4u.dao.BookDao;
import com.freetests4u.dao.StoreDao;
import com.freetests4u.dto.BookSearchRequest;
import com.freetests4u.exceptions.DuplicateBookEntryException;
import com.freetests4u.model.Book;
import com.freetests4u.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	StoreDao storeDao;

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public void addBook(Book b) throws DuplicateBookEntryException {
		// TODO Auto-generated method stub
		
//		List<Book> old = bookDao.getBook(b.getTitle().toLowerCase());
//		if(old.size()>0) {
//			throw new DuplicateBookEntryException("Book with same name already exists");
//		}		
		
//		Sessio	
		
	      TransactionStatus status=null;
	      try {
	         status = TransactionAspectSupport.currentTransactionStatus();
	      } catch (NoTransactionException e) {
	          System.err.println(e);
	      }
	      System.out.println(status!=null? "active transaction": "no transaction");

	      try {
//		storeDao.createStoreEntry(13);
		bookDao.addBook(b);
		storeDao.createStoreEntry(b.getId());
	      }
	      catch(Exception e) {
	    	  System.out.println("throwing exception");
	    	  e.printStackTrace();
	    	  throw e;
	      }
	}

	@Override
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return bookDao.getBook(id);
	}

	@Override
	public List<Book> getBooks(int limit, int offset) {
		// TODO Auto-generated method stub
		
		return bookDao.getBookList(limit, offset);
	}

	@Override
	public List<Book> getBook(String name) {
		// TODO Auto-generated method stub
		return bookDao.getBook(name);
	}

	@Override
	public List<Book> getBooks(BookSearchRequest br) {
		// TODO Auto-generated method stub
		return bookDao.getBooks(br.getWriter(), br.getLanguage(), br.getCategory(), br.getLimit(), br.getOffset());
	}

}
