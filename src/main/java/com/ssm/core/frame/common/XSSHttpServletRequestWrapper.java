package com.ssm.core.frame.common;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

/**
 * @author RelSol Chen
 * <br>
 * 功能概述：控制XSS攻击
 * <br>
 * 创建时间：2013-4-26上午10:16:51
 * <br>
 * 修改记录：
 * <br>
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String[] getParameterValues(String parameter) {
		String newParameter = parameter+GlobalConfigure.MISC_SUFFIX;
		String[] newValues = super.getParameterValues(newParameter);
		if(newValues != null){
			return newValues;
		}
		
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		String newParameter = parameter+GlobalConfigure.MISC_SUFFIX;
		String newValue = super.getParameter(newParameter);
		if(newValue != null){
			return newValue;
		}
		
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		//解决get方式乱码问题
		boolean bol = this.checkEncoding(value);
		if(!bol){
			value = this.iso8859TOUtf8(value);
		}
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
				"\"\"");
		value = value.replaceAll("script", "");
		return value;
	}
	
	 /**
	  * 解决get方式乱码问题
	 * @param string
	 * @return
	 */
	private String iso8859TOUtf8(String value) {
        String tempString = "";
        if(StringUtils.isBlank(value)){
        	return tempString;
        }
        try {
            byte[] b = value.getBytes("iso-8859-1");
            tempString = new String(b, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return tempString;
    }
	
	private boolean checkEncoding(String value){
		String encode = "UTF-8";
		try {
			if(value.equals(new String(value.getBytes(encode), encode))){
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		return false;
	}

}
