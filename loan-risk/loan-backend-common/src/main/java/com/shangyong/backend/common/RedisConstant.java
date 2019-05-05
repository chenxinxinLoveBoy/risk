package com.shangyong.backend.common;

import com.shangyong.backend.common.enums.FraudBizEnum;

/**
 * redis 常量配置
 * @author xiangxianjin
 *
 */
public class RedisConstant {

	/** 聚信立安全凭证码 配置于redis中hash的key值  */
	public static final String JXL_ACCESS_TOKEN = "backend:juxinli:access_token";

	/**
	 * 获取权限token
	 */
	public static final String MG_ACCESS_TOKEN = "backend:miguan:access_token";		//获取权限token

	/**
	 * 蜜罐报文
	 */
	public static final String MG_JSON = "miguan:json:";		//蜜罐报文

	/**
	 * 芝麻欺诈清单存储在Redis的key
	 */
	public static final String ZHIMA_REDIS_QZQD_KEY = "ZhiMa:QZQD:";
	
	/**
	 * 芝麻欺诈清单存储在Redis的key(MQ用)
	 */
	public static final String ZHIMA_MQ_REDIS_QZQD_KEY = "ZhiMa:MQ:QZQD:";
	
	/**
	 * 芝麻信用评分存储在Redis的key
	 */
	public static final String ZHIMA_REDIS_ZMXYPF_KEY = "ZhiMa:ZMXYPF:";
	
	/**
	 * 芝麻信用行业关注名单存储在Redis的key
	 */
	public static final String ZHIMA_REDIS_ZMXYHYMD_KEY = "ZhiMa:ZMHYMD:";
	
	
	/**
	 * 芝麻申请欺诈评分存储在Redis的key
	 */
	public static final String ZHIMA_REDIS_SQQZPF_KEY = "ZhiMa:SQQZPF:";
	
	/**
	 * 芝麻欺诈信息验证存储在Redis的key
	 */
	public static final String ZHIMA_REDIS_QZXXYZ_KEY = "ZhiMa:QZXXYZ:";
	
	/**
	 * 芝麻欺诈信息验证存储在Redis的key
	 */
	public static final String THIRDPARTY_REDIS__OSS_KEY = "THIRDPARTY:OSS:";
	
	/**
	 * 同盾贷前审核存储在Redis的key
	 */
	public static final String TD_REDIS_DQSH_KEY = "Td:TQSH:";
	
	/**
	 * 聚信立用户移动运营商原始数据异常Redis的key
	 */
	public static final String JXLRAWDATA_EXCEPTION_DATA = "JXLRAWDATA_EXCEPTION_DATA";	
	
	/**
	 * 聚信立查询报告时间参数
	 */
	public static final String JXL_QUERY_REPORT_TIME = "JXL_QUERY_REPORT_TIME";
	
	
	/** 葫芦数据令牌access_token 配置于redis中hash的key值  */
	public static final String HL_ACCESS_TOKEN = "hl:access_token";

	/** 已推送大数据的记录数 配置于redis中hash的key值  */
	public static final String TASK_IS_HBASE = "task:ishbase:";
	
	/** 已推送人工审批的记录数 配置于redis中hash的key值  */
	public static final String TASK_RG_APPROVAL = "task:isRgApproval:";
	
	/** 小视科技 银行逾期查询 存储在Redis的key  */
	public static final String XSKJ_REDIS_BANK_KEY = "xskj_redis_bank_key";

	/** 小视科技 网贷逾期查询 存储在Redis的key  */
	public static final String XSKJ_REDIS_NETLOAN_KEY = "xskj_redis_netloan_key";
	
	/**
	 * 潘多拉黑名单身份证存储在Redis的key
	 */
	public static final String BLACKLIST_REDIS_CERTCODE_KEY = "BLACKLIST:BLACKLIST_CERTCODE_KEY";
	
