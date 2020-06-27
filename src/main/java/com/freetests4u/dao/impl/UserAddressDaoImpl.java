package com.freetests4u.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.dao.UserAddressDao;
import com.freetests4u.model.UserAddress;

@Component
public class UserAddressDaoImpl implements UserAddressDao{

	@Autowired
	SessionFactory factory;
	@Override
	public UserAddress getUserDefaultAddress(String userId) {
		// TODO Auto-generated method stub
		String hql="from UserAddress add where add.userId=:id and add.isDefault=true";
		
		Session session = factory.getCurrentSession();
		
		UserAddress address = (UserAddress) session.createQuery(hql)
		.setParameter("id", userId)
		.uniqueResult();
		
		return address;
	}

	@Transactional
	@Override
	public void addUserAddress(UserAddress add, String userId) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		String hql1="update UserAddress add set add.isDefault=false Where add.userId=:id";
		
		session.createQuery(hql1)
		.setParameter("id", userId)
		.executeUpdate();
		
		add.setDefault(true);
		add.setUserId(userId);
		session.save(add);
		System.out.println("hibernate insert id: "+add.getId());
	}

	@Transactional
	@Override
	public void changeDefaultUserAddress(String userId, int addressId) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		
		String hql1 = "update UserAddress add set add.isDefault=false where add.userId=:userId";
		String hql2 = "update UserAddress add set add.isDefault=true where add.id=:addressId and add.userId=:userId";
		
		session.createQuery(hql1)
		.setParameter("userId", userId)
		.executeUpdate();
		
		session.createQuery(hql2)
		.setParameter("userId", userId)
		.setParameter("addressId", addressId)
		.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAddress> getAddressesForUser(String userId) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		
		String hql = "from UserAddress add where add.userId=:userId";
		
		List<UserAddress> l = session.createQuery(hql)
				.setParameter("userId", userId)
				.list();
		
		return l;
	}

	@Override
	public UserAddress getUserAddressById(int addId, String userId) {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		
		String hql = "from UserAddress add where add.id=:addId and add.userId=:userId";
		
		UserAddress add = (UserAddress) session.createQuery(hql)
				.setParameter("addId", addId)
				.setParameter("userId", userId)
				.uniqueResult();
		
		return add;
	}

}
