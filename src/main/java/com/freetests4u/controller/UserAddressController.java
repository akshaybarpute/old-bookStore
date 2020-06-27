package com.freetests4u.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.User;
import com.freetests4u.model.UserAddress;
import com.freetests4u.service.UserAddressService;

@Controller
public class UserAddressController {

	@Autowired
	UserAddressService addService;
	
	@RequestMapping(value="/createAddress",method=RequestMethod.POST)
	ResponseEntity<GenericResponseObject<UserAddress>> addAddress(@RequestBody UserAddress add, @RequestAttribute(value="userInfo") User u){
		
		try {
		addService.addAddressForUser(u.getId(), add);
		return new ResponseEntity<>(new GenericResponseObject<>(add,"created",false),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getAddresses",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<List<UserAddress>>> getAddressList(@RequestAttribute(value="userInfo") User u){
		
		try {
			List<UserAddress> l = addService.getAddressesForUser(u.getId());
			return new ResponseEntity<>(new GenericResponseObject<>(l,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getAddress/{id}",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<UserAddress>> getAddressById(@RequestAttribute(value="userInfo") User u,@PathVariable int id){
		
		try {
			UserAddress add = addService.getUserAddressById(id, u.getId());
			return new ResponseEntity<>(new GenericResponseObject<>(add,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getAddress", method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<UserAddress>> getUserDefaultAddress(@RequestAttribute(value="userInfo") User u){
		
		try {
		UserAddress add=  addService.getUserDefaultAddress(u.getId());
		return new ResponseEntity<>(new GenericResponseObject<>(add,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
	
	// todo
	
}
