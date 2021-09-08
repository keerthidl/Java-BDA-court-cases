package com.finch.legal.opinion.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finch.legal.opinion.app.employee.model.CourtCaseDetailsModel;
import com.finch.legal.opinion.app.employee.model.ScheduleDetailsModel;
import com.finch.legal.opinion.app.entities.AdvocatesEntity;
import com.finch.legal.opinion.app.entities.CaseHistoryEntity;
import com.finch.legal.opinion.app.entities.CommentEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.CourtCaseEntity;
import com.finch.legal.opinion.app.entities.CourtEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;
import com.finch.legal.opinion.app.entities.PrayerEntity;
import com.finch.legal.opinion.app.entities.ScheduleEntity;
import com.finch.legal.opinion.app.entities.SectionEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.CourtCasesRepository;
import com.finch.legal.opinion.app.repositories.ScheduleRepository;
import com.finch.legal.opinion.app.util.GeneralUtil;

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
	
	 /** employee repository **/
	@Autowired
	private SectionsService sectionsService;
	
	
	 /** employee repository **/
	@Autowired
	private CommentService commentService;
	
	 /** employee repository **/
	@Autowired
	private PrayersService prayerService;
	
	
	 /** employee repository **/
	@Autowired
	private FileMovementService fileMovementService;
		
		 /** employee repository **/
	@Autowired
	private DocumentsService documentsService;

	 /** employee repository **/
	@Autowired
	private AdvocatesService advocatesService;
	
	 /** employee repository **/
	@Autowired
	private CourtService courtService;
	
	
	
	/**
	 * is employee exists
	 */
	public CourtCaseEntity getCourtCaseEntity(String caseId) {
		long startTime = System.currentTimeMillis();
		CourtCaseEntity courtCaseEntity = courtCasesRepository.findById(Integer.parseInt(caseId));
		long endTime = System.currentTimeMillis();
	
		LOG.info(" TIME TAKEN FOR Case READ "+((endTime-startTime)/1000));
		return courtCaseEntity;
		
	}
	
	/**
	 * is employee exists
	 */
	@Transactional
	public int addCourtCase(CourtCaseDetailsModel courtCaseDetailsModel) {
		
		long startTime = System.currentTimeMillis();
		SectionEntity sectionEntity = null;
		
		PrayerEntity prayerEntity = null;
		
	
		CourtCaseEntity courtCaseEntity = courtCasesRepository.save(transformCaseModelToEntity(courtCaseDetailsModel,new CourtCaseEntity()));
		
			
		List<ScheduleEntity> lstScheduleEntity = getLstScheduleEntity(courtCaseDetailsModel.getSchedules(),courtCaseEntity);
		
	
		scheduleRepository.saveAll(lstScheduleEntity);
		
		
		List<String> lstSections = courtCaseDetailsModel.getSections();
		
	
		List<SectionEntity> lstSectionsEntities = new ArrayList<>();
	
		for(String scetionId:lstSections) {
			sectionEntity = new SectionEntity();
			sectionEntity.setCase_main_id(courtCaseEntity.getId());	
			sectionEntity.setSectionId(scetionId);
			lstSectionsEntities.add(sectionEntity);
			
		}
		
		
		sectionsService.addSection(lstSectionsEntities);
		
		
		long endTime = System.currentTimeMillis();
		
		LOG.info(" TIME TAKEN IS "+((endTime-startTime)/1000));
		return courtCaseEntity.getId();
		
	}
	
	/**
	 * is employee exists
	 */
	public CourtCaseDetailsModel getCourtCaseDetails(String id) {
		
		List<String> lstSections = new ArrayList();
		
		List<String> lstPrayers = new ArrayList();
		AdvocatesEntity advocateEntity=null;
		
	
		List<ScheduleDetailsModel> lstScheduleDetailsModel = new ArrayList<ScheduleDetailsModel>();
		
		CourtCaseEntity courtCaseEntity = courtCasesRepository.findById(Integer.parseInt(id));
	
		List<ScheduleEntity> lstScheduleEntity = scheduleRepository.findByCase_no(Integer.parseInt(id));
	
		List<CaseHistoryEntity> lstCaseHistoryEntity = caseHistoryService.getLstCaseHistoryEntity(""+courtCaseEntity.getId());
		
		
		List<CommentEntity> lstCommentEntity = commentService.getAllRecords(""+courtCaseEntity.getId());
		
		
		
		List<ContemptEntity> lstContemptEntity = contenptyService.getAllRecords(""+courtCaseEntity.getId());
		
		
		
		List<DocumentEntity> lstDocumentEntity = documentsService.getAllRecords(""+courtCaseEntity.getId());
		
		
		
		List<FileMovementEntity> lsFileMovementEntity = fileMovementService.getAllRecords(""+courtCaseEntity.getId());
		
	
		
		CourtCaseDetailsModel courtCaseDetailsModel = transformCaseEntityToModel(courtCaseEntity);
		courtCaseDetailsModel.setCase_id(""+courtCaseEntity.getId());
	
		
		try {
			advocateEntity =      advocatesService.getAdvocatesEntity(courtCaseEntity.getAdvocate_id());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		CourtEntity courtEntity = courtService.getCourtEntity(courtCaseEntity.getCourt_id());
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			
			lstScheduleDetailsModel.add(transformScheduleEntityToModel(scheduleEntity));
			
		}
		
	
		List<SectionEntity> sections = sectionsService.getAllRecords(""+id);
		
			
		for(SectionEntity sectionEntity:sections) {
			
			lstSections.add(sectionEntity.getSectionId());
		}
		
		
		courtCaseDetailsModel.setSchedules(lstScheduleDetailsModel);
		courtCaseDetailsModel.setCase_history(lstCaseHistoryEntity);
		courtCaseDetailsModel.setSections(lstSections);
		courtCaseDetailsModel.setContempt(lstContemptEntity);
		courtCaseDetailsModel.setDocuments(lstDocumentEntity);
		courtCaseDetailsModel.setFile_movement(lsFileMovementEntity);
		courtCaseDetailsModel.setSchedules(lstScheduleDetailsModel);
		courtCaseDetailsModel.setComments(lstCommentEntity);
		
		if(advocateEntity!=null) {
			courtCaseDetailsModel.setAdvocate_name(advocateEntity.getName());
		}
		
		if(courtEntity!=null) {
			courtCaseDetailsModel.setCourt_name(courtEntity.getName());
		}
		
		return courtCaseDetailsModel;
		
	}
	
	/**
	 * is employee exists
	 */
	@Transactional
	public int updateCourtCase(CourtCaseDetailsModel courtCaseDetailsModel,String id) {
		
		CourtCaseEntity courtCaseEntity = null;
		
		courtCaseEntity = courtCasesRepository.findById(Integer.parseInt(id));
		
		courtCaseEntity = transformCaseModelToEntity(courtCaseDetailsModel,courtCaseEntity);
		
		courtCaseEntity.setId(Integer.parseInt(id));
		courtCaseEntity = courtCasesRepository.save(courtCaseEntity);
		
		List<ScheduleEntity> lstScheduleEntity = getLstScheduleEntity(courtCaseDetailsModel.getSchedules(),courtCaseEntity);
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			scheduleRepository.save(scheduleEntity);
		}
		
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
	private CourtCaseEntity transformCaseModelToEntity(CourtCaseDetailsModel courtCaseDetailsModel,CourtCaseEntity courtCaseEntity) {
		
		
		
		if(courtCaseDetailsModel.getAction_date()!=null && courtCaseDetailsModel.getAction_date().trim().length()>0) {
			courtCaseEntity.setAction_date(courtCaseDetailsModel.getAction_date());
		}
		if(courtCaseDetailsModel.getAdvocate_id()!=null && courtCaseDetailsModel.getAdvocate_id().trim().length()>0) {
			courtCaseEntity.setAdvocate_id(""+courtCaseDetailsModel.getAdvocate_id());
		}
		if(courtCaseDetailsModel.getBill_amount()!=null && courtCaseDetailsModel.getBill_amount().trim().length()>0) {
			courtCaseEntity.setBill_amount(courtCaseDetailsModel.getBill_amount());
		}
		
		if(courtCaseDetailsModel.getCase_created_date()!=null && courtCaseDetailsModel.getCase_created_date().trim().length()>0) {
			courtCaseEntity.setCase_created_date(courtCaseDetailsModel.getCase_created_date());
		}
		
		if(courtCaseDetailsModel.getCase_last_updated()!=null && courtCaseDetailsModel.getCase_last_updated().trim().length()>0) {
			courtCaseEntity.setCase_last_updated(courtCaseDetailsModel.getCase_last_updated());
		}
		if(courtCaseDetailsModel.getCase_no()!=null && courtCaseDetailsModel.getCase_no().trim().length()>0) {
			courtCaseEntity.setCase_no(courtCaseDetailsModel.getCase_no());
		}
		if(courtCaseDetailsModel.getCompliance_report()!=null && courtCaseDetailsModel.getCompliance_report().trim().length()>0) {
			courtCaseEntity.setCase_year(courtCaseDetailsModel.getCompliance_report());
		}
		
		if(courtCaseDetailsModel.getOrder_status()!=null && courtCaseDetailsModel.getOrder_status().trim().length()>0) {
			courtCaseEntity.setOrder_status(courtCaseDetailsModel.getOrder_status());
		}
		
		if(courtCaseDetailsModel.getOrder_summary()!=null && courtCaseDetailsModel.getOrder_summary().trim().length()>0) {
			courtCaseEntity.setOrder_summary(courtCaseDetailsModel.getOrder_summary());
		}
		
		
		courtCaseEntity.setCase_last_updated(GeneralUtil.getTodaysDate());
		
		LOG.info(" COURT CASE COURT ID "+courtCaseDetailsModel.getCourt_id());
		if(courtCaseDetailsModel.getCourt_id()!=null && courtCaseDetailsModel.getCourt_id().trim().length()>0) {
			courtCaseEntity.setCourt_id(courtCaseDetailsModel.getCourt_id());
		}
		
		if(courtCaseDetailsModel.getDistrict()!=null && courtCaseDetailsModel.getDistrict().trim().length()>0) {
		
			courtCaseEntity.setDistrict(courtCaseDetailsModel.getDistrict());
		}
		
		/*
		 * if(courtCaseDetailsModel.getDivision()!=null &&
		 * courtCaseDetailsModel.getDivision().trim().length()>0) {
		 * courtCaseEntity.setDivision(courtCaseDetailsModel.getDivision()); }
		 */
		
		if(courtCaseDetailsModel.getHobli()!=null && courtCaseDetailsModel.getHobli().trim().length()>0) {
			courtCaseEntity.setHobli(courtCaseDetailsModel.getHobli());
		}
		
		if(courtCaseDetailsModel.getJudgement_date()!=null && courtCaseDetailsModel.getJudgement_date().trim().length()>0) {
			courtCaseEntity.setJudgement_date(courtCaseDetailsModel.getJudgement_date());
		}

		/*
		 * if(courtCaseDetailsModel.getOffices()!=null &&
		 * courtCaseDetailsModel.getOffices().trim().length()>0) {
		 * courtCaseEntity.setOffices(courtCaseDetailsModel.getOffices()); }
		 */
		
		if(courtCaseDetailsModel.getOpponent_advocate_id_number()!=null && courtCaseDetailsModel.getOpponent_advocate_id_number().trim().length()>0) {
			courtCaseEntity.setOpponent_advocate_id_number(courtCaseDetailsModel.getOpponent_advocate_id_number());
		}
		
		if(courtCaseDetailsModel.getOpponent_name()!=null && courtCaseDetailsModel.getOpponent_name().trim().length()>0) {
			courtCaseEntity.setOpponent_name(courtCaseDetailsModel.getOpponent_name());
		}
		if(courtCaseDetailsModel.getOrder_type()!=null && courtCaseDetailsModel.getOrder_type().trim().length()>0) {
			courtCaseEntity.setOrder_type(courtCaseDetailsModel.getOrder_type());
		}
		
		if(courtCaseDetailsModel.getOther_respondants()!=null && courtCaseDetailsModel.getOther_respondants().trim().length()>0) {
			courtCaseEntity.setOther_respondants(courtCaseDetailsModel.getOther_respondants());
		}
		
		if(courtCaseDetailsModel.getPetinitioner_name()!=null && courtCaseDetailsModel.getPetinitioner_name().trim().length()>0) {
			courtCaseEntity.setPetinitioner_name(courtCaseDetailsModel.getPetinitioner_name());
		}
		if(courtCaseDetailsModel.getPrayer()!=null && courtCaseDetailsModel.getPrayer().trim().length()>0) {
			courtCaseEntity.setPrayer(courtCaseDetailsModel.getPrayer());
		}
		if(courtCaseDetailsModel.getRemark()!=null && courtCaseDetailsModel.getRemark().trim().length()>0) {
			courtCaseEntity.setRemark(courtCaseDetailsModel.getRemark());
		}
		if(courtCaseDetailsModel.getSchedule()!=null && courtCaseDetailsModel.getSchedule().trim().length()>0) {
			courtCaseEntity.setSchedule(courtCaseDetailsModel.getSchedule());
		}
		
		if(courtCaseDetailsModel.getCase_year()!=null && courtCaseDetailsModel.getCase_year().trim().length()>0) {
			courtCaseEntity.setCase_year(courtCaseDetailsModel.getCase_year());
		}
		
		if(courtCaseDetailsModel.getSob_filed()!=null && courtCaseDetailsModel.getSob_filed().trim().length()>0) {
			courtCaseEntity.setSob_filed(courtCaseDetailsModel.getSob_filed());
		}
		if(courtCaseDetailsModel.getStatus()!=null && courtCaseDetailsModel.getStatus().trim().length()>0) {
			courtCaseEntity.setStatus(courtCaseDetailsModel.getStatus());
		}
		if(courtCaseDetailsModel.getThaluk()!=null && courtCaseDetailsModel.getThaluk().trim().length()>0) {
			courtCaseEntity.setThaluk(courtCaseDetailsModel.getThaluk());
		}
		if(courtCaseDetailsModel.getType_of_opponent()!=null && courtCaseDetailsModel.getType_of_opponent().trim().length()>0) {
			courtCaseEntity.setType_of_opponent(courtCaseDetailsModel.getType_of_opponent());
		}
		if(courtCaseDetailsModel.getVillage()!=null && courtCaseDetailsModel.getVillage().trim().length()>0) {
			courtCaseEntity.setVillage(courtCaseDetailsModel.getVillage());
		}
		if(courtCaseDetailsModel.getZone()!=null && courtCaseDetailsModel.getZone().trim().length()>0) {
			courtCaseEntity.setZone(courtCaseDetailsModel.getZone());
		}
		System.out.println(" ORDER STATUS "+courtCaseDetailsModel.getOrder_status());
		if(courtCaseDetailsModel.getOrder_status()!=null && courtCaseDetailsModel.getOrder_status().trim().length()>0) {
			courtCaseEntity.setOrder_status(courtCaseDetailsModel.getOrder_status());
		}
		
		System.out.println(" ORDER STATUS "+courtCaseDetailsModel.getOrder_summary());
		
		if(courtCaseDetailsModel.getOrder_summary()!=null && courtCaseDetailsModel.getOrder_summary().trim().length()>0) {
			courtCaseEntity.setOrder_summary(courtCaseDetailsModel.getOrder_summary());
		}
		
		if(courtCaseDetailsModel.getCompliance_report()!=null && courtCaseDetailsModel.getCompliance_report().trim().length()>0) {
			courtCaseEntity.setCompliance_report(courtCaseDetailsModel.getCompliance_report());
		}
		
		
		if(courtCaseDetailsModel.getZone()!=null && courtCaseDetailsModel.getZone().trim().length()>0) {
			courtCaseEntity.setZone(courtCaseDetailsModel.getZone());
		}
		
		return courtCaseEntity;
	}
	

	/**
	 * convert court case data model to entity
	 */
	private CourtCaseDetailsModel transformCaseEntityToModel(CourtCaseEntity courtCaseEntity) {
		CourtCaseDetailsModel courtCaseDetailsModel = new CourtCaseDetailsModel();
		
		courtCaseDetailsModel.setAction_date(courtCaseEntity.getAction_date());
		courtCaseDetailsModel.setCase_id(""+courtCaseEntity.getId());
		courtCaseDetailsModel.setAdvocate_id(""+courtCaseEntity.getAdvocate_id());
		courtCaseDetailsModel.setBill_amount(courtCaseEntity.getBill_amount());
		courtCaseDetailsModel.setCase_created_date(courtCaseEntity.getCase_created_date());
		courtCaseDetailsModel.setCase_last_updated(courtCaseEntity.getCase_last_updated());
		courtCaseDetailsModel.setCase_no(courtCaseEntity.getCase_no());
		courtCaseDetailsModel.setCase_year(courtCaseEntity.getCompliance_report());
		courtCaseDetailsModel.setCompliance_report(courtCaseEntity.getCompliance_report());
		courtCaseDetailsModel.setCourt_id(courtCaseEntity.getCourt_id());
		courtCaseDetailsModel.setDistrict(courtCaseEntity.getDistrict());
		//courtCaseDetailsModel.setDivision(courtCaseEntity.getDivision());
		courtCaseDetailsModel.setHobli(courtCaseEntity.getHobli());
		courtCaseDetailsModel.setJudgement_date(courtCaseEntity.getJudgement_date());
		//courtCaseDetailsModel.setName(courtCaseEntity.getName());
	//	courtCaseDetailsModel.setOffices(courtCaseEntity.getOffices());
		courtCaseDetailsModel.setOpponent_advocate_id_number(courtCaseEntity.getOpponent_advocate_id_number());
		courtCaseDetailsModel.setOpponent_name(courtCaseEntity.getOpponent_name());
		courtCaseDetailsModel.setOrder_type(courtCaseEntity.getOrder_type());
		courtCaseDetailsModel.setOther_respondents(courtCaseEntity.getOther_respondants());
		courtCaseDetailsModel.setPetinitioner_name(courtCaseEntity.getPetinitioner_name());
		courtCaseDetailsModel.setPrayer(courtCaseEntity.getPrayer());
		courtCaseDetailsModel.setRemark(courtCaseEntity.getRemark());
		courtCaseDetailsModel.setSchedule(courtCaseEntity.getSchedule());
		
		courtCaseDetailsModel.setSob_filed(courtCaseEntity.getSob_filed());
		courtCaseDetailsModel.setStatus(courtCaseEntity.getStatus());
		courtCaseDetailsModel.setThaluk(courtCaseEntity.getThaluk());
		courtCaseDetailsModel.setType_of_opponent(courtCaseEntity.getType_of_opponent());
		courtCaseDetailsModel.setVillage(courtCaseEntity.getVillage());
		courtCaseDetailsModel.setZone(courtCaseEntity.getZone());
		courtCaseDetailsModel.setCase_entered_date(courtCaseEntity.getCase_entered_date());
		
		courtCaseDetailsModel.setCase_year(courtCaseEntity.getCase_year());
		
		courtCaseDetailsModel.setCase_entered_date(courtCaseEntity.getCase_entered_date());
		
		courtCaseDetailsModel.setOrder_status(courtCaseEntity.getOrder_status());
		courtCaseDetailsModel.setOrder_summary(courtCaseEntity.getOrder_summary());
		courtCaseDetailsModel.setCompliance_report(courtCaseEntity.getCompliance_report());

		return courtCaseDetailsModel;
	}
	
	
	/**
	 * convert court case data model to entity
	 */
	private ScheduleEntity transformScheduleModelToEntity(ScheduleDetailsModel scheduleDetailsModel, CourtCaseEntity courtCaseEntity) {
		
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		
		scheduleEntity.setCase_main_id(""+courtCaseEntity.getId());
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
		
		List<CourtCaseEntity> lstAllCourtCases = courtCasesRepository.findAll();
		
		List<CourtCaseDetailsModel> lstCourtCaseDetailsModel = new ArrayList();
		
		CourtCaseDetailsModel courtCaseDetailsModel = null;
		
		if(lstAllCourtCases==null) {
			return null;
		}
		
		for(CourtCaseEntity courtCaseEntity: lstAllCourtCases) {
			
			courtCaseDetailsModel = populateCourtCases(""+courtCaseEntity.getId(),courtCaseEntity);//transformCaseEntityToModel(courtCaseEntity);
			
			lstCourtCaseDetailsModel.add(courtCaseDetailsModel);
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
	
	
	/**
	 * 1. Case Entered Date
	 * 2. Case History Event Addition
	 */
	/**
	 * populate court case details
	 */
	private CourtCaseDetailsModel populateCourtCases(String id,CourtCaseEntity courtCaseEntity) {
		List<String> lstSections = new ArrayList();
		
		List<String> lstPrayers = new ArrayList();
		AdvocatesEntity advocateEntity=null;
		
		List<ScheduleDetailsModel> lstScheduleDetailsModel = new ArrayList<ScheduleDetailsModel>();
		List<ScheduleEntity> lstScheduleEntity = scheduleRepository.findByCase_no(Integer.parseInt(id));
		
		List<CaseHistoryEntity> lstCaseHistoryEntity = caseHistoryService.getLstCaseHistoryEntity(""+courtCaseEntity.getId());
		
		List<CommentEntity> lstCommentEntity = commentService.getAllRecords(""+courtCaseEntity.getId());
		
		List<ContemptEntity> lstContemptEntity = contenptyService.getAllRecords(""+courtCaseEntity.getId());
		
		List<DocumentEntity> lstDocumentEntity = documentsService.getAllRecords(""+courtCaseEntity.getId());
		
		List<FileMovementEntity> lsFileMovementEntity = fileMovementService.getAllRecords(""+courtCaseEntity.getId());
		
		CourtCaseDetailsModel courtCaseDetailsModel = transformCaseEntityToModel(courtCaseEntity);
		courtCaseDetailsModel.setCase_id(""+courtCaseEntity.getId());
		
		
		try {
			advocateEntity =      advocatesService.getAdvocatesEntity(courtCaseEntity.getAdvocate_id());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(lstCommentEntity!=null)
		courtCaseDetailsModel.setCommentCount(lstCommentEntity.size());
		if(lstContemptEntity!=null)
		courtCaseDetailsModel.setContemptCount(lstContemptEntity.size());
		if(lstDocumentEntity!=null)
		courtCaseDetailsModel.setDocumentsCount(lstDocumentEntity.size());
		if(lstCaseHistoryEntity!=null)
		courtCaseDetailsModel.setHistoryCount(lstCaseHistoryEntity.size());
		
		
		CourtEntity courtEntity = courtService.getCourtEntity(courtCaseEntity.getCourt_id());
		
		for(ScheduleEntity scheduleEntity:lstScheduleEntity) {
			
			lstScheduleDetailsModel.add(transformScheduleEntityToModel(scheduleEntity));
			
		}
		
		
		List<SectionEntity> sections = sectionsService.getAllRecords(""+id);
	
		for(SectionEntity sectionEntity:sections) {
			
			lstSections.add(sectionEntity.getSectionId());
		}
		
	
		//courtCaseDetailsModel.setSchedules(lstScheduleDetailsModel);
		//courtCaseDetailsModel.setCase_history(lstCaseHistoryEntity);
		courtCaseDetailsModel.setSections(lstSections);
	//	courtCaseDetailsModel.setContempt(lstContemptEntity);
	//	courtCaseDetailsModel.setDocuments(lstDocumentEntity);
	//	courtCaseDetailsModel.setFile_movement(lsFileMovementEntity);
		courtCaseDetailsModel.setSchedules(lstScheduleDetailsModel);
	//	courtCaseDetailsModel.setComments(lstCommentEntity);
		
		if(advocateEntity!=null) {
			courtCaseDetailsModel.setAdvocate_name(advocateEntity.getName());
		}
		
		if(courtEntity!=null) {
			courtCaseDetailsModel.setCourt_name(courtEntity.getName());
		}
		
		return courtCaseDetailsModel;
	}
}
