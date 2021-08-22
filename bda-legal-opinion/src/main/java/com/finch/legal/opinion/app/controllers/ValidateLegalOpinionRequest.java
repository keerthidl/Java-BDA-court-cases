package com.finch.legal.opinion.app.controllers;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.identitymanagement.model.ErrorDetails;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.util.GeneralUtil;

/**
 * 
 * @author finch
 *
 */
public class ValidateLegalOpinionRequest {

	/**
	 * validate
	 */
	public List<ErrorDetails> validate(LegalOpinionRequestEntity legalOpenionRequestEntity) {
		
		List<ErrorDetails> lstErrorDetails = new ArrayList();
		ErrorDetails errorDetails = null;
		if(legalOpenionRequestEntity==null) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request Object");
			lstErrorDetails.add(errorDetails);
			
		}else if(legalOpenionRequestEntity.getExpectedDate()==null || legalOpenionRequestEntity.getExpectedDate().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Expected Date, not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getExpectedDate()!=null && legalOpenionRequestEntity.getExpectedDate().trim().length()>12) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Expected Date crossed allowed 12 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getRequestDetails()==null || legalOpenionRequestEntity.getRequestDetails().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request Details not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getRequestDetails()!=null && legalOpenionRequestEntity.getRequestDetails().trim().length()>5000) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request Details Data crossed allowed 5000 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getPropertyNumber()==null || legalOpenionRequestEntity.getPropertyNumber().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request Details Property Number not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getPropertyNumber()!=null && legalOpenionRequestEntity.getPropertyNumber().trim().length()>100) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Property Number Data crossed allowed 100 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getSurveyNumber()==null || legalOpenionRequestEntity.getSurveyNumber().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request Details Survey Number not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getSurveyNumber()!=null && legalOpenionRequestEntity.getSurveyNumber().trim().length()>100) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Survey Number Data crossed allowed 100 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getOpinion()!=null  && legalOpenionRequestEntity.getOpinion().trim().length()>5000) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Opinion Details Data crossed allowed 5000 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getAssignedTo()!=null && legalOpenionRequestEntity.getAssignedTo().trim().length()>100) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Assigned To Data crossed allowed 5000 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getAssignedTo()!=null && legalOpenionRequestEntity.getAssignedTo().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid data, assigned to not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getStatus()!=null && legalOpenionRequestEntity.getStatus().trim().length()>100) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid status Data crossed allowed 100 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getStatus()==null || legalOpenionRequestEntity.getStatus().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid status , status not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getRequestedBy()!=null && legalOpenionRequestEntity.getRequestedBy().trim().length()>100) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Requested By Data crossed allowed 100 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getRequestedBy()==null || legalOpenionRequestEntity.getRequestedBy().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid status , requested by not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getRequestedOn()!=null && legalOpenionRequestEntity.getRequestedOn().trim().length()>12) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Requested On Data crossed allowed 100 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getRequestedOn()==null || legalOpenionRequestEntity.getRequestedOn().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid status , requested on not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getReqId()!=null && legalOpenionRequestEntity.getReqId().trim().length()>50) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request ID Data crossed allowed 50 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getReqId()==null || legalOpenionRequestEntity.getReqId().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid status , requested id not provided");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getDeptId()!=null && legalOpenionRequestEntity.getDeptId().trim().length()>50) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Department ID Data crossed allowed 50 characters");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpenionRequestEntity.getDeptId()==null || legalOpenionRequestEntity.getDeptId().trim().length()<1) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid status , department id not provided");
			lstErrorDetails.add(errorDetails);
		}else if(!GeneralUtil.isFutureDate(legalOpenionRequestEntity.getExpectedDate())) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Expected Date ,please provide a future date");
			lstErrorDetails.add(errorDetails);
		}else if(!GeneralUtil.isPreviousDate(legalOpenionRequestEntity.getRequestedOn())) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Requested Date ,please provide a valid requested date");
			lstErrorDetails.add(errorDetails);
		}
		return lstErrorDetails;
	}
}
