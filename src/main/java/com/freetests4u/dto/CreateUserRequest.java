package com.freetests4u.dto;

import com.freetests4u.model.User;

public class CreateUserRequest {

	private User user;
	private LoginType type;
	
	
	
	public CreateUserRequest(User user, LoginType type) {
		super();
		this.user = user;
		this.type = type;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LoginType getType() {
		return type;
	}
	public void setType(LoginType type) {
		this.type = type;
	}
	
	
}
