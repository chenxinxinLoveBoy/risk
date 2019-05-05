package com.shangyong.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 征信平台拒绝客户列表bean
 * 
 * @author xk
 * @date Wed Jun 07 00:09:11 CST 2017
 **/
@JsonIgnoreProperties(ignoreUnknown = true) // 可以忽略掉从JSON(由于在应用中没有完全匹配的POJO)中获得的所有“多余的”属性(此处忽略从Basebo继承来的pageIndex和
											// pageSize属性)
public class BuBlacklist extends BaseBo {
	
	/** 拒绝编号 **/
	private String blacklistId;

	/** APP名称：1-闪贷；2-速贷 **/
	private String appName;

	/** 平台用户账号 **/
	private String platformId;

	/** APP应用客户编号 **/
	private String customerId;

	/** 客户姓名 **/
	private String name;

	/** 证件类型 ： 1.身份证 2.护照 3.其他 **/
	private String certType;

	/** 证件号码 **/
	private String certCode;

	/** 手机号 **/
	private String phoneNum;

	/** 被拒绝平台类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚、08-催收监控、09-欺诈拒绝、10-白骑士、11-小视科技、12-葫芦、21-APP同步、22-人工审核 、23逾期） **/
	private String rejectType;

	/** 被拒绝标志（0-是，1-否） **/
	private String rejectFlag;

	/** 是否失效（0-是，1-否） **/
	private String isFailure;

	/** 禁止项规则对应编号 **/
	private String banCode;

	/** 数据来源
	 * @see com.shangyong.backend.common.enums.BlackListDsSourceEnum
	 */
	private String dsSource;

	/** 流水编号(借款申请编号、贷后推送流水号) **/
	private String sNumber;
	
	/**设备ID**/
	private String deviceId;
	
	/**创建时间**/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 备注 **/
	private String remark;

	/**批量删除**/
	private String[] ids;
	
	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;
	
	/**黑名单分类代码**/
	private String classCode;
	
	public BuBlacklist() {
		super();
	}

	public BuBlacklist(String blacklistId, String appName, String platformId, String customerId, String name,
			String certType, String certCode, String phoneNum, String rejectType, String rejectFlag, String isFailure,
			String banCode, String dsSource, String sNumber, String createTime, String createMan, String modifyTime,
			String modifyMan, String remark, String deviceId, String classCode) {
		super();
		this.blacklistId = blacklistId;
		this.appName = appName;
		this.platformId = platformId;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.rejectType = rejectType;
		this.rejectFlag = rejectFlag;
		this.isFailure = isFailure;
		this.banCode = banCode;
		this.dsSource = dsSource;
		this.sNumber = sNumber;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.deviceId = deviceId;
		this.classCode = classCode;
	}

	public String getBlacklistId() {
		return blacklistId;
	}

	public void setBlacklistId(String blacklistId) {
		this.blacklistId = blacklistId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRejectType() {
		return rejectType;
	}

	public void setRejectType(String rejectType) {
		this.rejectType = rejectType;
	}

	public String getRejectFlag() {
		return rejectFlag;
	}

	public void setRejectFlag(String rejectFlag) {
		this.rejectFlag = rejectFlag;
	}

	public String getIsFailure() {
		return isFailure;
	}

	public void setIsFailure(String isFailure) {
		this.isFailure = isFailure;
	}

	public String getBanCode() {
		return banCode;
	}

	public void setBanCode(String banCode) {
		this.banCode = banCode;
	}

	public String getDsSource() {
		return dsSource;
	}

	public void setDsSource(String dsSource) {
		this.dsSource = dsSource;
	}

	public String getsNumber() {
		return sNumber;
	}

	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeviceId() {
		return deviceId;
	}
	
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	 
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@Override
	public String toString() {
		return "BuBlacklist [blacklistId=" + blacklistId + ", appName=" + appName + ", platformId=" + platformId
				+ ", customerId=" + customerId + ", name=" + name + ", certType=" + certType + ", certCode=" + certCode
				+ ", phoneNum=" + phoneNum + ", rejectType=" + rejectType + ", rejectFlag=" + rejectFlag
				+ ", isFailure=" + isFailure + ", banCode=" + banCode + ", dsSource=" + dsSource + ", sNumber="
				+ sNumber + ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + ", remark=" + remark + ", deviceId=" + deviceId + ", classCode=" + classCode + "]";
	}

}