	/**
	 * 潘多拉黑名单手机号存储在Redis的key
	 */
	public static final String BLACKLIST_REDIS_PHONENUM_KEY = "BLACKLIST:BLACKLIST_PHONENUM_KEY";
	
	/**
	 * 潘多拉黑名单设备ID存储在Redis的key
	 */
	public static final String BLACKLIST_REDIS_DEVICEID_KEY = "BLACKLIST:BLACKLIST_DEVICEID_KEY";

	/** app推送最后登录时间 存储在Redis的key  */
	public static final String LATEST_LOGIN_TIME_REDIS_KEY = "latest_login_time_redis_key";

	/** app推送客户app应用列表 存储在Redis的key  */
	public static final String COLLECT_APP_INFO_REDIS_KEY = "collect_app_info_redis_key";

	/** app推送客户通讯录 存储在Redis的key  */
	public static final String COLLECT_DIRECTORIES_REDIS_KEY = "collect_directories_redis_key";

	/** app推送客户短信数据 存储在Redis的key  */
	public static final String COLLECT_FEW_MESSAGE_REDIS_KEY = "collect_few_message_redis_key";

	/** app推送客户通话记录 存储在Redis的key  */
	public static final String COLLECT_CALL_RECORD_REDIS_KEY = "collect_call_record_redis_key";
	

	
	/**
	 * 欺诈评分 key 
	 *   redis数据类型: hashmap
	 * @param applicationId
	 * @param frundBizeEnum
	 * @return
	 */
	public static final String buildFraudScoresKey(String applicationId, FraudBizEnum frundBizeEnum){
		return "FRAUD:SCORES:" + applicationId + ":" + frundBizeEnum.getCode();
	}


	/**
	 * 欺诈评分 key 1.8
	 * 		redis数据类型: hashmap
	 * @param applicationId
	 * @param frundBizeEnum
	 * @return
	 */
	public static final String buildFraudScoresKey1_8(String applicationId, FraudBizEnum frundBizeEnum){
		return "FRAUD:SCORES:1.8:" + applicationId + ":" + frundBizeEnum.getCode();
	}

	/**
	 * 欺诈评分 key 2.0
	 * 		redis数据类型: hashmap
	 * @param applicationId
	 * @param frundBizeEnum
	 * @return
	 */
	public static final String buildFraudScoresKey2_0(String applicationId, FraudBizEnum frundBizeEnum){
		return "FRAUD:SCORES:2.0:" + applicationId + ":" + frundBizeEnum.getCode();
	}

	/**
	 * 用户人脸识别 json 数据
	 *  ps: app 后端 会把数据存在redis 0 库中
	 */
	public static final String buildFacePlusKey(String customerId){
		return "FACE_BUY:" + customerId;
	}

	/**
	 * 用户身份证验证 正面 json 数据
	 * ps: app 后端 会把数据存在redis 0 库中
	 * @param customerId
	 * @return
	 */
	public static final String buildCardPostiveKey(String customerId){
		return "CARD_POSITIVE_BUY:" +customerId;
	}

	/**
	 * 用户身份证验证 背面 json 数据
	 * ps: app 后端 会把数据存在redis 0 库中
	 * @param customerId
	 * @return
	 */
	public static final String buildCardEndKey(String customerId){
		return "CARD_END_BUY:" + customerId;
	}

	/**
	 * 构建 黑名单 省份证 分类 key
	 * @return
	 */
	public static final String buildBlackListCertClassKey(String classCode){
		return RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY + ":" + classCode;
	}

	/**
	 * 构建 黑名单 手机号 分类 key
	 * @return
	 */
	public static final String buildBlackListPhoneClassKey(String classCode){
		return RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY + ":" + classCode;
	}

	/**
	 * 构建 黑名单 设备Id 分类 key
	 * @return
	 */
	public static final String buildBlackListDeviceIdClassKey(String classCode){
		return RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY + ":" + classCode;
	}
}
