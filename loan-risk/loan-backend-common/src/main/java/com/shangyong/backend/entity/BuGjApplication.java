package com.shangyong.backend.entity;


/**
 * 贷款管家客户申请记录表bean
 * @author xiajiyun
 * @date Thu Jul 20 20:44:43 CST 2017
 **/
public class BuGjApplication {

	/**申请单编号**/
	private String applicationId;

	/**APP应用请求流水号**/
	private String appSerialNumber;

	/**平台用户编号**/
	private String platformId;

	/**申请单业务编号**/
	private String applicationBuId;

	/**APP应用客户编号**/
	private String customerId;

	/**客户姓名**/
	private String name;

	/**证件类型（1.身份证 2.护照 3.其他）**/
	private String certType;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**借款用户公网IP**/
	private String loanIp;

	/**处理时间**/
	private String handleTime;

	/**处理状态（1-待处理、2-处理完成、3-处理失败）**/
	private String handleState;

	/**申请来源（0——Android；1——IOS）**/
	private String source;

	/**处理结果描述**/
	private String auditResult;

	/**是否通知App（0-否、1-是）**/
	private String isPushApp;

	/**步骤标识（1：同盾贷前审核、2：聚信立蜜蜂、3：聚信立蜜罐）**/
	private String isStep;

	/**步骤处理异常描述**/
	private String errorDescription;

	/**处理人用户编号**/
	private String auditMan;

	/**同盾blackBox**/
	private String blackBox;

	/**聚信立token**/
	private String jxlToken;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public BuGjApplication() {
		super();
	}
	public BuGjApplication(String applicationId,String appSerialNumber,String platformId,String applicationBuId,String customerId,String name,String certType,String certCode,String phoneNum,String loanIp,String handleTime,String handleState,String source,String auditResult,String isPushApp,String isStep,String errorDescription,String auditMan,String blackBox,String jxlToken,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.applicationId = applicationId;
		this.appSerialNumber = appSerialNumber;
		this.platformId = platformId;
		this.applicationBuId = applicationBuId;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.loanIp = loanIp;
		this.handleTime = handleTime;
		this.handleState = handleState;
		this.source = source;
		this.auditResult = auditResult;
		this.isPushApp = isPushApp;
		this.isStep = isStep;
		this.errorDescription = errorDescription;
		this.auditMan = auditMan;
		this.blackBox = blackBox;
		this.jxlToken = jxlToken;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setAppSerialNumber(String appSerialNumber){
		this.appSerialNumber = appSerialNumber;
	}

	public String getAppSerialNumber(){
		return this.appSerialNumber;
	}

	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}

	public String getPlatformId(){
		return this.platformId;
	}

	public void setApplicationBuId(String applicationBuId){
		this.applicationBuId = applicationBuId;
	}

	public String getApplicationBuId(){
		return this.applicationBuId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertType(){
		return this.certType;
	}

	public void setCertCode(String certCode){
		this.certCode = certCode;
	}

	public String getCertCode(){
		return this.certCode;
	}

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setLoanIp(String loanIp){
		this.loanIp = loanIp;
	}

	public String getLoanIp(){
		return this.loanIp;
	}

	public void setHandleTime(String handleTime){
		this.handleTime = handleTime;
	}

	public String getHandleTime(){
		return this.handleTime;
	}

	public void setHandleState(String handleState){
		this.handleState = handleState;
	}

	public String getHandleState(){
		return this.handleState;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return this.source;
	}

	public void setAuditResult(String auditResult){
		this.auditResult = auditResult;
	}

	public String getAuditResult(){
		return this.auditResult;
	}

	public void setIsPushApp(String isPushApp){
		this.isPushApp = isPushApp;
	}

	public String getIsPushApp(){
		return this.isPushApp;
	}

	public void setIsStep(String isStep){
		this.isStep = isStep;
	}

	public String getIsStep(){
		return this.isStep;
	}

	public void setErrorDescription(String errorDescription){
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription(){
		return this.errorDescription;
	}

	public void setAuditMan(String auditMan){
		this.auditMan = auditMan;
	}

	public String getAuditMan(){
		return this.auditMan;
	}

	public void setBlackBox(String blackBox){
		this.blackBox = blackBox;
	}

	public String getBlackBox(){
		return this.blackBox;
	}

	public void setJxlToken(String jxlToken){
		this.jxlToken = jxlToken;
	}

	public String getJxlToken(){
		return this.jxlToken;
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

}
