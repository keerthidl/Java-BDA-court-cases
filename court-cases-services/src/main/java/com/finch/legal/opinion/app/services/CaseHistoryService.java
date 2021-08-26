package com.finch.legal.opinion.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ScheduleDetailsModel;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.CaseHistoryRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class CaseHistoryService {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(CaseHistoryService.class);

	 /** employee repository **/
	@Autowired
	private CaseHistoryRepository caseHistoryRepository;
	
	/**
	 * is employee exists
	 */
	public CaseHistoryEntity getCaseHistoryEntity(String caseId) {
		
		return caseHistoryRepository.findById(Integer.parseInt(caseId)).get();
		
	}
	
	/**
	 * is employee exists
	 */
	public void addCaseHistory(CaseHistoryEntity caseHistoryEntity) {
		caseHistoryRepository.save(caseHistoryEntity);	
	}
	
	/**
	 * scheule entities
	 */
	public List<CaseHistoryEntity> getLstCaseHistoryEntity(String caseId){
		
		return caseHistoryRepository.findByCase_main_id(Integer.parseInt(caseId));
	}
	
	
}
