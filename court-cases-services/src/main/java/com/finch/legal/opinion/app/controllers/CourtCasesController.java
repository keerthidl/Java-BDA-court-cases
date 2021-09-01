package com.finch.legal.opinion.app.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.finch.legal.opinion.app.employee.model.ReadAllCasesResponse;
import com.finch.legal.opinion.app.employee.model.ReadCaseResponse;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.services.ContemptService;
import com.finch.legal.opinion.app.services.CourtCaseService;
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
	public String addNewCase(@RequestBody String strCourtCase) {
		LOG.info(" Entered New Case Adding Service ");
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		BaseResponse baseResponse = new BaseResponse();
		int id=0;
	
		try {
			
			if(strCourtCase==null || strCourtCase.trim().length()<1) {
				throw new InvalidRequestException(" Invalid court case payload"); 
			
			} 
			
			System.out.println(" PAY LOAD IS "+strCourtCase);
				
			id = courtCaseService.addCourtCase(((CourtCaseDetailsModel)JSONFormatter.buildJSONObject(strCourtCase, CourtCaseDetailsModel.class)));
		
		    baseResponse.setStatus("200");
		    baseResponse.setResult(""+id);
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(InvalidRequestException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid new court case details");
		}catch(Exception e) {
			LOG.error(" add court case failed with general exception",e);
			throw new InternalServerException(" error occurred while processing the request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.COURT_CASE_READ_URL)
	public String getCourtCases(@PathVariable("id") String id) {
		LOG.info(" Entered Reading acourt case");
		CourtCaseDetailsModel courtCaseDetailsModel = null;
	   
	    ReadCaseResponse readCaseResponse = null;
		try {
			
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
	public String updateCase(@RequestBody String strCourtCase,@PathVariable("id") String id) {
		LOG.info("Entered Updating Case Details ");
		CourtCaseEntity advocatesEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		int id1=0;
		try {
			
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
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
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
	
	@DeleteMapping(value=AppConstants.COURT_CASE_DELETE_URL)
	public String deleteCase(@PathVariable("id") String caseId) {
		LOG.info(" Entered Delete Court Case ");
		CourtCaseDetailsModel courtCaseDetailsModel=null;
		BaseResponse baseResponse = new BaseResponse();
		try {
			
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
			
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add court case failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
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
	public String getAllCases() {
		LOG.info(" Entered retreiving all court cases");
	    List<CourtCaseDetailsModel> lstCourtCaseDetailsModel=null; 
	    ReadAllCasesResponse readAllcasesResponse = new ReadAllCasesResponse();
		try {
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
