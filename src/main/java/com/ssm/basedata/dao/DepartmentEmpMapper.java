package com.ssm.basedata.dao;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.DepartmentEmp;

@Repository
public interface DepartmentEmpMapper {

	/**
	 * 根据用户ID删除
	 * @param employeeId
	 */
	void delByEmployeeId(Integer employeeId);

	/**	
	 * 新增 
	 * @param obj
	 */
	void addOne(DepartmentEmp obj);
	
}
