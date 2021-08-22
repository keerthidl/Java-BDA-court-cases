package com.finch.legal.opinion.app.util;

import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.exceptions.JSONConverterException;

/**
 * 
 * @author 91948
 *
 */
public class Tester {
	
	
	/**
	 * default constructor
	 */
	public Tester() {
		
	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		
		LegalOpinionRequestEntity legalRequestEntity = new LegalOpinionRequestEntity();
		
		
		
		try {
			System.out.println(" CHECK THIS "+JSONFormatter.buildStringObject(legalRequestEntity));
		} catch (JSONConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
