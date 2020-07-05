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

import com.freetests4u.dto.CreateBuyerRequest;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.BuyerRequest;
import com.freetests4u.service.BuyerRequestService;

@Controller
public class BuyerRequestController {

	@Autowired
	private BuyerRequestService buyerRequestService;
	
	//will receive the sellerRequestId here
	@RequestMapping(value="/registerBuyerRequest", method=RequestMethod.POST)
	ResponseEntity<GenericResponseObject<CreateBuyerRequest>> createBuyerRequest(@RequestBody CreateBuyerRequest br){
		try {
			buyerRequestService.registerBuyerRequest(br.getBuyerRequest(),br.getSellerRequestId());
			return new ResponseEntity<>(new GenericResponseObject<>(br,"created",false), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getBuyerRequest/{id}", method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<BuyerRequest>> getBuyerRequestById(@PathVariable int id){
		
		try {
		BuyerRequest buyerRequest =  buyerRequestService.getBuyerRequestById(id);
		return new ResponseEntity<>(new GenericResponseObject<>(buyerRequest,"success",false), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true), HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/getBuyerRequestsForBook",method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<List<BuyerRequest>>> getBuyerRequestsForBook(@RequestParam Integer page,Integer bookId, String bookName, Integer size){
		
		try {
			System.out.println("bookId: "+bookId+ "bookName "+bookName+ " "+size+" "+page);
			
			int bookIdVal = bookId!=null ?bookId.intValue():0;
			int pageVal = page!=null ?  page.intValue():1;
			int sizeVal = size!=null ? size.intValue():10;
			
			if(pageVal-1<0) {
				throw new Exception("Page value can't be less than 0");
			}
			
			System.out.println("size: "+sizeVal+" page: "+pageVal);
			
			GenericResponseObject<List<BuyerRequest>> obj  = buyerRequestService.getBuyerRequestsForBook(bookIdVal, bookName, sizeVal, pageVal-1);
		
		return new ResponseEntity<>(obj,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
}
