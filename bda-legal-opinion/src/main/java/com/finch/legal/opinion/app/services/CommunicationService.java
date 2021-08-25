package com.finch.legal.opinion.app.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.finch.legal.opinion.app.employee.model.NotificationRequestModel;
import com.finch.legal.opinion.app.employee.model.TemplateBasedEmailMessageModel;
import com.finch.legal.opinion.app.employee.model.TemplateBasedSMSMessageModel;
import com.google.gson.Gson;

/**
 * 
 * @author finch
 *
 */
@Service
public class CommunicationService {
	
	/** SMS Communication URL **/
	private final static String SMS_COMMUNICATION_URL="http://finchnotifications-env.eba-yepdppsg.ap-south-1.elasticbeanstalk.com//notifications/api/v1/message";
	/**
	 * default constructor
	 */
	public CommunicationService() {
		
	}
	
	/**
	 * send notification on new request
	 */
	public void sendNewRequest(List<String> lstToAddresses,String strMessage) {
		ResponseEntity<String> serviceResponseEntity = null;
		Gson gson = new Gson();
		HashMap<String, String> hashMapSMSProperties = new HashMap();
		
		HashMap<String, String> hashMapMessageProperties = new HashMap();
		
		hashMapSMSProperties.put("TEMPLATEID", "SMS_001");
		hashMapSMSProperties.put("TYPE", "SMS");
		
		
		NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
		notificationRequestModel.setHashMapMesageProperties(hashMapSMSProperties);
		
		hashMapMessageProperties.put("OTP", strMessage);
		
		TemplateBasedSMSMessageModel templateBasedSMSMessageModel = new TemplateBasedSMSMessageModel();
		templateBasedSMSMessageModel.setHashMapMessageProperties(hashMapMessageProperties);
		templateBasedSMSMessageModel.setLstToAddresses(lstToAddresses);
		

		notificationRequestModel.setMessage(gson.toJson(templateBasedSMSMessageModel));
		
		RestTemplate restTemplate = new RestTemplate();
		try{
		serviceResponseEntity = restTemplate.postForEntity(SMS_COMMUNICATION_URL, gson.toJson(notificationRequestModel), String.class);
		
		System.out.println(" RESPONSE ENTITY "+serviceResponseEntity);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
