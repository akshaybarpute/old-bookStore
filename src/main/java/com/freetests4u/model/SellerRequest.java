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
	int id;
	
	@Column(name="userid")
	String userId;
	
	@Column(name="bookid")
	int bookId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
	User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookid", referencedColumnName = "id", insertable = false, updatable = false)
	Book book;
	
	@Column(name="seller_price")
	int sellerPrice;
	
	@Column(name="seller_currency")
	String sellerCurrency;
	
	@Column(name="actual_price")
	int actualPrice;
	
	@Column(name="is_active")
	boolean isActive;
	
	@Column(name="created_at")
	Timestamp  createdAt;
	
	@Column(name="updated_at")
	Timestamp updatedAt;

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
