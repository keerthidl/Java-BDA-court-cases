package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

/**
 * result
 * @author 91948
 *
 */
public class ReadCaseResult {

	/** list **/
	private List<CourtCaseDetailsModel> result=new ArrayList();
	
	/**
	 * default constructor
	 */
	public ReadCaseResult(){
	
	}

	/**
	 * @return the result
	 */
	public List<CourtCaseDetailsModel> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<CourtCaseDetailsModel> result) {
		this.result = result;
	}
	
	
}
