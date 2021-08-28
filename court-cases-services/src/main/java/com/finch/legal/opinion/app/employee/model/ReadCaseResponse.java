package com.finch.legal.opinion.app.employee.model;

import java.util.List;

/**
 * add case
 * @author 91948
 *
 */
public class ReadCaseResponse {

	/** status **/
	private String status="200";
	
	/** status **/
	private String message="";
	
	/** result **/
	private CourtCaseDetailsModel result=new CourtCaseDetailsModel();
    	
    	/**
    	 * default constructor
    	 */
    public ReadCaseResponse() {
    	
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
		public CourtCaseDetailsModel getResult() {
			return result;
		}

		/**
		 * @param result the result to set
		 */
		public void setResult(CourtCaseDetailsModel result) {
			this.result = result;
		}

		
}
