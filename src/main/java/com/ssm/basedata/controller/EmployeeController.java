package com.ssm.basedata.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;
import com.ssm.core.frame.common.JSONResponse;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.utils.JsonUtils;
import com.ssm.core.frame.utils.SessionUtil;
import com.ssm.core.frame.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.basedata.constants.DepartmentCons;
import com.ssm.basedata.entity.Employee;
import com.ssm.basedata.service.DepartmentEmpService;
import com.ssm.basedata.service.DepartmentService;
import com.ssm.basedata.service.EmployeeService;

/**
 * @author RelSol.Chen
 * 
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {

	private final static Logger logger = LoggerFactory
			.getLogger(EmployeeController.class);

	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private DepartmentEmpService departmentEmpService;

	/**
	 * 跳转到用户首页
	 * @return
	 */
	@RequestMapping("/main")
	@RequiresPermissions("/employee/main")
	public String main(Model model){
		String str = this.departmentService.showTreeByAll(null, DepartmentCons.OPEN_DEP);
		model.addAttribute("departmentTreeData", str);
		return "basedata/employee/listEmployee";
	}
	
	/**
	 * @param employee 用户
	 * @return 用户列表
	 * @Description:查询用户集合
	 */
	@RequestMapping("/findPage")
	public@ResponseBody
	PageInfo<Employee> findProjectList(Employee employee,
									   @RequestParam(defaultValue = "0", required = false) Integer pageNo){
		PageInfo<Employee> pageView = this.employeeService.queryByPage(
				employee, pageNo);
		return pageView;
	}

	/**
	 * 展示员工信息详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showEmployee")
//	@RequiresPermissions("/employee/showEmployee")
	public String showEmployee(@RequestParam("id") Integer id, Model model) {
		Employee employee = this.employeeService.findById(id);
		model.addAttribute("user", employee);
		return "basedata/employee/showEmployee";
	}

	/**
	 * 跳转到添加或修改员工页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toSaveOrAddEmployee")
	@RequiresPermissions("/employee/toSaveOrAddEmployee")
	public String toSaveOrAddEmployee(Integer id, Model model) {
		Integer employeeId = null;
		if (id != null && id != -1) {
			Employee employee = this.employeeService.findById(id);
			model.addAttribute("user", employee);
			model.addAttribute("type", id);
			employeeId = id;
		}
		String str = this.departmentService.showTreeByAll(employeeId, DepartmentCons.OPEN_DEP);
		model.addAttribute("departmentTreeData", str);
		return "basedata/employee/addEmployee";
	}

	/**
	 * 保存员工信息
	 * @param employee
	 * @return
	 */
	@RequestMapping("/saveOrAddEmployee")
