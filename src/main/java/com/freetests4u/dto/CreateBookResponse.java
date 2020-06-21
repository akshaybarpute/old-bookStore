package com.freetests4u.dto;

import java.security.Timestamp;

import com.freetests4u.model.Book;

public class CreateBookResponse {

	private Book createdBook;
	private String message;
	private boolean error;
	
	public CreateBookResponse(Book book, String m, boolean error) {
		this.createdBook=book;
		this.message=m;
		this.error=error;
	}
	public Book getCreatedBook() {
		return createdBook;
	}
	public void setCreatedBook(Book createdBook) {
		this.createdBook = createdBook;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean setError() {
		return this.error;
	}
	
	public boolean getError() {
		return error;
	}
	
	
}
