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
public interface PrayerRepository extends JpaRepository<PrayerEntity, Integer> {
	
	/** find by id **/
	PrayerEntity findById(String id);
	/** find by id **/
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_prayers  WHERE case_main_id=?1",nativeQuery = true)
	 List<PrayerEntity> findByCase_no(String case_no);
	
	/** find by id **/
	@Query(value="SELECT * FROM courtcase_prayers  WHERE case_main_id=?1",nativeQuery = true)
	void deleteByCase_no(int case_no);
}
