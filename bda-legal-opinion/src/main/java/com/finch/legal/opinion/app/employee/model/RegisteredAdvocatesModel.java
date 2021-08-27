package com.finch.legal.opinion.app.employee.model;

/**
 * 
 * @author 91948
 *
 */
public class RegisteredAdvocatesModel {

	
	/** empid **/
	private String empId="";
	
	/** name **/
	private String name="";
	
	/**
	 * RegisteredAdvocatesModel
	 */
	public RegisteredAdvocatesModel() {
		
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
