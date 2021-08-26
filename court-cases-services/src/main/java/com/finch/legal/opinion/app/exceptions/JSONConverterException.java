package com.finch.legal.opinion.app.exceptions;


/**
 * 
 * @author finch
 *
 */
public class JSONConverterException extends Exception{

	/** message **/
	private String message;
	
	/** message **/
	private String errorCode;
	
	/**
	 * default constructor
	 */
	public JSONConverterException(String errorMessage,String errorCode) {
		this.message = errorMessage;
		this.errorCode = errorCode;
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

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
