package com.shangyong.backend.entity.tdf;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * 同盾设备指纹策略规则表bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityPolicySet {

	/**主键id**/
	private String tdFacilityPolicySetId;

	/**申请单编号**/
	private String buApplicationId;

	/**策略uuid**/
	private String policyUuid;

	/**该条策略的决策结果，Accept/Review/Reject**/
	private String policyDecision;

	/**该条策略的策略模式，FirstMatch/WorstMatch/Weighted**/
	private String policyMode;

	/**该条策略的风险分数，只有在权重模式下有效**/
	private String policyScore;

	/**策略名称**/
	private String policyName;

	/**策略风险类型**/
	private String riskType;

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


	public TdFacilityPolicySet() {
		super();
	}
	public TdFacilityPolicySet(String tdFacilityPolicySetId,String buApplicationId,String policyUuid,String policyDecision,String policyMode,String policyScore,String policyName,String riskType,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.tdFacilityPolicySetId = tdFacilityPolicySetId;
		this.buApplicationId = buApplicationId;
		this.policyUuid = policyUuid;
		this.policyDecision = policyDecision;
		this.policyMode = policyMode;
		this.policyScore = policyScore;
		this.policyName = policyName;
		this.riskType = riskType;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTdFacilityPolicySetId(String tdFacilityPolicySetId){
		this.tdFacilityPolicySetId = tdFacilityPolicySetId;
	}

	public String getTdFacilityPolicySetId(){
		return this.tdFacilityPolicySetId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setPolicyUuid(String policyUuid){
		this.policyUuid = policyUuid;
	}

	public String getPolicyUuid(){
		return this.policyUuid;
	}

	public void setPolicyDecision(String policyDecision){
		this.policyDecision = policyDecision;
	}

	public String getPolicyDecision(){
		return this.policyDecision;
	}

	public void setPolicyMode(String policyMode){
		this.policyMode = policyMode;
	}

	public String getPolicyMode(){
		return this.policyMode;
	}

	public void setPolicyScore(String policyScore){
		this.policyScore = policyScore;
	}

	public String getPolicyScore(){
		return this.policyScore;
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
		return "TdFacilityPolicySet [tdFacilityPolicySetId=" + tdFacilityPolicySetId + ", buApplicationId="
				+ buApplicationId + ", policyUuid=" + policyUuid + ", policyDecision=" + policyDecision
				+ ", policyMode=" + policyMode + ", policyScore=" + policyScore + ", policyName=" + policyName
				+ ", riskType=" + riskType + ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime="
				+ modifyTime + ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}

}
