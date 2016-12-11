package com.ssm.basedata.entity;

import com.ssm.core.frame.common.IdEntity;

import java.util.Date;

/**
 *
 * 用户表
 *
 */
public class Employee extends IdEntity {

	private static final long serialVersionUID = -115678341402144496L;
	/** 登录帐号 **/ 
	private String loginName;
	/** 登录密码 **/ 
	private String password;
	/** 姓名 **/ 
	private String userName;
	/** 性别，1-男，0-女 **/ 
	private Integer sex;
	/** 电话 **/ 
	private String phone;
	/** 手机 **/ 
	private String mobile;
	/** 住址 **/ 
	private String address;
	/** 注销  0：未注销，1：已注销 **/ 
	private Integer cancel;
	/** 是否是系统用户 **/ 
	private String systemUser;
	/** 创建人ID **/ 
	private Integer createrId;
	/** 创建时间 **/ 
	private Date createrTime;
	/** 修改人ID **/ 
	private Integer updaterId;
	/** 修改时间 **/ 
	private Date updaterTime;
	/** 修改ip **/ 
	private String updaterIp;
	/** IP地址 **/ 
	private String ipAddress;
	/** 激光推送ID **/ 
	private Integer jpushId;
	/** 安全级别ID **/ 
	private Integer safeLevelId;
	/** 安全级别 **/ 
	private String safeLevel;
	
	private Integer departmentId;
	private String departmentIds;
	private String departmentName;
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/** 无参的构造函数 **/
	public Employee(){
		super();
	}
	
	/** 有参构造函数 **/
	public Employee(String loginName,String password,String userName,Integer sex,String phone,String mobile,String address,Integer cancel,String systemUser,Integer createrId,Date createrTime,Integer updaterId,Date updaterTime,String updaterIp,String ipAddress,Integer jpushId,Integer safeLevelId,String safeLevel){
		super();
		this.loginName = loginName;
		this.password = password;
		this.userName = userName;
		this.sex = sex;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.cancel = cancel;
		this.systemUser = systemUser;
		this.createrId = createrId;
		this.createrTime = createrTime;
		this.updaterId = updaterId;
		this.updaterTime = updaterTime;
		this.updaterIp = updaterIp;
		this.ipAddress = ipAddress;
		this.jpushId = jpushId;
		this.safeLevelId = safeLevelId;
		this.safeLevel = safeLevel;
	}
 	
		
	/**
	 * @return 登录帐号
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName 登录帐号
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
		
	/**
	 * @return 登录密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password 登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
		
	/**
	 * @return 姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName 姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
		
	/**
	 * @return 性别，1-男，0-女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param sex 性别，1-男，0-女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
		
	/**
	 * @return 电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone 电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
	/**
	 * @return 手机
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile 手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
		
	/**
	 * @return 住址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address 住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
		
	/**
	 * @return 注销  0：未注销，1：已注销
	 */
	public Integer getCancel() {
		return cancel;
	}
	/**
	 * @param cancel 注销  0：未注销，1：已注销
	 */
	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
		
	/**
	 * @return 是否是系统用户
	 */
	public String getSystemUser() {
		return systemUser;
	}
	/**
	 * @param systemUser 是否是系统用户
	 */
	public void setSystemUser(String systemUser) {
		this.systemUser = systemUser;
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
		
	/**
	 * @return IP地址
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress IP地址
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
		
	/**
	 * @return 激光推送ID
	 */
	public Integer getJpushId() {
		return jpushId;
	}
	/**
	 * @param jpushId 激光推送ID
	 */
	public void setJpushId(Integer jpushId) {
		this.jpushId = jpushId;
	}
		
	/**
	 * @return 安全级别ID
	 */
	public Integer getSafeLevelId() {
		return safeLevelId;
	}
	/**
	 * @param safeLevelId 安全级别ID
	 */
	public void setSafeLevelId(Integer safeLevelId) {
		this.safeLevelId = safeLevelId;
	}
		
	/**
	 * @return 安全级别
	 */
	public String getSafeLevel() {
		return safeLevel;
	}
	/**
	 * @param safeLevel 安全级别
	 */
	public void setSafeLevel(String safeLevel) {
		this.safeLevel = safeLevel;
	}
 	
}
