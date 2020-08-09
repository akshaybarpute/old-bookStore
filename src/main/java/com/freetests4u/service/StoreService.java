package com.freetests4u.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Book;
import com.freetests4u.model.Store;

public interface StoreService {
	void addToStore(int bookId);
	void updateStore(int bookId, StoreAction action);
	Store getCountByBookId(int bookId);
	Map<String,List<Book>> getSomeBookSToDisplay();
}
