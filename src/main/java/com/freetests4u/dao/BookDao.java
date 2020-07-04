package com.freetests4u.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.freetests4u.model.Book;

public interface BookDao {

	List<Book> getBooks(String writer, String language, String category, int limit, int offset);
	
}
