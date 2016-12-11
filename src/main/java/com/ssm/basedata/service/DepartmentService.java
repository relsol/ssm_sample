package com.ssm.basedata.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.constants.DepartmentCons;
import com.ssm.basedata.dao.DepartmentMapper;
import com.ssm.basedata.entity.Department;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.ObjectUtil;
import com.ssm.core.frame.utils.SessionUtil;

 	
@Service
@Transactional(readOnly=true)
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	/**
	 * 根据机构ID查询机构下的所有子机构
	 * @param departmentId
	 * @return
	 */
	public List<Department> findAllChildren(Integer departmentId) {
		return null;
	}

	public List<Department> findAllWorkArea() {
		return null;
	}

	/**
	 * 查询所有的段
	 * @return
	 */
	public List<Department> findAllSection() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", DepartmentCons.SECTION);
		return this.departmentMapper.findPageList(params);
	}

	public String showTreeByAll(Integer employeeId, Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", 1);
		List<Department> departmentList = this.departmentMapper.findPageList(params);
		StringBuilder str = new StringBuilder();
		str.append("[");
		str.append("{id:0, name:'组织机构', open:true, nocheck:true},");
		if(ObjectUtil.isNotBlank(departmentList)){
			str.append(this.getTreeNode(departmentList, employeeId, status));
		}
		str.append("]");
		return str.toString();
	}
	
	public List<Department> findByEmployeeId(Integer employeeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employeeId);
		return this.departmentMapper.findPageList(params);
	}
	
	private String getTreeNode(List<Department> departmentList, Integer employeeId, Integer status){
		StringBuilder str = new StringBuilder();
		if(ObjectUtil.isNotBlank(departmentList)){
			for(Department dep : departmentList){
				str.append("{");
				str.append("id: '"+dep.getId()+"',");
				str.append("name: '"+dep.getName()+"',");
				str.append("open: true,");
				if(null != employeeId){
					List<Department> empDepList = this.findByEmployeeId(employeeId);
					if(empDepList.contains(dep)){
						str.append("checked : true,");
					}
				}
				str.append("pId: '"+dep.getPid()+"'},");
			}
		}
		str.deleteCharAt(str.length() - 1); // 删除最后一个字节
		return str.toString();
	}

	/**
	 * 通过ID查询机构
	 * @param id
	 * @return
	 */
	public Department findById(Integer id) {
		if(null == id)
			throw new NkThrowException("ID不存在!");
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		List<Department> depList = this.departmentMapper.findPageList(params);
		if(ObjectUtil.isNotBlank(depList)){
			return depList.get(0);
		}
		return null;
	}

	/**
	 * 新增或修改用户机构
	 * @param department
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Department saveOrUpdate(Department department) {
		department.setUpdaterId(SessionUtil.getEmployeeId());
		department.setUpdaterIp(SessionUtil.getLoginIp());
		department.setUpdaterTime(new Date());
		
		if (department.getId() != null) {
			Department dep = this.findById(department.getId());
			department.setCode(dep.getCode());
			department.setCreaterId(dep.getCreaterId());
			department.setCreaterTime(dep.getCreaterTime());
			this.departmentMapper.updateOne(department);
		} else {
			department.setCode(this.computeDepartmentCode(department.getPid()));
			department.setCreaterId(SessionUtil.getEmployeeId());
			department.setCreaterTime(new Date());
			department.setStatus(DepartmentCons.OPEN_DEP);
			this.departmentMapper.addOne(department);
		}
		
		return department;
	}
	
	/**
	 * 根据ID删除机构信息
	 * @param id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delById(Integer id){
		if(null == id)
			throw new NkThrowException("ID不存在!");
		this.departmentMapper.delById(id);
	}
	
	/**
	 * 修改机构状态
	 * @param id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void changeStatus(Integer id) {
		if(null == id)
			throw new NkThrowException("ID不存在!");
		
		Department dep = this.findById(id);
		if(null == dep){
			throw new NkThrowException("根据ID未找到对应的机构!");
		}
		
		if(DepartmentCons.CLOSE_DEP.equals(dep.getStatus())){
			dep.setStatus(DepartmentCons.OPEN_DEP);
			dep.setUpdaterId(SessionUtil.getEmployeeId());
			dep.setUpdaterIp(SessionUtil.getLoginIp());
			dep.setUpdaterTime(new Date());
		} else {
			dep.setStatus(DepartmentCons.CLOSE_DEP);
			dep.setUpdaterId(SessionUtil.getEmployeeId());
			dep.setUpdaterIp(SessionUtil.getLoginIp());
			dep.setUpdaterTime(new Date());
		}
		
		this.departmentMapper.updateOne(dep);
	}
	
	public List<Department> findChild(Integer departmentId, Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != departmentId){
			params.put("pid", departmentId);
		}
		if(null != status){
			params.put("status", status);
		}
		return this.departmentMapper.findPageList(params);
	}
	
	/*
	 * 通过上级机构的编号算出本机构的编号
	 * 01
	 * 	  0101
	 *   	010101
	 *   	010102
	 *    0102
	 * 02
	 * 	  0201
	 * 	  	  020101
	 *        020102
	 */
	private String computeDepartmentCode(Integer pid){
		pid = null==pid?0:pid;
		Department dep = this.findById(pid);
		String pCode = null==dep?"":dep.getCode();
		return computeDepartmentCode(pCode, pid);
	}
	
	private String computeDepartmentCode(String pCode, Integer pid){
		List<Department> list = this.findChild(pid, null);
		int codeInt = 0;
		for(Department dep : list){
			if(StringUtils.isNotBlank(dep.getCode()) && Integer.valueOf(dep.getCode()) > 0)
				codeInt = Integer.valueOf(dep.getCode().substring(dep.getCode().length()-2, dep.getCode().length()));
		}
		codeInt += 1;
		if(codeInt >9 ) {
			return pCode+codeInt;
		} else {
			return pCode+"0"+codeInt;
		}
	}

	/**
	 * 查询用户可管辖的工区
	 * @param employeeId
	 * @return List<Department>
	 */
	public List<Department> findWorkAreaByEmployeeId(Integer employeeId) {
		return this.departmentMapper.findWorkAreaByEmployeeId(employeeId);
	}

	/**
	 * 根据工区ID查询所属的段
	 * @param workAreaId
	 * @return
	 */
	public Department findSectionByWorkAreaId(Integer workAreaId) {
		return this.departmentMapper.findSectionByWorkAreaId(workAreaId);
	}

	/**
	 * 查询所有机构
	 * @return
	 */
	public List<Department> findAll() {
		return this.departmentMapper.findPageList(null);
	}

	/**
	 * 通过用户ID查询用户可管辖的机构
	 * @param employeeId
	 * @return
	 */
	public List<Department> findDepAndChildByEmployeeId(Integer employeeId) {
		return this.departmentMapper.findDepAndChildByEmployeeId(employeeId);
	}

}
