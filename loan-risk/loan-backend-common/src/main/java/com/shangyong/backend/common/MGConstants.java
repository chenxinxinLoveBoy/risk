package com.shangyong.backend.common;

/**
 * 聚信立 -- 蜜罐常量
 * @author xiangxianjin
 *
 */
public class MGConstants {

	/**
	 * 访问接口权限获取url
	 */
	public static final String MG_ACCESS_REPORT_TOKEN_URL = "MG_ACCESS_REPORT_TOKEN_URL";	
	
	/**
	 *根据 用户三要素 获取报告url
	 */
	public static final String MG_USER_REPORT_URL = "MG_USER_REPORT_URL";	

	//	public static final String ACCESS_REPORT_TOKEN_URL = "https://mi.juxinli.com/api/access_token";//访问接口权限获取
//	
//	public static final String USER_REPORT_URL = "https://mi.juxinli.com/api/search"; //根据 用户三要素 获取报告
//	
	/**
	 * 接口访问结果code
	 */
	
	/** 获取蜜罐访问令牌成功 **/
	public static final String  MIGUAN_ACCESS_SUCCESS = "MIGUAN_ACCESS_SUCCESS";//获取蜜罐访问令牌成功
	
	/** 获取蜜罐查询成功 **/
	public static final String  MIGUAN_SEARCH_SUCCESS = "MIGUAN_SEARCH_SUCCESS";//获取蜜罐查询成功
	
	/**
	 *蜜罐报告返回状态禁止项 - 身份证不合法
	 */
	public static final String MIGUAN_SEARCH_IDCARD_INVALID = "MIGUAN_SEARCH_IDCARD_INVALID";	

	/**
	 *蜜罐报告返回状态禁止项 - 姓名不合法
	 */
	public static final String MIGUAN_SEARCH_NAME_INVALID = "MIGUAN_SEARCH_NAME_INVALID";	

	/**
	 *蜜罐报告返回状态禁止项 - 手机号码不合法
	 */
	public static final String MIGUAN_SEARCH_PHONE_INVALID = "MIGUAN_SEARCH_PHONE_INVALID";	

	/**
	 *蜜罐报告返回状态禁止项 - 疑似重复查询异常
	 */
	public static final String MIGUAN_SEARCH_SIMILAR_QUERY_ERROR = "MIGUAN_SEARCH_SIMILAR_QUERY_ERROR";	

	/**
	 *蜜罐报告返回状态禁止项 - 
	 */
	public static final String MG_USER_REPORT_STATU = "MG_USER_REPORT_STATU";	
	
}
