package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 芝麻信用评分
 * 
 * @author xiajiyun
 *
 */
public class CuZmAntifraudScoreCreditBo extends BaseBo{

	private Integer creditId;

	private String zmAntifraudScoreCreditId;

	private String zmScore;

	private String zmBizNo;

	private String platformCustomerId;

	private String applicationId;

	private Integer state;

	private String createTime;

	private String createMan;

	private String modifyTime;

	private String modifyMan;

	private String remark;

	private String openId;
	
	private String ipRemark;
	

	public String getIpRemark() {
		return ipRemark;
	}

	public void setIpRemark(String ipRemark) {
		this.ipRemark = ipRemark;
	}


	public Integer getCreditId() {
		return creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	public String getZmAntifraudScoreCreditId() {
		return zmAntifraudScoreCreditId;
	}

	public void setZmAntifraudScoreCreditId(String zmAntifraudScoreCreditId) {
		this.zmAntifraudScoreCreditId = zmAntifraudScoreCreditId == null ? null
				: zmAntifraudScoreCreditId.trim();
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
}