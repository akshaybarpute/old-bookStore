package com.freetests4u.dao;

import java.util.List;

import com.freetests4u.dto.TransactionType;
import com.freetests4u.model.BuyerSellerMapping;

public interface BuyerSellerMappingDao {

	BuyerSellerMapping getBuyerSellerMappingById(int id);
	BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id);
	BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id);
	void createBuyerSellerMapping(BuyerSellerMapping mapping);
	List<BuyerSellerMapping> getActiveBuyerSellerMappingForBuyerId(String userId, TransactionType type);
	List<BuyerSellerMapping> getActiveBuyerSellerMappingForSellerId(String userId,TransactionType type);
}