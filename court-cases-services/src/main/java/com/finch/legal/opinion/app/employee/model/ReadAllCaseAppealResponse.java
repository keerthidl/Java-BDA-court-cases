package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

/**
 * add case
 * @author 91948
 *
 */
public class ReadAllCaseAppealResponse {

	/** status **/
	private String status="200";
	
	/** status **/
	private String message="";
	
	/** result **/
	private List<CourtCaseAppealDetailsModel> result=new ArrayList();
    	
    	/**
    	 * default constructor
    	 */
    public ReadAllCaseAppealResponse() {
    	
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
		 * @return the result
		 */
		public List<CourtCaseAppealDetailsModel> getResult() {
			return result;
		}

		/**
		 * @param result the result to set
		 */
		public void setResult(List<CourtCaseAppealDetailsModel> result) {
			this.result = result;
		}
}
