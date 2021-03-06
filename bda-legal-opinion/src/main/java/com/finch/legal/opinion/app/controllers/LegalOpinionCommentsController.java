package com.finch.legal.opinion.app.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.BaseResponse;
import com.finch.legal.opinion.app.employee.model.EmployeeModel;
import com.finch.legal.opinion.app.employee.model.LegalOpinionAllCommentsResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionReadCommentsResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionReadResponse;
import com.finch.legal.opinion.app.employee.model.LoginDetails;
import com.finch.legal.opinion.app.employee.model.SupportingDocumentsResponse;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;
import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.services.EmployeeService;
import com.finch.legal.opinion.app.services.LOpinionCommentService;
import com.finch.legal.opinion.app.services.LegalOpinionService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.ROOT_URL)
public class LegalOpinionCommentsController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(LegalOpinionCommentsController.class);
	/** employee service **/
	@Autowired
	private LOpinionCommentService lOpinionCommentService;
	
	/**
	 * validate login credentials
	 */
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.LEGAL_OPINION_ADD_COMMENT_URL)
	public String uploadSupportingDocuent(@RequestBody String strCommentt) {
		LOG.info(" Entered Adding a new Supporting Document   "+strCommentt);
		
		BaseResponse baseResponse = new BaseResponse();
		
		LOpinionCommentEntity lOpinionCommentEntity = null;
		if(strCommentt==null || strCommentt.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
		
		try {
			
			lOpinionCommentEntity = (LOpinionCommentEntity)JSONFormatter.buildJSONObject(strCommentt, LOpinionCommentEntity.class);
			
			lOpinionCommentService.addComment(lOpinionCommentEntity);
			baseResponse.setStatusCode("201");
			baseResponse.setResponse(""+lOpinionCommentEntity.getId());
			return JSONFormatter.buildStringObject(baseResponse);
		} catch (JSONConverterException e) {
			LOG.error(" Error while uploading document",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}catch (Exception e) {
			LOG.error(" Error while uploading document",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}
	}
	
	/**
	 * validate login credentials
	 */
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.LEGAL_OPINIONS_ALL_COMMENTS_URL)
	public String getAllSupportingDocuments(@PathVariable("id") String caseId) {
		LOG.info(" Entered query Supporting Documents ==========================="+caseId);
		LOpinionCommentEntity lOpinionCommentEntity = null;
		LegalOpinionAllCommentsResponse legalOpinionAllCommentsResponse = new LegalOpinionAllCommentsResponse();
		if(caseId==null || caseId.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
	
		try {		
			legalOpinionAllCommentsResponse.setResponse(lOpinionCommentService.getAllRecords(caseId));
			
			if(legalOpinionAllCommentsResponse.getResponse()==null || legalOpinionAllCommentsResponse.getResponse().size()<1) {
				throw new ResourceNotFoundException("No Documents Founcd");
			}
			
			return JSONFormatter.buildStringObject(legalOpinionAllCommentsResponse);
			
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
	@GetMapping(value=AppConstants.LEGAL_OPINION_READ_COMMENT_URL)
	public String getSupportingDocument(@PathVariable("id") String id) {
		LOG.info(" Entered Retreiving Supporting DOcument");
		LegalOpinionReadCommentsResponse legalOpinionReadCommentsResponse = new LegalOpinionReadCommentsResponse();
		LOpinionCommentEntity lOpinionCommentEntity = null;
		if(id==null || id.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
		
	
		try {
			
			LOG.info(" Entered Retreiving Supporting DOcument << ID >> "+id);
			lOpinionCommentEntity = lOpinionCommentService.getCommentEntity(id);
			LOG.info(" Entered Retreiving Supporting DOcument << bbbbbbb >> "+id);
			if(lOpinionCommentEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			
			legalOpinionReadCommentsResponse.setStatusCode("200");
			legalOpinionReadCommentsResponse.setResponse(lOpinionCommentEntity);
			return JSONFormatter.buildStringObject(legalOpinionReadCommentsResponse);
		} catch (JSONConverterException e) {
			throw new InternalServerException("Invalid Legal Support Document");
		}catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException("Requested Document Not Found");
		}catch (Exception e) {
			throw new InternalServerException("Invalid Legal Support Document");
		}
		
	}
}
