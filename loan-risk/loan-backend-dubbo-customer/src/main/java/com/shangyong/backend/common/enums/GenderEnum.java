package com.shangyong.backend.common.enums;

/**
 * 性别枚举类
 * @author xixinghui
 *
 */
public enum GenderEnum {
	MAN("1", "男"),
	FEMALE("2", "女");
	
	/**
	 * 性别Code
	 */
	private String genderCode;
	
	/**
	 * 性别
	 */
	private String genderName;

	private GenderEnum(String genderCode, String genderName) {
		this.genderCode = genderCode;
		this.genderName = genderName;
	}

	public static boolean contain(String code) {
		for (GenderEnum gender : GenderEnum.values()) {
			if (gender.getGenderCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	
}
