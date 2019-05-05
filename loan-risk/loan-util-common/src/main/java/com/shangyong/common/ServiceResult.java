package com.shangyong.common;

public class ServiceResult {

	public static String SUCCESS = "200";
	/**
	 * 返回代码
	 */
	private String code = SUCCESS;//默认成功
	
	/*
	 * 提示信息
	 */
	private String message = "操作成功"; //默认提示语
	
	private Object ext = null;
	
	public boolean isSuccessed(){
		return getCode().equals(SUCCESS);
	}
	
	public ServiceResult(String code){
		this.code = code;
	}
	public ServiceResult(){
	}
	
	public ServiceResult(String code,String message){
		this.code = code;
		this.message  = message;
	}
	public ServiceResult(String code,String message,Object ext){
		this.code = code;
		this.message  = message;
		this.ext = ext;
	}

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

	public Object getExt() {
		return ext;
	}

	public void setExt(Object ext) {
		this.ext = ext;
	}
}
