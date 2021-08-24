package com.personal.notification.sms.data.model;

import java.util.List;

/**
 * 
 * @author dvsnk
 *
 */
public class SMSMessageModel {

	/** subject **/
	private String body;
	
	/** to addresses **/
	private List<String> lstToAddresses;
	
	
	/**
	 * default constructor
	 */
	public SMSMessageModel() {
		
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
}
