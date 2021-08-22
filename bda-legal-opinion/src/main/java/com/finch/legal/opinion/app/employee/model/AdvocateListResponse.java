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
	
	/** list of advocates **/
	private List<AdvocatesEntity> lstAdvocateEntities = new ArrayList();

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
	 * @return the lstAdvocateEntities
	 */
	public List<AdvocatesEntity> getLstAdvocateEntities() {
		return lstAdvocateEntities;
	}

	/**
	 * @param lstAdvocateEntities the lstAdvocateEntities to set
	 */
	public void setLstAdvocateEntities(List<AdvocatesEntity> lstAdvocateEntities) {
		this.lstAdvocateEntities = lstAdvocateEntities;
	}
}
