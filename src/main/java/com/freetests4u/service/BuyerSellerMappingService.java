package com.freetests4u.service;

import com.freetests4u.dto.TransactionListForUser;
import com.freetests4u.model.BuyerSellerMapping;

public interface BuyerSellerMappingService {

	BuyerSellerMapping getBuyerSellerMappingById(int id);
	BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id);
	BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id);
	TransactionListForUser getPendingTransListForUser(String userId);
	TransactionListForUser getCompletedTransListForUser(String userId);
}
