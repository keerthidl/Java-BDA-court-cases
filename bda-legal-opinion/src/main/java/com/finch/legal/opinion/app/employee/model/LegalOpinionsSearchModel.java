package com.finch.legal.opinion.app.employee.model;

import java.util.HashMap;

/**
 * 
 * @author finch
 *
 */
public class LegalOpinionsSearchModel {

	/** user id **/
	private String userId;
	
	/** field to be sorted **/
	private String sortField;
	
	/** sorting mode **/
	private String sortingMode="ASC";
	
	/** search criteria **/
	private HashMap hashMapSearchCriteria=new HashMap();
	
	
	/**
	 * default constructor
	 */
	public LegalOpinionsSearchModel() {
		
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}


	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}


	/**
	 * @return the sortingMode
	 */
	public String getSortingMode() {
		return sortingMode;
	}


	/**
	 * @param sortingMode the sortingMode to set
	 */
	public void setSortingMode(String sortingMode) {
		this.sortingMode = sortingMode;
	}


	/**
	 * @return the hashMapSearchCriteria
	 */
	public HashMap getHashMapSearchCriteria() {
		return hashMapSearchCriteria;
	}


	/**
	 * @param hashMapSearchCriteria the hashMapSearchCriteria to set
	 */
	public void setHashMapSearchCriteria(HashMap hashMapSearchCriteria) {
		this.hashMapSearchCriteria = hashMapSearchCriteria;
	}
}
