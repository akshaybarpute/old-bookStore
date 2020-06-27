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
import javax.persistence.Table;

@Entity
@Table(name="seller_requests")
public class SellerRequest {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userid")
	private String userId;
	
	@Column(name="bookid")
	private int bookId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookid", referencedColumnName = "id", insertable = false, updatable = false)
	private Book book;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dispatch_address",referencedColumnName = "id", insertable = false, updatable = false )
	private UserAddress dispatchAddress;
	
	@Column(name="seller_price")
	private int sellerPrice;
	
	@Column(name="seller_currency")
	private String sellerCurrency;
	
	@Column(name="actual_price")
	private int actualPrice;
	
	@Column(name="dispatch_address")
	private int dispatchAddressId;
	
	@Column(name="weight")
	private float weight;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="created_at")
	private Timestamp  createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public UserAddress getDispatchAddress() {
		return dispatchAddress;
	}

	public void setDispatchAddress(UserAddress dispatchAddress) {
		this.dispatchAddress = dispatchAddress;
	}

	public int getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(int sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public String getSellerCurrency() {
		return sellerCurrency;
	}

	public void setSellerCurrency(String sellerCurrency) {
		this.sellerCurrency = sellerCurrency;
	}

	public int getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(int actualPrice) {
		this.actualPrice = actualPrice;
	}

	public int getDispatchAddressId() {
		return dispatchAddressId;
	}

	public void setDispatchAddressId(int dispatchAddressId) {
		this.dispatchAddressId = dispatchAddressId;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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


	
}
