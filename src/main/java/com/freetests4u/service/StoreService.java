package com.freetests4u.service;

import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Store;

public interface StoreService {
	void addToStore(int bookId);
	void updateStore(int bookId, StoreAction action);
	Store getCountByBookId(int bookId);
}
