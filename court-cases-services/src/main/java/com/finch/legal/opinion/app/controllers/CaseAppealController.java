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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.BaseResponse;
import com.finch.legal.opinion.app.employee.model.CourtCaseAppealDetailsModel;
import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ReadAllCaseAppealResponse;
import com.finch.legal.opinion.app.employee.model.ReadCaseAppealResponse;
import com.finch.legal.opinion.app.entities.CaseAppealEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.AuthenticationService;
import com.finch.legal.opinion.app.services.CaseAppealService;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.COURT_CASE_BASE_URL)
public class CaseAppealController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(CaseAppealController.class);
	
	/** employee repository **/
	@Autowired
	private CaseAppealService caseAppealService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private AuthenticationService authenticationService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private CaseHistoryService caseHistoryService;
	
	
	
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.CASE_APPEAL_ADD_URL)
	public String addNewContempt(@RequestBody String strComment, HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Case Appeal Adding Service ");
	    BaseResponse baseResponse = new BaseResponse();
	    CaseAppealEntity caseAppealEntity=null;
		String userId="";
	    try {
	    	
	    	authenticationService.isAuthenticationValid(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			if(strComment==null || strComment.trim().length()<1) {
				throw new InvalidRequestException(" Invalid comment payload"); 
			
			}
				
			int id = caseAppealService.addCourtCaseAppeal(((CourtCaseAppealDetailsModel)JSONFormatter.buildJSONObject(strComment, CourtCaseAppealDetailsModel.class)));
			
		    baseResponse.setStatus("200");
		    baseResponse.setResult(""+id);
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add case appeal failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid case appeal details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" add case appeal failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid case appeal details");
		}catch(Exception e) {
			LOG.error(" add case appeal failed with general exception",e);
			throw new InternalServerException(" error occurred while processing add case appeal request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.CASE_APPEAL_READ_URL)
	public String getContempt(@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Reading Case Appeal");
		CourtCaseAppealDetailsModel courtCaseAppealDetailsModel = null;
	    ReadCaseAppealResponse readCaseAppealResponse = new ReadCaseAppealResponse();
	    String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid Case Appeal id"); 
			
			}
		
			courtCaseAppealDetailsModel = caseAppealService.getCourtAppeaLDetails(id);
			
		
			if(courtCaseAppealDetailsModel==null) {
				throw new ResourceNotFoundException(" Requested Case Appeal Details Not Found");
			}
			
			readCaseAppealResponse.setStatus("200");
			readCaseAppealResponse.setResult(courtCaseAppealDetailsModel);
			return JSONFormatter.buildStringObject(readCaseAppealResponse);
		}catch(JSONConverterException e) {
			
			LOG.error(" retreiving Case Appeal details failed",e);
			throw new InvalidRequestException("retreiving Case Appeal details failed");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving Case Appeal details failed, invalid request",e);
			throw new InternalServerException("retreiving Case Appeal details failed, general exception");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving Case Appeal details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving Case Appeal details failed, general exception");
		}catch(Exception e) {
			
			LOG.error("retreiving Case Appeal details failed, general exception",e);
			throw new InternalServerException("retreiving Case Appeal details failed, general exception");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@PutMapping(value=AppConstants.CASE_APPEAL_UPDATE_URL)
	public String updateCase(@RequestBody String strCommentDetails, @PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		System.out.println(" entered update case appeal details "+id);
		CourtCaseAppealDetailsModel courtCaseAppealDetailsModel = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			if(strCommentDetails==null || strCommentDetails.trim().length()<1) {
				throw new InvalidRequestException(" Invalid case appeal details");
			
			}
			
			courtCaseAppealDetailsModel = caseAppealService.getCourtAppeaLDetails(id);
			
			
			if(courtCaseAppealDetailsModel==null) {
				throw new ResourceNotFoundException(" Requested case appeal Details Not Found");
			}
				
			courtCaseAppealDetailsModel = ((CourtCaseAppealDetailsModel)JSONFormatter.buildJSONObject(strCommentDetails, CourtCaseAppealDetailsModel.class));
			
			int id1 = caseAppealService.updateCourtCase(courtCaseAppealDetailsModel,id);
			
			baseResponse.setStatus("200");
			baseResponse.setResult(""+id1);
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" update case appeal failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid case appeal details");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving case appeal details failed, invalid request",e);
			throw new InternalServerException("retreiving case appeal details failed, general exception");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving comment details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving comment details failed, general exception");
		}catch(Exception e) {
			
			LOG.error("retreiving comment details failed, general exception",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@DeleteMapping(value=AppConstants.CASE_APPEAL_DELETE_URL)
	public String deleteComment(@PathVariable("id") String id, HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Delete Case Appeal ");
		CourtCaseAppealDetailsModel courtCaseAppealDetailsModel = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid Case Appeal id");
			
			}
			
			courtCaseAppealDetailsModel = caseAppealService.getCourtAppeaLDetails(id);
			
			
			if(courtCaseAppealDetailsModel==null) {
				throw new ResourceNotFoundException(" Requested Case Details Not Found");
			}
				
			caseAppealService.deleteCourtCases(id);
		
			baseResponse.setStatus("200");
			baseResponse.setResult("");
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" update comment failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving comment details failed, invalid request",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving comment details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving comment details failed, general exception");
		}catch(Exception e) {
			
			LOG.error("retreiving comment details failed, general exception",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
		}
	}
	
	/**
	 * employee enrollment service
	 */
	
	@RequestMapping(value=AppConstants.ALL_CASE_APPEALS_URL,method=RequestMethod.GET)
	
	public String getCaseAppeals(HttpServletRequest httpServletRequest) {
		System.out.println(" Retreiving all comments ");
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		ReadAllCaseAppealResponse readAllCaseAppealResponse = new ReadAllCaseAppealResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getParameter(AppConstants.AUTH_HEADER_KEY));
			
			List<CourtCaseAppealDetailsModel> lstCaseAppealEntities = caseAppealService.getAllAppeals();
			
			if(lstCaseAppealEntities==null) {
				throw new ResourceNotFoundException("Requested Resource Not Found");
			}
			
			readAllCaseAppealResponse.setStatus("200");
			readAllCaseAppealResponse.setResult(lstCaseAppealEntities);
			return JSONFormatter.buildStringObject(readAllCaseAppealResponse);
			
		}catch(JSONConverterException e) {
			LOG.error(" update comment failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving comment details failed, invalid request",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving comment details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving comment details failed, general exception");
		}catch(Exception e) {
			
			LOG.error("retreiving comment details failed, general exception",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
		}
	}
}