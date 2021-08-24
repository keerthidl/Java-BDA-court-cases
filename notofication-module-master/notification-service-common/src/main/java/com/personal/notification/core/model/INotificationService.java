package com.personal.notification.core.model;

import java.util.HashMap;

import com.personal.notification.core.exceptions.NotificationDeliveryException;

/**
 * 
 * @author dvsnk
 *
 */
@FunctionalInterface
public interface INotificationService {

	/**
	 * message
	 */
	public String processMessage(String message,HashMap hashMapMessageProperties) throws NotificationDeliveryException;
	
}
