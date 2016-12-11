package com.ssm.core.frame.utils;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssm.basedata.constants.BaseCons;
import com.ssm.basedata.entity.Department;
import com.ssm.basedata.entity.Employee;


/**
 * 从Session中获取属性的工具类
 * 使用此工具类，因为我们使用的shiro安全框架，可以使用SecurityUtils与web容器解耦。
 * @author hailin.liu
 *
 */
public final class SessionUtil {
	private final static Logger logger = LoggerFactory.getLogger(SessionUtil.class);
	
	public static WebApplicationContext getWebApplicationContext(){
		Subject subject = SecurityUtils.getSubject();
		HttpServletRequest request = WebUtils.getHttpRequest(subject);
		return WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	}
	
	/**
	 * 获取Session对象
	 */
	public static Session getSession() {
		try {
			return SecurityUtils.getSubject().getSession();
		} catch (Exception e) {
			logger.error("getSession:获取session异常");
			return null;
		}
	}
	
	/**
	 * 放置属性进入session
	 * @param key
	 * @param value
	 */
	public static void setAttribute(Object key,Object value){
		getSession().setAttribute(key,value);
	}
	/**
	 * 从session中获取属性
	 * @param key
	 * @return
	 */
	public static Object getAttribute(Object key){
		return getSession().getAttribute(key);
	}
	
	/**
	 * 从session中取出当前用户Id
	 * 方法功能说明
	 * @return
	 */
	public static Integer getEmployeeId(){
		try {
			return getCurrentUser().getId();
		} catch (Exception e) {
			logger.error("getEmployeeId:获取userId异常");
			return null;
		}
	}
	
	/**
	 * 从Session中取出当前用户登录名
	 * 方法功能说明
	 * @return
	 */
	public static String getLoginName(){
		try {
			return getCurrentUser().getLoginName();
		} catch (Exception e) {
			logger.error("getLoginName:获取当前用户名异常");
			return "system";
		}
	}
	
	/**
	 * 从Session中取出当前用户名称
	 * 方法功能说明
	 * @return
	 */
	public static String getUserName(){
		try {
			return getCurrentUser().getUserName();
		} catch (Exception e) {
			logger.error("getUserName:获取当前用户uname异常");
			return "system";
		}
	}
	/**
	 * 从session中取出当前用户
	 * 方法功能说明
	 * @return
	 */
	public static Employee getCurrentUser(){
		return (Employee)getSession().getAttribute(BaseCons.EMPLOYEE);
	}
	
	/**
	 * 从session中取出当前用户IP
	 * 方法功能说明
	 * @return
	 */
	public static String getLoginIp(){
		return (String) SessionUtil.getSession().getAttribute(BaseCons.LOGIN_IP);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Department> getWorkAreaList(){
		return (List<Department>) getSession().getAttribute(BaseCons.My_WORK_AREA);
	}
	
	public static String getCurrIpAddress(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if(ipAddress.indexOf(",") != -1){
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }
	
}
