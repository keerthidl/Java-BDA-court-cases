package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.entities.LOpinionTransHistoryEntity;
import com.finch.legal.opinion.app.repositories.LOpinionTranactionsHistoryRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class LOpinionTransactionsHistoryService {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(LOpinionTransactionsHistoryService.class);

	 /** employee repository **/
	@Autowired
	private LOpinionTranactionsHistoryRepository lOpinionTranactionsHistoryRepository;
	
	/**
	 * is employee exists
	 */
	public LOpinionTransHistoryEntity getLOpinionHistoryEntity(String caseId) {
		
		return lOpinionTranactionsHistoryRepository.findById(Integer.parseInt(caseId)).get();
		
	}
	
	/**
	 * is employee exists
	 */
	public void addLOpinionTransaction(LOpinionTransHistoryEntity lOpinionTransHistoryEntity) {
		lOpinionTranactionsHistoryRepository.save(lOpinionTransHistoryEntity);	
	}
	
	/**
	 * scheule entities
	 */
	public List<LOpinionTransHistoryEntity> getLstLOpinionTransEntity(String lopinionTrqId){
		
		return lOpinionTranactionsHistoryRepository.findByLORequest_id(Integer.parseInt(lopinionTrqId));
	}
	
	
}
