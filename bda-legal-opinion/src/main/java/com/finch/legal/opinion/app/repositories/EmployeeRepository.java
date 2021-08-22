package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.EmployeeEntity;



public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	//List<EmployeeEntity> findByEmpId(String empId);
	
	EmployeeEntity findByLoginId(String emailId);
	
	List<EmployeeEntity> deleteByEmpId(String empId);
	
	EmployeeEntity findByEmpId(String empId);
	
	List<EmployeeEntity> findByRoleId(String roleId);
	
}
