package com.shangyong.backend.common.exception;

/**
 * 自定义异常
 * @author xiajiyun
 *
 */
public class ZhimaParameterException extends Exception{

	private static final long serialVersionUID = -8797078175534774726L;
	
	
	private String errorCode;// 错误code
	
	private String errorMessage;// 错误内容
	
	public ZhimaParameterException(String errorCode, String errorMessage){
		super(errorCode + " : " + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ZhimaParameterException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ZhimaParameterException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
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
