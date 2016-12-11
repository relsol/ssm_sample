package com.ssm.basedata.constants;

import java.util.HashMap;
import java.util.Map;

public class RoleCons {

	/** 启用角色  value=1**/
	public final static Integer OPEN_ROLE = 1;
	/** 停用角色  value=0**/
	public final static Integer CLOSE_ROLE = 0;
	
	public final static Map<Integer, String> STATUSMAP = new HashMap<Integer, String>();
	static {
		STATUSMAP.put(1, "启用");
		STATUSMAP.put(0, "停用");
	}
}
