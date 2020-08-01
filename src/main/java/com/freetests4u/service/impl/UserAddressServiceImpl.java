package com.freetests4u.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.freetests4u.model.UserAddress;
import com.freetests4u.repositories.UserAddressRepository;
import com.freetests4u.service.UserAddressService;

@Transactional
@Service
public class UserAddressServiceImpl implements UserAddressService{
	
//	@Autowired
//	UserAddressDao addDao;
	
	@Autowired
	UserAddressRepository userAddressRepository;

	@Override
	public List<UserAddress> getUserAddressesForUser(String userId) {
		// TODO Auto-generated method stub
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withIgnorePaths("id","isDefault");
		
		UserAddress address = new UserAddress();
		address.setUserId(userId);
		
		Page<UserAddress> p = userAddressRepository.findAll(Example.of(address, matcher), new PageRequest(0,5, new Sort(Sort.Direction.DESC,"id")));
		return p.getContent();
//		return addDao.getAddressesForUser(userId);
	}

	@Override
	public void addAddressForUser(String userId,UserAddress add) {
		// TODO Auto-generated method stub
		
		add.setUserId(userId);
		userAddressRepository.saveAddress(add);
//		addDao.addUserAddress(add, userId);
	}

	@Override
	public void changeDafaultAddressForUser(String userId, int addressId) throws Exception {
		// TODO Auto-generated method stub
		
		ExampleMatcher matcher = ExampleMatcher.matching()
			.withIgnorePaths("isDefault");
;
		
		
		UserAddress add = new UserAddress();
		add.setUserId(userId);
		add.setId(addressId);
		
		UserAddress address = userAddressRepository.findOne(Example.of(add, matcher));
		
//		UserAddress address = addDao.getUserAddressById(addressId, userId);
		
		if(address==null) {
			throw new Exception("Address Not Found");
		}
		userAddressRepository.setNewDefaultUserAddress(address);
//		addDao.changeDefaultUserAddress(userId, addressId);
	}

	@Override
	public List<UserAddress> getAddressesForUser(String userId) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id","isDefault");
		
		UserAddress address = new UserAddress();
		address.setUserId(userId);
		
		Page<UserAddress> p = userAddressRepository.findAll(Example.of(address, matcher), new PageRequest(0,5, new Sort(Sort.Direction.DESC,"id")));
		return p.getContent();
//		return addDao.getAddressesForUser(userId);
	}

	@Override
	public UserAddress getUserAddressById(int addressId, String userId) {
		// TODO Auto-generated method stub
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("isDefault");
		
		UserAddress address = new UserAddress();
		address.setUserId(userId);
		address.setId(addressId);
		
		return userAddressRepository.findOne(Example.of(address, matcher));
		
//		return addDao.getUserAddressById(addressId, userId);
	}

	@Override
	public UserAddress getUserDefaultAddress(String userId) {
		// TODO Auto-generated method stub
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id");
		
		UserAddress address = new UserAddress();
		address.setUserId(userId);
		address.setDefault(true);
		
		return userAddressRepository.findOne(Example.of(address, matcher));
	}

}
