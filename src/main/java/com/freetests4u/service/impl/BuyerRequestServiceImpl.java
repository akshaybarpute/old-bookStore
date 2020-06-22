package com.freetests4u.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.BuyerRequestDao;
import com.freetests4u.dao.BuyerSellerMappingDao;
import com.freetests4u.dao.SellerRequestDao;
import com.freetests4u.dao.StoreDao;
import com.freetests4u.dto.StoreAction;
import com.freetests4u.exceptions.BookNotAvailableException;
import com.freetests4u.exceptions.InvalidSellerRequestIdException;
import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.BuyerRequest;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.model.Store;
import com.freetests4u.service.BuyerRequestService;

//if book not available buyer will be notified when the book will available

@Service
public class BuyerRequestServiceImpl implements BuyerRequestService{

	@Autowired
	private BuyerRequestDao buyerRequestDao;
	
	@Autowired
	private BuyerSellerMappingDao buyerSellerMappingDao;
	
	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private SellerRequestDao sellerRequestDao;
	
	@Transactional
	@Override
	public void registerBuyerRequest(BuyerRequest br, int sellerRequestId) throws InvalidSellerRequestIdException, BookNotAvailableException {
		// TODO Auto-generated method stub
		if(sellerRequestId==0) {
		buyerRequestDao.createBuyerRequest(br);
		// will add functionality to notify buyers when the book will be available in store & with the provided price
		}
		else {
			
			SellerRequest sr = sellerRequestDao.getSellerRequestById(sellerRequestId);
			if(sr==null) {
				throw new InvalidSellerRequestIdException("Seller Request Id not found");
			}
			if(sr.getBookId()!=br.getBookId()) {
				throw new InvalidSellerRequestIdException("SellerRequest BuyerRequest bookId mismatch");
			}
			Store store = storeDao.getBookCountByBookId(br.getBookId());

			if(store.getBookCount()<=0) {
				throw new BookNotAvailableException("Book count is 0 in store");
			}
			
			br.setFullfilled(true);
			buyerRequestDao.createBuyerRequest(br);
			BuyerSellerMapping mapping = new BuyerSellerMapping();
			mapping.setBuyerRequestId(br.getId());
			mapping.setSellerRequestId(sellerRequestId);
			buyerSellerMappingDao.createBuyerSellerMapping(mapping);
			storeDao.updateStore(br.getBookId(), StoreAction.DECREMENT);
			sellerRequestDao.markRequestInActive(sellerRequestId);
		}
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
