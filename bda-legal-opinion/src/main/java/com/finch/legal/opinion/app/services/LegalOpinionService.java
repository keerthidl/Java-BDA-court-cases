package com.finch.legal.opinion.app.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.controllers.ValidateLegalOpinionRequest;
import com.finch.legal.opinion.app.employee.model.LegalOpinionRequestDataModel;
import com.finch.legal.opinion.app.employee.model.LegalOpinionsSearchModel;
import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.DepartmentEntity;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;
import com.finch.legal.opinion.app.entities.LOpinionTransHistoryEntity;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.entities.StatusEntity;
import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;
import com.finch.legal.opinion.app.exceptions.InvalidRequestException;
import com.finch.legal.opinion.app.repositories.LegalOpinionRequestRepository;
import com.finch.legal.opinion.app.repositories.SupportingDocumentRepository;
import com.finch.legal.opinion.app.util.GeneralUtil;
import com.finch.legal.opinion.app.util.NotificationService;

/**
 * 
 * @author finch
 *
 */

@Service
public class LegalOpinionService {
	
	/** Logger **/
	private AppLogger LOG = LogManager.getLogger(LegalOpinionService.class);

	 /** employee repository **/
	@Autowired
	private LegalOpinionRequestRepository legalOpinionRequestRepository;
		
	/** employee repository **/
	@Autowired
	private SupportingDocumentRepository supportingDocumentRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	/** legal opinion service **/
	@Autowired
	private LegalOpinionService legalOpinionService;
	
	
	/** legal opinion service **/
	@Autowired
	private AdvocatesService advocatesService;
	
	/** legal opinion service **/
	@Autowired
	private DepartmentsService departmentsService;
	
	
	/** legal opinion service **/
	@Autowired
	private NotificationService notificationService;
	
	
	/** legal opinion service **/
	@Autowired
	private LOpinionCommentService lOpinionCommentService;
	
	/** legal opinion service **/
	@Autowired
	private StatusService statusService;
	
	/** legal opinion service **/
	@Autowired
	private LOpinionTransactionsHistoryService lOpinionTransactionsHistoryService;
	

	
	
		
	/**
	 * is employee exists
	 */
	public LegalOpinionRequestDataModel getLegalOpinionRequestDataModel(String reqId,String requestedBy) { 
		
		LegalOpinionRequestEntity legalOpinionRequestEntity = legalOpinionRequestRepository.findById(Integer.parseInt(reqId));
		 return transformEntityToDataModel(legalOpinionRequestEntity,requestedBy);
	}
	
	
	/**
	 * is employee exists
	 */
	public LegalOpinionRequestEntity getLegalOpinionRequest(String reqId) { 
		return  legalOpinionRequestRepository.findById(Integer.parseInt(reqId));
		
	}
	
