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
	
	/** UI URL **/
	public final static String LOCAL_HOST_URL="http://localhost:4200/";
	
	/** UI URL **/
	public final static String WILD_CARD_URL="*";
	
	/** UI URL **/
	public final static String SEARCH_QUERY_1="SELECT * FROM legal_opinion_requests WHERE advocate_id=?1 order by requested_on desc";
	
	/** UI URL **/
	public final static String SEARCH_QUERY_4="SELECT * FROM legal_opinion_requests WHERE advocate_id=?1 order by requested_on desc";
	
	/** UI URL **/
	public final static String SEARCH_QUERY_2="SELECT * FROM legal_opinion_requests where requestor_emp_id=?1 order by requested_on desc";
	
	/** UI URL **/
	public final static String SEARCH_QUERY_3="SELECT * FROM legal_opinion_requests order by requested_on desc";
	
	/** UI URL **/
	public final static String ROOT_URL="/bda/legalopinion/v1";
	
	/** UI URL **/
	public final static String ENROLL_URL="/enroll";
	
	
	/** ADVOCATES_LIST_URL **/
	public final static String ADVOCATES_LIST_URL="/advocates";
	
	/** UI URL **/
	public final static String EMPLOYEES_URL="/employees";
	
	/** UI URL **/
	public final static String EMPLOYEE_URL="/employee";
	
	/** UI URL **/
	public final static String PING_URL="/ping";
	/** UI URL **/
	public final static String LOGIN_URL="/login";
	
	/** UI URL **/
	public final static String STATUS_READ_URL="/statuses";
	
	/** UI URL **/
	public final static String ALL_STATUS_URL="/statuses/";
	
	
	/** UI URL **/
	public final static String SUPPORT_DOC_URL="/docs/{id}";
	
	/** UI URL **/
	public final static String ALL_SUPPORT_DOC_URL="/adocs/{caseid}";
	
	/** UI URL **/
	public final static String ADD_SUPPORT_DOC_URL="/doc";
	
	/** UI URL **/
	public final static String LEGAL_OPINIONS_URL="/lopinions";
	/** UI URL **/
	public final static String LEGAL_OPINION_ADD_URL="/lopinion";
	/** UI URL **/
	public final static String LEGAL_OPINION_UPDATE_URL="/lopinions/lopinion/{id}";
	
	
	/** UI URL **/
	public final static String LEGAL_OPINIONS_ALL_COMMENTS_URL="/comments/{id}";
	/** UI URL **/
	public final static String LEGAL_OPINION_ADD_COMMENT_URL="/comments/comment";
	/** UI URL **/
	public final static String LEGAL_OPINION_UPDATE_COMMENT_URL="/comments/comment/{id}";
	
	/** UI URL **/
	public final static String LEGAL_OPINION_DELETE_COMMENT_URL="/comments/comment/{id}";
	
	/** UI URL **/
	public final static String LEGAL_OPINION_READ_COMMENT_URL="/comments/comment/{id}";
	
	
	/** UI URL **/
	public final static String LEGAL_OPINIONS_ALL_TRANS_HISTORY_URL="/transactions/transhistory/{id}";
	
	/** UI URL **/
	public final static String LEGAL_OPINIONS_READ_TRANS_HISTORY_URL="/transhistory/{id}";
	
	
	
	
	
	
	
	/** UI URL **/
	public final static String LEGAL_OPINION_DELETE_URL="/dlopinion/{id}";
	
	/** UI URL **/
	public final static String LEGAL_OPINION_DETAILS_URL="/lopinions/{id}/lopinion";
	
	/** UI URL **/
	public final static String REQUESTED_STATUS="REQUESTED";
	
	/** UI URL **/
	public final static String ASSIGNEDTO_STATUS="ASSIGNEDTO";
	/** UI URL **/
	public final static String CLOSED_STATUS="CLOSED";
	
	/** UI URL **/
	public final static String LAW_OFFICER_ROLEID="law officer";
	
	/** UI URL **/
	public final static String ADVOCATE_ROLEID="advocate";
	
	/** UI URL **/
	public final static String HOD_ROLEID="hod";
	
	/** UI URL **/
	public final static String ADMIN_ROLEID="admin";
	
	/** Lege Legal Opinion Sought **/
	public final static String LEGAL_OPINION_SOUGHT_SMS_MESSAGE="Legal Opinion Sought";
	
	//"
	
	/** Lege Legal Opinion Sought **/
	public final static String LEGAL_OPINION_ASSIGNED_SMS_MESSAGE="Assigned for Legal Opinion";
	
	/** Lege Legal Opinion Sought **/
	public final static String LEGAL_OPINION_REQUESTED_SMS_MESSAGE="Legal Opinion Requested";
	
	//""
	
	/** Lege Legal Opinion Sought **/
	public final static String LEGAL_OPINION_REQUEST_CLOSED_SMS_MESSAGE="Legal Opinion Req Closed";
	
	/** Lege Legal Opinion Sought **/
	public final static String INVALID_DATA_VALUE_ERROR_CODE = "400";
	
	/** Lege Legal Opinion Sought **/
	public final static String USER_ID_KEY = "USERID";

}
