package com.finch.legal.opinion.app.employee.model;

/**
 * error details
 * @author 91948
 *
 */
public class ErrorDetails {

	
	/** code **/
	private String code="";
	
	/** message **/
	private String message="";
	
	/**
	 * default constructor
	 */
	public ErrorDetails() {
		
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
