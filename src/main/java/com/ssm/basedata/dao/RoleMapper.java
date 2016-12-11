package com.ssm.basedata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.Role;

@Repository
public interface RoleMapper {
	
	/**
	 * 查询记录总数
	 * @param params 查询参数
	 */
	long countList(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param params 查询参数
	 * @return List<Role>
	 */
	List<Role> findPageList(Map<String, Object> params);
	
	/**
	 * 新增
	 * @param role
	 */
	void addOne(Role role);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	void delById(Integer id);
	
	/**
	 * 更新
 	 * @param role
	 */
	void updateOne(Role role);
	
	/**
	 * 根据ID查询
 	 * @param id
	 * @return Role
	 */
	 Role findById(Integer id);
}
