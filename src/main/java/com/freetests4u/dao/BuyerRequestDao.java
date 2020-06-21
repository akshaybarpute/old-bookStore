package com.freetests4u.dao;

import java.util.List;

import com.freetests4u.model.BuyerRequest;

public interface BuyerRequestDao {

	void createBuyerRequest(BuyerRequest b);
	List<BuyerRequest> getBuyerRequest(int id);
	BuyerRequest getBuyerRequestDetailsById(int id);
	List<BuyerRequest> getBuyerRequestListByBookId(int bookId,int offset, int limit);
	List<BuyerRequest> getBuyerRequestListByBookId(String bookName,int offset, int limit);
}
