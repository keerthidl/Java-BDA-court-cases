package com.personal.notification.sms.data.model;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author dvsnk
 *
 */
public class TemplateBasedSMSMessageModel {

	
	/** Message Properties **/
	private HashMap hashMapMessageProperties=new HashMap();
	
	/** to addresses **/
	private List<String> lstToAddresses;
	
	
	/**
	 * default constructor
	 */
	public TemplateBasedSMSMessageModel() {
		
	}
	
	/**
	 * @return the hashMapMessageProperties
	 */
	public HashMap getHashMapMessageProperties() {
		return hashMapMessageProperties;
	}


	/**
	 * @param hashMapMessageProperties the hashMapMessageProperties to set
	 */
	public void setHashMapMessageProperties(HashMap hashMapMessageProperties) {
		this.hashMapMessageProperties = hashMapMessageProperties;
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
