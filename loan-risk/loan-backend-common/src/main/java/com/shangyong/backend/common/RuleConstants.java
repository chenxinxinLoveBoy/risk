package com.shangyong.backend.common;

/**
 * 规则校验翻译比对 -- 对照常量
 * @author xiangxianjin
 *
 */
public class RuleConstants {
	
	/**
	 * 数字
	 */
	public final static String NUM = "01";

	/**
	 * 字符
	 */
	public final static String STR = "02";

	/**
	 * 数组list
	 */
	public final static String ARR = "03";

	/**
	 * 时间区间
	 */
	public final static String TIME = "04";

	/**
	 *技术校验规则 
	 *0101-（数值）小于、0102-（数值）小于等于、0103-（数值）等于、0104-（数值）大于、0105-（数值）大于等于）
	 *0106-（数值）大于开始值小于结束值、0107-（数值）大于等于开始值小于等于结束值、0108-（数值）大于等于开始值小于结束值、0109-（数值）大于开始值小于等于结束值
	 *0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null、
	 *0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致
	 *0301-（集合）size大于0、0302-（集合）集合为null或size小于1	
	 *0401-（时间）小于、0402-（时间）小于等于、0403-（时间）等于、0404-（时间）大于、0405-（时间）大于等于）
	 *0406-（时间）大于开始值小于结束值、0407-（时间）大于等于开始值小于等于结束值、0408-（时间）大于等于开始值小于结束值、0409-（时间）大于开始值小于等于结束值
	 */

	/** 0101-（数值）小于	 **/
	public final static String NUM_LOW = "0101";
	/** 0102-（数值）小于等于	 **/
	public final static String NUM_LOW_EQ = "0102";
	/** 0103-（数值）等于	 **/
	public final static String NUM_EQ = "0103";
	/** 0104-（数值）大于	 **/
	public final static String NUM_HIGH = "0104";
	/** 0105-（数值）大于等于	 **/
	public final static String NUM_HIGH_EQ = "0105";
	/** 0106-（数值）大于开始值小于结束值	 **/
	public final static String NUM_HIGH_LOW = "0106";
	/**0107-（数值）大于等于开始值小于等于结束值	 **/
	public final static String NUM_HIGH_EQ_LOW_EQ = "0107";
	/** 0108-（数值）大于等于开始值小于结束值	 **/
	public final static String NUM_HIGH_EQ_LOW = "0108";
	/** 0109-（数值）大于开始值小于等于结束值	 **/
	public final static String NUM_HIGH_LOW_EQ = "0109";
	
	/** 0201-（字符）数据一致	 **/
	public final static String STR_EQ = "0201";
	/** 0202-（字符）不为空且不为null	 **/
	public final static String STR_NOT_BLANK = "0202";
	/** 0203-（字符）为空或为null	 **/
	public final static String STR_IS_BLANK = "0203";
	/** 0204-（字符）规则在结果中存在	 **/
	public final static String STR_IN_RESULT = "0204";
	/** 0205-（字符）规则在结果中不存在	 **/
	public final static String STR_NOT_IN_RESULT = "0205";
	/** 0206-（字符）数据不一致 **/
	public final static String STR_NOT_EQ = "0206";
	
	/**0301-（集合）条数大于、0302-（集合）条数大于等于、0303-（集合）条数小于、0304-（集合）条数小于等于、0305-（集合）条数等于 **/
	
	/** 0301-（集合）条数大于 **/
	public final static String ARR_HIGH = "0301";
	/** 0302-（集合）条数大于等于 **/
	public final static String ARR_HIGH_EQ = "0302";
	/** 0303-（集合）条数小于 **/
	public final static String ARR_LOW = "0303";
	/** 0304-（集合）条数小于等于 **/
	public final static String ARR_LOW_EQ = "0304";
	/** 0305-（集合）条数等于 **/
	public final static String ARR_EQ = "0305";
	
	/** 0401-（时间）小于	 **/
	public final static String TIME_LOW = "0401";
	/** 0402-（时间）小于等于	 **/
	public final static String TIME_LOW_EQ = "0402";
	/** 0403-（时间）等于	 **/
	public final static String TIME_EQ = "0403";
	/** 0404-（时间）大于	 **/
	public final static String TIME_HIGH = "0404";
	/** 0405-（时间）大于等于	 **/
	public final static String TIME_HIGH_EQ = "0405";
	/** 0406-（时间）大于开始值小于结束值	 **/
	public final static String TIME_HIGH_LOW = "0406";
	/**0107-（时间）大于等于开始值小于等于结束值	 **/
	public final static String TIME_HIGH_EQ_LOW_EQ = "0407";
	/** 0408-（时间）大于等于开始值小于结束值	 **/
	public final static String TIME_HIGH_EQ_LOW = "0408";
	/** 0409-（时间）大于开始值小于等于结束值	 **/
	public final static String TIME_HIGH_LOW_EQ = "0409";

