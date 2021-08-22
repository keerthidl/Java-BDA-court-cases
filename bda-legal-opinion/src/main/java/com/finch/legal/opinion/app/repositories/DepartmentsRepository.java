package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;



public interface DepartmentsRepository extends JpaRepository<DepartmentEntity, Integer> {
	List<DepartmentEntity> findByDeptId(String deptId);
	
}
