package com.shangyong.exception;

/**
 * 自定义异常
 * @author zhangze
 *
 */
@SuppressWarnings("serial")
public class CalfException extends RuntimeException {
	
	public CalfException(String code, String message) {
		super(message);
		this.code = code;
		
	}
	/**
	 * 错误码
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
