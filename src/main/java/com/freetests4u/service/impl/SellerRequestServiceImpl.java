package com.freetests4u.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.freetests4u.exceptions.BookNotFoundException;
import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.Book;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.repositories.BookRepository;
import com.freetests4u.repositories.SellerRequestRepository;
import com.freetests4u.repositories.StoreRepository;
import com.freetests4u.service.SellerRequestService;

@Transactional
@Service
public class SellerRequestServiceImpl implements SellerRequestService {

//	@Autowired
//	private SellerRequestDao sellerRequestDao;
	
	@Autowired
	private SellerRequestRepository sellerRequestRepo;
	
//	@Autowired
//	private StoreDao storeDao;
	
	@Autowired
	private StoreRepository storeRepository;

	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void createSellerRequestService(SellerRequest sr) throws Exception {
		// TODO Auto-generated method stub		
		
		try {
			
		Book book =  bookRepository.findOne(sr.getBookId());
		
		if(book==null||book.getId()==0) {
			throw new BookNotFoundException("No Such Book exists");
		}
		sr.setActive(true);
//		sellerRequestDao.registerRequest(sr);
		sellerRequestRepo.save(sr);
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
		return sellerRequestRepo.findOne(id);
//		return sellerRequestDao.getSellerRequestById(id);
	}

	@Override
	public List<SellerRequest> getSellerRequestListByBook(int limit, int offset, int bookId, String bookName) throws MalformedRequestExeption {
		// TODO Auto-generated method stub
		
		ExampleMatcher matcher;
		if(bookId==0&&bookName==null) {
			throw new MalformedRequestExeption("either bookId or bookName required");
		}
		if(bookId!=0) {
			SellerRequest sr = new SellerRequest();
			sr.setBookId(bookId);
			sr.setActive(true);
			matcher = ExampleMatcher.matching()
					.withMatcher("bookId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
					.withIgnorePaths("id", "sellerPrice","actualPrice","userId","dispatchAddressId","weight");
			
			Page<SellerRequest> p = sellerRequestRepo.findAll(Example.of(sr, matcher), new PageRequest(offset,limit,new Sort(Sort.Direction.DESC,"id")));
			return p.getContent();
//			return sellerRequestDao.getSellerRequestForBookId(bookId, limit, offset);
		}
		else {			
			
			Page<SellerRequest> p = sellerRequestRepo.getSellerRequestsForBook(bookName, new PageRequest(offset,limit, new Sort(Sort.Direction.DESC,"id")));
			return p.getContent();
			//			return sellerRequestDao.getSellerRequestForBookName(bookName, limit, offset);
		}
		
		
	}

}
