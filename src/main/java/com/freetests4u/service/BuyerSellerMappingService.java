package com.freetests4u.service;

import com.freetests4u.model.BuyerSellerMapping;

public interface BuyerSellerMappingService {

	BuyerSellerMapping getBuyerSellerMappingById(int id);
	BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id);
	BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id);
}
