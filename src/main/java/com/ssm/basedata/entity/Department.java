package com.ssm.basedata.entity;

import com.ssm.core.frame.common.IdEntity;

import java.util.Date;

/**
 *
 * 机构
 *
 */
public class Department extends IdEntity {

	private static final long serialVersionUID = 5516365593534380362L;
	/** 机构编号 **/ 
	private String code;
	/** 部门名称 **/ 
	private String name;
	/** 机构类型 **/ 
	private String type;
	/** 值班电话 **/ 
	private String dutyPhone;
	/** 地址 **/ 
	private String address;
	/** 状态 1-启用，0-停用 **/ 
	private Integer status;
	/** 备注 **/ 
	private String remark;
	/** 父ID **/ 
	private Integer pid;
	/** 排序号 **/ 
	private Integer order;
	/** 创建人ID **/ 
	private Integer createrId;
	/** 创建时间 **/ 
	private Date createrTime;
	/** 创建IP **/ 
	private String createrIp;
	/** 修改人ID **/ 
	private Integer updaterId;
	/** 修改时间 **/ 
	private Date updaterTime;
	/** 修改ip **/ 
	private String updaterIp;
	
	/** 无参的构造函数 **/
	public Department(){
		super();
	}
	
	/** 有参构造函数 **/
	public Department(String code,String name,String type,String dutyPhone,String address,Integer status,String remark,Integer pid,Integer order,Integer createrId,Date createrTime,String createrIp,Integer updaterId,Date updaterTime,String updaterIp){
		super();
		this.code = code;
		this.name = name;
		this.type = type;
		this.dutyPhone = dutyPhone;
		this.address = address;
		this.status = status;
		this.remark = remark;
		this.pid = pid;
		this.order = order;
		this.createrId = createrId;
		this.createrTime = createrTime;
		this.createrIp = createrIp;
		this.updaterId = updaterId;
		this.updaterTime = updaterTime;
		this.updaterIp = updaterIp;
	}
 	
		
	/**
	 * @return 机构编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code 机构编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
		
	/**
	 * @return 部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * @return 机构类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type 机构类型
	 */
	public void setType(String type) {
		this.type = type;
	}
		
	/**
	 * @return 值班电话
	 */
	public String getDutyPhone() {
		return dutyPhone;
	}
	/**
	 * @param dutyPhone 值班电话
	 */
	public void setDutyPhone(String dutyPhone) {
		this.dutyPhone = dutyPhone;
	}
		
	/**
	 * @return 地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
		
	/**
	 * @return 状态 1-启用，0-停用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status 状态 1-启用，0-停用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
		
	/**
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
		
	/**
	 * @return 父ID
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid 父ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
		
	/**
	 * @return 排序号
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order 排序号
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
		
	/**
	 * @return 创建人ID
	 */
	public Integer getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId 创建人ID
	 */
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
		
	/**
	 * @return 创建时间
	 */
	public Date getCreaterTime() {
		return createrTime;
	}
	/**
	 * @param createrTime 创建时间
	 */
	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}
		
	/**
	 * @return 创建IP
	 */
	public String getCreaterIp() {
		return createrIp;
	}
	/**
	 * @param createrIp 创建IP
	 */
	public void setCreaterIp(String createrIp) {
		this.createrIp = createrIp;
	}
		
	/**
	 * @return 修改人ID
	 */
	public Integer getUpdaterId() {
		return updaterId;
	}
	/**
	 * @param updaterId 修改人ID
	 */
	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}
		
	/**
	 * @return 修改时间
	 */
	public Date getUpdaterTime() {
		return updaterTime;
	}
	/**
	 * @param updaterTime 修改时间
	 */
	public void setUpdaterTime(Date updaterTime) {
		this.updaterTime = updaterTime;
	}
		
	/**
	 * @return 修改ip
	 */
	public String getUpdaterIp() {
		return updaterIp;
	}
	/**
	 * @param updaterIp 修改ip
	 */
	public void setUpdaterIp(String updaterIp) {
		this.updaterIp = updaterIp;
	}
 	
}
