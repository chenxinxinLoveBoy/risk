package com.shangyong.common;

public class JsonResult {

	public static String SUCCESS = "200";
	/**
	 * 返回代码
	 */
	private String code ;
	
	/**
	 * 提示信息
	 */
	private String message ; 
	
	public JsonResult(ServiceResult serviceResult){
		this.code = serviceResult.getCode();
		this.message = serviceResult.getMessage();
	}
	
	public boolean isSuccessed(){
		return getCode().equals(SUCCESS);
	}
	
	public JsonResult(String code){
		this.code = code;
	}
	public JsonResult(){
	}
	
	public JsonResult(String code,String message){
		this.code = code;
		this.message  = message;
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
}
