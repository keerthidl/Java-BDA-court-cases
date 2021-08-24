package com.personal.notifiction.service.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.notification.common.logging.LogManager;
import com.personal.notification.common.logging.Logger;
import com.personal.notification.core.constants.NotificationConstants;
import com.personal.notification.core.exceptions.InternalServerException;
import com.personal.notification.core.exceptions.InvalidRequestException;
import com.personal.notification.core.exceptions.NotificationDeliveryException;
import com.personal.notifiction.service.service.NotificationProcessService;
import com.personal.notifiction.service.service.NotificationReportService;
import com.personal.notifiction.service.service.TemplateRegistryService;


/**
 * 
 * @author dvsnk
 *
 */
@RestController
@RequestMapping(NotificationConstants.SERVICE_BASE_URL)
public class NotificationDeliveryServiceController {
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(NotificationDeliveryServiceController.class);

	@Autowired
	private NotificationProcessService notificationProcessService;
	
	@Autowired
	private TemplateRegistryService templateRegistryService;
	
	@Autowired
	private NotificationReportService notificationReportService;
	
	/**
	 * Template
	 */
	@PostMapping(NotificationConstants.TEMPLATE_REGISTRY_URL)
	public String registerNotifications(@RequestBody String templateBody) throws InvalidRequestException,InternalServerException{
		LOG.info(" Received Template Registry ...");
		try {
			return templateRegistryService.processMessage(templateBody);
		}catch(NotificationDeliveryException notificationDeliveryException) {
			LOG.info(" Error occurred while processing Template Registry ...");
			LOG.error(notificationDeliveryException);
			throw new InternalServerException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, "Internal error occurred while processing the request");
		}
	}
	
	/**
	 * Template
	 */
	@GetMapping(NotificationConstants.SUMMARY_URL)
	public String notificationSummary() throws InvalidRequestException,InternalServerException{
		LOG.info(" Received Report Summary ...");
		try {
			return notificationReportService.reportSummary(new HashMap());
		}catch(Exception notificationDeliveryException) {
			LOG.info(" Error occurred while processing Report Summary ...");
			LOG.error(notificationDeliveryException);
			throw new InternalServerException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, "Internal error occurred while processing the request");
		}
	}
	

	/**
	 * deliver notifications
	 */
	@PostMapping(NotificationConstants.MESSAGE_SERVICE_URL)
	public String deliveryNotifications(@RequestBody String message) throws InvalidRequestException,InternalServerException {
		String notificationId ="DEFAULT_ID_00000";
		LOG.info(" Received Notification ...");
		try {
			return notificationProcessService.processMessage(message);
		}catch(NotificationDeliveryException notificationDeliveryException) {
			
			if(notificationDeliveryException.getCode()==NotificationConstants.INVALID_REQUEST_ERROR_ODE) {
				LOG.info(" Error occurred while processing Notifications invalid request "+notificationDeliveryException.getMessage());
				LOG.error(notificationDeliveryException);
				throw new InvalidRequestException(notificationDeliveryException.getCode(), notificationDeliveryException.getMessage());
			}else if(notificationDeliveryException.getCode()==NotificationConstants.INTERNAL_SERVER_ERROR_ODE) {
				LOG.info(" Error occurred while processing Notifications invalid request "+notificationDeliveryException.getMessage());
				LOG.error(notificationDeliveryException);
				throw new InternalServerException(notificationDeliveryException.getCode(), notificationDeliveryException.getMessage());
			}else {
				LOG.info(" Error occurred while processing Notifications unknown exception request ");
				throw new InternalServerException(notificationDeliveryException.getCode(), notificationDeliveryException.getMessage());
			}
		}catch(Exception exception) {
			LOG.info(" Error occurred while processing Notifications ...general exception");
			LOG.error(exception);
			throw new InternalServerException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, exception.getMessage());
		}
	}
	
	/**
	 * deliver notifications
	 * 1. Getting Summary
	 * 2. Gilter based Notifications
	 * 4. Single Notifications
	 */
	@PostMapping("/mptification")
	public String summary(@RequestBody String message) throws InvalidRequestException,InternalServerException {
		String notificationId ="DEFAULT_ID_00000";
		try {
			notificationId = notificationProcessService.processMessage(message);
		}catch(NotificationDeliveryException notificationDeliveryException) {
			
			if(notificationDeliveryException.getCode()==NotificationConstants.INVALID_REQUEST_ERROR_ODE) {
				throw new InvalidRequestException(notificationDeliveryException.getCode(), notificationDeliveryException.getMessage());
			}else if(notificationDeliveryException.getCode()==NotificationConstants.INTERNAL_SERVER_ERROR_ODE) {
				throw new InternalServerException(notificationDeliveryException.getCode(), notificationDeliveryException.getMessage());
			}
		}catch(Exception exception) {
			throw new InternalServerException(NotificationConstants.INTERNAL_SERVER_ERROR_ODE, exception.getMessage());
		}
		return notificationId;
	}
}
