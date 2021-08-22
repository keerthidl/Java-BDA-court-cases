package com.finch.legal.opinion.app.employee.model;

import java.util.Date;

/**
 * 
 * @author finch
 *
 */
public class LegalOpinionRequestModel {
	
	/** requestor emp id **/
	private String reqEmpId;
	
	/** property id **/
	private String propertyId;
	
	/** survey number **/
	private String surveyNum;
	
	/** advocate id **/
	private String advocateId;
	
	/** created id **/
	private Date createDateTime = null;
	
	
	/**
	 * default constructor
	 */
	public LegalOpinionRequestModel() {
		
	}


	/**
	 * @return the reqEmpId
	 */
	public String getReqEmpId() {
		return reqEmpId;
	}


	/**
	 * @param reqEmpId the reqEmpId to set
	 */
	public void setReqEmpId(String reqEmpId) {
		this.reqEmpId = reqEmpId;
	}


	/**
	 * @return the propertyId
	 */
	public String getPropertyId() {
		return propertyId;
	}


	/**
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}


	/**
	 * @return the surveyNum
	 */
	public String getSurveyNum() {
		return surveyNum;
	}


	/**
	 * @param surveyNum the surveyNum to set
	 */
	public void setSurveyNum(String surveyNum) {
		this.surveyNum = surveyNum;
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
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}


	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	
}
