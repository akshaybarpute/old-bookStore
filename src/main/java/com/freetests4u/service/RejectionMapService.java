package com.freetests4u.service;

import java.util.List;

import com.freetests4u.model.RejectionReasonMap;

public interface RejectionMapService {

	List<RejectionReasonMap> getRejectionReasonListForBuyers();
	List<RejectionReasonMap> getRejectionReasonListForSellers();
	
}
