package com.freetests4u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.BuyerRequestDao;
import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.BuyerRequest;
import com.freetests4u.service.BuyerRequestService;


@Service
public class BuyerRequestServiceImpl implements BuyerRequestService{

	@Autowired
	private BuyerRequestDao buyerRequestDao;
	
	@Override
	public void registerBuyerRequest(BuyerRequest br) {
		// TODO Auto-generated method stub
		buyerRequestDao.createBuyerRequest(br);
	}

	@Override
	public List<BuyerRequest> getBuyerRequest(int id) {
		// TODO Auto-generated method stub
		
		return buyerRequestDao.getBuyerRequest(id);
	}
	
	public BuyerRequest getBuyerRequestById(int id) {
		
		return buyerRequestDao.getBuyerRequestDetailsById(id);
	}

	
	@Override
	public List<BuyerRequest> getBuyerRequestsForBook(int bookId, String bookName, int limit, int offset) throws MalformedRequestExeption {
		// TODO Auto-generated method stub
		
		if(bookId==0 && bookName==null) {
			throw new MalformedRequestExeption("Either book id or book name required");
		}
		
		if(bookId!=0) {
			
			return buyerRequestDao.getBuyerRequestListByBookId(bookId, limit, offset);
		}
		else {
			return buyerRequestDao.getBuyerRequestListByBookId(bookName, limit, offset);
		}
	}

}