	/**************************
	 * 
	 * 禁止项规则对应编号
	 * 
	 *************************/
	
	/** 01-同盾贷前审核 **/
	/** 同盾风险项列表 **/
	public final static String TD_RISK_LIST = "0100001";//同盾风险项列表
	/** 同盾贷前审核报告建议 **/
	public final static String TD_RISK_SUGGEST = "0100002";//同盾建议
	/** 同盾贷前审核报告分数 **/
	public final static String TD_RISK_SUGGEST_SCORE = "0100003";//同盾分数
	/*
	 * 0100004 - 0100009 在app端埋点检查项
	 */
	/** 七天内申请人在多个平台申请借款 **/
	public final static String TD_RISK_PLATFORM_WEEK= "0100010";
	/** 一个月内申请人在多个平台申请借款 **/
	public final static String TD_RISK_PLATFORM_MONTH = "0100011";
	/** 七天内身份证使用过多设备进行申请 **/
	public final static String TD_RISK_IDCARD_PHONE_WEEK = "0100012";
	/** 一个月内身份证使用过多设备进行申请 **/
	public final static String TD_RISK_IDCARD_PHONE_MONTH = "0100013";
	/** 3个月内身份证关联多个申请信息（手机号） **/
	public final static String TD_RISK_THREE_IDCARD_MONTH = "0100014";
	/** 身份证超过有效期或有效期在近一个月内 **/
	public final static String PLATFORM_FRAUD_IDCARD = "0100015";
	/** 通讯录正常的联系人 **/
	public final static String PLATFORM_FRAUD_DIRECTORIES = "1300007";


	/** 02-聚信立蜜蜂 **/
	/** 身份证在法院黑名单 **/
	public final static String JXL_COURT = "0200001";//"身份证在法院黑名单"
	/** 身份证在金融机构黑名单 **/
	public final static String JXL_FINANCIAL = "0200002";//"身份证在金融机构黑名单"
	/** 手机没有实名认证:姓名检查 **/
	public final static String JXL_REALNAME = "0200003";//"手机没有实名认证:姓名检查"
	/** 手机没有实名认证:身份证号检查 **/
	public final static String JXL_ID_CARD = "0200004";//"手机没有实名认证:身份证号检查"
	/** 手机是临时小号 **/
	public final static String JXL_TEMP_PHONE = "0200005";//"手机是临时小号";
	/** 有澳门通话记录 **/
	public final static String JXL_OUT_CALL = "0200006";//"有澳门通话记录"
	/** 有澳门通话记录 **/
	public final static String JXL_OUT_CALL_LESS = "0200007";//"有澳门通话记录"
	/** 110有3次以上通话记录 **/
	public final static String JXL_POLICE = "0200008";//"110有3次以上通话记录"
	/** 20有3次以上通话记录 **/
	public final static String JXL_HOSPITAL = "0200009";//20有3次以上通话记录"
	/** 律师号码有3次以上通话记录 **/
	public final static String JXL_LAWYER = "0200010";//"律师号码有3次以上通话记录"),
	/** 贷款类号码联系次数在5次以上且主动呼叫占比在20%-50%之间 **/
	public final static String JXL_LOAN = "0200011";//"贷款类号码联系次数在5次以上且主动呼叫占比在20%-50%之间"
	/** 银行类号码联系次数在5次以上且主动呼叫占比在20%-50%之间" **/
	public final static String JXL_BANK = "0200012";//"银行类号码联系次数在5次以上且主动呼叫占比在20%-50%之间"
	/** 月运营商消费数据 **/
	public final static String JXL_MONTH_PHONE_CONSUME = "0200013";
	/** 近6月运营商消费数据 **/
	public final static String JXL_TOTAL_PHONE_CONSUME= "0200014";
	/** 年龄	* */
	public final static String JXL_AGE = "0200015";
	/** 连续关机天数	* */
	public final static String JXL_PHONE_CLOSE = "0200016";//"连续关机天数"
	/** 互通过电话号码的数量	* */
	public final static String JXL_CALLED_NUM = "0200017";//"互通过电话号码的数量"
	/** 实名使用时间	* */
	public final static String JXL_REALTIME = "0200018";//"实名使用时间"
	/** 城市 **/
	public final static String JXL_CITY = "0200019";//"城市"
	/** 月运营商流量消费 **/
	public final static String JXL_MONTH_PHONE_FLOW = "0200020";
	/** 夜间活动情况(23点-6点) **/
	public final static String JXL_NIGHT_TIME= "0200021";
	/** 年龄(未成年)	* */
	public final static String JXL_AGE_CHILD = "0200022";
	
