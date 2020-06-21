package com.freetests4u.dto;

import java.util.List;

public class GetListResponse {

	private boolean error;
	private String message;
	private List<? extends Object> data;
	
	
	
	
	public GetListResponse(boolean error, String message, List<? extends Object> data) {
		super();
		this.error = error;
		this.message = message;
		this.data = data;
	}
	
	public GetListResponse() {
		// TODO Auto-generated constructor stub
	}

	public boolean getError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<? extends Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	
	
}
