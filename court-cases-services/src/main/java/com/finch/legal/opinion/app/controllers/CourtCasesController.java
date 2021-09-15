package com.finch.legal.opinion.app.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping(AppConstants.COURT_CASE_BASE_URL)
public class CourtCasesController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(CourtCasesController.class);
	
	/** employee repository **/
	@Autowired
	private CourtCaseService courtCaseService;
	
	/** employee repository **/
	@Autowired
	private CaseHistoryService caseHistoryService;
	
	/** employee repository **/
	@Autowired
	private ContemptService contemptService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private AuthenticationService authenticationService;
	
	
	
	/**
	 * ping service
	 */

	@GetMapping(value=AppConstants.COURT_CASE_PING_URL)
	public String ping() {
		LOG.info(" Successfully executed Ping Service");
		return "Successfully Loaded Court Cases Application";
	}
	
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.COURT_CASE_ADD_URL)
	public String addNewCase(@RequestBody String strCourtCase,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered New Case Adding Service ");
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		BaseResponse baseResponse = new BaseResponse();
		int id=0;
	    String userId="";
	    
		try {
			
			long startTime = System.currentTimeMillis();
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			long endTime = System.currentTimeMillis();
			
			LOG.info(" AUTH TIME ="+((endTime-startTime)/1000));
			
			if(strCourtCase==null || strCourtCase.trim().length()<1) {
				throw new InvalidRequestException(" Invalid court case payload"); 
			
			} 
			
			startTime = System.currentTimeMillis();
		   courtCaseDetailsModel = ((CourtCaseDetailsModel)JSONFormatter.buildJSONObject(strCourtCase, CourtCaseDetailsModel.class));
		   
		   endTime = System.currentTimeMillis();
			
			LOG.info(" OBJECT TIME ="+((endTime-startTime)/1000));
			
		   
		   if(courtCaseDetailsModel.getPrayer()!=null && courtCaseDetailsModel.getPrayer().length()>5000) {
			   throw new InvalidRequestException("Prayer Field Length more than allowed characters (>5000");
		   }
		   if(courtCaseDetailsModel.getOrder_summary()!=null && courtCaseDetailsModel.getOrder_summary().length()>5000) {
			   throw new InvalidRequestException("Order Summary Field Length more than allowed characters (>5000");
		   }
		   if(courtCaseDetailsModel.getCompliance_report()!=null && courtCaseDetailsModel.getCompliance_report().length()>5000) {
			   throw new InvalidRequestException("Compliance Report Field Length more than allowed characters (>5000");
		   }
		 
		   if(courtCaseDetailsModel.getOrder_status()!=null && courtCaseDetailsModel.getOrder_status().length()>254) {
			   throw new InvalidRequestException("Order Status Field Length more than allowed characters (>5000");
		   }
		   
		  
		   
			id = courtCaseService.addCourtCase(courtCaseDetailsModel);
		
		    baseResponse.setStatus("200");
		    baseResponse.setResult(""+id);
		    
		    startTime = System.currentTimeMillis();															
		    caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(id,""+id,"Case Created","Case No:<B>"+courtCaseDetailsModel.getCase_id() +" Created On"+GeneralUtil.getTodaysDate()+"</B>",userId));

		    endTime = System.currentTimeMillis();
			
			LOG.info(" QUERY TIME ="+((endTime-startTime)/1000));
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid new court case details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(SQLIntegrityConstraintViolationException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Duplicate Record");
		}catch(DataIntegrityViolationException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Duplicate Record");
		}catch(ConstraintViolationException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Duplicate Record");
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error(" add court case failed with general exception",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.COURT_CASE_READ_URL)
	public String getCourtCases(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Reading acourt case ========");
		CourtCaseDetailsModel courtCaseDetailsModel = null;
	    String userId="";
	    ReadCaseResponse readCaseResponse = null;
		try {
			
			LOG.info(" BEARER TOKEN ================="+httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			//authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			//userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			LOG.info(" BEARER TOKEN ccccc "+httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid court case payload"); 
			
			}
			
			courtCaseDetailsModel = courtCaseService.getCourtCaseDetails(id);
			contemptService.getContemptEntity(id);
			caseHistoryService.getLstCaseHistoryEntity(id);
			
			if(courtCaseDetailsModel==null) {
				throw new ResourceNotFoundException(" Requested Court Case Details Not Found");
			}
			
			readCaseResponse = new ReadCaseResponse();
			readCaseResponse.setStatus("200");
			readCaseResponse.setResult(courtCaseDetailsModel);
			return JSONFormatter.buildStringObject(readCaseResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" retreiving court case details failed",e);
			throw new InvalidRequestException("retreiving court case details failed");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, invalid request",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, requested resource not found",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, general exception",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@PutMapping(value=AppConstants.COURT_CASE_UPDATE_URL)
	public String updateCase(@RequestBody String strCourtCase,@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info("Entered Updating Case Details ");
		CourtCaseEntity advocatesEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		int id1=0;
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			if(strCourtCase==null || strCourtCase.trim().length()<1) {
				throw new InvalidRequestException(" Invalid advocate details");
			
			}
			
			courtCaseDetailsModel = courtCaseService.getCourtCaseDetails(""+id);
			LOG.info(" COURT DETAILS "+strCourtCase);
			if(courtCaseDetailsModel==null) {
				throw new ResourceNotFoundException("Requested Resource Not Found");
			}
				
			id1 = courtCaseService.updateCourtCase(((CourtCaseDetailsModel)JSONFormatter.buildJSONObject(strCourtCase, CourtCaseDetailsModel.class)),id);
			
			
			//courtCaseDetailsModel = (CourtCaseDetailsModel)JSONFormatter.buildJSONObject(strCourtCase, CourtCaseDetailsModel.class);
			
			//id1 = courtCaseService.updateCourtCase(courtCaseDetailsModel,id);
		
			baseResponse.setStatus("200");
		    baseResponse.setResult(""+id);
		    
		    caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(id1,""+id,"Case Updated","Case No:<B>"+courtCaseDetailsModel.getCase_id() +" Updated On"+GeneralUtil.getTodaysDate()+"</B>",userId));

			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, invalid request",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving court case details failed, general exception");
		}catch(SQLIntegrityConstraintViolationException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Duplicate Record");
		}catch(DataIntegrityViolationException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Duplicate Record");
		}catch(ConstraintViolationException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Duplicate Record");
		}catch(Exception e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@DeleteMapping(value=AppConstants.COURT_CASE_DELETE_URL)
	public String deleteCase(@PathVariable("id") String caseId,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Delete Court Case ");
		CourtCaseDetailsModel courtCaseDetailsModel=null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(caseId==null || caseId.trim().length()<1) {
				throw new InvalidRequestException(" Invalid advocate details");
			
			}
			
           courtCaseDetailsModel = courtCaseService.getCourtCaseDetails(caseId);
			
			if(courtCaseDetailsModel==null) {
				throw new ResourceNotFoundException("Requested Resource Not Found");
			}
			baseResponse.setStatus("200");
			baseResponse.setResult(caseId);
			courtCaseService.deleteCourtCases(caseId);
			 
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(Integer.parseInt(""+caseId),caseId,"Case Contempt Updated","Case No:<B>"+courtCaseDetailsModel.getCase_id() +" Deleted On"+GeneralUtil.getTodaysDate()+"</B>",userId));

			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, invalid request",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving court case details failed, general exception");
		}catch(Exception e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
	}
	

	
	/**
	 * employee enrollment service
	 */
	
	@GetMapping(value=AppConstants.COURT_CASE_ALL_URL)
	public String getAllCases(HttpServletRequest httpServletRequest) {
		LOG.info(" Entered retreiving all court cases");
	    List<CourtCaseDetailsModel> lstCourtCaseDetailsModel=null; 
	    ReadAllCasesResponse readAllcasesResponse = new ReadAllCasesResponse();
	    String userId="";
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			lstCourtCaseDetailsModel = courtCaseService.getAllCourtCases();
			
			if(lstCourtCaseDetailsModel==null) {
	            	throw new ResourceNotFoundException("Requested Resource Not Found");
	        }
			
			
			readAllcasesResponse.setStatus("200");
			readAllcasesResponse.setResult(lstCourtCaseDetailsModel);
			
			return JSONFormatter.buildStringObject(readAllcasesResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, invalid request",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving court case details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving court case details failed, general exception");
		}catch(Exception e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
	}
}
