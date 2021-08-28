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
 *
 *
 *
 * "case_id": 0, ------> integer
		  "from_dept_id": 0, ------> integer
		  "to_dept_id": 0, --------> integer
		  "file_move_date": "string", -----> date type 
		  "comments": "" -----> string 
 */
@Entity
@Table(name = "file_movement")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FileMovementEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	
	 /** case activity **/
	@Column(name = "case_main_id")
    private int case_main_id=0;
    
    /** case activity **/
	@Column(name = "from_dept_id")
    private String from_dept_id="";
    
    /** case activity **/
	@Column(name = "to_dept_id")
    private String to_dept_id="";
    
    /** case activity **/
	@Column(name = "file_move_date")
    private String file_move_date="";
	
	 /** case activity **/
	 @Column(name = "comments")
	 private String comments="";

    
   
	/**
	 * default constructor
	 */
	public FileMovementEntity() {
		
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
	 * @return the from_dept_id
	 */
	public String getFrom_dept_id() {
		return from_dept_id;
	}


	/**
	 * @param from_dept_id the from_dept_id to set
	 */
	public void setFrom_dept_id(String from_dept_id) {
		this.from_dept_id = from_dept_id;
	}


	/**
	 * @return the to_dept_id
	 */
	public String getTo_dept_id() {
		return to_dept_id;
	}


	/**
	 * @param to_dept_id the to_dept_id to set
	 */
	public void setTo_dept_id(String to_dept_id) {
		this.to_dept_id = to_dept_id;
	}


	/**
	 * @return the file_move_date
	 */
	public String getFile_move_date() {
		return file_move_date;
	}


	/**
	 * @param file_move_date the file_move_date to set
	 */
	public void setFile_move_date(String file_move_date) {
		this.file_move_date = file_move_date;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * main method
	 */
	public static void main(String[] args) {
		FileMovementEntity fileMovementEntity = new FileMovementEntity();
		
		//fileMovementEntity.setCase_id("1");
		fileMovementEntity.setComments("TESTING");
		fileMovementEntity.setFile_move_date("12/12/2021");
		
		fileMovementEntity.setFrom_dept_id("admin");
		fileMovementEntity.setTo_dept_id("law");
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(fileMovementEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
