package com.freetests4u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.dto.LoginPasswordRequest;
import com.freetests4u.model.User;
import com.freetests4u.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public ResponseEntity<GenericResponseObject<User>> addUser(@RequestBody User user){
				
		try {
			userService.addUser(user);
		return new ResponseEntity<GenericResponseObject<User>>(new GenericResponseObject<User>(user, "created", false), HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println("inside the add user catching");
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<User>>(new GenericResponseObject<User>(null, e.getMessage(),true), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<GenericResponseObject<User>> getUser(@RequestBody LoginPasswordRequest request){
		
		try {
			System.out.println("email: "+request.getEmail());
			System.out.println("password: "+request.getPassword());
			User user = userService.getUserByEmailAndPassword(request.getEmail(), request.getPassword());
			return new ResponseEntity<GenericResponseObject<User>>(new GenericResponseObject<User>(user,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<User>>(new GenericResponseObject<User>(null,e.getMessage(),true),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
