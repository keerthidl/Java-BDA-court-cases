package com.finch.legal.opinion.app.logs;

import java.util.HashMap;

/**
 * 
 * @author 91948
 *
 */
public class LogManager {

	
	/** Hashtable **/
	private static HashMap hashMapLoggers = new HashMap();
	/**
	 * get log
	 */
	public static AppLogger getLogger(Class appClass) {
		
		if(hashMapLoggers!=null && hashMapLoggers.containsKey(appClass)) {
			return ((AppLogger)hashMapLoggers.get(appClass));
		}else {
			AppLogger appLogger = new AppLogger(appClass);
			hashMapLoggers.put(appClass, appLogger);
			return appLogger;
		}
	}
}
