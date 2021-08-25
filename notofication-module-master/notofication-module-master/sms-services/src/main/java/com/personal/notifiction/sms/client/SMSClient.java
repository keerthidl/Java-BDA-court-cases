package com.personal.notifiction.sms.client;


import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import com.personal.notification.common.logging.LogManager;
import com.personal.notification.common.logging.Logger;
import com.personal.notification.core.constants.NotificationConstants;
import com.personal.notification.core.exceptions.NotificationDeliveryException;
import com.personal.notification.core.model.INotificationService;
import com.personal.notification.core.model.TemplateDetails;
import com.personal.notification.core.util.JSONConverter;
import com.personal.notification.rest.client.ServiceClient;
import com.personal.notification.sms.data.model.MobilNxtSMSMessageModel;
import com.personal.notification.sms.data.model.SMSMessageModel;
import com.personal.notification.sms.data.model.TemplateBasedSMSMessageModel;



/**
 * 
 * @author dvsnk
 *
 */
public class SMSClient implements INotificationService {
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(SMSClient.class);
	
	/** SMS channel properties **/
	public static Properties channelProperties;
	
	/**
	 * default constructor
	 */
	public SMSClient() {
	}

	/**
	 * processing the message
	 */
	public String processMessage(String message, HashMap hashMapMessageProperties) throws NotificationDeliveryException{
		SMSMessageModel smsMessageModel = null;
		TemplateBasedSMSMessageModel templateBasedSMSMessageModel=null;
		LOG.info(" sending SMS notification started");
		if(hashMapMessageProperties!=null && hashMapMessageProperties.containsKey(NotificationConstants.TEMPLATEID_KEY_NAME)) {
			templateBasedSMSMessageModel = validateTemplateBasedMessage(message);
			smsMessageModel = buildMessage(hashMapMessageProperties, ""+hashMapMessageProperties.get(NotificationConstants.TEMPLATEID_KEY_NAME),templateBasedSMSMessageModel);
		}else {
			smsMessageModel = validateMessage(message);
		}
		
		send(smsMessageModel);
		LOG.info(" SUccessfully sent SMS notification");
		return "SUCCESS";
	}
	
	/**
	 * send email
	 */
	private void send(SMSMessageModel smsMessageModel) throws NotificationDeliveryException{
	
		String body = "";
		String[] toAddress = null;
		
		
		List<String> lstToAddress = smsMessageModel.getLstToAddresses();
			
		toAddress = convertToList(lstToAddress);
	
		
		body = smsMessageModel.getBody();
		sendMessage(body, toAddress);
	}
	
	/**
	 * build message
	 */
	private SMSMessageModel buildMessage(HashMap hashMapMessageProperties, String templateId, TemplateBasedSMSMessageModel templateBasedModel) {
		SMSMessageModel smsMessageModel = null;
		String subject = "";
		String body = "";
		String keys = "";
		String key="";
		smsMessageModel = new SMSMessageModel();
		
		TemplateDetails templateDetails = (TemplateDetails)hashMapMessageProperties.get(NotificationConstants.TEMPLATEDETAILS_KEY_NAME);
		
		HashMap hashMapMesgProperties = templateBasedModel.getHashMapMessageProperties();
		
		body = templateDetails.getBody();
		subject = templateDetails.getSubject();
		keys = templateDetails.getKeys();
		StringTokenizer stringTokenizer = new StringTokenizer(keys,",");
		
		while(stringTokenizer.hasMoreTokens()) {
			key = ""+stringTokenizer.nextToken();
			body = body.replace(NotificationConstants.PREFIX_TYPE_KEY_NAME+key, ""+hashMapMesgProperties.get(key));
		}
		
		smsMessageModel.setLstToAddresses(templateBasedModel.getLstToAddresses());
		smsMessageModel.setBody(body);
		
		
		return smsMessageModel;
		
	}
	
