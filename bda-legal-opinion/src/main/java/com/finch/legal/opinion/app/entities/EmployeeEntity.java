package com.finch.legal.opinion.app.entities;

/**
 * 
 * @author finch
 *
 */
import javax.persistence.*;

@Entity
@Table(name = "legal_opinion_user")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/** id **/
	private int id;
	
	@Column(name = "emp_no")
	/** employee id **/
	private String empId;
	
	@Column(name = "emp_name")
	/** name **/
	private String name ;
	
	@Column(name = "phone_num")
	/** name **/
	private String phoneNum ;
	
	@Column(name = "email_id")
	/** name **/
	private String emailId ;
	
	@Column(name = "role")
	/** name **/
	private String roleId ;
	
	@Column(name = "dept_id")
	/** name **/
	private String deptId ;
	
	@Column(name = "login_id")
	/** name **/
	private String loginId ;
	
	
	/**
	 * default constructor
	 */
	public EmployeeEntity() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
