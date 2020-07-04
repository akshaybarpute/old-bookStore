package com.freetests4u.service;

import java.util.List;


import com.freetests4u.dto.BookSearchRequest;
import com.freetests4u.dto.GenericResponseObject;
import com.freetests4u.model.Book;

public interface BookService {

	void addBook(Book b) throws Exception;
	Book getBook(int id);
	GenericResponseObject<List<Book>> getBooks(int size, int page);
	List<Book> getBook(String name);
	List<Book> getBooks(BookSearchRequest b);
}
