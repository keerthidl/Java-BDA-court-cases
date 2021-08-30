package com.finch.legal.opinion.app.entities;

import java.util.Date;

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
import com.finch.legal.opinion.app.util.GeneralUtil;
import com.finch.legal.opinion.app.util.JSONFormatter;

/**
 * 
 * @author 91948
 *
 */
@Entity
@Table(name = "legal_opinion_transactions_history")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class LOpinionTransHistoryEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	 
	 /** case activity **/
	@Column(name = "lopinion_activity")
    private String lopinion_activity="";
      
    /** case activity **/
	@Column(name = "activity_type")
    private String activity_type="";
    
    /** case activity **/
	@Column(name = "legal_opinion_request_id")
    private int legal_opinion_request_id=0;
    
  
    /** case activity **/
	@Column(name = "created_date")
    private Date created_date;
	
	 /** case activity **/
	@Column(name = "performed_by")
	private int performed_by;
	
	 /** case activity **/
	@Column(name = "performed_by_role")
	private String performed_by_role;
	
	/**
	 * default constructor
	 */
	public LOpinionTransHistoryEntity() {
		
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
	 * @return the lopinion_activity
	 */
	public String getLopinion_activity() {
		return lopinion_activity;
	}

	/**
	 * @param lopinion_activity the lopinion_activity to set
	 */
	public void setLopinion_activity(String lopinion_activity) {
		this.lopinion_activity = lopinion_activity;
	}

	/**
	 * @return the activity_type
	 */
	public String getActivity_type() {
		return activity_type;
	}

	/**
	 * @param activity_type the activity_type to set
	 */
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}

	/**
	 * @return the legal_opinion_request_id
	 */
	public int getLegal_opinion_request_id() {
		return legal_opinion_request_id;
	}

	/**
	 * @param legal_opinion_request_id the legal_opinion_request_id to set
	 */
	public void setLegal_opinion_request_id(int legal_opinion_request_id) {
		this.legal_opinion_request_id = legal_opinion_request_id;
	}

	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	/**
	 * @return the performed_by
	 */
	public int getPerformed_by() {
		return performed_by;
	}

	/**
	 * @param performed_by the performed_by to set
	 */
	public void setPerformed_by(int performed_by) {
		this.performed_by = performed_by;
	}

	/**
	 * @return the performed_by_role
	 */
	public String getPerformed_by_role() {
		return performed_by_role;
	}

	/**
	 * @param performed_by_role the performed_by_role to set
	 */
	public void setPerformed_by_role(String performed_by_role) {
		this.performed_by_role = performed_by_role;
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
		
		LOpinionTransHistoryEntity lOpinionTransHistoryEntity = new LOpinionTransHistoryEntity();
		
		lOpinionTransHistoryEntity.setActivity_type("ADD");
		lOpinionTransHistoryEntity.setCreated_date(GeneralUtil.getCurrentDate());
		lOpinionTransHistoryEntity.setLegal_opinion_request_id(288);
		lOpinionTransHistoryEntity.setPerformed_by(1);
		lOpinionTransHistoryEntity.setPerformed_by_role("admin");
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(lOpinionTransHistoryEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
