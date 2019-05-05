package com.shangyong.backend.common.enums;

  
/**
* 
* @author gdl
* @version 1.0
*/
public enum CodeEnum {
	
	SUCCESS("200","成功",true),
	FAIL("500","失败",false),
	SAVE_SUCCESS("2001","保存成功", true),
	SAVE_FAIL("2002","保存失败",false),
	DELETE_SUCCESS("3001","删除成功",true),
	DELETE_FAIL("3002","删除失败",false),
	UPDATE_SUCCESS("4001","修改成功",true),
	UPDATE_FAIL("4002","修改失败",false),
	LINEON_SUCCESS("5001","发布成功",true),
	LINEON_FAIL("5002","发布失败",false),
	SYS_PARAM_FAIL("103", "该参数记录已存在,请重新输入!", false),
	SUCCESSNEWS("101","返回成功。",true),
	NAME_FAIL("102", "已存在", false),
	NON_EXISTENT("103", "信息不存在", false),
	REST_SUCCESS("6001", "密码重置成功！", true),
	REST_FALL("6002", "密码重置失败！", false),
	DISABLED_SUCCESS("7001", "禁用成功！", true),
	DISABLED_FALL("7002", "禁用失败！", false),
	IMAGE_UPLOAD_FAILUE_TYPE("1011","上传的文件类型错误，请上传GIF、PNG、JPG图片！",false),
	BACKEND_ERROR("-200","程序异常请联系管理员",false),// 说明，BACKEND开头是后台系统定义
	SCORE_PARAM_FAIL("103", "该参分数范围与已存在的数据有重叠,请重新输入!", false),
	BACKEND_PRA_MISS("6001","缺少参数",false),
	BACKEND_OPT_ERROR("6002","操作失败",false),
	BACKEND_PHONE_MISS("6003","非有效的11位手机号",false),
	BACKEND_OBJECT_NULL("6004","参数对象为空",false),
	VERSION_FAIL("7002","当前修改的数据包含版本号不是最新，请刷新页面重新再试！",false);
	
	private String code;//错误码
	private String message;//错误消息
	private Boolean flag;//返回状态
	
	private CodeEnum(String code, String message, Boolean flag) {
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
