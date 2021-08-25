package com.personal.notification.core.model;

import java.util.HashMap;

/**
 * 
 * @author dvsnk
 *
 */
public class NotificationRequestModel {

	/** HashMap properties **/
	private HashMap hashMapMesageProperties=new HashMap();
	
	/** message content **/
	private String message="";
	
	/**
	 * default constructor
	 */
	public NotificationRequestModel() {
		
	}

	/**
	 * @return the hashMapMesageProperties
	 */
	public HashMap getHashMapMesageProperties() {
		return hashMapMesageProperties;
	}

	/**
	 * @param hashMapMesageProperties the hashMapMesageProperties to set
	 */
	public void setHashMapMesageProperties(HashMap hashMapMesageProperties) {
		this.hashMapMesageProperties = hashMapMesageProperties;
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
}
