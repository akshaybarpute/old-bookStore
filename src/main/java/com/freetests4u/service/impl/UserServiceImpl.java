package com.freetests4u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.UserDao;
import com.freetests4u.model.User;
import com.freetests4u.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	UserDao userDao;

	@Override
	public void addUser(User user){
		// TODO Auto-generated method stub
		 userDao.createUser(user);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.getUser(email, password);
	}

	@Override
	public List<User> searchUser(User user) {
		// TODO Auto-generated method stub
		return userDao.getUsers(user);
	}
	
	
}
