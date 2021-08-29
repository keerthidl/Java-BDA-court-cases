package com.finch.legal.opinion.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.CourtEntity;



public interface CourtRepository extends JpaRepository<CourtEntity, Integer> {
	CourtEntity findById(int id);
}
