package com.finch.legal.opinion.app.entities;

import java.util.List;

import javax.persistence.Column;
/**
 * department entity
 * @author finch
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "court_cases")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CourtCaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "name")
	/** name **/
	private String name;
	
	/** name **/
	@Column(name = "case_created_date")
	private String case_created_date="";
	
	/** name **/
	@Column(name = "petinitioner_name")
	private String petinitioner_name="";
	
	/** name **/
	@Column(name = "division")
	private String division="";
	
	/** name **/
	@Column(name = "case_no")
	private String case_no="";
	
	/** name **/
	@Column(name = "village")
	private String village="";
	
	/** name **/
	
	@Column(name = "thaluk")
	private String thaluk="";
	
	/** name **/
	@Column(name = "hobli")
	private String hobli="";
	
	/** name **/
	
	@Column(name = "zone")
	private String zone="";
	/** name **/
	@Column(name = "district")
	private String district="";
	/** name **/
	@Column(name = "sob_filed")
	private String sob_filed="";
	/** name **/
	@Column(name = "schedule")
	private String schedule="";
	/** name **/
	@Column(name = "advocate_id")
	private String advocate_id="";
	
	/** name **/
	@Column(name = "section")
	private String section="";
	
	/** name **/
	@Column(name = "case_year")
	private String case_year="";
	/** name **/
	@Column(name = "court_id")
	private String court_id="";
	
	/** name **/
	@Column(name = "compliance_report")
	private String compliance_report="";
	
	
	/** name **/
	@Column(name = "prayer")
	private String prayer="";
	
	/** name **/
	@Column(name = "remark")
	private String remark="";
	
	/** name **/
	@Column(name = "status")
	private String status="";
	
	/** name **/
	@Column(name = "judgement_date")
	private String judgement_date="";
	
	/** name **/
	@Column(name = "action_date")
	private String action_date="";
	
	/** name **/
	@Column(name = "order_type")
	private String order_type="";
	
	/** name **/
	@Column(name = "bill_amount")
	private String bill_amount="";
	
	/** name **/
	@Column(name = "offices")
	private String offices="";
	
	/** name **/
	@Column(name = "case_last_updated")
	private String case_last_updated="";
	
	/** name **/
	@Column(name = "other_respondants")
	private String other_respondants="";
	
	/** name **/
	@Column(name = "type_of_opponent")
	private String type_of_opponent="";
	
	/** name **/
	@Column(name = "opponent_name")
	private String opponent_name="";
	
	/** name **/
	@Column(name = "opponent_advocate_id_number")
	private String opponent_advocate_id_number="";
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	public String getOther_respondants() {
		return other_respondants;
	}

	/**
	 * @param other_respondents the other_respondents to set
	 */
	public void setOther_respondants(String other_respondants) {
		this.other_respondants = other_respondants;
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
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}

