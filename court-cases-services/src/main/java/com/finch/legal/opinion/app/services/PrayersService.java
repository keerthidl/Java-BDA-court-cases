package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.DocumentEntity;
import com.finch.legal.opinion.app.entities.PrayerEntity;
import com.finch.legal.opinion.app.entities.SectionEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.PrayerRepository;
import com.finch.legal.opinion.app.repositories.SectionRepository;

@Service
public class PrayersService {
	
	
	/** logger **/
	private static AppLogger LOG = LogManager.getLogger(PrayersService.class);

	 /** employee repository **/
	@Autowired
	private PrayerRepository prayerRepository;
	
	/**
	 * is employee exists
	 */
	public PrayerEntity getPrayerEntity(String id) {
		
		return prayerRepository.findById(Integer.parseInt(id)).get();
		
	}
	
	/**
	 * is employee exists
	 */
	public PrayerEntity addPrayer(PrayerEntity prayerEntity) {
		return prayerRepository.save(prayerEntity);	
	}
	
	/**
	 * is employee exists
	 */
	public PrayerEntity updatePrayerEntity(PrayerEntity prayerEntity) {
		return prayerRepository.save(prayerEntity);	
	}
	
	/**
	 * scheule entities
	 */
	public List<PrayerEntity> getAllRecords(String caseId){
		return prayerRepository.findByCase_no(caseId);
	}
	
	/**
	 * scheule entities
	 */
	public void deleteFDocument(String id){
		prayerRepository.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * scheule entities
	 */
	public void deletePrayers(String caseId){
		prayerRepository.deleteByCase_no(Integer.parseInt(caseId));
	}

}
