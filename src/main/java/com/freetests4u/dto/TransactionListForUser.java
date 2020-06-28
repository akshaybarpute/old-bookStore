package com.freetests4u.dto;

import java.util.List;

import com.freetests4u.model.BuyerSellerMapping;

public class TransactionListForUser {

	private List<BuyerSellerMapping> pendingBuyingTransactions;
	private List<BuyerSellerMapping> pendingSellTransactions;
	private List<BuyerSellerMapping> rejectedBuyingTransactions;
	private List<BuyerSellerMapping> rejectedSellTransactions;
	private List<BuyerSellerMapping> deliverdBuyingTransactions;
	private List<BuyerSellerMapping> deliveredSellTransactions;
	
	
	public List<BuyerSellerMapping> getPendingBuyingTransactions() {
		return pendingBuyingTransactions;
	}
	public void setPendingBuyingTransactions(List<BuyerSellerMapping> pendingBuyingTransactions) {
		this.pendingBuyingTransactions = pendingBuyingTransactions;
	}
	public List<BuyerSellerMapping> getPendingSellTransactions() {
		return pendingSellTransactions;
	}
	public void setPendingSellTransactions(List<BuyerSellerMapping> pendingSellTransactions) {
		this.pendingSellTransactions = pendingSellTransactions;
	}
	public List<BuyerSellerMapping> getRejectedBuyingTransactions() {
		return rejectedBuyingTransactions;
	}
	public void setRejectedBuyingTransactions(List<BuyerSellerMapping> rejectedBuyingTransactions) {
		this.rejectedBuyingTransactions = rejectedBuyingTransactions;
	}
	public List<BuyerSellerMapping> getRejectedSellTransactions() {
		return rejectedSellTransactions;
	}
	public void setRejectedSellTransactions(List<BuyerSellerMapping> rejectedSellTransactions) {
		this.rejectedSellTransactions = rejectedSellTransactions;
	}
	public List<BuyerSellerMapping> getDeliverdBuyingTransactions() {
		return deliverdBuyingTransactions;
	}
	public void setDeliverdBuyingTransactions(List<BuyerSellerMapping> deliverdBuyingTransactions) {
		this.deliverdBuyingTransactions = deliverdBuyingTransactions;
	}
	public List<BuyerSellerMapping> getDeliveredSellTransactions() {
		return deliveredSellTransactions;
	}
	public void setDeliveredSellTransactions(List<BuyerSellerMapping> deliveredSellTransactions) {
		this.deliveredSellTransactions = deliveredSellTransactions;
	}


}
