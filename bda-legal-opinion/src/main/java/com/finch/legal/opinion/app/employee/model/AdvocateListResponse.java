package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;

/**
 * 
 * @author dvsnk
 *
 */
public class AdvocateListResponse {

	/** message **/
	private String message="";
	
	/** message **/
	private String statusCode="200";
	
	/** list of advocates **/
	private List<RegisteredAdvocatesModel> response = new ArrayList();

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
	 * @return the response
	 */
	public List<RegisteredAdvocatesModel> getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(List<RegisteredAdvocatesModel> response) {
		this.response = response;
	}
}
