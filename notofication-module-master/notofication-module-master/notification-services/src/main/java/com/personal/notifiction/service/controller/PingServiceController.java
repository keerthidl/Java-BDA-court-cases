package com.personal.notifiction.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.notification.core.model.NotificationRequestModel;
import com.personal.notification.core.util.JSONConverter;
import com.personal.notification.sms.data.model.MobilNxtSMSMessageModel;
import com.personal.notifiction.email.data.model.EmailMessageModel;
import com.personal.notifiction.email.data.model.TemplateBasedEmailMessageModel;
import com.personal.notifiction.service.service.MessageFormat;
import com.personal.notifiction.service.service.NotificationReportService;
import com.personal.notification.core.constants.NotificationConstants;

/**
 * ping service
 * @author dvsnk
 *
 */

@RestController
public class PingServiceController {
	
	
	/**
	 * ping service
	 */
	@GetMapping(NotificationConstants.PING_SERVICE_URL)
	public String ping() {
		
	
		return "Successfully running Notification Servicec <<  >> ";//+notificationReportService.reportSummary(new HashMap());
	}
	
	
	
	/**
	 * temp method
	 */
	private String getMessage() {
		String strResult="";
		List<String> lstToAddress = new ArrayList<String>();
		lstToAddress.add("dvsnkumar72@gmail.com");
		NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
		EmailMessageModel emailMessageModel = new EmailMessageModel();
		emailMessageModel.setBody("TEST BODY");
		emailMessageModel.setLstToAddresses(lstToAddress);
		emailMessageModel.setLstCCAddresses(lstToAddress);
		emailMessageModel.setLstBCCAddresses(lstToAddress);
		HashMap<String, String> hashMapProperties = new HashMap();
		hashMapProperties.put("TYPE", "EMAIL");
		hashMapProperties.put("TEMPLATEID", "EMAIL_001");
		notificationRequestModel.setHashMapMesageProperties(hashMapProperties);
		
		MessageFormat templateDetails = new MessageFormat();
		templateDetails.setBody("Hi $Name, Welcome to my app $MyApp");
		templateDetails.setSubject("Hi $Subject1, Welcome to my app $Subject2");
		templateDetails.setTemplateID("EMAIL001");
		templateDetails.setKeys("$Name,$MyApp,$Subject1,$Subject2");
		
		TemplateBasedEmailMessageModel templateBasedEmailMessageModel = new TemplateBasedEmailMessageModel();
		
		//$Subject1,$Subject2,$Name,$MyApp
		HashMap hashMapProperties1 = new HashMap();
		hashMapProperties1.put("Subject1", "TESTING-SUBJECT1");
		hashMapProperties1.put("Subject2", "TESTING-SUBJECT2");
		hashMapProperties1.put("Name", "TESTING-NAME");
		hashMapProperties1.put("MyApp", "TESTING-MYAPP");
		templateBasedEmailMessageModel.setHashMapMessageProperties(hashMapProperties1);
		templateBasedEmailMessageModel.setLstToAddresses(lstToAddress);
		templateBasedEmailMessageModel.setLstCCAddresses(lstToAddress);
		templateBasedEmailMessageModel.setLstBCCAddresses(lstToAddress);
		
		
		MobilNxtSMSMessageModel mobilNxtSMSMessageModel = new MobilNxtSMSMessageModel();
		 
		mobilNxtSMSMessageModel.setText("TESTING========");
		mobilNxtSMSMessageModel.setAccesskey("Yhf7awQ9apjnbVcW9aeGGkaddfVWbh");
		mobilNxtSMSMessageModel.setFrom("BLRBDA");
		mobilNxtSMSMessageModel.setTid("1207161891665132303");
		mobilNxtSMSMessageModel.setTo("8904173832");
		
		try {
			
			notificationRequestModel.setMessage(""+JSONConverter.convertPOJOToString(templateBasedEmailMessageModel));
			
			//notificationRequestModel.setMessage(""+JSONConverter.convertPOJOToString(templateDetails));
			return ""+JSONConverter.convertPOJOToString(mobilNxtSMSMessageModel);
		}catch(Exception e) {
			return "";
		}
	}

}
