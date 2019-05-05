package com.shangyong.backend.bo;


/**
 * 欺诈信息验证
 * @author xiajiyun
 *
 */
public class CuZmAntifraudScoreCheckBo {
	
	private Integer checkId;

	private String zmAntifraudScoreCheckId;

	private String zmVerifyCode;

	private String zmBizNo;

	private String name;

	private String certType;

	private String certCode;

	private String platformCustomerId;

	private String applicationId;

	private Integer state;

	private String createTime;

	private String createMan;

	private String modifyTime;

	private String modifyMan;

	private String remark;

	private String openId;

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getZmAntifraudScoreCheckId() {
		return zmAntifraudScoreCheckId;
	}

	public void setZmAntifraudScoreCheckId(String zmAntifraudScoreCheckId) {
		this.zmAntifraudScoreCheckId = zmAntifraudScoreCheckId == null ? null
				: zmAntifraudScoreCheckId.trim();
	}

	public String getZmVerifyCode() {
		return zmVerifyCode;
	}

	public void setZmVerifyCode(String zmVerifyCode) {
		this.zmVerifyCode = zmVerifyCode == null ? null : zmVerifyCode.trim();
	}

	public String getZmBizNo() {
		return zmBizNo;
	}

	public void setZmBizNo(String zmBizNo) {
		this.zmBizNo = zmBizNo == null ? null : zmBizNo.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType == null ? null : certType.trim();
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode == null ? null : certCode.trim();
	}

	public String getPlatformCustomerId() {
		return platformCustomerId;
	}

	public void setPlatformCustomerId(String platformCustomerId) {
		this.platformCustomerId = platformCustomerId == null ? null
				: platformCustomerId.trim();
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId == null ? null : applicationId
				.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan == null ? null : createMan.trim();
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

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan == null ? null : modifyMan.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}
}