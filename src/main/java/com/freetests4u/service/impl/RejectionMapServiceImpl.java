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

import com.freetests4u.model.RejectionReasonMap;
import com.freetests4u.repositories.RejectionMapRepository;
import com.freetests4u.service.RejectionMapService;

@Transactional
@Service
public class RejectionMapServiceImpl implements RejectionMapService{

//	@Autowired
//	RejectionReasonMapDao dao;
	
	@Autowired
	RejectionMapRepository rejectionReasonRepository;
	
	@Override
	public List<RejectionReasonMap> getRejectionReasonListForBuyers() {
		// TODO Auto-generated method stub
		RejectionReasonMap rmap = new RejectionReasonMap();
		rmap.setType("buyer");
		ExampleMatcher matcher= ExampleMatcher.matching()
				.withIgnorePaths("id");
		
		Page<RejectionReasonMap> p = rejectionReasonRepository.findAll(Example.of(rmap,matcher), new PageRequest(0,10, new Sort(Sort.Direction.DESC,"id")));
		return p.hasContent() ? p.getContent():null;
//		return dao.getRejectionReasonsForBuyer();
	}

	@Override
	public List<RejectionReasonMap> getRejectionReasonListForSellers() {
		// TODO Auto-generated method stub
		RejectionReasonMap rmap = new RejectionReasonMap();
		rmap.setType("seller");
		ExampleMatcher matcher= ExampleMatcher.matching()
				.withIgnorePaths("id");
		
		Page<RejectionReasonMap> p = rejectionReasonRepository.findAll(Example.of(rmap,matcher), new PageRequest(0,10, new Sort(Sort.Direction.DESC,"id")));
		return p.hasContent() ? p.getContent():null;
	}

}
