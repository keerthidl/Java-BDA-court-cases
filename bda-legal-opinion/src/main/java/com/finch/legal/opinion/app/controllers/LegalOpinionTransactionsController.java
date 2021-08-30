package com.finch.legal.opinion.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.LegalOpinionAllCommentsResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionAllTransResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionReadCommentsResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionReadTransResponse;
import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;
import com.finch.legal.opinion.app.entities.LOpinionTransHistoryEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.services.LOpinionTransactionsHistoryService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.ROOT_URL)
public class LegalOpinionTransactionsController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(LegalOpinionTransactionsController.class);
	/** employee service **/
	@Autowired
	private LOpinionTransactionsHistoryService lOpinionTransactionsHistoryService;
	
	
	/**
	 * validate login credentials
	 */
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.LEGAL_OPINIONS_ALL_TRANS_HISTORY_URL)
	public String getAllSupportingDocuments(@PathVariable("id") String caseId) {
		LOG.info(" Entered query Supporting Documents ==========================="+caseId);
		LOpinionCommentEntity lOpinionCommentEntity = null;
		LegalOpinionAllTransResponse legalOpinionAllTransResponse = new LegalOpinionAllTransResponse();
		if(caseId==null || caseId.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
	
		try {		
			legalOpinionAllTransResponse.setResponse(lOpinionTransactionsHistoryService.getLstLOpinionTransEntity(caseId));
			
			if(legalOpinionAllTransResponse.getResponse()==null || legalOpinionAllTransResponse.getResponse().size()<1) {
				throw new ResourceNotFoundException("No Documents Founcd");
			}
			
			return JSONFormatter.buildStringObject(legalOpinionAllTransResponse);
			
		} catch (JSONConverterException e) {
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("No Records Found");
		}catch (Exception e) {
			throw new ResourceNotFoundException("Internal Server Error");
		}
	}
	
	/**
	 * validate login credentials
	 */
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.LEGAL_OPINIONS_READ_TRANS_HISTORY_URL)
	public String getSupportingDocument(@PathVariable("id") String id) {
		LOG.info(" Entered Retreiving Supporting DOcument");
		LegalOpinionReadTransResponse legalOpinionReadTransResponse = new LegalOpinionReadTransResponse();
		LOpinionTransHistoryEntity lOpinionTransHistoryEntity = null;
		if(id==null || id.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
		
	
		try {
			
			LOG.info(" Entered Retreiving Supporting DOcument << ID >> "+id);
			lOpinionTransHistoryEntity = lOpinionTransactionsHistoryService.getLOpinionHistoryEntity(id);
			LOG.info(" Entered Retreiving Supporting DOcument << bbbbbbb >> "+id);
			if(lOpinionTransHistoryEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			
			legalOpinionReadTransResponse.setStatusCode("200");
			legalOpinionReadTransResponse.setResponse(lOpinionTransHistoryEntity);
			return JSONFormatter.buildStringObject(legalOpinionReadTransResponse);
		} catch (JSONConverterException e) {
			throw new InternalServerException("Invalid Legal Support Document");
		}catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException("Requested Document Not Found");
		}catch (Exception e) {
			throw new InternalServerException("Invalid Legal Support Document");
		}
		
	}
}
