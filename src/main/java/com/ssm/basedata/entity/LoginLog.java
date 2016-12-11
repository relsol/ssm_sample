package com.ssm.basedata.entity;

import com.ssm.core.frame.common.IdEntity;

import java.util.Date;

public class LoginLog extends IdEntity {

	private static final long serialVersionUID = -2238577765631468014L;

	private Integer employeeId;
	private String employeeName;
	private Date loginTime;
	private String loginIp;
	private String sysCode;
	
	public LoginLog(){}
	
	public LoginLog(Integer employeeId, String employeeName, Date loginTime,
			String loginIp, String sysCode) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.sysCode = sysCode;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
}
