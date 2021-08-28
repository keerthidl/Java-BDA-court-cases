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
@Table(name = "case_contempt")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContemptEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id=0;
	
	 /** case activity **/
	@Column(name = "contempt_no")
    private String contempt_no="";
    
    /** case activity **/
	@Column(name = "contempt_details")
    private String contempt_details="";
    
    /** case activity **/
	@Column(name = "contempt_date")
    private String contempt_date="";
    
    /** case activity **/
	@Column(name = "contempt_year")
    private String contempt_year="";
    
    /** case activity **/
	@Column(name = "case_main_id")
    private int case_main_id=0;
    
    /** case activity **/
	@Column(name = "advocate_name")
    private String advocate_name="";
	
	/**
	 * default constructor
	 */
	public ContemptEntity() {
		
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
	 * @return the contempt_details
	 */
	public String getContempt_details() {
		return contempt_details;
	}

	/**
	 * @param contempt_details the contempt_details to set
	 */
	public void setContempt_details(String contempt_details) {
		this.contempt_details = contempt_details;
	}

	/**
	 * @return the contempt_date
	 */
	public String getContempt_date() {
		return contempt_date;
	}

	/**
	 * @param contempt_date the contempt_date to set
	 */
	public void setContempt_date(String contempt_date) {
		this.contempt_date = contempt_date;
	}

	/**
	 * @return the contempt_year
	 */
	public String getContempt_year() {
		return contempt_year;
	}

	/**
	 * @param contempt_year the contempt_year to set
	 */
	public void setContempt_year(String contempt_year) {
		this.contempt_year = contempt_year;
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
	 * @return the advocate_name
	 */
	public String getAdvocate_name() {
		return advocate_name;
	}

	/**
	 * @param advocate_name the advocate_name to set
	 */
	public void setAdvocate_name(String advocate_name) {
		this.advocate_name = advocate_name;
	}
	

	/**
	 * main method
	 */
	public static void main(String[] args) {
		ContemptEntity contemptEntity = new ContemptEntity();
		
		contemptEntity.setAdvocate_name("Shiva");
		contemptEntity.setCase_main_id(1);
		contemptEntity.setContempt_date("12/12/20121");
		contemptEntity.setContempt_details("TESTINg CONTEMP SERVICE");
		contemptEntity.setContempt_no("2000");
		contemptEntity.setContempt_year("2023");
		
		try {
			System.out.println(" MSG "+JSONFormatter.buildStringObject(contemptEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
