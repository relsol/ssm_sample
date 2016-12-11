package com.ssm.basedata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.Permission;

@Repository
public interface PermissionMapper {
	
	/**
	 * 查询记录总数
	 * @param params 查询参数
	 */
	long countList(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param params 查询参数
	 * @return List<Permission>
	 */
	List<Permission> findPageList(Map<String, Object> params);
	
	/**
	 * 新增
	 * @param permission
	 */
	void addOne(Permission permission);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	void delById(Integer id);
	
	/**
	 * 更新
 	 * @param permission
	 */
	void updateOne(Permission permission);
	
	/**
	 * 根据ID查询
 	 * @param id
	 * @return Permission
	 */
	 Permission findById(Integer id);
}
