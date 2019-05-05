package com.shangyong.backend.common.consts;

import com.shangyong.backend.common.enums.FraudBizEnum;

/**
 * Redis key 定义
 */
public class RedisKeyConstants {

    /**
     * 参数配置表于redis中hash的key值 SYS_PARAM_INFO
     **/
    public static final String RISK_SYS_PARAM_INFO = "RISK_SYS_PARAM_INFO";

    /** 已推送人工审批的记录数 配置于redis中hash的key值  */
    public static final String TASK_RG_APPROVAL = "task:isRgApproval:";

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
}
