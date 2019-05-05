package com.shangyong.backend.entity.xczx;

public class XczxQueryUserLog {

	private String applicationQueryId;
	private String realName;
	private String idCard;
	private String createTime;
	private String modifyTime;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApplicationQueryId() {
		return applicationQueryId;
	}
	public void setApplicationQueryId(String applicationQueryId) {
		this.applicationQueryId = applicationQueryId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Override
	public String toString() {
		return "XczxQueryUserLog [applicationQueryId=" + applicationQueryId + ", realName=" + realName + ", idCard="
				+ idCard + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
	
}
