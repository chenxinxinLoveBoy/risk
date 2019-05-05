package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 欺诈评分明细表bean
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
public class ScFraudScoreDetail extends BaseBo{

	/**评分明细编号**/
	private String fraudScoreDetailId;

	/**申请单编号**/
	private String applicationId;

	/**规则序号**/
	private String fraudRuleId;

	/**欺诈评分项规则名称**/
	private String ruleName;

	/**分数**/
	private String score;

	/**创建时间**/
	private String createTime;

	/**备注**/
	private String remark;
	
	/**欺诈模板id**/
	private String fraudTplId;



	public String getFraudTplId() {
		return fraudTplId;
	}

	public void setFraudTplId(String fraudTplId) {
		this.fraudTplId = fraudTplId;
	}

	public void setFraudScoreDetailId(String fraudScoreDetailId){
		this.fraudScoreDetailId = fraudScoreDetailId;
	}

	public String getFraudScoreDetailId(){
		return this.fraudScoreDetailId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setFraudRuleId(String fraudRuleId){
		this.fraudRuleId = fraudRuleId;
	}

	public String getFraudRuleId(){
		return this.fraudRuleId;
	}

	public void setRuleName(String ruleName){
		this.ruleName = ruleName;
	}

	public String getRuleName(){
		return this.ruleName;
	}

	public void setScore(String score){
		this.score = score;
	}

	public String getScore(){
		return this.score;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	@Override
	public String toString() {
		return "ScFraudScoreDetail [fraudScoreDetailId=" + fraudScoreDetailId + ", applicationId=" + applicationId
				+ ", fraudRuleId=" + fraudRuleId + ", ruleName=" + ruleName + ", score=" + score + ", createTime="
				+ createTime + ", remark=" + remark + ", fraudTplId=" + fraudTplId + "]";
	}

	public ScFraudScoreDetail(String fraudScoreDetailId, String applicationId, String fraudRuleId, String ruleName,
			String score, String createTime, String remark, String fraudTplId) {
		super();
		this.fraudScoreDetailId = fraudScoreDetailId;
		this.applicationId = applicationId;
		this.fraudRuleId = fraudRuleId;
		this.ruleName = ruleName;
		this.score = score;
		this.createTime = createTime;
		this.remark = remark;
		this.fraudTplId = fraudTplId;
	}

	public ScFraudScoreDetail() {
		super();
	}
	
	
 
}
