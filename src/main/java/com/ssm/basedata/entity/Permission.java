package com.ssm.basedata.entity;
import com.ssm.core.frame.common.IdEntity;

import java.util.List;

/**
 *
 * 资源
 *
 */
public class Permission extends IdEntity {

	private static final long serialVersionUID = 8671442696656320583L;
	/** 资源名称 **/ 
	private String name;
	/** uri **/ 
	private String uri;
	/** 上级资源ID **/ 
	private Integer pid;
	/** 备注 **/ 
	private String remark;
	/** 排序 **/ 
	private Integer order;
	/** 级别1:导航  2:菜单  3:button **/
	private Integer level;
	/** 项目ID **/ 
	private Integer projectId;
	
	//非数据库字段： 子权限
	private List<Permission> children;
	
	
	
	/** 无参的构造函数 **/
	public Permission(){
		super();
	}
	
	/** 有参构造函数 **/
	public Permission(String name,String uri,Integer pid,String remark,Integer order,Integer level, Integer projectId){
		super();
		this.name = name;
		this.uri = uri;
		this.pid = pid;
		this.remark = remark;
		this.order = order;
		this.level = level;
		this.projectId = projectId;
	}
 	
		
	/**
	 * @return 资源名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 资源名称
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * @return uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
		
	/**
	 * @return 上级资源ID
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid 上级资源ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
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
	 * @return 排序
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order 排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
		
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return 项目ID
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId 项目ID
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * @return 子权限
	 */
	public List<Permission> getChildren() {
		return children;
	}
	/**
	 * @param children 子权限
	 */
	public void setChildren(List<Permission> children) {
		this.children = children;
	}
 	
}
