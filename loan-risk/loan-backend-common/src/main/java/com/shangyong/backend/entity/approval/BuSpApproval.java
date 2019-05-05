package com.shangyong.backend.entity.approval;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 人工审批扩展表bean
 * @author xiajiyun
 * @date Sat Aug 12 22:40:55 CST 2017
 **/
public class BuSpApproval extends BaseBo {

	/**人工审批编号**/
	private String approvalId;

	/**申请单编号**/
	private String applicationId;

	/**系统审批状态（1-待审批、2-审批通过、3-审批未通过）**/
	private String auditingState;

	/**人工审批状态（1-待审批、2-审批通过、3-审批未通过）**/
	private String rgAuditingState;

	/**人工审批日期**/
	private String rgAuditingTime;

	/**拒绝原因**/
	private String refuseName;

	/**拒绝编号**/
	private String refuseCode;

	/**系统审核额度**/
	private String creditMoney;

	/**人工审核额度**/
	private String rgCreditMoney;

	/**是否与本人电核（0：未电核，1：已电核）**/
	private String isCallPhone;

	/**网查无异常（0：无异常，1：有异常）**/
	private String netcheckNoAbnormal;

	/**人工审核备注**/
	private String rgAuditingRemark;

	/**领取人编号**/
	private String receiveCode;

	/**领取人姓名**/
	private String receiveName;

	/**修改人编号**/
	private String modifyNo;

	/**修改人姓名**/
	private String modifyMan;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public BuSpApproval() {
		super();
	}
	public BuSpApproval(String approvalId,String applicationId,String auditingState,String rgAuditingState,String rgAuditingTime,String refuseName,String refuseCode,String creditMoney,String rgCreditMoney,String isCallPhone,String netcheckNoAbnormal,String rgAuditingRemark,String receiveCode,String receiveName,String modifyNo,String modifyMan,String createTime,String modifyTime) {
		super();
		this.approvalId = approvalId;
		this.applicationId = applicationId;
		this.auditingState = auditingState;
		this.rgAuditingState = rgAuditingState;
		this.rgAuditingTime = rgAuditingTime;
		this.refuseName = refuseName;
		this.refuseCode = refuseCode;
		this.creditMoney = creditMoney;
		this.rgCreditMoney = rgCreditMoney;
		this.isCallPhone = isCallPhone;
		this.netcheckNoAbnormal = netcheckNoAbnormal;
		this.rgAuditingRemark = rgAuditingRemark;
		this.receiveCode = receiveCode;
		this.receiveName = receiveName;
		this.modifyNo = modifyNo;
		this.modifyMan = modifyMan;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setApprovalId(String approvalId){
		this.approvalId = approvalId;
	}

	public String getApprovalId(){
		return this.approvalId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setAuditingState(String auditingState){
		this.auditingState = auditingState;
	}

	public String getAuditingState(){
		return this.auditingState;
	}

	public void setRgAuditingState(String rgAuditingState){
		this.rgAuditingState = rgAuditingState;
	}

	public String getRgAuditingState(){
		return this.rgAuditingState;
	}

	public void setRgAuditingTime(String rgAuditingTime){
		this.rgAuditingTime = rgAuditingTime;
	}

	public String getRgAuditingTime(){
		return this.rgAuditingTime;
	}

	public void setRefuseName(String refuseName){
		this.refuseName = refuseName;
	}

	public String getRefuseName(){
		return this.refuseName;
	}

	public void setRefuseCode(String refuseCode){
		this.refuseCode = refuseCode;
	}

	public String getRefuseCode(){
		return this.refuseCode;
	}

	public void setCreditMoney(String creditMoney){
		this.creditMoney = creditMoney;
	}

	public String getCreditMoney(){
		return this.creditMoney;
	}

	public void setRgCreditMoney(String rgCreditMoney){
		this.rgCreditMoney = rgCreditMoney;
	}

	public String getRgCreditMoney(){
		return this.rgCreditMoney;
	}

	public void setIsCallPhone(String isCallPhone){
		this.isCallPhone = isCallPhone;
	}

	public String getIsCallPhone(){
		return this.isCallPhone;
	}

	public void setNetcheckNoAbnormal(String netcheckNoAbnormal){
		this.netcheckNoAbnormal = netcheckNoAbnormal;
	}

	public String getNetcheckNoAbnormal(){
		return this.netcheckNoAbnormal;
	}

	public void setRgAuditingRemark(String rgAuditingRemark){
		this.rgAuditingRemark = rgAuditingRemark;
	}

	public String getRgAuditingRemark(){
		return this.rgAuditingRemark;
	}

	public void setReceiveCode(String receiveCode){
		this.receiveCode = receiveCode;
	}

	public String getReceiveCode(){
		return this.receiveCode;
	}

	public void setReceiveName(String receiveName){
		this.receiveName = receiveName;
	}

	public String getReceiveName(){
		return this.receiveName;
	}

	public void setModifyNo(String modifyNo){
		this.modifyNo = modifyNo;
	}

	public String getModifyNo(){
		return this.modifyNo;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}
	@Override
	public String toString() {
		return "BuSpApproval [approvalId=" + approvalId + ", applicationId=" + applicationId + ", auditingState="
				+ auditingState + ", rgAuditingState=" + rgAuditingState + ", rgAuditingTime=" + rgAuditingTime
				+ ", refuseName=" + refuseName + ", refuseCode=" + refuseCode + ", creditMoney=" + creditMoney
				+ ", rgCreditMoney=" + rgCreditMoney + ", isCallPhone=" + isCallPhone + ", netcheckNoAbnormal="
				+ netcheckNoAbnormal + ", rgAuditingRemark=" + rgAuditingRemark + ", receiveCode=" + receiveCode
				+ ", receiveName=" + receiveName + ", modifyNo=" + modifyNo + ", modifyMan=" + modifyMan
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
	
	

}
