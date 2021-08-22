package com.finch.legal.opinion.app.entities;

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
@Table(name = "lglsupportingdocuments")
public class SupportingDocumentsEntity {

	/** id **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/** request id **/
	@Column(name = "request_id")
	private String legalRequestId;
	
    /** uploaded date **/
	@Column(name = "uploaded_date")
	private String uploadedDate;
	
	/** uploaded by **/
	@Column(name = "uploaded_by")
	private String uploadedBy;
	
	/** document type **/
	@Column(name = "doc_type")
	private String docType;
	
	/** document **/
	/** document type **/
	@Column(name = "doc_data")
	private String documentData;
	
	/** document **/
	/** document type **/
	@Column(name = "doc_name")
	private String documentName;
	
	/** document **/
	/** document type **/
	@Column(name = "doc_filetype")
	private String documentFileType;
	
	/**
	 * default constructor
	 */
	public SupportingDocumentsEntity() {
		
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}



	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	/**
	 * @return the documentFileType
	 */
	public String getDocumentFileType() {
		return documentFileType;
	}

	/**
	 * @param documentFileType the documentFileType to set
	 */
	public void setDocumentFileType(String documentFileType) {
		this.documentFileType = documentFileType;
	}

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
	 * @return the legalRequestId
	 */
	public String getLegalRequestId() {
		return legalRequestId;
	}

	/**
	 * @param legalRequestId the legalRequestId to set
	 */
	public void setLegalRequestId(String legalRequestId) {
		this.legalRequestId = legalRequestId;
	}

	/**
	 * @return the uploadedDate
	 */
	public String getUploadedDate() {
		return uploadedDate;
	}

	/**
	 * @param uploadedDate the uploadedDate to set
	 */
	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
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

	/**
	 * @return the docType
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * @return the documentData
	 */
	public String getDocumentData() {
		return documentData;
	}

	/**
	 * @param documentData the documentData to set
	 */
	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}
}