	/**
	 * is employee exists
	 */
	public List<LegalOpinionRequestDataModel> searchLegalOpinionRequests(final LegalOpinionsSearchModel legalOpinionsSearchModel,String requestedBy) { 
		LOG.info(" IN SERVICE << searchLegalOpinionRequests ===========>> "+requestedBy);
		EmployeeEntity employeeEntity = employeeService.getEmployeeById(requestedBy);
		List<LegalOpinionRequestEntity> lstLegalOpinionRequestEntity = null;
		
		
		List<LegalOpinionRequestDataModel> lstLegalOpinionRequestDataModel = new ArrayList();
		LOG.info(" IN SERVICE << searchLegalOpinionRequests >> "+requestedBy+":::::::::::::::: "+employeeEntity.getRoleId());
		
		if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.LAW_OFFICER_ROLEID))){
			lstLegalOpinionRequestEntity = legalOpinionRequestRepository.findAllRequests();
		}else if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADMIN_ROLEID))){
			lstLegalOpinionRequestEntity =legalOpinionRequestRepository.findAllRequests();
		}else if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADVOCATE_ROLEID))){
			LOG.info(" REQUESTED BY IS "+requestedBy);
			lstLegalOpinionRequestEntity = legalOpinionRequestRepository.findByAssignedTo(requestedBy);
		}else if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.HOD_ROLEID))){
			lstLegalOpinionRequestEntity = legalOpinionRequestRepository.findByRequestedBy(requestedBy);
		}
		
		
		if(lstLegalOpinionRequestEntity!=null) {
			
			for(LegalOpinionRequestEntity legalOpinionRequestEntity :lstLegalOpinionRequestEntity) {
				lstLegalOpinionRequestDataModel.add (transformEntityToDataModel(legalOpinionRequestEntity,requestedBy));
			}
		}
		
		return  lstLegalOpinionRequestDataModel==null?null:lstLegalOpinionRequestDataModel;
	}
	
	/**
	 * is employee exists
	 */
	public List<LegalOpinionRequestEntity> searchLegalOpinionRequestsReqBy(LegalOpinionsSearchModel legalOpinionsSearchModel,String requestedBy) { 
		 return  legalOpinionRequestRepository.findByRequestedBy(legalOpinionsSearchModel.getUserId());
	}
	/**
	 * is employee exists
	 */
	public LegalOpinionRequestEntity addLegalOpinionRequest(LegalOpinionRequestDataModel legalOpinionRequestDataModel,String requestedBy) throws InvalidRequestException {
		
		LegalOpinionRequestEntity legalOpinionRequestEntity=null;
		List<EmployeeEntity> lstAssignedToEmployeeEntity = employeeService.getEmpByRole(AppConstants.LAW_OFFICER_ROLEID);
		
		if(lstAssignedToEmployeeEntity==null) {
			throw new InvalidRequestException("No Law Officer Found");
		}else if(lstAssignedToEmployeeEntity!=null && lstAssignedToEmployeeEntity.size()<1) {
			throw new InvalidRequestException("No Law Officer Found");
		}
		legalOpinionRequestEntity = transformDataModelToEntity(legalOpinionRequestDataModel,requestedBy);
		legalOpinionRequestEntity.setAssignedTo(""+((EmployeeEntity)lstAssignedToEmployeeEntity.get(0)).getId());
		legalOpinionRequestEntity.setStatus(1);

		ValidateLegalOpinionRequest validateLegalOpinionRequest = new ValidateLegalOpinionRequest();
		validateLegalOpinionRequest.validate(legalOpinionRequestEntity);
		legalOpinionRequestEntity= legalOpinionRequestRepository.save(legalOpinionRequestEntity);
		
		/**
		 * file Upload
		 */
		List<LOpinionCommentEntity> lstLOpinionCommentEntity = legalOpinionRequestDataModel.getLstLOpionionCommentsEntity();
		
		for(LOpinionCommentEntity lOpinionCommentEntity:lstLOpinionCommentEntity) {
			lOpinionCommentService.addComment(lOpinionCommentEntity);
		}
		
		/**
		 * file Upload
		 */
		List<SupportingDocumentsEntity> lstSupportingDocumentsEntity = legalOpinionRequestDataModel.getLstSupportingDocumentsEntity();
		
		for(SupportingDocumentsEntity supportingDocumentsEntity:lstSupportingDocumentsEntity) {
			uploadSupportingDocuments(supportingDocumentsEntity);
		}
		
		/** Adding Transaction History **/
		
		LOpinionTransHistoryEntity lOpinionTransHistoryEntity = new LOpinionTransHistoryEntity();
		lOpinionTransHistoryEntity.setCreated_date(GeneralUtil.getCurrentDate());
		lOpinionTransHistoryEntity.setActivity_type("CREATION");
		lOpinionTransHistoryEntity.setLegal_opinion_request_id(legalOpinionRequestEntity.getId());
		lOpinionTransHistoryEntity.setLopinion_activity("Legal Opinion Requested");
		
		EmployeeEntity employeeEntity = employeeService.getEmployeeById(requestedBy);
		
		lOpinionTransHistoryEntity.setPerformed_by(Integer.parseInt(requestedBy));
		lOpinionTransHistoryEntity.setPerformed_by_role(employeeEntity.getRoleId());
		lOpinionTransactionsHistoryService.addLOpinionTransaction(lOpinionTransHistoryEntity);
		/************************************/
		
		 notificationService.send(""+legalOpinionRequestEntity.getStatus(), legalOpinionRequestEntity.getRequestedBy());
		return legalOpinionRequestEntity;
	}
	
	
	/**
	 * is employee exists
	 */
	public LegalOpinionRequestEntity updateLegalOpinionRequest(LegalOpinionRequestEntity legalOpinionRequestEntity,String requestedBy,LegalOpinionRequestDataModel legalOpinionRequestDataModel) throws InvalidRequestException {
		

		ValidateLegalOpinionRequest validateLegalOpinionRequest = new ValidateLegalOpinionRequest();
		validateLegalOpinionRequest.validate(legalOpinionRequestEntity);
		
		System.out.println(" IN UPDATE   "+legalOpinionRequestEntity);
		legalOpinionRequestEntity= legalOpinionRequestRepository.save(legalOpinionRequestEntity);
		
		/**
		 * file Upload
		 */
		List<LOpinionCommentEntity> lstLOpinionCommentEntity = legalOpinionRequestDataModel.getLstLOpionionCommentsEntity();
		
		for(LOpinionCommentEntity lOpinionCommentEntity:lstLOpinionCommentEntity) {
			lOpinionCommentService.addComment(lOpinionCommentEntity);
		}
		
		/**
		 * file Upload
		 */
		
		
		List<SupportingDocumentsEntity> lstSupportingDocumentsEntity = legalOpinionRequestDataModel.getLstSupportingDocumentsEntity();
		
		for(SupportingDocumentsEntity supportingDocumentsEntity:lstSupportingDocumentsEntity) {
			uploadSupportingDocuments(supportingDocumentsEntity);
		}
		
		/** Adding Transaction History **/
		
		LOpinionTransHistoryEntity lOpinionTransHistoryEntity = new LOpinionTransHistoryEntity();
		lOpinionTransHistoryEntity.setCreated_date(GeneralUtil.getCurrentDate());
		lOpinionTransHistoryEntity.setActivity_type("UPDATED");
		lOpinionTransHistoryEntity.setLegal_opinion_request_id(legalOpinionRequestEntity.getId());
		lOpinionTransHistoryEntity.setLopinion_activity("Legal Opinion Request Updated");
		
		EmployeeEntity employeeEntity = employeeService.getEmployeeById(requestedBy);
		
		lOpinionTransHistoryEntity.setPerformed_by(Integer.parseInt(requestedBy));
		lOpinionTransHistoryEntity.setPerformed_by_role(employeeEntity.getRoleId());
		lOpinionTransactionsHistoryService.addLOpinionTransaction(lOpinionTransHistoryEntity);
		
		
		/************************************/
		
		 notificationService.send("1", legalOpinionRequestEntity.getRequestedBy());
		 
		 
		return legalOpinionRequestEntity;
	}
	
	/**
	 * is employee exists
	 */
	public boolean isIDReqExists(String strId) {
		
		return legalOpinionRequestRepository.findByReqId(strId)==null?false:true;
		
	}
	
	/**
	 * is employee exists
	 */
	public int getMaxCount(String strDeptId) {
		
		int iCount=0;
		List<LegalOpinionRequestEntity>  lstLegalOpinionRequestEntities = legalOpinionRequestRepository.findByDeptId(strDeptId);
		
		if(lstLegalOpinionRequestEntities!=null ) {
			return lstLegalOpinionRequestEntities.size();
		}
		return 0;
		
	}
	
	/**
	 * is employee exists
	 */
	public SupportingDocumentsEntity uploadSupportingDocuments(SupportingDocumentsEntity supportingDocumentsEntity) {
		
		supportingDocumentsEntity= supportingDocumentRepository.save(supportingDocumentsEntity);
		 
		return supportingDocumentsEntity;
	}
	
	/**
	 * is employee exists
	 */
	public List<SupportingDocumentsEntity> getAllSupportingDocuents(String strLegalReqId) {
		
		 
		return supportingDocumentRepository.findByLegalRequestId(strLegalReqId);
	}
	
	/**
	 * is employee exists
	 */
	public SupportingDocumentsEntity getSupportingDocuent(String strLegalReqId) {
		
		 
		return supportingDocumentRepository.findById(Integer.parseInt(strLegalReqId)).get();
	}
	
	/**
	 * is employee exists
	 */
	public void deleteLegalRequest(String strId) {
		
		legalOpinionRequestRepository.deleteById(Integer.parseInt(strId));
	
	}
	
	
	
	/**
	 * is employee exists
	 */
	public List<LegalOpinionRequestEntity> getAllLegalOpinionRequests() {
		
		List<LegalOpinionRequestEntity> lsLegalOpinionResponseEntity= legalOpinionRequestRepository.findAll();
		 
		 
		return lsLegalOpinionResponseEntity;
	}
	
	/**
	 * transform from data model to entity
	 */
	private LegalOpinionRequestEntity transformDataModelToEntity(LegalOpinionRequestDataModel legalOpinionRequestDataModel,String requesterId) {
		
		LegalOpinionRequestEntity legalOpinionRequestEntity = new LegalOpinionRequestEntity();
		
		EmployeeEntity employeeEntity = employeeService.getEmployeeById(requesterId);
		
		LOG.info(" EMP ID IS <<< >>>>>>>>>>>>> "+requesterId);
		legalOpinionRequestEntity.setAdvocateId(legalOpinionRequestDataModel.getAdvocateId());
		legalOpinionRequestEntity.setDeptId(employeeEntity.getDeptId());
		legalOpinionRequestEntity.setExpectedDate(legalOpinionRequestDataModel.getExpectedDate());
		legalOpinionRequestEntity.setPropertyNumber(legalOpinionRequestDataModel.getPropertyNumber());
		legalOpinionRequestEntity.setReqId(buildLOpinionID(employeeEntity.getDeptId()));
		legalOpinionRequestEntity.setRequestDetails(legalOpinionRequestDataModel.getRequestDetails());
		legalOpinionRequestEntity.setRequestedBy(requesterId);
		legalOpinionRequestEntity.setRequestedOn(GeneralUtil.getCurrentDate());
		legalOpinionRequestEntity.setStatus(legalOpinionRequestEntity.getStatus());
		legalOpinionRequestEntity.setSurveyNumber(legalOpinionRequestDataModel.getSurveyNumber());
		
		return legalOpinionRequestEntity;
	}
	
	/**
	 * transform from data model to entity
	 */
	private LegalOpinionRequestDataModel transformEntityToDataModel(LegalOpinionRequestEntity legalOpinionRequestEntity,String requesterId) {
		
		LegalOpinionRequestDataModel legalOpinionRequestDataModel = new LegalOpinionRequestDataModel();
		
	
		
		EmployeeEntity employeeEntity = employeeService.getEmployeeById(requesterId);
		
		legalOpinionRequestDataModel.setAdvocateId(legalOpinionRequestEntity.getAdvocateId());
		legalOpinionRequestDataModel.setDeptId(legalOpinionRequestEntity.getDeptId());
		legalOpinionRequestDataModel.setExpectedDate(legalOpinionRequestEntity.getExpectedDate());
		legalOpinionRequestDataModel.setPropertyNumber(legalOpinionRequestEntity.getPropertyNumber());
		legalOpinionRequestDataModel.setReqId(buildLOpinionID(employeeEntity.getDeptId()));
		legalOpinionRequestDataModel.setRequestDetails(legalOpinionRequestEntity.getRequestDetails());
		legalOpinionRequestDataModel.setRequestedBy(requesterId);
		legalOpinionRequestDataModel.setRequestedOn(legalOpinionRequestEntity.getRequestedOn());
		legalOpinionRequestDataModel.setStatus(legalOpinionRequestEntity.getStatus());
		legalOpinionRequestDataModel.setSurveyNumber(legalOpinionRequestEntity.getSurveyNumber());
		
		
		StatusEntity statusEntity = statusService.getStatusEntity(""+legalOpinionRequestEntity.getStatus());
		
		if(statusEntity!=null) {
			legalOpinionRequestDataModel.setStatusName(""+statusEntity.getName());
		}
		
		EmployeeEntity requestedEmployee = employeeService.getEmployeeById(requesterId);
		
	
		if(requestedEmployee!=null) {
			legalOpinionRequestDataModel.setRequestorName(requestedEmployee.getName());
		}
		
		EmployeeEntity assignedTo = employeeService.getEmployeeById(legalOpinionRequestEntity.getAssignedTo());
		
	
		
		if(assignedTo!=null) {
			legalOpinionRequestDataModel.setAssignedToName(assignedTo.getName());
		}
		
		AdvocatesEntity advocatesEntity = advocatesService.getAdvocatesEntity(legalOpinionRequestEntity.getAdvocateId());
		
		
		
		if(advocatesEntity!=null) {
			legalOpinionRequestDataModel.setAdvocateName(advocatesEntity.getName());
		}
		
		
		DepartmentEntity departmentEntity = departmentsService.getDepartmentDetails(legalOpinionRequestEntity.getAdvocateId());
		
	
		if(departmentEntity!=null) {
			legalOpinionRequestDataModel.setDeptName(departmentEntity.getName());
		}
		
	/**	List<LOpinionTransHistoryEntity> lstTransactionHistory = lOpinionTransactionsHistoryService.getLstLOpinionTransEntity(""+legalOpinionRequestEntity.getId());
		legalOpinionRequestDataModel.setLstLOpinionTransHistoryEntity(lstTransactionHistory);
		
		legalOpinionRequestDataModel.setLstLOpionionCommentsEntity(lOpinionCommentService.getAllRecords(""+legalOpinionRequestEntity.getId()));
		
		legalOpinionRequestDataModel.setLstSupportingDocumentsEntity(legalOpinionService.getAllSupportingDocuents(""+legalOpinionRequestEntity.getId()));
		**/
		
		legalOpinionRequestDataModel.setId(legalOpinionRequestEntity.getId());
		
		return legalOpinionRequestDataModel;
	}
	
	/**
	 * create legal opinion request id
	 */
	private String buildLOpinionID(String strDeptId) {
		int maxCount =0;
		String newRecordID="";
		int len=0;
		Date date = new Date();

        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
       
    	maxCount = legalOpinionService.getMaxCount(strDeptId);
    	newRecordID=""+maxCount;
		
		len = (4-(""+maxCount).length());
		for(int index=0; index<len; index++) {
			newRecordID="0"+newRecordID;
		}
		return ""+strDeptId.toUpperCase()+"/"+getLocalDate.getYear()+"/"+newRecordID;
	}
	
}
