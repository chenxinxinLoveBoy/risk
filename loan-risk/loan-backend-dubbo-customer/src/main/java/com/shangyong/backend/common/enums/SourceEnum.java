package com.shangyong.backend.common.enums;

/**
 * 申请来源枚举类
 * @author xixinghui
 *
 */
public enum SourceEnum {
	ANDROID("0", "ANDROID"),
	IOS("1", "IOS");

	/**
	 * 申请来源code
	 */
	private String sourceCode;
	
	/**
	 * 申请来源名
	 */
	private String sourceName;

	private SourceEnum(String sourceCode, String sourceName) {
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
	}
	
	public static boolean contain(String code) {
		for (SourceEnum source : SourceEnum.values()) {
			if (source.getSourceCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
}
