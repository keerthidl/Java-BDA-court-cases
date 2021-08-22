package com.finch.legal.opinion.app.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.EmployeeModel;
import com.finch.legal.opinion.app.employee.model.LegalOpinionUpdateRequest;
import com.finch.legal.opinion.app.employee.model.LegalOpinionsSearchModel;
import com.finch.legal.opinion.app.employee.model.LoginDetails;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.services.EmployeeService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.ROOT_URL)
public class LoginController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(LoginController.class);
	/** employee service **/
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * validate login credentials
	 */

	@PostMapping(value=AppConstants.LOGIN_URL)
	public String login(@RequestBody String strLoginDetails) {
		LOG.info(" Entered Login Verification");
	  
		/** Employee Entity **/
		EmployeeEntity employeeEntity = null;
		
		if(strLoginDetails==null || strLoginDetails.trim().length()<1){
			 throw new InvalidRequestException("Invalid Login Request");
		}
		
		try {
			
			employeeEntity = employeeService.getEmployeeDetails(((LoginDetails)JSONFormatter.buildJSONObject(strLoginDetails, LoginDetails.class)).getLoginId());
			
			if(employeeEntity==null) {
				
				throw new ResourceNotFoundException("Invalid Login");
			}
			
			return (JSONFormatter.buildStringObject(employeeEntity));
			
		}catch(ResourceNotFoundException e) {
			LOG.error("No valid user found",e);
			throw new ResourceNotFoundException("Invalid Login");
		}catch(Exception e) {
			LOG.error("Error while validating the credentials",e);
			throw new InternalServerException("Error occurred while processing the Request");
		}
	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		LegalOpinionUpdateRequest legalOpinionUpdateRequest = new LegalOpinionUpdateRequest();
		
		legalOpinionUpdateRequest.setAssignedTo("TESTED");
		legalOpinionUpdateRequest.setExpectedDate("12/12/2-22");
		legalOpinionUpdateRequest.setOpinionDetails("GGGGGGGGGGGGGGGGGGIOOOOOOOOOOOOOOOOOOOOOOOooD");
		legalOpinionUpdateRequest.setRequestDetails("NANANANANANANA");
		legalOpinionUpdateRequest.setStatus("IN-PROGRESS");
		legalOpinionUpdateRequest.setSurveyNumber("SURVEYNUMBER123");
		
		LegalOpinionsSearchModel legalOpinionsSearchModel = new LegalOpinionsSearchModel();
		legalOpinionsSearchModel.setSortField("Date");
		legalOpinionsSearchModel.setSortingMode("ASC");
		legalOpinionsSearchModel.setUserId("admin");
		legalOpinionsSearchModel.setHashMapSearchCriteria(null);
		
		try {
				System.out.println(""+JSONFormatter.buildStringObject(legalOpinionsSearchModel));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
