package com.shangyong.backend.common.enums;

/**
 * 账号认证标志枚举类
 * @author xixinghui
 *
 */
public enum CertificateMarkEnum {
	CERTIFICATED("1", "已认证"),
	UNCERTIFICATED("2", "未认证");
	
	/**
	 * 认证code
	 */
	private String certificateMarkCode;
	
	/**
	 * 认证状态名
	 */
	private String certificateMarkName;

	private CertificateMarkEnum(String certificateMarkCode, String certificateMarkName) {
		this.certificateMarkCode = certificateMarkCode;
		this.certificateMarkName = certificateMarkName;
	}
	
	public static boolean contain(String code) {
		for (CertificateMarkEnum certificateMark : CertificateMarkEnum.values()) {
			if (certificateMark.getCertificateMarkCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getCertificateMarkCode() {
		return certificateMarkCode;
	}

	public void setCertificateMarkCode(String certificateMarkCode) {
		this.certificateMarkCode = certificateMarkCode;
	}

	public String getCertificateMarkName() {
		return certificateMarkName;
	}

	public void setCertificateMarkName(String certificateMarkName) {
		this.certificateMarkName = certificateMarkName;
	}
	
}