	/** 行为检测list point **/
	/** 有澳门通话记录 **/
	public final static String JXL_OUT_CALL_POINT = "contact_macao";//"有澳门通话记录"
	/** 110有3次以上通话记录 **/
	public final static String JXL_POLICE_POINT = "contact_110";//"110有3次以上通话记录"
	/** 20有3次以上通话记录 **/
	public final static String JXL_HOSPITAL_POINT = "contact_120";//20有3次以上通话记录"
	/** 律师号码有3次以上通话记录 **/
	public final static String JXL_LAWYER_POINT = "contact_lawyer";//"律师号码有3次以上通话记录"),
	/** 贷款类号码联系次数在5次以上且主动呼叫占比在20%-50%之间 **/
	public final static String JXL_LOAN_POINT = "contact_loan";//"贷款类号码联系次数在5次以上且主动呼叫占比在20%-50%之间"
	/** 银行类号码联系次数在5次以上且主动呼叫占比在20%-50%之间 **/
	public final static String JXL_BANK_POINT = "contact_bank";//"银行类号码联系次数在5次以上且主动呼叫占比在20%-50%之间"
	/** 号码使用时间 **/
	public final static String JXL_PHONE_USED_TIME = "phone_used_time";//号码使用时间
	/** 手机静默情况(连续24小时内无通话记录计为静默一天) **/
	public final static String JXL_PHONE_SILENT = "phone_silent";//手机静默情况(连续24小时内无通话记录计为静默一天)
	/** 互通过电话的号码数量 **/
	public final static String JXL_CONTACT_EACH_OTHER = "contact_each_other";//互通过电话的号码数量
	/** 夜间活动情况(23点-6点) **/
	public final static String JXL_CONTACT_NIGHT = "contact_night";//互通过电话的号码数量
	/** 朋友圈在哪里**/
	public final static String JXL_REGULAR_CIRCLE = "regular_circle";//互通过电话的号码数量
	

	/** 03-聚信立蜜罐 **/
	/** 黑中介分数10分以下 **/
	public final static String MG_GRAYSCORE = "0300001";//"黑中介分数10分以下"
	/** 直接联系人在黑名单数量超过2个 **/
	public final static String MG_DIRECTBLACK = "0300002";//"直接联系人在黑名单数量超过2个"
	/** 间接联系人在黑名单数量超过10个 **/
	public final static String MG_WITHBLACK = "0300003";//"间接联系人在黑名单数量超过10个"
	/** 引起黑名单的直接联系人数量超过5个 **/
	public final static String MG_WITHDIRECT = "0300004";//"引起黑名单的直接联系人数量超过5个"
	/** 引起黑名单的直接联系人占比超过10% **/
	public final static String MG_BLACKPER = "0300005";//"引起黑名单的直接联系人占比超过10%"
	/** 被标记的黑名单分类 **/
	public final static String MG_BLACKGORY = "0300006";//"被标记的黑名单分类"
	/** 姓名和手机是否在黑名单 **/
	public final static String MG_NAMEPHONE = "0300007";//"姓名和手机是否在黑名单"
	/** 身份证和姓名在黑名单 **/
	public final static String MG_IDCARDPHONE = "0300008";//"身份证和姓名在黑名单"
	/** 机构查询超过20次 **/
	public final static String MG_SEARCH = "0300009";//"机构查询超过20次"
	/** 手机存疑 **/
	public final static String MG_CELLPHONE = "0300010";//"手机存疑"
	/** 身份证存疑 **/
	public final static String MG_IDCARD = "0300011";//"身份证存疑"
	/** 身份证存疑：身份证号码绑定过其他姓名 **/
	public final static String MG_IDCARD_WITH_OTHER_NAME = "0300012";//"身份证存疑"
	/** 身份证存疑：身份证绑定过其他手机号码 **/
	public final static String MG_IDCARD_WITH_OTHER_CELLPHONE = "0300013";//"身份证存疑"
	/** 身份证存疑：身份证在其它机构使用过 **/
	public final static String MG_IDCARD_WITH_OTHER_ORG = "0300014";//"身份证存疑"
	/** 手机存疑：手机号码绑定过其他身份证 **/
	public final static String MG_CELLPHONE_WITH_OTHER_IDCARD = "0300015";//"手机存疑"
	/** 手机存疑：手机号码绑定过其他姓名 **/
	public final static String MG_CELLPHONE_WITH_OTHER_NAME = "0300016";//"手机存疑"
	/** 手机存疑：电话号码在其它机构使用过 **/
	public final static String MG_CELLPHONE_WITH_OTHER_ORG = "0300017";//"手机存疑"

	
	/** 04-芝麻信用 **/
	
