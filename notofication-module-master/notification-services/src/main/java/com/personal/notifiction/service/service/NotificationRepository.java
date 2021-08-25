package com.personal.notifiction.service.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author dvsnk
 *
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

	/**
	 * find by method
	 */
//     Optional<Notification> findById(Long id);
    
//     @Query("SELECT count(*) as c FROM Notifications WHERE channel = ?1")
//     int findChannelwiseCount(String channelType);
    
}
