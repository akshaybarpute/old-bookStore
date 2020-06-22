package com.freetests4u.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.dao.BuyerSellerMappingDao;
import com.freetests4u.model.BuyerSellerMapping;

@Component
public class BuyerSellerMappingDaoImpl implements BuyerSellerMappingDao{

	@Autowired
	SessionFactory sessionFactory;
	
//	@Transactional
	@Override
	public BuyerSellerMapping getBuyerSellerMappingById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "from BuyerSellerMapping mapping where mapping.transId=:transId";
		
		BuyerSellerMapping mapping =  (BuyerSellerMapping) session.createQuery(hql)
		.setParameter("transId", id)
		.uniqueResult();
		
		return mapping;
	}

	
	@Override
	public BuyerSellerMapping getBuyerSellerMappingByBuyerRequestId(int id) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from BuyerSellerMapping mapping where mapping.buyerRequestId=:id";

		BuyerSellerMapping mapping = (BuyerSellerMapping) session.createQuery(hql)
				.setParameter("id", id)
				.uniqueResult();
		
		return mapping;
	}

	@Override
	public BuyerSellerMapping getBuyerSellerMappingBySellerRequestId(int id) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from BuyerSellerMapping mapping where mapping.sellerRequestId=:id";
		
		BuyerSellerMapping mapping = (BuyerSellerMapping) session.createQuery(hql)
				.setParameter("id", id)
				.uniqueResult();
	
		return mapping;
	}

	@Transactional
	@Override
	public void createBuyerSellerMapping(BuyerSellerMapping mapping) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		session.save(mapping);
	}

}
