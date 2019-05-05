package com.shangyong.backend.common.consts;

/**
 * redis的key常量类
 * 
 * @author xk
 *
 */
public class RedisConst {

	/** 参数配置表于redis中hash的key值 SYS_PARAM_INFO **/
	public static final String RISK_SYS_PARAM_INFO = "RISK_SYS_PARAM_INFO";

	/** 规则配置表于redis中hash的key值 SC_BAN_CONTROL **/
	public static final String RISK_SC_BAN_CONTROL = "RISK_SC_BAN_CONTROL";

	/** 数据字典大类表于redis中hash的key值 RISK_DICT__BIG_CODE **/
	public static final String RISK_DICT_BIG_CODE = "RISK_DICT_BIG_CODE";

	/** 数据字典小类表于redis中hash的key值 RISK_DICT__SMALL_CODE **/
	public static final String RISK_DICT_SMALL_CODE = "RISK_DICT_SMALL_CODE";
	
	/**add: xiajiyun, 数据字典小类表于redis中hash的key值 GET_RISK_DICT_SMALL_CODE **/
	public static final String GET_RISK_DICT_SMALL_CODE = "SJZD:GET_RISK_DICT_SMALL_CODE:";

	/** 评分卡大类表于redis中hash的key值 RISK_SC_SCORE_BIG **/
	public static final String RISK_SC_SCORE_BIG = "RISK_SC_SCORE_BIG";

	/** 评分卡小类表于redis中hash的key值 RISK_SC_SCORE_SMALL **/
	public static final String RISK_SC_SCORE_SMALL = "RISK_SC_SCORE_SMALL";
	 
	/** 黑名单表于redis中hash的key值 RISK_BLACK_CERT_CODE **/
	public static final String RISK_BLACK_CERT_CODE = "RISK_BLACK_CERT_CODE";

	/** 白名单表于redis中hash的key值   *RISK_SC_WHITE_CERCODE*/
	public static final String RISK_SC_WHITE_CERCODE  = "RISK_SC_WHITE_CERCODE";
	
	/**
	 * 用户登录失败于Redis中的hash的key值
	 */
	public static final String LOGIN_FLAG_USER_CODE = "login_flag_user:";
}
