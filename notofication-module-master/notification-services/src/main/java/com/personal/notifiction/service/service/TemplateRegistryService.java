package com.personal.notifiction.service.service;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.personal.notification.core.constants.NotificationConstants;
import com.personal.notification.core.exceptions.NotificationDeliveryException;
import com.personal.notification.core.model.NotificationRequestModel;
import com.personal.notification.core.util.JSONConverter;
import com.personal.notification.core.util.SecurityUtil;
import com.personal.notifiction.email.client.EmailClient;


/**
 * 
 * @author dvsnk
 *
 */
@Service
public class TemplateRegistryService {
	 
	 /** repo reference **/
	 @Autowired
	 private MessageFormatRepository notificationTemplatesRepository;
	
	/**
	 * default constructor
	 */
	public TemplateRegistryService() {
		
	}
	
	/**
	 * process
	 */
	public String processMessage(String templateDetails) throws NotificationDeliveryException{
		MessageFormat objTemplateDetails = null;
		String channelType="";
		String id="Failed";
		Notification notification = null;
		String templateID="";
		
		try {
			objTemplateDetails = (MessageFormat)JSONConverter.convertToPOJO(templateDetails, MessageFormat.class);
			notificationTemplatesRepository.save(objTemplateDetails);
			return objTemplateDetails.getId().toString();
		}catch(JsonParseException jsonParseException) {
			jsonParseException.printStackTrace();
			throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, jsonParseException.getMessage());
		}catch(JsonMappingException jsonMappingException) {
			jsonMappingException.printStackTrace();
			throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, jsonMappingException.getMessage());
		}catch(IOException ioException) {
			ioException.printStackTrace();
			throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, ioException.getMessage());
		}catch(Exception exception) {
			exception.printStackTrace();
			throw new NotificationDeliveryException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, exception.getMessage());
		}
	}

	/**
	 * build notification record
	 */
	private Notification buildNotificationRecord(HashMap hashMapProperties, String payLoad,String id,String status) {
		 Notification notification = new Notification();
		 
		   notification.setNotificationDataModel(SecurityUtil.encrypt(payLoad));
		    if(hashMapProperties.containsKey("APPID")) {
		    	notification.setAppId(""+hashMapProperties.containsKey("APPID"));
		    }else {
		    	notification.setAppId("GENERAL");
		    }
		    if(hashMapProperties.containsKey("CHANNEL")) {
		    	notification.setChannel(""+hashMapProperties.containsKey("CHANNEL"));
		    }else {
		    	notification.setChannel("EMAIL");
		    }
	       
	        notification.setDtRequested(new Date(10,10,1972));
	        notification.setSentDate(new Date(10,10,1972));
	        notification.setLastUpdated(new Date(10,10,1972));
	        notification.setNotificationId(id);
	        
	        if(hashMapProperties.containsKey("NOTIFICATIONTYPE")) {
		    	notification.setNotificationType(""+hashMapProperties.get("NOTIFICATIONTYPE"));
		    }else {
		    	notification.setNotificationType("GENERAL");
		    }
	        notification.setStatus(status);
	     return notification;   
	}
}
