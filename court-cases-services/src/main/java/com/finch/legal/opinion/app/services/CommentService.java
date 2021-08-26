package com.finch.legal.opinion.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.entities.CommentEntity;
import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.logs.AppLogger;
import com.finch.legal.opinion.app.logs.LogManager;
import com.finch.legal.opinion.app.repositories.CaseCommentsRepository;
import com.finch.legal.opinion.app.repositories.ContemptRepository;

/**
 * 
 * @author finch
 *
 */

@Service
public class CommentService {
	
		/** logger **/
		private static AppLogger LOG = LogManager.getLogger(CommentService.class);

	 /** employee repository **/
		@Autowired
		private CaseCommentsRepository caseCommentsRepository;
		
		/**
		 * is employee exists
		 */
		public CommentEntity getCommentEntity(String id) {
			
			return caseCommentsRepository.findById(Integer.parseInt(id)).get();
			
		}
		
		/**
		 * is employee exists
		 */
		public CommentEntity addComment(CommentEntity commentEntity) {
			caseCommentsRepository.save(commentEntity);
			return commentEntity;
		}
		
		/**
		 * is employee exists
		 */
		public CommentEntity updateComment(CommentEntity commentEntity) {
			caseCommentsRepository.save(commentEntity);
			return commentEntity;
		}
		

		/**
		 * is employee exists
		 */
		public void deleteComment(String id) {
			caseCommentsRepository.deleteById(Integer.parseInt(id));
			
		}
		
		/**
		 * is employee exists
		 */
		public CommentEntity getCommentRecord(String id) {
			return caseCommentsRepository.findById(id);
			
		}
		
		/**
		 * scheule entities
		 */
		public List<CommentEntity> getAllRecords(String caseId){
			return caseCommentsRepository.findByCase_main_id(Integer.parseInt(caseId));
		}
}
