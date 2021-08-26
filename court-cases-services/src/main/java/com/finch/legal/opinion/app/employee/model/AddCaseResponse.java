package com.finch.legal.opinion.app.employee.model;

import java.util.List;

/**
 * add case
 * @author 91948
 *
 */
public class AddCaseResponse {

	/** status **/
	private boolean status;
	
	/** status **/
	private String message;
	
	/** result **/
	private AddCaseResult result;
    	
    	/**
    	 * default constructor
    	 */
    public AddCaseResponse() {
    	
    }

		/**
		 * @return the status
		 */
		public boolean isStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(boolean status) {
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
		public AddCaseResult getResult() {
			return result;
		}

		/**
		 * @param result the result to set
		 */
		public void setResult(AddCaseResult result) {
			this.result = result;
		}
}
