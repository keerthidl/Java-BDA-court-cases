package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;



public interface LOpinionCommentsRepository extends JpaRepository<LOpinionCommentEntity, Integer> {
	
	/** find by id **/
	LOpinionCommentEntity findById(String id);
	/** find by id **/
	@Query(value="SELECT * FROM legal_opinion_comments  WHERE legal_opinion_req_id=?1",nativeQuery = true)
	List<LOpinionCommentEntity> findByCase_main_id(int case_main_id); 
}
