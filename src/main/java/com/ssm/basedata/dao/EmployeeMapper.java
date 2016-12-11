package com.ssm.basedata.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssm.basedata.entity.Employee;

@Repository
public interface EmployeeMapper {
	
	/**
	 * 查询记录总数
	 * @param params 查询参数
	 */
	long countList(Map<String, Object> params);
	
	/**
	 * 分页查询
	 * @param params 查询参数
	 * @return List<Employee>
	 */
	List<Employee> findPageList(Map<String, Object> params);
	
	/**
	 * 新增
	 * @param employee
	 */
	void addOne(Employee employee);
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	void delById(Integer id);
	
	/**
	 * 更新
 	 * @param employee
	 */
	void updateOne(Employee employee);
	
	/**
	 * 根据ID查询
 	 * @param id
	 * @return Employee
	 */
	 Employee findById(Integer id);
	 
	/**
	 * @Description: 通过帐号查询用户
	 * @param loginName 登录帐号
	 * @return Employee
	 */
	Employee findByLoginName(String loginName);
}
