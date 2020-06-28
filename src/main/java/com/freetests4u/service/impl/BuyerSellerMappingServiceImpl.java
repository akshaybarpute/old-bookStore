package com.freetests4u.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.BuyerSellerMappingDao;
import com.freetests4u.dto.TransactionListForUser;
import com.freetests4u.dto.TransactionType;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.service.BuyerSellerMappingService;

@Transactional
@Service
public class BuyerSellerMappingServiceImpl implements BuyerSellerMappingService{

	@Autowired
	BuyerSellerMappingDao mappingDao;
	
	@Override
	public BuyerSellerMapping getBuyerSellerMappingById(int id) {
		// TODO Auto-generated method stub
		return mappingDao.getBuyerSellerMappingById(id);
	}

	@Override
	public BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id) {
		// TODO Auto-generated method stub
		return mappingDao.getBuyerSellerMappingByBuyerRequestId(id);
	}

	@Override
	public BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id) {
		// TODO Auto-generated method stub
		return mappingDao.getBuyerSellerMappingBySellerRequestId(id);
	}

	@Override
	public TransactionListForUser getPendingTransListForUser(String userId) {
		// TODO Auto-generated method stub
		
		List<BuyerSellerMapping> l1= mappingDao.getActiveBuyerSellerMappingForBuyerId(userId, TransactionType.PENDING);
		List<BuyerSellerMapping> l2 = mappingDao.getActiveBuyerSellerMappingForSellerId(userId, TransactionType.PENDING);
		
		TransactionListForUser l= new TransactionListForUser();
		
		l.setPendingBuyingTransactions(l1);
		l.setPendingSellTransactions(l2);
		return l;
	}

}
