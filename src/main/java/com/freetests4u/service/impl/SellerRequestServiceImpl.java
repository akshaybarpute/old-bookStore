package com.freetests4u.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.freetests4u.dao.SellerRequestDao;
import com.freetests4u.exceptions.BookNotFoundException;
import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.Book;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.repositories.BookRepository;
import com.freetests4u.repositories.StoreRepository;
import com.freetests4u.service.SellerRequestService;

@Transactional
@Service
public class SellerRequestServiceImpl implements SellerRequestService {

	@Autowired
	private SellerRequestDao sellerRequestDao;
	
//	@Autowired
//	private StoreDao storeDao;
	
	@Autowired
	private StoreRepository storeRepository;

	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void createSellerRequestService(SellerRequest sr) throws Exception {
		// TODO Auto-generated method stub		
		sellerRequestDao.registerRequest(sr);
		
		try {
			
		Book book =  bookRepository.findOne(sr.getBookId());
		
		if(book==null||book.getId()==0) {
			throw new BookNotFoundException("No Such Book exists");
		}
		sr.setActive(true);
		sellerRequestDao.registerRequest(sr);
		storeRepository.incrementStoreBookCount(sr.getBookId());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error while registering seller request");
		}
	}

	@Override
	public SellerRequest getSellerRequestById(int id) {
		// TODO Auto-generated method stub
		return sellerRequestDao.getSellerRequestById(id);
	}

	@Override
	public List<SellerRequest> getSellerRequestListByBook(int limit, int offset, int bookId, String bookName) throws MalformedRequestExeption {
		// TODO Auto-generated method stub
		if(bookId==0&&bookName==null) {
			throw new MalformedRequestExeption("either bookId or bookName required");
		}
		if(bookId!=0) {
			return sellerRequestDao.getSellerRequestForBookId(bookId, limit, offset);
		}
		else {
			return sellerRequestDao.getSellerRequestForBookName(bookName, limit, offset);
		}
	}

}
