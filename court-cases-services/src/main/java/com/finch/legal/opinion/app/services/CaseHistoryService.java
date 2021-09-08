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
import com.finch.legal.opinion.app.util.GeneralUtil;

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
		LOG.info(" ADDDDDDDDDDDDDDDDDDD   "+GeneralUtil.getTodaysDate());
		caseHistoryRepository.save(caseHistoryEntity);	
		LOG.info(" MMMMMMMMMMMMMMMMMMMMMMMMM   "+GeneralUtil.getTodaysDate());
	}
	
	/**
	 * scheule entities
	 */
	public List<CaseHistoryEntity> getLstCaseHistoryEntity(String caseId){
		
		return caseHistoryRepository.findByCase_main_id(Integer.parseInt(caseId));
	}
	

	/**
	 * court case history
	 */
	public CaseHistoryEntity buildCaseHistory(int caseMainId,String caseNumber,String activity,String caseDetails,String userid) {
		
		CaseHistoryEntity caseHistoryEntity = new CaseHistoryEntity();
		caseHistoryEntity.setCase_activity(activity);
		caseHistoryEntity.setCase_details(caseDetails);
		caseHistoryEntity.setCase_id(caseNumber);
		caseHistoryEntity.setCase_main_id(caseMainId);
		LOG.info(" TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT   "+GeneralUtil.getTodaysDate());
		caseHistoryEntity.setCreated_date(GeneralUtil.getTodaysDate());
		caseHistoryEntity.setUser_dept(userid);
		return caseHistoryEntity;
	}
}
