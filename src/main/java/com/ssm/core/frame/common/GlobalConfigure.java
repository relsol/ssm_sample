package com.ssm.core.frame.common;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ssm.core.frame.utils.PropertiesUtils;

@Component
public class GlobalConfigure {

	public static String FILE_SERVER_LOCAL_PATH = null;
	public static String HTTP_FILE_SERVER_PATH = null;
	public static String CAS_SERVER_PATH = null;
	
	public static final int DEFAULT_PAGE_SIZE = 20;
	public static final int MAX_PAGE_SIZE = 100;
	

	@PostConstruct
	private void init() {

		Properties application = PropertiesUtils.getProperties("application.properties");
		
		String os = System.getProperties().getProperty("os.name");
		
		HTTP_FILE_SERVER_PATH = application.getProperty("http.file.server");
		CAS_SERVER_PATH = application.getProperty("cas.server.ip.address");
		
		if(StringUtils.startsWithIgnoreCase(os, "win")){
			FILE_SERVER_LOCAL_PATH = application.getProperty("windows.file.server.local.path");
		} else {
			FILE_SERVER_LOCAL_PATH = application.getProperty("linux.file.server.local.path");
		}
	}
	
	@PreDestroy
	private void destroy() {
		GlobalConfigure.FILE_SERVER_LOCAL_PATH = null;
		GlobalConfigure.HTTP_FILE_SERVER_PATH = null;
		GlobalConfigure.CAS_SERVER_PATH = null;
	}
	
	public static final String PAGINATION_SQL_START = "start";

	public static final String PAGINATION_SQL_END = "end";

    public static final String MISC_SUFFIX = "_@MISC@";

}
