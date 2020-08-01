package com.freetests4u.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.freetests4u.model.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Integer>, UserAddressRepositoryCustom{

}
