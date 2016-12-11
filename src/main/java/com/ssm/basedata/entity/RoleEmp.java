package com.ssm.basedata.entity;

import com.ssm.core.frame.common.IdEntity;

/**
 *
 * 角色用户中间表
 *
 */
public class RoleEmp extends IdEntity {

	private static final long serialVersionUID = 3125229669987487718L;
	/** 角色ID **/ 
	private Integer roleId;
	/** 用户ID **/ 
	private Integer employeeId;
	
	/** 无参的构造函数 **/
	public RoleEmp(){
		super();
	}
	
	/** 有参构造函数 **/
	public RoleEmp(Integer roleId,Integer employeeId){
		super();
		this.roleId = roleId;
		this.employeeId = employeeId;
	}
 	
		
	/**
	 * @return 角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId 角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
