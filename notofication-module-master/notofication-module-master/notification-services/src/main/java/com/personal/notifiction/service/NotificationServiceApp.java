package com.personal.notifiction.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * main app
 * @author dvsnk
 *
 */
@SpringBootApplication


public class NotificationServiceApp {

	
	/**
	 * main method
	 * @param args
	 */
	 public static void main(String[] args) {
		 System.out.println(" Running app ..............................................");
		 SpringApplication.run(NotificationServiceApp.class, args);
	}
}
