package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;



public interface DocumentsRepository extends JpaRepository<DocumentEntity, Integer> {
	
	/** find by id **/
	DocumentEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM case_file_upload  WHERE case_main_id=?1",nativeQuery = true)
	 List<DocumentEntity> findByCase_no(String case_no);
}
