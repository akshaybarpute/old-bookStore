package com.freetests4u.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.RejectionReasonMap;
import com.freetests4u.service.RejectionMapService;



@Controller
public class RejectionReasonMapController {
	
	@Autowired
	RejectionMapService service;

	@RequestMapping(value="/getBuyerRejectionCodes", method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<List<RejectionReasonMap>>> getRejectionReasonsForBuyer(){
		
		try {
		List<RejectionReasonMap> l = service.getRejectionReasonListForBuyers();
		return new ResponseEntity<>(new GenericResponseObject<>(l,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getSellerRejectionCodes", method=RequestMethod.GET)
	ResponseEntity<GenericResponseObject<List<RejectionReasonMap>>> getRejectionReasonsForSeller(){
		
		try {
			List<RejectionReasonMap> l = service.getRejectionReasonListForSellers();
			return new ResponseEntity<>(new GenericResponseObject<>(l,"success",false), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true), HttpStatus.OK);
		}
	}

	
}
