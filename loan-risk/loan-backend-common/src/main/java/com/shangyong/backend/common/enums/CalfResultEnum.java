package com.shangyong.backend.common.enums;


/**
* 自定义返回信息
* @author zhangze
*/
public enum CalfResultEnum {
	FAIL_PARAMS_INVALID("10001","参数无效",false),
	FAIL_PARAMS_NULL("10002","参数为空",false),
	FAIL_PWD_EASY("10003","密码过于简单",false),
	FAIL_OBJECT_NULL("10004","对象不存在",false),
	FAIL_SCHEDULE_ERROR("10005","定时任务 ERROR",false),
	FAIL_SCHEDULE_CRON("10006","CRON ERROR",false);
	
	
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
	
	private CalfResultEnum(String code, String message,Boolean flag) {
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
