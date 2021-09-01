package com.finch.legal.opinion.app.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.amazonaws.services.identitymanagement.model.ErrorDetails;
import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.LegalOpinionAddResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionAllResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionQueryResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionReadResponse;
import com.finch.legal.opinion.app.employee.model.LegalOpinionRequestDataModel;
import com.finch.legal.opinion.app.employee.model.LegalOpinionUpdateRequest;
import com.finch.legal.opinion.app.employee.model.LegalOpinionsSearchModel;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.exceptions.InternalServerException;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.exceptions.ResourceNotFoundException;
import com.finch.legal.opinion.app.services.EmployeeService;
import com.finch.legal.opinion.app.services.LegalOpinionService;
import com.finch.legal.opinion.app.util.JSONFormatter;
import com.finch.legal.opinion.app.util.NotificationService;

/**
 * 
 * @author finch
 *
 */
@CrossOrigin(origins ={AppConstants.WILD_CARD_URL, AppConstants.LOCAL_HOST_URL}, allowedHeaders = "*")
@RestController
@RequestMapping(AppConstants.ROOT_URL)
public class LegalOpinionsController {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(LegalOpinionsController.class);
	
	/** legal opinion service **/
	@Autowired
	private LegalOpinionService legalOpinionService;
	
	/** legal opinion service **/
	@Autowired
	private EmployeeService employeeService;
	
	/** communication service **/
	@Autowired
	private NotificationService notificationService;
	

	/**
	 * adding new legal opinion request
	 */

