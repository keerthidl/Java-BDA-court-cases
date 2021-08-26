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
import com.finch.legal.opinion.app.employee.model.ReadCaseResponse;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.services.DocumentsService;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.COURT_CASE_BASE_URL)
public class DocumentsController {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(DocumentsController.class);
	
	/** employee repository **/
	@Autowired
	private DocumentsService documentsService;
	
	
	
	/**
	 * employee enrollment service
	 */
	
	@PostMapping(value=AppConstants.DOCUMENT_ADD_URL)
	public String addNewDocument(@RequestBody String strDocument) {
		LOG.info(" Entered Document Adding Service ");
		DocumentEntity documentEntity = null;
	    BaseResponse baseResponse = new BaseResponse();
		
		try {
			
			if(strDocument==null || strDocument.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document payload"); 
			
			}
				
			documentEntity = documentsService.addDocument(((DocumentEntity)JSONFormatter.buildJSONObject(strDocument, DocumentEntity.class)));
		    baseResponse.setStatus("200");
			baseResponse.setResult(JSONFormatter.buildStringObject(documentEntity));
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" add document failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid contempt details");
		}catch(InvalidRequestException e) {
			LOG.error(" add document failed with invalid payload",e);
			throw new InvalidRequestException(" Invalid document details");
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
	public String getDocuments(@PathParam("id") String caseId) {
		LOG.info(" Entered Reading contempt");
		BaseResponse baseResponse = new BaseResponse();
		List<DocumentEntity> lstDocumentEntity = null;
		
		try {
			
			if(caseId==null || caseId.trim().length()<1) {
				throw new InvalidRequestException(" Invalid case id"); 
			
			}
		
			lstDocumentEntity = documentsService.getAllRecords(caseId);
			if(lstDocumentEntity==null) {
				throw new ResourceNotFoundException(" Requested Court Case Details Not Found");
			}
			baseResponse.setStatus("200");
			baseResponse.setResult(JSONFormatter.buildStringObject(lstDocumentEntity));
		
			return JSONFormatter.buildStringObject(baseResponse);
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
	public String updateDocument(@RequestBody String strDocumentEntity, @PathParam("id") String id) {
		System.out.println(" entered update document details ");
		DocumentEntity documentEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		try {
			
			if(strDocumentEntity==null || strDocumentEntity.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document id");
			
			}
				
			documentEntity = documentsService.getDocumentEntity(id);
			
			if(documentEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			documentEntity = documentsService.updateDocumentEntity(((DocumentEntity)JSONFormatter.buildJSONObject(strDocumentEntity, DocumentEntity.class)));
			baseResponse.setResult(JSONFormatter.buildStringObject(documentEntity));
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" update document failed with JSON Conversion",e);
			throw new InvalidRequestException(" Invalid document details");
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
	public String deleteDocument(@PathParam("id") String id) {
		LOG.info(" Entered Delete Document ");
		DocumentEntity documentEntity = null;
		BaseResponse baseResponse = new BaseResponse();
		
		try {
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document id");
			
			}
			
			documentEntity = documentsService.getDocumentEntity(id);
			
			if(documentEntity==null) {
				throw new ResourceNotFoundException("Requested Document Not Found");
			}
			documentsService.deleteFDocument(id);
			baseResponse.setStatus("200");
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" delete document failed, json conversion",e);
			throw new InvalidRequestException(" Invalid contempt");
		}catch(InvalidRequestException e) {
			LOG.error(" delete document failed with invalid request",e);
			throw new InvalidRequestException(" Invalid contempt  id");
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
	
	public String getDocument(@PathVariable("id") String id) {
		LOG.info(" Retreiving document Details ");
		
		BaseResponse baseResponse = new BaseResponse();
		DocumentEntity documentEntities = null;
		
		try {
			
			if(id==null || id.trim().length()<1) {
				throw new InvalidRequestException(" Invalid document id"); 
			
			}
		
			documentEntities = documentsService.getDocumentEntity(id);
			
			if(documentEntities==null) {
				throw new ResourceNotFoundException("No Documents Found");
			}
			baseResponse.setStatus("200");
			baseResponse.setResult(id);
			baseResponse.setResult(JSONFormatter.buildStringObject(documentEntities));
			return JSONFormatter.buildStringObject(baseResponse);
		}catch(JSONConverterException e) {
			LOG.error(" retreiving document details failed",e);
			throw new InvalidRequestException("retreiving document details failed");
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