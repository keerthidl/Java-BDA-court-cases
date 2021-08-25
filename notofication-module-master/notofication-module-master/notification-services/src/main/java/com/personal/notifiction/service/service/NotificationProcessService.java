package com.personal.notifiction.service.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.personal.notification.common.logging.LogManager;
import com.personal.notification.common.logging.Logger;
import com.personal.notification.core.constants.NotificationConstants;
import com.personal.notification.core.exceptions.NotificationDeliveryException;
import com.personal.notification.core.model.NotificationRequestModel;
import com.personal.notification.core.model.TemplateDetails;
import com.personal.notification.core.util.JSONConverter;
import com.personal.notification.core.util.SecurityUtil;
import com.personal.notifiction.email.client.EmailClient;
import com.personal.notifiction.service.controller.NotificationDeliveryServiceController;
import com.personal.notifiction.sms.client.SMSClient;


/**
 * 
 * @author dvsnk
 *
 */
@Service
public class NotificationProcessService {
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(NotificationProcessService.class);
	/** repo reference **/
	 @Autowired
	 private NotificationRepository repository;
	 
	 /** repo reference **/
	 @Autowired
	 private NotificationTemplatesRepository notificationTemplatesRepository;
	 
	 /** SMS channel Properties **/
	 private Properties smsProperties = new Properties();
	 /** SMS channel Properties **/
	 private Properties emailProperties = new Properties();
	 
	 /** SMS channel Properties **/
	 private Properties emailAccntProperties = new Properties();
	 
	 /** InputStream is **/
	 private InputStream resourceStream = null;
	
	/**
	 * default constructor
	 */
	public NotificationProcessService() {
		LOG.info("In Notification Service Constructor...");
		try {
			  resourceStream = this.getClass().getClassLoader().getResourceAsStream("SMS_CHANNEL.properties");
			  smsProperties.load(resourceStream);
			  
			  resourceStream = this.getClass().getClassLoader().getResourceAsStream("EMAIL_CHANNEL.properties");
			  emailProperties.load(resourceStream);
			  
			  resourceStream = this.getClass().getClassLoader().getResourceAsStream("EMAIL_ACCOUNT.properties");
			  emailAccntProperties.load(resourceStream);
			  
		}catch(Exception e) {
			LOG.info("In Notification Service Constructor..< error >."+e.getMessage());
			LOG.error(e);
		}
	}
	
