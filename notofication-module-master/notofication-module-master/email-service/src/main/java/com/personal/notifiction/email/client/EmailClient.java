package com.personal.notifiction.email.client;


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
import com.personal.notification.core.util.SecurityUtil;
import com.personal.notifiction.email.data.model.EmailMessageModel;
import com.personal.notifiction.email.data.model.TemplateBasedEmailMessageModel;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author dvsnk
 *
 */
public class EmailClient implements INotificationService {
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(EmailClient.class);
	
	/** host name **/
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	
	/** post **/
	private static final String SMTP_PORT = "465";
	
		/** from address **/
	private static final String emailFromAddress = "deverakonda72@gmail.com";
	
	/** ssl factory **/
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		
	/** properties **/
	public static Properties channelProperties = new Properties();
	
	/** properties **/
	public static Properties fromAddressProperties = new Properties();
	
	static {
		channelProperties.put("mail.smtp.host", SMTP_HOST_NAME);
		channelProperties.put("mail.smtp.port", SMTP_PORT);
		channelProperties.put("mail.smtp.socketFactory.port", SMTP_PORT);
		channelProperties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		channelProperties.put("mail.smtp.socketFactory.fallback", "false");
		channelProperties.put("enable_starttls_auto", false);
		channelProperties.put("mail.smtp.auth", "true");
		channelProperties.put("mail.debug", "true");
		 
		
	}

	
	/**
	 * default constructor
	 */
	public EmailClient() {
		
	}

	/**
	 * processing the message
	 */
	public String processMessage(String message, HashMap hashMapMessageProperties) throws NotificationDeliveryException{
		EmailMessageModel emailMessageModel = null;
		TemplateBasedEmailMessageModel templateBasedEmailMessageModel=null;
		
		LOG.info(" In Email Process message ...");
		
		if(hashMapMessageProperties!=null && hashMapMessageProperties.containsKey(NotificationConstants.TEMPLATEID_KEY_NAME)) {
			templateBasedEmailMessageModel = validateTemplateBasedMessage(message);
			emailMessageModel = buildMessage(hashMapMessageProperties, ""+hashMapMessageProperties.get(NotificationConstants.TEMPLATEID_KEY_NAME),templateBasedEmailMessageModel);
		}else {
			emailMessageModel = validateMessage(message);
		}
		send(emailMessageModel);
		LOG.info(" SUccessfully sent the message ...");
		return "SUCCESS";
	}
	
	/**
	 * send email
	 */
	private void send(EmailMessageModel emailMessageModel) throws NotificationDeliveryException{
		String subject = "";
		String body = "";
		String[] toAddress = null;
		String[] ccAddress = null;
		String[] bccAddress = null;
		
		List<String> lstToAddress = emailMessageModel.getLstToAddresses();
		List<String> lstCCAddress = emailMessageModel.getLstCCAddresses();
		List<String> lstBCCAddress = emailMessageModel.getLstBCCAddresses();
		
		toAddress = convertToList(lstToAddress);
		ccAddress = convertToList(lstCCAddress);
		bccAddress = convertToList(lstBCCAddress);
		
		subject = emailMessageModel.getSubject();
		body = emailMessageModel.getBody();
		sendMessage(subject, body, toAddress, ccAddress, bccAddress);
	}
	
