package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.ScheduleEntity;



public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
	
	/** find by id **/
	ScheduleEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM schedule  WHERE case_no=?1",nativeQuery = true)
	 List<ScheduleEntity> findByCase_no(String case_no);
}
