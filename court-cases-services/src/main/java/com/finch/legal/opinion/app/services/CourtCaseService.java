package com.finch.legal.opinion.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ScheduleDetailsModel;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.CaseHistoryRepository;
import com.finch.legal.opinion.app.repositories.CourtCasesRepository;
import com.finch.legal.opinion.app.repositories.ScheduleRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class CourtCaseService {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(CourtCaseService.class);

	 /** employee repository **/
	@Autowired
	private CourtCasesRepository courtCasesRepository;
	
	 /** employee repository **/
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	 /** employee repository **/
	@Autowired
	private CaseHistoryService caseHistoryService;
	
	 /** employee repository **/
	@Autowired
	private ContemptService contenptyService;
	
	/**
	 * is employee exists
	 */
	public CourtCaseEntity getCourtCaseEntity(String caseId) {
		
		return courtCasesRepository.findById(Integer.parseInt(caseId)).get();
		
	}
	
	/**
	 * is employee exists
	 */
	public int addCourtCase(CourtCaseDetailsModel courtCaseDetailsModel) {
		
		CourtCaseEntity courtCaseEntity = courtCasesRepository.save(transformCaseModelToEntity(courtCaseDetailsModel));
		
		List<ScheduleEntity> lstScheduleEntity = getLstScheduleEntity(courtCaseDetailsModel.getSchedules(),courtCaseEntity);
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			scheduleRepository.save(scheduleEntity);
		}
		
		caseHistoryService.addCaseHistory(buildCaseHistory(courtCaseEntity.getId(),courtCaseDetailsModel.getCase_no(),"Case Created","case Created","ADMIN"));
		return courtCaseEntity.getId();
		
	}
	
	/**
	 * is employee exists
	 */
	public CourtCaseDetailsModel getCourtCaseDetails(String id) {
		
		List<ScheduleDetailsModel> lstScheduleDetailsModel = new ArrayList<ScheduleDetailsModel>();
		
	
		
		CourtCaseEntity courtCaseEntity = courtCasesRepository.findById(Integer.parseInt(id)).get();
		
		List<ScheduleEntity> lstScheduleEntity = scheduleRepository.findByCase_no(courtCaseEntity.getCase_no());
		
		List<CaseHistoryEntity> lstCaseHistoryEntity = caseHistoryService.getLstCaseHistoryEntity(""+courtCaseEntity.getId());
		
		List<ContemptEntity> lstContemptEntity = contenptyService.getAllRecords(""+courtCaseEntity.getId());
		
		CourtCaseDetailsModel courtCaseDetailsModel = transformCaseEntityToModel(courtCaseEntity);
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			lstScheduleDetailsModel.add(transformScheduleEntityToModel(scheduleEntity));
			
		}
		courtCaseDetailsModel.setSchedules(lstScheduleDetailsModel);
		courtCaseDetailsModel.setContempt(lstContemptEntity);
		courtCaseDetailsModel.setCase_history(lstCaseHistoryEntity);
		
		
	
		
		return courtCaseDetailsModel;
		
	}
	
	/**
	 * is employee exists
	 */
	public int updateCourtCase(CourtCaseDetailsModel courtCaseDetailsModel,String id) {
		
		CourtCaseEntity courtCaseEntity = null;
		
		courtCaseEntity = transformCaseModelToEntity(courtCaseDetailsModel);
		
		courtCaseEntity.setId(Integer.parseInt(id));
		courtCaseEntity = courtCasesRepository.save(courtCaseEntity);
		
		List<ScheduleEntity> lstScheduleEntity = getLstScheduleEntity(courtCaseDetailsModel.getSchedules(),courtCaseEntity);
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			scheduleRepository.save(scheduleEntity);
		}
		
		caseHistoryService.addCaseHistory(buildCaseHistory(courtCaseEntity.getId(),courtCaseDetailsModel.getCase_no(),"Case Updated","Case Updated","ADMIN"));
		return courtCaseEntity.getId();
		
	}

	
	/**
	 * scheule entities
	 */
	private List<ScheduleEntity> getLstScheduleEntity(List<ScheduleDetailsModel> lstScheduleDetailsModel,CourtCaseEntity courtCaseEntity){
		List<ScheduleEntity> lstScheduleEntity = new ArrayList<ScheduleEntity>();
		
		
		for(ScheduleDetailsModel scheduleDetailsModel:lstScheduleDetailsModel) {
			lstScheduleEntity.add(transformScheduleModelToEntity(scheduleDetailsModel,courtCaseEntity));
		}
		
		return lstScheduleEntity;
	}
	
	/**
	 * convert court case data model to entity
	 */
	private CourtCaseEntity transformCaseModelToEntity(CourtCaseDetailsModel courtCaseDetailsModel) {
		CourtCaseEntity courtCaseEntity = new CourtCaseEntity();
		
		courtCaseEntity.setAction_date(courtCaseDetailsModel.getAction_date());
		courtCaseEntity.setAdvocate_id(""+courtCaseDetailsModel.getAdvocate_id());
		courtCaseEntity.setBill_amount(courtCaseDetailsModel.getBill_amount());
		courtCaseEntity.setCase_created_date(courtCaseDetailsModel.getCase_created_date());
		courtCaseEntity.setCase_last_updated(courtCaseDetailsModel.getCase_last_updated());
		courtCaseEntity.setCase_no(courtCaseDetailsModel.getCase_no());
		courtCaseEntity.setCase_year(courtCaseDetailsModel.getCompliance_report());
		courtCaseEntity.setCompliance_report(courtCaseDetailsModel.getCompliance_report());
		courtCaseEntity.setCourt_id(courtCaseDetailsModel.getCourt_id());
		courtCaseEntity.setDistrict(courtCaseDetailsModel.getDistrict());
		courtCaseEntity.setDivision(courtCaseDetailsModel.getDivision());
		courtCaseEntity.setHobli(courtCaseDetailsModel.getHobli());
		courtCaseEntity.setJudgement_date(courtCaseDetailsModel.getJudgement_date());
	//	courtCaseEntity.setName(courtCaseDetailsModel.getName());
		courtCaseEntity.setOffices(courtCaseDetailsModel.getOffices());
		courtCaseEntity.setOpponent_advocate_id_number(courtCaseDetailsModel.getOpponent_advocate_id_number());
		courtCaseEntity.setOpponent_name(courtCaseDetailsModel.getOpponent_name());
		courtCaseEntity.setOrder_type(courtCaseDetailsModel.getOrder_type());
		courtCaseEntity.setOther_respondents(courtCaseDetailsModel.getOther_respondents());
		courtCaseEntity.setPetinitioner_name(courtCaseDetailsModel.getPetinitioner_name());
		courtCaseEntity.setPrayer(courtCaseDetailsModel.getPrayer());
		courtCaseEntity.setRemark(courtCaseDetailsModel.getRemark());
		courtCaseEntity.setSchedule(courtCaseDetailsModel.getSchedule());
		courtCaseEntity.setSection(courtCaseDetailsModel.getSection());
		courtCaseEntity.setSob_filed(courtCaseDetailsModel.getSob_filed());
		courtCaseEntity.setStatus(courtCaseDetailsModel.getStatus());
		courtCaseEntity.setThaluk(courtCaseDetailsModel.getThaluk());
		courtCaseEntity.setType_of_opponent(courtCaseDetailsModel.getType_of_opponent());
		courtCaseEntity.setVillage(courtCaseDetailsModel.getVillage());
		courtCaseEntity.setZone(courtCaseDetailsModel.getZone());
		
		return courtCaseEntity;
	}
	
	/**
	 * convert court case data model to entity
	 */
	private ScheduleEntity transformScheduleModelToEntity(ScheduleDetailsModel scheduleDetailsModel, CourtCaseEntity courtCaseEntity) {
		
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		
		scheduleEntity.setCase_no(courtCaseEntity.getCase_no());
		scheduleEntity.setEast(scheduleDetailsModel.getEast());
		scheduleEntity.setExtent(scheduleDetailsModel.getExtent());
		scheduleEntity.setNorth(scheduleDetailsModel.getNorth());
		scheduleEntity.setSite_no(scheduleDetailsModel.getSite_no());
		scheduleEntity.setSouth(scheduleDetailsModel.getSouth());
		scheduleEntity.setSurvey_no(scheduleDetailsModel.getSurvey_no());
		scheduleEntity.setType(scheduleDetailsModel.getType());
		scheduleEntity.setWest(scheduleDetailsModel.getWest());
		
		return scheduleEntity;
		
	}
	
	
	/**
	 * convert court case data model to entity
	 */
	private CourtCaseDetailsModel transformCaseEntityToModel(CourtCaseEntity courtCaseEntity) {
		CourtCaseDetailsModel courtCaseDetailsModel = new CourtCaseDetailsModel();
		
		courtCaseDetailsModel.setAction_date(courtCaseEntity.getAction_date());
		courtCaseDetailsModel.setAdvocate_id(""+courtCaseEntity.getAdvocate_id());
		courtCaseDetailsModel.setBill_amount(courtCaseEntity.getBill_amount());
		courtCaseDetailsModel.setCase_created_date(courtCaseEntity.getCase_created_date());
		courtCaseDetailsModel.setCase_last_updated(courtCaseEntity.getCase_last_updated());
		courtCaseDetailsModel.setCase_no(courtCaseEntity.getCase_no());
		courtCaseDetailsModel.setCase_year(courtCaseEntity.getCompliance_report());
		courtCaseDetailsModel.setCompliance_report(courtCaseEntity.getCompliance_report());
		courtCaseDetailsModel.setCourt_id(courtCaseEntity.getCourt_id());
		courtCaseDetailsModel.setDistrict(courtCaseEntity.getDistrict());
		courtCaseDetailsModel.setDivision(courtCaseEntity.getDivision());
		courtCaseDetailsModel.setHobli(courtCaseEntity.getHobli());
		courtCaseDetailsModel.setJudgement_date(courtCaseEntity.getJudgement_date());
		//courtCaseDetailsModel.setName(courtCaseEntity.getName());
		courtCaseDetailsModel.setOffices(courtCaseEntity.getOffices());
		courtCaseDetailsModel.setOpponent_advocate_id_number(courtCaseEntity.getOpponent_advocate_id_number());
		courtCaseDetailsModel.setOpponent_name(courtCaseEntity.getOpponent_name());
		courtCaseDetailsModel.setOrder_type(courtCaseEntity.getOrder_type());
		courtCaseDetailsModel.setOther_respondents(courtCaseEntity.getOther_respondents());
		courtCaseDetailsModel.setPetinitioner_name(courtCaseEntity.getPetinitioner_name());
		courtCaseDetailsModel.setPrayer(courtCaseEntity.getPrayer());
		courtCaseDetailsModel.setRemark(courtCaseEntity.getRemark());
		courtCaseDetailsModel.setSchedule(courtCaseEntity.getSchedule());
		courtCaseDetailsModel.setSection(courtCaseEntity.getSection());
		courtCaseDetailsModel.setSob_filed(courtCaseEntity.getSob_filed());
		courtCaseDetailsModel.setStatus(courtCaseEntity.getStatus());
		courtCaseDetailsModel.setThaluk(courtCaseEntity.getThaluk());
		courtCaseDetailsModel.setType_of_opponent(courtCaseEntity.getType_of_opponent());
		courtCaseDetailsModel.setVillage(courtCaseEntity.getVillage());
		courtCaseDetailsModel.setZone(courtCaseEntity.getZone());
		
		return courtCaseDetailsModel;
	}
	
	/**
	 * convert court case data model to entity
	 */
	private ScheduleDetailsModel transformScheduleEntityToModel(ScheduleEntity scheduleEntity) {
		
		ScheduleDetailsModel scheduleDetailsModel = new ScheduleDetailsModel();
		
		scheduleDetailsModel.setEast(scheduleEntity.getEast());
		scheduleDetailsModel.setExtent(scheduleEntity.getExtent());
		scheduleDetailsModel.setNorth(scheduleEntity.getNorth());
		scheduleDetailsModel.setSite_no(scheduleEntity.getSite_no());
		scheduleDetailsModel.setSouth(scheduleEntity.getSouth());
		scheduleDetailsModel.setSurvey_no(scheduleEntity.getSurvey_no());
		scheduleDetailsModel.setType(scheduleEntity.getType());
		scheduleDetailsModel.setWest(scheduleEntity.getWest());
		
		return scheduleDetailsModel;
		
	}
	
	/**
	 * is employee exists
	 */
	public CourtCaseEntity updateCourtCaseEntity(CourtCaseEntity courtCaseEntity) {
		
		return courtCasesRepository.save(courtCaseEntity);
		
	}
	
	/**
	 * is employee exists
	 */
	public List<CourtCaseDetailsModel> getAllCourtCases() {
		
		//CourtCaseEntity courtCaseEntity = null;
		
		List<CourtCaseEntity> lstAllCourtCases = courtCasesRepository.findAll();
		
		List<CourtCaseDetailsModel> lstCourtCaseDetailsModel = new ArrayList();
		
		if(lstAllCourtCases==null) {
			return null;
		}
		
		for(CourtCaseEntity courtCaseEntity: lstAllCourtCases) {
			lstCourtCaseDetailsModel.add(transformCaseEntityToModel(courtCaseEntity));
		}
		
		return lstCourtCaseDetailsModel;
	}
	
	/**
	 * is employee exists
	 */
	public void deleteCourtCases(String courtCaseId) {
		 
		 courtCasesRepository.deleteById(Integer.parseInt(courtCaseId));
	}
	
	/**
	 * court case history
	 */
	private CaseHistoryEntity buildCaseHistory(int caseMainId,String caseNumber,String activity,String caseDetails,String dept) {
		
		CaseHistoryEntity caseHistoryEntity = new CaseHistoryEntity();
		caseHistoryEntity.setCase_activity(activity);
		caseHistoryEntity.setCase_details(caseDetails);
		caseHistoryEntity.setCase_id(caseNumber);
		caseHistoryEntity.setCase_main_id(caseMainId);
		caseHistoryEntity.setCreated_date(caseNumber);
		caseHistoryEntity.setUser_dept(dept);
		return caseHistoryEntity;
	}
}
