package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.repositories.AdvocatesRepository;
import com.finch.legal.opinion.app.repositories.DepartmentsRepository;
import com.finch.legal.opinion.app.repositories.EmployeeRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class AdvocatesService {

	 /** employee repository **/
	@Autowired
	private AdvocatesRepository advocatesRepository;
	
	/**
	 * is employee exists
	 */
	public AdvocatesEntity getAdvocatesEntity(String advocateId) {
		
		
		return advocatesRepository.findByAdvocateId(advocateId);
		
	}
	
	/**
	 * is employee exists
	 */
	public List<AdvocatesEntity> getAllAdvocates() {
		 
		return advocatesRepository.findAll();
	}
}
