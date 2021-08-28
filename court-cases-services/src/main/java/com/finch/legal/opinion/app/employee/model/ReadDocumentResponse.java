package com.finch.legal.opinion.app.employee.model;

import java.util.List;

import com.finch.legal.opinion.app.entities.CommentEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;

/**
 * add case
 * @author 91948
 *
 */
public class ReadDocumentResponse {

	/** status **/
	private String status="200";
	
	/** status **/
	private String message="";
	
	/** result **/
	private DocumentEntity result=new DocumentEntity();
    	
    	/**
    	 * default constructor
    	 */
    public ReadDocumentResponse() {
    	
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
		public DocumentEntity getResult() {
			return result;
		}



		/**
		 * @param result the result to set
		 */
		public void setResult(DocumentEntity result) {
			this.result = result;
		}

  
}
