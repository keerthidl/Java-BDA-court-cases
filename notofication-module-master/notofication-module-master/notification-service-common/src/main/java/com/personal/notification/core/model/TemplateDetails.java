package com.personal.notification.core.model;

/**
 * 
 * @author dvsnk
 *
 */
public class TemplateDetails {
	
	/** id **/
	private String id;
	
	/** templateID **/
	private String templateID;
	
	/** subject **/
	private String subject;
	
	/** body **/
	private String body;
	/** body **/
	private String keys;
	
	/**
	 * default constructor
	 */
	public TemplateDetails() {
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the templateID
	 */
	public String getTemplateID() {
		return templateID;
	}

	/**
	 * @param templateID the templateID to set
	 */
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
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
	 * @return the keys
	 */
	public String getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(String keys) {
		this.keys = keys;
	}
}
