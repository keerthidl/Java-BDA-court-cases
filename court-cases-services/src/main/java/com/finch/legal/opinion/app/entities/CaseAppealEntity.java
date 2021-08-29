package com.finch.legal.opinion.app.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author 91948
 *
 */
@Entity
@Table(name = "case_comments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseAppealEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	 
	 /** case activity **/
	@Column(name = "appeal_no")
    private String appeal_no="";
	
	 /** case activity **/
	@Column(name = "case_id")
	private int case_id=0;
	
	 /** case activity **/
	@Column(name = "case_no")
	private String case_no="";
	
	 /** case activity **/
	@Column(name = "PETINITIONER_NAME")
	private String petitioner_name=""; 
		
	/** case activity **/
	@Column(name = "sob_field")
    private String sob_field="";
	
	 /** case activity **/
	@Column(name = "advocate_id")
	private String advocate_id="";
			
	/** case activity **/
	@Column(name = "court_name")
	private String court_name="";
	
	 /** case activity **/
	@Column(name = "remarks")
	private String remarks="";
				
	/** case activity **/
	@Column(name = "status")
	private String status="";
	
	 /** case activity **/
	@Column(name = "other_respondents")
	private String other_respondents="";
					
	/** case activity **/
	@Column(name = "order_type")
	private String order_type="";
	
	/** case activity **/
	@Column(name = "judgement_date")
	private Date judgement_date;
	
	/** case activity **/
	@Column(name = "type_of_opponent")
	private String type_of_opponent;
	
	/** case activity **/
	@Column(name = "opponent_name")
	private String opponent_name;
	
	/** case activity **/
	@Column(name = "opponent_advocate_id_name")
	private String opponent_advocate_id_name;
	
	
	/**
	 * default constructor
	 */
	public CaseAppealEntity() {
		
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
	 * @return the appeal_no
	 */
	public String getAppeal_no() {
		return appeal_no;
	}


	/**
	 * @param appeal_no the appeal_no to set
	 */
	public void setAppeal_no(String appeal_no) {
		this.appeal_no = appeal_no;
	}


	/**
	 * @return the case_id
	 */
	public int getCase_id() {
		return case_id;
	}


	/**
	 * @param case_id the case_id to set
	 */
	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}


	/**
	 * @return the case_no
	 */
	public String getCase_no() {
		return case_no;
	}


	/**
	 * @param case_no the case_no to set
	 */
	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}


	/**
	 * @return the petitioner_name
	 */
	public String getPetitioner_name() {
		return petitioner_name;
	}


	/**
	 * @param petitioner_name the petitioner_name to set
	 */
	public void setPetitioner_name(String petitioner_name) {
		this.petitioner_name = petitioner_name;
	}


	/**
	 * @return the sob_field
	 */
	public String getSob_field() {
		return sob_field;
	}


	/**
	 * @param sob_field the sob_field to set
	 */
	public void setSob_field(String sob_field) {
		this.sob_field = sob_field;
	}


	/**
	 * @return the advocate_id
	 */
	public String getAdvocate_id() {
		return advocate_id;
	}


	/**
	 * @param advocate_id the advocate_id to set
	 */
	public void setAdvocate_id(String advocate_id) {
		this.advocate_id = advocate_id;
	}


	/**
	 * @return the court_name
	 */
	public String getCourt_name() {
		return court_name;
	}


	/**
	 * @param court_name the court_name to set
	 */
	public void setCourt_name(String court_name) {
		this.court_name = court_name;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the other_respondents
	 */
	public String getOther_respondents() {
		return other_respondents;
	}


	/**
	 * @param other_respondents the other_respondents to set
	 */
	public void setOther_respondents(String other_respondents) {
		this.other_respondents = other_respondents;
	}


	/**
	 * @return the order_type
	 */
	public String getOrder_type() {
		return order_type;
	}


	/**
	 * @param order_type the order_type to set
	 */
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}


	/**
	 * @return the judgement_date
	 */
	public Date getJudgement_date() {
		return judgement_date;
	}


	/**
	 * @param judgement_date the judgement_date to set
	 */
	public void setJudgement_date(Date judgement_date) {
		this.judgement_date = judgement_date;
	}


	/**
	 * @return the type_of_opponent
	 */
	public String getType_of_opponent() {
		return type_of_opponent;
	}


	/**
	 * @param type_of_opponent the type_of_opponent to set
	 */
	public void setType_of_opponent(String type_of_opponent) {
		this.type_of_opponent = type_of_opponent;
	}


	/**
	 * @return the opponent_name
	 */
	public String getOpponent_name() {
		return opponent_name;
	}


	/**
	 * @param opponent_name the opponent_name to set
	 */
	public void setOpponent_name(String opponent_name) {
		this.opponent_name = opponent_name;
	}


	/**
	 * @return the opponent_advocate_id_name
	 */
	public String getOpponent_advocate_id_name() {
		return opponent_advocate_id_name;
	}


	/**
	 * @param opponent_advocate_id_name the opponent_advocate_id_name to set
	 */
	public void setOpponent_advocate_id_name(String opponent_advocate_id_name) {
		this.opponent_advocate_id_name = opponent_advocate_id_name;
	}
	
}
