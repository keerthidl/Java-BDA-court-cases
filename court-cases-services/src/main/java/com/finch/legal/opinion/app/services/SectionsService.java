package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.SectionEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.SectionRepository;

@Service
public class SectionsService {
	
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(SectionsService.class);

	 /** employee repository **/
	@Autowired
	private SectionRepository sectionRepository;
	
	/**
	 * is employee exists
	 */
	public SectionEntity getSectionEntity(String id) {
		
		return sectionRepository.findById(Integer.parseInt(id)).get();
		
	}
	
	/**
	 * is employee exists
	 */
	public SectionEntity addSection(SectionEntity sectionEntity) {
		return sectionRepository.save(sectionEntity);	
	}
	
	/**
	 * is employee exists
	 */
	public SectionEntity updateSectionEntity(SectionEntity sectionEntity) {
		return sectionRepository.save(sectionEntity);	
	}
	
	/**
	 * scheule entities
	 */
	public List<SectionEntity> getAllRecords(String caseId){
		return sectionRepository.findByCase_no(Integer.parseInt(caseId));
	}
	
	/**
	 * scheule entities
	 */
	public void deleteSection(String id){
		sectionRepository.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * scheule entities
	 */
	public void deleteSections(String caseId){
		sectionRepository.deleteByCaseId(Integer.parseInt(caseId));
	}
	

}