	/** 芝麻信用评分 **/
	public final static String ZHIMA_SCORE = "0400001";//"芝麻信用评分"
	/** 芝麻信用欺诈清单 **/
	public final static String ZHIMA_VALIDATE = "0400002";//"芝麻信用欺诈清单"
	/** 欺诈风险名单明细 **/
	public final static String ZHIMA_CERTCODE_NET = "0400003";
	/** 芝麻信用行业关注名单-逾期未处理*/
	public final static String ZHIMA_WATCHLISTII_SETTLEMENT  = "0400004";
	/** 芝麻信用行业关注名单-黑名单*/
	public final static String ZHIMA_WATCHLISTII_ISMATCHED  = "0400005";
	/** 芝麻信用认证信息 **/
	public final static String ZHIMA_APPROVE = "0400006";


	/** 白骑士反欺诈云- 黑名单（Reject）*/
	public final static String BQS_BQSANTIFRAUD_FINALDECISION  = "1000002";
	/** 白骑士反欺诈云- 规则集(高风险，memo)*/
	public final static String BQS_BQSANTIFRAUD_MOME = "1000001";
	
	/** 白骑士反欺诈云- 规则集(高风险，memo) 
	 * 7条明细 ：
		 灰名单-信贷行业-曾经逾期(0 ~ 30天)
		灰名单-信贷行业-曾经逾期(未知期限)
		黑名单-信贷行业-信用/消费贷黑名单
		黑名单-信贷行业-信贷黑名单
		黑名单-信贷行业-骗取补贴
		黑名单-信贷行业-P2P黑名单
		黑名单-信贷行业-欺诈
	 * 
	 * */
	public final static String BQS_BQSANTIFRAUD_MOME_01 = "1000011";
	public final static String BQS_BQSANTIFRAUD_MOME_02 = "1000012";
	public final static String BQS_BQSANTIFRAUD_MOME_03 = "1000013";
	
	public final static String BQS_BQSANTIFRAUD_MOME_04 = "1000014";
	public final static String BQS_BQSANTIFRAUD_MOME_05 = "1000015";
	public final static String BQS_BQSANTIFRAUD_MOME_06 = "1000016";
	public final static String BQS_BQSANTIFRAUD_MOME_07 = "1000017";

	/** 05 91信用 **/
	public final static String XCZX_SCORE  = "0500001";
	
	/**腾讯云-欺诈分**/
	public final static String TXY_SCORE = "0400001";
	

	/**同盾反欺诈
	 * 5条明细
	 * 同盾反欺风险总得分
	 * 近7天申请次数
	 * 近30天申请次数
	 * 近90天申请次数
	 * 近180天申请次数
	 * **/
	public final static String TD_SEVEN_COUNT = "0900006";
	public final static String TD_THIRTY_COUNT = "0900007";
	public final static String TD_NINETY_COUNT = "0900008";
	public final static String TD_HALFYEAR_COUNT = "0900009";
	
