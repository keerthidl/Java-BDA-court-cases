package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeDesignationsEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;



public interface BDAEmpDesignationsRepository extends JpaRepository<EmployeeDesignationsEntity, Integer> {
	List<EmployeeDesignationsEntity> findByDesgtId(String desgId);
	
}
