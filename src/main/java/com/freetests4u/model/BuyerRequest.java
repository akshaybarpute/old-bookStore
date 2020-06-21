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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="buyer_requests")
public class BuyerRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty
	@NotNull
	@Column(name="bid_price")
	private int bidPrice;
	
	@NotEmpty
	@NotNull
	@Column(name="userid")
	private String userId; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", referencedColumnName = "id", insertable = false, updatable = false)
	User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookid", referencedColumnName = "id", insertable = false, updatable = false)
	Book book;
	
	@Column(name="bookid")
	private int bookId;
	
	@Column(name="bid_currency")
	private String bidCurrency;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="is_fullfilled")
	private boolean isFullfilled;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBidCurrency() {
		return bidCurrency;
	}

	public void setBidCurrency(String bidCurrency) {
		this.bidCurrency = bidCurrency;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isFullfilled() {
		return isFullfilled;
	}

	public void setFullfilled(boolean isFullfilled) {
		this.isFullfilled = isFullfilled;
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
