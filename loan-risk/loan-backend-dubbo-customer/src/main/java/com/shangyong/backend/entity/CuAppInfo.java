package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 客户手机应用列表记录bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
public class CuAppInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**记录序号**/
	private String appInfoId;

	/**平台 1闪贷 2速贷**/
	private Integer appName;

	/**APP客户编号**/
	private String customerId;

	/**应用程序名称**/
	private String launcherName;

	/**应用程序所对应的包名**/
	private String packageName;

	/**应用程序的versionName**/
	private String versionName;

	/**应用程序的versionCode**/
	private String versionCode;

	/**是否是系统应用（0-否、1-是）**/
	private Integer isSystemApp;

	/**应用最近一次更新时间**/
	private String lastUpdateTime;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	public CuAppInfo() {
		super();
	}

	public CuAppInfo(String appInfoId, Integer appName, String customerId, String launcherName, String packageName,
			String versionName, String versionCode, Integer isSystemApp, String lastUpdateTime, String createTime,
			String modifyTime) {
		super();
		this.appInfoId = appInfoId;
		this.appName = appName;
		this.customerId = customerId;
		this.launcherName = launcherName;
		this.packageName = packageName;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.isSystemApp = isSystemApp;
		this.lastUpdateTime = lastUpdateTime;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	public String getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(String appInfoId) {
		this.appInfoId = appInfoId;
	}

	public Integer getAppName() {
		return appName;
	}

	public void setAppName(Integer appName) {
		this.appName = appName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLauncherName() {
		return launcherName;
	}

	public void setLauncherName(String launcherName) {
		this.launcherName = launcherName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public Integer getIsSystemApp() {
		return isSystemApp;
	}

	public void setIsSystemApp(Integer isSystemApp) {
		this.isSystemApp = isSystemApp;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
