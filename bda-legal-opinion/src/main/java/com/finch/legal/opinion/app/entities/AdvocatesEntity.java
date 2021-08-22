package com.finch.legal.opinion.app.entities;

import javax.persistence.Column;
/**
 * department entity
 * @author finch
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advocates")
public class AdvocatesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "ADVOCATE_NAME")
	/** employee id **/
	private String name;
	@Column(name = "ADVOCATE_EMAIL")
	/** employee id **/
	private String email;
	
	@Column(name = "ADVOCATE_MOBILE")
	/** employee id **/
	private String mobile;
	

	@Column(name = "ADVOCATE_WHATSAPP")
	/** employee id **/
	private String whatsApp;
	 
	@Column(name = "ADVOCATE_QUALIFICATION")
	/** employee id **/
	private String qualification;
	@Column(name = "ADVOCATE_ID_NUMBER")
	/** employee id **/
	private String idNumber;
	 
	@Column(name = "ADVOCATE_ID")
	/** employee id **/
	private String advocateId;
	@Column(name = "ADVOCATE_ADDRESS")
	/** employee id **/
	private String address;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the whatsApp
	 */
	public String getWhatsApp() {
		return whatsApp;
	}
	/**
	 * @param whatsApp the whatsApp to set
	 */
	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}
	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}
	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * @return the advocateId
	 */
	public String getAdvocateId() {
		return advocateId;
	}
	/**
	 * @param advocateId the advocateId to set
	 */
	public void setAdvocateId(String advocateId) {
		this.advocateId = advocateId;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}	 
}

