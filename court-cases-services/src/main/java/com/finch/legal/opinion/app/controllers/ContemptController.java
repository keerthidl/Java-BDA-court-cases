package com.finch.legal.opinion.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

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
import com.finch.legal.opinion.app.employee.model.AddCaseResponse;
import com.finch.legal.opinion.app.employee.model.AddCaseResult;
import com.finch.legal.opinion.app.employee.model.BaseResponse;
import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ReadAllContemptResponse;
import com.finch.legal.opinion.app.employee.model.ReadCaseResponse;
import com.finch.legal.opinion.app.employee.model.ReadContempResponse;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.services.ContemptService;
import com.finch.legal.opinion.app.services.AuthenticationService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL,"*"}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.COURT_CASE_BASE_URL)
public class ContemptController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(ContemptController.class);
	
	/** employee repository **/
	@Autowired
	private ContemptService contemptService;
	
	 
	
	/** ServiceAuthenticationService **/
	@Autowired
	private AuthenticationService authenticationService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private CaseHistoryService caseHistoryService;
	
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.CONTEMPT_ADD_URL)
	public String addNewContempt(@RequestBody String strContempt,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Contempt Adding Service ");
		ContemptEntity contemptEntity = null;
		AddCaseResponse addCaseResponse = new AddCaseResponse();
		AddCaseResult result=null;
		BaseResponse baseResponse = new BaseResponse();
	    String userId="";
		
	    try {
	    	
	    	authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			
			if(strContempt==null || strContempt.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt payload"); 
			
			} 
				
			contemptEntity = contemptService.addContempt(((ContemptEntity)JSONFormatter.buildJSONObject(strContempt, ContemptEntity.class)));
			
		    
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(contemptEntity.getCase_main_id(),""+contemptEntity.getCase_main_id(),"Case Contempt Added","Case Contempt :<B>"+contemptEntity.getContempt_details() +"</B>  of No :<B>"+contemptEntity.getContempt_no()+ "</B> contempt date <B>"+contemptEntity.getContempt_date()+" added</B>",userId));

			
			baseResponse.setStatus("200");
			baseResponse.setResult(""+contemptEntity.getId());
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add contempt failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid contempt details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" add contempt failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid contempt details");
		}catch(Exception e) {
			LOG.error(" add contempt failed with general exception",e);
			throw new InternalServerException(" error occurred while processing add contempt request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.CONTEMPT_READ_URL)
	public String getContempt(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Reading contempt");
		ContemptEntity contemptEntity = null;
		ReadContempResponse readContempResponse = new ReadContempResponse();
		String userId="";
		
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt id"); 
			
			}
			
			
			contemptEntity = contemptService.getContemptRecord(id);
			
		
			if(contemptEntity==null) {
				throw new ResourceNotFoundException(" Requested Court Case Details Not Found");
			}
			readContempResponse.setStatus("200");
			readContempResponse.setResult(contemptEntity);
			return JSONFormatter.buildStringObject(readContempResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" retreiving contempt details failed",e);
			throw new InvalidRequestException("retreiving contempt details failed");
		}catch(InvalidRequestException e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, invalid request",e);
			throw new InternalServerException("retreiving contempt details failed, general exception");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving contempt details failed, general exception");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, general exception",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@PutMapping(value=AppConstants.CONTEMPT_UPDATE_URL)
	public String updateCase(@RequestBody String strContemptDetails, @PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		System.out.println(" entered update contempt details ");
		ContemptEntity contemptEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			
			if(strContemptDetails==null || strContemptDetails.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt id");
			
			}
				
			contemptEntity = contemptService.getContemptRecord(id);
			
			
			if(contemptEntity==null) {
				throw new ResourceNotFoundException(" Requested Contempt Details Not Found");
			}
			contemptEntity = ((ContemptEntity)JSONFormatter.buildJSONObject(strContemptDetails, ContemptEntity.class));
			contemptEntity.setId(Integer.parseInt(id));
			contemptEntity = contemptService.updateContempt(contemptEntity);
			
			baseResponse.setStatus("200");
			baseResponse.setResult(""+contemptEntity.getId());
	
		    
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(contemptEntity.getCase_main_id(),""+contemptEntity.getCase_main_id(),"Case Contempt Updated","Case Contempt :<B>"+contemptEntity.getContempt_details() +"</B>  of No :<B>"+contemptEntity.getContempt_no()+ "</B> contempt date <B>"+contemptEntity.getContempt_date()+" updated</B>",userId));

			return JSONFormatter.buildStringObject(baseResponse);
			
		}catch(JSONConverterException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" error occurred while processing the request");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new ResourceNotFoundException(" error occurred while processing the request");
		}catch(Exception e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@DeleteMapping(value=AppConstants.CONTEMPT_DELETE_URL)
	public String deleteCase(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Delete Contempt ");
		ContemptEntity contemptEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt id");
			
			}
				
			contemptEntity = contemptService.getContemptRecord(id);
		
			
			if(contemptEntity==null) {
				throw new ResourceNotFoundException(" Requested Contempt Details Not Found");
			}
			
			
			contemptService.delete(id);
			baseResponse.setStatus("200");
			baseResponse.setResult("");
			  
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(contemptEntity.getCase_main_id(),""+contemptEntity.getCase_main_id(),"Case Contempt Added","Case Contempt :<B>"+contemptEntity.getContempt_details() +"</B>  of No :<B>"+contemptEntity.getContempt_no()+ "</B> contempt date <B>"+contemptEntity.getContempt_date()+" added</B>",userId));

			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" error occurred while processing the request");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new ResourceNotFoundException(" error occurred while processing the request");
		}catch(Exception e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
	}
	
	/**
	 * employee enrollment service
	 */
	
	@RequestMapping(value=AppConstants.ALL_CONTEMPTS_URL,method=RequestMethod.GET)
	
	public String getCase(@PathVariable("caseid") String caseId,HttpServletRequest httpServletRequest) {
		System.out.println(" Retreiving contempts Details ");
		ReadAllContemptResponse readAllContemptResponse = new ReadAllContemptResponse();
		List<ContemptEntity> lstContempt = null;
		String userId="";
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			
			if(caseId==null || caseId.trim().length()<1) {
				throw new InvalidRequestException(" Invalid court case id"); 
			
			}
			
			lstContempt = contemptService.getAllRecords(caseId);
			
			if(lstContempt==null || lstContempt.size()<1) {
				throw new ResourceNotFoundException("Resource Not Found Exception ");
			}
			readAllContemptResponse.setStatus("200");
			readAllContemptResponse.setResult(lstContempt);
			return JSONFormatter.buildStringObject(readAllContemptResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" error occurred while processing the request");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new ResourceNotFoundException(" error occurred while processing the request");
		}catch(Exception e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
	}
	
}