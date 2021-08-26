package com.finch.legal.opinion.app.services;

import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.ContemptRepository;
import com.finch.legal.opinion.app.repositories.DocumentsRepository;
import com.finch.legal.opinion.app.repositories.FileMovementRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class DocumentsService {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(DocumentsService.class);

	 /** employee repository **/
	@Autowired
	private DocumentsRepository documentsRepository;
	
	/**
	 * is employee exists
	 */
	public DocumentEntity getDocumentEntity(String id) {
		
		return documentsRepository.findById(Integer.parseInt(id)).get();
		
	}
	
	/**
	 * is employee exists
	 */
	public DocumentEntity addDocument(DocumentEntity documentEntity) {
		return documentsRepository.save(documentEntity);	
	}
	
	/**
	 * is employee exists
	 */
	public DocumentEntity updateDocumentEntity(DocumentEntity documentEntity) {
		return documentsRepository.save(documentEntity);	
	}
	
	/**
	 * scheule entities
	 */
	public List<DocumentEntity> getAllRecords(String caseId){
		return documentsRepository.findByCase_no(caseId);
	}
	
	/**
	 * scheule entities
	 */
	public void deleteFDocument(String id){
		documentsRepository.deleteById(Integer.parseInt(id));
	}
	
}
