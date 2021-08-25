package com.personal.notification.common.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author dvsnk
 *
 */
public class Logger {
	
	/** class **/
	private Class objClass = null;
	
	/**
	 * default constructor
	 */
	public Logger(Class objClass) {
		this.objClass = objClass;
	}
	
	/**
	 * info
	 */
	public void info(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(objClass.getName()+"<<Time >> "+dtf.format(now) +" << INFO >> "+message);
	}
	
	/**
	 * info
	 */
	public void error(Exception exception) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(objClass.getName()+"<<Time >> "+dtf.format(now) +" << ERROR >> "+exception.getMessage());
	}
	
	/**
	 * info
	 */
	public void debug(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(objClass.getName()+"<<Time >> "+dtf.format(now) +" << DEBUG >> "+message);
	}
	
	/**
	 * info
	 */
	public void warn(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(objClass.getName()+"<<Time >> "+dtf.format(now) +" << WARN >> "+message);
	}


}
