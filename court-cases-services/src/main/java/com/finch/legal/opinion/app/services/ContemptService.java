package com.finch.legal.opinion.app.services;

import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.ContemptRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class ContemptService {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(ContemptService.class);

	 /** employee repository **/
	@Autowired
	private ContemptRepository contemptRepository;
	
	/**
	 * is employee exists
	 */
	public ContemptEntity getContemptEntity(String id) {
		
		return contemptRepository.findById(Integer.parseInt(id));
		
	}
	
	/**
	 * is employee exists
	 */
	public ContemptEntity addContempt(ContemptEntity contemptEntity) {
		contemptRepository.save(contemptEntity);
		return contemptEntity;
	}
	
	/**
	 * is employee exists
	 */
	public ContemptEntity updateContempt(ContemptEntity contemptEntity) {
		contemptRepository.save(contemptEntity);
		return contemptEntity;
	}
	
	/**
	 * is employee exists
	 */
	public ContemptEntity getContemptRecord(String id) {
		return contemptRepository.findById(Integer.parseInt(id));
		
	}
	
	/**
	 * scheule entities
	 */
	public List<ContemptEntity> getAllRecords(String caseId){
		return contemptRepository.findByCase_main_id(Integer.parseInt(caseId));
	}
	
	/**
	 * scheule entities
	 */
	public void delete(String id){
		contemptRepository.deleteById(Integer.parseInt(id));
	}
}
