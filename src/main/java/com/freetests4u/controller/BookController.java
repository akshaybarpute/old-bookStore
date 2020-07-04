package com.freetests4u.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.freetests4u.dto.BookSearchRequest;
import com.freetests4u.dto.CreateBookRequest;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.Book;
import com.freetests4u.service.BookService;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/addBook", method=RequestMethod.POST)
	public ResponseEntity<GenericResponseObject<Book>> addBook(@RequestBody CreateBookRequest bookRequest){	
		
		try {
			
			Book b = new Book();
			BeanUtils.copyProperties(bookRequest, b);
		bookService.addBook(b);	
		
		return new ResponseEntity<>(new GenericResponseObject<Book>(b,"created",false),HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println("error message: "+ e.getMessage());
			String m = e.getMessage();
			return new ResponseEntity<>(new GenericResponseObject<Book>(null,m,true),HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public ResponseEntity<GenericResponseObject<Book>> getBook(@PathVariable int id){	
		
		try {
		Book b = bookService.getBook(id);
		return new ResponseEntity<>(new GenericResponseObject<>(b,"success",false), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,"failed",true), HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<String> test(){
		
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getBooks", method=RequestMethod.GET)
	public ResponseEntity<GenericResponseObject<List<Book>>> getList(@RequestParam int size, @RequestParam int page){
		
		try {
			if(page-1<0) {
				throw new Exception("Page index can't be -ve");
			}
			GenericResponseObject<List<Book>> obj = bookService.getBooks(size, page-1);
		return new ResponseEntity<>(obj,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(new GenericResponseObject<>(null,e.getMessage(),true),HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/getBooks", method=RequestMethod.POST)
	public ResponseEntity<GenericResponseObject<List<Book>>> getList(@RequestBody BookSearchRequest bookRequest){
		
		try {
			
			System.out.println("writer: "+ bookRequest.getLanguage());
						
			List<Book> l = bookService.getBooks(bookRequest);
			
			return new ResponseEntity<>(new GenericResponseObject<>(l,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,"failed",true),HttpStatus.OK);
	}
		
	}
	
	
	@RequestMapping(value="/getBook", method=RequestMethod.GET)
	public ResponseEntity<GenericResponseObject<List<Book>>> getBook(@RequestParam String name){
		
		try {
		List<Book> l = bookService.getBook(name);
		return new ResponseEntity<>(new GenericResponseObject<>(l,"success",false),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new GenericResponseObject<>(null,"failed",false),HttpStatus.OK);
		}
	}
	
	
}
