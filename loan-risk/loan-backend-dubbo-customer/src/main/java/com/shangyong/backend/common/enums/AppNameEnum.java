package com.shangyong.backend.common.enums;

/**
 * App名称枚举类
 * @author xixinghui
 *
 */
public enum AppNameEnum {
	SHANDAI("1", "闪贷"),
	SUDAI("2", "速贷"),
	DUANJIA("3", "贷款管家");
	
	/**
	 * App code
	 */
	private String appNameCode;
	
	/**
	 * App名称
	 */
	private String AppName;

	private AppNameEnum(String appNameCode, String appName) {
		this.appNameCode = appNameCode;
		AppName = appName;
	}
	
	public static boolean contain(String code) {
		for (AppNameEnum appName : AppNameEnum.values()) {
			if (appName.getAppNameCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getAppNameCode() {
		return appNameCode;
	}

	public void setAppNameCode(String appNameCode) {
		this.appNameCode = appNameCode;
	}

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}
	
}
