package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.CaseAppealEntity;
import com.finch.legal.opinion.app.entities.PrayerEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;
import com.finch.legal.opinion.app.entities.SectionEntity;

/**
 * 
 * @author 91948
 *
 */
public interface CaseAppealRepository extends JpaRepository<CaseAppealEntity, Integer> {
	
	
}
