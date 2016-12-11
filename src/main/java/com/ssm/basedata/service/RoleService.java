package com.ssm.basedata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.constants.RoleCons;
import com.ssm.basedata.dao.RoleMapper;
import com.ssm.basedata.entity.Role;
import com.ssm.basedata.entity.RoleEmp;
import com.ssm.basedata.entity.RolePermission;
import com.ssm.core.frame.common.GlobalConfigure;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.ObjectUtil;
import com.ssm.core.frame.utils.SessionUtil;
 
@Service
@Transactional(readOnly = true)
public class RoleService {
 	
 	@Autowired
	private RoleMapper roleMapper;
 	@Autowired
 	private RolePermissionService rolePermissionService;
 	@Autowired
 	private RoleEmpService roleEmpService;
 	
 	
 	/**
 	 * 分页查询
	 * @param role
	 * @param pageNo 开始条数
	 */
 	public PageInfo<Role> queryByPage(Role role, Integer pageNo){
		PageHelper.startPage(pageNo, GlobalConfigure.DEFAULT_PAGE_SIZE);
				
 		Map<String, Object> parameter = new HashMap<String, Object>();
 		// TODO 查询条件
 		long count = this.roleMapper.countList(parameter);
 		List<Role> result = null;
 		if(count > 0){
 			result = this.roleMapper.findPageList(parameter);
 		}
		List<Role> list = roleMapper.findPageList(parameter);
		PageInfo<Role> page = new PageInfo<>(list);
		return page;
 	}
 	
 	@Transactional(readOnly=false,  rollbackFor=Exception.class)
	public boolean saveOrUpdate(Role role) {
 		
 		this.dataVaildate(role);
 		
		if (role.getId() != null) {
			Role _role = this.findById(role.getId());
			role.setCode(_role.getCode());
			role.setStatus(_role.getStatus());
			role.setCreaterId(_role.getCreaterId());
			role.setCreaterIp(_role.getCreaterIp());
			role.setCreaterTime(_role.getCreaterTime());
			role.setUpdaterId(SessionUtil.getEmployeeId());
			role.setUpdaterIp(SessionUtil.getLoginIp());
			role.setUpdaterTime(new Date());
			this.roleMapper.updateOne(role);
		} else {
			role.setStatus(RoleCons.OPEN_ROLE);
			role.setCreaterId(SessionUtil.getEmployeeId());
			role.setCreaterIp(SessionUtil.getLoginIp());
			role.setCreaterTime(new Date());
			role.setUpdaterId(SessionUtil.getEmployeeId());
			role.setUpdaterIp(SessionUtil.getLoginIp());
			role.setUpdaterTime(new Date());
			this.roleMapper.addOne(role);
		}
		return true;
	}
 	
 	// 后台数据验证
 	public void dataVaildate(Role role) {
 		if (role == null) {
 			throw new NkThrowException("角色不可为空.");
 		}
 		if (StringUtils.isBlank(role.getName())) {
 			throw new NkThrowException("角色名不可为空.");
 		}
 	}
 	
 	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void openOrCloseRole(Integer id, Integer status) {
 		if (id == null) {
			throw new NkThrowException("ID为空");
		}
		if (status == null) {
			throw new NkThrowException("停启用状态为空");
		}
		if (!status.equals(RoleCons.OPEN_ROLE) && !status.equals(RoleCons.CLOSE_ROLE)) {
			throw new NkThrowException("停启用状态参数有误");
		}
		Role role = this.findById(id);
		if (status.equals(role.getStatus())) {
			return;
		}
		role.setStatus(status);
		this.updateOne(role);
	}
 	
