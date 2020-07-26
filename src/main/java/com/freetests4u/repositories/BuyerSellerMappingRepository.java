package com.freetests4u.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freetests4u.model.BuyerSellerMapping;

public interface BuyerSellerMappingRepository extends JpaRepository<BuyerSellerMapping, Integer>{

	@Query(value="SELECT b FROM BuyerSellerMapping b "
			+ "LEFT JOIN FETCH b.buyerRequest br "
			+ "LEFT JOIN FETCH br.book "
			+ "WHERE b.buyerRequest.user.id=:buyerId AND b.status=:status",
			countQuery="SELECT count(b.id) FROM BuyerSellerMapping b WHERE b.buyerRequest.user.id=:buyerId AND b.status=:status")
	Optional<Page<BuyerSellerMapping>> getTransactionsForBuyerId(@Param("buyerId")String buyerId,@Param("status")String status,Pageable p);
	
	@Query(value="SELECT b FROM BuyerSellerMapping b "
			+ "LEFT JOIN FETCH b.buyerRequest br "
			+ "LEFT JOIN FETCH br.book "
			+ "WHERE b.sellerRequest.user.id=:sellerId AND b.status=:status",
			countQuery="SELECT count(b.id) FROM BuyerSellerMapping b WHERE b.sellerRequest.user.id=:sellerId AND b.status=:status")
	Optional<Page<BuyerSellerMapping>> getTransactionsForSellerId(@Param("sellerId")String sellerId,@Param("status")String status,Pageable p);

}
