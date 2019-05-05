package com.shangyong.backend.common.enums;

/**
 * 证件类别枚举
 * @author xixinghui
 *
 */
public enum CertTypeEnum {
	
	ID_CARD("1", "身份证"),
	PASSPORT("2", "护照"),
	OTHER("3", "其他");
	
	
	/**
	 * 证件类型
	 */
	private String certTypeCode;
	
	/**
	 * 证件类型名称
	 */
	private String certTypeName;
	
	private CertTypeEnum(String certTypeCode, String certTypeName) {
		this.certTypeCode = certTypeCode;
		this.certTypeName = certTypeName;
	}
	
	public static boolean contain(String code) {
		for (CertTypeEnum certType : CertTypeEnum.values()) {
			if (certType.getCertTypeCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getCertTypeCode() {
		return certTypeCode;
	}

	public void setCertTypeCode(String certTypeCode) {
		this.certTypeCode = certTypeCode;
	}

	public String getCertTypeName() {
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}
	
}
