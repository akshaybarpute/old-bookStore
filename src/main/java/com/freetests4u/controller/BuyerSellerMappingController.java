package com.freetests4u.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.dto.TransactionListForUser;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.model.User;
import com.freetests4u.service.BuyerSellerMappingService;

@Controller
public class BuyerSellerMappingController {

	@Autowired
	BuyerSellerMappingService service;
	
	@RequestMapping(value="/transaction/{id}",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<BuyerSellerMapping>> getTransactionById(@PathVariable int id){
		
		try {
		BuyerSellerMapping mapping = service.getBuyerSellerMappingById(id);
		return new ResponseEntity<> (new GenericResponseObject<>(mapping,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<> (new GenericResponseObject<>(null,"failed",true),HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/getPendingTransactionsForUser",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<TransactionListForUser>> getPendingTransactionsForBuyer(@RequestAttribute(value="userInfo") User u){
		
		try {
			TransactionListForUser l = service.getPendingTransListForUser(u.getId());
			return new ResponseEntity<>(new GenericResponseObject<>(l,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
}
