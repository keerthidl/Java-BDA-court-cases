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
import com.finch.legal.opinion.app.employee.model.EmployeeModel;
import com.finch.legal.opinion.app.employee.model.LoginDetails;
import com.finch.legal.opinion.app.employee.model.SupportingDocumentsResponse;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.services.EmployeeService;
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
public class DocumentManagementController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(DocumentManagementController.class);
	/** employee service **/
	@Autowired
	private LegalOpinionService legalOpinionService;
	
	/**
	 * validate login credentials
	 */
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.ADD_SUPPORT_DOC_URL)
	public String uploadSupportingDocuent(@RequestBody String strSupportingDocument) {
		LOG.info(" Entered Adding a new Supporting Document");
		SupportingDocumentsEntity supportingDocumentsEntity = null;
		if(strSupportingDocument==null || strSupportingDocument.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
		
		try {
			
			supportingDocumentsEntity = (SupportingDocumentsEntity)JSONFormatter.buildJSONObject(strSupportingDocument, SupportingDocumentsEntity.class);
			
			legalOpinionService.uploadSupportingDocuments(supportingDocumentsEntity);
			
			return JSONFormatter.buildStringObject(supportingDocumentsEntity);
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
	@GetMapping(value=AppConstants.ALL_SUPPORT_DOC_URL)
	public String getAllSupportingDocuments(@RequestBody String strLegalRequestId) {
		LOG.info(" Entered query Supporting Documents");
		SupportingDocumentsEntity supportingDocumentsEntity = null;
		
		if(strLegalRequestId==null || strLegalRequestId.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
		
		SupportingDocumentsResponse supportingDocumentsResponse = new SupportingDocumentsResponse();
		
		try {		
			supportingDocumentsResponse.setLstSupportingDocumentsEntity(legalOpinionService.getAllSupportingDocuents(strLegalRequestId));
			
			if(supportingDocumentsResponse.getLstSupportingDocumentsEntity()==null || supportingDocumentsResponse.getLstSupportingDocumentsEntity().size()<1) {
				throw new ResourceNotFoundException("No Documents Founcd");
			}
			
			return JSONFormatter.buildStringObject(supportingDocumentsResponse);
			
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
	@GetMapping(value=AppConstants.SUPPORT_DOC_URL)
	public String getSupportingDocument(@PathVariable("id") String strLegalRequestId) {
		LOG.info(" Entered Retreiving Supporting DOcument");
		SupportingDocumentsEntity supportingDocumentsEntity = null;
		
		if(strLegalRequestId==null || strLegalRequestId.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Document");
		}
		
		SupportingDocumentsResponse supportingDocumentsResponse = new SupportingDocumentsResponse();
		try {
			supportingDocumentsEntity = legalOpinionService.getSupportingDocuent(strLegalRequestId);
			
			if(supportingDocumentsEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			return JSONFormatter.buildStringObject(supportingDocumentsEntity);
		} catch (JSONConverterException e) {
			throw new InternalServerException("Invalid Legal Support Document");
		}catch (ResourceNotFoundException e) {
				throw new ResourceNotFoundException("Requested Document Not Found");
		}catch (Exception e) {
			throw new InternalServerException("Invalid Legal Support Document");
		}
		
	}
}
