package com.freetests4u.service;

import java.util.List;

import com.freetests4u.dto.LoginType;
import com.freetests4u.dto.UserLoginResponse;
import com.freetests4u.exceptions.UserNotFoundException;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.model.User;

public interface UserService {

	void addUser(User user, LoginType loginType);
	UserLoginResponse loginUserWithPassword(String email, String password) throws UserNotFoundException, Exception;
	List<User> searchUser(User user);
}
