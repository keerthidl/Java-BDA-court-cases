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
@Table(name = "schedule")
public class ScheduleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** type **/
	private int id;

	@Column(name = "type")
	/** type **/
	private String type="";
	
	@Column(name = "site_no")
	/** type **/
	private String site_no="";
	
	@Column(name = "survey_no")
	/** type **/
	private String survey_no="";
	
	@Column(name = "extent")
	/** type **/
	private String extent="";
	@Column(name = "north")
	/** type **/
	private String north="";
	@Column(name = "south")
	/** type **/
	private String south="";
	@Column(name = "east")
	/** type **/
	private String east="";
	@Column(name = "west")
	/** type **/
	private String west="";
	
	@Column(name = "case_no")
	private String case_no="";
	
	@Column(name = "CASE_MAIN_ID")
	private String case_main_id="1";
    	
	/**
	 * default constructor
	 */
	public ScheduleEntity() {
		
	}

	
	/**
	 * @return the case_main_id
	 */
	public String getCase_main_id() {
		return case_main_id;
	}


	/**
	 * @param case_main_id the case_main_id to set
	 */
	public void setCase_main_id(String case_main_id) {
		this.case_main_id = case_main_id;
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
	 * @return the site_no
	 */
	public String getSite_no() {
		return site_no;
	}

	/**
	 * @param site_no the site_no to set
	 */
	public void setSite_no(String site_no) {
		this.site_no = site_no;
	}

	/**
	 * @return the survey_no
	 */
	public String getSurvey_no() {
		return survey_no;
	}

	/**
	 * @param survey_no the survey_no to set
	 */
	public void setSurvey_no(String survey_no) {
		this.survey_no = survey_no;
	}

	/**
	 * @return the extent
	 */
	public String getExtent() {
		return extent;
	}

	/**
	 * @param extent the extent to set
	 */
	public void setExtent(String extent) {
		this.extent = extent;
	}

	/**
	 * @return the north
	 */
	public String getNorth() {
		return north;
	}

	/**
	 * @param north the north to set
	 */
	public void setNorth(String north) {
		this.north = north;
	}

	/**
	 * @return the south
	 */
	public String getSouth() {
		return south;
	}

	/**
	 * @param south the south to set
	 */
	public void setSouth(String south) {
		this.south = south;
	}

	/**
	 * @return the east
	 */
	public String getEast() {
		return east;
	}

	/**
	 * @param east the east to set
	 */
	public void setEast(String east) {
		this.east = east;
	}

	/**
	 * @return the west
	 */
	public String getWest() {
		return west;
	}

	/**
	 * @param west the west to set
	 */
	public void setWest(String west) {
		this.west = west;
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
}
