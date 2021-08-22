package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeDesignationsEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.repositories.BDAEmpDesignationsRepository;
import com.finch.legal.opinion.app.repositories.DepartmentsRepository;
import com.finch.legal.opinion.app.repositories.EmployeeRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class BDAEmpDesignationsService {

	 /** employee repository **/
	@Autowired
	private BDAEmpDesignationsRepository bdaEmpDesignationsRepository;
	
	/**
	 * is employee exists
	 */
	public EmployeeDesignationsEntity getDepartmentDetails(String desgId) {
		
		 List<EmployeeDesignationsEntity> lstDepartments = bdaEmpDesignationsRepository.findByDesgtId(desgId);
		 
		 if(lstDepartments!=null && lstDepartments.size()>0) {
			 return (EmployeeDesignationsEntity)lstDepartments.get(0);
		 }
		return null;
	}
	
	/**
	 * is employee exists
	 */
	public List<EmployeeDesignationsEntity> getAllDesignations() {
		
		 List<EmployeeDesignationsEntity> lstEmployeeDesignationsEntity = bdaEmpDesignationsRepository.findAll();
		 
		 
		return lstEmployeeDesignationsEntity;
	}
	
	/**
	 * is employee exists
	 */
	public EmployeeDesignationsEntity updateEmployeeDesignation(EmployeeDesignationsEntity employeeDesignationsEntity) {
		
		employeeDesignationsEntity= bdaEmpDesignationsRepository.save(employeeDesignationsEntity);
		 
		 
		return employeeDesignationsEntity;
	}
	
	/**
	 * is employee exists
	 */
	public void deleteDesignationt(int desgId) {
		
		bdaEmpDesignationsRepository.deleteById(desgId);
		
	}
	
	/**
	 * is employee exists
	 */
	public EmployeeDesignationsEntity save(EmployeeDesignationsEntity employeeDesignationsEntity) {
		
		return bdaEmpDesignationsRepository.save(employeeDesignationsEntity);
		
	}
}
