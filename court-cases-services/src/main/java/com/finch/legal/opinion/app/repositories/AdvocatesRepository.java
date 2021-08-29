package com.finch.legal.opinion.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;



public interface AdvocatesRepository extends JpaRepository<AdvocatesEntity, Integer> {
	AdvocatesEntity findById(int id);
}
