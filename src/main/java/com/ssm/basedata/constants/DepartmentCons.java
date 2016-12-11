package com.ssm.basedata.constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DepartmentCons {

    /** 启用部门 value = 1 **/
    public final static Integer OPEN_DEP = 1;
    /** 停用部门 value = 0 **/
    public final static Integer CLOSE_DEP = 0;

    /** 所亭 value = 1 **/
    public final static Integer TRAN_STATION = 1;
    /** 区间站场 value = 3 **/
    public final static Integer SECTION = 3;

    /** 管辖范围类型MAP **/
    public final static Map<Integer, String> JURISDICT_MAP = new HashMap<Integer, String>(){

        private static final long serialVersionUID = -6621842525595382635L;
        {
            this.put(DepartmentCons.TRAN_STATION, "变电所");
            this.put(DepartmentCons.SECTION, "区间战场");
        }
    };

    /** 段 value = 1 **/
    public static final String SUPPLY_SECTION = "1";
    /** 车间 value = 2 **/
    public static final String SUPPLY_WORKSHOP = "2";
    /** 工区 value = 3 **/
    public static final String WORK_AREA = "3";
    /** 其他 value = 5 **/
    public static final String OTHER = "5";

    public static final Map<String, String> TYPE_MAP = new LinkedHashMap<String, String>(){
        private static final long serialVersionUID = -6632392759049945868L;
        {
            this.put(DepartmentCons.SUPPLY_SECTION, "段");
            this.put(DepartmentCons.SUPPLY_WORKSHOP, "车间");
            this.put(DepartmentCons.WORK_AREA, "工区");
            this.put(DepartmentCons.OTHER, "其他");
        }
    };

}
