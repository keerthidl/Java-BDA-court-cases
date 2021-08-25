package com.personal.notification.rest.client;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.personal.notification.core.model.NotificationRequestModel;
import com.personal.notification.core.util.JSONConverter;
import com.personal.notification.sms.data.model.MobilNxtSMSMessageModel;

/**
 * 
 * @author dvsnk
 *
 */
public class ServiceClient {
	
	/**
	 * default constructor
	 */
	public ServiceClient() {
		
	}
	
	/**
	 * test post
	 */
	public void executeGet(String url) {
		System.out.println(" Testing POST ");
	
		try {
			RestTemplate restTemplate = new RestTemplate();

			//HttpEntity<MobilNxtSMSMessageModel> request = new HttpEntity<MobilNxtSMSMessageModel>(mobilNxtSMSMessageModel);
			String serviceResponse = restTemplate.getForObject(url, String.class);
			System.out.println(" SMS RESPONSE "+serviceResponse);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * test post
	 */
	public void executePost(String url, String strMessage) {
		System.out.println(" Testing POST ");
		/**MobilNxtSMSMessageModel mobilNxtSMSMessageModel = new MobilNxtSMSMessageModel();
		
		mobilNxtSMSMessageModel.setTxt("TESTING");
		mobilNxtSMSMessageModel.setApiKey("Yhf7awQ9apjnbVcW9aeGGkaddfVWbh");
		mobilNxtSMSMessageModel.setFrom("BLRBDA");
		mobilNxtSMSMessageModel.setTid("1207161891665132303");
		mobilNxtSMSMessageModel.setTo("8904173832");**/
		
		try {
			//System.out.println("<< Service Response >> "+JSONConverter.convertPOJOToString(mobilNxtSMSMessageModel));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RestTemplate restTemplate = new RestTemplate();

		//HttpEntity<MobilNxtSMSMessageModel> request = new HttpEntity<MobilNxtSMSMessageModel>(mobilNxtSMSMessageModel);
		//String serviceResponse = restTemplate.postForObject(url, request, String.class);
		
	}
	
	/**
	 * main method
	 * 
	 */
	public static void main(String[] args) {
		String strURL = "http://localhost:8090/notifications/api/v1/message";
		String smsURL = "https://www.mobilnxt.in/api/push";
		
		
		ServiceClient serviceClient = new ServiceClient();
		serviceClient.executePost(strURL, "");
	}

}
