package com.finch.legal.opinion.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.SplittableRandom;

import org.joda.time.DateTimeFieldType;

/**
 * 
 * @author dvsnk
 *
 */
public class GeneralUtil {

	
	/**
	 * returns random number
	 */
	public static String getRandomNumber() {
		StringBuilder generatedOTP = new StringBuilder();
		SplittableRandom splittableRandom = new SplittableRandom();

		for (int i = 0; i < 4; i++) {
				
			int randomNumber = splittableRandom.nextInt(0, 9);
			generatedOTP.append(randomNumber);
		}
		
		return generatedOTP.toString();
	}
	
	/**
	 * date formatter
	 */
	public static String getTodaysDate() {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	}
	
	
	/**
	 * date formatter
	 */
	public static String getFormattedDate(String strDate) {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(strDate);
	}
	/**
	 * date formatter
	 */
	public static boolean isFutureDate(String strDate) {
		String pattern = "MM-dd-yyyy";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(strDate);
			Date currentDate = new Date();
			return date.before(currentDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * date formatter
	 */
	public static boolean isTodaysDate(String strDate) {
		String pattern = "MM-dd-yyyy";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(strDate);
			Date currentDate = new Date();
			if(date.compareTo(currentDate)==0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * date formatter
	 */
	public static boolean isPreviousDate(String strDate) {
		String pattern = "MM-dd-yyyy";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(strDate);
			Date currentDate = new Date();
			return date.before(currentDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
