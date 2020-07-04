package com.freetests4u.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freetests4u.dto.StoreAction;
import com.freetests4u.model.Store;

public interface StoreRepository extends JpaRepository<Store,Integer>{
	
	Store findByBookId(int bookId);
	
	@Modifying
	@Query("UPDATE Store s SET s.bookCount=s.bookCount+1 WHERE s.bookId=:bookId")
	void incrementStoreBookCount(@Param("bookId")int bookId);
	
	@Modifying
	@Query("UPDATE Store s SET s.bookCount=s.bookCount-1 WHERE s.bookId=:bookId")
	void decrementStoreBookCount(@Param("bookId")int bookId);

}
