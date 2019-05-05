package com.shangyong.backend.common.enums;

/**
 * 教育类型枚举类
 * @author xixinghui
 *
 */
public enum EducationEnum {
	PRE_HIGH_SCHOOL("PRE_HIGH_SCHOOL", "高中以下"),
	HIGH_SCHOOL("HIGH_SCHOOL", "高中/中专"),
	JUNIOR_COLLEGE("JUNIOR_COLLEGE", "大专"),
	UNDER_GRADUATE("UNDER_GRADUATE", "本科"),
	POST_GRADUATE("POST_GRADUATE", "研究生");
	
	/**
	 * 学历Code
	 */
	private String educationCode;
	
	/**
	 * 学历名称
	 */
	private String educationName;

	private EducationEnum(String educationCode, String educationName) {
		this.educationCode = educationCode;
		this.educationName = educationName;
	}
	
	public static boolean contain(String code) {
		for (EducationEnum education : EducationEnum.values()) {
			if (education.getEducationCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getEducationCode() {
		return educationCode;
	}

	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	
}
