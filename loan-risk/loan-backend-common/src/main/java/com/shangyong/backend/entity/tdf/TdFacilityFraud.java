package com.shangyong.backend.entity.tdf;


/**
 * 同盾设备指纹反欺诈主表bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityFraud {

	/**主键id**/
	private String tdFacilityFraudId;

	/**申请单编号**/
	private String buApplicationId;

	/**接口调用是否成功，失败原因参见reason_code字段**/
	private String success;

	/**接口调用异常的状态码，失败原因参见reason_code表**/
	private String reasonCode;

	/**请求的唯一序列号**/
	private String seqId;

	/**该接口内部处理耗时,单位毫秒(ms)**/
	private String spendTime;

	/**风险决策的最终结果，Accept/Review/Reject**/
	private String finalDecision;

	/**最终的风险分数，只有在权重模式下有效**/
	private String finalScore;

	/**策略集名称**/
	private String policySetName;

	/**与policy_set中的policy_set_name内容相同，为了向前兼容**/
	private String policyName;

	/**命中的风险类型的标识及风险结果的集合**/
	private String riskType;

	/**是否命中,1命中0未命中**/
	private String state;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public TdFacilityFraud() {
		super();
	}
	public TdFacilityFraud(String tdFacilityFraudId,String buApplicationId,String success,String reasonCode,String seqId,String spendTime,String finalDecision,String finalScore,String policySetName,String policyName,String riskType,String state,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.tdFacilityFraudId = tdFacilityFraudId;
		this.buApplicationId = buApplicationId;
		this.success = success;
		this.reasonCode = reasonCode;
		this.seqId = seqId;
		this.spendTime = spendTime;
		this.finalDecision = finalDecision;
		this.finalScore = finalScore;
		this.policySetName = policySetName;
		this.policyName = policyName;
		this.riskType = riskType;
		this.state = state;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTdFacilityFraudId(String tdFacilityFraudId){
		this.tdFacilityFraudId = tdFacilityFraudId;
	}

	public String getTdFacilityFraudId(){
		return this.tdFacilityFraudId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setSuccess(String success){
		this.success = success;
	}

	public String getSuccess(){
		return this.success;
	}

	public void setReasonCode(String reasonCode){
		this.reasonCode = reasonCode;
	}

	public String getReasonCode(){
		return this.reasonCode;
	}

	public void setSeqId(String seqId){
		this.seqId = seqId;
	}

	public String getSeqId(){
		return this.seqId;
	}

	public void setSpendTime(String spendTime){
		this.spendTime = spendTime;
	}

	public String getSpendTime(){
		return this.spendTime;
	}

	public void setFinalDecision(String finalDecision){
		this.finalDecision = finalDecision;
	}

	public String getFinalDecision(){
		return this.finalDecision;
	}

	public void setFinalScore(String finalScore){
		this.finalScore = finalScore;
	}

	public String getFinalScore(){
		return this.finalScore;
	}

	public void setPolicySetName(String policySetName){
		this.policySetName = policySetName;
	}

	public String getPolicySetName(){
		return this.policySetName;
	}

	public void setPolicyName(String policyName){
		this.policyName = policyName;
	}

	public String getPolicyName(){
		return this.policyName;
	}

	public void setRiskType(String riskType){
		this.riskType = riskType;
	}

	public String getRiskType(){
		return this.riskType;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
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

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
	@Override
	public String toString() {
		return "TdFacilityFraud [tdFacilityFraudId=" + tdFacilityFraudId + ", buApplicationId=" + buApplicationId
				+ ", success=" + success + ", reasonCode=" + reasonCode + ", seqId=" + seqId + ", spendTime="
				+ spendTime + ", finalDecision=" + finalDecision + ", finalScore=" + finalScore + ", policySetName="
				+ policySetName + ", policyName=" + policyName + ", riskType=" + riskType + ", state=" + state
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}

}
