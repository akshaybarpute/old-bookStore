package com.freetests4u.controller;

import java.util.ArrayList;
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
import com.freetests4u.dto.CreateBookResponse;
import com.freetests4u.dto.GetListResponse;
import com.freetests4u.model.Book;
import com.freetests4u.service.BookService;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/addBook", method=RequestMethod.POST)
	public ResponseEntity<CreateBookResponse> addBook(@RequestBody CreateBookRequest bookRequest){	
		
		try {
			
			Book b = new Book();
			BeanUtils.copyProperties(bookRequest, b);
		bookService.addBook(b);	
		
		return new ResponseEntity<CreateBookResponse>(new CreateBookResponse(b,"created",false),HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println("error message: "+ e.getMessage());
			String m = e.getMessage();
			return new ResponseEntity<CreateBookResponse>(new CreateBookResponse(null,m,true),HttpStatus.OK); 
		}
	}
	
	
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable int id){	
		
		return new ResponseEntity<Book>(bookService.getBook(id), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<String> test(){
		
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getBooks", method=RequestMethod.GET)
	public ResponseEntity<GetListResponse> getList(@RequestParam int limit, @RequestParam int offset){
		
		try {
		return new ResponseEntity<GetListResponse>(new GetListResponse(false,"success", bookService.getBooks(limit, offset)), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<GetListResponse>(new GetListResponse(true, e.getMessage(), null),HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/getBooks", method=RequestMethod.POST)
	public ResponseEntity<GetListResponse> getList(@RequestBody BookSearchRequest bookRequest){
		
		try {
			
			System.out.println("writer: "+ bookRequest.getLanguage());
						
			List<Book> l = bookService.getBooks(bookRequest);
			
			return new ResponseEntity<GetListResponse>(new GetListResponse(false, "success",l), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<GetListResponse>(new GetListResponse(true, e.getMessage(),new ArrayList<Book>()), HttpStatus.OK);
	}
		
		
	}
	
	@RequestMapping(value="/getBook", method=RequestMethod.GET)
	public ResponseEntity<GetListResponse> getBook(@RequestParam String name){
		List<Book> l = bookService.getBook(name);
		return new ResponseEntity<GetListResponse>(new GetListResponse(false,"success",l),HttpStatus.OK);
	}
	
	
}
