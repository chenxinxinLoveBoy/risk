package com.shangyong.backend.common.enums;

/**
 * 亲属关系枚举类
 * @author xixinghui
 *
 */
public enum RelationEnum {
	FATHER("father", "父亲"),
	MOTHER("mother", "母亲"),
	SPOUSE("spouse", "配偶"),
	CHILD("child", "子女"),
	OTHER_RELATIVE("other_relative", "其他亲属"),
	FRIEND("friend", "朋友"),
	COWORKER("coworker", "同事"),
	OTHERS("others", "其他");
	
	/**
	 * 关系code
	 */
	private String relationCode;
	
	/**
	 * 关系名称
	 */
	private String relationName;

	private RelationEnum(String relationCode, String relationName) {
		this.relationCode = relationCode;
		this.relationName = relationName;
	}

	public static boolean contain(String code) {
		for (RelationEnum relation : RelationEnum.values()) {
			if (relation.getRelationCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
}
