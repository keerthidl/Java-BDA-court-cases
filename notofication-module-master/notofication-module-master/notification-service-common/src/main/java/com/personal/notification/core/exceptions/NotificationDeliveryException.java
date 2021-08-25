package com.personal.notification.core.exceptions;

/**
 * 
 * @author dvsnk
 *
 */

public class NotificationDeliveryException extends Exception{

	/** Scode **/
	private int code=-1;
	
	/**
	 * default constructor
	 */
	public NotificationDeliveryException(int code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