	@PostMapping(value=AppConstants.LEGAL_OPINION_ADD_URL)
	public String addLegalOpinionRequest(@RequestBody String strLegalReqOpinionDetails,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		LOG.info("Entered Adding new Legal Opinion Request");
		List<ErrorDetails> lstErrorDetails = null;
		LegalOpinionRequestEntity legalOpinionRequestEntity = null;
		LegalOpinionRequestDataModel legalOpinionRequestDataModel =null;
		EmployeeEntity employeeEntity = null;
		int maxCount=0;
		ValidateLegalOpinionRequest validateLegalOpinionRequest = new ValidateLegalOpinionRequest();
		LegalOpinionAddResponse legalOpinionAddResponse = new LegalOpinionAddResponse();
		
		if(strLegalReqOpinionDetails==null || strLegalReqOpinionDetails.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Request Payload");
		}else if(httpServletRequest.getHeader(AppConstants.USER_ID_KEY)!=null && httpServletRequest.getHeader(AppConstants.USER_ID_KEY).trim().length()<0) {
			throw new InvalidRequestException("No user id provided");
		}
			
		try {
			
			legalOpinionRequestDataModel = (LegalOpinionRequestDataModel)JSONFormatter.buildJSONObject(strLegalReqOpinionDetails, LegalOpinionRequestDataModel.class);
			
			String requestedBy =httpServletRequest.getHeader(AppConstants.USER_ID_KEY);
			employeeEntity = employeeService.getEmployeeByLoginId(requestedBy);
		
			legalOpinionRequestEntity = legalOpinionService.addLegalOpinionRequest(legalOpinionRequestDataModel,requestedBy);
			LOG.info(" New Legal Opinion Request Created Successfully ");
		   
		    LOG.info(" New Legal Opinion Request Notification sent Successfully ");
		    legalOpinionAddResponse.setErrors(null);
			legalOpinionAddResponse.setMessage("Success");
			httpServletResponse.setStatus(201);
			legalOpinionAddResponse.setResponse(""+legalOpinionRequestEntity.getId());
		    return JSONFormatter.buildStringObject(legalOpinionAddResponse);
			
		} catch (JSONConverterException e) {
			LOG.error(" Error while creating JSONPOJO",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Error while adding new legal opinion request",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}
	}
	/**
	 * employee enrollment service
	 */

	@PutMapping(value=AppConstants.LEGAL_OPINION_UPDATE_URL)
	public String updateLegalOpinionRequest(@PathVariable("id") String id,@RequestBody String strLegalReqOpinionDetails,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		LOG.info(" ==Enetered Legal Opinion Request Update==      "+strLegalReqOpinionDetails);
		LegalOpinionRequestEntity legalOpinionRequestEntity = null;
		LegalOpinionUpdateRequest legalOpinionUpdateRequest = null;
		ValidateLegalOpinionRequest validateLegalOpinionRequest = new ValidateLegalOpinionRequest();
		LegalOpinionAddResponse legalOpinionAddResponse = new LegalOpinionAddResponse();
		List<ErrorDetails> lstErrorDetails = null;
		List<EmployeeEntity> lstEmployeeEntities=null;
		EmployeeEntity employeeEntity = null;
		LegalOpinionRequestDataModel legalOpinionRequestDataModel =null; 
		int maxCount=0;
		String assignedId="";
		
		if(id==null || id.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Request id");
		}else if(strLegalReqOpinionDetails==null || strLegalReqOpinionDetails.trim().length()<1){
			LOG.info(" CONTROL HERE << strLegalReqOpinionDetails >> "+strLegalReqOpinionDetails);
			throw new InvalidRequestException("Invalid Legal Opinion Request Payload");
		}else if(httpServletRequest.getHeader(AppConstants.USER_ID_KEY)!=null && httpServletRequest.getHeader(AppConstants.USER_ID_KEY).trim().length()<0) {
			LOG.info(" CONTROL HERE 0000");
			throw new InvalidRequestException("No user id provided");
		}
	
		try {
			
			LOG.info(" CONTROL HERE 1 << ID >>"+id);
			legalOpinionRequestDataModel = (LegalOpinionRequestDataModel)JSONFormatter.buildJSONObject(strLegalReqOpinionDetails, LegalOpinionRequestDataModel.class);
			LOG.info(" CONTROL HERE 2 "+legalOpinionRequestDataModel);
			String requestedBy =httpServletRequest.getHeader(AppConstants.USER_ID_KEY);
			
			LOG.info(" CONTROL HERE 3 --------------"+requestedBy);
			employeeEntity = employeeService.getEmployeeById(requestedBy);
			
			LOG.info(" CONTROL HERE 4 ============"+employeeEntity);
			legalOpinionRequestEntity = legalOpinionService.getLegalOpinionRequest(id);
			LOG.info(" CONTROL HERE 6 << ROLE ID>> "+employeeEntity.getRoleId());
			lstEmployeeEntities = employeeService.getEmpByRole(AppConstants.LAW_OFFICER_ROLEID);
			
			if((employeeEntity!=null && employeeEntity.getRoleId()!=null) && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.HOD_ROLEID)) {
				legalOpinionRequestEntity.setRequestDetails(legalOpinionRequestDataModel.getRequestDetails());
				legalOpinionRequestEntity.setSurveyNumber(legalOpinionRequestDataModel.getSurveyNumber());
				legalOpinionRequestEntity.setStatus(legalOpinionRequestDataModel.getStatus());
				legalOpinionRequestEntity.setExpectedDate(legalOpinionRequestDataModel.getExpectedDate());
				legalOpinionRequestEntity.setPropertyNumber(legalOpinionRequestDataModel.getPropertyNumber());
			}else if((employeeEntity!=null && employeeEntity.getRoleId()!=null) && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADVOCATE_ROLEID)) {
				legalOpinionRequestEntity.setOpinion(legalOpinionRequestDataModel.getOpinion());
				legalOpinionRequestEntity.setStatus(legalOpinionRequestDataModel.getStatus());
			}else if((employeeEntity!=null && employeeEntity.getRoleId()!=null) && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADMIN_ROLEID)) {
				legalOpinionRequestEntity.setRequestDetails(legalOpinionRequestDataModel.getRequestDetails());
				legalOpinionRequestEntity.setSurveyNumber(legalOpinionRequestDataModel.getSurveyNumber());
				legalOpinionRequestEntity.setStatus(legalOpinionRequestDataModel.getStatus());
				legalOpinionRequestEntity.setPropertyNumber(legalOpinionRequestDataModel.getPropertyNumber());
				legalOpinionRequestEntity.setExpectedDate(legalOpinionRequestDataModel.getExpectedDate());
			}else if((employeeEntity!=null && employeeEntity.getRoleId()!=null) && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.LAW_OFFICER_ROLEID)) {
				legalOpinionRequestEntity.setOpinion(legalOpinionRequestDataModel.getOpinion());
				legalOpinionRequestEntity.setStatus(legalOpinionRequestDataModel.getStatus());
				assignedId = legalOpinionUpdateRequest.getAssignedTo();
				EmployeeEntity assignedToEmployeeEntity = employeeService.getEmployeeById(assignedId);
				
				if(assignedToEmployeeEntity==null) {
					throw new InvalidRequestException("Invalid Assignment");
				}else if(assignedToEmployeeEntity!=null && !assignedToEmployeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADVOCATE_ROLEID)) {
					throw new InvalidRequestException("Assigned Role is not matching");
				}
				legalOpinionRequestEntity.setAssignedTo(legalOpinionUpdateRequest.getAssignedTo());
			}
			
			//lstErrorDetails = validateLegalOpinionRequest.validate(legalOpinionRequestEntity);
			
			/** if(lstErrorDetails!=null && lstErrorDetails.size()>0) {
				legalOpinionAddResponse.setErrors(JSONFormatter.buildStringObject(lstErrorDetails));
				legalOpinionAddResponse.setStatusCode("400");
				legalOpinionAddResponse.setMessage("Invalid Request");
				httpServletResponse.setStatus(400);
				return JSONFormatter.buildStringObject(legalOpinionAddResponse);
			}
			**/
			
			legalOpinionRequestEntity = legalOpinionService.updateLegalOpinionRequest(legalOpinionRequestEntity,requestedBy,legalOpinionRequestDataModel);
			LOG.info(" Legal Opinion Request Entity Updated Successfully");
		
			LOG.info(" Legal Opinion Request Entity Updated Notifications Successfully");
			
			 LOG.info(" New Legal Opinion Request Notification sent Successfully ");
			 legalOpinionAddResponse.setErrors(null);
			 legalOpinionAddResponse.setMessage("Success");
			 httpServletResponse.setStatus(201);
			 legalOpinionAddResponse.setResponse(""+legalOpinionRequestEntity.getId());
			
				
			return JSONFormatter.buildStringObject(legalOpinionAddResponse);
			
		} catch (JSONConverterException e) {
			LOG.error("Error while updating the Legal Opinion Request Details, JSON Error",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		} catch (ResourceNotFoundException e) {
			LOG.error("Error while updating the Legal Opinion Request Details, Resource Not Found",e);
			throw new ResourceNotFoundException("Invalid Legal Opinion Request");
		}  catch (InvalidRequestException e) {
			LOG.error("Invalid Assignment, Employee Not Found or assigned roleis not advocate",e);
			throw new ResourceNotFoundException("Invalid Legal Opinion Request");
		} catch (Exception e) {
			LOG.error("Error while updating the Legal Opinion Request Details, GeneralError",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}
	}
	
	/**
	 * employee enrollment service
	 */
	@DeleteMapping(value=AppConstants.LEGAL_OPINION_DELETE_URL)
	public String deleteLegalRequest(@PathVariable("id") String id,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered LegalOpinion Request Deletion <<id>> "+id);		
		LegalOpinionRequestEntity legalOpinionRequestEntity = null;
		String strRquestId="";
		EmployeeEntity employeeEntity = null;
		
		if(id==null || id.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Request Id");
		}
		
		try {
			legalOpinionRequestEntity = legalOpinionService.getLegalOpinionRequest(id);
			
			if(legalOpinionRequestEntity==null) {
				throw new ResourceNotFoundException("Requested Resource Not Found");
			}else if(legalOpinionRequestEntity.getStatus()!=1){
				System.out.println(" TEST "+" Invalid Request,Resource Cannot be Deleted at this status << >> "+legalOpinionRequestEntity.getStatus());
				throw new InvalidRequestException(" Invalid Request,Resource Cannot be Deleted at this status << >> "+legalOpinionRequestEntity.getStatus());
			}
			
			strRquestId = ""+httpServletRequest.getHeader(AppConstants.USER_ID_KEY);
			employeeEntity = employeeService.getEmployeeById(strRquestId);
			LOG.info(" ID "+id+"       "+strRquestId+"::::::::::::::::: "+legalOpinionRequestEntity.getRequestedBy());
			if(strRquestId!=null && strRquestId.equalsIgnoreCase(legalOpinionRequestEntity.getRequestedBy())) {
				legalOpinionService.deleteLegalRequest(id);
				return "SUCCESS";
			}else {
				throw new InvalidRequestException("Invalid Access to this record");
			}
			
			
		}catch (ResourceNotFoundException e) {
			LOG.error(" Error while Deleting the request",e);
			throw new ResourceNotFoundException("Requested Resource Not Found");
		}catch (InvalidRequestException e) {
			e.printStackTrace();
			LOG.error(" Invalid Request,Resource Cannot be Deleted at this status ",e);
			throw new InvalidRequestException("Requested Resource Not Found");
		} catch (Exception e) {
			LOG.error(" Error while Deleting the request",e);
			throw new InternalServerException("Invalid Legal Opinion Request");
		}
	}
	
	
	
	/**
	 * employee enrollment service
	 */
	@GetMapping(value=AppConstants.LEGAL_OPINION_DETAILS_URL)
	public String getLegalOpinionRequest(@PathVariable("id") String strRequestId,HttpServletRequest httpServletRequest) {
		LOG.info(" Entered Legal Opinion Details Request ");
		LegalOpinionRequestEntity legalOpinionRequestEntity = null;
		String strRquestId="";	
		EmployeeEntity employeeEntity = null;
		String userId="";
		
		LegalOpinionRequestDataModel legalOpinionRequestDataModel = new LegalOpinionRequestDataModel();
		
		LegalOpinionReadResponse legalOpinionReadResponse = new LegalOpinionReadResponse();
		
		if(strRequestId==null || strRequestId.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Response Request");
		}else if(httpServletRequest.getHeader(AppConstants.USER_ID_KEY)==null || httpServletRequest.getHeader(AppConstants.USER_ID_KEY).trim().length()<0) {
			throw new InvalidRequestException("No user id provided");
		}
		
		try {
			
			
			userId = ""+httpServletRequest.getHeader(AppConstants.USER_ID_KEY);
			
			LOG.info(" USER ID "+userId+":::::::   "+userId);
			
			employeeEntity = employeeService.getEmployeeById(userId);
			LOG.info(" USER ID "+userId+":::::::   "+employeeEntity);
			
			if(employeeEntity.getRoleId()!=null && (employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADVOCATE_ROLEID) && !employeeEntity.getLoginId().equalsIgnoreCase(legalOpinionRequestEntity.getAssignedTo()))) {
				throw new InvalidRequestException(" Not Allowed To Access this Record");
			}else if(employeeEntity.getRoleId()!=null && (employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.HOD_ROLEID) && !employeeEntity.getLoginId().equalsIgnoreCase(legalOpinionRequestEntity.getRequestedBy()))) {
				throw new InvalidRequestException(" Not Allowed To Access this Record");
			}
			legalOpinionRequestDataModel = legalOpinionService.getLegalOpinionRequestDataModel(strRequestId,userId); 
			

			legalOpinionReadResponse.setStatusCode("200");

			legalOpinionReadResponse.setResponse(legalOpinionRequestDataModel);
			return JSONFormatter.buildStringObject(legalOpinionRequestDataModel);
		} catch (JSONConverterException e) {
			LOG.error(" Error while processing the Legal Opinion Request Details, JSON Conversion Failure",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}catch (ResourceNotFoundException e) {
			e.printStackTrace();
			LOG.error(" Error while processing the Legal Opinion Request Details, Resource Not Found Exception",e);
			throw new ResourceNotFoundException(" Requested Legal Opinion Requesy Not Found");
		}catch (InvalidRequestException e) {
			LOG.error(" Error while processing the Legal Opinion Request Details, Resource Not Found Exception",e);
			throw new InvalidRequestException(" Requested Legal Opinion Requesy Not Found");
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Error while processing the Legal Opinion Request Details,General Exception",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}
	}
	
	/**
	 * employee enrollment service
	 */
	@PostMapping(value=AppConstants.LEGAL_OPINIONS_URL)
	public String getLegalOpinionRequests(@RequestBody String searchModel, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		LOG.info(" Entered Legal Opinion Details Request ");
		LegalOpinionRequestEntity legalOpinionRequestEntity = null;
		LegalOpinionsSearchModel legalOpinionsSearchModel = null;
		
		LegalOpinionAllResponse legalOpinionAllResponse = new LegalOpinionAllResponse();
		
		List<ErrorDetails> lstErrorDetails = null;
		LegalOpinionQueryResponse legalOpinionSearchResponse = new LegalOpinionQueryResponse();
		List<LegalOpinionRequestDataModel> lstLegalOpinionRequestDataModel = null;
		String strRquestId="";
		ValidateLegalOpinionSearchRequest validateLegalOpinionSearchRequest = new ValidateLegalOpinionSearchRequest();
		
		if(searchModel==null || searchModel.trim().length()<1){
			throw new InvalidRequestException("Invalid Legal Opinion Search Request model");
		}else if(httpServletRequest.getHeader(AppConstants.USER_ID_KEY)!=null && httpServletRequest.getHeader(AppConstants.USER_ID_KEY).trim().length()<0) {
			throw new InvalidRequestException("No user id provided");
		}
		
		try {
			strRquestId = ""+httpServletRequest.getHeader(AppConstants.USER_ID_KEY);
			LOG.info(" USER ID =============> "+strRquestId);
			
	
			
			legalOpinionsSearchModel = (LegalOpinionsSearchModel)JSONFormatter.buildJSONObject(searchModel, LegalOpinionsSearchModel.class);
			
			//lstErrorDetails = validateLegalOpinionSearchRequest.validate(legalOpinionsSearchModel);
			
			if(lstErrorDetails!=null && lstErrorDetails.size()>0) {
				legalOpinionSearchResponse.setErrors(JSONFormatter.buildStringObject(lstErrorDetails));
				legalOpinionSearchResponse.setStatusCode("400");
				legalOpinionSearchResponse.setMessage("Invalid Request");
				httpServletResponse.setStatus(400);
				return JSONFormatter.buildStringObject(legalOpinionSearchResponse);
			}
			
		
			lstLegalOpinionRequestDataModel = legalOpinionService.searchLegalOpinionRequests(legalOpinionsSearchModel,strRquestId); 
			
			if(lstLegalOpinionRequestDataModel==null) {
				throw new ResourceNotFoundException(" Requested Legal Opinion Request Not Found");
			}
			
					
					
			legalOpinionAllResponse.setErrors(null);
			legalOpinionAllResponse.setStatusCode("200");
			legalOpinionAllResponse.setMessage("Success");
			legalOpinionAllResponse.setResponse(lstLegalOpinionRequestDataModel);
			httpServletResponse.setStatus(200);
			
			
			return JSONFormatter.buildStringObject(legalOpinionAllResponse);
		
		} catch (JSONConverterException e) {
			e.printStackTrace();
			LOG.error(" Error while processing the Legal Opinion Request Details, JSON Conversion Failure",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}catch (ResourceNotFoundException e) {
			LOG.error(" Error while processing the Legal Opinion Request Details, Resource Not Found Exception",e);
			throw new ResourceNotFoundException(" Requested Legal Opinion Requesy Not Found");
		}catch (Exception e) {
			LOG.error(" Error while processing the Legal Opinion Request Details,General Exception",e);
			throw new InternalServerException("Invalid Legal Opinion Response Request");
		}
	}
	
   /**
    *  1. Search Based List
    *  2. Sorting
    *  3. Index Based Returns
    *  4. Employee Based Legal Request
    *  5. Sending the Supported Documents as Part of the Legal Requests
    *  6. Upload time also single service for Documents Update
    *  7. Adding new Requests with Documents
    *  8. JWT Security
    *  9. Encrypting / Decrypting the PII data
    *  10. Field Validations = DONE
    */
	/**
	 * employee enrollment service
	 */
	
	
	public static void main(String[] args) {
		
		HashMap<String, String> hashMap = new HashMap();
		
		hashMap.put("ASSIGNEDTO", "ASSIGNEDTO");
		hashMap.put("REQID", "REQID");
		hashMap.put("REQDATE", "REQDATE");
		
		
		LegalOpinionsSearchModel legalOpinionsSearchModel = new LegalOpinionsSearchModel();
		
		legalOpinionsSearchModel.setSortField("REQID");
		legalOpinionsSearchModel.setSortingMode("ASC");
		legalOpinionsSearchModel.setUserId("22");
		legalOpinionsSearchModel.setHashMapSearchCriteria(hashMap);
		
		try {
			System.out.println(" MSG"+JSONFormatter.buildStringObject(legalOpinionsSearchModel));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
