package com.personal.notification.core.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * 
 * @author dvsnk
 *
 */
public class JSONConverter {

	/**
	 * converts string to POJO
	 */
	public static Object convertToPOJO(String content, Class objClass) throws IOException,JsonParseException,JsonMappingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(content, objClass);
		
	}
	
	/**
	 * converts string to POJO
	 */
	public static Object convertPOJOToString(Object objPOJO) throws JsonProcessingException{
			
		ObjectMapper objectMapper = new ObjectMapper();
		return ""+ objectMapper.writeValueAsString(objPOJO);
		
	}
}
