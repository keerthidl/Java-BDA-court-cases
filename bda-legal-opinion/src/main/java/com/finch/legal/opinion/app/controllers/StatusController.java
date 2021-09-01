package com.finch.legal.opinion.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.LegalOpinionUpdateRequest;
import com.finch.legal.opinion.app.employee.model.LegalOpinionsSearchModel;
import com.finch.legal.opinion.app.employee.model.LoginDetails;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.services.EmployeeService;
import com.finch.legal.opinion.app.services.StatusService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.ROOT_URL)
public class StatusController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(StatusController.class);
	/** employee service **/
	@Autowired
	private StatusService statusService;
	
	/**
	 * validate login credentials
	 */

	@PostMapping(value=AppConstants.STATUS_READ_URL)
	public String status() {
		LOG.info(" Entered Status ");
	  
		
		try {
			
		
			return (JSONFormatter.buildStringObject(statusService.getAllStatus()));
			
		}catch(ResourceNotFoundException e) {
			LOG.error("No status found",e);
			throw new ResourceNotFoundException("Invalid Login");
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("Error while returning status",e);
			throw new InternalServerException("Error occurred while processing the Request");
		}
	}
}
