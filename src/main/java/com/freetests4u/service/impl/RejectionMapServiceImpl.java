package com.freetests4u.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freetests4u.dao.RejectionReasonMapDao;
import com.freetests4u.model.RejectionReasonMap;
import com.freetests4u.service.RejectionMapService;

@Transactional
@Service
public class RejectionMapServiceImpl implements RejectionMapService{

	@Autowired
	RejectionReasonMapDao dao;
	@Override
	public List<RejectionReasonMap> getRejectionReasonListForBuyers() {
		// TODO Auto-generated method stub
		return dao.getRejectionReasonsForBuyer();
	}

	@Override
	public List<RejectionReasonMap> getRejectionReasonListForSellers() {
		// TODO Auto-generated method stub
		return dao.getRejectionReasonsForSeller();
	}

}
