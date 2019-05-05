
package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年8月8日  
 *
 */
public class IsBlacklistResult implements Serializable{

	private static final long serialVersionUID = -8986428977721648242L;
	
	/** 是否是黑名单*/
	private boolean blacklistFlag = false;
	
	/** 黑名单来源*/
	private String dsSource;
	
	/** 黑名单联系人分类代码*/
	private String classCode;
	
	/** 禁止项编码*/
	private String banCode;
	
	private String remark;

	public boolean isBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(boolean blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	public String getDsSource() {
		return dsSource;
	}

	public void setDsSource(String dsSource) {
		this.dsSource = dsSource;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getBanCode() {
		return banCode;
	}

	public void setBanCode(String banCode) {
		this.banCode = banCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "IsBlacklistResult [blacklistFlag=" + blacklistFlag + ", dsSource=" + dsSource + ", classCode="
				+ classCode + ", banCode=" + banCode + ", remark=" + remark + "]";
	}

}

