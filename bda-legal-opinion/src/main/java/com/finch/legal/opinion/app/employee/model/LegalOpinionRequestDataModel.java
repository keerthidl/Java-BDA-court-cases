package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;
import com.finch.legal.opinion.app.entities.LOpinionTransHistoryEntity;
import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.util.GeneralUtil;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author 91948
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LegalOpinionRequestDataModel {
	
	
	/** id **/
	private int id;
	
	
	/** employee id **/
	private String reqId;
	
	
	/** employee id **/
	private String deptId;
	
	
	/** name **/
	private String surveyNumber ;
	
	
	/** name **/
	private String propertyNumber ;
	
	
	
	/** name **/
	private String requestedBy ;
	
	
	/** name **/
	private String requestDetails ;
	
	
	/** name **/
	private Date requestedOn ;
	
	
	/** name **/
	private int status ;
	
	
	/** name **/
	private String opinion ;
	
	
	/** name **/
	private  Date expectedDate ;
	
	
	/** name **/
	private String advocateId ;
	
	/** name **/
	private String advocateName ;
	
	/** name **/
	private String requestorName ;
	
	/** name **/
	private String deptName ;
	
	/** name **/
	private String statusName ;
	
	/** List file uploads **/
	private List<SupportingDocumentsEntity> lstSupportingDocumentsEntity = new ArrayList();
	
	/** List file uploads **/
	private List<LOpinionCommentEntity> lstLOpionionCommentsEntity = new ArrayList();
	
	/** List file uploads **/
	private List<LOpinionTransHistoryEntity> lstLOpinionTransHistoryEntity = new ArrayList();
	
	
	/**
	 * default constructor
	 */
	
	public LegalOpinionRequestDataModel() {
		
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the reqId
	 */
	public String getReqId() {
		return reqId;
	}


	/**
	 * @param reqId the reqId to set
	 */
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}


	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}


	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	/**
	 * @return the surveyNumber
	 */
	public String getSurveyNumber() {
		return surveyNumber;
	}


	/**
	 * @param surveyNumber the surveyNumber to set
	 */
	public void setSurveyNumber(String surveyNumber) {
		this.surveyNumber = surveyNumber;
	}


	/**
	 * @return the propertyNumber
	 */
	public String getPropertyNumber() {
		return propertyNumber;
	}


	/**
	 * @param propertyNumber the propertyNumber to set
	 */
	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}


	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}


	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}


	/**
	 * @return the requestDetails
	 */
	public String getRequestDetails() {
		return requestDetails;
	}


	/**
	 * @param requestDetails the requestDetails to set
	 */
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}


	/**
	 * @return the requestedOn
	 */
	public Date getRequestedOn() {
		return requestedOn;
	}


	/**
	 * @param requestedOn the requestedOn to set
	 */
	public void setRequestedOn(Date requestedOn) {
		this.requestedOn = requestedOn;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	/**
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}


	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	/**
	 * @return the expectedDate
	 */
	public Date getExpectedDate() {
		return expectedDate;
	}


	/**
	 * @param expectedDate the expectedDate to set
	 */
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}


	/**
	 * @return the advocateId
	 */
	public String getAdvocateId() {
		return advocateId;
	}


	/**
	 * @param advocateId the advocateId to set
	 */
	public void setAdvocateId(String advocateId) {
		this.advocateId = advocateId;
	}


	/**
	 * @return the advocateName
	 */
	public String getAdvocateName() {
		return advocateName;
	}


	/**
	 * @param advocateName the advocateName to set
	 */
	public void setAdvocateName(String advocateName) {
		this.advocateName = advocateName;
	}


	/**
	 * @return the requestorName
	 */
	public String getRequestorName() {
		return requestorName;
	}


	/**
	 * @param requestorName the requestorName to set
	 */
	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}


	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}


	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}


	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	/**
	 * @return the lstSupportingDocumentsEntity
	 */
	public List<SupportingDocumentsEntity> getLstSupportingDocumentsEntity() {
		return lstSupportingDocumentsEntity;
	}


	/**
	 * @param lstSupportingDocumentsEntity the lstSupportingDocumentsEntity to set
	 */
	public void setLstSupportingDocumentsEntity(List<SupportingDocumentsEntity> lstSupportingDocumentsEntity) {
		this.lstSupportingDocumentsEntity = lstSupportingDocumentsEntity;
	}


	/**
	 * @return the lstLOpionionCommentsEntity
	 */
	public List<LOpinionCommentEntity> getLstLOpionionCommentsEntity() {
		return lstLOpionionCommentsEntity;
	}


	/**
	 * @param lstLOpionionCommentsEntity the lstLOpionionCommentsEntity to set
	 */
	public void setLstLOpionionCommentsEntity(List<LOpinionCommentEntity> lstLOpionionCommentsEntity) {
		this.lstLOpionionCommentsEntity = lstLOpionionCommentsEntity;
	}


	/**
	 * @return the lstLOpinionTransHistoryEntity
	 */
	public List<LOpinionTransHistoryEntity> getLstLOpinionTransHistoryEntity() {
		return lstLOpinionTransHistoryEntity;
	}


	/**
	 * @param lstLOpinionTransHistoryEntity the lstLOpinionTransHistoryEntity to set
	 */
	public void setLstLOpinionTransHistoryEntity(List<LOpinionTransHistoryEntity> lstLOpinionTransHistoryEntity) {
		this.lstLOpinionTransHistoryEntity = lstLOpinionTransHistoryEntity;
	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		
		List<SupportingDocumentsEntity> lstSupportingDocumentsEntity = new ArrayList();
		LegalOpinionRequestDataModel legalOpinionRequestDataModel = new LegalOpinionRequestDataModel();
		
		legalOpinionRequestDataModel.setAdvocateId("1");
		legalOpinionRequestDataModel.setDeptId("1");
		legalOpinionRequestDataModel.setSurveyNumber("SURYYY100");
		legalOpinionRequestDataModel.setPropertyNumber("123333");
		legalOpinionRequestDataModel.setExpectedDate(GeneralUtil.getCurrentDate());
		legalOpinionRequestDataModel.setRequestedBy("2");
		legalOpinionRequestDataModel.setRequestDetails("TESTING");
		
		
		SupportingDocumentsEntity supportingDocumentsEntity = new SupportingDocumentsEntity();
		supportingDocumentsEntity.setDocType("PDF");
		supportingDocumentsEntity.setDocumentData("BASE^$ENCODED ATA");
		supportingDocumentsEntity.setDocumentName("LEGAL");
		supportingDocumentsEntity.setLegalRequestId("288");
		supportingDocumentsEntity.setUploadedBy("1");
		lstSupportingDocumentsEntity.add(supportingDocumentsEntity);
		
		legalOpinionRequestDataModel.setLstSupportingDocumentsEntity(lstSupportingDocumentsEntity);
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(legalOpinionRequestDataModel));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
