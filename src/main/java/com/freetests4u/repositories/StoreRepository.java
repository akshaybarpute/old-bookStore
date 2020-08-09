package com.freetests4u.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Book;
import com.freetests4u.model.Store;

public interface StoreRepository extends JpaRepository<Store,Integer>{
	
	Store findByBookId(int bookId);
	
	@Modifying
	@Query("UPDATE Store s SET s.bookCount=s.bookCount+1 WHERE s.bookId=:bookId")
	void incrementStoreBookCount(@Param("bookId")int bookId);
	
	@Modifying
	@Query("UPDATE Store s SET s.bookCount=s.bookCount-1 WHERE s.bookId=:bookId")
	void decrementStoreBookCount(@Param("bookId")int bookId);
	
	@Query("SELECT s.book.category as category, sum(s.sellCount) as sellCount FROM Store s "
			+"GROUP BY category "
			+"ORDER BY sellCount DESC")
		List<Map<String,String>> getBookCategoriesWithHighestSells();
	
	//s.bookCount>0 AND 
	@Query("From Store s JOIN FETCH s.book WHERE s.bookCount>0 AND s.book.category=:category "
			+ "Order BY s.sellCount DESC")
	List<Store> getHighestSellingAvailableBookSForCategory(@Param("category") String category, Pageable p);

}
