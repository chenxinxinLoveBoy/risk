package com.shangyong.backend.common;

/**
 * 数据字典小类常量
 * 
 * @author yin
 *
 */
public class DictConstant {

	/***************************************************** 证件类型 *********************************************************/
	/** 身份证 **/
	public static final String CERT_TYPE_ONE = "1";

	/** 护照 **/
	public static final String CERT_TYPE_TWO = "2";

	/** 其他 **/
	public static final String CERT_TYPE_THREE = "3";

	/******************************************************* APP名称 *******************************************************/
	/** 闪贷 **/
	public static final String APP_NAME_ONE = "1";

	/** 速贷 **/
	public static final String APP_NAME_TWO = "2";

	/******************************************************* 审批状态 *******************************************************/
	/** 待审批 **/
	public static final String AUDITING_STATE = "1";

	/** 审批通过 **/
	public static final String AUDITING_STATE_TWO = "2";

	/** 审批未通过 **/
	public static final String AUDITING_STATE_THREE = "3";

	/** 待人工确认 **/
	public static final String AUDITING_STATE_FOUR = "4";

	/******************************************************* 申请来源 *******************************************************/
	/** 申请来源 0——Android **/
	public static final String SOURCE = "0";

	/** 申请来源 1——IOS **/
	public static final String SOURCE_ONE = "1";

	/******************************************************* 是否通知App *******************************************************/
	/** 是否通知App 0-否 **/
	public static final String IS_PUSH_APP = "0";

	/** 是否通知App 1-是 **/
	public static final String IS_PUSH_APP_ONE = "1";

	/******************************************************* 被拒绝平台类型 *******************************************************/
	/** 01-同盾 **/
	public static final String REJECT_TYPE_ONE = "01";

	/** 02-聚信立蜜蜂 **/
	public static final String REJECT_TYPE_TWO = "02";

	/** 03-聚信立蜜罐 **/
	public static final String REJECT_TYPE_THREE = "03";

	/** 04-芝麻信用 **/
	public static final String REJECT_TYPE_FOUR = "04";

	/** 05-91信用卡 **/
	public static final String REJECT_TYPE_FIVE = "05";

	/** 06-宜信 **/
	public static final String REJECT_TYPE_SIX = "06";

	/** 07-中智诚 **/
	public static final String REJECT_TYPE_SEVEN = "07";

	/******************************************************* 被拒绝标志 *******************************************************/

	/** 被拒绝标志 0-是 **/
	public static final String REJECT_FLAG = "0";

	/** 被拒绝标志 1-否 **/
	public static final String REJECT_FLAG_ONE = "1";

	/******************************************************* 是否失效 *******************************************************/

	/** 是否失效 0-是 **/
	public static final String IS_FAILURE = "0";

	/** 是否失效 1-否 **/
	public static final String IS_FAILURE_ONE = "1";

	/******************************************************* 其它账号信息类型 *******************************************************/

	/** 其它账号信息类型 1-QQ **/
	public static final String OTHER_TYPE_ONE = "1";

	/** 其它账号信息类型 2-邮件 **/
	public static final String OTHER_TYPE_TWO = "2";

	/** 其它账号信息类型 3-微信 **/
	public static final String OTHER_TYPE_THREE = "3";

	/** 其它账号信息类型 4-淘宝 **/
	public static final String OTHER_TYPE_FOUR = "4";

	/** 其它账号信息类型 5-京东 **/
	public static final String OTHER_TYPE_FIVE = "5";

	/** 其它账号信息类型 6-支付宝 **/
	public static final String OTHER_TYPE_SIX = "6";

	/******************************************************* 账号认证标志 *******************************************************/

	/** 账号认证标志 1-已认证 **/
	public static final String CERTIFICATE_MARK_ONE = "1";

	/** 账号认证标志 2-未认证 **/
	public static final String CERTIFICATE_MARK_TWO = "2";

	/******************************************************* 类型说明 *******************************************************/

	/** 类型说明 father 父亲 **/
	public static final String TYPE_FATHER = "father";

	/** 类型说明 mother 母亲 **/
	public static final String TYPE_MOTHER = "mother";

	/** 类型说明 spouse 配偶 **/
	public static final String TYPE_SPOUSE = "spouse";

	/** 类型说明 child 子女 **/
	public static final String TYPE_CHILD = "child";

	/** 类型说明 other_relative 其他亲属 **/
	public static final String TYPE_OTHER_RELATLIVE = "other_relative";

