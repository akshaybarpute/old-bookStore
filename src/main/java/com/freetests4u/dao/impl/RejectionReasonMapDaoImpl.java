package com.freetests4u.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freetests4u.dao.RejectionReasonMapDao;
import com.freetests4u.model.RejectionReasonMap;

@Component
public class RejectionReasonMapDaoImpl implements RejectionReasonMapDao{

	@Autowired
	SessionFactory factory;
	@Override
	public List<RejectionReasonMap> getRejectionReasonsForBuyer() {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		
		String hql = "from RejectionReasonMap where type='buyer'";
		
		@SuppressWarnings("unchecked")
		List<RejectionReasonMap> l = (List<RejectionReasonMap>)session.createQuery(hql)
				.list();
		return l;
	}

	@Override
	public List<RejectionReasonMap> getRejectionReasonsForSeller() {
		// TODO Auto-generated method stub
		
		Session session = factory.getCurrentSession();
		
		String hql = "from RejectionReasonMap where type='seller'";
		
		@SuppressWarnings("unchecked")
		List<RejectionReasonMap> l = (List<RejectionReasonMap>)session.createQuery(hql)
				.list();
		return l;
	}

}
