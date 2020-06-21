package com.freetests4u.service;

import java.util.List;


import com.freetests4u.dto.BookSearchRequest;
import com.freetests4u.model.Book;

public interface BookService {

	void addBook(Book b) throws Exception;
	Book getBook(int id);
	List<Book> getBooks(int limit, int offset);
	List<Book> getBook(String name);
	List<Book> getBooks(BookSearchRequest b);
}
