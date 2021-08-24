package com.personal.notifiction.service.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author dvsnk
 *
 */
public interface MessageFormatRepository extends CrudRepository<MessageFormat, Long> {

	/**
	 * find by method
	 */
    Optional<MessageFormat> findById(Long id);
    
    /**
	 * find by method
	 */
    Optional<MessageFormat> findByTemplateID(String templateID);
    
}
