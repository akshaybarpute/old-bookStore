package com.freetests4u.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.Store;
import com.freetests4u.service.StoreService;

@Controller
public class StoreController {

	@Autowired
	StoreService storeService;
	
	@RequestMapping(value="/getStoreBookCount/{id}", method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<Store>> getBookCountByBookId(@PathVariable int bookId){
		
		try {
			Store store = storeService.getCountByBookId(bookId);
			
			return new ResponseEntity<GenericResponseObject<Store>>(new GenericResponseObject<Store>(store,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<Store>>(new GenericResponseObject<Store>(null,"success",false),HttpStatus.OK); 
		}
	}
}
