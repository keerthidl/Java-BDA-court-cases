package com.finch.legal.opinion.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.finch.legal.opinion.app.exceptions.JSONConverterException;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author 91948
 *
 */
@Entity
@Table(name = "courtcase_coment")
public class CommentEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	
	 /** case activity **/
	@Column(name = "contempt_no")
    private String contempt_no="";
    
    /** case activity **/
	@Column(name = "comment")
    private String comment="";
    
    /** case activity **/
	@Column(name = "recorded_on")
    private String recorded_on="";
    
    /** case activity **/
	@Column(name = "commented_by")
    private String commented_by="";
    
    /** case activity **/
	@Column(name = "case_main_id")
    private int case_main_id=0;
    
  
	
	/**
	 * default constructor
	 */
	public CommentEntity() {
		
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
	 * @return the contempt_no
	 */
	public String getContempt_no() {
		return contempt_no;
	}



	/**
	 * @param contempt_no the contempt_no to set
	 */
	public void setContempt_no(String contempt_no) {
		this.contempt_no = contempt_no;
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
		CommentEntity commentEntity = new CommentEntity();
		
		commentEntity.setCase_main_id(1);
		commentEntity.setComment(" TESTSTSTSTST ");
		commentEntity.setCommented_by("admin");
		commentEntity.setContempt_no("123");
		commentEntity.setRecorded_on("12/12/2021");
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(commentEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
