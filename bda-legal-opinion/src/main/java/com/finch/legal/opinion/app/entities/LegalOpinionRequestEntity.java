package com.finch.legal.opinion.app.entities;


import java.util.Date;

import javax.persistence.Column;
/**
 * department entity
 * @author finch
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "legal_opinion_requests")
public class LegalOpinionRequestEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "request_id")
	/** employee id **/
	private String reqId;
	
	@Column(name = "requested_dept_id")
	/** employee id **/
	private String deptId;
	
	@Column(name = "survey_number")
	/** name **/
	private String surveyNumber ;
	
	@Column(name = "property_number")
	/** name **/
	private String propertyNumber ;
	
	
	@Column(name = "requestor_user_id")
	/** name **/
	private String requestedBy ;
	
	@Column(name = "request_details_text")
	/** name **/
	private String requestDetails ;
	
	@Column(name = "requested_on")
	/** name **/
	private Date requestedOn ;
	
	@Column(name = "status")
	/** name **/
	private int status ;
	
	@Column(name = "legal_opinion_text")
	/** name **/
	private String opinion ;
	
	@Column(name = "legal_opinion_expected_date")
	/** name **/
	private  Date expectedDate ;
	
	@Column(name = "request_assigned_advocate_id")
	/** name **/
	private String advocateId ;
	
	@Column(name = "request_assigned_to")
	/** name **/
	private String assignedTo ;
	
	
	/**
	 * default constructor
	 */
	public LegalOpinionRequestEntity() {
		
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
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}


	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
}
