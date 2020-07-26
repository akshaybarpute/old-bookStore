package com.freetests4u.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freetests4u.model.SellerRequest;

@Repository
public interface SellerRequestRepository extends JpaRepository<SellerRequest,Integer>{

	@Query("SELECT sr FROM SellerRequest sr "
			+ "INNER JOIN sr.book "
			+ "WHERE sr.book.title=:bookName AND sr.isActive=true")
	Page<SellerRequest> getSellerRequestsForBook(@Param("bookName") String name,Pageable p);
	
	@Modifying
	@Query("UPDATE SellerRequest sr SET sr.isActive=false WHERE id=:id")
	void markRequestInActive(@Param("id")int id);
}
