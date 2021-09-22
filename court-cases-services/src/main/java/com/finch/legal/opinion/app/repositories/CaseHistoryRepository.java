package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.employee.model.CaseHistory;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;



public interface CaseHistoryRepository extends JpaRepository<CaseHistoryEntity, Integer> {
	
	/** find by id **/
	CaseHistoryEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_history  WHERE case_main_id=?1 ORDER BY created_date",nativeQuery = true)
	List<CaseHistoryEntity> findByCase_main_id(int case_main_id);
}
