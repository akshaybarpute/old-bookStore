package com.freetests4u.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name="rejection_code")
	private Integer rejectionCode;
	
	@Column(name="reason")
	private String rejectionReason;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;
	
	@Column(name="status")
	private String status;

	@OneToOne
	@JoinColumn(name = "buyer_request_id", referencedColumnName = "id", insertable = false, updatable = false)
	BuyerRequest buyerRequest;
	
	//ManyToOne
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "seller_request_id", referencedColumnName = "id", insertable = false, updatable = false)
	SellerRequest sellerRequest;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rejection_code",referencedColumnName = "id", insertable = false, updatable = false)
	RejectionReasonMap rejectionMap;

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

	public Integer getRejectionCode() {
		return rejectionCode;
	}

	public void setRejectionCode(Integer rejectionCode) {
		this.rejectionCode = rejectionCode;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public RejectionReasonMap getRejectionMap() {
		return rejectionMap;
	}

	public void setRejectionMap(RejectionReasonMap rejectionMap) {
		this.rejectionMap = rejectionMap;
	}

	
}
	