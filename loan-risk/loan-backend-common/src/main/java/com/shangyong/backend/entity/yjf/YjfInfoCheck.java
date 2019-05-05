package com.shangyong.backend.entity.yjf;

/**
 * 易极付黑名单主表bean
 * @author kenzhao
 * @date Thu Dec 21 16:56:41 CST 2017
 **/
public class YjfInfoCheck {

	/****/
	private String yjfInfoId;

	/**申请单编号**/
	private String buApplicationId;

	/**签名**/
	private String sign;

	/**协议**/
	private String protocol;

	/**订单号**/
	private String orderNo;

	/**签名类型**/
	private String signType;

	/**服务名**/
	private String service;

	/**响应码**/
	private String resultCode;
	
	private String blackListTypeDesc;
	
	private String blackListType;


	/**商户编号**/
	private String partnerId;

	/**响应内容**/
	private String resultMessage;

	/**是否成功**/
	private String success;

	/**黑名单类型**/
	private String userType;

	/**版本号**/
	private String version;

	/**是否命中,1命中0未命中**/
	private String state;

	/**备注**/
	private String remark;

	/**创建时间**/
	private String createTime;

	/****/
	private String createMan;

	/****/
	private String modifyTime;

	/**最后修改时间**/
	private String modifyMan;


	public YjfInfoCheck() {
		super();
	}
	
	@Override
	public String toString() {
		return "YjfInfoCheck [yjfInfoId=" + yjfInfoId + ", buApplicationId=" + buApplicationId + ", sign=" + sign
				+ ", protocol=" + protocol + ", orderNo=" + orderNo + ", signType=" + signType + ", service=" + service
				+ ", resultCode=" + resultCode + ", blackListTypeDesc=" + blackListTypeDesc + ", blackListType="
				+ blackListType + ", partnerId=" + partnerId + ", resultMessage=" + resultMessage + ", success="
				+ success + ", userType=" + userType + ", version=" + version + ", state=" + state + ", remark="
				+ remark + ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + "]";
	}

	public void setYjfInfoId(String yjfInfoId){
		this.yjfInfoId = yjfInfoId;
	}

	public String getYjfInfoId(){
		return this.yjfInfoId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setSign(String sign){
		this.sign = sign;
	}

	public String getSign(){
		return this.sign;
	}

	public void setProtocol(String protocol){
		this.protocol = protocol;
	}

	public String getProtocol(){
		return this.protocol;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo(){
		return this.orderNo;
	}

	public void setSignType(String signType){
		this.signType = signType;
	}

	public String getSignType(){
		return this.signType;
	}

	public void setService(String service){
		this.service = service;
	}

	public String getService(){
		return this.service;
	}

	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}

	public String getResultCode(){
		return this.resultCode;
	}

	public void setPartnerId(String partnerId){
		this.partnerId = partnerId;
	}

	public String getPartnerId(){
		return this.partnerId;
	}

	public void setResultMessage(String resultMessage){
		this.resultMessage = resultMessage;
	}

	public String getResultMessage(){
		return this.resultMessage;
	}

	public void setSuccess(String success){
		this.success = success;
	}

	public String getSuccess(){
		return this.success;
	}

	public void setUserType(String userType){
		this.userType = userType;
	}

	public String getUserType(){
		return this.userType;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return this.version;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
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
	public String getBlackListTypeDesc() {
		return blackListTypeDesc;
	}
	public void setBlackListTypeDesc(String blackListTypeDesc) {
		this.blackListTypeDesc = blackListTypeDesc;
	}
	public String getBlackListType() {
		return blackListType;
	}
	public void setBlackListType(String blackListType) {
		this.blackListType = blackListType;
	}

}
