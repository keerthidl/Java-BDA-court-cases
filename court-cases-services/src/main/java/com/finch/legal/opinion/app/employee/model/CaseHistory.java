package com.finch.legal.opinion.app.employee.model;


/**
 * 
 * @author finch
 *
 */
public class CaseHistory {

	
	/** id **/
	private int id=0;
	 
	 /** case activity **/
    private String case_activity="";
    
    /** case activity **/
    private String user_dept="";
    
    /** case activity **/
    private String case_details="";
    
    /** case activity **/
    private String case_id="";
    
    /** case activity **/
    private int case_main_id=0;
    
    /** case activity **/
    private String created_date="";
    
    /**
     * default constructor
     */
    public CaseHistory() {
    	
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
	 * @return the case_activity
	 */
	public String getCase_activity() {
		return case_activity;
	}

	/**
	 * @param case_activity the case_activity to set
	 */
	public void setCase_activity(String case_activity) {
		this.case_activity = case_activity;
	}

	/**
	 * @return the user_dept
	 */
	public String getUser_dept() {
		return user_dept;
	}

	/**
	 * @param user_dept the user_dept to set
	 */
	public void setUser_dept(String user_dept) {
		this.user_dept = user_dept;
	}

	/**
	 * @return the case_details
	 */
	public String getCase_details() {
		return case_details;
	}

	/**
	 * @param case_details the case_details to set
	 */
	public void setCase_details(String case_details) {
		this.case_details = case_details;
	}

	/**
	 * @return the case_id
	 */
	public String getCase_id() {
		return case_id;
	}

	/**
	 * @param case_id the case_id to set
	 */
	public void setCase_id(String case_id) {
		this.case_id = case_id;
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
	 * @return the created_date
	 */
	public String getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
}
