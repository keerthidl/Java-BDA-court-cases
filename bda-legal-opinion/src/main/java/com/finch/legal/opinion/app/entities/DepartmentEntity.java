package com.finch.legal.opinion.app.entities;


import javax.persistence.Column;
/**
 * department entity
 * @author finch
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dept_master")
public class DepartmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "dept_id")
	/** employee id **/
	private String deptId;
	
	@Column(name = "dept_name")
	/** name **/
	private String name ;

}
