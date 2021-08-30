package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.finch.legal.opinion.app.entities.LOpinionTransHistoryEntity;



public interface LOpinionTranactionsHistoryRepository extends JpaRepository<LOpinionTransHistoryEntity, Integer> {
	
	/** find by id **/
	LOpinionTransHistoryEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM legal_opinion_transactions_history  WHERE legal_opinion_request_id=?1",nativeQuery = true)
	List<LOpinionTransHistoryEntity> findByLORequest_id(int case_main_id);
}
