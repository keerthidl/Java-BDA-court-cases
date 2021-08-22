package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finch.legal.opinion.app.constants.AppConstants;
import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.LegalOpinionRequestEntity;



public interface LegalOpinionRequestRepository extends JpaRepository<LegalOpinionRequestEntity, Integer> {
	
	/**
	 * 
	 * @param reqId
	 * @return
	 */
	LegalOpinionRequestEntity findById(int reqId);
	
	/**
	 * 
	 * @param reqId
	 * @return
	 */
	
	LegalOpinionRequestEntity findByReqId(String reqId);
	
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	List<LegalOpinionRequestEntity> findByDeptId(String deptId);
	
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	@Query(value=AppConstants.SEARCH_QUERY_1,nativeQuery = true)
	List<LegalOpinionRequestEntity> findByAssignedTo(String userId);
	
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	@Query(value=AppConstants.SEARCH_QUERY_2,nativeQuery = true)
	List<LegalOpinionRequestEntity> findByRequestedBy(String userId);
	
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	@Query(value=AppConstants.SEARCH_QUERY_3,nativeQuery = true)
	List<LegalOpinionRequestEntity> findAllRequests();
	
}
