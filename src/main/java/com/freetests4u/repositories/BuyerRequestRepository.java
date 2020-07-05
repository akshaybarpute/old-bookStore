package com.freetests4u.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freetests4u.model.BuyerRequest;

@Repository
public interface BuyerRequestRepository extends JpaRepository<BuyerRequest,Integer>{

	@Query("SELECT br from BuyerRequest br "
			+ "LEFT JOIN FETCH br.user "
			+ "LEFT JOIN FETCH br.book "
			+ "LEFT JOIN FETCH br.deliveryAddress "
			+ "where br.id=:Id")
	Optional<BuyerRequest> getBuyerRequestDetailsById(@Param("Id") int id);
	
	@Query("select b from BuyerRequest b "
			+ "INNER JOIN b.book where b.book.title=:bookName")
	Page<BuyerRequest> getBuyerRequestListByBookName(@Param("bookName") String name, Pageable p);
	
	
}
