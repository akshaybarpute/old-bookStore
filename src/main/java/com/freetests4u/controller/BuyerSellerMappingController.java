package com.freetests4u.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.BuyerSellerMapping;
import com.freetests4u.service.BuyerSellerMappingService;

@Controller
public class BuyerSellerMappingController {

	@Autowired
	BuyerSellerMappingService service;
	
	@RequestMapping(value="/transaction/{id}",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<BuyerSellerMapping>> getTransactionById(@PathVariable int id){
		BuyerSellerMapping mapping = service.getBuyerSellerMappingById(id);
		
		return new ResponseEntity<GenericResponseObject<BuyerSellerMapping>> (new GenericResponseObject<BuyerSellerMapping>(mapping,"success",false),HttpStatus.OK);
	}
}