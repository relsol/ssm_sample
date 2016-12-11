package com.ssm.basedata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.constants.EmpCons;
import com.ssm.basedata.dao.EmployeeMapper;
import com.ssm.basedata.entity.DepartmentEmp;
import com.ssm.basedata.entity.Employee;
import com.ssm.core.frame.common.GlobalConfigure;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.SessionUtil;

@Service
@Transactional(readOnly=true)
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;	
	@Autowired
	private DepartmentEmpService departmentEmpService;

 	public PageInfo<Employee> queryByPage(Employee employee, Integer pageNo){
		PageHelper.startPage(pageNo, GlobalConfigure.DEFAULT_PAGE_SIZE);
				
 		Map<String, Object> parameter = new HashMap<String, Object>();

		if (employee.getUserName() != null
				&& !"".equals(employee.getUserName())
				&& !"".equals(employee.getUserName().trim())) {
			parameter.put("userName", "%"+employee.getUserName().trim()+"%");
		}
		if (employee.getMobile() != null && !"".equals(employee.getMobile())
				&& !"".equals(employee.getMobile().trim())) {
			parameter.put("mobile", "%"+employee.getMobile().trim()+"%");
		}
		if (employee.getCancel() != null) {
			parameter.put("cancel", employee.getCancel());
		}
		
		if (employee.getDepartmentId() != null) {
			parameter.put("departmentId", employee.getDepartmentId());
		}

		List<Employee> list = employeeMapper.findPageList(parameter);
		PageInfo<Employee> page = new PageInfo<>(list);
 		return page;
 	}
 	
 	/**
 	 * 新增
	 * @param employee
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addOne(Employee employee){
 		this.employeeMapper.addOne(employee);
 	}
 	
 	/**
 	 * 修改
	 * @param employee
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void updateOne(Employee employee){
 		this.employeeMapper.updateOne(employee);
 	}
 	
 	/**
 	 * 根据ID删除
	 * @param id
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void delById(Integer id){
 		this.employeeMapper.delById(id);
 	}
 	
 	/**
 	 * 根据ID查询
	 * @param id
	 * @return Employee
	 */
 	public Employee findById(Integer id) {
 		if(null != id){
	 		Employee employee = this.employeeMapper.findById(id);
	 		return employee;
 		} else {
 			throw new NkThrowException("ID不存在");
 		}
 	}
	
	/**
	 * 通过登陆账号查询用户信息
	 * @param loginName
	 * @return
	 */
	public Employee findByLoginName(String loginName) {
		return this.employeeMapper.findByLoginName(loginName);
	}

	/**
	 * 添加或保存用户
	 * @param employee
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void save(Employee employee) {
		if (employee.getId() == null) {
			if (StringUtils.isNotEmpty(employee.getLoginName())
					&& StringUtils.isNotEmpty(employee.getLoginName().trim())) {
				Employee emp = this.findByLoginName(employee.getLoginName());
				if (emp != null) {
					throw new NkThrowException("登录帐号已存在");
				}
			}
			employee.setCancel(EmpCons.UNDELETE);
			employee.setPassword(DigestUtils.md5Hex(EmpCons.PASSWORD));
			employee.setCreaterId(SessionUtil.getEmployeeId());
			employee.setCreaterTime(new Date());
			employee.setUpdaterIp(SessionUtil.getLoginIp());
			employee.setUpdaterId(SessionUtil.getEmployeeId());
			employee.setUpdaterTime(new Date());
			employee.setUpdaterIp(SessionUtil.getLoginIp());
			this.addOne(employee);
		} else {
			Employee emp = this.findById(employee.getId());
			
			emp.setUserName(employee.getUserName());
			emp.setSex(employee.getSex());
			emp.setPhone(employee.getPhone());
			emp.setMobile(employee.getMobile());
			emp.setAddress(employee.getAddress());
			emp.setSystemUser(employee.getSystemUser());
			emp.setUpdaterId(SessionUtil.getEmployeeId());
			emp.setUpdaterTime(new Date());
			emp.setUpdaterIp(SessionUtil.getLoginIp());
			this.updateOne(employee);
		}
		

		if(null != employee.getDepartmentIds()){
			String departmentIds[] = employee.getDepartmentIds().split(",");
			Integer employeeId = employee.getId();
			List<DepartmentEmp> list = new ArrayList<DepartmentEmp>();
			
			this.departmentEmpService.delByEmployeeId(employeeId);
			for (int i = 0; i < departmentIds.length; i++) {
				if(null==departmentIds[i] || "".equals(departmentIds[i]) || "".equals(departmentIds[i].trim())){
					continue;
				}
				DepartmentEmp departmentEmp = new DepartmentEmp();
				departmentEmp.setDepartmentId(Integer.valueOf(departmentIds[i]));
				departmentEmp.setEmployeeId(employee.getId());
				list.add(departmentEmp);
			}
			this.departmentEmpService.saveList(list);
		}
	}

	/**
	 * 通过机构查询用户
	 * @param departmentId
	 * @return
	 */
	public List<Employee> findByDepartmentId(Integer departmentId) {
		Map<String, Object> params = new HashMap<>();
		params.put("departmentId", departmentId);
		return this.employeeMapper.findPageList(params);
	}

	/**
	 * 验证用户密码是否正确
	 * @param employeeId
	 * @param oldPassword
	 * @return
	 */
	public boolean checkPwd(Integer employeeId, String oldPassword) {
		Employee employee = this.findById(employeeId);
		if(employee==null){
			throw new NkThrowException("该用户不存在，疑似被管理员删除！");
		} 
		if(employee.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(oldPassword.trim()))){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修改用户密码
	 * @param employeeId
	 * @param oldPassword
	 * @param newPassword
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void modifyPwd(Integer employeeId, String oldPassword, String newPassword) {
		Employee employee = this.findById(employeeId);
		if(employee==null){
			throw new NkThrowException("该用户不存在，疑似被管理员删除！");
		} 
		if(employee.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(oldPassword.trim()))){
			employee.setPassword(DigestUtils.md5Hex(newPassword.trim()));
			if(SessionUtil.getSession()!=null 
					&& StringUtils.isNotBlank(SessionUtil.getLoginIp())){
				employee.setUpdaterIp(SessionUtil.getLoginIp());
			}
			if(SessionUtil.getSession()!=null 
					&& SessionUtil.getCurrentUser()!=null){
				employee.setUpdaterId(SessionUtil.getEmployeeId());
			}
			employee.setUpdaterTime(new Date());
			this.employeeMapper.updateOne(employee);
		} else {
			throw new NkThrowException("用户密码错误，请重新输入！");
		}
	}

	/**
	 * @param employeeId
	 * @param loginName
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void modifyLoginName(Integer employeeId, String loginName) {
		Employee employee = this.findById(employeeId);
		if(employee==null){
			throw new NkThrowException("该用户不存在，操作失败！");
		}
		if(StringUtils.isNotBlank(loginName)){
			employee.setLoginName(loginName.trim());
			if(SessionUtil.getSession()!=null && StringUtils.isNotBlank(SessionUtil.getLoginIp())){
				employee.setUpdaterIp(SessionUtil.getLoginIp());
			}
			if(SessionUtil.getSession()!=null && SessionUtil.getCurrentUser()!=null){
				employee.setUpdaterId(SessionUtil.getEmployeeId());
			}
			employee.setUpdaterTime(new Date());
			this.employeeMapper.updateOne(employee);
		} else {
			throw new NkThrowException("用户名不可全为空格！");
		}
		
	}

	/**
	 * 初始化密码
	 * @param id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void resetPassword(Integer id) {
		Employee employee = this.findById(id);
		if(employee==null){
			throw new NkThrowException("该用户不存在，疑似被管理员删除！");
		} 
		employee.setPassword(DigestUtils.md5Hex(EmpCons.PASSWORD));
		employee.setUpdaterIp(SessionUtil.getLoginIp());
		employee.setUpdaterId(SessionUtil.getEmployeeId());
		employee.setUpdaterTime(new Date());
		this.employeeMapper.updateOne(employee);
	}

	/**
	 * 更改状态
	 * @param id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void changeStatus(Integer id) {
		Employee employee = this.findById(id);
		if("admin".equals(employee.getLoginName().trim())){
			throw new NkThrowException("admin 帐号不可更改状态");
		}
		if(employee.getCancel().equals(EmpCons.DELETE)){
			employee.setCancel(EmpCons.UNDELETE);
		} else {
			employee.setCancel(EmpCons.DELETE);
		}
		this.employeeMapper.updateOne(employee);
	}
}
