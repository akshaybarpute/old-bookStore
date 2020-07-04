package com.freetests4u.dto;

public class GenericResponseObject<ObjectType> {

	private ObjectType data;
	private String message;
	private boolean error;
	private int count;
	private int totalPages;
	private int currentPage;
	
	
	
	public GenericResponseObject(ObjectType data, String message, boolean error, int count, int totalPages, int currentPage) {
		super();
		this.data = data;
		this.message = message;
		this.error = error;
		this.count=count;
		this.totalPages=totalPages;
		this.currentPage=currentPage;
	}
	
	public GenericResponseObject(ObjectType data, String message, boolean error) {
		super();
		this.data = data;
		this.message = message;
		this.error = error;
		this.count=0;
		this.totalPages=0;
		this.currentPage=0;
		}

	public ObjectType getData() {
		return data;
	}

	public void setData(ObjectType data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

			
}