	/**
	 * build message
	 */
	private EmailMessageModel buildMessage(HashMap hashMapMessageProperties, String templateId, TemplateBasedEmailMessageModel templateBasedModel) {
		EmailMessageModel emailMessageModel = null;
		String subject = "";
		String body = "";
		String keys = "";
		String key="";
		emailMessageModel = new EmailMessageModel();
		
		TemplateDetails templateDetails = (TemplateDetails)hashMapMessageProperties.get(NotificationConstants.TEMPLATEDETAILS_KEY_NAME);
		
		HashMap hashMapMesgProperties = templateBasedModel.getHashMapMessageProperties();
		
		
		
		body = templateDetails.getBody();
		subject = templateDetails.getSubject();
		keys = templateDetails.getKeys();
		StringTokenizer stringTokenizer = new StringTokenizer(keys,",");
		
		while(stringTokenizer.hasMoreTokens()) {
			key = ""+stringTokenizer.nextToken();
			subject = subject.replace(NotificationConstants.PREFIX_TYPE_KEY_NAME+key+key, ""+hashMapMesgProperties.get(key));
			body = body.replace(NotificationConstants.PREFIX_TYPE_KEY_NAME+key, ""+hashMapMesgProperties.get(key));
		}
		emailMessageModel.setLstBCCAddresses(templateBasedModel.getLstBCCAddresses());
		emailMessageModel.setLstCCAddresses(templateBasedModel.getLstCCAddresses());
		emailMessageModel.setLstToAddresses(templateBasedModel.getLstToAddresses());
		emailMessageModel.setBody(body);
		emailMessageModel.setSubject(subject);
		
		return emailMessageModel;
		
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
	private EmailMessageModel validateMessage(String message) throws NotificationDeliveryException{
		EmailMessageModel emailMessageModel = null;
		
		try {
			emailMessageModel = (EmailMessageModel)JSONConverter.convertToPOJO(message, EmailMessageModel.class);
			if(emailMessageModel==null) {
				throw buildException("Invalid Email Message Payload");
			}else if(emailMessageModel.getBody()==null || emailMessageModel.getBody().trim().length()<1) {
				throw buildException("Invalid Email Body");
			}else if(emailMessageModel.getSubject()==null || emailMessageModel.getSubject().trim().length()<1) {
				throw buildException("Invalid Email Subject");
			}else if(emailMessageModel.getLstToAddresses()==null || emailMessageModel.getLstToAddresses().size()<1) {
				throw buildException("Invalid Email Recepient List");
			}
		}catch(Exception e) {
			LOG.info(" Email message validation failed ...");
			LOG.error(e);
			throw buildException("Invalid Email Message Payload");
		}
		return emailMessageModel;
	}
	
	/**
	 * validate message
	 */
	private TemplateBasedEmailMessageModel validateTemplateBasedMessage(String message) throws NotificationDeliveryException{
		TemplateBasedEmailMessageModel templateBasedEmailMessageModel = null;
		
		try {
			
			
			templateBasedEmailMessageModel = (TemplateBasedEmailMessageModel)JSONConverter.convertToPOJO(message, TemplateBasedEmailMessageModel.class);
			
			if(templateBasedEmailMessageModel==null) {
				throw buildException("Invalid Email Message Payload");
			}else if(templateBasedEmailMessageModel.getLstToAddresses()==null || templateBasedEmailMessageModel.getLstToAddresses().size()<1) {
				throw buildException("Invalid Email Recepient List");
			}else if(templateBasedEmailMessageModel.getHashMapMessageProperties()==null || templateBasedEmailMessageModel.getHashMapMessageProperties().size()<1) {
				throw buildException("Invalid Email Message Properties");
			}
		}catch(Exception e) {
			LOG.info(" Email message validation failed <<template based >> ...");
			LOG.error(e);
			throw buildException("Invalid Email Message Payload");
		}
		return templateBasedEmailMessageModel;
	}
	
	/**
	 * send message
	 */
	private void sendMessage(String subject, String body, String[] toAddress, String[] ccAddress, String[] bccAddress) throws NotificationDeliveryException {
		
		Message msg = null;
		InternetAddress addressFrom = null;
		InternetAddress[] addressTo = null;
		InternetAddress[] addressCC = null;
		InternetAddress[] addressBCC = null;
		
		Session session = Session.getDefaultInstance(channelProperties,new javax.mail.Authenticator() {

		protected PasswordAuthentication getPasswordAuthentication() {
				    String decryptedPassword = SecurityUtil.decrypt(fromAddressProperties.getProperty("PASSWORD"), SecurityUtil.SECURITY_KEY);
				   	
					return new PasswordAuthentication(fromAddressProperties.getProperty("FROM_ADDRESS"), decryptedPassword);
			}
		});

		session.setDebug(true);
	
		try {
			LOG.info(" Sending email ......");
			msg = new MimeMessage(session);
			addressFrom = new InternetAddress(emailFromAddress);
			msg.setFrom(addressFrom);
		
			addressTo = new InternetAddress[toAddress.length];
			for (int i = 0; i < toAddress.length; i++) {
				addressTo[i] = new InternetAddress(toAddress[i]);
			}
			addressCC = new InternetAddress[ccAddress.length];
			for (int i = 0; i < ccAddress.length; i++) {
				addressCC[i] = new InternetAddress(ccAddress[i]);
			}
			
			addressBCC = new InternetAddress[bccAddress.length];
			for (int i = 0; i < bccAddress.length; i++) {
				addressBCC[i] = new InternetAddress(bccAddress[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setRecipients(Message.RecipientType.CC, addressCC);
			msg.setRecipients(Message.RecipientType.BCC, addressBCC);
		
			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setContent(body, "text/plain");
			Transport.send(msg);
			LOG.info(" Successfully sent email ......");
		}catch(Exception e) {
			LOG.info(" Error while Sending email ......"+e.getMessage());
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
