/**

 * @Title: LoginInterceptor.java
 * @Package com.nk.emis.employee.interceptor
 * @Description: 用户登录拦截器
 * @author ZhangZhiBin
 * @date 2013-6-17 上午11:10:49
 * @version V1.0
 */
package com.ssm.core.frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssm.basedata.constants.BaseCons;
import com.ssm.core.frame.utils.SessionUtil;

/**
 * @author ZhangZhiBin
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath();
		request.setAttribute("path", contextPath);
		
//		Subject subject = SecurityUtils.getSubject();
//		if(!subject.isAuthenticated()){
//			for(String url : GlobalConfigure.NO_INTERCEPTOR){
//				if(pathMatcher.match(url, requestUrl)){
//					request.setAttribute("noInterceptor", true);
//					return true;
//				}
//			}
//			String ajaxFlag = request.getHeader("x-requested-with");
//			if(StringUtils.isNotBlank(ajaxFlag)){
//				JSONResponse json = new JSONResponse();
//				json.setSuccess(true);
//				json.setMsg("登陆超时，请重新登陆!");
//				response.getWriter().write(JsonUtils.objToString(json));
//				return false;
//			}
//			response.getWriter().write("<script>top.location='"+contextPath+"/login.html';</script>");
//			return false;
//		}
		
		try {
			if(SessionUtil.getSession() == null
					|| SessionUtil.getCurrentUser() == null){
				response.sendRedirect(contextPath+"/loginUI");
				return false;
			}
		} catch (Exception e) {
			response.sendRedirect(contextPath+"/loginUI");
			return false;
		}
		
		SessionUtil.setAttribute(BaseCons.LOGIN_IP, SessionUtil.getCurrIpAddress(request));
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
