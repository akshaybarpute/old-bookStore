package com.freetests4u.service;

import java.util.List;

import com.freetests4u.model.User;

public interface UserService {

	void addUser(User user);
	User getUserByEmailAndPassword(String email, String password);
	List<User> searchUser(User user);
}
