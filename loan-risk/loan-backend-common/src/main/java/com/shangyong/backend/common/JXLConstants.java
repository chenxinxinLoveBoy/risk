package com.shangyong.backend.common;

/**
 * 聚信立 
 * @author xiangxianjin
 *
 */
public class JXLConstants {
	
	/**
	 * 访问密钥
	 */
//	
//	/**
//	 * 机构名称
//	 */
//	public static final String ORG_NAME = "xuluxinxi"; //机构名称
//	
//	/**
//	 * 登录密码
//	 */
//	public static final String PASSWORD = "27af36";		//登录密码
//	
//	/**
//	 * 访问秘钥-机构标识码
//	 */
//	public static final String CLIENT_SECRET = "da269b06dcec47659af347985368cc1d";		//访问秘钥-机构标识码

	/**
	 * 接口链接
	 */
	
	/** 聚信立获得安全凭证码URL 对应系统参数key */
	public static final String JXL_ACCESS_REPORT_TOKEN_URL = "JXL_ACCESS_REPORT_TOKEN_URL";

	/** 聚信立根据 token 获取报告 */
	public static final String JXL_TOKEN_REPORT_URL = "JXL_TOKEN_REPORT_URL";

	/** 聚信立根据 token 查询报告状态 */
	public static final String JXL_QUERY_REPORT_STATU_URL = "JXL_QUERY_REPORT_STATU_URL";
	
	/** 聚信立获取用户移动运营商原始数据URL 对应系统参数key */
	public static final String JXL_MOBLIE_OPERATOR_RAW_DATA_URL = "JXL_MOBLIE_OPERATOR_RAW_DATA_URL";

	/** 聚信立常量  对应系统参数key */
	public static final String JXL_CONSTANT = "JXL_CONSTANT";

	
//	public static final String ACCESS_REPORT_TOKEN = "https://www.juxinli.com/api/v2/access_report_token";//访问接口权限获取
//	
//	public static final String TOKEN_REPORT = "https://www.juxinli.com/api/access_report_data_by_token"; //根据 token 获取报告
//	
//	public static final String USER_REPORT = "https://www.juxinli.com/api/access_report_data"; //根据 用户三要素 获取报告
//	
//	public static final String QUERY_REPORT = "https://www.juxinli.com/api/v2/job/access_jobs_status_by_token"; //根据 token 查询报告状态
//	
//	public static final String MOBILE_OPERATOR_RAW_DATA = "https://www.juxinli.com/api/access_raw_data_by_token"; //根据 token 获取用户移动运营商原始数据

	/**
	 * url请求常量参数
	 */
	
	/** access_token 有效时间设置 **/
	public static final String HOURS_KEEP_STATU = "per";
	
	/**
	 * 用户检测 常量
	 */
	
	/** 身份证 **/
	public static final String ID_CARD = "id_card"; //身份证
	
	/** 手机号 **/
	public static final String CELL_PHONE = "cell_phone"; //手机号
	
	/** 家庭地址 **/
	public static final String HOME_ADDRESS = "home_addr"; // 家庭地址

	/** 家庭电话 **/
	public static final String HOME_PHONE = "home_phone"; //家庭电话
	
	/** 联系人 **/
	public static final String CONTACT = "contact"; //联系人

	/** 京东 **/
	public static final String JINGDONG = "jingdong"; //京东
	
	/** 淘宝 **/
	public static final String TAOBAO = "taobao"; //淘宝

}
