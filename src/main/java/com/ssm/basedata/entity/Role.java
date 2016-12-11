package com.ssm.basedata.entity;
import com.ssm.core.frame.common.IdEntity;

import java.util.Date;

/**
 *
 * 角色
 *
 */
public class Role extends IdEntity {

	private static final long serialVersionUID = -5921919524171792350L;
	/** 角色名称 **/ 
	private String name;
	/** 角色编号 **/ 
	private String code;
	/** 状态 1-启用，0-停用 **/ 
	private Integer status;
	/** 备注 **/ 
	private String remark;
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
	public Role(){
		super();
	}
	
	/** 有参构造函数 **/
	public Role(String name, String code, Integer status, String remark, Integer createrId, Date createrTime,
			String createrIp, Integer updaterId, Date updaterTime, String updaterIp) {
		super();
		this.name = name;
		this.code = code;
		this.status = status;
		this.remark = remark;
		this.createrId = createrId;
		this.createrTime = createrTime;
		this.createrIp = createrIp;
		this.updaterId = updaterId;
		this.updaterTime = updaterTime;
		this.updaterIp = updaterIp;
	}
 	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 角色名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 角色名称
	 */
	public void setName(String name) {
		this.name = name;
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
