package com.ssm.basedata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.dao.RolePermissionMapper;
import com.ssm.basedata.entity.RolePermission;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.ObjectUtil;
 
@Service
@Transactional(readOnly = true)
public class RolePermissionService {
 	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

 	/**
 	 * 根据角色ID,删除属于该角色的所有角色-权限关系
	 * @param roleId
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void delByRoleId(Integer roleId) {
 		if (roleId == null) {
 			throw new NkThrowException("缺少参数roleId,操作失败.");
 		}
 		this.rolePermissionMapper.delByRoleId(roleId);
 	}
 	
 	/**
 	 * 新增List
	 * @param rolePermissionList
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addList(List<RolePermission> rolePermissionList) {
 		if (ObjectUtil.isBlank(rolePermissionList)) {
 			throw new NkThrowException("保存的列表为空");
 		}
 		for (RolePermission rolePermission : rolePermissionList) {
 			this.rolePermissionMapper.addOne(rolePermission);
 		}
 	}
 	
 	/**
 	 * 新增
	 * @param rolePermission
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addOne(RolePermission rolePermission) {
 		if (rolePermission == null) {
 			throw new NkThrowException("要保存的对象为空,操作失败.");
 		}
 		this.rolePermissionMapper.addOne(rolePermission);
 	}
 	
 	/**
 	 * 修改
	 * @param rolePermission
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void updateOne(RolePermission rolePermission) {
 		if (rolePermission == null) {
 			throw new NkThrowException("要修改的对象为空,操作失败.");
 		}
 		if (rolePermission.getId() == null) {
 			throw new NkThrowException("要修改的对象ID为空,操作失败.");
 		}
 		this.rolePermissionMapper.updateOne(rolePermission);
 	}
 	
 	/**
 	 * 根据ID删除
	 * @param id
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void delById(Integer id) {
 		if (null == id) {
 			throw new NkThrowException("缺少参数ID,操作失败.");
 		}
		this.rolePermissionMapper.delById(id);
 	}
 	
 	/**
 	 * 根据ID查询
	 * @param id
	 * @return RolePermission
	 */
 	public RolePermission findById(Integer id) {
 		if (id == null) {
 			throw new NkThrowException("缺少参数ID,操作失败.");
 		}
 		RolePermission rolePermission = this.rolePermissionMapper.findById(id);
 		return rolePermission;
 	}
}
