package com.finch.common.logger;


/**
 * 
 * @author dvsnk
 *
 */
public class AppLogger {

	
	/** Class reference **/
	private Class appClass=null;
	/**
	 * default constructor
	 */
	public AppLogger(Class appClass) {
		this.appClass = appClass;
	}
	
	/**
	 * info
	 */
	public void info(String message) {
		System.out.println(appClass + " <<INFO>> "+message);
	}
	
	/**
	 * warn
	 */
	public void warn(String message) {
		System.out.println(appClass + " <<WARN>> "+message);
	}
	
	/**
	 * debug
	 */
	public void debug(String message) {
		System.out.println(appClass + " <<debug>> "+message);
	}
	
	/**
	 * fatal
	 */
	public void fatal(String message) {
		System.out.println(appClass + " <<fatal>> "+message);
	}
	
	/**
	 * fatal
	 */
	public void error(String message) {
		System.out.println(appClass + " <<error>> "+message);
	}
	
	/**
	 * fatal
	 */
	public void error(String message, Exception e) {
		System.out.println(appClass + " <<error>> "+message);
	}
}
