package com.ssm.basedata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.dao.DepartmentEmpMapper;
import com.ssm.basedata.entity.DepartmentEmp;
import com.ssm.core.frame.utils.ObjectUtil;
 	
@Service
@Transactional(readOnly = true)
public class DepartmentEmpService {
	
	@Autowired
	private DepartmentEmpMapper departmentEmpMapper;

	/**
	 * 根据ID删除
	 * @param employeeId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delByEmployeeId(Integer employeeId) {
		this.departmentEmpMapper.delByEmployeeId(employeeId);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveList(List<DepartmentEmp> list) {
		if(ObjectUtil.isNotBlank(list)){
			for(DepartmentEmp obj : list){
				this.departmentEmpMapper.addOne(obj);
			}
		}
	}
 	
 	
}
