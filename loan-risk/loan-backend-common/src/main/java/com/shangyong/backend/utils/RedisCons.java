package com.shangyong.backend.utils;

/**
 * redis的key常量类
 * 
 * @author xk
 *
 */
public class RedisCons {

	/** 参数配置表于redis中hash的key值 SYS_PARAM_INFO **/
	public static final String RISK_SYS_PARAM_INFO = "RISK_SYS_PARAM_INFO";
	
	/** 业务参数配置表于redis中hash的key值 RISK_BUSINESS_PARAM_INFO **/
	public static final String RISK_BUSINESS_PARAM_INFO = "RISK_BUSINESS_PARAM_INFO";

	/** 规则配置表于redis中hash的key值 SC_BAN_CONTROL **/
	public static final String RISK_SC_BAN_CONTROL = "RISK_SC_BAN_CONTROL";

	/** 数据字典大类表于redis中hash的key值 RISK_DICT__BIG_CODE **/
	public static final String RISK_DICT_BIG_CODE = "RISK_DICT_BIG_CODE";

	/** 数据字典小类表于redis中hash的key值 RISK_DICT__SMALL_CODE **/
	public static final String RISK_DICT_SMALL_CODE = "RISK_DICT_SMALL_CODE";

	/** add: xiajiyun, 数据字典小类表于redis中hash的key值 GET_RISK_DICT_SMALL_CODE **/
	public static final String GET_RISK_DICT_SMALL_CODE = "SJZD:GET_RISK_DICT_SMALL_CODE:";
	/** add: ken, 数据字典小类表于redis中hash的key值，包含失败 GET_RISK_DICT_SMALL_CODE **/
	public static final String GET_RISK_DICT_SMALL_CODE_STATUS = "SJZD:GET_ALL_DICT_SMALL_CODE:";

	/** 评分卡大类表于redis中hash的key值 RISK_SC_SCORE_BIG **/
	public static final String RISK_SC_SCORE_BIG = "RISK_SC_SCORE_BIG";

	/** 评分卡小类表于redis中hash的key值 RISK_SC_SCORE_SMALL **/
	public static final String RISK_SC_SCORE_SMALL = "RISK_SC_SCORE_SMALL";

	/** 黑名单表于redis中hash的key值 RISK_BLACK_CERT_CODE **/
	public static final String RISK_BLACK_CERT_CODE = "RISK_BLACK_CERT_CODE";

	/** 白名单表于redis中hash的key值 *RISK_SC_WHITE_CERCODE */
	public static final String RISK_SC_WHITE_CERCODE = "RISK_SC_WHITE_CERCODE";

	/**
	 * 用户登录失败于Redis中的hash的key值
	 */
	public static final String LOGIN_FLAG_USER_CODE = "login_flag_user:";
	
	/** 欺诈规则配置表于redis中hash的key值 SC_FRAUD_RULE **/
	public static final String RISK_SC_FRAUD_RULE = "RISK_SC_FRAUD_RULE";

	
	/** 提升额度比例于redis中hash的key值 DEFAULT_PROMOTION_QUOTA **/
	public static final String  DEFAULT_PROMOTION_QUOTA = "DEFAULT_PROMOTION_QUOTA";


	/** mq消息批量重发于redis中key值 MQ_MSG_RESEND_CODE **/
	public static final String MQ_MSG_RESEND_CODE = "MQ_MSG_RESEND_CODE";

	/** add: kenzhao, 数据字典小类表于redis中hash的key值 GET_RISK_DICT_SMALL_CODE_MAP **/
	public static final String GET_RISK_DICT_SMALL_CODE_MAP = "SJZD:GET_RISK_DICT_SMALL_CODE_MAP:";

	/** mq消息批量于redis中key值 MQ_MSG_RESEND_BACKEND_CODE **/
	public static final String MQ_MSG_RESEND_BACKEND_CODE = "MQ_MSG_RESEND_BACKEND_CODE";
	
	
	/** mq消息批量补发推送消息数据至APP于redis中key值 MQ_MSG_RESEND_CODE **/
	public static final String MQ_MSG_RESEND_APP_CODE = "MQ_MSG_RESEND_APP_CODE";

	/** APP信用报告于redis中key值 RISK_APP_CREADIT_REPORT **/
	public static final String RISK_APP_CREADIT_REPORT = "RISK_APP_CREADIT_REPORT";
	
	/** APP信用报告 7天多头借贷 **/
	public static final String RISK_APP_CREADIT_WEEK = "RISK_APP_CREADIT_WEEK";

	/** APP信用报告 1个月多头借贷 **/
	public static final String RISK_APP_CREADIT_MONTH = "RISK_APP_CREADIT_MONTH";

	/** APP信用报告 3个月多头借贷 **/
	public static final String RISK_APP_CREADIT_THREE_MONTH= "RISK_APP_CREADIT_THREE_MONTH";

	/** APP信用报告 朋友圈在哪儿 **/
	public static final String RISK_APP_CREADIT_LOCATION = "RISK_APP_CREADIT_LOCATION";

	/** APP信用报告 直接黑名单 **/
	public static final String RISK_APP_CREADIT_DIRECT_BLACK= "RISK_APP_CREADIT_DIRECT_BLACK";
	
	/** APP信用报告 间接黑名单 **/
	public static final String RISK_APP_CREADIT_SECOND_BLACK = "RISK_APP_CREADIT_SECOND_BLACK";
	
}
