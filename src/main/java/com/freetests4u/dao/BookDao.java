package com.freetests4u.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.freetests4u.model.Book;

public interface BookDao {

	boolean addBook(Book book);
	List<Book> getBookList(int limit, int offset);
	Book getBook(int id);
	List<Book> getBook(String name);
	List<Book> getBooks(String writer, String language, String category, int limit, int offset);
	
}