//	@AfterOperationLog("修改或添加员工")
	public @ResponseBody
	String saveOrUpdateEmployee(@ModelAttribute("employee") Employee employee) {
		try {
			this.employeeService.save(employee);
		} catch (NkThrowException e) {
			return e.getMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 检查登录名是否已被使用
	 * @param loginName
	 * @return
	 */
	@RequestMapping("/checkLoginName")
	public @ResponseBody boolean checkLoginName(Integer id,@RequestParam String loginName) {
		Employee emp = this.employeeService.findByLoginName(loginName);
		if (emp == null) {
			return true;
		} else if(id != null && emp.getId().equals(id)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更改员工注销与否的状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/changeStatus")
//	@AfterOperationLog("更改员工注销与否的状态")
//	@RequiresPermissions("/employee/changeStatus")
	public @ResponseBody
	JSONResponse changeStatus(@RequestParam Integer id) {
		JSONResponse json = new JSONResponse();
		try {
			this.employeeService.changeStatus(id);
			json.setSuccess(true);
		} catch (NkThrowException e) {
			logger.error(e.getMessage(), e);
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		} catch (Exception e) {
			logger.error("更改状态失败", e);
			json.setSuccess(false);
			json.setMsg("更改状态失败");
		}
		return json;
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping("/resetPassword")
	public @ResponseBody String resetPassword(Integer id) {
		try {
			if(id != null){
				this.employeeService.resetPassword(id);
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("重置密码失败", e);
			return ERROR;
		}
	}
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequestMapping("/toModifyPwd")
	public String toModifyPwd(Integer employeeId, Model model){
		if(null == employeeId){
			employeeId = SessionUtil.getEmployeeId();
		}
		model.addAttribute("employee", this.employeeService.findById(employeeId));
		model.addAttribute("employeeId", employeeId);
		return "basedata/employee/modify_password";
	}
	
	/**
	 * 跳转到修改登录名页面
	 * @return
	 */
	@RequestMapping("/toModifyLoginName")
	public String toModifyLoginName(Integer employeeId, Model model){
		if(null == employeeId){
			employeeId = SessionUtil.getEmployeeId();
		}
		model.addAttribute("employee", this.employeeService.findById(employeeId));
		model.addAttribute("employeeId", employeeId);
		return "basedata/employee/modify_loginName";
	}
	
	/**
	 * 修改用户名
	 * @param loginName
	 * @return
	 */
	@RequestMapping("/modifyLoginName")
	public @ResponseBody String modifyLoginName(@RequestParam(value="loginName", required=true)String loginName, 
			Integer employeeId){
		try {
			employeeId = null == employeeId ? SessionUtil.getEmployeeId() : employeeId;
			this.employeeService.modifyLoginName(employeeId, loginName);
			return SUCCESS;
		}catch (NkThrowException e) {
			logger.error("用户id:"+employeeId+"修改用户名失败!",e);
			return e.getMessage();
		}catch (Exception e) {
			logger.error("用户id:"+employeeId+"修改用户名失败!",e);
			return "操作失败";
		}
	}
	
	/**
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping("/modifyPwd")
	public @ResponseBody String modifyPwd(@RequestParam(value="oldPassword", required=true)String oldPassword, 
			@RequestParam(value="newPassword", required=true)String newPassword,
			Integer employeeId){
		try {
			employeeId = null==employeeId?SessionUtil.getEmployeeId():employeeId;
			this.employeeService.modifyPwd(employeeId, oldPassword, newPassword);
			return SUCCESS;
		} catch (NkThrowException e) {
			logger.error("用户id:"+employeeId+"修改密码失败!",e);
			return e.getMessage();
		} catch (Exception e) {
			logger.error("用户id:"+employeeId+"修改密码失败!",e);
			return "操作失败";
		}
	}
	
	/**
	 * 判断用户密码是否正确
	 * @param oldPassword
	 * @return
	 */
	@RequestMapping("/checkPwd")
	public @ResponseBody boolean checkPwd(@RequestParam(value="oldPassword", required=true)String oldPassword){
		try {
			boolean flg = this.employeeService.checkPwd(SessionUtil.getEmployeeId(), oldPassword);
			return flg;
		} catch (NkThrowException e) {
			logger.warn(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 通过机构ID查询员工
	 * @param departmentId 机构ID
	 * @return
	 */
	@RequestMapping("/findByDepartmentId")
	public String findEmployeeByDepartment(Integer departmentId, Model model){
		if(null != departmentId){
			List<Employee> employeeList = this.employeeService.findByDepartmentId(departmentId);
			model.addAttribute("employeeList", employeeList);
			model.addAttribute("empJson", JsonUtils.objToString(employeeList));
		}
		return "basedata/employee/employee_div";
	}
	
	/**
	 * 通过机构ID查询员工
	 * @param departmentId 机构ID
	 * @return JSONResponse
	 */
	@RequestMapping("/acquireByDepartmentId")
	public @ResponseBody JSONResponse acquireEmployeeByDepartment(Integer departmentId){
		JSONResponse json = new JSONResponse();
		if (null != departmentId) {
			List<Employee> employeeList = this.employeeService.findByDepartmentId(departmentId);
			json.setData(employeeList);
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
		}
		return json;
	}
}
