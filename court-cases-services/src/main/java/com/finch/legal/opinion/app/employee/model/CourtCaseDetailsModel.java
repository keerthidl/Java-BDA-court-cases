package com.finch.legal.opinion.app.employee.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;

/**
 * 
 * @author dvsnk
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourtCaseDetailsModel {

	/** employee id **/
	private String case_no="";
	

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
	
	
	
	/** employee id **/
	private String thaluk="";
	
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
	private String section="";
	
	
	
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
	
	/** employee id **/
	private List<ScheduleDetailsModel> schedules=new ArrayList();
	
	/** employee id **/
	private List<ContemptEntity> contempt=new ArrayList();
	
	/** employee id **/
	private List<CaseHistoryEntity> case_history=new ArrayList();
	
	/** employee id **/
	private List<CaseHistoryEntity> file_movement=new ArrayList();
	
	/** employee id **/
	private List<CaseHistoryEntity> document=new ArrayList();
	
	/** employee id **/
	private List<String> sections=new ArrayList();
	
	/** employee id **/
	private List<String> prayers=new ArrayList();
	
	/** default constructor **/
	public CourtCaseDetailsModel() {
		
	}

	/**
	 * @return the file_movement
	 */
	public List<CaseHistoryEntity> getFile_movement() {
		return file_movement;
	}


	/**
	 * @param file_movement the file_movement to set
	 */
	public void setFile_movement(List<CaseHistoryEntity> file_movement) {
		this.file_movement = file_movement;
	}


	/**
	 * @return the document
	 */
	public List<CaseHistoryEntity> getDocument() {
		return document;
	}


	/**
	 * @param document the document to set
	 */
	public void setDocument(List<CaseHistoryEntity> document) {
		this.document = document;
	}


	/**
	 * @return the contempt
	 */
	public List<ContemptEntity> getContempt() {
		return contempt;
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
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
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
		return sections;
	}

	/**
	 * @param sections the sections to set
	 */
	public void setSections(List<String> sections) {
		this.sections = sections;
	}

	/**
	 * @return the prayers
	 */
	public List<String> getPrayers() {
		return prayers;
	}

	/**
	 * @param prayers the prayers to set
	 */
	public void setPrayers(List<String> prayers) {
		this.prayers = prayers;
	}
	
}
