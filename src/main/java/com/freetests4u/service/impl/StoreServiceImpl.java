package com.freetests4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.StoreDao;
import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Store;
import com.freetests4u.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreDao storeDao;
	@Override
	public void addToStore(int bookId) {
		// TODO Auto-generated method stub
		storeDao.createStoreEntry(bookId);
	}

	@Override
	public void updateStore(int bookId, StoreAction action) {
		// TODO Auto-generated method stub
		storeDao.updateStore(bookId, action);
	}

	@Override
	public Store getCountByBookId(int bookId) {
		// TODO Auto-generated method stub
		return storeDao.getBookCountByBookId(bookId);
	}

}
