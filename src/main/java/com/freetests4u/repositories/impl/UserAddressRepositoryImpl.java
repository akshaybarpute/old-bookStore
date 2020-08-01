package com.freetests4u.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.model.UserAddress;
import com.freetests4u.repositories.UserAddressRepositoryCustom;

@Component
public class UserAddressRepositoryImpl implements UserAddressRepositoryCustom{

	@Autowired
	SessionFactory factory;
	@Override
	public void setNewDefaultUserAddress(UserAddress add) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		
		String hql1 = "update UserAddress add set add.isDefault=false where add.userId=:userId";
		String hql2 = "update UserAddress add set add.isDefault=true where add.id=:addressId and add.userId=:userId";
		
		session.createQuery(hql1)
		.setParameter("userId", add.getUserId())
		.executeUpdate();
		
		session.createQuery(hql2)
		.setParameter("userId", add.getUserId())
		.setParameter("addressId", add.getId())
		.executeUpdate();
	}
	@Override
	public void saveAddress(UserAddress add) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();

		String hql1 = "update UserAddress add set add.isDefault=false where add.userId=:userId";
		
		session.createQuery(hql1)
		.setParameter("userId", add.getUserId())
		.executeUpdate();

		add.setDefault(true);
		session.save(add);
	}

}
