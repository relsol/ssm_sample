package com.ssm.core.frame.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author RelSol Chen
 * <br>
 * 功能概述：控制XSS攻击
 * <br>
 * 创建时间：2013-4-26上午10:17:15
 * <br>
 * 修改记录：
 * <br>
 */
public class XSSFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(XSSFilter.class.getName());
	
	FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest)request), response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

}
