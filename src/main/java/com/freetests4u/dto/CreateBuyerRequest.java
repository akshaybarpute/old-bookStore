package com.freetests4u.dto;

import com.freetests4u.model.BuyerRequest;

public class CreateBuyerRequest {

	private BuyerRequest buyerRequest;
	private int sellerRequestId;
	private int buyerAddressId;
	
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
	public int getBuyerAddressId() {
		return buyerAddressId;
	}
	public void setBuyerAddressId(int buyerAddressId) {
		this.buyerAddressId = buyerAddressId;
	}
	
	
	
}
