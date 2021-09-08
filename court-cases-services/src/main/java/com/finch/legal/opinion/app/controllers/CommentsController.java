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
import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ReadAllCommentResponse;
import com.finch.legal.opinion.app.employee.model.ReadCommentResponse;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.CommentEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.services.CommentService;
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
public class CommentsController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(CommentsController.class);
	
	/** employee repository **/
	@Autowired
	private CommentService commentService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private AuthenticationService authenticationService;
	
	
	
	
	/** ServiceAuthenticationService **/
	@Autowired
	private CaseHistoryService caseHistoryService;
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.COMMENT_ADD_URL)
	public String addNewContempt(@RequestBody String strComment,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Contempt Adding Service ");
		CommentEntity commentEntity = null;
	    BaseResponse baseResponse = new BaseResponse();
	    String userId="";
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			
			if(strComment==null || strComment.trim().length()<1) {
				throw new InvalidRequestException(" Invalid comment payload"); 
			
			}
			commentEntity = ((CommentEntity)JSONFormatter.buildJSONObject(strComment, CommentEntity.class));	
			commentEntity = commentService.addComment(commentEntity);
			
		    baseResponse.setStatus("200");
		    baseResponse.setResult(""+commentEntity.getId());
		    
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(commentEntity.getCase_main_id(),""+commentEntity.getCase_main_id(),"Case Comments Added","Case Comment :<B>"+commentEntity.getComment() +"</B>  of Type :<B>"+commentEntity.getType()+ "</B> ADDED On <B>"+commentEntity.getRecorded_on()+"</B>",userId));
			
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add comment failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid comment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" add comment failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid comment details");
		}catch(Exception e) {
			LOG.error(" add comment failed with general exception",e);
			throw new InternalServerException(" error occurred while processing add comment request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.COMMENT_READ_URL)
	public String getContempt(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Reading Comment");
		CommentEntity commentEntity = null;
	    ReadCommentResponse readCommentResponse = new ReadCommentResponse();
	    String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid contempt id"); 
			
			}
		
			commentEntity = commentService.getCommentEntity(id);
			
		
			if(commentEntity==null) {
				throw new ResourceNotFoundException(" Requested comment Details Not Found");
			}
			
			readCommentResponse.setStatus("200");
			readCommentResponse.setResult(commentEntity);
			return JSONFormatter.buildStringObject(readCommentResponse);
		}catch(JSONConverterException e) {
			
			LOG.error(" retreiving comment details failed",e);
			throw new InvalidRequestException("retreiving comment details failed");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving comment details failed, invalid request",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
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
	
	@PutMapping(value=AppConstants.COMMENTT_UPDATE_URL)
	public String updateCase(@RequestBody String strCommentDetails, @PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		System.out.println(" entered update comment details "+id);
		CommentEntity commentEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			
			if(strCommentDetails==null || strCommentDetails.trim().length()<1) {
				throw new InvalidRequestException(" Invalid comment details");
			
			}
			
			commentEntity = commentService.getCommentEntity(id);
			
			
			if(commentEntity==null) {
				throw new ResourceNotFoundException(" Requested comment Details Not Found");
			}
				
			commentEntity = ((CommentEntity)JSONFormatter.buildJSONObject(strCommentDetails, CommentEntity.class));
			commentEntity.setId(Integer.parseInt(id));
			commentEntity = commentService.updateComment(commentEntity);
			
			baseResponse.setStatus("200");
			baseResponse.setResult(""+commentEntity.getId());
			
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(commentEntity.getCase_main_id(),""+commentEntity.getCase_main_id(),"Case Comments updated","Case Comment :<B>"+commentEntity.getComment() +"</B>  of Type :<B>"+commentEntity.getType()+ "</B> Updated On <B>"+commentEntity.getRecorded_on()+"</B>",userId));
				
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
	
	@DeleteMapping(value=AppConstants.COMMENT_DELETE_URL)
	public String deleteComment(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Delete Comment ");
		CommentEntity commentEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid comment id");
			
			}
			
			commentEntity = commentService.getCommentEntity(id);
			
			
			if(commentEntity==null) {
				throw new ResourceNotFoundException(" Requested comment Details Not Found");
			}
				
			commentService.deleteComment(id);
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(commentEntity.getCase_main_id(),""+commentEntity.getCase_main_id(),"Case Comments updated","Case Comment :<B>"+commentEntity.getComment() +"</B>  of Type :<B>"+commentEntity.getType()+ "</B> Deleted On <B>"+commentEntity.getRecorded_on()+"</B>",userId));
				
			baseResponse.setStatus("200");
			baseResponse.setResult("");
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" update comment failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving comment details failed, invalid request",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
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
	
	@RequestMapping(value=AppConstants.ALL_COMMENTS_URL,method=RequestMethod.GET)
	
	public String getCase(@PathVariable("caseid") String caseId,HttpServletRequest httpServletRequest) {
		System.out.println(" Retreiving all comments "+caseId);
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		ReadAllCommentResponse readAllCommentResponse = new ReadAllCommentResponse();
		String userId="";
		
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(caseId==null || caseId.trim().length()<1) {
				throw new InvalidRequestException(" Invalid comment id"); 
			
			}
			
		
			List<CommentEntity> lstCommentEntity = commentService.getAllRecords(caseId);
			
			if(lstCommentEntity==null) {
				throw new ResourceNotFoundException("Requested Resource Not Found");
			}
			
			readAllCommentResponse.setStatus("200");
			readAllCommentResponse.setResult(lstCommentEntity);
			return JSONFormatter.buildStringObject(readAllCommentResponse);
			
		}catch(JSONConverterException e) {
			LOG.error(" update comment failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid enrolment details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
		
			LOG.error("retreiving comment details failed, invalid request",e);
			throw new InternalServerException("retreiving comment details failed, general exception");
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