package com.freetests4u.repositories;


import com.freetests4u.model.UserAddress;

public interface UserAddressRepositoryCustom {

	void setNewDefaultUserAddress(UserAddress add);
	
	void saveAddress(UserAddress add);

}
