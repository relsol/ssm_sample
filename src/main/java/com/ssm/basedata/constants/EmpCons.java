package com.ssm.basedata.constants;

import java.util.HashMap;
import java.util.Map;

public class EmpCons {
	
	/** 员工默认密码  **/
	public final static String PASSWORD = "000000";

	/** 员工注销  1 **/
	public final static Integer DELETE = 1;
	/** 员工正常  0 **/
	public final static Integer UNDELETE = 0;
	
	/** 系统用户 1 **/
	public final static Integer IS_SYSTEM = 1;
	/** 非系统用户  0 **/
	public final static Integer NOT_SYSTEM = 1;
	
	/** 状态Map **/
	public final static Map<Integer, Object> STATUS_MAP = new HashMap<Integer, Object>();
	/** 性别Map **/
	public final static Map<Integer, Object> SEXMAP = new HashMap<Integer, Object>();
	/** 是否系统用户Map **/
	public final static Map<Integer, Object> SYSTEM_USER_MAP = new HashMap<Integer, Object>();
	
	static {
		STATUS_MAP.put(EmpCons.DELETE, "已注销");
		STATUS_MAP.put(EmpCons.UNDELETE, "正常");
		
		EmpCons.SEXMAP.put(1, "男");
		EmpCons.SEXMAP.put(0, "女");
		
		SYSTEM_USER_MAP.put(EmpCons.IS_SYSTEM, "系统用户");
		SYSTEM_USER_MAP.put(EmpCons.NOT_SYSTEM, "非系统用户");
	}
	
}
