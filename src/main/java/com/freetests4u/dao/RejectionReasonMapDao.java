package com.freetests4u.dao;

import java.util.List;

import com.freetests4u.model.RejectionReasonMap;

public interface RejectionReasonMapDao {

	List<RejectionReasonMap> getRejectionReasonsForBuyer();
	List<RejectionReasonMap> getRejectionReasonsForSeller();
}
