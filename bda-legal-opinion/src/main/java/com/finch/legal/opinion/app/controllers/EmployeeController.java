package com.finch.legal.opinion.app.controllers;

import java.util.List;

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
import com.finch.legal.opinion.app.employee.model.AdvocateListResponse;
import com.finch.legal.opinion.app.employee.model.BaseResponse;
import com.finch.legal.opinion.app.employee.model.RegisteredAdvocatesModel;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
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
public class EmployeeController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(EmployeeController.class);
	
	/** employee repository **/
	@Autowired
	private EmployeeService employeeService;
	/**
	 * ping service
	 */
	
	@GetMapping(value=AppConstants.PING_URL)
	public String ping() {
		LOG.info(" In Ping Service ");
		return "Successfully Loaded the Legal Opinion Dashboard Application";
	}
	
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.ENROLL_URL)
	public String enrollEmployee(@RequestBody String strEmployeeDetails) {
		LOG.info("Entered User Enrollment for Legel Opinion Dashboard");
		EmployeeEntity employeeEntity = null;
		
		try {
			
			if(strEmployeeDetails==null || strEmployeeDetails.trim().length()<1) {
				throw new InvalidRequestException(" Invalid enrolment details");
			
			}else {
				
				employeeEntity = (EmployeeEntity)JSONFormatter.buildJSONObject(strEmployeeDetails, EmployeeEntity.class);
				
				if(employeeEntity==null) {
					throw new InvalidRequestException(" Invalid enrolment details");
				}
				
				employeeService.save(employeeEntity);
			}
			return JSONFormatter.buildStringObject(employeeEntity);
		}catch(JSONConverterException e) {
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(Exception e) {
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.EMPLOYEES_URL)
	public String employeeDetails(@PathVariable("empid") String strEmpId) {
		LOG.info("Entered Request for Employee Details ");
		EmployeeEntity employeeEntity = null;
		
		try {
			
			if((employeeEntity=employeeService.getEmployeeDetails(strEmpId))==null){
				throw new ResourceNotFoundException(" Requested Employee Details Could not be Found");
			}
			
			return JSONFormatter.buildStringObject(employeeEntity);
		
		}catch(JSONConverterException e) {
			LOG.error("Error while Fetching EMployee Details,JSON Converter Issues",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(ResourceNotFoundException resourceNotFoundException) {
			LOG.error("Requested Employee Details Could not be Found",resourceNotFoundException);
			throw new ResourceNotFoundException(" Requested Employee Details Could not be Found");
		}catch(Exception e) {
			LOG.error("Error while Fetching EMployee Details,General Exception",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.ADVOCATES_LIST_URL)
	public String advocatesList() {
		LOG.info("Entered Request for Advocates Details ");
		EmployeeEntity employeeEntity = null;
		AdvocateListResponse baseResponse = new AdvocateListResponse();
		
		List<RegisteredAdvocatesModel> lstRegisteredAdvocatesModel = null;
		
		try {
			lstRegisteredAdvocatesModel = employeeService.getAdvocates("advocate");
			if(lstRegisteredAdvocatesModel==null || lstRegisteredAdvocatesModel.size()<1){
				throw new ResourceNotFoundException(" Requested Advocates Details Could not be Found");
			}
			baseResponse.setStatusCode("200");
			baseResponse.setResponse(lstRegisteredAdvocatesModel);
			return JSONFormatter.buildStringObject(baseResponse);
		
		}catch(JSONConverterException e) {
			LOG.error("Error while Fetching EMployee Details,JSON Converter Issues",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(ResourceNotFoundException resourceNotFoundException) {
			LOG.error("Requested Employee Details Could not be Found",resourceNotFoundException);
			throw new ResourceNotFoundException(" Requested Employee Details Could not be Found");
		}catch(Exception e) {
			LOG.error("Error while Fetching EMployee Details,General Exception",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
}
