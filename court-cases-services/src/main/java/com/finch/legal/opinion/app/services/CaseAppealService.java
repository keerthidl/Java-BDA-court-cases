package com.finch.legal.opinion.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.employee.model.CourtCaseAppealDetailsModel;
import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ScheduleDetailsModel;
import com.finch.legal.opinion.app.entities.CaseAppealEntity;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;
import com.finch.legal.opinion.app.entities.PrayerEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;
import com.finch.legal.opinion.app.entities.SectionEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.CaseAppealRepository;
import com.finch.legal.opinion.app.repositories.CourtCasesRepository;
import com.finch.legal.opinion.app.repositories.ScheduleRepository;
import com.finch.legal.opinion.app.repositories.SectionRepository;

@Service
public class CaseAppealService {
	
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(CaseAppealService.class);

	 /** employee repository **/
	@Autowired
	private CaseAppealRepository caseAppealRepository;
	
	 /** employee repository **/
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	
	private DocumentsService documentsService;
	
	
	
	/**
	 * is employee exists
	 */
	public int addCourtCaseAppeal(CourtCaseAppealDetailsModel courtCaseAppealDetailsModel) {
		
		CaseAppealEntity caseAppealEntity = caseAppealRepository.save(transformCaseAppealModelToEntity(courtCaseAppealDetailsModel));
		
		List<ScheduleEntity> lstScheduleEntity = getLstScheduleEntity(courtCaseAppealDetailsModel.getSchedules(),caseAppealEntity);
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			scheduleRepository.save(scheduleEntity);
		}
		
		
		return caseAppealEntity.getId();
		
	}
	
	/**
	 * is employee exists
	 */
	public CourtCaseAppealDetailsModel getCourtAppeaLDetails(String id) {
		
		List<String> lstSections = new ArrayList();
		
		List<String> lstPrayers = new ArrayList();
		
		List<ScheduleDetailsModel> lstScheduleDetailsModel = new ArrayList<ScheduleDetailsModel>();
		
		CourtCaseAppealDetailsModel courtCaseAppealDetailsModel =null;
		
		CaseAppealEntity caseAppealEntity = caseAppealRepository.findById(Integer.parseInt(id)).get();
		
		courtCaseAppealDetailsModel = transformCaseEntityToModel(caseAppealEntity);
		
		List<ScheduleEntity> lstScheduleEntity = scheduleRepository.findByCase_no(caseAppealEntity.getId());
		
		courtCaseAppealDetailsModel.setSchedules(lstScheduleDetailsModel);

	
		
		return courtCaseAppealDetailsModel;
		
	}
	
	/**
	 * is employee exists
	 */
	public int updateCourtCase(CourtCaseAppealDetailsModel courtCaseAppealDetailsModel,String id) {
		
		CaseAppealEntity caseAppealEntity = null;
		
		caseAppealEntity = transformCaseAppealModelToEntity(courtCaseAppealDetailsModel);
		
		caseAppealEntity.setId(Integer.parseInt(id));
		caseAppealEntity = caseAppealRepository.save(caseAppealEntity);
		
		List<ScheduleEntity> lstScheduleEntity = getLstScheduleEntity(courtCaseAppealDetailsModel.getSchedules(),caseAppealEntity);
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			scheduleRepository.save(scheduleEntity);
		}
		
	
		return caseAppealEntity.getId();
		
	}

	
	/**
	 * scheule entities
	 */
	private List<ScheduleEntity> getLstScheduleEntity(List<ScheduleDetailsModel> lstScheduleDetailsModel,CaseAppealEntity caseAppealEntity){
		List<ScheduleEntity> lstScheduleEntity = new ArrayList<ScheduleEntity>();
		
		
		for(ScheduleDetailsModel scheduleDetailsModel:lstScheduleDetailsModel) {
			lstScheduleEntity.add(transformScheduleModelToEntity(scheduleDetailsModel,caseAppealEntity));
		}
		
		return lstScheduleEntity;
	}
	
	/**
	 * convert court case data model to entity
	 */
	private CaseAppealEntity transformCaseAppealModelToEntity(CourtCaseAppealDetailsModel courtCaseAppealDetailsModel) {
		CaseAppealEntity caseAppealEntity = new CaseAppealEntity();
		
		
		caseAppealEntity.setAdvocate_id(""+courtCaseAppealDetailsModel.getAdvocate_id());
		caseAppealEntity.setCase_no(courtCaseAppealDetailsModel.getCase_no());
	//	caseAppealEntity.setJudgement_date(courtCaseAppealDetailsModel.getJudgement_date());
		//caseAppealEntity.setOpponent_advocate_id(courtCaseAppealDetailsModel.getOpponent_advocate_id_name());
		//caseAppealEntity.setOpponent_name(courtCaseAppealDetailsModel.getOpponent_name());
		caseAppealEntity.setOrder_type(courtCaseAppealDetailsModel.getOrder_type());
		caseAppealEntity.setOther_respondants(courtCaseAppealDetailsModel.getOther_respondents());
		caseAppealEntity.setPetitioner_name(courtCaseAppealDetailsModel.getPetitioner_name());
		caseAppealEntity.setAppeal_no(courtCaseAppealDetailsModel.getAppeal_no());
		caseAppealEntity.setCase_id(courtCaseAppealDetailsModel.getCase_id());
		caseAppealEntity.setCourt_id(courtCaseAppealDetailsModel.getCourt_name());
		
		
		caseAppealEntity.setRemark(courtCaseAppealDetailsModel.getRemarks());
		//caseAppealEntity.setType_of_opponent(courtCaseAppealDetailsModel.getType_of_opponent());
		//caseAppealEntity.setStatus(courtCaseAppealDetailsModel.getStatus());
		//caseAppealEntity.setSob_field(courtCaseAppealDetailsModel.getSob_field());
		
		return caseAppealEntity;
	}
	
	/**
	 * convert court case data model to entity
	 */
	private ScheduleEntity transformScheduleModelToEntity(ScheduleDetailsModel scheduleDetailsModel, CaseAppealEntity caseAppealEntity) {
		
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		
		scheduleEntity.setCase_no(caseAppealEntity.getCase_no());
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
	private CourtCaseAppealDetailsModel transformCaseEntityToModel(CaseAppealEntity caseAppealEntity) {
		CourtCaseAppealDetailsModel courtCaseAppealDetailsModel = new CourtCaseAppealDetailsModel();
		
	/**	courtCaseAppealDetailsModel.setAppealId(""+caseAppealEntity.getId());
		
		courtCaseAppealDetailsModel.setAdvocate_id(""+caseAppealEntity.getAdvocate_id());
		courtCaseAppealDetailsModel.setCase_no(caseAppealEntity.getCase_no());
		courtCaseAppealDetailsModel.setJudgement_date(caseAppealEntity.getJudgement_date());
		courtCaseAppealDetailsModel.setOpponent_advocate_id_name(caseAppealEntity.getOpponent_advocate_id_name());
		courtCaseAppealDetailsModel.setOpponent_name(caseAppealEntity.getOpponent_name());
		courtCaseAppealDetailsModel.setOrder_type(caseAppealEntity.getOrder_type());
		courtCaseAppealDetailsModel.setOther_respondents(caseAppealEntity.getOther_respondents());
		courtCaseAppealDetailsModel.setPetitioner_name(caseAppealEntity.getPetitioner_name());
		courtCaseAppealDetailsModel.setAppeal_no(caseAppealEntity.getAppeal_no());
		courtCaseAppealDetailsModel.setCase_id(caseAppealEntity.getCase_id());
		courtCaseAppealDetailsModel.setCourt_name(caseAppealEntity.getCourt_name());
		
		
		courtCaseAppealDetailsModel.setRemarks(caseAppealEntity.getRemarks());
		courtCaseAppealDetailsModel.setType_of_opponent(caseAppealEntity.getType_of_opponent());
		courtCaseAppealDetailsModel.setStatus(caseAppealEntity.getStatus());
		courtCaseAppealDetailsModel.setSob_field(caseAppealEntity.getSob_field());
		**/
		
		return courtCaseAppealDetailsModel;
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
	public CaseAppealEntity updateCourtCaseEntity(CaseAppealEntity caseAppealEntity) {
		
		return caseAppealRepository.save(caseAppealEntity);
		
	}
	
	/**
	 * is employee exists
	 */
	public List<CourtCaseAppealDetailsModel> getAllAppeals() {
		
		//CourtCaseEntity courtCaseEntity = null;
		
		List<CaseAppealEntity> lstAllCourtCases = caseAppealRepository.findAll();
		
		List<CourtCaseAppealDetailsModel> lstCourtCaseDetailsModel = new ArrayList();
		
		if(lstAllCourtCases==null) {
			return null;
		}
		
		for(CaseAppealEntity caseAppealEntity: lstAllCourtCases) {
			lstCourtCaseDetailsModel.add(transformCaseEntityToModel(caseAppealEntity));
		}
		
		return lstCourtCaseDetailsModel;
	}
	
	/**
	 * is employee exists
	 */
	public void deleteCourtCases(String courtCaseId) {
		 
		 caseAppealRepository.deleteById(Integer.parseInt(courtCaseId));
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
