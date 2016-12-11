package com.ssm.basedata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.RolePermission;

@Repository
public interface RolePermissionMapper {
	
 	/**
 	 * 根据角色ID,删除属于该角色的所有角色-权限关系
	 * @param roleId
	 */
	void delByRoleId(Integer roleId);
	
	/**
	 * 查询记录总数
	 * @param params 查询参数
	 */
	long countList(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param params 查询参数
	 * @return List<RolePermission>
	 */
	List<RolePermission> findPageList(Map<String, Object> params);
	
	/**
	 * 新增
	 * @param rolePermission
	 */
	void addOne(RolePermission rolePermission);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	void delById(Integer id);
	
	/**
	 * 更新
 	 * @param rolePermission
	 */
	void updateOne(RolePermission rolePermission);
	
	/**
	 * 根据ID查询
 	 * @param id
	 * @return RolePermission
	 */
	 RolePermission findById(Integer id);
}
