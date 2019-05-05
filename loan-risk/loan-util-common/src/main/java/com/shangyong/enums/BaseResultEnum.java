package com.shangyong.enums;


/**
* 基础返回信息
* @author zhangze
*/
public enum BaseResultEnum {
	SUCCESS("200","成功",true),
	FAIL("303","失败",false),
	ERROR("99999","系统内部错误",false);
	
	/**
	 * 错误码
	 */
	private String code;
	/**
	 * 错误描述
	 */
	private String message;
	/**
	 * 返回状态
	 */
	private Boolean flag;
	
	private BaseResultEnum(String code, String message,Boolean flag) {
		this.code = code;
		this.message = message;
		this.flag = flag;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
}
