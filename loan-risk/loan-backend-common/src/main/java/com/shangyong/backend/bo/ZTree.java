package com.shangyong.backend.bo;


/**
 * zTree树
 * @author xiajiyun
 *
 */
public class ZTree {

	private String  id;// 唯一标识id
	
	private String pId;// 父节点id
	
	private String name;// 节点名称
	
	private boolean checked; // 是否勾选
	
	private boolean open; // 是否展开

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
	
	
}
