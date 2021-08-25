/**
 * 
 */
package com.personal.notifiction.service.service;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dvsnk
 *
 */
@Service
public class NotificationReportService {

	/** repo **/
	@Autowired
	private NotificationRepository notificationRepository;
	
	/**
	 * Default constructor
	 */
	public NotificationReportService() {
		
	}
	
	/**
	 * return Report Summary
	 */
	public String reportSummary(HashMap hashMapFilterCriteria) {
		
		int iCount = 0;// notificationRepository.findChannelwiseCount("EMAIL");
		Number countResult=null;
// 		System.out.println(" HHHHHHHHHHHHHHHHHHH    "+notificationRepository.findChannelwiseCount("SMS"));
		
		try{
			/*EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpa");
			EntityManager em=emf.createEntityManager();
			
			System.out.println(" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA    ");
			
			Query query=em.createQuery("SELECT COUNT(*) FROM Notifications");
			countResult=(Number) query.getSingleResult();
			System.out.println(" VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVv    ");
			
			System.out.println("Count result:"+countResult);*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
// 		System.out.println(" COUNT IS "+iCount);
		return "COUNT= "; //+countResult.intValue();
		/**
		 * Channel wise summary
		 *     a. Total count
		 *     b. Total Failed
		 *     c. Total Successful
		 *     d. Total in Received
		 *     
		 * Full Summary
		 *     a. Total count
		 *     b. Total Failed
		 *     c. Total Successful
		 *     d. Total in Received
		 */
	}
	
	/**
	 * return Report Summary
	 */
	public void listNotifications(HashMap hashMapFilterCriteria) {
		
		/**
		 * Channel wise summary
		 *     a. Total count
		 *     b. Total Failed
		 *     c. Total Successful
		 *     d. Total in Received
		 *     
		 * Full Summary
		 *     a. Total count
		 *     b. Total Failed
		 *     c. Total Successful
		 *     d. Total in Received
		 */
	}
}
