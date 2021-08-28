package com.finch.legal.opinion.app.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.employee.model.LegalOpinionsSearchModel;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;
import com.finch.legal.opinion.app.entities.LegalOpinionResponseEntity;
import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;
import com.finch.legal.opinion.app.repositories.LegalOpinionRequestRepository;
import com.finch.legal.opinion.app.repositories.SupportingDocumentRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class LegalOpinionService {
	
	/** Logger **/
	private AppLogger LOG = LogManager.getLogger(LegalOpinionService.class);

	 /** employee repository **/
	@Autowired
	private LegalOpinionRequestRepository legalOpinionRequestRepository;
		
	/** employee repository **/
	@Autowired
	private SupportingDocumentRepository supportingDocumentRepository;
	
	@Autowired
	private EmployeeService employeeService;
		
	/**
	 * is employee exists
	 */
	public LegalOpinionRequestEntity getLegalOpinionRequest(String reqId) { 
		 return  (legalOpinionRequestRepository.findById(Integer.parseInt(reqId)));
	}
	
	/**
	 * is employee exists
	 */
	public List<LegalOpinionRequestEntity> searchLegalOpinionRequests(final LegalOpinionsSearchModel legalOpinionsSearchModel,String requestedBy) { 
		LOG.info(" IN SERVICE << searchLegalOpinionRequests >> ");
		EmployeeEntity employeeEntity = employeeService.getEmployeeByLoginId(requestedBy);
		List<LegalOpinionRequestEntity> lstLegalOpinionRequestEntity = null;
		
		
		if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.LAW_OFFICER_ROLEID))){
			lstLegalOpinionRequestEntity = legalOpinionRequestRepository.findAllRequests();
		}else if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADMIN_ROLEID))){
			lstLegalOpinionRequestEntity =legalOpinionRequestRepository.findAll();
		}else if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.ADVOCATE_ROLEID))){
			LOG.info(" REQUESTED BY IS "+requestedBy);
			lstLegalOpinionRequestEntity = legalOpinionRequestRepository.findByAssignedTo(requestedBy);
		}else if(employeeEntity!=null && (employeeEntity.getRoleId()!=null && employeeEntity.getRoleId().equalsIgnoreCase(AppConstants.HOD_ROLEID))){
			lstLegalOpinionRequestEntity = legalOpinionRequestRepository.findByRequestedBy(requestedBy);
		}
		
		return  lstLegalOpinionRequestEntity;
	}
	
	/**
	 * is employee exists
	 */
	public List<LegalOpinionRequestEntity> searchLegalOpinionRequestsReqBy(LegalOpinionsSearchModel legalOpinionsSearchModel,String requestedBy) { 
		 return  legalOpinionRequestRepository.findByRequestedBy(legalOpinionsSearchModel.getUserId());
	}
	/**
	 * is employee exists
	 */
	public LegalOpinionRequestEntity addLegalOpinionRequest(LegalOpinionRequestEntity legalOpinionRequestEntity) {

		legalOpinionRequestEntity= legalOpinionRequestRepository.save(legalOpinionRequestEntity);
		 
		 
		return legalOpinionRequestEntity;
	}
	
	/**
	 * is employee exists
	 */
	public boolean isIDReqExists(String strId) {
		
		
		return legalOpinionRequestRepository.findByReqId(strId)==null?false:true;
		
	}
	
	/**
	 * is employee exists
	 */
	public int getMaxCount(String strDeptId) {
		
		int iCount=0;
		List<LegalOpinionRequestEntity>  lstLegalOpinionRequestEntities = legalOpinionRequestRepository.findByDeptId(strDeptId);
		
		if(lstLegalOpinionRequestEntities!=null ) {
			return lstLegalOpinionRequestEntities.size();
		}
		return 0;
		
	}
	
	/**
	 * is employee exists
	 */
	public SupportingDocumentsEntity uploadSupportingDocuments(SupportingDocumentsEntity supportingDocumentsEntity) {
		
		System.out.println("  KKKKKKKKKKK    UUUUUUUUUUUPPPPPPPPPPPPPPPPPPPPLLLLLLLLLLLLL   "+supportingDocumentsEntity.getId());
		supportingDocumentsEntity= supportingDocumentRepository.save(supportingDocumentsEntity);
		System.out.println("  KKKKKKKKKKK    DONEEEEEEEEEEEEEEEEEEEE   "+supportingDocumentsEntity.getId());
		 
		return supportingDocumentsEntity;
	}
	
	/**
	 * is employee exists
	 */
	public List<SupportingDocumentsEntity> getAllSupportingDocuents(String strLegalReqId) {
		
		 
		return supportingDocumentRepository.findByLegalRequestId(strLegalReqId);
	}
	
	/**
	 * is employee exists
	 */
	public SupportingDocumentsEntity getSupportingDocuent(String strLegalReqId) {
		
		 
		return supportingDocumentRepository.findById(Integer.parseInt(strLegalReqId)).get();
	}
	
	/**
	 * is employee exists
	 */
	public void deleteLegalRequest(String strId) {
		
		System.out.println("  KKKKKKKKKKK       "+strId);
		legalOpinionRequestRepository.deleteById(Integer.parseInt(strId));
	
	}
	
	
	
	/**
	 * is employee exists
	 */
	public List<LegalOpinionRequestEntity> getAllLegalOpinionRequests() {
		
		List<LegalOpinionRequestEntity> lsLegalOpinionResponseEntity= legalOpinionRequestRepository.findAll();
		 
		 
		return lsLegalOpinionResponseEntity;
	}
}
