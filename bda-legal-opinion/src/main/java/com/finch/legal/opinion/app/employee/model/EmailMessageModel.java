package com.finch.legal.opinion.app.employee.model;

import java.util.List;

/**
 * 
 * @author dvsnk
 *
 */
public class EmailMessageModel {

	/** subject **/
	private String subject;
	
	/** subject **/
	private String body;
	
	/** to addresses **/
	private List<String> lstToAddresses;
	
	/** cc email addresses **/
	private List<String> lstCCAddresses;
	
	/** bcc email addresses **/
	private List<String> lstBCCAddresses;
	/**
	 * default constructor
	 */
	public EmailMessageModel() {
		
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the lstToAddresses
	 */
	public List<String> getLstToAddresses() {
		return lstToAddresses;
	}
	/**
	 * @param lstToAddresses the lstToAddresses to set
	 */
	public void setLstToAddresses(List<String> lstToAddresses) {
		this.lstToAddresses = lstToAddresses;
	}
	/**
	 * @return the lstCCAddresses
	 */
	public List<String> getLstCCAddresses() {
		return lstCCAddresses;
	}
	/**
	 * @param lstCCAddresses the lstCCAddresses to set
	 */
	public void setLstCCAddresses(List<String> lstCCAddresses) {
		this.lstCCAddresses = lstCCAddresses;
	}
	/**
	 * @return the lstBCCAddresses
	 */
	public List<String> getLstBCCAddresses() {
		return lstBCCAddresses;
	}
	/**
	 * @param lstBCCAddresses the lstBCCAddresses to set
	 */
	public void setLstBCCAddresses(List<String> lstBCCAddresses) {
		this.lstBCCAddresses = lstBCCAddresses;
	}
}
