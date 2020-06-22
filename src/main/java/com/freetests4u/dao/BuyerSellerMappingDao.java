package com.freetests4u.dao;

import com.freetests4u.model.BuyerSellerMapping;

public interface BuyerSellerMappingDao {

	BuyerSellerMapping getBuyerSellerMappingById(int id);
	BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id);
	BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id);
	void createBuyerSellerMapping(BuyerSellerMapping mapping);
}
