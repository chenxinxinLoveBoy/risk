package com.shangyong.backend.common.enums;

/**
 * 其他账号类型枚举类
 * @author xixinghui
 *
 */
public enum OtherAccountEnum {
	QQ("1", "QQ"),
	EMAIL("2", "邮件"),
	WECHAT("3", "微信"),
	TAOBAO("4", "淘宝"),
	JD("5", "京东"),
	ALIPAY("6", "支付宝");
	
	/**
	 * 账号code
	 */
	private String accountCode;
	
	/**
	 * 账号名称
	 */
	private String accountName;

	private OtherAccountEnum(String accountCode, String accountName) {
		this.accountCode = accountCode;
		this.accountName = accountName;
	}

	public static boolean contain(String code) {
		for (OtherAccountEnum otherAccount : OtherAccountEnum.values()) {
			if (otherAccount.getAccountCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}
