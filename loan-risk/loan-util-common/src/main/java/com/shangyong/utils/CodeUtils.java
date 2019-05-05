package com.shangyong.utils;


/**
* 
* @author gdl
* @version 1.0
*/
public enum CodeUtils {
	
	SUCCESS("200","成功",true),
	FAIL("500","失败",false),
	PHONE_FAIL("500","手机格式错误",false),
	
	SMS_SEND_SUCCESS("200","短信发送成功，请查收",true),
	AUDIO_SEND_SUCCESS("200","语音发送成功，请接听",true),
	SMS_SEND_FAILUE("200","短信发送失败",false),
	PHONE_NUMBER_NULL("200","手机号码不能为空",false),
	SMS_CODE_ERROR("200","请不要频繁获取短信,请稍后再试",false),
	CHANNEL_TYPE_NULL("200","通道类型不能为空",false),
	SMS_CHANNEL_CLOSED("200","短信通道已经关闭",false),
	AUDIO_CHANNEL_CLOSED("200","语音通道已经关闭",false),
	CHANNEL_TYPE_ERROR("200","通道类型错误",false),
	PHONE_NUMBER_ERROR("200","请输入正确手机号码",false),
	VALIDATE_CODE_NULL("200","短信验证码不能为空",false),
	GET_VALIDATE_CODE("200","请重新获取短信验证码",false),
	LOGIN_SUCCESS("200","登录成功",true),
	LOGIN_FAILUE("200","登录失败",false),
	SMS_VALIDATE_CODE_TRUE("200","短信验证码正确",true),
	SMS_VALIDATE_CODE_ERROR("200","短信验证码错误",false),
	LOGOUT_SUCCESS("200","用户退出成功",true),
	LOGOUT_FAILUE("200","用户退出失败",false),
	NO_LOGIN("1009","请登录",false),
	AUDIO_SEND_FAILUE("200","语音发送失败",false),
	PHONE_NUMBER_REGIST("200","手机号码已注册",true),
	PHONE_NUMBER_NOT_REGIST("200","手机号码未注册",false),
	PASSWORD_NULL("200","密码不能为空",false),
	PASSWORD_LENGTH_ERROR("200","密码长度必须是6到16位",false),
	REGISTER_SUCCESS("200","注册成功",true),
	REGISTER_FAILUE("200","注册失败",false),
	PASSWORD_ERROR("200","登录密码错误",false),
	TOKEN_NULL("200","token不能为空",false),
	UPDATE_PASSWORD_SUCCESS("200","密码修改成功",true),
	UPDATE_PASSWORD_FAILUE("200","密码修改失败",false),
	OLD_PASSWORD_NULL("200","原密码不能为空",false),
	OLD_PASSWORD_LENGTH_ERROR("200","原密码长度必须是6到16位",false),
	NEW_PASSWORD_NULL("200","新密码不能为空",false),
	NEW_PASSWORD_LENGTH_ERROR("200","新密码长度必须是6到16位",false),
	RE_PASSWORD_NULL("200","确认密码不能为空",false),
	RE_PASSWORD_LENGTH_ERROR("200","确认密码长度必须是6到16位",false),
	PASSWORD_NOT_SAME("200","新密码和确认密码不一样",false),
	CUSTOMER_NOTICE_NULL("200","暂无消息",true),
	CUSTOMER_NOTICE_ID_NULL("200","用户消息主键不能为空",false),
	MARKET_NULL("200","市场渠道不能为空",false),
	OSVERSION_NULL("200","OSVERSION不能为空",false),
	APPVERSION_NULL("200","APPVERSION不能为空",false),
	DEVICEID_NULL("200","DEVICEID不能为空",false),
	SDKVERSION_NULL("200","SDKVERSION不能为空",false),
	DATA_NULL("200","DATA不能为空",false),
	NO_UPDATE("200","无需更新",false),
	BLACK_BOX_NULL("200","BLACKBOX不能为空",false),
	IP_NULL("200","IP不能为空",false),
	TONGDU_NULL("200","获取同盾数据失败",false),
	SMS_BUSY_ERRROR("200","1小时内手机接收验证码次数过于频繁",false),
	

	FILE_UPLOAD_SUCCESS("200","上传成功",true),
	FILE_UPLOAD_NULL("500","文件为空",false),
	FILE_UPLOAD_SIZE_ERROR("500","文件大小超出限制",false),
	FILE_UPLOAD_TYPE_ERROR("500","文件类型错误",false),
	FILE_UPLOAD_FAIL("500","上传失败",false),
	PARAM_ERROR("500","参数错误",false),
	FIND_PARAM_NOT_EXIST("500","数据不存在",false),
	EMAIL_ERROR("500","邮箱格式错误",false),
	WECHAT_ERROR("500","微信格式错误",false),
	TAOBAO_ERROR("500","淘宝账号错误",false),
	QQ_ERROR("500","QQ号码错误",false),
	SAVE_SUCCESS("200","保存成功",true),
	SAVE_FAIL("500","保存失败",false),
	COMPANYTEL_ERROR("500","公司固话号码错误",false),
	USER_JXL_TOKEN_ERROR("500","用户认证信息失效",false),
	USER_INFO_NOT_EXIST("500","请先填写个人信息",false);

	private String code;//错误码
	private String message;//错误消息
	private Boolean flag;//返回状态
	
	private CodeUtils(String code, String message,Boolean flag) {
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
