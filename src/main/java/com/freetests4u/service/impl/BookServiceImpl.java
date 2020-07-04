package com.freetests4u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.freetests4u.dao.BookDao;
import com.freetests4u.dto.BookSearchRequest;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.exceptions.DuplicateBookEntryException;
import com.freetests4u.model.Book;
import com.freetests4u.model.Store;
import com.freetests4u.repositories.BookRepository;
import com.freetests4u.repositories.StoreRepository;
import com.freetests4u.service.BookService;

@Transactional
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
//	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public void addBook(Book b) throws DuplicateBookEntryException {
		// TODO Auto-generated method stub
				
	  try {
		bookRepository.save(b);
		System.out.println("bookId: "+b.getId());
		Store s = new Store();
		s.setBookId(b.getId());
		s.setBookCount(0);
		s.setDeleted(false);
		storeRepository.save(s);
//		storeDao.createStoreEntry(b.getId());
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
		return bookRepository.findOne(id);
	}

	@Override
	public GenericResponseObject<List<Book>> getBooks(int size, int page) {
		// TODO Auto-generated method stub
		System.out.println("page "+size+" "+page);
		
		PageRequest pr = new PageRequest(page,size, new Sort(Sort.Direction.DESC,"id"));
		Page<Book> p= bookRepository.findAll(pr);
		
		return new GenericResponseObject<>(p.getContent(),"success",false,p.getNumberOfElements(),p.getTotalPages(),page+1);
//		return bookDao.getBookList(limit, offset);
	}

	@Override
	public List<Book> getBook(String name) {
		// TODO Auto-generated method stub
		return (List<Book>) bookRepository.getBook(name);
	}

	@Override
	public List<Book> getBooks(BookSearchRequest br) {
		// TODO Auto-generated method stub
		return bookDao.getBooks(br.getWriter(), br.getLanguage(), br.getCategory(), br.getLimit(), br.getOffset());
	}

}
