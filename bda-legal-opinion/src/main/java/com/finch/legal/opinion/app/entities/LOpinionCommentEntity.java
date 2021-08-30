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
@Table(name = "legal_opinion_comments")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LOpinionCommentEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	
    
    /** case activity **/
	@Column(name = "comment")
    private String comment="";
	
	 /** case activity **/
	@Column(name = "type")
	private String type="";
    
    /** case activity **/
	@Column(name = "recorded_on")
    private String recorded_on="";
    
    /** case activity **/
	@Column(name = "commented_by")
    private String commented_by="";
    
    /** case activity **/
	@Column(name = "legal_opinion_req_id")
    private int   legal_opinion_req_id=0;
    
  
	
	/**
	 * default constructor
	 */
	public LOpinionCommentEntity() {
		
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}



	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * @return the recorded_on
	 */
	public String getRecorded_on() {
		return recorded_on;
	}



	/**
	 * @param recorded_on the recorded_on to set
	 */
	public void setRecorded_on(String recorded_on) {
		this.recorded_on = recorded_on;
	}



	/**
	 * @return the commented_by
	 */
	public String getCommented_by() {
		return commented_by;
	}



	/**
	 * @param commented_by the commented_by to set
	 */
	public void setCommented_by(String commented_by) {
		this.commented_by = commented_by;
	}



	/**
	 * @return the legal_opinion_req_id
	 */
	public int getLegal_opinion_req_id() {
		return legal_opinion_req_id;
	}



	/**
	 * @param legal_opinion_req_id the legal_opinion_req_id to set
	 */
	public void setLegal_opinion_req_id(int legal_opinion_req_id) {
		this.legal_opinion_req_id = legal_opinion_req_id;
	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		
		LOpinionCommentEntity lOpinionCommentEntity = new LOpinionCommentEntity();
		
		lOpinionCommentEntity.setComment("TESING");
		lOpinionCommentEntity.setCommented_by("1");
		lOpinionCommentEntity.setLegal_opinion_req_id(288);
		lOpinionCommentEntity.setType("ADD");
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(lOpinionCommentEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
