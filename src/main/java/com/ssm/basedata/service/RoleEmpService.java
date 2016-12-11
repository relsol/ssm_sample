package com.ssm.basedata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.dao.RoleEmpMapper;
import com.ssm.basedata.entity.RoleEmp;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.ObjectUtil;
 
@Service
@Transactional(readOnly = true)
public class RoleEmpService {
 	
	@Autowired
	private RoleEmpMapper roleEmpMapper;

 	
 	/**
 	 * 根据员工ID删除员工-角色关系
	 * @param employeeId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void delByEmployeeId(Integer employeeId) {
 		Map<String, Object> parameter = new HashMap<String, Object>();
 		parameter.put("employeeId", employeeId);
		this.roleEmpMapper.deleteByParams(parameter);
	}
 	
 	/**
 	 * 根据员工ID删除员工-角色关系
	 * @param roleId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void delByRoleId(Integer roleId) {
 		Map<String, Object> parameter = new HashMap<>();
 		parameter.put("roleId", roleId);
		this.roleEmpMapper.deleteByParams(parameter);
	}
 	
 	/**
 	 * 新增List
	 * @param roleEmpList
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addList(List<RoleEmp> roleEmpList) {
 		if (ObjectUtil.isBlank(roleEmpList)) {
 			throw new NkThrowException("保存的列表为空");
 		}
 		for (RoleEmp roleEmp : roleEmpList) {
 			this.roleEmpMapper.addOne(roleEmp);
 		}
 	}
 	
 	/**
 	 * 新增
	 * @param roleEmp
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addOne(RoleEmp roleEmp) {
 		if (roleEmp == null) {
 			throw new NkThrowException("要保存的对象为空,操作失败.");
 		}
 		this.roleEmpMapper.addOne(roleEmp);
 	}
 	
 	/**
 	 * 修改
	 * @param roleEmp
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void updateOne(RoleEmp roleEmp) {
 		if (roleEmp == null) {
 			throw new NkThrowException("要修改的对象为空,操作失败.");
 		}
 		if (roleEmp.getId() == null) {
 			throw new NkThrowException("要修改的对象ID为空,操作失败.");
 		}
 		this.roleEmpMapper.updateOne(roleEmp);
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
 		this.roleEmpMapper.delById(id);
 	}
 	
 	/**
 	 * 根据ID查询
	 * @param id
	 * @return RoleEmp
	 */
 	public RoleEmp findById(Integer id) {
 		if (null == id) {
	 		throw new NkThrowException("缺少参数ID,操作失败.");
 		}
 		RoleEmp roleEmp = this.roleEmpMapper.findById(id);
 		return roleEmp;
 	}
}
