package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.repositories.DepartmentsRepository;
import com.finch.legal.opinion.app.repositories.EmployeeRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class DepartmentsService {

	 /** employee repository **/
	@Autowired
	private DepartmentsRepository departmentsRepository;
	
	/**
	 * is employee exists
	 */
	public DepartmentEntity getDepartmentDetails(String deptId) {
		
		 List<DepartmentEntity> lstDepartments = departmentsRepository.findByDeptId(deptId);
		 
		 if(lstDepartments!=null && lstDepartments.size()>0) {
			 return (DepartmentEntity)lstDepartments.get(0);
		 }
		return null;
	}
	
	/**
	 * is employee exists
	 */
	public List<DepartmentEntity> getAllDepartments() {
		
		 List<DepartmentEntity> lstDepartmentEntitys = departmentsRepository.findAll();
		 
		 
		return lstDepartmentEntitys;
	}
	
	/**
	 * is employee exists
	 */
	public DepartmentEntity updateEmployee(DepartmentEntity departmentEntity) {
		
		departmentEntity= departmentsRepository.save(departmentEntity);
		 
		 
		return departmentEntity;
	}
	
	/**
	 * is employee exists
	 */
	public void deleteDepartment(int deptId) {
		
		departmentsRepository.deleteById(deptId);
		
	}
	
	/**
	 * is employee exists
	 */
	public DepartmentEntity save(DepartmentEntity departmentEntity) {
		
		return departmentsRepository.save(departmentEntity);
		
	}
}