	/**
	 * process
	 */
	public String processMessage(String notificationModel) throws NotificationDeliveryException{
		NotificationRequestModel notifictionRequestModel = null;
		String channelType="";
		Notification notification = null;
		String templateID="";
		String notificationId=""+UUID.randomUUID();
		try {
			notifictionRequestModel = (NotificationRequestModel)JSONConverter.convertToPOJO(notificationModel, NotificationRequestModel.class);
			notification = buildNotificationRecord(notifictionRequestModel.getHashMapMesageProperties(),notifictionRequestModel.getMessage(),notificationId,NotificationConstants.NOTIFICATION_STATUS_RECEIVED);
			notification.setNotificationId(notificationId);
			
			LOG.info("Received a notification for channel."+notificationId);
			
			if((notifictionRequestModel!=null && notifictionRequestModel.getHashMapMesageProperties()!=null) && notifictionRequestModel.getHashMapMesageProperties().containsKey("TYPE")) {
				channelType= ""+notifictionRequestModel.getHashMapMesageProperties().get(NotificationConstants.TYPE_KEY_NAME);
				templateID= ""+notifictionRequestModel.getHashMapMesageProperties().get(NotificationConstants.TEMPLATEID_KEY_NAME);
				LOG.info("Received a notification for channel."+channelType+" << template id >> "+templateID);
				if(channelType!=null && channelType.trim().length()>0) {
					notification.setChannel(channelType);
					if(channelType.equalsIgnoreCase(NotificationConstants.EMAIL_CHANNEL_TYPE_CODE) && (templateID!=null && templateID.trim().trim().length()<1)) {
						EmailClient emailClient = new EmailClient();
						emailClient.channelProperties = emailProperties;
						emailClient.processMessage(notifictionRequestModel.getMessage(),notifictionRequestModel.getHashMapMesageProperties());
						LOG.info("Received a notification for channel."+channelType+" << no trmplate >> "+templateID);
					}else if(channelType.equalsIgnoreCase(NotificationConstants.EMAIL_CHANNEL_TYPE_CODE) && (templateID!=null && templateID.trim().trim().length()>0)) {
						EmailClient emailClient = new EmailClient();
						emailClient.channelProperties = emailProperties;
						emailClient.fromAddressProperties = emailAccntProperties;
						TemplateDetails formatMessage = new TemplateDetails();
						  InputStream is = this.getClass().getClassLoader().getResourceAsStream("MessageTemplates.txt");
						  Properties properties = new Properties();
						  properties.load(is);
						  formatMessage.setSubject(""+properties.get(templateID+"_SUBJECT"));
						  formatMessage.setBody(""+properties.get(templateID+"_BODY"));
						  formatMessage.setKeys(""+properties.get(templateID+"_KEYS"));
						  
						 notifictionRequestModel.getHashMapMesageProperties().put(NotificationConstants.TEMPLATEDETAILS_KEY_NAME,formatMessage);
						emailClient.processMessage(notifictionRequestModel.getMessage(),notifictionRequestModel.getHashMapMesageProperties());
						LOG.info("Received a notification for channel."+channelType+" << template id >> "+templateID);
					}else if(channelType.equalsIgnoreCase(NotificationConstants.SMS_CHANNEL_TYPE_CODE) && (templateID!=null && templateID.trim().trim().length()<1)) {
						SMSClient smsClient = new SMSClient();
						smsClient.channelProperties = smsProperties;
						smsClient.processMessage(notifictionRequestModel.getMessage(),notifictionRequestModel.getHashMapMesageProperties());
						LOG.info("Received a notification for channel."+channelType+" << template id >> "+templateID);
					}else if(channelType.equalsIgnoreCase(NotificationConstants.SMS_CHANNEL_TYPE_CODE) && (templateID!=null && templateID.trim().trim().length()>0)) {
						
						SMSClient smsClient = new SMSClient();
						smsClient.channelProperties = smsProperties;
						TemplateDetails formatMessage = new TemplateDetails();
						  InputStream is = this.getClass().getClassLoader().getResourceAsStream("MessageTemplates.txt");
						  Properties properties = new Properties();
						  properties.load(is);
						  formatMessage.setBody(""+properties.get(templateID+"_BODY"));
						  formatMessage.setKeys(""+properties.get(templateID+"_KEYS"));
						  notifictionRequestModel.getHashMapMesageProperties().put("TEMPLATEDETAILS",formatMessage);
						  smsClient.processMessage(notifictionRequestModel.getMessage(),notifictionRequestModel.getHashMapMesageProperties());
						  LOG.info("Received a notification for channel."+channelType+" << template id >> "+templateID);
					}else {
						notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
						repository.save(notification);
						LOG.info("error while processing the notifications."+channelType+" << template id >> "+templateID);
						LOG.info(" No Channel Type mentioned .....");
						throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, "Channel Type is Missing allowed values (EMAIL,SMS");
					}
				}else {
					notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
					repository.save(notification);
					LOG.info("error while processing the notifications. No Hash Properties mentioned ");
					
					throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, "Channel Type is Missing allowed values (EMAIL,SMS");
				}
			}else {
				notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
				repository.save(notification);
				LOG.info("error while processing the notifications. 2 No Hash Properties mentioned ");
				throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, "Channel Type is Missing allowed values (EMAIL,SMS");
			}
			
			
		}catch(JsonParseException jsonParseException) {
			LOG.error(jsonParseException);
			
			notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
			repository.save(notification);
			throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, jsonParseException.getMessage());
		}catch(JsonMappingException jsonMappingException) {
			LOG.error(jsonMappingException);
			notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
			repository.save(notification);
			
			throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, jsonMappingException.getMessage());
		}catch(IOException ioException) {
			LOG.error(ioException);
			notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
			repository.save(notification);
			
			throw new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, ioException.getMessage());
		}catch(Exception exception) {
			LOG.error(exception);
			System.out.println(" CHECK THIS ::: "+notification);
			notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_FAILED);
			repository.save(notification);
			throw new NotificationDeliveryException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, exception.getMessage());
		}
		notification.setStatus(NotificationConstants.NOTIFICATION_STATUS_DELIVERED);
		repository.save(notification);
		return notification.getNotificationId();
	}

	/**
	 * build notification record
	 */
	private Notification buildNotificationRecord(HashMap hashMapProperties, String payLoad,String id,String status) {
		 Notification notification = new Notification();
		
		   notification.setNotificationDataModel(SecurityUtil.encrypt(payLoad));
		    if(hashMapProperties.containsKey(NotificationConstants.APP_ID_KEY_NAME)) {
		    	notification.setAppId(""+hashMapProperties.containsKey(NotificationConstants.APP_ID_KEY_NAME));
		    }else {
		    	notification.setAppId(NotificationConstants.UNKNOWN_KEY_NAME);
		    }
		    if(hashMapProperties.containsKey(NotificationConstants.CHANNEL_KEY_NAME)) {
		    	notification.setChannel(""+hashMapProperties.containsKey(NotificationConstants.CHANNEL_KEY_NAME));
		    }else {
		    	notification.setChannel(NotificationConstants.UNKNOWN_KEY_NAME);
		    }
		    java.util.Date localDate = Calendar.getInstance().getTime();
		    Calendar.getInstance().getTime();
	        notification.setDtRequested(localDate);
	        notification.setSentDate(localDate);
	        notification.setLastUpdated(localDate);
	        notification.setNotificationId(id);
	        
	        if(hashMapProperties.containsKey(NotificationConstants.NOTIFICATION_TYPE_KEY_NAME)) {
		    	notification.setNotificationType(""+hashMapProperties.get(NotificationConstants.NOTIFICATION_TYPE_KEY_NAME));
		    }else {
		    	notification.setNotificationType(NotificationConstants.UNKNOWN_KEY_NAME);
		    }
	        notification.setStatus(status);
	     return notification;   
	}
}
