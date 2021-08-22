package com.finch.legal.opinion.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Employee Designations
 * @author finch
 *
 */
@Entity
@Table(name = "bdaempdesignations")
public class EmployeeDesignationsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "desg_id")
	/** employee id **/
	private String desgtId;
	
	@Column(name = "desgt_name")
	/** name **/
	private String name ;

}
