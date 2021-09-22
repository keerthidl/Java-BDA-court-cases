package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.ContemptEntity;



public interface ContemptRepository extends JpaRepository<ContemptEntity, Integer> {
	
	/** find by id **/
	ContemptEntity findById(int id);
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_contempt  WHERE case_main_id=?1 ORDER BY contempt_date",nativeQuery = true)
	List<ContemptEntity> findByCase_main_id(int case_main_id); 
}
