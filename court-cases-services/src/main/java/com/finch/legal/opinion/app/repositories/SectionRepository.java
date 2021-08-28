package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.PrayerEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;
import com.finch.legal.opinion.app.entities.SectionEntity;

/**
 * 
 * @author 91948
 *
 */
public interface SectionRepository extends JpaRepository<SectionEntity, Integer> {
	
	/** find by id **/
	SectionEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_sections  WHERE case_main_id=?1",nativeQuery = true)
	 List<SectionEntity> findByCase_no(int case_no);
	
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_sections  WHERE case_main_id=?1",nativeQuery = true)
	 void deleteByCaseId(int case_no);
}