	/** 白骑士
	 * 金融机构黑名单
	 * 法院黑名单
	 * 未实名认证
	 * 运营商姓名不匹配
	 * 互通过电话号码的去重以后的数量
	 * 入网时长
	 * 疑似赌博号码通话次数
	 * 6个月话费金额
	 * 近3个月主叫总时长
	 * 近6个月平均被叫时长
	 * 近3个月平均被叫次数
	 * 与联系人1且和联系人2都没有通话记录
	 * **/
	public final static String BQS_XINDAI_BLACK = "1100011";
	public final static String BQS_FAYUAN_BLACK = "1100012";
	public final static String BQS_REAL_NAME = "1100013";
	public final static String BQS_NAME_MISMARCHING = "1100014";
	public final static String BQS_PHONE_NUMBER = "1100015";
	public final static String BQS_SHORT_NUMBER = "1100016";
	public final static String BQS_GAMBLE_NUMBER = "1100017";
	public final static String BQS_SIX_MONEY = "1100018";
	public final static String BQS_THREE_TOTALTIME = "1100019";
	public final static String BQS_SIX_TIME = "1100020";
	public final static String BQS_THREE_MEANNUMBER = "1100021";
	public final static String BQS_PHONE_RECORD = "1100022";
	public final static String BQS_RISK_BLACK = "1100023";
	
	/** 同盾数据魔盒禁止性配置 **/
	public final static String TD_REAL_NAME = "4200001";//未实名认证
	public final static String TD_NAME_MISMARCHING = "4200002";//运营商姓名不匹配
	public final static String TD_ACCOUNT_STATE = "4200003";//账户状态非正常
	public final static String TD_SHORT_NUMBER  = "4200004";//互通过电话号码的数量
	public final static String TD_PHONE_DAY  = "4200005";//最短号码使用天数量
	public final static String TD_PHONE_MONEY  = "4200006";//6个月话费总金额(消费)
	public final static String TD_THREE_MONTH  = "4200007";//近3个月主叫总时长
	public final static String TD_SIX_VERAGE  = "4200008";//近6个月平均被叫时长
	public final static String TD_THREE_VERAGE  = "4200009";//近3个月平均被叫次数
	public final static String TD_PHONE_RECORD = "4200010" ;//与联系人1且和联系人2都没有通话记录
	public final static String TD_COLLECTION_COUNT = "4200011" ;//与催收号码通话次数 
	public final static String TD_BLACKLIST_ACCOUNTED = "4200012" ;//前10手机联系人命中黑名单占比


	/** 06 宜信禁止性配置 **/
	public final static String YX_SCORE  = "0600001";
	/** 07 极光配置禁止项**/
	public final static String JG_CIRCLE_SCORE  = "0700001";
	public final static String JG_OVERDUE_SCORE  = "0700002";
	public final static String JG_VIOLATION_SCORE  = "0700003";
	

	/** 11 小视科技禁止项配置 **/
	public final static String XSKJ_BANK_GRADE_SCORE  = "1100001";
	public final static String XSKJ_NET_GRADE_SCORE  = "1100002";
	
	
	/** 12 葫芦索伦禁止性配置 **/
//	public final static String HLSL_GRADE_SCORE  = "1200001";
//	public final static String HLSL_ID_SCORE  = "1200002";
//	public final static String HLSL_PHONE_SCORE  = "1200003";
//	public final static String HLSL_FAYUAN_SCORE  = "1200004";
//	public final static String HLSL_WDAI_SCORE  = "1200005";
//	public final static String HLSL_BANK_SCORE  = "1200006";
	


	/** 09-欺诈分拒绝 **/
	public final static String PLATFORM_FRAUD_REFUSE = "0900001";//存在拒绝名单中

	
	/**************************
	 * 
	 * 评分项常量 
	 * 
	 *************************/
	/** 年龄	* */
	public final static String SCORE_JXL_AGE = "0200001";//"年龄"
	/** 性别	* */
	public final static String SCORE_JXL_GENDER = "0200002";//"性别"
	/** 户籍所在地	* */
	public final static String SCORE_JXL_REGION = "0200003";//"户籍所在地"
	/** 实名使用时间	* */
	public final static String SCORE_JXL_REALTIME = "0200004";//"实名使用时间"
	/** 连续关机天数（占比）	* */
	public final static String SCORE_JXL_PHONE_CLOSE = "0200005";//"连续关机天数"
	/** 澳门通话	* */
	public final static String SCORE_JXL_OUT_CALL = "0200006";//"澳门通话"
	/** 与亲属联系人互动排名	* */
	public final static String SCORE_JXL_FIRST_CONTACT_ROW = "0200007";//"与亲属联系人互动排名"
	/** 与亲属联系人互通电话次数	* */
	public final static String SCORE_JXL_FIRST_CONTACT_NUM = "0200008";//"与亲属联系人互通电话次数"
	/** 与第二联系人互动排名	* */
	public final static String SCORE_JXL_SECOND_CONTACT_ROW = "0200009";//"与第二联系人互动排名"
	/** 与第二联系人互动次数	* */
	public final static String SCORE_JXL_SECOND_CONTACT_NUM = "0200010";//"与第二联系人互动次数"
	/** 互通过电话号码的数量	* */
	public final static String SCORE_JXL_CALLED_NUM = "0200011";//"互通过电话号码的数量"
	/** 通话记录有效电话号码数	* */
	public final static String SCORE_JXL_VALID_CALL = "0200012";//"通话记录有效电话号码数"
	/** 近6个月的平均话费	* */
	public final static String SCORE_JXL_CALL_CONSUME = "0200013";//"近6个月的平均话费"
	/** app上传的学历	* */
	public final static String SCORE_APP_GRADUATION = "0200014";
	/** 与第二联系人最晚沟通时间	* */
	public final static String SCORE_SECOND_CONTACT_DATE = "0200015";
	/** 与亲属联系人最晚沟通时间	* */
	public final static String SCORE_FIRST_CONTACT_DATE = "0200016";

