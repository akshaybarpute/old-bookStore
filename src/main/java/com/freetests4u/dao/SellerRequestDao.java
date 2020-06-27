package com.freetests4u.dao;

import java.util.List;

import com.freetests4u.model.SellerRequest;

public interface SellerRequestDao {

	void registerRequest(SellerRequest sr);
	void markRequestInActive(int id);
	SellerRequest getSellerRequestById(int id);
	List<SellerRequest> getSellerRequestForBookId(int id,int limit,int offset);
	List<SellerRequest> getSellerRequestForBookName(String name,int limit, int offset);
}
