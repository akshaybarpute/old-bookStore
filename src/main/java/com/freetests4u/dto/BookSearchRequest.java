package com.freetests4u.dto;

public class BookSearchRequest {

	private String writer;
	private String category;
	private String language;
	private int limit;
	private int offset;
	
	public BookSearchRequest() {
		
	}
	public BookSearchRequest(String writer, String category, String language, int limit, int offset) {
		super();
		this.writer = writer;
		this.category = category;
		this.language = language;
		this.limit = limit;
		this.offset = offset;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	
	
}
