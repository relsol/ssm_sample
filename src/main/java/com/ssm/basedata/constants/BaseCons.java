package com.ssm.basedata.constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseCons {
	
	public static final String DEPARTMENT_IDS      =  "departmentIds";
	public static final String DEPARTMENT_NAMES    =  "departmentNames";
	public static final String TRAN_STATION        =  "tranStation";
	public static final String TRAN_STATION_IDS    =  "tranStationIds";
	
	public static final String PROJECT_CODE = "emis";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String LOGIN_NAME = "loginName";
	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String LOGIN_IP = "loginIp";
	public static final String EMPLOYEE = "employee";
	public static final String EMPLOYEE_PHOTO = "employeePhoto";
	
	/** 用户可管辖的工区 **/
	public static final String My_WORK_AREA = "workarea";
	/** 用户所在的段 **/
	public static final String MY_SECTION = "MY_SECTION";
	
	/** 草稿 value = 1 **/
	public final static Integer FRAFT = 1;
	/** 提交 value = 3 **/
	public final static Integer SUBMIT = 3;
	/** 审核 value = 5 **/
	public final static Integer EXAM = 5;
	/** 驳回 value = 7 **/
	public final static Integer REJECT = 7;

	public final static Map<Integer, String> BASE_STATUS = new HashMap<Integer, String>(){
		private static final long serialVersionUID = -5956813870212766395L;
		{
			this.put(BaseCons.FRAFT, "草稿");
			this.put(BaseCons.SUBMIT, "待审核");
			this.put(BaseCons.EXAM, "审核通过");
			this.put(BaseCons.REJECT, "驳回");
		}
	};
	
	/**汉族**/
	public final static String HAN = "汉族";
	/**壮族**/
	public final static String ZHUANG = "壮族";
	/**满族**/
	public final static String MAN = "满族";
	/**回族**/
	public final static String HUI = "回族";
	/**苗族**/
	public final static String MIAO = "苗族";
	/**维吾尔族**/
	public final static String WEI_WU_ER = "维吾尔族";
	/**土家族**/
	public final static String TU_JIA = "土家族";
	/**彝族**/
	public final static String YI = "彝族";
	/**蒙古族**/
	public final static String MENG_GU = "蒙古族";
	/**藏族**/
	public final static String ZANG = "藏族";	
	/**布依族**/
	public final static String BU_YI = "布依族";
	/**侗族**/
	public final static String TONG = "侗族";
	/**瑶族**/
	public final static String YAO = "瑶族";	
	/**朝鲜族**/
	public final static String CHAO_XIAN = "朝鲜族";
	/**白族**/
	public final static String BAI = "白族";
	/**哈尼族**/
	public final static String HA_NI = "哈尼族";
	/**哈萨克族**/
	public final static String HA_SA_KE = "哈萨克族";
	/**黎族**/
	public final static String LI = "黎族";
	/**傣族**/
	public final static String DAI = "傣族";
	/**畲族**/
	public final static String SHE = "畲族";
	/**傈僳族**/
	public final static String LI_SU = "傈僳族";
	/**仡佬族**/
	public final static String GE_LAO = "仡佬族";	
	/**东乡族**/
	public final static String DONG_XIANG = "东乡族";
	/**高山族**/
	public final static String GAO_SHAN = "高山族";
	/**拉祜族**/
	public final static String LA_HU = "拉祜族";
	/**水族**/
	public final static String SHUI = "水族";
	/**佤族**/
	public final static String WA = "佤族";
	/**纳西族**/
	public final static String NA_XI = "纳西族";
	/**羌族**/
	public final static String QIANG = "羌族";
	/**土族**/
	public final static String TU = "土族";
	/**仫佬族**/
	public final static String MU_LAO = "仫佬族";
	/**锡伯族**/
	public final static String XI_BO = "锡伯族";
	/**柯尔克孜族**/
	public final static String A_ER_KE_ZI = "柯尔克孜族";	
	/**达斡尔族**/
	public final static String DA_WO_ER = "达斡尔族";
	/**景颇族**/
	public final static String JING_PO = "景颇族";
	/**毛南族**/
	public final static String MAO_NAN = "毛南族";
	/**撒拉族**/
	public final static String SA_LA = "撒拉族";
	/**塔吉克族**/
	public final static String TA_JI_KE = "塔吉克族";
	/**阿昌族**/
	public final static String A_CHANG = "阿昌族";
	/**普米族**/
	public final static String PU_MI = "普米族";
	/**鄂温克族**/
	public final static String E_WEN_KE = "鄂温克族";
	/**怒族**/
	public final static String NU = "怒族";
	/**京族**/
	public final static String JING = "京族";
	/**基诺族**/
	public final static String JI_NUO = "基诺族";
	/**德昂族**/
	public final static String DE_ANG = "德昂族";
	/**保安族**/
	public final static String BAO_AN = "保安族";
	/**俄罗斯族**/
	public final static String E_LUO_SI = "俄罗斯族";
	/**裕固族**/
	public final static String YU_GU = "裕固族";
	/**乌兹别克族**/
	public final static String WU_ZI_BIE_KE = "乌兹别克族";
	/**门巴族**/
	public final static String MEN_BA = "门巴族";
	/**鄂伦春族**/
	public final static String E_LUN_CHUN = "鄂伦春族";
	/**独龙族**/
	public final static String DU_LONG = "独龙族";
	/**塔塔尔族**/
	public final static String TA_TA_ER = "塔塔尔族";
	/**赫哲族**/
	public final static String HE_ZHE = "赫哲族";
	/**珞巴族**/
	public final static String LUO_BA = "珞巴族";
	/**布朗族**/
	public final static String BU_LANG = "布朗族";
	
	public final static Map<String, String> ETHNIC_GROUPS = new LinkedHashMap<String, String>(){
		/** 
		* @Fields serialVersionUID : (用一句话描述这个变量表示什么) 
		*/ 
		private static final long serialVersionUID = 8795991981168522844L;
		{
			put(BaseCons.HAN, "汉族");
			put(BaseCons.ZHUANG, "壮族");
			put(BaseCons.MAN, "满族");
			put(BaseCons.HUI, "回族");
			put(BaseCons.MIAO, "苗族");
			put(BaseCons.WEI_WU_ER, "维吾尔族");
			put(BaseCons.TU_JIA, "土家族");
			put(BaseCons.YI, "彝族");
			put(BaseCons.MENG_GU, "蒙古族");
			put(BaseCons.ZANG, "藏族");
			put(BaseCons.BU_YI, "布依族");
			put(BaseCons.TONG, "侗族");
			put(BaseCons.YAO, "瑶族");
			put(BaseCons.CHAO_XIAN, "朝鲜族");
			put(BaseCons.BAI, "白族");
			put(BaseCons.HA_NI, "哈尼族");
			put(BaseCons.HA_SA_KE, "哈萨克族");
			put(BaseCons.LI, "黎族");
			put(BaseCons.DAI, "傣族");
			put(BaseCons.SHE, "畲族");
			put(BaseCons.LI_SU, "傈僳族");
			put(BaseCons.GE_LAO, "仡佬族");
			put(BaseCons.DONG_XIANG, "东乡族");
			put(BaseCons.GAO_SHAN, "高山族");
			put(BaseCons.LA_HU, "拉祜族");
			put(BaseCons.SHUI, "水族");
			put(BaseCons.WA, "佤族");
			put(BaseCons.NA_XI, "纳西族");
			put(BaseCons.QIANG, "羌族");
			put(BaseCons.TU, "土族");
			put(BaseCons.MU_LAO, "仫佬族");
			put(BaseCons.XI_BO, "锡伯族");
			put(BaseCons.A_ER_KE_ZI, "柯尔克孜族");
			put(BaseCons.DA_WO_ER, "达斡尔族");
			put(BaseCons.JING_PO, "景颇族");
			put(BaseCons.MAO_NAN, "毛南族");
			put(BaseCons.SA_LA, "撒拉族");
			put(BaseCons.TA_JI_KE, "塔吉克族");
			put(BaseCons.A_CHANG, "阿昌族");
			put(BaseCons.PU_MI, "普米族");
			put(BaseCons.E_WEN_KE, "鄂温克族");
			put(BaseCons.NU, "怒族");
			put(BaseCons.JING, "京族");
			put(BaseCons.JI_NUO, "基诺族");
			put(BaseCons.DE_ANG, "德昂族");
			put(BaseCons.BAO_AN, "保安族");
			put(BaseCons.E_LUO_SI, "俄罗斯族");
			put(BaseCons.YU_GU, "裕固族");
			put(BaseCons.WU_ZI_BIE_KE, "乌兹别克族");
			put(BaseCons.MEN_BA, "门巴族");
			put(BaseCons.E_LUN_CHUN, "鄂伦春族");
			put(BaseCons.DU_LONG, "独龙族");
			put(BaseCons.TA_TA_ER, "塔塔尔族");
			put(BaseCons.HE_ZHE, "赫哲族");
			put(BaseCons.LUO_BA, "珞巴族");
			put(BaseCons.BU_LANG, "布朗族");
		};
	};
}
