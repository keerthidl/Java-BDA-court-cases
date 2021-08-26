package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.CourtCaseEntity;



public interface CourtCasesRepository extends JpaRepository<CourtCaseEntity, Integer> {
	//List<CourtCaseEntity> findByCase_no(String case_no);
}
