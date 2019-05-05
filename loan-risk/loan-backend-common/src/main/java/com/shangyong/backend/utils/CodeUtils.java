package com.shangyong.backend.utils;

  
/**
* 
* @author gdl
* @version 1.0
*/
public enum CodeUtils {
	
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
	APPLICATION_ID_NULL("303","查询申请单id为空",false),// 说明，BACKEND开头是后台系统定义
	APPLICATION_MONGO_NULL("303","mongo未查到数据",false),// 说明，mongo未查到数据
	SCORE_PARAM_FAIL("103", "该参分数范围与已存在的数据有重叠,请重新输入!", false),
	BACKEND_PRA_MISS("6001","缺少参数",false),
	BACKEND_OPT_ERROR("6002","操作失败",false),
	BACKEND_PHONE_MISS("6003","非有效的11位手机号",false),
	BACKEND_OBJECT_NULL("6004","参数对象为空",false),
	BACKEND_OPT_FAIL("6005","模板有效权重总和不能超过100%",false),
	BACKEND_NOT_ALOW("6006","参数不合法",false),
	BACKEND_REPEAT("6007","优先级疑似重复",false),
	VERSION_FAIL("7002","当前修改的数据包含版本号不是最新，请刷新页面重新再试！",false),
	FORBIDDEN_FAIL("8001","模板下至少要包含一条有效规则！",false),
	FAILURE_TIMES("8002","当前次数无需清除！",false),
	TREE_FAIL("8003","决策树下模板状态必须全部有效！",false),
	UPDATE_TPL_FAIL("8004","模板状态修改失败,模板存在有效的关联决策树",false),
	XCZX_FAIL("8005","该用户未发起过91征信查询",false),
	UPDATE_TREE_FAIL("8006","默认基础决策树状态不允许修改为失效！",false),
	TOKEN_ERROR("8007","秘钥有误，非法访问！",false),
	OPEN_TREE_FAIL("8008","需要打开的决策树已是打开状态，决策树切换失败！",false),
	CLOSE_TREE_FAIL("8009","需要关闭的决策树已是关闭状态，决策树切换失败！",false),
	OPEN_TREE_NON_EXISTENT("8010","需要打开的决策树不存在，决策树切换失败！",false),
	CLOSE_TREE_NON_EXISTENT("8011","需要关闭的决策树不存在，决策树切换失败！",false),
	SCLIFTCONFIGURATION_SAVE_FAIL("9001","是否匹配手机号、是否匹配证件号码、是否匹配姓名中至少有一种选择为是",false);
	
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
