package com.ssm.basedata.controller;


import com.github.pagehelper.PageInfo;
import com.ssm.basedata.entity.Role;
import com.ssm.basedata.service.PermissionService;
import com.ssm.basedata.service.RoleService;
import com.ssm.core.frame.exception.NkThrowException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色控制层
 * @author RelSol.Chen
 * @date 2013-07-12 下午10:45
 * @version V1.0 
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	private final static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	private final static String SUCCESS = "success";
	private final static String ERROR = "error";

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 跳转到角色首页
	 * @return
	 */
	@RequestMapping("/main")
	@RequiresPermissions("/role/main")
	public String main(){
		return "basedata/role/roleMain";
	}

	/**
	 * @param role
	 * @param pageNo
     * @return
     */
	@RequestMapping("/findPage")
	public @ResponseBody
	PageInfo<Role> findProjectList(Role role,
								   @RequestParam(defaultValue = "0", required = false) Integer pageNo){
		return this.roleService.queryByPage(role, pageNo);
	}
	
	/**
	 * 跳转到添加角色页面
	 * @return
	 */
	@RequestMapping("/toAddRole")
//	@RequiresPermissions("/role/toAddRole")
	public String toAddRole(Integer id, Model model){
		if(id!=null && id!=-1){
			Role role = this.roleService.findById(id);
			model.addAttribute("role", role);
		}
		return "basedata/role/addRole";
	}
	
	/**
	 * 保存角色
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping("/addRole")
	public@ResponseBody String addRole(@ModelAttribute("role")Role role, Model model){
		try {
			this.roleService.saveOrUpdate(role);
		} catch (Exception e) {
			logger.error("保存角色失败", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 停启角色
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/openOrCloseRole.html")
//	@RequiresPermissions("/role/openOrCloseRole")
	public @ResponseBody String openOrCloseRole(@RequestParam("id")Integer id, @RequestParam("status") Integer status){
		try {
			//超级管理员不能停用
			if(id != null && id.equals(1)){
				return "ERROR";
			}
			this.roleService.openOrCloseRole(id, status);
			return SUCCESS;
		} catch (NkThrowException e) {
			logger.error("停启用角色异常", e.getMessage(), e);
			return ERROR;
		} catch (Exception e){
			logger.error("停启用角色异常", e);
			return ERROR;
		}
	}
	
	/**
	 * 查看权限
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toShowPermission")
//	@RequiresPermissions("/role/toShowPermission")
	public String toShowPermission(@RequestParam("roleId")Integer roleId, Model model){
		String treeNode = this.permissionService.getTreeToGiveAuth(roleId);
		model.addAttribute("treeNode", treeNode);
		model.addAttribute("roleId", roleId);
		return "basedata/role/permissionTree";
	}
	
	/**
	 * 更新角色的权限信息
	 * @param permissionIds
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/updatePermissionToRole")
	public@ResponseBody String updatePermissionToRole(String permissionIds, Integer roleId){
		try {
			this.roleService.updatePermissionToRole(permissionIds, roleId);
		} catch (Exception e) {
			logger.error("更新角色信息异常", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 展示角色
	 * @param employeeId
	 * @return
	 */
	@RequestMapping("/showRoleTree")
	public String showRoleTree(Integer employeeId, Integer projectId, Model model){
		String treeNode = this.roleService.getRoleTree(employeeId, projectId);
		model.addAttribute("treeNode", treeNode);
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("projectId", projectId);
		return "basedata/role/roleTree";
	}

	/**
	 * 更新角色和员工关系
	 * @param roleIds
	 * @param employeeId
     * @return
     */
	@RequestMapping("/savaRoleEmployee")
	public @ResponseBody String saveRoleEmployee(String roleIds, Integer employeeId){
		try {
			this.roleService.saveRoleEmployee(roleIds, employeeId);
		} catch (Exception e) {
			logger.error("更新角色和员工关系异常", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 检查编号是否已被使用
	 * @param code
	 * @return
	 *//*
	@RequestMapping("/checkCode.html")
	public @ResponseBody boolean checkCode(@RequestParam String code) {
		try {
			Role role = this.roleService.findByCode(code);
			if (role == null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}*/
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/delRole")
	public @ResponseBody String delRole(Integer id){
		try {
			this.roleService.delById(id);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
	}
}
