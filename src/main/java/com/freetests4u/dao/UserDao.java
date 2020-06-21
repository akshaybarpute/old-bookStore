package com.freetests4u.dao;

import java.util.List;

import com.freetests4u.model.User;

public interface UserDao {

	void createUser(User user);
	User getUser(String email, String password);
	List<User> getUsers(User s);
}
