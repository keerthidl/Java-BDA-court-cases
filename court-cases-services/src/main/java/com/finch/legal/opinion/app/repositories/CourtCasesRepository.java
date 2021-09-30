package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.CourtCaseEntity;



public interface CourtCasesRepository extends JpaRepository<CourtCaseEntity, Integer> {
	
	CourtCaseEntity findById(int id);
	
	@Query(value="SELECT max(id) FROM court_cases  WHERE case_year=?1  and zone=?2",nativeQuery = true)
	int findMaxId(String year, String zone);
}
