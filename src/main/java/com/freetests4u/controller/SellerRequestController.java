package com.freetests4u.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.SellerRequest;
import com.freetests4u.service.SellerRequestService;

//implementation need to be changed to increment store book count

@Controller 
public class SellerRequestController {
	
	@Autowired
	private SellerRequestService sellerRequestService;
	
	@RequestMapping(value="/getSellerRequest/{id}",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<SellerRequest>> getSellerRequest(@PathVariable int id){
		
		try {
			SellerRequest sr = sellerRequestService.getSellerRequestById(id);
			return new ResponseEntity<GenericResponseObject<SellerRequest>>(new GenericResponseObject<SellerRequest>(sr,"created",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<SellerRequest>>(new GenericResponseObject<SellerRequest>(null,e.getMessage(),true),HttpStatus.OK); 
		}
	}
	
	
	//need to be revisted
	@RequestMapping(value="/createSellerRequest",method=RequestMethod.POST)
	ResponseEntity<GenericResponseObject<SellerRequest>> createSellerRequest(@RequestBody SellerRequest sr){
		
		try {
			sellerRequestService.createSellerRequestService(sr);
			return new ResponseEntity<GenericResponseObject<SellerRequest>>(new GenericResponseObject<SellerRequest>(sr,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<SellerRequest>>(new GenericResponseObject<SellerRequest>(null,e.getMessage(),true),HttpStatus.OK); 
		}
	
	}

}
