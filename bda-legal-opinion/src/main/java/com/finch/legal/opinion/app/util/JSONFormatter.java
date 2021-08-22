package com.finch.legal.opinion.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;

/**
 * 
 * @author finch
 *
 */
public class JSONFormatter {

	
	/**
	 * convert string to JSON object
	 */
	public static Object buildJSONObject(String strJSON, Class className) throws JSONConverterException{
		
		try {
			return new ObjectMapper().readValue(strJSON, className); 
		}catch(Exception e) {
			throw new JSONConverterException(AppConstants.STRING_TO_JSONOBJ_ERR_CODE, AppConstants.STRING_TO_JSONOBJ_ERR_MSG);
		}
		
	}
	/**
	 * convert JSON to String object
	 */
	public static String buildStringObject(Object strJSON) throws JSONConverterException{
		
		try {
			return new ObjectMapper().writeValueAsString(strJSON);
		}catch(Exception e) {
			throw new JSONConverterException(AppConstants.STRING_TO_JSONOBJ_ERR_CODE, AppConstants.STRING_TO_JSONOBJ_ERR_MSG);
		}
		
	}
}
