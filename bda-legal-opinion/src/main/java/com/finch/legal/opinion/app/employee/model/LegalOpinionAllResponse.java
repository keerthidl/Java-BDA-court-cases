package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;

/**
 * 
 * @author finch
 *
 */
public class LegalOpinionAllResponse {


	/** status **/
	private String statusCode="200";
	/** status **/
	private String message="Success";
	/** response**/
	private List<LegalOpinionRequestEntity> response= new ArrayList();
	/**error **/
	private String errors="";
	/**
	 * default constructor
	 */
	public LegalOpinionAllResponse() {
		
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the response
	 */
	public List<LegalOpinionRequestEntity> getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(List<LegalOpinionRequestEntity> response) {
		this.response = response;
	}
	/**
	 * @return the errors
	 */
	public String getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
}
