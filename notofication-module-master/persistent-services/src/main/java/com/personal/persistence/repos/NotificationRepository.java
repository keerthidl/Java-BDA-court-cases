package com.personal.persistence.repos;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author dvsnk
 *
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    Optional<Notification> findById(Long id);

}
