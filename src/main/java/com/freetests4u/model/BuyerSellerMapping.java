package com.freetests4u.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="buyer_seller_request_mapping")
public class BuyerSellerMapping {

	@Id
	@Column(name="transid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transId;
	
	@Column(name="buyer_request_id")
	private int buyerRequestId;
	
	@Column(name="seller_request_id")
	private int sellerRequestId;
	
	@Column(name="created_at")
	private Timestamp createdAt;

	@OneToOne
	@JoinColumn(name = "buyer_request_id", referencedColumnName = "id", insertable = false, updatable = false)
	BuyerRequest buyerRequest;
	
	@OneToOne
	@JoinColumn(name = "seller_request_id", referencedColumnName = "id", insertable = false, updatable = false)
	SellerRequest sellerRequest;

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getBuyerRequestId() {
		return buyerRequestId;
	}

	public void setBuyerRequestId(int buyerRequestId) {
		this.buyerRequestId = buyerRequestId;
	}

	public int getSellerRequestId() {
		return sellerRequestId;
	}

	public void setSellerRequestId(int sellerRequestId) {
		this.sellerRequestId = sellerRequestId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public BuyerRequest getBuyerRequest() {
		return buyerRequest;
	}

	public void setBuyerRequest(BuyerRequest buyerRequest) {
		this.buyerRequest = buyerRequest;
	}

	public SellerRequest getSellerRequest() {
		return sellerRequest;
	}

	public void setSellerRequest(SellerRequest sellerRequest) {
		this.sellerRequest = sellerRequest;
	}

	
	
}
	