package com.finch.legal.opinion.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 91948
 *
 */

@Entity
@Table(name = "case_file_upload")
public class DocumentEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	
 /** case activity **/
	@Column(name = "file")
	private String file="";
		
	/** case activity **/
	@Column(name = "file_name")
	 private String file_name="";
    
    /** case activity **/
	@Column(name = "case_main_id")
    private int case_main_id=0;
    
   
	
	/**
	 * default constructor
	 */
	public DocumentEntity() {
		
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
	 * @return the file
	 */
	public String getFile() {
		return file;
	}



	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}



	/**
	 * @return the file_name
	 */
	public String getFile_name() {
		return file_name;
	}



	/**
	 * @param file_name the file_name to set
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}



	/**
	 * @return the case_main_id
	 */
	public int getCase_main_id() {
		return case_main_id;
	}



	/**
	 * @param case_main_id the case_main_id to set
	 */
	public void setCase_main_id(int case_main_id) {
		this.case_main_id = case_main_id;
	}
}
