package com.shangyong.backend.common;

/**
 * 欺诈分规则
 * @author xiangxianjin
 *
 */
public class RiskFraudConstants {
	
	public final static String YYS_RW_TIME = "0100010";//入网时长
	
	public final static String YYS_JM_TIME = "0100011";//静默3天次数
	
	public final static String YYS_JM_ZB = "0100013";//静默天数占比
	
	public final static String SFZ_SEX = "0100008";//身份证性别
	
	public final static String YJ_TH_ZB = "0100015";//晚间活跃频率占比（23点-6点）
	
	public final static String HR_HC_SIX = "0100016";//呼入次数/呼出次数（6个月）
	
	public final static String HR_HC_THREE_SIX = "0100017";//3个月呼入次数/6个月呼入次数
	
	public final static String YYS_TH_NUMBER = "0100018";//有通话联系人个数
	
	public final static String HR_HC_THREE = "0100019";//呼入次数/呼出次数（3个月）
	
	public final static String HR_HC_ONE = "0100020";//呼入次数/呼出次数（1个月）
	
	public final static String THREE_TH_SIX_TH = "0100021";//3个月通话次数/6个月通话次数
	
	public final static String YYS_TH_LX = "0100022";//通话次数>=10联系人数
	
	public final static String TD_P2P_DT = "0100023";//同盾P2P网贷7天多头
	
	public final static String THREE_HR_SIX_HR = "0100024";//3个月呼入时长/6个月呼入时长
	
	public final static String YYS_HR_LX = "0100025";//呼入次数>=10联系人数
	
	public final static String HC_THREE_HC_SIX = "0100026";//3个月呼出次数/6个月呼出次数
	
	/**网洪反欺诈基础分**/
	public final static String HZ_QZF_SCORE = "HZ_QZF_SCORE_";
}
