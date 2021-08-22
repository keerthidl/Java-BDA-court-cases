package com.finch.legal.opinion.app.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author finch
 *
 */
@Entity
@Table(name = "legalopinionresponse")
public class LegalOpinionResponseEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "request_id")
	/** employee id **/
	private String reqId;
	
	@Column(name = "uploaded_time")
	/** name **/
	private Date uploadedTime ;
	
	@Column(name = "file_type")
	/** name **/
	private String fileType ;
	
	@Column(name = "remarks")
	/** name **/
	private String remarks ;
	
	@Column(name = "uploaded_by")
	/** name **/
	private String uploadedBy ;

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
	 * @return the reqId
	 */
	public String getReqId() {
		return reqId;
	}

	/**
	 * @param reqId the reqId to set
	 */
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	/**
	 * @return the uploadedTime
	 */
	public Date getUploadedTime() {
		return uploadedTime;
	}

	/**
	 * @param uploadedTime the uploadedTime to set
	 */
	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the uploadedBy
	 */
	public String getUploadedBy() {
		return uploadedBy;
	}

	/**
	 * @param uploadedBy the uploadedBy to set
	 */
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	
}
