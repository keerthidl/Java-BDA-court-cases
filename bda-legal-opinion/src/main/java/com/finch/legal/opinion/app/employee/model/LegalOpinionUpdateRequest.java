package com.finch.legal.opinion.app.employee.model;

/**
 * 
 * @author finch
 *
 */
public class LegalOpinionUpdateRequest {
	
	
	/** opinion details **/
	private String opinionDetails="";
	
	/** request Details **/
	private String requestDetails="";
	
	/** expected date **/
	private String expectedDate="";
	
	/** survey Number **/
	private String surveyNumber="";
	
	/** property number **/
	private String propertyNumber="";
	
	/** property number **/
	private String status="";
	
	/** AssignedTo() **/
	private String assignedTo="";

	/**
	 * default Request
	 */
	public LegalOpinionUpdateRequest() {
		
	}

	/**
	 * @return the opinionDetails
	 */
	public String getOpinionDetails() {
		return opinionDetails;
	}

	/**
	 * @param opinionDetails the opinionDetails to set
	 */
	public void setOpinionDetails(String opinionDetails) {
		this.opinionDetails = opinionDetails;
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
