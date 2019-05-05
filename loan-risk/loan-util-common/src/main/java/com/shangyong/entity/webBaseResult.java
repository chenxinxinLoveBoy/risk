package com.shangyong.entity;

/**
 * HTTP请求返回的最外层对象
 * @author zhangze
 *
 * @param <T>
 */
public class webBaseResult<T> {
	/**
	 * 错误码
	 */
	private String code;
	/**
	 * 提示信息
	 */
	private String message;
	/**
	 * 具体内容
	 */
	private T data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "BaseResult [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
}
