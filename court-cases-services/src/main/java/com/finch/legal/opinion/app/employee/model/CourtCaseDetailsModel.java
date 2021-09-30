package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.CommentEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;

/**
 * 
 * @author dvsnk
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CourtCaseDetailsModel {

	/** employee id **/
	private String case_no="";
	
	/** employee id **/
	private String case_id="";
	

	/** employee id **/
	private String case_year="";
	
	/** employee id **/
	private String case_created_date="";
	
	/** employee id **//** employee id **/
	private String petinitioner_name="";
	
	/** employee id **/
	private String division="";
	
	/** employee id **/
	private String village="";
	
	/** employee id **/
	private String hobli="";
	
	/**case_entered_date **/
	private String case_entered_date="";
	
	
	/**case_entered_date **/
	private String layout="";
	
	
	
	/** employee id **/
	private String thaluk="";
	
	
	/** employee id **/
	private String advocate_name="";
	
	/** employee id **/
	private String parent_case_no="";
	
	/** employee id **/
	private String case_type="";
	

	/** employee id **/
	private String court_name="";
	
	/** employee id **/
	private String other_respondants="";
	
	/** employee id **/
	private String zone="";
	/** employee id **/
	private String district="";
	
	/** employee id **/
	private String order_type="";
	/** employee id **/
	private String sob_filed="";
	/** employee id **/
	private String schedule="";
	/** employee id **/
	private String advocate_id="";
	
	/** employee id **/
	private String court_id="";
	/** employee id **/
	private String compliance_report="";
	/** employee id **/
	private String prayer="";
	/** employee id **/
	private String remark="";
	/** employee id **/
	private String status="";
	/** employee id **/
	private String judgement_date="";
	/** employee id **/
	private String action_date="";

	/** employee id **/
	private String bill_amount="";
	/** employee id **/
	private String offices="";
	/** employee id **/
	private String case_last_updated="";
	/** employee id **/
	private String other_respondents="";
	/** employee id **/
	private String type_of_opponent="";
	
	/** employee id **/
	private String opponent_name="";
	
	
	/** employee id **/
	private String opponent_advocate_id_number="";
	
	
	
   /** order summary **/    
	private String order_summary="";
	
	  /** order summary **/    
	private int  commentCount=0;
	
	  /** order summary **/    
	private int  contemptCount=0;

	  /** order summary **/    
	private int  documentsCount=0;
	
	  /** order summary **/    
	private int  historyCount=0;
	
	
	  /** order summary **/    
	private String  caseType="";
	
	  /** order summary **/    
	private String  parentCaseId="-1";
		
	
	
	/** employee id **/
	private List<ScheduleDetailsModel> schedules=new ArrayList();
	
	/** employee id **/
	private List<ContemptEntity> contempt=new ArrayList();
	
	/** employee id **/
	private List<CaseHistoryEntity> case_history=new ArrayList();
	
	/** employee id **/
	private List<FileMovementEntity> file_movement=new ArrayList();
	
	/** employee id **/
	private List<DocumentEntity> documents=new ArrayList();
	
	/** employee id **/
	private List<String> sections=new ArrayList();
	
	/** employee id **/
	private List<CommentEntity> comments=new ArrayList();
	
	
	/** court case AppealDetails **/
	private CourtCaseAppealDetailsModel courtCaseAppealDetailsModel = null;
	
	
	
	
	/**
	 * @return the order_status
	 */
	public String getOrder_status() {
		return order_status;
	}
	
	

	/**
	 * @return the courtCaseAppealDetailsModel
	 */
	public CourtCaseAppealDetailsModel getCourtCaseAppealDetailsModel() {
		return courtCaseAppealDetailsModel;
	}



	/**
	 * @param courtCaseAppealDetailsModel the courtCaseAppealDetailsModel to set
	 */
	public void setCourtCaseAppealDetailsModel(CourtCaseAppealDetailsModel courtCaseAppealDetailsModel) {
		this.courtCaseAppealDetailsModel = courtCaseAppealDetailsModel;
	}



	/**
	 * @return the layout
	 */
	public String getLayout() {
		return layout;
	}



	/**
	 * @param layout the layout to set
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}



	/**
	 * @param order_status the order_status to set
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	/** name **/
	
	private String order_status="";
	
	
	
	/** default constructor **/
	public CourtCaseDetailsModel() {
		
	}

	/**
	 * @return the file_movement
	 */
	public List<FileMovementEntity> getFile_movement() {
		return file_movement;
	}


	/**
	 * @param file_movement the file_movement to set
	 */
	public void setFile_movement(List<FileMovementEntity> file_movement) {
		this.file_movement = file_movement;
	}



	/**
	 * @param contempt the contempt to set
	 */
	public void setContempt(List<ContemptEntity> contempt) {
		this.contempt = contempt;
	}


	/**
	 * @return the case_history
	 */
	public List<CaseHistoryEntity> getCase_history() {
		return case_history;
	}


	/**
	 * @param case_history the case_history to set
	 */
	public void setCase_history(List<CaseHistoryEntity> case_history) {
		this.case_history = case_history;
	}


	/**
	 * @return the case_created_date
	 */
	public String getCase_created_date() {
		return case_created_date;
	}

	/**
	 * @param case_created_date the case_created_date to set
	 */
	public void setCase_created_date(String case_created_date) {
		this.case_created_date = case_created_date;
	}

	/**
	 * @return the petinitioner_name
	 */
	public String getPetinitioner_name() {
		return petinitioner_name;
	}

	/**
	 * @param petinitioner_name the petinitioner_name to set
	 */
	public void setPetinitioner_name(String petinitioner_name) {
		this.petinitioner_name = petinitioner_name;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	
	/**
	 * @return the caseType
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 * @param caseType the caseType to set
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	/**
	 * @return the parentCaseId
	 */
	public String getParentCaseId() {
		return parentCaseId;
	}

	/**
	 * @param parentCaseId the parentCaseId to set
	 */
	public void setParentCaseId(String parentCaseId) {
		this.parentCaseId = parentCaseId;
	}

	/**
	 * @return the case_no
	 */
	public String getCase_no() {
		return case_no;
	}

	/**
	 * @param case_no the case_no to set
	 */
	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}

	/**
	 * @return the village
	 */
	public String getVillage() {
		return village;
	}

	/**
	 * @param village the village to set
	 */
	public void setVillage(String village) {
		this.village = village;
	}

	/**
	 * @return the thaluk
	 */
	public String getThaluk() {
		return thaluk;
	}

	/**
	 * @param thaluk the thaluk to set
	 */
	public void setThaluk(String thaluk) {
		this.thaluk = thaluk;
	}

	/**
	 * @return the hobli
	 */
	public String getHobli() {
		return hobli;
	}

	/**
	 * @param hobli the hobli to set
	 */
	public void setHobli(String hobli) {
		this.hobli = hobli;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the sob_filed
	 */
	public String getSob_filed() {
		return sob_filed;
	}

	/**
	 * @param sob_filed the sob_filed to set
	 */
	public void setSob_filed(String sob_filed) {
		this.sob_filed = sob_filed;
	}

	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the advocate_id
	 */
	public String getAdvocate_id() {
		return advocate_id;
	}

	/**
	 * @param advocate_id the advocate_id to set
	 */
	public void setAdvocate_id(String advocate_id) {
		this.advocate_id = advocate_id;
	}

	/**
	 * @return the case_year
	 */
	public String getCase_year() {
		return case_year;
	}

	/**
	 * @param case_year the case_year to set
	 */
	public void setCase_year(String case_year) {
		this.case_year = case_year;
	}

	/**
	 * @return the court_id
	 */
	public String getCourt_id() {
		return court_id;
	}

	/**
	 * @param court_id the court_id to set
	 */
	public void setCourt_id(String court_id) {
		this.court_id = court_id;
	}

	/**
	 * @return the compliance_report
	 */
	public String getCompliance_report() {
		return compliance_report;
	}

	/**
	 * @param compliance_report the compliance_report to set
	 */
	public void setCompliance_report(String compliance_report) {
		this.compliance_report = compliance_report;
	}

	/**
	 * @return the prayer
	 */
	public String getPrayer() {
		return prayer;
	}

	/**
	 * @param prayer the prayer to set
	 */
	public void setPrayer(String prayer) {
		this.prayer = prayer;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the judgement_date
	 */
	public String getJudgement_date() {
		return judgement_date;
	}

	/**
	 * @param judgement_date the judgement_date to set
	 */
	public void setJudgement_date(String judgement_date) {
		this.judgement_date = judgement_date;
	}

	/**
	 * @return the action_date
	 */
	public String getAction_date() {
		return action_date;
	}

	/**
	 * @param action_date the action_date to set
	 */
	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}

	/**
	 * @return the order_type
	 */
	public String getOrder_type() {
		return order_type;
	}

	/**
	 * @param order_type the order_type to set
	 */
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	/**
	 * @return the bill_amount
	 */
	public String getBill_amount() {
		return bill_amount;
	}

	/**
	 * @param bill_amount the bill_amount to set
	 */
	public void setBill_amount(String bill_amount) {
		this.bill_amount = bill_amount;
	}

	/**
	 * @return the offices
	 */
	public String getOffices() {
		return offices;
	}

	/**
	 * @param offices the offices to set
	 */
	public void setOffices(String offices) {
		this.offices = offices;
	}

	/**
	 * @return the case_last_updated
	 */
	public String getCase_last_updated() {
		return case_last_updated;
	}

	/**
	 * @param case_last_updated the case_last_updated to set
	 */
	public void setCase_last_updated(String case_last_updated) {
		this.case_last_updated = case_last_updated;
	}

	/**
	 * @return the other_respondents
	 */
	public String getOther_respondents() {
		return other_respondents;
	}

	/**
	 * @param other_respondents the other_respondents to set
	 */
	public void setOther_respondents(String other_respondents) {
		this.other_respondents = other_respondents;
	}

	/**
	 * @return the type_of_opponent
	 */
	public String getType_of_opponent() {
		return type_of_opponent;
	}

	/**
	 * @param type_of_opponent the type_of_opponent to set
	 */
	public void setType_of_opponent(String type_of_opponent) {
		this.type_of_opponent = type_of_opponent;
	}

	/**
	 * @return the opponent_name
	 */
	public String getOpponent_name() {
		return opponent_name;
	}

	/**
	 * @param opponent_name the opponent_name to set
	 */
	public void setOpponent_name(String opponent_name) {
		this.opponent_name = opponent_name;
	}

	/**
	 * @return the opponent_advocate_id_number
	 */
	public String getOpponent_advocate_id_number() {
		return opponent_advocate_id_number;
	}

	/**
	 * @param opponent_advocate_id_number the opponent_advocate_id_number to set
	 */
	public void setOpponent_advocate_id_number(String opponent_advocate_id_number) {
		this.opponent_advocate_id_number = opponent_advocate_id_number;
	}

	/**
	 * @return the schedules
	 */
	public List<ScheduleDetailsModel> getSchedules() {
		return schedules;
	}

	/**
	 * @param schedules the schedules to set
	 */
	public void setSchedules(List<ScheduleDetailsModel> schedules) {
		this.schedules = schedules;
	}


	/**
	 * @return the other_respondants
	 */
	public String getOther_respondants() {
		return other_respondants;
	}


	/**
	 * @param other_respondants the other_respondants to set
	 */
	public void setOther_respondants(String other_respondants) {
		this.other_respondants = other_respondants;
	}

	/**
	 * @return the sections
	 */
	public List<String> getSections() {
		//System.out.println(" JJJJJJJJJJJJJJJJJJJJJJJJJJJ <<SETTER>>"+sections);
		return sections;
	}

	/**
	 * @param sections the sections to set
	 */
	public void setSections(List<String> sections) {
		//System.out.println(" JJJJJJJJJJJJJJJJJJJJJJJJJJJ <<GETTER>>"+sections);
		this.sections = sections;
	}

	/**
	 * @return the case_id
	 */
	public String getCase_id() {
		return case_id;
	}

	/**
	 * @param case_id the case_id to set
	 */
	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}

	/**
	 * @return the order_summary
	 */
	public String getOrder_summary() {
		return order_summary;
	}

	/**
	 * @param order_summary the order_summary to set
	 */
	public void setOrder_summary(String order_summary) {
		this.order_summary = order_summary;
	}

	/**
	 * @return the documents
	 */
	public List<DocumentEntity> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<DocumentEntity> documents) {
		this.documents = documents;
	}

	/**
	 * @return the comments
	 */
	public List<CommentEntity> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	/**
	 * @return the contempt
	 */
	public List<ContemptEntity> getContempt() {
		return contempt;
	}

	/**
	 * @return the advocate_name
	 */
	public String getAdvocate_name() {
		return advocate_name;
	}

	/**
	 * @param advocate_name the advocate_name to set
	 */
	public void setAdvocate_name(String advocate_name) {
		this.advocate_name = advocate_name;
	}

	/**
	 * @return the court_name
	 */
	public String getCourt_name() {
		return court_name;
	}

	/**
	 * @param court_name the court_name to set
	 */
	public void setCourt_name(String court_name) {
		this.court_name = court_name;
	}

	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the contemptCount
	 */
	public int getContemptCount() {
		return contemptCount;
	}

	/**
	 * @param contemptCount the contemptCount to set
	 */
	public void setContemptCount(int contemptCount) {
		this.contemptCount = contemptCount;
	}

	/**
	 * @return the documentsCount
	 */
	public int getDocumentsCount() {
		return documentsCount;
	}

	/**
	 * @param documentsCount the documentsCount to set
	 */
	public void setDocumentsCount(int documentsCount) {
		this.documentsCount = documentsCount;
	}

	/**
	 * @return the historyCount
	 */
	public int getHistoryCount() {
		return historyCount;
	}

	/**
	 * @param historyCount the historyCount to set
	 */
	public void setHistoryCount(int historyCount) {
		this.historyCount = historyCount;
	}

	/**
	 * @return the case_entered_date
	 */
	public String getCase_entered_date() {
		return case_entered_date;
	}

	/**
	 * @param case_entered_date the case_entered_date to set
	 */
	public void setCase_entered_date(String case_entered_date) {
		this.case_entered_date = case_entered_date;
	}

	/**
	 * @return the parent_case_no
	 */
	public String getParent_case_no() {
		return parent_case_no;
	}

	/**
	 * @param parent_case_no the parent_case_no to set
	 */
	public void setParent_case_no(String parent_case_no) {
		this.parent_case_no = parent_case_no;
	}

	/**
	 * @return the case_type
	 */
	public String getCase_type() {
		return case_type;
	}

	/**
	 * @param case_type the case_type to set
	 */
	public void setCase_type(String case_type) {
		this.case_type = case_type;
	}
	
	
}
