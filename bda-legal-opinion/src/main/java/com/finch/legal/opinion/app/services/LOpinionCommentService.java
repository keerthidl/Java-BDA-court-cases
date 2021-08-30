package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.entities.LOpinionCommentEntity;
import com.finch.legal.opinion.app.repositories.LOpinionCommentsRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class LOpinionCommentService {
	
		/** logger **/
		private static AppLogger LOG = LogManager.getLogger(LOpinionCommentService.class);

	 /** employee repository **/
		@Autowired
		private LOpinionCommentsRepository lOpinionCommentsRepository;
		
		/**
		 * is employee exists
		 */
		public LOpinionCommentEntity getCommentEntity(String id) {
			
			return lOpinionCommentsRepository.findById(Integer.parseInt(id)).get();
			
		}
		
		/**
		 * is employee exists
		 */
		public LOpinionCommentEntity addComment(LOpinionCommentEntity lOpinionCommentEntity) {
			lOpinionCommentsRepository.save(lOpinionCommentEntity);
			return lOpinionCommentEntity;
		}
		
		/**
		 * is employee exists
		 */
		public LOpinionCommentEntity updateComment(LOpinionCommentEntity lOpinionCommentEntity) {
			lOpinionCommentsRepository.save(lOpinionCommentEntity);
			return lOpinionCommentEntity;
		}
		

		/**
		 * is employee exists
		 */
		public void deleteComment(String id) {
			lOpinionCommentsRepository.deleteById(Integer.parseInt(id));
			
		}
		
		/**
		 * is employee exists
		 */
		public LOpinionCommentEntity getCommentRecord(String id) {
			return lOpinionCommentsRepository.findById(id);
			
		}
		
		/**
		 * scheule entities
		 */
		public List<LOpinionCommentEntity> getAllRecords(String caseId){
			return lOpinionCommentsRepository.findByCase_main_id(Integer.parseInt(caseId));
		}
}
