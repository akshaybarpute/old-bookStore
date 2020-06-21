package com.freetests4u.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.BuyerRequest;
import com.freetests4u.service.BuyerRequestService;

@Controller
public class BuyerRequestController {

	@Autowired
	private BuyerRequestService buyerRequestService;
	
	@RequestMapping(value="/registerBuyerRequest", method=RequestMethod.POST)
	ResponseEntity<GenericResponseObject<BuyerRequest>> createBuyerRequest(@RequestBody BuyerRequest br){
		try {
			buyerRequestService.registerBuyerRequest(br);
			return new ResponseEntity<GenericResponseObject<BuyerRequest>>(new GenericResponseObject<BuyerRequest>(br,"created",false), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<BuyerRequest>>(new GenericResponseObject<BuyerRequest>(null,e.getMessage(),true), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getBuyerRequest/{id}", method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<BuyerRequest>> getBuyerRequestById(@PathVariable int id){
		
		try {
		BuyerRequest buyerRequest =  buyerRequestService.getBuyerRequestById(id);
		return new ResponseEntity<GenericResponseObject<BuyerRequest>>(new GenericResponseObject<BuyerRequest>(buyerRequest,"success",false), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<BuyerRequest>>(new GenericResponseObject<BuyerRequest>(null,e.getMessage(),true), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getBuyerRequestsForBook",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<List<BuyerRequest>>> getBuyerRequestsForBook(@RequestParam Integer offset,Integer bookId, String bookName, Integer limit){
		
		try {
//			System.out.println("bookId: "+bookId+ "bookName "+bookName);
			
			int bookIdVal = bookId!=null ?bookId.intValue():0;
			int offsetVal = offset==null ?  offset.intValue():0;
			int limitVal = limit==null ? limit.intValue():0;
			
		List<BuyerRequest> buyerRequestList = buyerRequestService.getBuyerRequestsForBook(bookIdVal, bookName, offsetVal, limitVal);
		
		return new ResponseEntity<GenericResponseObject<List<BuyerRequest>>>(new GenericResponseObject<List<BuyerRequest>>(buyerRequestList,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GenericResponseObject<List<BuyerRequest>>>(new GenericResponseObject<List<BuyerRequest>>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
}
