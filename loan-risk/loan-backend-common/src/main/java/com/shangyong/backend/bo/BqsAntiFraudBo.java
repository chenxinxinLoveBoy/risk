package com.shangyong.backend.bo;

import java.io.Serializable;


/**
 * 白骑士反欺诈风险决策主表Bo
 * @author xiajiyun
 * @date Wed Jul 26 16:37:24 CST 2017
 **/
public class BqsAntiFraudBo implements Serializable{

	private static final long serialVersionUID = 5428637185366574888L;

	/**主键自增id**/
	private Integer antiFraudId;

	/**事件类型，参考eventType附录码表用来标识应用下某个策略集事件**/
	private String eventType;

	/**申请单编号**/
	private String applicationId;

	/**身份证号码**/
	private String certCode;

	/**身份证姓名**/
	private String certName;

	/**手机号**/
	private String mobile;

	/**结果码，参见ressultCode附录码表**/
	private String resultCode;

	/**本次请求的流水号，用于事后案件调查**/
	private String flowNo;

	/**决策结果码，参见decision码表**/
	private String finalDecision;

	/**最终风险系数，只有权重策略模式下有效**/
	private String finalScore;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**状态，默认1为可用，0关闭**/
	private Integer state;

	/**备注**/
	private String remark;


	public BqsAntiFraudBo() {
		super();
	}
	public BqsAntiFraudBo(Integer antiFraudId,String eventType,String applicationId,String certCode,String certName,String mobile,String resultCode,String flowNo,String finalDecision,String finalScore,String createTime,String createMan,String modifyTime,String modifyMan,Integer state,String remark) {
		super();
		this.antiFraudId = antiFraudId;
		this.eventType = eventType;
		this.applicationId = applicationId;
		this.certCode = certCode;
		this.certName = certName;
		this.mobile = mobile;
		this.resultCode = resultCode;
		this.flowNo = flowNo;
		this.finalDecision = finalDecision;
		this.finalScore = finalScore;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.state = state;
		this.remark = remark;
	}
	public void setAntiFraudId(Integer antiFraudId){
		this.antiFraudId = antiFraudId;
	}

	public Integer getAntiFraudId(){
		return this.antiFraudId;
	}

	public void setEventType(String eventType){
		this.eventType = eventType;
	}

	public String getEventType(){
		return this.eventType;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setCertCode(String certCode){
		this.certCode = certCode;
	}

	public String getCertCode(){
		return this.certCode;
	}

	public void setCertName(String certName){
		this.certName = certName;
	}

	public String getCertName(){
		return this.certName;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}

	public String getResultCode(){
		return this.resultCode;
	}

	public void setFlowNo(String flowNo){
		this.flowNo = flowNo;
	}

	public String getFlowNo(){
		return this.flowNo;
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

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
	@Override
	public String toString() {
		return "BqsAntiFraudBo [antiFraudId=" + antiFraudId + ", eventType=" + eventType + ", applicationId="
				+ applicationId + ", certCode=" + certCode + ", certName=" + certName + ", mobile=" + mobile
				+ ", resultCode=" + resultCode + ", flowNo=" + flowNo + ", finalDecision=" + finalDecision
				+ ", finalScore=" + finalScore + ", createTime=" + createTime + ", createMan=" + createMan
				+ ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", state=" + state + ", remark=" + remark
				+ "]";
	}

	
}
