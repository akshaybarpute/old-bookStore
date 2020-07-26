package com.freetests4u.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.exceptions.BookNotAvailableException;
import com.freetests4u.exceptions.InvalidSellerRequestIdException;
import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.BuyerRequest;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.model.Store;
import com.freetests4u.repositories.BuyerRequestRepository;
import com.freetests4u.repositories.BuyerSellerMappingRepository;
import com.freetests4u.repositories.SellerRequestRepository;
import com.freetests4u.repositories.StoreRepository;
import com.freetests4u.service.BuyerRequestService;

//if book not available buyer will be notified when the book will available

@Service
public class BuyerRequestServiceImpl implements BuyerRequestService{

//	@Autowired
//	private BuyerRequestDao buyerRequestDao;
	
	@Autowired
	private BuyerRequestRepository buyerRequestRepository;
	
	@Autowired
	private BuyerSellerMappingRepository mappingRepository;
	
	@Autowired
	private SellerRequestRepository sellerRequestRepo;
	
//	@Autowired
//	private BuyerSellerMappingDao buyerSellerMappingDao;
	
//	@Autowired
//	private StoreDao storeDao;
	
	@Autowired
	private StoreRepository storeRepository;

	
//	@Autowired
//	private SellerRequestDao sellerRequestDao;
	
	@Transactional
	@Override
	public void registerBuyerRequest(BuyerRequest br, int sellerRequestId) throws InvalidSellerRequestIdException, BookNotAvailableException {
		// TODO Auto-generated method stub
		if(sellerRequestId==0) {
//		buyerRequestDao.createBuyerRequest(br);
		buyerRequestRepository.save(br);
		// will add functionality to notify buyers when the book will be available in store & with the provided price
		}
		else {
			
			SellerRequest sr = sellerRequestRepo.findOne(sellerRequestId);
//			SellerRequest sr = sellerRequestDao.getSellerRequestById(sellerRequestId);
			if(sr==null) {
				throw new InvalidSellerRequestIdException("Seller Request Id not found");
			}
			if(sr.getBookId()!=br.getBookId()) {
				throw new InvalidSellerRequestIdException("SellerRequest BuyerRequest bookId mismatch");
			}
			
			Store store = storeRepository.findByBookId(br.getBookId());

			if(store.getBookCount()<=0) {
				throw new BookNotAvailableException("Book count is 0 in store");
			}
			
			br.setFullfilled(true);
			buyerRequestRepository.save(br);
//			buyerRequestDao.createBuyerRequest(br);
			BuyerSellerMapping mapping = new BuyerSellerMapping();
			mapping.setBuyerRequestId(br.getId());
			mapping.setSellerRequestId(sellerRequestId);
			mappingRepository.save(mapping);
//			buyerSellerMappingDao.createBuyerSellerMapping(mapping);
			storeRepository.decrementStoreBookCount(br.getBookId());
			sellerRequestRepo.markRequestInActive(sellerRequestId);
//			sellerRequestDao.markRequestInActive(sellerRequestId);
		}
	}

	@Override
	public List<BuyerRequest> getBuyerRequest(int id) {
		// TODO Auto-generated method stub
		List<BuyerRequest> l = new ArrayList<>();
		BuyerRequest br =  buyerRequestRepository.findOne(id);
		l.add(br);
		return l;
	}
	
	
	public BuyerRequest getBuyerRequestById(int id) {
		Optional<BuyerRequest> op = buyerRequestRepository.getBuyerRequestDetailsById(id);
		return op.isPresent() ? op.get(): null;
	}

	
	@Override
	public GenericResponseObject<List<BuyerRequest>> getBuyerRequestsForBook(int bookId, String bookName, int size, int page) throws MalformedRequestExeption {
		// TODO Auto-generated method stub
		
		System.out.println("Size "+size+" page "+page);
		
		if(bookId==0 && bookName==null) {
			throw new MalformedRequestExeption("Either book id or book name required");
		}
		
		if(bookId!=0) {
			BuyerRequest br = new BuyerRequest();
			br.setBookId(bookId);
			ExampleMatcher matcher = ExampleMatcher.matching()
					.withMatcher("bookId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
					.withIgnorePaths("id", "isActive","isFullfilled","bidPrice");
			
			Page<BuyerRequest> p = buyerRequestRepository.findAll(Example.of(br,matcher), new PageRequest(page,size, new Sort(Sort.Direction.DESC,"id")));
			GenericResponseObject<List<BuyerRequest>> obj = new GenericResponseObject<>(p.getContent(),"success",false, p.getNumberOfElements(),p.getTotalPages(),page+1);
			return obj;
		}
		else {
			Page<BuyerRequest> p = buyerRequestRepository.getBuyerRequestListByBookName(bookName, new PageRequest(page,size, new Sort(Sort.Direction.DESC,"id")));
			GenericResponseObject<List<BuyerRequest>> obj = new GenericResponseObject<>(p.getContent(),"success",false, p.getNumberOfElements(),p.getTotalPages(),page+1);
			return obj;
		}
	}

}
