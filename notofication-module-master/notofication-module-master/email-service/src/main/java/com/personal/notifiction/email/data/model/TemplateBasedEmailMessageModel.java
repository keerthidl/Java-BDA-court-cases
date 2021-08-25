package com.personal.notifiction.email.data.model;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author dvsnk
 *
 */
public class TemplateBasedEmailMessageModel {

	
	/** Message Properties **/
	private HashMap hashMapMessageProperties=new HashMap();
	
	/** to addresses **/
	private List<String> lstToAddresses;
	
	/** cc email addresses **/
	private List<String> lstCCAddresses;
	
	/** bcc email addresses **/
	private List<String> lstBCCAddresses;
	/**
	 * default constructor
	 */
	public TemplateBasedEmailMessageModel() {
		
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
