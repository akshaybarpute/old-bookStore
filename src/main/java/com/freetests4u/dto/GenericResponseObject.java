package com.freetests4u.dto;

public class GenericResponseObject<ObjectType> {

	private ObjectType data;
	private String message;
	private boolean error;
	
	
	
	public GenericResponseObject(ObjectType data, String message, boolean error) {
		super();
		this.data = data;
		this.message = message;
		this.error = error;
	}

	public GenericResponseObject() {
		
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
	
	

			
}
