package com.finch.legal.opinion.app.util;

import java.text.DateFormat;
import java.text.ParseException;
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
	public static java.util.Date getCurrentDate() {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(simpleDateFormat.format(new java.util.Date()));
		} catch (ParseException e) {
			 return null;
		}
	}
	
	/**
	 * date formatter
	 */
	public static String getTodaysDate() {
		String pattern = "MM-dd-yyyy HH:MM:SS";
		
		DateFormat inputFormat = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss 'GMT' Z", Locale.ENGLISH);
		
		return inputFormat.format(new java.util.Date())+ " (India Standard Time)";
		
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
	public static boolean isFutureDate(Date strDate) {
		String pattern = "MM-dd-yyyy";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(simpleDateFormat.format(strDate));
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
	public static boolean isTodaysDate(Date strDate) {
		String pattern = "MM-dd-yyyy";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(simpleDateFormat.format(strDate));
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
	public static boolean isPreviousDate(Date strDate) {
		String pattern = "MM-dd-yyyy";
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Date date = simpleDateFormat.parse(simpleDateFormat.format(strDate));
			Date currentDate = new Date();
			return date.before(currentDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
