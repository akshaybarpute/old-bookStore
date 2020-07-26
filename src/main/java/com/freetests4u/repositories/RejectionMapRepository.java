package com.freetests4u.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.freetests4u.model.RejectionReasonMap;

@Repository
public interface RejectionMapRepository extends JpaRepository<RejectionReasonMap,Integer>{

}
