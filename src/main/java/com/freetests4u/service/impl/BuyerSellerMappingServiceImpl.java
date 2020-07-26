package com.freetests4u.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.freetests4u.dto.TransactionListForUser;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.repositories.BuyerSellerMappingRepository;
import com.freetests4u.service.BuyerSellerMappingService;

@Transactional
@Service
public class BuyerSellerMappingServiceImpl implements BuyerSellerMappingService{

//	@Autowired
//	BuyerSellerMappingDao mappingDao;
	
	@Autowired
	BuyerSellerMappingRepository mappingRepository; 
	
	@Override
	public BuyerSellerMapping getBuyerSellerMappingById(int id) {
		// TODO Auto-generated method stub
		
		return mappingRepository.getOne(id);
//		return mappingDao.getBuyerSellerMappingById(id);
	}

	
	@Override
	public BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id) {
		// TODO Auto-generated method stub
		
		BuyerSellerMapping map = new BuyerSellerMapping();
		map.setBuyerRequestId(id);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("requestId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withIgnorePaths("transId","sellerRequestId","rejectionCode");
		return mappingRepository.findOne(Example.of(map, matcher));
//		return mappingDao.getBuyerSellerMappingByBuyerRequestId(id);
	}

	@Override
	public BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id) {
		// TODO Auto-generated method stub will revisit
		BuyerSellerMapping map = new BuyerSellerMapping();
		map.setSellerRequestId(id);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("sellerRequestId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withIgnorePaths("transId","rejectionCode","requestId");
		
		return mappingRepository.findOne(Example.of(map, matcher));
//		return mappingDao.getBuyerSellerMappingBySellerRequestId(id);
	}

	
	@Override
	public TransactionListForUser getPendingTransListForUser(String userId) {
		// TODO Auto-generated method stub
		
		PageRequest p = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"id"));
		Optional<Page<BuyerSellerMapping>> opSell = mappingRepository.getTransactionsForBuyerId(userId, "pending", p);
		Optional<Page<BuyerSellerMapping>> opBuy = mappingRepository.getTransactionsForSellerId(userId, "pending", p);		
		TransactionListForUser l= new TransactionListForUser();
//		
		if(opSell.isPresent()) {
			Page<BuyerSellerMapping> p1 = opSell.get();
			l.setPendingSellTransactions(p1.getContent());
		}
		else if(opBuy.isPresent()) {
			Page<BuyerSellerMapping> p2 = opSell.get();
			l.setPendingBuyingTransactions(p2.getContent());
		}
		
		return l;
	}
	
	

	@Override
	public TransactionListForUser getCompletedTransListForUser(String userId) {
		// TODO Auto-generated method stub
		
		PageRequest p = new PageRequest(0, 10, new Sort(Sort.Direction.DESC,"id"));
		Optional<Page<BuyerSellerMapping>> opSell = mappingRepository.getTransactionsForBuyerId(userId, "deliverd", p);
		Optional<Page<BuyerSellerMapping>> opBuy = mappingRepository.getTransactionsForSellerId(userId, "deliverd", p);		

		
		TransactionListForUser l= new TransactionListForUser();
		
		if(opSell.isPresent()) {
			Page<BuyerSellerMapping> p1 = opSell.get();
			l.setDeliveredSellTransactions(p1.getContent());
		}
		else if(opBuy.isPresent()) {
			Page<BuyerSellerMapping> p2 = opSell.get();
			l.setDeliverdBuyingTransactions(p2.getContent());
		}

		return l;
	}
	
}
