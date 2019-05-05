package com.shangyong.backend.common.response;

import java.io.Serializable;

/**
 * 
 * @author xiajiyun
 *
 */
public abstract class ZhimaResponse implements Serializable{

	private static final long serialVersionUID = -7952156308805693639L;
	
	/**
	 * 成功/失败
	 */
	private boolean success;
	
	/**
	 * 错误码
	 */
	private String errorCode;
	
	/**
	 * 错误信息
	 */
	private String errorMessage;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
