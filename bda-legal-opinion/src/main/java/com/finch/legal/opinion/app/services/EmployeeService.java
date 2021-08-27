package com.finch.legal.opinion.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.employee.model.RegisteredAdvocatesModel;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.repositories.EmployeeRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class EmployeeService {

	 /** employee repository **/
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * is employee exists
	 */
	public EmployeeEntity getEmployeeDetails(String emailId) {
		
		 return employeeRepository.findByLoginId(emailId);
	
	}
	
	/**
	 * is employee exists
	 */
	public List<EmployeeEntity> getEmpByRole(String roleId) {
		
		 return employeeRepository.findByRoleId(roleId);
	
	}
	
	/**
	 * is employee exists
	 */
	public List<RegisteredAdvocatesModel> getAdvocates(String roleId) {
		
		 List<EmployeeEntity> lstEmployeeEntity =  employeeRepository.findByRoleId(roleId);
		 
		 List<RegisteredAdvocatesModel> lstRegisteredAdvocates = new ArrayList();
		 RegisteredAdvocatesModel registeredAdvocatesModel = null;
		 
		 for(EmployeeEntity employeeEntity: lstEmployeeEntity) {
			 registeredAdvocatesModel = new RegisteredAdvocatesModel();
			 registeredAdvocatesModel.setEmpId(employeeEntity.getLoginId());
			 registeredAdvocatesModel.setName(employeeEntity.getName());
			 lstRegisteredAdvocates.add(registeredAdvocatesModel);
		 }
		 
		 return lstRegisteredAdvocates;
	
	}
	
	/**
	 * is employee exists
	 */
	public EmployeeEntity getEmployeeById(String empId) {
		
		return employeeRepository.findByEmpId(empId);
	
	}
	
	/**
	 * is employee exists
	 */
	public EmployeeEntity getEmployeeByLoginId(String empLoginId) {
		
		return employeeRepository.findByLoginId(empLoginId);
	
	}
	
	/**
	 * is employee exists
	 */
	public List<EmployeeEntity> getAllEmployees() {
		
		 List<EmployeeEntity> lstEmployees = employeeRepository.findAll();
		 
		 
		return lstEmployees;
	}
	
	/**
	 * is employee exists
	 */
	public EmployeeEntity updateEmployee(EmployeeEntity employeeEnity) {
		
		employeeEnity= employeeRepository.save(employeeEnity);
		 
		 
		return employeeEnity;
	}
	
	/**
	 * is employee exists
	 */
	public void deleteEmployee(int employeeId) {
		
		employeeRepository.deleteById(employeeId);
		
	}
	
	/**
	 * is employee exists
	 */
	public EmployeeEntity save(EmployeeEntity employeeEnity) {
		
		return employeeRepository.save(employeeEnity);
		
	}
}
