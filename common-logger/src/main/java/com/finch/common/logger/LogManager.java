package com.finch.common.logger;

import java.util.HashMap;

/**
 * lpg manager
 * @author finch
 *
 */
public class LogManager {
	
	/** App Loggers **/
	private static HashMap hashMapAppLoggers = new HashMap();
	
	
	/**
	 * returns app loggers
	 */
	public static AppLogger getLogger(Class appClass) {
		
		if(hashMapAppLoggers!=null && hashMapAppLoggers.containsKey(appClass)) {
			return ((AppLogger)hashMapAppLoggers.get(appClass));
		}
		AppLogger appLogger = new AppLogger(appClass);
		
		hashMapAppLoggers.put(appClass, appLogger);
		return appLogger;
	}
	

}
