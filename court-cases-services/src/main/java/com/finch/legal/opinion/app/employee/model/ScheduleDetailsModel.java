package com.finch.legal.opinion.app.employee.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author 91948
 *
 */
public class ScheduleDetailsModel {

	/** type **/
	private String type="";
	
	
	/** type **/
	private String site_no="";
	
	
	/** type **/
	private String survey_no="";
	

	/** type **/
	private String extent="";
	
	/** type **/
	private String north="";
	
	/** type **/
	private String south="";
	
	/** type **/
	private String east="";
	
	/** type **/
	private String west="";
	
	/**
	 * default constructor
	 */
	public ScheduleDetailsModel() {
		
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
}