	/** 类型说明 friend 朋友 **/
	public static final String TYPE_OTHER_FRIEND = "friend";

	/** 类型说明 coworker 同事 **/
	public static final String TYPE_COWORKER = "coworker";

	/** 类型说明 others 其他 **/
	public static final String TYPE_OTHERS = "others";

	/******************************************************* 学历 *******************************************************/

	/** PRE_HIGH_SCHOOL-高中以下 **/
	public static final String PRE_HIGH_SCHOOL = "PRE_HIGH_SCHOOL";

	/** HIGH_SCHOOL-高中/中专 **/
	public static final String HIGH_SCHOOL = "HIGH_SCHOOL";

	/** JUNIOR_COLLEGE-大专 **/
	public static final String JUNIOR_COLLEGE = "JUNIOR_COLLEGE";

	/** UNDER_GRADUATE-本科 **/
	public static final String UNDER_GRADUATE = "UNDER_GRADUATE";

	/** POST_GRADUATE-研究生 **/
	public static final String POST_GRADUATE = "POST_GRADUATE";

	/******************************************************* 职业编号 *******************************************************/
	/** ADVANCED-高级资深人员 **/
	public static final String ADVANCED = "ADVANCED";

	/** INTERMEDIATES-中级技术人员 **/
	public static final String INTERMEDIATES = "INTERMEDIATES";

	/** BEGINNERS-初级、助理人员 **/
	public static final String BEGINNERS = "BEGINNERS";

	/** PRACTICE-见习专员 **/
	public static final String PRACTICE = "PRACTICE";

	/** SENIOR-高层管理人员 **/
	public static final String SENIOR = "SENIOR";

	/** MIDDLE-中层管理人员 **/
	public static final String MIDDLE = "MIDDLE";

	/** JUNIOR-基层管理人员 **/
	public static final String JUNIOR = "JUNIOR";

	/** NORMAL-普通员工 **/
	public static final String NORMAL = "NORMAL";

	/******************************************************* 婚姻状况 *******************************************************/

	/** SPINSTERHOOD-未婚 **/
	public static final String SPINSTERHOOD = "SPINSTERHOOD";

	/** MARRIED-已婚 **/
	public static final String MARRIED = "MARRIED";

	/** DIVORCED-离异 **/
	public static final String DIVORCED = "DIVORCED";

	/** WIDOWED-丧偶 **/
	public static final String WIDOWED = "WIDOWED";

	/** REMARRY-再婚 **/
	public static final String REMARRY = "REMARRY";

	/** REMARRY_FORMER-复婚 **/
	public static final String REMARRY_FORMER = "REMARRY_FORMER";

	/******************************************************* 是否通过实名认证 *******************************************************/
	/** 是否通过实名认证 1-是， **/
	public static final String IS_ID_CHECKED_ONE = "是";

	/** 是否通过实名认证 2-否 **/
	public static final String IS_ID_CHECKED_TWO = "否";

	/******************************************************* 检查结果 *******************************************************/
	/** 检查结果 0-无数据 **/
	public static final String SCORE = "0";

	/** 检查结果 1-通过 **/
	public static final String SCORE_ONE = "1";

	/** 检查结果 2-不通过 **/
	public static final String SCORE_TWO = "2";

	/******************************************************* 身份证是否有效 *******************************************************/
	/** 身份证是否有效 0 无效 **/
	public static final String USER_IDCARD_VALID_ONE = "1";

	/** 身份证是否有效 1 有效 **/
	public static final String USER_IDCARD_VALID_TWO = "2";

	/******************************************************* 是否是本机构查询 *******************************************************/
	/** 是否是本机构查询 1-是 **/
	public static final String ORG_SELF_ONE = "1";

	/** 是否是本机构查询 0-不是 **/
	public static final String ORG_SELF = "0";

	/******************************************************* 技术比对值类型 *******************************************************/

	/** 技术比对值类型 01-数值 **/
	public static final String RULE_COMPARISON_TYPE_ONE = "01";

	/** 技术比对值类型 02-字符 **/
	public static final String RULE_COMPARISON_TYPE_TWO = "02";

	/** 技术比对值类型 03-集合 **/
	public static final String RULE_COMPARISON_TYPE_THREE = "03";

	/******************************************************* 技术校验规则 *******************************************************/
	/** 技术校验规则 0101-（数值）小于、 **/
	public static final String ALIDATE_RULE_ONE = "0101";

	/** 技术校验规则0102-（数值）小于等于、 **/
	public static final String ALIDATE_RULE_TWO = "0102";

