package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 客户手机应用列表记录bean
 * @author xiajiyun
 * @date Sat Aug 12 22:40:56 CST 2017
 **/
public class CuAppInfo  extends BaseBo{

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
	public CuAppInfo(String appInfoId,Integer appName,String customerId,String launcherName,String packageName,String versionName,String versionCode,Integer isSystemApp,String lastUpdateTime,String createTime,String modifyTime) {
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
	public void setAppInfoId(String appInfoId){
		this.appInfoId = appInfoId;
	}

	public String getAppInfoId(){
		return this.appInfoId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setLauncherName(String launcherName){
		this.launcherName = launcherName;
	}

	public String getLauncherName(){
		return this.launcherName;
	}

	public void setPackageName(String packageName){
		this.packageName = packageName;
	}

	public String getPackageName(){
		return this.packageName;
	}

	public void setVersionName(String versionName){
		this.versionName = versionName;
	}

	public String getVersionName(){
		return this.versionName;
	}

	public void setVersionCode(String versionCode){
		this.versionCode = versionCode;
	}

	public String getVersionCode(){
		return this.versionCode;
	}

	public void setIsSystemApp(Integer isSystemApp){
		this.isSystemApp = isSystemApp;
	}

	public Integer getIsSystemApp(){
		return this.isSystemApp;
	}

	public void setLastUpdateTime(String lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}
	@Override
	public String toString() {
		return "CuAppInfo [appInfoId=" + appInfoId + ", appName=" + appName + ", customerId=" + customerId
				+ ", launcherName=" + launcherName + ", packageName=" + packageName + ", versionName=" + versionName
				+ ", versionCode=" + versionCode + ", isSystemApp=" + isSystemApp + ", lastUpdateTime=" + lastUpdateTime
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
 
}
