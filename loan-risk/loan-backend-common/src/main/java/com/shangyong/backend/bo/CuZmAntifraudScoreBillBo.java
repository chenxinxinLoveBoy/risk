package com.shangyong.backend.bo;

import java.util.List;


/**
 * 欺诈关注清单,Bo
 * 
 * @author xiajiyun
 *
 */
public class CuZmAntifraudScoreBillBo {
	
	private Integer billId;

	private String zmAntifraudScoreBillId;

	private String zmHit;

	private String zmRiskCode;

	private String zmBizNo;

	private String name;

	private String certType;

	private String certCode;

	private String platformCustomerId;

	private String applicationId;

	private String riskCode;

	private Integer state;

	private String createTime;

	private String createMan;

	private String modifyTime;

	private String modifyMan;

	private String remark;

	private String openId;
	
//	private String[] riskCodeArr;// 用于页面显示对应的riskCode码
	private List<CuZmAntifraudScoreBillRiskCodeBo> listRiskCode;
	
	private String ipRemark;
	

	public String getIpRemark() {
		return ipRemark;
	}

	public void setIpRemark(String ipRemark) {
		this.ipRemark = ipRemark;
	}

	

	public List<CuZmAntifraudScoreBillRiskCodeBo> getListRiskCode() {
		return listRiskCode;
	}

	public void setListRiskCode(List<CuZmAntifraudScoreBillRiskCodeBo> listRiskCode) {
		this.listRiskCode = listRiskCode;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getZmAntifraudScoreBillId() {
		return zmAntifraudScoreBillId;
	}

	public void setZmAntifraudScoreBillId(String zmAntifraudScoreBillId) {
		this.zmAntifraudScoreBillId = zmAntifraudScoreBillId == null ? null
				: zmAntifraudScoreBillId.trim();
	}

	public String getZmHit() {
		return zmHit;
	}

	public void setZmHit(String zmHit) {
		this.zmHit = zmHit == null ? null : zmHit.trim();
	}

	public String getZmRiskCode() {
		return zmRiskCode;
	}

	public void setZmRiskCode(String zmRiskCode) {
		this.zmRiskCode = zmRiskCode == null ? null : zmRiskCode.trim();
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

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode == null ? null : riskCode.trim();
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