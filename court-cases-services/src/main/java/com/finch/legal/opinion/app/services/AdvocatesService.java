package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.repositories.AdvocatesRepository;

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
	public AdvocatesEntity getAdvocatesEntity(String id) {
		
		
		return advocatesRepository.findById(Integer.parseInt(id));
		
	}
	
	/**
	 * is employee exists
	 */
	public List<AdvocatesEntity> getAllAdvocates() {
		 
		return advocatesRepository.findAll();
	}
}
