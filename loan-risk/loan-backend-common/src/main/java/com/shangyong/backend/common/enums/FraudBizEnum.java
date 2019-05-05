package com.shangyong.backend.common.enums;

public enum FraudBizEnum {

	TENCENT_CLOUD("tencentCloud","腾讯云"), // 1.1, 1.8
	TONG_DUN("tongDun", "同盾多头"), // 1.1, 1.8
	TONG_DUN_REPORT("tongDunReport","同盾数据魔盒报告"), // 1.1, 1.8
	TONG_DUN_FINGER_PRINT_EQU("tongDunFingerPrintEqu","同盾指纹设备"),//1.8
	BAI_QI_SHI("baiQiShi","白骑士"), // 1.1, 1.8
	BASIC_INFO("basicInfo","基本信息"), // 1.1, 1.8
	BASIC_INFO_BANK_TYPE("basicInfoBankType","基本信息bank_type"), // 1.1, 1.8
	ZX91("zx91","91征信"), // 1.1, 1.8
	ADDRESS_BOOK("addressBook", "通讯录"),//1.8, 2.0
	SHANGHAI_CREDIT("shangHaiCredit", "上海资信") // 2.0
	;
	
	private String code;
	private String remark;
	
	FraudBizEnum(String code, String remark){
		this.code = code;
		this.remark = remark;
	}
	
	public String getCode() {
		return code;
	}
	public String getRemark() {
		return remark;
	}
	
}
