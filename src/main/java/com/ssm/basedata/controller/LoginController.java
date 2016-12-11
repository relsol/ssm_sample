package com.ssm.basedata.controller;

import javax.servlet.http.HttpSession;

import com.ssm.basedata.service.EmployeeService;
import com.ssm.core.frame.common.JSONResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/loginUI")
	public String loginUI(){
		return "login";
	}

	@RequestMapping("/login")
	@ResponseBody
	public JSONResponse login(String loginName, String password){
		JSONResponse json = new JSONResponse();
		if(StringUtils.isEmpty(loginName)){
			json.setMsg("登录名不能为空!");
			return json;
		}

		if(StringUtils.isEmpty(password)){
			json.setMsg("密码不能为空!");
			return json;
		}

		UsernamePasswordToken token = new UsernamePasswordToken(loginName, DigestUtils.md5DigestAsHex(password.toLowerCase().getBytes()));
		token.setRememberMe(true);

		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if(!subject.isAuthenticated()){
				json.setMsg("用户验证失败!");
				return json;
			}

			json.setSuccess(true);
			json.setMsg("登录成功.");
		} catch (UnknownAccountException uae) {
			logger.error("There is no user with username of " + token.getPrincipal());
			json.setMsg("用户不存在,请重新输入!");
		} catch (IncorrectCredentialsException ice) {
			logger.error("Password for account " + token.getPrincipal() + " was incorrect!");
			json.setMsg("密码错误,请重新输入!");
		} catch (Exception ae) {
			logger.error("Authentication Error:", ae);
		}

		return json;
	}

	/**
	 * 注销登录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dologout")
	public String doLogout(HttpSession session) {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (Exception e) {
			if (!(e instanceof IllegalStateException)) {
				logger.error(e.getMessage(), e);
			} else {
				logger.debug("Session already invalidated.");
			}
		}
		
		return "redirect:/login";
	}

}
