package com.freetests4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Store;
import com.freetests4u.repositories.StoreRepository;
import com.freetests4u.service.StoreService;

//@Transactional
@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	StoreRepository storeRepository;
	
	@Override
	public void addToStore(int bookId) {
		// TODO Auto-generated method stub
		Store store = new Store();
		store.setBookId(bookId);
		store.setBookCount(0);
		store.setDeleted(false);
		storeRepository.save(store);
	}

	@Override
	public void updateStore(int bookId, StoreAction action) {
		// TODO Auto-generated method stub
		if(action==StoreAction.INCREMENT) {
			storeRepository.incrementStoreBookCount(bookId);
		}
		else if(action==StoreAction.DECREMENT){
			storeRepository.decrementStoreBookCount(bookId);
		}
	}

	@Override
	public Store getCountByBookId(int bookId) {
		// TODO Auto-generated method stub
		return storeRepository.findByBookId(bookId);
	}

}
