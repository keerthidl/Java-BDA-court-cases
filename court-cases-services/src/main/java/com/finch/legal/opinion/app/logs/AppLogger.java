package com.finch.legal.opinion.app.logs;


/**
 * 
 * @author 91948
 *
 */
public class AppLogger {

	 /** class **/
	private static Class appClass;
	
	/**
	 * Default constructore
	 */
	public AppLogger(Class appClass) {
		this.appClass = appClass;
	}
	
	/**
	 * info
	 */
	public void info(String message) {
		
		System.out.println("INFO:  "+ appClass.getName()+" :  "+message);
	}
	
	/**
	 * info
	 */
	public void debug(String message) {
		System.out.println("DEBUG:  "+ appClass.getName()+" :  "+message);
	}
	
	/**
	 * info
	 */
	public void warning(String message) {
		System.out.println("WARNING:  "+ appClass.getName()+" :  "+message);
	}
	/**
	 * info
	 */
	public void fatal(String message) {
		System.out.println("FATAL:  "+ appClass.getName()+" :  "+message);
	}
	/**
	 * info
	 */
	public void error(String message) {
		System.out.println("ERROR:  "+ appClass.getName()+" :  "+message);
	}
	
	/**
	 * info
	 */
	public void error(String message,Exception exception) {
		System.out.println("ERROR:  "+ appClass.getName()+" :  "+message);
		//exception.printStackTrace();
	}
}
