package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.CommentEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;



public interface CaseCommentsRepository extends JpaRepository<CommentEntity, Integer> {
	
	/** find by id **/
	CommentEntity findById(String id);
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_comment  WHERE case_main_id=?1",nativeQuery = true)
	List<CommentEntity> findByCase_main_id(int case_main_id); 
}
