package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.StatusEntity;
import com.finch.legal.opinion.app.repositories.AdvocatesRepository;
import com.finch.legal.opinion.app.repositories.DepartmentsRepository;
import com.finch.legal.opinion.app.repositories.EmployeeRepository;
import com.finch.legal.opinion.app.repositories.StatusRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class StatusService {

	 /** employee repository **/
	@Autowired
	private StatusRepository statusRepository;
	
	/* is employee exists
	 */
	public StatusEntity getStatusEntity(String statusId) {
		
		
		return statusRepository.findById(Integer.parseInt(statusId));
		
	}
	
	/**
	 * is employee exists
	 */
	public List<StatusEntity> getAllStatus() {
		 
		return statusRepository.findAll();
	}
}
