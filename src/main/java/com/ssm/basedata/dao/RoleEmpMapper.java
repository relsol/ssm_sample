package com.ssm.basedata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.RoleEmp;

@Repository
public interface RoleEmpMapper {
	
	/**
	 * 根据条件删除
	 * @param id
	 */
	void deleteByParams(Map<String, Object> params);
	
	/**
	 * 查询记录总数
	 * @param params 查询参数
	 */
	long countList(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param params 查询参数
	 * @return List<RoleEmp>
	 */
	List<RoleEmp> findPageList(Map<String, Object> params);
	
	/**
	 * 新增
	 * @param roleEmp
	 */
	void addOne(RoleEmp roleEmp);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	void delById(Integer id);
	
	/**
	 * 更新
 	 * @param roleEmp
	 */
	void updateOne(RoleEmp roleEmp);
	
	/**
	 * 根据ID查询
 	 * @param id
	 * @return RoleEmp
	 */
	 RoleEmp findById(Integer id);
}
