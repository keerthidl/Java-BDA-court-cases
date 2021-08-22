package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;

/**
 * 
 * @author dvsnk
 *
 */
public class SupportingDocumentsResponse {

	
	/**
	 * message 
	 */
	private String message="";
	
	/** list **/
	private List<SupportingDocumentsEntity> lstSupportingDocumentsEntity=new ArrayList<SupportingDocumentsEntity>();
	
	/**
	 * default constructor
	 */
	public SupportingDocumentsResponse() {
		
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
	
}
