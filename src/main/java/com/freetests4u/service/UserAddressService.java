package com.freetests4u.service;

import java.util.List;

import com.freetests4u.model.UserAddress;

public interface UserAddressService {

	List<UserAddress> getUserAddressesForUser(String userId);
	void addAddressForUser(String userId, UserAddress add);
	void changeDafaultAddressForUser(String userId,int addressId) throws Exception;
	List<UserAddress> getAddressesForUser(String userId);
	UserAddress getUserAddressById(int addressId, String userId );
	UserAddress getUserDefaultAddress(String userId);
}
