package com.finch.legal.opinion.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.legal.opinion.app.entities.ContemptEntity;
import com.finch.legal.opinion.app.entities.FileMovementEntity;



public interface FileMovementRepository extends JpaRepository<FileMovementEntity, Integer> {
	
	/** find by id **/
	FileMovementEntity findById(int id);
	/** find by id **/
	@Query(value="SELECT * FROM file_movement  WHERE case_id=?1",nativeQuery = true)
	List<FileMovementEntity> findByCase_main_id(int case_main_id); 
}
