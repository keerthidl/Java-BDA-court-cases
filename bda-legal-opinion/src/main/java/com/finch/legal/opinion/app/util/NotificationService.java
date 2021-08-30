package com.finch.legal.opinion.app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.services.CommunicationService;
import com.finch.legal.opinion.app.services.EmployeeService;

/**
 * 
 * @author dvsnk
 *
 */
@Service
public class NotificationService {
	
	/** Logger **/
	private static AppLogger LOG = LogManager.getLogger(NotificationService.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CommunicationService communicationService;
	
	/**
	 * SendNotifications
	 */
	public NotificationService() {
		
	}
	
	/**
	 * send notification
	 */
	
	public void send(String status,String requestedBy) {
		List<String> lstAddresses = null;
		List<EmployeeEntity> lstEmployeeEntities = null;
		EmployeeEntity employeeEntity = null;	
		LOG.info("Entered Sendinf Notification ");
		
		/*** COMMUNICATION ***/
		if(status!=null && status.equalsIgnoreCase(AppConstants.REQUESTED_STATUS)) {
				lstAddresses = new ArrayList();
				lstEmployeeEntities = employeeService.getEmpByRole(AppConstants.LAW_OFFICER_ROLEID);
			
				if(lstEmployeeEntities!=null && lstEmployeeEntities.size()>0) {
					lstAddresses = new ArrayList();
					employeeEntity = (EmployeeEntity)lstEmployeeEntities.get(0);
					lstAddresses.add(employeeEntity.getPhoneNum());
					communicationService.sendNewRequest(lstAddresses,AppConstants.LEGAL_OPINION_SOUGHT_SMS_MESSAGE);
				}else {
					LOG.warn("No Law Officer Role Found for Sending Notifications");;
				}
				
				employeeEntity = employeeService.getEmployeeById(requestedBy);
				
				if(employeeEntity!=null && employeeEntity.getPhoneNum().trim().length()>0) {
					lstAddresses = new ArrayList();
					employeeEntity = (EmployeeEntity)lstEmployeeEntities.get(0);
					lstAddresses.add(employeeEntity.getPhoneNum());
					communicationService.sendNewRequest(lstAddresses,AppConstants.LEGAL_OPINION_SOUGHT_SMS_MESSAGE);
				}else {
					LOG.warn("No Law Officer Role Found for Sending Notifications");;
				}
				
				lstAddresses = new ArrayList();
				lstAddresses.add(employeeEntity.getPhoneNum());
				communicationService.sendNewRequest(lstAddresses, AppConstants.LEGAL_OPINION_REQUESTED_SMS_MESSAGE);
				LOG.info("Successfully sent SMS notifications for Legela Opinion Request");
				
		}else if(status!=null && status.equalsIgnoreCase("1"+AppConstants.ASSIGNEDTO_STATUS)) {
			lstAddresses = new ArrayList();
			lstEmployeeEntities = employeeService.getEmpByRole(AppConstants.LAW_OFFICER_ROLEID);
		
		    /** Sent to law officer **/	
			lstAddresses.add(requestedBy);
			communicationService.sendNewRequest(lstAddresses, AppConstants.LEGAL_OPINION_ASSIGNED_SMS_MESSAGE);
			/**************************************/
			
			/*** communication to requester **/
			
			employeeEntity = employeeService.getEmployeeById(requestedBy);
			lstAddresses = new ArrayList();
			//lstAddresses.add(loggedInUserPhoneNumber);
			communicationService.sendNewRequest(lstAddresses, AppConstants.LEGAL_OPINION_ASSIGNED_SMS_MESSAGE);
			
			/*************************************************/
			
			/*** communication to lawyer **/
		
			employeeEntity = employeeService.getEmployeeById(requestedBy);
			lstAddresses = new ArrayList();
			lstAddresses.add(employeeEntity.getPhoneNum());
			communicationService.sendNewRequest(lstAddresses, "Requested for Legal Opinion");
			LOG.info("Successfully sent SMS notifications for Legela Opinion Request");
			/*************************************************/
	   }else if(status!=null && status.equalsIgnoreCase("2"+AppConstants.CLOSED_STATUS)) {
			lstAddresses = new ArrayList();
			lstEmployeeEntities = employeeService.getEmpByRole(AppConstants.LAW_OFFICER_ROLEID);
		
		    /** Sent to law officer **/	
			//lstAddresses.add(loggedInUserPhoneNumber);
			communicationService.sendNewRequest(lstAddresses, AppConstants.LEGAL_OPINION_REQUESTED_SMS_MESSAGE);
			/**************************************/
			
			/*** communication to requester **/
			
			employeeEntity = employeeService.getEmployeeById(requestedBy);
			lstAddresses = new ArrayList();
			//lstAddresses.add(loggedInUserPhoneNumber);
			communicationService.sendNewRequest(lstAddresses, AppConstants.LEGAL_OPINION_REQUEST_CLOSED_SMS_MESSAGE);
			
			/*************************************************/
			
			/*** communication to lawyer **/
			//employeeEntity = employeeService.getEmployeeById(assignedTo);
			lstAddresses = new ArrayList();
			//lstAddresses.add(loggedInUserPhoneNumber);
			communicationService.sendNewRequest(lstAddresses, AppConstants.LEGAL_OPINION_REQUEST_CLOSED_SMS_MESSAGE);
			
	   }
		
	}
}
