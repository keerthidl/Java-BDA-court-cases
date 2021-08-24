package com.personal.services.tester;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.personal.notification.core.model.NotificationRequestModel;

/**
 * 
 * @author dvsnk
 *
 */
public class ServiceTester {
	
	/**
	 * default constructor
	 */
	public ServiceTester() {
		
	}
	
	/**
	 * test post
	 */
	public void testPost(String url, String payload) {
		System.out.println(" Testing POST ");
		NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
		
		notificationRequestModel.setMessage("TESTING");
		notificationRequestModel.setHashMapMesageProperties(new HashMap());
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<NotificationRequestModel> request = new HttpEntity<NotificationRequestModel>(notificationRequestModel);
		String serviceResponse = restTemplate.postForObject(url, request, String.class);
		System.out.println("<< Service Response >> "+serviceResponse);
	}
	
	/**
	 * main method
	 * 
	 */
	public static void main(String[] args) {
		String strURL = "http://localhost:8090/notifications/api/v1/message";
		ServiceTester serviceTester = new ServiceTester();
		serviceTester.testPost(strURL, "");
	}

}