	/**
	 * convert to list to array
	 */
	private String[] convertToList(List<String> lstAddresses) {
		String[] arrayAddresses = new String[lstAddresses.size()];
		int index=0;
		for(String strAddress: lstAddresses) {
			arrayAddresses[index++] = strAddress;
		}
		return arrayAddresses;
	}
	/**
	 * validate message
	 */
	private SMSMessageModel validateMessage(String message) throws NotificationDeliveryException{
		SMSMessageModel smsMessageModel = null;
		
		try {
			smsMessageModel = (SMSMessageModel)JSONConverter.convertToPOJO(message, SMSMessageModel.class);
			if(smsMessageModel==null) {
				throw buildException("Invalid Email Message Payload");
			}else if(smsMessageModel.getBody()==null || smsMessageModel.getBody().trim().length()<1) {
				throw buildException("Invalid Email Body");
			}else if(smsMessageModel.getLstToAddresses()==null || smsMessageModel.getLstToAddresses().size()<1) {
				throw buildException("Invalid Email Recepient List");
			}
		}catch(Exception e) {
			LOG.info(" error while validating sms notifications "+e.getMessage());
			LOG.error(e);
			throw buildException("Invalid Email Message Payload");
		}
		return smsMessageModel;
	}
	
	/**
	 * validate message
	 */
	private TemplateBasedSMSMessageModel validateTemplateBasedMessage(String message) throws NotificationDeliveryException{
		TemplateBasedSMSMessageModel templateBasedSMSMessageModel = null;
		
		try {
			
			
			templateBasedSMSMessageModel = (TemplateBasedSMSMessageModel)JSONConverter.convertToPOJO(message, TemplateBasedSMSMessageModel.class);
			
			if(templateBasedSMSMessageModel==null) {
				throw buildException("Invalid Email Message Payload");
			}else if(templateBasedSMSMessageModel.getLstToAddresses()==null || templateBasedSMSMessageModel.getLstToAddresses().size()<1) {
				throw buildException("Invalid Email Recepient List");
			}else if(templateBasedSMSMessageModel.getHashMapMessageProperties()==null || templateBasedSMSMessageModel.getHashMapMessageProperties().size()<1) {
				throw buildException("Invalid Email Message Properties");
			}
		}catch(Exception e) {
			LOG.info(" error while validating sms notifications << template id >> "+e.getMessage());
			LOG.error(e);
			throw buildException("Invalid Email Message Payload");
		}
		return templateBasedSMSMessageModel;
	}
	
	/**
	 * send message
	 */
	private void sendMessage(String body, String[] toAddress) throws NotificationDeliveryException {
		
		ServiceClient serviceClient = null;
		MobilNxtSMSMessageModel mobilNxtSMSMessageModel = null;
		String urlParameters = "";
		try {
			
			mobilNxtSMSMessageModel = new MobilNxtSMSMessageModel();
			mobilNxtSMSMessageModel.setAccesskey(channelProperties.getProperty(NotificationConstants.ACCESS_KEY_NAME));
			mobilNxtSMSMessageModel.setFrom(channelProperties.getProperty(NotificationConstants.FROM_KEY_NAME));
			mobilNxtSMSMessageModel.setTid(channelProperties.getProperty(NotificationConstants.TEMPLATEID_KEY));
			
			
			mobilNxtSMSMessageModel.setText(body);
			serviceClient = new ServiceClient();
			
			urlParameters =channelProperties.getProperty(NotificationConstants.CHANNEL_SERVICE_URL) +"?";
			urlParameters = urlParameters+"accesskey="+channelProperties.getProperty(NotificationConstants.ACCESS_KEY_NAME);
			urlParameters = urlParameters+"&from="+channelProperties.getProperty(NotificationConstants.FROM_KEY_NAME);
			urlParameters = urlParameters+"&tid="+channelProperties.getProperty(NotificationConstants.TEMPLATEID_KEY);
			urlParameters = urlParameters+"&text="+body;
			
			
			for(String phoneNumber: toAddress) {
				urlParameters = urlParameters+"&to="+phoneNumber;
				serviceClient.executeGet(urlParameters);
			}
			
		}catch(Exception e) {
			LOG.info(" error while sendin sms notifications << template id >> "+e.getMessage());
			LOG.error(e);
			throw new NotificationDeliveryException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, e.getMessage());
		}
	}
	/**
	 * build exception
	 */
	private NotificationDeliveryException buildException(String message) {
		return new NotificationDeliveryException(NotificationConstants.INVALID_REQUEST_ERROR_ODE, message);
	}
	
	
}
