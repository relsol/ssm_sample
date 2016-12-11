package com.ssm.basedata.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class PermissionCons {

	/** 表示URL为d导航 **/
	public static final Integer NAVIGATION = 1;
	/** 表示URL为菜单 **/
	public static final Integer MENU = 2;
	/** 表示URL为按钮 **/
	public static final Integer BUTTON = 3;
	
	public static final Map<Integer,String> LEVEL = new LinkedHashMap<Integer, String>(){
		
		{
			this.put(PermissionCons.NAVIGATION, "导航");
			this.put(PermissionCons.MENU, "菜单");
			this.put(PermissionCons.BUTTON, "按钮");
		}
	};
	
}
