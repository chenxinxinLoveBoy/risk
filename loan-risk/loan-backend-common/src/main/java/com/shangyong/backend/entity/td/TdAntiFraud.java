package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdAntiFraud {

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


	public TdAntiFraud() {
		super();
	}
	public TdAntiFraud(String tdAntiFraudId,String applicationId,String state,String tdId,String finalScore,String finalDecision,String remark,String createTime,String createMan,String modifyTime,String modifyMan) {
		super();
		this.tdAntiFraudId = tdAntiFraudId;
		this.applicationId = applicationId;
		this.state = state;
		this.tdId = tdId;
		this.finalScore = finalScore;
		this.finalDecision = finalDecision;
		this.remark = remark;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}
	public void setTdAntiFraudId(String tdAntiFraudId){
		this.tdAntiFraudId = tdAntiFraudId;
	}

	public String getTdAntiFraudId(){
		return this.tdAntiFraudId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
	}

	public void setTdId(String tdId){
		this.tdId = tdId;
	}

	public String getTdId(){
		return this.tdId;
	}

	public void setFinalScore(String finalScore){
		this.finalScore = finalScore;
	}

	public String getFinalScore(){
		return this.finalScore;
	}

	public void setFinalDecision(String finalDecision){
		this.finalDecision = finalDecision;
	}

	public String getFinalDecision(){
		return this.finalDecision;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

}
