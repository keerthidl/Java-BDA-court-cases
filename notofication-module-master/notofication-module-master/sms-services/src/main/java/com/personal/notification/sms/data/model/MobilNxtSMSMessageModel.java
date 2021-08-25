package com.personal.notification.sms.data.model;


/**
 * 
 * @author dvsnk
 *
 */
public class MobilNxtSMSMessageModel {
	
	/** from **/
	private String from;
	
	/** to **/
	private String to;
	
	/** api key **/
	private String accesskey="";
	
	/** txt **/
	private String text;
	
	/** tid **/
	private String tid;
	/**
	 * default constructor
	 */
	public MobilNxtSMSMessageModel() {
		
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the accesskey
	 */
	public String getAccesskey() {
		return accesskey;
	}
	/**
	 * @param accesskey the accesskey to set
	 */
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}
	/**
	 * @param tid the tid to set
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
}
