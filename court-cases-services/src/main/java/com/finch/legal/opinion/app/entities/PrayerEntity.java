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
@Table(name = "courtcase_prayers")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PrayerEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	
	 /** case activity **/
	@Column(name = "section_id")
    private String prayerId="";
    
  
    /** case activity **/
	@Column(name = "case_main_id")
    private int case_main_id=0;
    
  
	
	/**
	 * default constructor
	 */
	public PrayerEntity() {
		
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
	 * @return the prayerId
	 */
	public String getPrayerId() {
		return prayerId;
	}



	/**
	 * @param prayerId the prayerId to set
	 */
	public void setPrayerId(String prayerId) {
		this.prayerId = prayerId;
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
