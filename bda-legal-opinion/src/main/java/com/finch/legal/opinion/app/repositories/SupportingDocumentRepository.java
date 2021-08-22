package com.finch.legal.opinion.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finch.legal.opinion.app.entities.EmployeeEntity;
import com.finch.legal.opinion.app.entities.SupportingDocumentsEntity;



public interface SupportingDocumentRepository extends JpaRepository<SupportingDocumentsEntity, String> {
	
	Optional<SupportingDocumentsEntity> findById(int legalDocId);
	
	List<SupportingDocumentsEntity> findByLegalRequestId(String legalRequestId);
	
	
}
