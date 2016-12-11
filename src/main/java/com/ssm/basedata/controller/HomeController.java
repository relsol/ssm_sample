package com.ssm.basedata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ssm.core.frame.common.GlobalConfigure;
import com.ssm.core.frame.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.basedata.constants.BaseCons;
import com.ssm.basedata.constants.PermissionCons;
import com.ssm.basedata.entity.Permission;
import com.ssm.basedata.service.PermissionService;

@Controller
public class HomeController {
	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request, Model model) {
		model.addAttribute("casServerPath", GlobalConfigure.CAS_SERVER_PATH);

		List<Permission> perList = this.permissionService.findByEmpAndLevel(
				SessionUtil.getEmployeeId(), PermissionCons.NAVIGATION);
		
		for(Permission per : perList){
			List<Permission> childrens = this.permissionService.findByPidAndEmpIdAndLevel(per.getId(),
					SessionUtil.getEmployeeId(),
					PermissionCons.MENU);
			per.setChildren(childrens);
		}
		
		model.addAttribute("menus", perList);
		return "home";
	}
}
