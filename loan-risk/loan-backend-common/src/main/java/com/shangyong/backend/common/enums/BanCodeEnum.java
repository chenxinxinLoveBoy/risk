package com.shangyong.backend.common.enums;

/**
 * 禁止项定义
 */
public enum BanCodeEnum {
	
	 /**
	 * 91征信 禁止项配置
	 */
	NINE_ONE_CREDIT_M1_ABOVE("0500001","征信状态存在M1及以上"),
	
	/**
	 * 白骑士 禁止项配置
	 */
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME("1000001",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"白骑士反欺诈云- 规则集(高风险，memo)"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_FINALDECISION("1000002",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"白骑士反欺诈云- 黑名单(Reject)"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_01("1000011",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"灰名单-信贷行业-曾经逾期(0 ~ 30天)"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_02("1000012",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"灰名单-信贷行业-曾经逾期(未知期限)"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_03("1000013",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"黑名单-信贷行业-信用/消费贷黑名单"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_04("1000014",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"黑名单-信贷行业-信贷黑名单"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_05("1000015",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"黑名单-信贷行业-骗取补贴"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_06("1000016",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"黑名单-信贷行业-P2P黑名单"),
//	BAI_QI_SHI_CREDIT_ANTIFRAUD_MOME_07("1000017",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"黑名单-信贷行业-欺诈"),
//	BAI_QI_SHI_CREDIT_XINDAI_BLACK("1100011",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"金融机构黑名单"),
	BAI_QI_SHI_CREDIT_FAYUAN_BLACK("1100012", "法院黑名单"),
//	BAI_QI_SHI_CREDIT_REAL_NAME("1100013",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"未实名认证"),
//	BAI_QI_SHI_CREDIT_NAME_MISMARCHING("1100014",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"运营商姓名不匹配"),
//	BAI_QI_SHI_CREDIT_PHONE_NUMBER("1100015",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"互通过电话号码的去重以后的数量"),
//	BAI_QI_SHI_CREDIT_SHORT_NUMBER("1100016",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"入网时长"),
//	BAI_QI_SHI_CREDIT_GAMBLE_NUMBER("1100017",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"疑似赌博号码通话次数"),
//	BAI_QI_SHI_CREDIT_SIX_MONEY("1100018",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"6个月话费金额"),
//	BAI_QI_SHI_CREDIT_THREE_TOTALTIME("1100019",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"近3个月主叫总时长"),
//	BAI_QI_SHI_CREDIT_SIX_TIME("1100020",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"近6个月平均被叫时长"),
//	BAI_QI_SHI_CREDIT_THREE_MEANNUMBER("1100021",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"近3个月平均被叫次数"),
//	BAI_QI_SHI_CREDIT_PHONE_RECORD("1100022",BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode(),"与联系人1且和联系人2都没有通话记录"),
	BAI_QI_SHI_CREDIT_HIGH_RISK("1100023", "风险名单命中高风险"),
	
	/**
	 *
	 * 新用户准入规则
	 * 户籍区域校验 禁止项配置
	 */
	CENSUS_REGISTER_AREA("1300010","户籍区域校验"),
	HONG_CHANG_CARD_UNIT ("1300008", "身份证(前4位)区域"),
	XIAO_NIU_CARD_REGION ("1300003", "身份证归属地(汉字)"),
	XIAO_NIU_CARD_AGE_CEILING ("1300001", "年龄上限限制"),
	XIAO_NIU_CARD_AGE_FLOOR("1300002", "年龄下限限制") ,
	HONG_CHANG_COMPANY_UNIT("1300009", "行业名称判断"),
	XIAO_NIU_CARD_VALID ("1300004", "身份证在有效期内"),
	XIAO_NIU_CARD_UNIT("1300005", "单位限制"),
	XIAO_NIU_CARD_UNIT_NUM ("1300006", "单位限制-纯数字"),
	HONG_CHENG_CHARACTER_WU("1300011", "新用户准入规则-单位限制-无"),

	/**
	 * 极光黑名单 禁止项配置
	 */
	JI_GUANG_RISK_ALLOCATION_AURORA_RELATION_CIRCLE("0700001", "极光关系圈风险分配置"),
	JI_GUANG_AURORA_OVERDUE_ALLOCATION("0700002", "极光逾期分配置"),
	JI_GUANG_AURORA_DEFAULT_ALLOCATION("0700003", "极光违约分配置"),
	
	
	/**
	 * 腾讯云黑名单 禁止项配置
	 */
	TENCENT_CLOUD_FRAUD_ALLOCATION("0400001", "腾讯云欺诈分配置"),
	
	/**
	 * 同盾黑名单 禁止项配置
	 */
	TONG_DUN_CONFIGURATION_SAME_SHIELD_EQUIPMENT("0900001", "同盾设备指纹规则配置"),
	TONG_DUN_TOTAL_RISK_SCORE_SAME_SHIELD_AGAINST_BULLYING("0900005", "同盾反欺风险总得分"),

	/**
	 * 同盾指纹设备黑名单
	 */
	SHIELD_FINGERPRINT_EQUIPMENT_TRUE_IP("0900002","同盾指纹设备真实IP地址黑名单"),
	SHIELD_FINGERPRINT_EQUIPMENT_DEVICE_ID("0900003","同盾指纹设备ID黑名单"),
	SHIELD_FINGERPRINT_EQUIPMENT_WIFI_MAC("0900004","同盾指纹设备无线mac地址"),

	/**
	 * 易极付-黑名单 禁止项配置
	 */
	YI_JI_PAY_BLACKLIST("1200001", "易极付黑名单数据抓取"),
	
	/**
	 * 芝麻分数 禁止项配置
	 */
	ZHI_MA_SCORES("0400007", "芝麻分数"),
	ZHI_MA_INDUSTRY("0400008", "芝麻行业关注清单"),
	
	/**
     * 上海资信 禁止项配置
     */
    SHANG_HAI_CREDIT_ACCOUNT_ABNORMAL("1400001", "账户状态非正常 账户状态异常为“关注”、“次级”、“可疑”、“损失” 表示命中 拒绝"),
    SHANG_HAI_CREDIT_IS_OVERDUE("1400002", "当前逾期 账户状态未结清，实际还款金额减去本月应还金额小于0拒绝"),
    SHANG_HAI_CREDIT_OVERDUE_SINGLE_COUNT("1400003", "单笔贷款最高逾期期数, 最高逾期期数大于等于3拒绝"),
    SHANG_HAI_CREDIT_OVERDUE_SINGLE_TOTAL("1400004", "单笔贷款累计逾期期数,累计逾期期数大于等于5拒绝"),
    SHANG_HAI_CREDIT_3_MONTH_HAVE_2_M1("1400005", "单笔贷款近3个月有2个M1及以上,24月内各月还款状况近3个月显示有2个“1”，及以上表示命中拒绝"),
    SHANG_HAI_CREDIT_6_MONTH_HAVE_1_M2("1400006", "单笔贷款近6个月有M2及以上,24月内各月还款状况近6个月显示有“2”及以上表示命中拒绝"),
    SHANG_HAI_CREDIT_6_MONTH_3_M1("1400007", "单笔贷款近6个月有3个M1,24月内各月还款状况近6个月显示有三个“1”及以上表示命中拒绝"),
    SHANG_HAI_CREDIT_12_MONTH_4_M1("1400008", "单笔贷款近12个月有4个M1, 24月内各月还款状况近12个月显示有四个“1”及以上表示命中拒绝"),
    SHANG_HAI_CREDIT_OVERDUE_TOTAL("1400009", "累计逾期期数,所有贷款累计逾期期数的和大于等于6拒绝"),
    SHANG_HAI_CREDIT_TARDY("1400010", "长期拖欠, 特殊交易信息记录类型显示“长期拖欠”表示命中拒绝"),
    SHANG_HAI_CREDIT_INDIVIDUAL_GUARANTEE_INDEMNITY("1400011", "个人担保代偿, 特殊交易信息记录类型显示“个人担保代偿”表示命中拒绝"),
    SHANG_HAI_CREDIT_SINGLE_MAX_OVERDE("1400012", "最高逾期期数最大值, 所有贷款中最高逾期期数的最大值大于等于3拒绝"),

    /**
     * 紧急联系人1撞黑名单 禁止项配置
     */
    BLACKLIST_EMERGENCY_CONTACT ("1500001", "紧急联系人1,联系人2撞黑名单"),


	/** 08-存在拒绝名单中 **/
	BLACKLIST_USER("0800001", "用户黑名单") ,

    ;
    private String code;
    private String remark;

    BanCodeEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public static BanCodeEnum parse(String code){
        for(BanCodeEnum elem : values()){
            if(elem.getCode().equals(code)){
                return elem;
            }
        }
        return null;
    }
}
