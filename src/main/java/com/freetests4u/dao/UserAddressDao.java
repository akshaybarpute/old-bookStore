package com.freetests4u.dao;

import java.util.List;

import com.freetests4u.model.UserAddress;

public interface UserAddressDao {

	UserAddress getUserDefaultAddress(String userId);
	void addUserAddress(UserAddress add, String userId);
	void changeDefaultUserAddress(String userId, int addressId);
	List<UserAddress> getAddressesForUser(String userId);
	UserAddress getUserAddressById(int addId, String userId);
//	UserAddress editUserAddress(int addressId);
}
