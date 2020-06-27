package com.freetests4u.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.UserAddressDao;
import com.freetests4u.model.UserAddress;
import com.freetests4u.service.UserAddressService;

@Transactional
@Service
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	UserAddressDao addDao;

	@Override
	public List<UserAddress> getUserAddressesForUser(String userId) {
		// TODO Auto-generated method stub
		
		return addDao.getAddressesForUser(userId);
	}

	@Override
	public void addAddressForUser(String userId,UserAddress add) {
		// TODO Auto-generated method stub
		
		addDao.addUserAddress(add, userId);
	}

	@Override
	public void changeDafaultAddressForUser(String userId, int addressId) throws Exception {
		// TODO Auto-generated method stub
		UserAddress address = addDao.getUserAddressById(addressId, userId);
		
		if(address==null) {
			throw new Exception("Address Not Found");
		}
		addDao.changeDefaultUserAddress(userId, addressId);
	}

	@Override
	public List<UserAddress> getAddressesForUser(String userId) {
		// TODO Auto-generated method stub
		return addDao.getAddressesForUser(userId);
	}

	@Override
	public UserAddress getUserAddressById(int addressId, String userId) {
		// TODO Auto-generated method stub
		return addDao.getUserAddressById(addressId, userId);
	}

	@Override
	public UserAddress getUserDefaultAddress(String userId) {
		// TODO Auto-generated method stub
		return addDao.getUserDefaultAddress(userId);
	}

}
