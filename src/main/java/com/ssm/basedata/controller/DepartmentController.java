package com.ssm.basedata.controller;

import javax.annotation.Resource;

import com.ssm.basedata.entity.Department;
import com.ssm.basedata.service.DepartmentService;
import com.ssm.core.frame.common.JSONResponse;
import com.ssm.core.frame.exception.NkThrowException;
import com.ssm.core.frame.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.basedata.constants.DepartmentCons;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Resource
	private DepartmentService departmentService;
	
	/**
	 * 展示机构树
	 * @param model
	 * @return
	 */
	@RequestMapping("/showTree")
//	@RequiresPermissions("/department/showTree")
	public String showDepartmentTree(Model model){
		String treeNode = this.departmentService.showTreeByAll(null, null);
		model.addAttribute("treeNode", treeNode);
		return "basedata/department/listDepartment";
	}
	
	/**
	 * 展示添加编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showDepartment")
//	@RequiresPermissions("/department/showDepartment")
	public String showDepartment(Integer id, Model model, String type){
		if(!"add".equals(type)){
			Department department = this.departmentService.findById(id);
			model.addAttribute("department", department);
			model.addAttribute("pid", department.getPid());
		} else {
			model.addAttribute("pid", id);
		}
		model.addAttribute("type_map", DepartmentCons.TYPE_MAP);
		return "basedata/department/addDepartment";
	}
	
	/**
	 * 添加或修改机构
	 * @param department
	 * @return
	 */
	@RequestMapping("/addDepartment")
//	@AfterOperationLog("添加或修改机构")
	public@ResponseBody
	JSONResponse saveOrUpdateDepartment(@ModelAttribute("department") Department department){
		JSONResponse json = new JSONResponse();
		try {
			Department dep = this.departmentService.saveOrUpdate(department);
			json.setData(dep);
			json.setSuccess(true);
			json.setMsg("保存机构成功!");
		} catch (Exception e) {
			logger.error("保存机构异常", e);
			json.setMsg(e.getMessage());
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	@RequestMapping("/delDepartment")
//	@RequiresPermissions("/department/delDepartment")
//	@AfterOperationLog("删除机构")
	public@ResponseBody JSONResponse delDepartment(@RequestParam("id")Integer id){
		JSONResponse json = new JSONResponse();
		try {
			this.departmentService.delById(id);
			json.setSuccess(true);
			json.setMsg("删除机构成功!");
		} catch (NkThrowException e) {
			logger.error(e.getMessage(), e);
			json.setMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json.setMsg("操作失败!");
		}
		return json;
	}
	

	/**
	 * 更改机构状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/changeStatus")
//	@RequiresPermissions("/department/changeStatus")
	public@ResponseBody JSONResponse changeStatus(@RequestParam Integer id) {
		JSONResponse json = new JSONResponse();
		try {
			this.departmentService.changeStatus(id);
			json.setSuccess(true);
		} catch (Exception e) {
			logger.error("更改状态失败", e);
			json.setSuccess(false);
			json.setMsg("更改状态失败");
		}
		return json;
	}
}
