package com.freetests4u.dto;

import com.freetests4u.model.BuyerRequest;

public class CreateBuyerRequest {

	BuyerRequest buyerRequest;
	int sellerRequestId;
	public BuyerRequest getBuyerRequest() {
		return buyerRequest;
	}
	public void setBuyerRequest(BuyerRequest buyerRequest) {
		this.buyerRequest = buyerRequest;
	}
	public int getSellerRequestId() {
		return sellerRequestId;
	}
	public void setSellerRequestId(int sellerRequestId) {
		this.sellerRequestId = sellerRequestId;
	}
	
	
}
