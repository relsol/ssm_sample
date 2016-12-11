package com.ssm.core.frame.common;

public class ZTreeNode {

	private Integer id;
	
	private Integer pId;
	
	private boolean open;
	
	private String name;
	
	private String file;

	private boolean nocheck;
	
	public ZTreeNode() {
	}

	public ZTreeNode(Integer id, Integer pId, boolean open, String name) {
		this.id = id;
		this.pId = pId;
		this.open = open;
		this.name = name;
	}
	
	public ZTreeNode(Integer id, Integer pId, boolean open, String name,String file) {
		this.id = id;
		this.pId = pId;
		this.open = open;
		this.name = name;
		this.file = file;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
}
