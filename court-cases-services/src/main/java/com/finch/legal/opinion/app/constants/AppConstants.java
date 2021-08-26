package com.finch.legal.opinion.app.constants;


/**
 * 
 * @author finch
 *
 */
public class AppConstants {

	
	/** JSON String to Object conversion exception **/
	public final static String JSON_STRING_TO_OBJ_ERR_CODE="JSONTOSTRING";
	
	/** JSON String to Object conversion exception **/
	public final static String STRING_TO_JSONOBJ_ERR_CODE="STRINGTOJSON";
	
	/** JSON String to Object conversion exception **/
	public final static String JSON_STRING_TO_OBJ_ERR_MSG="Error while converting String to JSON object";
	
	/** JSON String to Object conversion exception **/
	public final static String STRING_TO_JSONOBJ_ERR_MSG="Error while converting JSON to String object";
	
	/** court case add URL **/
	public final static String COURT_CASE_ADD_URL="cases/case";
	/** court case add URL **/
	public final static String COURT_CASE_ALL_URL="/cases";
	
	/** court case add URL **/
	public final static String COURT_CASE_PING_URL="/ping";
	
	/** court case add URL **/
	public final static String COURT_CASE_READ_URL="cases/case/{id}";
	
	/** court case add URL **/
	public final static String COURT_CASE_UPDATE_URL="cases/case/{id}";
	
	/** court case add URL **/
	public final static String COURT_CASE_DELETE_URL="cases/case/{id}";
	
	
	/** court case add URL **/
	public final static String COURT_CASES_URL="cases/";
	
	/** court case add URL **/
	public final static String COURT_CASE_BASE_URL="/courtcase/api/v1/";
	
	/** UI URL **/
	public final static String LOCAL_HOST_URL="http://localhost:4200/";
	
	/** WILD_CARD_URL **/
	public final static String WILD_CARD_URL = "*";
	
	
	/** court case add URL **/
	public final static String CONTEMPT_ADD_URL="contempts/contempt";
	/** court case add URL **/
	public final static String ALL_CONTEMPTS_URL="/contempts/{caseid}";
	
	
	/** court case add URL **/
	public final static String CONTEMPT_READ_URL="contempts/contempt/{id}";
	
	/** court case add URL **/
	public final static String CONTEMPT_UPDATE_URL="contempts/contempt/{id}";
	
	/** court case add URL **/
	public final static String CONTEMPT_DELETE_URL="contempts/{id}";
	
	
	
	/** court case add URL **/
	public final static String FILE_MOVEMENT_ADD_URL="files/file";
	/** court case add URL **/
	public final static String ALL_FILE_MOVEMENT_URL="/files/{caseid}";
	
	
	/** court case add URL **/
	public final static String FILE_MOVEMENT_READ_URL="files/file/{id}";
	
	/** court case add URL **/
	public final static String FILE_MOVEMENT_UPDATE_URL="files/file/{id}";
	
	/** court case add URL **/
	public final static String FILE_MOVEMENT_DELETE_URL="files/{id}";
	
	
	
	/** court case add URL **/
	public final static String COMMENT_ADD_URL="comments/comment";
	/** court case add URL **/
	public final static String ALL_COMMENTS_URL="/comments/{caseid}";
	
	
	/** court case add URL **/
	public final static String COMMENT_READ_URL="comments/comment/{id}";
	
	/** court case add URL **/
	public final static String COMMENTT_UPDATE_URL="comments/comment/{id}";
	
	/** court case add URL **/
	public final static String COMMENT_DELETE_URL="comments/comment/{id}";
	
	
	/** court case add URL **/
	public final static String DOCUMENT_ADD_URL="documents/document";
	/** court case add URL **/
	public final static String DOCUMENTS_URL="/documents";
	
	/** court case add URL **/
	public final static String DOCUMENT_URL="/document";
	
	
	/** court case add URL **/
	public final static String DOCUMENT_READ_URL="documents/document/{id}";
	
	/** court case add URL **/
	public final static String DOCUMENT_UPDATE_URL="documents/document/{id}";
	
	/** court case add URL **/
	public final static String DOCUMENT_DELETE_URL="document/{id}";
}
