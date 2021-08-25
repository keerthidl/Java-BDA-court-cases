package com.personal.persistence.repos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author dvsnk
 *
 */
@Entity
@Table(name="Notifications")
public class Notification {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	/** notifcation is **/
	 @Column(name="notificationId")
	private String notificationId;
	
	/** Date Requested **/
	 @Column(name="RequestedDate")
	private Date dtRequested;
	
	/** status **/
	 @Column(name="status")
	private String status;
	
	/** date sent **/
	 @Column(name="lastUpdated")
	private Date lastUpdated;
	 
	 /** date sent **/
	 @Column(name="sentDate")
	private Date sentDate;
	 
	 /** date sent **/
	 @Column(name="appId")
	private String appId;
	
	 /** date sent **/
	 @Column(name="notificationType")
	private String notificationType;
	/** string type **/
	 /** date sent **/
	 @Column(name="channel")
	private String channel;
	
	/** full request **/
	 @Column(name="payload")
	private String notificationDataModel;
	
	
	/**
	 * default constructor
	 */
	public Notification() {
		
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the notificationId
	 */
	public String getNotificationId() {
		return notificationId;
	}


	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}


	/**
	 * @return the dtRequested
	 */
	public Date getDtRequested() {
		return dtRequested;
	}


	/**
	 * @param dtRequested the dtRequested to set
	 */
	public void setDtRequested(Date dtRequested) {
		this.dtRequested = dtRequested;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}


	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	/**
	 * @return the sentDate
	 */
	public Date getSentDate() {
		return sentDate;
	}


	/**
	 * @param sentDate the sentDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}


	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}


	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}


	/**
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}


	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}


	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}


	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}


	/**
	 * @return the notificationDataModel
	 */
	public String getNotificationDataModel() {
		return notificationDataModel;
	}


	/**
	 * @param notificationDataModel the notificationDataModel to set
	 */
	public void setNotificationDataModel(String notificationDataModel) {
		this.notificationDataModel = notificationDataModel;
	}

}
