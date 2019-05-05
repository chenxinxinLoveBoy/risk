package com.shangyong.backend.common.enums;

/**
 * 婚姻状态枚举类
 * @author xixinghui
 *
 */
public enum MarriageStatusEnum {
	SPINSTERHOOD("SPINSTERHOOD", "未婚"),
	MARRIED("MARRIED", "已婚"),
	DIVORCED("DIVORCED", "离异"),
	WIDOWED("WIDOWED", "丧偶"),
	REMARRY("REMARRY", "再婚"),
	REMARRY_FORMER("REMARRY_FORMER", "复婚");
	
	/**
	 * 婚姻状态code
	 */
	private String marriageStatusCode;
	
	/**
	 * 婚姻状态名称
	 */
	private String marriageStatusName;

	private MarriageStatusEnum(String marriageStatusCode, String marriageStatusName) {
		this.marriageStatusCode = marriageStatusCode;
		this.marriageStatusName = marriageStatusName;
	}
	
	public static boolean contain(String code) {
		for (MarriageStatusEnum marriageStatus : MarriageStatusEnum.values()) {
			if (marriageStatus.getMarriageStatusCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getMarriageStatusCode() {
		return marriageStatusCode;
	}

	public void setMarriageStatusCode(String marriageStatusCode) {
		this.marriageStatusCode = marriageStatusCode;
	}

	public String getMarriageStatusName() {
		return marriageStatusName;
	}

	public void setMarriageStatusName(String marriageStatusName) {
		this.marriageStatusName = marriageStatusName;
	}
	
}
