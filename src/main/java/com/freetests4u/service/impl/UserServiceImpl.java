package com.freetests4u.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
//import com.freetests4u.dao.UserDao;
import com.freetests4u.dto.LoginType;
import com.freetests4u.dto.UserLoginResponse;
import com.freetests4u.exceptions.UserNotFoundException;
import com.freetests4u.model.User;
import com.freetests4u.repositories.UsersRepository;
import com.freetests4u.security.SecurityConstants;
import com.freetests4u.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService  {

//	@Autowired
//	UserDao userDao;
	
	@Autowired
	UsersRepository usersRepository;

	@Transactional
	@Override
	public void addUser(User user, LoginType type){
		// TODO Auto-generated method stub
		
		if(type==LoginType.PASSWORD) {
			String pwd = user.getSocialId();
			String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt(10));
			user.setSocialId(hashed);
		}
		usersRepository.save(user);
//		 userDao.createUser(user);
	}

	@SuppressWarnings("deprecation")
	@Override
	public UserLoginResponse loginUserWithPassword(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		 Map<String,Object> headerClaims = new HashMap<>();
		 UserLoginResponse loginResponse=null;
		 
//		 User user=userDao.getUser(email);
		 User user=usersRepository.getUserByEmail(email);

		 
		 if(user==null) {
			 throw new UserNotFoundException("UnAuthorised: email or password Incorrect");
		 }
		 		 		 
		 boolean match = BCrypt.checkpw(password, user.getSocialId());
		 
		 if(match==false) {
			 throw new UserNotFoundException("Un-Authorised: email or password Incorrect");
		 }
		 
		 Algorithm algorithm=null;
		 try {
		 headerClaims.put("userInfo", user);
		 algorithm = Algorithm.HMAC256(SecurityConstants.SECRET);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 throw new Exception("Error while generating jwt token");
		 }
		 
		 Date expiry = new Date();
		 expiry.setMinutes(expiry.getMinutes()+30);
		 
		 String token = JWT.create()
				 .withIssuer("freetests4u.com")
				 .withHeader(headerClaims)
				 .withExpiresAt(expiry)
				 .sign(algorithm);
		 
		 loginResponse = new UserLoginResponse();
		 loginResponse.setEmail(user.getEmail());
		 loginResponse.setId(user.getId());
		 loginResponse.setName(user.getName());
		 loginResponse.setToken(token);
		 
		 
		 System.out.println("token: "+token);
		 return loginResponse;
	}

	@Override
	public List<User> searchUser(User user) {
		// TODO Auto-generated method stub
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		Page<User> p=  usersRepository.findAll(Example.of(user, matcher), new PageRequest(0,10,new Sort(Sort.Direction.DESC,"id")));
		return p.getContent();
	}
	
	
}
