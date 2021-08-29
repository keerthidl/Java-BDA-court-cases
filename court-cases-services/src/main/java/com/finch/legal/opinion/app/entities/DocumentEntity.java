package com.finch.legal.opinion.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author 91948
 *
 */

@Entity
@Table(name = "case_file_upload")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
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
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		
		DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setCase_main_id(23);
		documentEntity.setFile("LEGAL OPINION");
		documentEntity.setFile_name("Great");
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(documentEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
