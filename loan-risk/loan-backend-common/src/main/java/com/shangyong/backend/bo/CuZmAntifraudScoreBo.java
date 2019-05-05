package com.shangyong.backend.bo;


/**
 * 申请欺诈评分
 * 
 * @author xiajiyun
 *
 */
public class CuZmAntifraudScoreBo {

	private Integer scoreId;

	private String zmAntifraudScoreId;

	private String zmScore;

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

	public Integer getScoreId() {
		return scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public String getZmAntifraudScoreId() {
		return zmAntifraudScoreId;
	}

	public void setZmAntifraudScoreId(String zmAntifraudScoreId) {
		this.zmAntifraudScoreId = zmAntifraudScoreId == null ? null
				: zmAntifraudScoreId.trim();
	}

	public String getZmScore() {
		return zmScore;
	}

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore == null ? null : zmScore.trim();
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