	/** 芝麻信用 **/
	/** 历史逾期情况-芝麻信用	* */
	public final static String SCORE_HIS_OVERDUE_ZHIMA= "0400001";

	/** 91征信 **/
	/** 拒单机构占比-历史记录	* */
	public final static String SCORE_REFUSE_ORG_PERCENT = "0500001";
	/** 逾期记录占比	* */
	public final static String SCORE_OVERDUE_PERCENT = "0500002";
	/** 已还款占比	* */
	public final static String SCORE_RETURN_LOAN_PERCENT= "0500003";
	
	/** 宜信 **/
	/** 历史逾期情况-宜信 * */
	public final static String SCORE_HIS_OVERDUE_YIXIN = "0600001";
	
	/** 后台还款记录数	* */
	public final static String SCORE_LOAN_COUNT = "0800001";
	
	
	/**************************
	 * 
	 *欺诈分评分规则大类对应编号
	 * 
	 *************************/
	/** 7天内多平台借贷总数	* */
	public final static String SCORE_PLATFORM_WEEK = "0100001";
	/** 1个月内多平台借贷总数	* */
	public final static String SCORE_PLATFORM_MONTH = "0100002";
	/** 3个月内身份证关联多个申请信息	* */
	public final static String SCORE_IDCARD_APPLY_THREE_MONTH = "0100003";
	/** 3个月内申请信息关联多个身份证	* */
	public final static String SCORE_APPLY_IDCARD_THREE_MONTH = "0100004";
	/** 7天内设备使用过多的身份证或手机号进行申请	* */
	public final static String SCORE_IDCARD_CELLPHONE_WEEK = "0100005";
	/** 7天内身份证使用过多设备进行申请 **/
	public final static String SCORE_IDCARD_CONSOLE_WEEK = "0100006";
	/** 一个月内身份证使用过多设备进行申请* */
	public final static String SCORE_IDCARD_CONSOLE_MONTH = "0100007";
	/** 贷款类号码主叫个数 **/
	public final static String SCORE_LOAN_CALLING = "0200001";
	/** 贷款类号码被叫个数 * */
	public final static String SCORE_LOAN_CALLED = "0200002";
	/** 蜜罐黑中介分数 **/
	public final static String SCORE_BLCAKSOCRE = "0300001";
	/** 7天内机构查询次数 **/
	public final static String SCORE_ORG_COUNT_WEEK = "0300002";
	/** 一个月内机构查询次数 **/
	public final static String SCORE_ORG_COUNT_MONTH = "0300003";

	
	/** 命中潘多拉大数据拒绝规则 **/
	public final static String BIG_DATA_RULE = "0800002";
	
	/** 芝麻鉴权失败拒绝规则 **/
	public final static String ZHIMA_DATA_RULE = "0800003";
	/** 蜜蜂采集失败拒绝规则 **/
	public final static String MF_DATA_RULE = "0800004";
	
	/**
	 * 欺诈结果tab标签
	 */
	/** 通过 **/
	public final static String SCORE_TAB_ACCEPT = "01";
	/** 拒绝 **/
	public final static String SCORE_TAB_REFUSE = "02";
	/** 人工审核 **/
	public final static String SCORE_TAB_REJECT = "03";
	
	/** 同盾：法院详情个数>0(命中法院失信名单|||命中法院执行名单|||命中法院结案名单 )  **/
	public final static String TD_COURT_DETAILS = "0100017";
	
}
