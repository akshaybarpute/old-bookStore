package com.freetests4u.service;

import java.util.List;

import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.BuyerRequest;

public interface BuyerRequestService {

	void registerBuyerRequest(BuyerRequest br);
	List<BuyerRequest> getBuyerRequest(int id);
	BuyerRequest getBuyerRequestById(int id);
	List<BuyerRequest> getBuyerRequestsForBook(int bookId, String bookName, int limit, int offset) throws MalformedRequestExeption;
}