 	public String getRoleTree(Integer employeeId, Integer projectId) {
 		if (employeeId == null) {
			throw new NkThrowException("缺少参数employeeId,操作失败.");
		}
		if (projectId == null) {
//			throw new NkThrowException("缺少参数projectId,操作失败.");
		}
		
 		Map<String, Object> parameter = new HashMap<String, Object>();
 		parameter.put("projectId", projectId);
 		parameter.put("status", RoleCons.OPEN_ROLE);
 		List<Role> roleList = this.roleMapper.findPageList(parameter);
 		
		parameter.put("employeeId", employeeId);
		List<Role> empRoles = this.roleMapper.findPageList(parameter);
		
		StringBuilder strNode = new StringBuilder();
		strNode.append("[");
		for (Role role : roleList) {
			strNode.append("{");
			strNode.append("id:"+role.getId()+",");
			if (empRoles.contains(role)) {
				strNode.append("checked: true,");
			}
			strNode.append("name:'"+role.getName()+"'");
			strNode.append("},");
		}
		if (strNode.length()>1) {
			strNode.deleteCharAt(strNode.length() - 1); // 删除最后一个字节
		}
		strNode.append("]");
		return strNode.toString();
	}
 	
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updatePermissionToRole(String permissionIds, Integer roleId) {
		if (roleId == null) {
			throw new NkThrowException("保存资源和角色关系时， roleId 为 null");
		}
		// 删除该角色的资源关系
		this.rolePermissionService.delByRoleId(roleId);
		// 保存新角色与资源的关系
		if (StringUtils.isBlank(permissionIds)) {
			return;
		}
		String[] strs = permissionIds.split(",");
		List<Integer> idPermisses = new ArrayList<Integer>();
		if (strs != null && strs.length > 0) {
			for (String str : strs) {
				if (!"".equals(str) && !"".equals(str.trim())) {
					idPermisses.add(Integer.parseInt(str));
				}
			}
			if (idPermisses.size() <= 0) {
				return;
			}
			RolePermission rolePer;
			List<RolePermission> rolePerList = new ArrayList<RolePermission>();
			for (Integer idPermission : idPermisses) {
				rolePer = new RolePermission();
				rolePer.setPermissionId(idPermission);
				rolePer.setRoleId(roleId);
				rolePerList.add(rolePer);
			}
			this.rolePermissionService.addList(rolePerList);
		}
	}
 	
 	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveRoleEmployee(String roleIds, Integer employeeId) {
		if(employeeId == null){
			throw new NkThrowException("保存员工和角色关系时， employeeId 为 null");
		}
		//删除该角色和员工的关系
		this.roleEmpService.delByEmployeeId(employeeId);
		//保存新角色与员工的关系
		if(StringUtils.isBlank(roleIds)){
			return;
		}
		String[] strs = roleIds.split(",");
		RoleEmp rolemp;
		List<RoleEmp> roleEmpList = new ArrayList<RoleEmp>();
		for(String roleId : strs){
			if(null!=roleId && !"".equals(roleId)){
				rolemp = new RoleEmp();
				rolemp.setEmployeeId(employeeId);
				rolemp.setRoleId(Integer.valueOf(roleId));
				roleEmpList.add(rolemp);
			}
		}
		this.roleEmpService.addList(roleEmpList);
	}
 	
 	/**
 	 * 新增
	 * @param role
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addOne(Role role) {
 		if (role == null) {
 			throw new NkThrowException("要保存的对象为空,操作失败.");
 		}
 		this.roleMapper.addOne(role);
 	}
 	
 	/**
 	 * 修改
	 * @param role
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void updateOne(Role role) {
 		if (role == null) {
 			throw new NkThrowException("要修改的对象为空,操作失败.");
 		}
 		if (role.getId() == null) {
 			throw new NkThrowException("要修改的对象ID为空,操作失败.");
 		}
 		this.roleMapper.updateOne(role);
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
 		this.roleEmpService.delByRoleId(id);
		this.rolePermissionService.delByRoleId(id);
 		this.roleMapper.delById(id);
 	}
 	
 	/**
 	 * 根据ID查询
	 * @param id
	 * @return Role
	 */
 	public Role findById(Integer id) {
 		if (null == id) {
	 		throw new NkThrowException("缺少参数ID,操作失败.");
 		}
 		Role role = this.roleMapper.findById(id);
 		return role;
 	}

	/**
	 * 通过用户ID查询用户所拥有的角色编号集合
	 * @param employeeId
	 * @return
	 */
	public List<String> findByEmployeeId(Integer employeeId) {
		List<Role> roleList = this.findListByEmployeeId(employeeId);
		List<String> strList = new ArrayList<>();
		if(ObjectUtil.isNotBlank(roleList)){
			for(Role role : roleList)
				strList.add(role.getCode());
		}
		return strList;
	}
	
	/**
	 * 通过用户ID查询用户所拥有的角色编号集合
	 * @param employeeId
	 * @return List<Role>
	 */
	public List<Role> findListByEmployeeId(Integer employeeId) {
		Map<String, Object> params = new HashMap<>();
		params.put("employeeId", employeeId);
		return this.roleMapper.findPageList(params);
	}
}
