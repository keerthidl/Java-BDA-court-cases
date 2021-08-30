package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;
import com.finch.legal.opinion.app.entities.LOpinionTransHistoryEntity;

/**
 * 
 * @author dvsnk
 *
 */
public class LegalOpinionReadTransResponse {

	/** message **/
	private String message="";
	
	/** message **/
	private String statusCode="200";
	
	/** list of advocates **/
	private LOpinionTransHistoryEntity response = new LOpinionTransHistoryEntity();

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
	public LOpinionTransHistoryEntity getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(LOpinionTransHistoryEntity response) {
		this.response = response;
	}
}