	/** 技术校验规则 0103-（数值）等于 **/
	public static final String ALIDATE_RULE_THREE = "0103";

	/** 技术校验规则 0104-（数值）大于 **/
	public static final String ALIDATE_RULE_FOUR = "0104";

	/** 技术校验规则 0105-（数值）大于等于） **/
	public static final String ALIDATE_RULE_FIVE = "0105";

	/** 技术校验规则 0201-（字符）数据一致 **/
	public static final String ALIDATE_RULE_SIX = "0201";

	/** 技术校验规则 0202-（字符）不为空且不为null **/
	public static final String ALIDATE_RULE_SEVEN = "0202";

	/** 技术校验规则 0203-（字符）为空或为null **/
	public static final String ALIDATE_RULE_EIGHT = "0203";

	/** 技术校验规则 0204-（字符）规则在结果中存在 **/
	public static final String ALIDATE_RULE_NINE = "0204";

	/** 技术校验规则 0205-（字符）规则在结果中不存在 **/
	public static final String ALIDATE_RULE_TEN = "0205";

	/** 技术校验规则 0206-（字符）数据不一致 **/
	public static final String ALIDATE_RULE_ELEVEN = "0206";

	/** 技术校验规则 0301-（集合）集合不为null且size大于0 **/
	public static final String ALIDATE_RULE_TWELEVEN = "0301";

	/** 技术校验规则 0302-（集合）集合为null或size小于1） **/
	public static final String ALIDATE_RULE_THIRTEEN = "0302";

	/******************************************************* 状态 *******************************************************/

	/** 状态 01-正常 **/
	public static final String STATE_ONE = "01";

	/** 状态 02-失效 **/
	public static final String STATE_TWO = "02";

	/******************************************************* 菜单等级 *******************************************************/

	/** 菜单等级 1：一级菜单 **/
	public static final String LEVEL_ONE = "1";

	/** 菜单等级 2：二级菜单 **/
	public static final String LEVEL_TWO = "2";

	/** 菜单等级 3：三级菜单 **/
	public static final String LEVEL_THREE = "3";

	/****************************************************** 任务是否有状态 *******************************************************/

	/** 任务是否有状态 1-是 **/
	public static final String IS_CONCURRENT_ONE = "1";

	/** 任务是否有状态 0-否 **/
	public static final String IS_CONCURRENT_TWO = "0";

	/****************************************************** 任务状态 *******************************************************/
	/** 任务状态 1-运行 **/
	public static final String JOB_STATUS_ONE = "1";

	/** 任务状态 0-非运行 **/
	public static final String JOB_STATUS_TWO = "0";

	/****************************************************** 性别 *******************************************************/
	/** 性别 1-男 **/
	public static final String SEX_TYPE_ONE = "1";

	/** 性别 2-女 **/
	public static final String SEX_TYPE_TWO = "2";

	/****************************************************** 征信机构类型 *******************************************************/

	/** 征信机构类型 01-同盾 **/
	public static final String CREDIT_TYPE_ONE = "01";

	/** 征信机构类型 02-聚信立蜜蜂 **/
	public static final String CREDIT_TYPE_TWO = "02";

	/** 征信机构类型 03-聚信立蜜罐 **/
	public static final String CREDIT_TYPE_THREE = "03";

	/** 征信机构类型 04-芝麻信用 **/
	public static final String CREDIT_TYPE_FOUR = "04";

	/** 征信机构类型 05-91信用卡 **/
	public static final String CREDIT_TYPE_FIVE = "05";

	/** 征信机构类型 06-宜信 **/
	public static final String CREDIT_TYPE_SIX = "06";

	/** 征信机构类型 07-中智诚 **/
	public static final String CREDIT_TYPE_SEVEN = "07";

	/****************************************************** 风险结果说明 *******************************************************/
	/** 风险结果说明 ACCEPT 建议通过 **/
	public static final String ACCEPT = "Accept";

	/** 风险结果说明 REJECT 建议拒绝 **/
	public static final String REJECT = "Reject";

	/** 风险结果说明 REVIEW 建议审核 **/
	public static final String REVIEW = "Review";

	/****************************************************** 风险等级 *******************************************************/
	/** 风险等级 HIGH 高 **/
	public static final String HIGH = "high";

	/** 风险等级 MEDIUM 中 **/
	public static final String MEDIUM = "medium";

	/** 风险等级 LOW 低 **/
	public static final String LOW = "low";
}
