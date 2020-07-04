package com.freetests4u.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.freetests4u.dao.BuyerSellerMappingDao;
import com.freetests4u.dto.TransactionType;
import com.freetests4u.model.BuyerSellerMapping;

@Component
public class BuyerSellerMappingDaoImpl implements BuyerSellerMappingDao{

	@Autowired
	SessionFactory sessionFactory;
	
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


	@Override
	public List<BuyerSellerMapping> getTransactionsForBuyerId(String userId, TransactionType type,int limit,int offset) {
		// TODO Auto-generated method stub
		
		String status=null;
		
		if(type==TransactionType.PENDING) {
			status="pending";
		}
		else if(type==TransactionType.REJECTED_BY_BUYER) {
			status="rejected_by_buyer";
		}
		else if(type==TransactionType.REJECTED_BY_SELLER) {
			status="rejected_by_seller";
		}
		else if(type==TransactionType.DELIVERD) {
			status="deliverd";
		}
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT b FROM BuyerSellerMapping b "
				+ "LEFT JOIN FETCH b.buyerRequest br "
				+ "LEFT JOIN FETCH br.book "
				+ "WHERE b.buyerRequest.user.id=:Id AND b.status=:status";
		
		@SuppressWarnings("unchecked")
		List<BuyerSellerMapping> l = session.createQuery(hql)
				.setParameter("Id", userId)
				.setParameter("status", status)
				.list();
		return l;
	}


	@Override
	public List<BuyerSellerMapping> getTransactionsForSellerId(String userId, TransactionType type, int limit, int offset) {
		// TODO Auto-generated method stub
		
		String status=null;
		
		if(type==TransactionType.PENDING) {
			status="pending";
		}
		else if(type==TransactionType.REJECTED_BY_BUYER) {
			status="rejected_by_buyer";
		}
		else if(type==TransactionType.REJECTED_BY_SELLER) {
			status="rejected_by_seller";
		}
		else if(type==TransactionType.DELIVERD) {
			status="deliverd";
		}
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT b FROM BuyerSellerMapping b "
				+ "LEFT JOIN FETCH b.buyerRequest br "
				+ "LEFT JOIN FETCH br.book "
				+ "WHERE b.sellerRequest.user.id=:Id AND b.status=:status";
		
		@SuppressWarnings("unchecked")
		List<BuyerSellerMapping> l = session.createQuery(hql)
				.setParameter("Id", userId)
				.setParameter("status", status)
				.list();
		return l;
	}

	}
