package com.personal.notification.common.logging;

import java.util.HashMap;

/*
 * 
 */
public class LogManager {
	
	/** loggers **/
	private static HashMap hashMapLogger = new HashMap();
	
	/**
	 * get Logger
	 */
	public static Logger getLogger(Class objClass) {
		
		if(hashMapLogger.containsKey(objClass)) {
			return (Logger)hashMapLogger.get(objClass);
		}
		Logger logger = new Logger(objClass);
		hashMapLogger.put(objClass, logger);
		return logger;
	}

}
