package com.freetests4u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.CreateUserRequest;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.dto.LoginPasswordRequest;
import com.freetests4u.dto.LoginType;
import com.freetests4u.dto.UserLoginResponse;
import com.freetests4u.model.User;
import com.freetests4u.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public ResponseEntity<GenericResponseObject<User>> addUser(@RequestBody CreateUserRequest userRequest){
				
		try {
			userService.addUser(userRequest.getUser(), userRequest.getType());
		return new ResponseEntity<GenericResponseObject<User>>(new GenericResponseObject<User>(userRequest.getUser(), "created", false), HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println("inside the add user catching");
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<User>>(new GenericResponseObject<User>(null, e.getMessage(),true), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<GenericResponseObject<UserLoginResponse>> getUser(@RequestBody LoginPasswordRequest request){
		
		try {
			System.out.println("email: "+request.getEmail());
			System.out.println("password: "+request.getPassword());
			UserLoginResponse user = userService.loginUserWithPassword(request.getEmail(), request.getPassword());
			return new ResponseEntity<GenericResponseObject<UserLoginResponse>>(new GenericResponseObject<UserLoginResponse>(user,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<UserLoginResponse>>(new GenericResponseObject<UserLoginResponse>(null,e.getMessage(),true),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
