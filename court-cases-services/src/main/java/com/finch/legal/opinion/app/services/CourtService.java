package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.CourtEntity;
import com.finch.legal.opinion.app.repositories.AdvocatesRepository;
import com.finch.legal.opinion.app.repositories.CourtRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class CourtService {

	 /** employee repository **/
	@Autowired
	private CourtRepository courtRepository;
	
	/**
	 * is employee exists
	 */
	public CourtEntity getCourtEntity(String id) {
		
		
		return courtRepository.findById(Integer.parseInt(id));
		
	}
	
	/**
	 * is employee exists
	 */
	public List<CourtEntity> getAllCourts() {
		 
		return courtRepository.findAll();
	}
}
