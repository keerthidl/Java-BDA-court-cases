package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.ScheduleEntity;


/**
 * 
 * @author 91948
 *
 */
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
	
	/** find by id **/
	ScheduleEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM schedules WHERE case_main_id=?1",nativeQuery = true)
	 List<ScheduleEntity> findByCase_no(int case_main_id);
}
