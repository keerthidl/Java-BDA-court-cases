package com.finch.legal.opinion.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.BaseResponse;
import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ReadAllCasesResponse;
import com.finch.legal.opinion.app.employee.model.ReadCaseResponse;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.AuthenticationService;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.services.ContemptService;
import com.finch.legal.opinion.app.services.CourtCaseService;
import com.finch.legal.opinion.app.util.GeneralUtil;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL,"*"}, allowedHeaders = "*")
@RestController

public class PingController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(PingController.class);
	
	
	
	/**
	 * ping service
	 */

	@GetMapping("/")
	public String ping() {
		LOG.info(" Successfully executed Ping Service");
		return "Successfully Loaded Court Cases Application";
	}

}
