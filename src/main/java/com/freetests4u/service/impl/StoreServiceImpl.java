package com.freetests4u.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Book;
import com.freetests4u.model.Store;
import com.freetests4u.repositories.BookRepository;
import com.freetests4u.repositories.StoreRepository;
import com.freetests4u.service.StoreService;

//@Transactional
@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	BookRepository bookRespository;
	
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

	@Override
	public Map<String, List<Book>> getSomeBookSToDisplay() {
		// TODO Auto-generated method stub
		List<Map<String,String>>  mList= storeRepository.getBookCategoriesWithHighestSells();
		Map<String,List<Book>> resultMap = new HashMap<>(); 
		String[] categoryList = new String[5];
		
		System.out.println("size: "+mList.size());
		
		for(int i=0;i<mList.size();i++) {
			Map<String,String> m = mList.get(i);
			String category = m.get("category");
			categoryList[i] = category;
			List<Store> resultStores = storeRepository.getHighestSellingAvailableBookSForCategory(category, new PageRequest(0, 5));
			
			List<Book> b = new ArrayList<>();
			
			for(Store s: resultStores) {
				b.add(s.getBook());
			}
			
			resultMap.put(category, b);
		}
		
		
		return resultMap;
	}

}
