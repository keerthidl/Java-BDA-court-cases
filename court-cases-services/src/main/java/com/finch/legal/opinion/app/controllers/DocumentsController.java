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
import com.finch.legal.opinion.app.employee.model.ReadAllDocumentResponse;
import com.finch.legal.opinion.app.employee.model.ReadCaseResponse;
import com.finch.legal.opinion.app.employee.model.ReadDocumentResponse;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.CaseHistoryService;
import com.finch.legal.opinion.app.services.DocumentsService;
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
public class DocumentsController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(DocumentsController.class);
	
	/** employee repository **/
	@Autowired
	private DocumentsService documentsService;
	
	/** ServiceAuthenticationService **/
	@Autowired
	private AuthenticationService authenticationService;
	
	
	
	
	 /** employee repository **/
		@Autowired
		private CaseHistoryService caseHistoryService;
		
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.DOCUMENT_ADD_URL)
	public String addNewDocument(@RequestBody String strDocument,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Document Adding Service ");
		DocumentEntity documentEntity = null;
	    BaseResponse baseResponse = new BaseResponse();
		String userId ="";
		
		try {
			
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
		
			if(strDocument==null || strDocument.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document payload"); 
			
			}
				
			documentEntity = documentsService.addDocument(((DocumentEntity)JSONFormatter.buildJSONObject(strDocument, DocumentEntity.class)));
		    baseResponse.setStatus("200");
			baseResponse.setResult(""+documentEntity.getId());
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(documentEntity.getCase_main_id(),""+documentEntity.getCase_main_id(),"Case Document Added","Case Document :<B>"+documentEntity.getFile_name() +"</B> uploaded on "+GeneralUtil.getTodaysDate()+ "</B>",userId));

			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add document failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid contempt details");
		}catch(InvalidRequestException e) {
			LOG.error(" add document failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid document details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving contempt details failed, general exception");
		}catch(Exception e) {
			LOG.error(" add document failed with general exception",e);
			throw new InternalServerException(" error occurred while processing add document request");
		}
		
	}
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.DOCUMENTS_URL)
	public String getDocuments(@PathParam("id") String caseId,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Reading contempt");
		ReadAllDocumentResponse readAllDocumentResponse = new ReadAllDocumentResponse();
		List<DocumentEntity> lstDocumentEntity = null;
		String userId="";

		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(caseId==null || caseId.trim().length()<1) {
				throw new InvalidRequestException(" Invalid case id"); 
			
			}
		
			lstDocumentEntity = documentsService.getAllRecords(caseId);
			if(lstDocumentEntity==null) {
				throw new ResourceNotFoundException(" Requested Court Case Details Not Found");
			}
			readAllDocumentResponse.setStatus("200");
			readAllDocumentResponse.setResult(lstDocumentEntity);
		
			return JSONFormatter.buildStringObject(readAllDocumentResponse);
		}catch(JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" retreiving contempt details failed",e);
			throw new InvalidRequestException("retreiving contempt details failed");
		}catch(InvalidRequestException e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, invalid request",e);
			throw new InternalServerException("retreiving contempt details failed, general exception");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, requested resource not found",e);
			throw new ResourceNotFoundException("retreiving contempt details failed, general exception");
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("retreiving contempt details failed, general exception",e);
			throw new InternalServerException("retreiving court case details failed, general exception");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@PutMapping(value=AppConstants.DOCUMENT_UPDATE_URL)
	public String updateDocument(@RequestBody String strDocumentEntity, @PathParam("id") String id,HttpServletRequest httpServletRequest) {
		System.out.println(" entered update document details ");
		DocumentEntity documentEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			if(strDocumentEntity==null || strDocumentEntity.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document id");
			
			}
				
			documentEntity = documentsService.getDocumentEntity(id);
			
			if(documentEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			documentEntity = documentsService.updateDocumentEntity(((DocumentEntity)JSONFormatter.buildJSONObject(strDocumentEntity, DocumentEntity.class)));
			baseResponse.setStatus("200");
			baseResponse.setResult(""+documentEntity.getId());
			
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(documentEntity.getCase_main_id(),""+documentEntity.getCase_main_id(),"Case Document Added","Case Document :<B>"+documentEntity.getFile_name() +"</B> uploaded on "+GeneralUtil.getTodaysDate()+ "</B>",userId));

			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" update document failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid document details");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(InvalidRequestException e) {
			LOG.error(" add document case failed with JSON Conversion",e);
			throw new InvalidRequestException(" error occurred while processing the update document request");
		}catch(ResourceNotFoundException e) {
			LOG.error(" add document case failed with JSON Conversion",e);
			throw new ResourceNotFoundException(" error occurred while processing the update document request");
		}catch(Exception e) {
			LOG.error(" add document case failed with JSON Conversion",e);
			throw new InternalServerException(" error occurred while processing the update document request");
		}
		
	}
	/**
	 * employee enrollment service
	 */
	
	@DeleteMapping(value=AppConstants.DOCUMENT_DELETE_URL)
	public String deleteDocument(@PathParam("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Delete Document ");
		DocumentEntity documentEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document id");
			
			}
			
			documentEntity = documentsService.getDocumentEntity(id);
			
			if(documentEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			documentsService.deleteFDocument(id);
			baseResponse.setStatus("200");
			caseHistoryService.addCaseHistory(caseHistoryService.buildCaseHistory(documentEntity.getCase_main_id(),""+documentEntity.getCase_main_id(),"Case Document Added","Case Document :<B>"+documentEntity.getFile_name() +"</B> deleted on "+GeneralUtil.getTodaysDate()+ "</B>",userId));

			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" delete document failed, json conversion",e);
			throw new InvalidRequestException(" Invalid contempt");
		}catch(InvalidRequestException e) {
			LOG.error(" delete document failed with invalid request",e);
			throw new InvalidRequestException(" Invalid contempt  id");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			
			LOG.error(" retreiving document details failed",e);
			throw new ResourceNotFoundException("retreiving document details failed");
		}catch(Exception e) {
			LOG.error("delete document failed with general exception",e);
			throw new InternalServerException(" error occurred while deleting document the request");
		}
	}
	
	/**
	 * employee enrollment service
	 */
	
	@RequestMapping(value=AppConstants.DOCUMENT_URL,method=RequestMethod.GET)
	
	public String getDocument(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Retreiving document Details ");
		
		ReadDocumentResponse readDocumentResponse = new ReadDocumentResponse();
		DocumentEntity documentEntities = null;
		String userId="";
		try {
			authenticationService.isAuthenticationValid(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			
			userId = authenticationService.getUserId(httpServletRequest.getHeader(AppConstants.AUTH_HEADER_KEY));
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document id"); 
			
			}
		
			documentEntities = documentsService.getDocumentEntity(id);
			
			if(documentEntities==null) {
				throw new ResourceNotFoundException("No Documents Found");
			}
			readDocumentResponse.setStatus("200");
			readDocumentResponse.setResult(documentEntities);
			
			return JSONFormatter.buildStringObject(readDocumentResponse);
		}catch(JSONConverterException e) {
			LOG.error(" retreiving document details failed",e);
			throw new InvalidRequestException("retreiving document details failed");
		}catch(UnAuthorizedAccessException e) {
			LOG.error(" add court case failed with invalid payload",e);
			throw new UnAuthorizedAccessException(" Un-Authorized Access");
		}catch(ResourceNotFoundException e) {
			
			LOG.error(" retreiving document details failed",e);
			throw new ResourceNotFoundException("retreiving document details failed");
		}catch(InvalidRequestException e) {
			LOG.error(" retreiving document details failed",e);
			throw new InvalidRequestException("retreiving document details failed");
		}catch(Exception e) {
			
			LOG.error("retreiving document details failed, general exception",e);
			throw new InternalServerException("retreiving document details failed, general exception");
		}
	}
	

}