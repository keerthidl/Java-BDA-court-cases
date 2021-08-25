package com.personal.notifiction.service.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NotificationTemplatesRepository  extends CrudRepository<MessageFormat, Long> {

	/**
	 * find by method
	 */
//     Optional<MessageFormat> findById(Long id);
    
    /**
	 * find by method
	 */
//     Optional<MessageFormat> findByTemplateID(String templateID);
    
   
    
}
