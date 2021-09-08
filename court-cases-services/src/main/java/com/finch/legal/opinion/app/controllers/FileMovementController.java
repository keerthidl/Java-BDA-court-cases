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
import com.finch.legal.opinion.app.employee.model.ReadAllFileMovementResponse;
import com.finch.legal.opinion.app.employee.model.ReadCaseResponse;
import com.finch.legal.opinion.app.employee.model.ReadFileMovementResponse;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.services.FileMovementService;
import com.finch.legal.opinion.app.services.AuthenticationService;
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
public class FileMovementController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(FileMovementController.class);
	
	/** employee repository **/
	@Autowired
	private FileMovementService fileMovementService;
	
	 /** employee repository **/
	@Autowired
	private CaseHistoryService caseHistoryService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.FILE_MOVEMENT_ADD_URL)
	public String addNewFileMovement(@RequestBody String strFileMovementModel,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered FileMovement Adding Service ");
		FileMovementEntity fileMovementEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		AddCaseResult result=null;
		int id=0;
	    String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(strFileMovementModel==null || strFileMovementModel.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt payload"); 
			
			}
			LOG.info(" Payload is "+strFileMovementModel);
			fileMovementEntity = fileMovementService.addFileMovement(((FileMovementEntity)JSONFormatter.buildJSONObject(strFileMovementModel, FileMovementEntity.class)));
			
			baseResponse.setStatus("200");
			baseResponse.setResult(""+fileMovementEntity.getId());
			
			
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(fileMovementEntity.getCase_main_id(),""+fileMovementEntity.getCase_main_id(),"Case Document Added","File Movement from :<B>"+fileMovementEntity.getFrom_dept_id() +"</B> to on "+fileMovementEntity.getTo_dept_id()+ " on "+GeneralUtil.getTodaysDate()+"</B>",userId));
		
			
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add file movement failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid file movement details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" add file movement failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid filemovement details");
		}catch(Exception e) {
			LOG.error(" add file movement failed with general exception",e);
			throw new InternalServerException(" error occurred while processing add file movement request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.FILE_MOVEMENT_READ_URL)
	public String getFileMovementEntity(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Reading File Movement");
		FileMovementEntity fileMovementEntity = null;
		ReadFileMovementResponse readFileMovementResponse = new ReadFileMovementResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid File Movement id"); 
			
			}
			
			fileMovementEntity = fileMovementService.getFileMovementEntity(id);
			if(fileMovementEntity==null) {
				throw new ResourceNotFoundException(" Requested File Movement Details Not Found");
			}
			readFileMovementResponse.setStatus("200");
			readFileMovementResponse.setResult(fileMovementEntity);
		
			return JSONFormatter.buildStringObject(readFileMovementResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" retreiving File Movement details failed",e);
			throw new InvalidRequestException("retreiving File Movement details failed");
		}catch(InvalidRequestException e) {
			
			LOG.error("retreiving File Movement details failed, invalid request",e);
			throw new InvalidRequestException("retreiving file movement details failed, general exception");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			
			LOG.error("retreiving contempt details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving contempt details failed, general exception");
		}catch(Exception e) {
			
			LOG.error("retreiving contempt details failed, general exception",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@PutMapping(value=AppConstants.FILE_MOVEMENT_UPDATE_URL)
	public String updateFileMovement(@RequestBody String strFileMovementDetails, @PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		System.out.println(" entered update file movement details ");
		FileMovementEntity fileMovementEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
	
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			if(strFileMovementDetails==null || strFileMovementDetails.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt id");
			
			}
			
			fileMovementEntity = fileMovementService.getFileMovementEntity(id);
			if(fileMovementEntity==null) {
				throw new ResourceNotFoundException(" Requested File Movement Details Not Found");
			}
			
			fileMovementEntity = (FileMovementEntity)JSONFormatter.buildJSONObject(strFileMovementDetails, FileMovementEntity.class);
			fileMovementEntity.setId(Integer.parseInt(id));
			fileMovementEntity = fileMovementService.updateFileMovement(fileMovementEntity);
			baseResponse.setStatus("200");
			baseResponse.setResult(""+fileMovementEntity.getId());
			
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(fileMovementEntity.getCase_main_id(),""+fileMovementEntity.getCase_main_id(),"Case Document Added","File Movement from :<B>"+fileMovementEntity.getFrom_dept_id() +"</B> to on "+fileMovementEntity.getTo_dept_id()+ " on "+GeneralUtil.getTodaysDate()+"</B>",userId));
			
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" update filemovement failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" update file movement failed with invalid request",e);
			throw new InvalidRequestException(" Invalid contempt  id");
		}catch(ResourceNotFoundException e) {
			LOG.error(" update file movement failed with invalid request",e);
			throw new ResourceNotFoundException(" Invalid contempt  id");
		}catch(Exception e) {
			LOG.error("update  file movement failed with general exception",e);
			throw new InternalServerException(" error occurred while deleting file movement the request");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@DeleteMapping(value=AppConstants.FILE_MOVEMENT_DELETE_URL)
	public String deleteFileMovement(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Delete File Movement ");
		FileMovementEntity fileMovementEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid file movement id");
			
			}
			
			fileMovementEntity = fileMovementService.getFileMovementEntity(id);
			if(fileMovementEntity==null) {
				throw new ResourceNotFoundException(" Requested File Movement Details Not Found");
			}
			fileMovementService.deleteFileMovementRecord(id);
			baseResponse.setStatus("200");
			baseResponse.setResult("");
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(fileMovementEntity.getCase_main_id(),""+fileMovementEntity.getCase_main_id(),"Case Document Added","File Movement from :<B>"+fileMovementEntity.getFrom_dept_id() +"</B> to on "+fileMovementEntity.getTo_dept_id()+ " on "+GeneralUtil.getTodaysDate()+"</B>",userId));
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" delete file movement failed, json conversion",e);
			throw new InvalidRequestException(" Invalid file movement");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" delete file movement failed with invalid request",e);
			throw new InvalidRequestException(" Invalid contempt  id");
		}catch(ResourceNotFoundException e) {
			LOG.error(" delete file movement failed with invalid request",e);
			throw new ResourceNotFoundException(" Invalid contempt  id");
		}catch(Exception e) {
			LOG.error("delete  file movement failed with general exception",e);
			throw new InternalServerException(" error occurred while deleting file movement the request");
		}
	}
	
	/**
	 * employee enrollment service
	 */
	
	@RequestMapping(value=AppConstants.ALL_FILE_MOVEMENT_URL,method=RequestMethod.GET)
	
	public String getFileMovement(@PathVariable("caseid") String caseId,HttpServletRequest httpServletRequest) {
		LOG.info(" Retreiving file movements Details ");
		
	    ReadAllFileMovementResponse readAllFileMovementResponse = new ReadAllFileMovementResponse();
		List<FileMovementEntity> lstFileMovementEntity=null;
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(caseId==null || caseId.trim().length()<1) {
				throw new InvalidRequestException(" Invalid court case id"); 
			
			}
			
			lstFileMovementEntity = fileMovementService.getAllRecords(caseId);
			
			if(lstFileMovementEntity==null) {
				throw new ResourceNotFoundException("Requested Resource Not Found");
			}
			readAllFileMovementResponse.setStatus("200");
			readAllFileMovementResponse.setResult(lstFileMovementEntity);
				
			return JSONFormatter.buildStringObject(readAllFileMovementResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" retreiving file movement details failed",e);
			throw new InvalidRequestException("retreiving file movement details failed");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" delete file movement failed with invalid request",e);
			throw new InvalidRequestException(" Invalid contempt  id");
		}catch(ResourceNotFoundException e) {
			LOG.error(" delete file movement failed with invalid request",e);
			throw new ResourceNotFoundException(" Invalid contempt  id");
		}catch(Exception e) {
			LOG.error("delete  file movement failed with general exception",e);
			throw new InternalServerException(" error occurred while deleting file movement the request");
		}
	}

}