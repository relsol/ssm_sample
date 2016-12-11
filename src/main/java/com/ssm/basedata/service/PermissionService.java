package com.ssm.basedata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.constants.PermissionCons;
import com.ssm.basedata.dao.PermissionMapper;
import com.ssm.basedata.entity.Permission;
import com.ssm.basedata.entity.Role;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.ObjectUtil;

@Service
@Transactional(readOnly = true)
public class PermissionService {
 	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private RoleService roleService;
 	

	@Transactional(readOnly=false,  rollbackFor=Exception.class)
	public Permission saveOrUpdate(Permission permission) {
		
		if (permission.getId() != null) {
			Permission dep = this.findById(permission.getId());
			permission.setPid(dep.getPid());
			permission.setProjectId(dep.getProjectId());
			this.permissionMapper.updateOne(permission);
		} else {
			this.permissionMapper.addOne(permission);
		}
		return permission;
	}
 	
	/**
	 * @param projectId
	 * @return
	 * @Description: 通过项目ID查询权限树
	 */
 	public String findTreeByProjectId(Integer projectId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		List<Permission> perList = this.permissionMapper.findPageList(params);
		StringBuilder str = new StringBuilder();
		str.append("[");
		if (ObjectUtil.isNotBlank(perList)) {
			str.append(this.getTree(perList, null, null));
		}
		str.append("]");
		return str.toString();
	}
 	
	/**
	 * 根据系统和父ID查树
	 * @param projectId
	 * @param pid
	 * @return
	 */
	public List<Permission> findListByProjectIdAndPid(Integer projectId,Integer pid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("pid",pid);
		List<Permission> perList = this.permissionMapper.findPageList(params);
		return perList;
	}
	
	public String getTreeToGiveAuth(Integer roleId) {
		Role role = this.roleService.findById(roleId);
		Integer projectId = role.getProjectId();// 获取这个角色所处的项目ID
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		List<Permission> perList = this.permissionMapper.findPageList(params);
		params.put("roleId", roleId);
		List<Permission> rolList = this.permissionMapper.findPageList(params);
		StringBuilder str = new StringBuilder();
		str.append("[");
		if (ObjectUtil.isNotBlank(perList)) {
			str.append(this.getTree(perList, rolList, null));
		}
		str.append("]");
		return str.toString();
	}
	private String getTree(List<Permission> list, List<Permission> rolList, Integer status){
		StringBuilder str = new StringBuilder();
		if(ObjectUtil.isNotBlank(list)){
			for(Permission per : list){
				str.append("{");
				str.append("id: "+per.getId()+",");
				str.append("pId: "+per.getPid()+",");
				str.append("name: '"+per.getName()+"',");
				
				if(ObjectUtil.isNotBlank(rolList) && rolList.contains(per)){
					str.append("checked: true,");
				}
				
				if(!per.getLevel().equals(PermissionCons.BUTTON)){
					if(per.getLevel().equals(PermissionCons.NAVIGATION)){
						str.append("open:true,");
					}
				}
				str.append("},");
			}
			str.deleteCharAt(str.length() - 1); // 删除最后一个字节
		}
		return str.toString();
	}
 	
 	/**
 	 * 新增
	 * @param permission
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void addOne(Permission permission){
 		this.permissionMapper.addOne(permission);
 	}
 	
 	/**
 	 * 修改
	 * @param permission
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void updateOne(Permission permission){
 		this.permissionMapper.updateOne(permission);
 	}
 	
 	/**
 	 * 根据ID删除
	 * @param id
	 */
 	@Transactional(readOnly = false, rollbackFor = Exception.class)
 	public void delById(Integer id){
 		this.permissionMapper.delById(id);
 	}
 	
 	/**
 	 * 根据ID查询
	 * @param id
	 * @return Permission
	 */
 	public Permission findById(Integer id) {
 		if(null != id){
	 		Permission permission = this.permissionMapper.findById(id);
	 		return permission;
 		} else {
 			throw new NkThrowException("ID不存在");
 		}
 	}
 	
 	/**
	 * 通过用户ID查询权限资源集合
	 * @param employeeId 用户ID
	 * @return List<Permission>
	 */
	public List<Permission> findByEmployeeId(Integer employeeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employeeId);
		return this.permissionMapper.findPageList(params);
	}
	
	/**
	 * 
	 * @Title: findByEmpAndLevelAndProject
	 * @Description: 通过用户ID和资源级别 所属系统查询用户所拥有的所有资源
	 * @param employeeId
	 * @param level
	 * @return
	 * List<Permission>
	 * @throws
	 */
	public List<Permission> findByEmpAndLevel(Integer employeeId, Integer level) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != employeeId) {
			params.put("employeeId", employeeId);
		}
		if(null != level) {
			params.put("level", level);
		}
		return this.permissionMapper.findPageList(params);
	}
	
	public List<Permission> findByPidAndEmpIdAndLevel(Integer pid, Integer employeeId, Integer level) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != pid) {
			params.put("pid", pid);
		}
		if(null != employeeId) {
			params.put("employeeId", employeeId);
		}
		if(null != level) {
			params.put("level", level);
		}
		return this.permissionMapper.findPageList(params);
	}
	
}
