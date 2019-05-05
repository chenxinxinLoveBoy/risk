package com.shangyong.backend.entity.td.vo;

import java.util.List;

import com.shangyong.backend.entity.td.TdRiskItems;

/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdAntiFraudVo {

	/**同盾反欺诈结果表**/
	private String tdAntiFraudId;

	/**申请单编号**/
	private String applicationId;

	/**状态：1:命中，0：未命中，此状态由校验禁止项目规则后的值**/
	private String state;

	/**同盾进件Id**/
	private String tdId;

	/**风险分数**/
	private String finalScore;

	/**决策结果值为PASS或 REVIEW 或 REJECT**/
	private String finalDecision;
	
	private List<TdRiskItems> tdRiskItems;

	/**备注**/
	private String remark;

	/****/
	private String createTime;

	/****/
	private String createMan;

	/****/
	private String modifyTime;

	/****/
	private String modifyMan;

	public String getTdAntiFraudId() {
		return tdAntiFraudId;
	}

	public void setTdAntiFraudId(String tdAntiFraudId) {
		this.tdAntiFraudId = tdAntiFraudId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTdId() {
		return tdId;
	}

	public void setTdId(String tdId) {
		this.tdId = tdId;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public List<TdRiskItems> getTdRiskItems() {
		return tdRiskItems;
	}

	public void setTdRiskItems(List<TdRiskItems> tdRiskItems) {
		this.tdRiskItems = tdRiskItems;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "TdAntiFraudVo [tdAntiFraudId=" + tdAntiFraudId + ", applicationId=" + applicationId + ", state=" + state
				+ ", tdId=" + tdId + ", finalScore=" + finalScore + ", finalDecision=" + finalDecision
				+ ", tdRiskItems=" + tdRiskItems + ", remark=" + remark + ", createTime=" + createTime + ", createMan="
				+ createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + "]";
	}

	public TdAntiFraudVo(String tdAntiFraudId, String applicationId, String state, String tdId, String finalScore,
			String finalDecision, List<TdRiskItems> tdRiskItems, String remark, String createTime, String createMan,
			String modifyTime, String modifyMan) {
		super();
		this.tdAntiFraudId = tdAntiFraudId;
		this.applicationId = applicationId;
		this.state = state;
		this.tdId = tdId;
		this.finalScore = finalScore;
		this.finalDecision = finalDecision;
		this.tdRiskItems = tdRiskItems;
		this.remark = remark;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}

	public TdAntiFraudVo() {
		super();
	}


}
