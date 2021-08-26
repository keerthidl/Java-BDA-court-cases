package com.finch.legal.opinion.app.services;

import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.ContemptRepository;
import com.finch.legal.opinion.app.repositories.FileMovementRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class FileMovementService {
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(FileMovementService.class);

	 /** employee repository **/
	@Autowired
	private FileMovementRepository fileMovementRepository;
	
	/**
	 * is employee exists
	 */
	public FileMovementEntity getFileMovementEntity(String id) {
		
		if(fileMovementRepository.findById(Integer.parseInt(id))==null) {
			return null;
		}
		return fileMovementRepository.findById(Integer.parseInt(id));
		
	}
	
	/**
	 * is employee exists
	 */
	public FileMovementEntity addFileMovement(FileMovementEntity fileMovementEntity) {
		return fileMovementRepository.save(fileMovementEntity);	
	}
	
	/**
	 * is employee exists
	 */
	public FileMovementEntity updateFileMovement(FileMovementEntity fileMovementEntity) {
		return fileMovementRepository.save(fileMovementEntity);	
	}
	
	/**
	 * scheule entities
	 */
	public List<FileMovementEntity> getAllRecords(String caseId){
		return fileMovementRepository.findByCase_main_id(Integer.parseInt(caseId));
	}
	
	/**
	 * scheule entities
	 */
	public void deleteFileMovementRecord(String id){
		fileMovementRepository.deleteById(Integer.parseInt(id));
	}
	
}
