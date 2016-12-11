package com.ssm.basedata.entity;

import com.ssm.core.frame.common.IdEntity;

/**
 *
 * 角色资源中间表
 *
 */
public class RolePermission extends IdEntity {

	private static final long serialVersionUID = -217811323404252941L;
	/** 资源ID **/ 
	private Integer permissionId;
	/** 角色ID **/ 
	private Integer roleId;
	
	/** 无参的构造函数 **/
	public RolePermission(){
		super();
	}
	
	/** 有参构造函数 **/
	public RolePermission(Integer permissionId,Integer roleId){
		super();
		this.permissionId = permissionId;
		this.roleId = roleId;
	}
 	
		
	/**
	 * @return 资源ID
	 */
	public Integer getPermissionId() {
		return permissionId;
	}
	/**
	 * @param permissionId 资源ID
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
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
 	
}
