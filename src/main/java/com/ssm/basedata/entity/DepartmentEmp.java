package com.ssm.basedata.entity;

import com.ssm.core.frame.common.IdEntity;

/**
 *
 * 机构用户中间表
 *
 */
public class DepartmentEmp extends IdEntity {

	private static final long serialVersionUID = -198646721815238124L;
	/** 机构ID **/ 
	private Integer departmentId;
	/** 用户ID **/ 
	private Integer employeeId;
	
	/** 无参的构造函数 **/
	public DepartmentEmp(){
		super();
	}
	
	/** 有参构造函数 **/
	public DepartmentEmp(Integer departmentId, Integer employeeId) {
		super();
		this.departmentId = departmentId;
		this.employeeId = employeeId;
	}


	/**
	 * @return 机构ID
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId 机构ID
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
		
	/**
	 * @return 用户ID
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId 用户ID
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
}
