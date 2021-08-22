package com.finch.legal.opinion.app.employee.model;


/**
 * 
 * @author finch
 *
 */
public class LoginDetails {

	/** login id **/
	private String loginId;
	
	/** password **/
	private String password;
	
	/**
	 * default constructor
	 */
	public LoginDetails() {
		
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
