package com.ssm.basedata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.Department;

@Repository
public interface DepartmentMapper {

	/**
	 * 查询机构集合
	 * @param params
	 * @return
	 */
	List<Department> findPageList(Map<String, Object> params);

	/**
	 * 添加机构
	 * @param department
	 */
	void addOne(Department department);

	/**
	 * 修改机构
	 * @param department
	 */
	void updateOne(Department department);
	
	/**
	 * 根据ID删除机构信息
	 * @param id
	 */
	void delById(Integer id);

	/**
	 * 查询用户可管辖的工区
	 * @param employeeId
	 * @return
	 */
	List<Department> findWorkAreaByEmployeeId(Integer employeeId);

	/**
	 * 根据工区查询该工区所属的段
	 * @param workAreaId
	 * @return
	 */
	Department findSectionByWorkAreaId(Integer workAreaId);

	/**
	 * 通过用户ID查询用户可管辖的机构
	 * @param employeeId
	 * @return
	 */
	List<Department> findDepAndChildByEmployeeId(Integer employeeId);
}
