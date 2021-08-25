package com.personal.notifiction.service.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author dvsnk
 *
 */
@Entity
@Table(name="TemplateDetails")
public class MessageFormat {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	/** id **/
	private Long id;
	
	 @Column(name="templateID")
	/** templateID **/
	private String templateID;
	
	@Column(name="subject")
	/** subject **/
	private String subject;
	
	@Column(name="body")
	/** body **/
	private String body;
	
	@Column(name="params")
	/** body **/
	private String keys;
	
	/**
	 * default constructor
	 */
	public MessageFormat() {
		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
