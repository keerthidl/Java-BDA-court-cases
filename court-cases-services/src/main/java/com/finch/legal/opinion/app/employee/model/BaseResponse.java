package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 91948
 *
 */
public class BaseResponse {
	
	
	/** status **/
	private String status="";
	
	/** result **/
	private String result="";
	
	/** errors **/
	private List<ErrorDetails> lstErrorDetails = new ArrayList();
	
	/**
	 * default constructor
	 */
	public BaseResponse() {
		
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the lstErrorDetails
	 */
	public List<ErrorDetails> getLstErrorDetails() {
		return lstErrorDetails;
	}

	/**
	 * @param lstErrorDetails the lstErrorDetails to set
	 */
	public void setLstErrorDetails(List<ErrorDetails> lstErrorDetails) {
		this.lstErrorDetails = lstErrorDetails;
	}
	
}
