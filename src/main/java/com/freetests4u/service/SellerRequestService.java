package com.freetests4u.service;

import java.util.List;

import com.freetests4u.exceptions.MalformedRequestExeption;
import com.freetests4u.model.SellerRequest;

public interface SellerRequestService{

	void createSellerRequestService(SellerRequest sr) throws Exception;
	SellerRequest getSellerRequestById(int id);
	List<SellerRequest> getSellerRequestListByBook(int limit, int offset, int bookId, String bookName) throws MalformedRequestExeption;
}
