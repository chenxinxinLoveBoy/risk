package com.shangyong.common;

import java.io.Serializable;

/**
 * 接口服务返回类型
 * @author xixinghui
 *
 */
public class DubboBaseResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 返回状态标识：成功：success/失败：failure */
	private String state;
	
	/** 返回错误码 */
	private String errorCode;
	
	/** 返回错误信息 */
	private String errorMsg;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "DubboBaseResponse [state=" + state + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
}
