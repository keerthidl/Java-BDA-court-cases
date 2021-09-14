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
	@Column(name = "prayer")
    private String prayer="";
	
	 /** case activity **/
	@Column(name = "advocate_id")
	private String advocate_id="";
			
	/** case activity **/
	@Column(name = "court_id")
	private String court_id="";
	
	 /** case activity **/
	@Column(name = "remark")
	private String remark="";
				

	
	 /** case activity **/
	@Column(name = "other_respondants")
	private String other_respondants="";
					
	/** case activity **/
	@Column(name = "order_type")
	private String order_type="";

	
	/** case activity **/
	@Column(name = "appeal_created_date")
	private String appeal_created_date="";
	
	
	/** case activity **/
	@Column(name = "opponent_advocate_id_number")
	private String opponent_advocate_id_number;
	
	
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
	 * @return the prayer
	 */
	public String getPrayer() {
		return prayer;
	}


	/**
	 * @param prayer the prayer to set
	 */
	public void setPrayer(String prayer) {
		this.prayer = prayer;
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
	 * @return the court_id
	 */
	public String getCourt_id() {
		return court_id;
	}


	/**
	 * @param court_id the court_id to set
	 */
	public void setCourt_id(String court_id) {
		this.court_id = court_id;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * @return the other_respondants
	 */
	public String getOther_respondants() {
		return other_respondants;
	}


	/**
	 * @param other_respondants the other_respondants to set
	 */
	public void setOther_respondants(String other_respondants) {
		this.other_respondants = other_respondants;
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
	 * @return the appeal_created_date
	 */
	public String getAppeal_created_date() {
		return appeal_created_date;
	}


	/**
	 * @param appeal_created_date the appeal_created_date to set
	 */
	public void setAppeal_created_date(String appeal_created_date) {
		this.appeal_created_date = appeal_created_date;
	}


	/**
	 * @return the opponent_advocate_id_number
	 */
	public String getOpponent_advocate_id_number() {
		return opponent_advocate_id_number;
	}


	/**
	 * @param opponent_advocate_id_number the opponent_advocate_id_number to set
	 */
	public void setOpponent_advocate_id_number(String opponent_advocate_id_number) {
		this.opponent_advocate_id_number = opponent_advocate_id_number;
	}
}
