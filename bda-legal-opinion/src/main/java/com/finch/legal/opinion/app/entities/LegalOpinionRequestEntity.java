package com.finch.legal.opinion.app.entities;


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
@Table(name = "legalopinions")
public class LegalOpinionRequestEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "req_id")
	/** employee id **/
	private String reqId;
	
	@Column(name = "dept_id")
	/** employee id **/
	private String deptId;
	
	@Column(name = "survey_number")
	/** name **/
	private String surveyNumber ;
	
	@Column(name = "property_number")
	/** name **/
	private String propertyNumber ;
	
	
	@Column(name = "requestor_emp_id")
	/** name **/
	private String requestedBy ;
	
	@Column(name = "request_details")
	/** name **/
	private String requestDetails ;
	
	@Column(name = "date_requested")
	/** name **/
	private String requestedOn ;
	
	@Column(name = "status")
	/** name **/
	private String status ;
	
	@Column(name = "opinion")
	/** name **/
	private String opinion ;
	
	@Column(name = "date_expected")
	/** name **/
	private String expectedDate ;
	
	@Column(name = "advocate_id")
	/** name **/
	private String assignedTo ;
	
	
	/**
	 * default constructor
	 */
	public LegalOpinionRequestEntity() {
		
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
	public String getRequestedOn() {
		return requestedOn;
	}


	/**
	 * @param requestedOn the requestedOn to set
	 */
	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
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
	public String getExpectedDate() {
		return expectedDate;
	}


	/**
	 * @param expectedDate the expectedDate to set
	 */
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
}
