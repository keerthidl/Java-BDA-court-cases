package com.finch.legal.opinion.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.identitymanagement.model.ErrorDetails;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.LegalOpinionsSearchModel;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.util.GeneralUtil;

/**
 * 
 * @author finch
 *
 */
public class ValidateLegalOpinionSearchRequest {
	
	/**
	 * sorting fields
	 */
	private static HashMap hashMapSortingFields = new HashMap();
	static {
		hashMapSortingFields.put("REQID", "REQID");
		hashMapSortingFields.put("REQDATE", "REQDATE");
		hashMapSortingFields.put("ASSIGNEDTO", "ASSIGNEDTO");
	}

	/**
	 * validate
	 */
	public List<ErrorDetails> validate(LegalOpinionsSearchModel legalOpinionsSearchModel) {
		
		List<ErrorDetails> lstErrorDetails = new ArrayList();
		
		ErrorDetails errorDetails = null;
		if(legalOpinionsSearchModel==null) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Request Object");
			lstErrorDetails.add(errorDetails);
			
		}else if(legalOpinionsSearchModel.getSortingMode()!=null && (!legalOpinionsSearchModel.getSortingMode().equalsIgnoreCase("ASC") && !legalOpinionsSearchModel.getSortingMode().equalsIgnoreCase("DSC"))) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Sorting Mode, allowed ONLY ASC | DSC");
			lstErrorDetails.add(errorDetails);
		}else if(legalOpinionsSearchModel.getSortField()!=null && !isSortingSupported(legalOpinionsSearchModel.getSortField())) {
			errorDetails = new ErrorDetails();
			errorDetails.setCode(AppConstants.INVALID_DATA_VALUE_ERROR_CODE);
			errorDetails.setMessage("Invalid Sorting Field");
			lstErrorDetails.add(errorDetails);
		}
		return lstErrorDetails;
	}
	
	/**
	 * supported sorting fields
	 */
	private boolean isSortingSupported(String supportingField) {
		return hashMapSortingFields.containsKey(supportingField);
	}
}
