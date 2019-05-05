package com.shangyong.backend.entity.tdReport;


/**
 * 同盾手机信息表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportMobileInfo {

	/**报告的唯一标识**/
	private String mobileInfoId;

	/**申请单编号**/
	private String applicationId;

	/**手机号**/
	private String userMobile;

	/**号码归属地**/
	private String mobileNetAddr;

	/**运营商**/
	private String mobileCarrier;

	/**运营商登记姓名**/
	private String realName;

	/**运营商登记身份证号码**/
	private String identityCode;

	/**手机号账户状态**/
	private String accountStatus;

	/**套餐名称**/
	private String packageType;

	/**账户余额**/
	private String accountBalance;

	/**入网时间**/
	private String mobileNetTime;

	/**入网时长**/
	private String mobileNetAge;

	/**邮箱**/
	private String email;

	/**联系地址**/
	private String contactAddr;

	/**姓名是否与运营商数据匹配**/
	private String realNameCheckYys;

	/**身份证号码是否与运营商数据匹配**/
	private String identityCodeCheckYys;

	/**家庭地址是否与运营商数据匹配**/
	private String homeAddrCheckYys;

	/**邮箱是否与运营商数据匹配**/
	private String emailCheckYys;

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


	public TdReportMobileInfo() {
		super();
	}
	public TdReportMobileInfo(String mobileInfoId,String applicationId,String userMobile,String mobileNetAddr,String mobileCarrier,String realName,String identityCode,String accountStatus,String packageType,String accountBalance,String mobileNetTime,String mobileNetAge,String email,String contactAddr,String realNameCheckYys,String identityCodeCheckYys,String homeAddrCheckYys,String emailCheckYys,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.mobileInfoId = mobileInfoId;
		this.applicationId = applicationId;
		this.userMobile = userMobile;
		this.mobileNetAddr = mobileNetAddr;
		this.mobileCarrier = mobileCarrier;
		this.realName = realName;
		this.identityCode = identityCode;
		this.accountStatus = accountStatus;
		this.packageType = packageType;
		this.accountBalance = accountBalance;
		this.mobileNetTime = mobileNetTime;
		this.mobileNetAge = mobileNetAge;
		this.email = email;
		this.contactAddr = contactAddr;
		this.realNameCheckYys = realNameCheckYys;
		this.identityCodeCheckYys = identityCodeCheckYys;
		this.homeAddrCheckYys = homeAddrCheckYys;
		this.emailCheckYys = emailCheckYys;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setMobileInfoId(String mobileInfoId){
		this.mobileInfoId = mobileInfoId;
	}

	public String getMobileInfoId(){
		return this.mobileInfoId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}

	public String getUserMobile(){
		return this.userMobile;
	}

	public void setMobileNetAddr(String mobileNetAddr){
		this.mobileNetAddr = mobileNetAddr;
	}

	public String getMobileNetAddr(){
		return this.mobileNetAddr;
	}

	public void setMobileCarrier(String mobileCarrier){
		this.mobileCarrier = mobileCarrier;
	}

	public String getMobileCarrier(){
		return this.mobileCarrier;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getRealName(){
		return this.realName;
	}

	public void setIdentityCode(String identityCode){
		this.identityCode = identityCode;
	}

	public String getIdentityCode(){
		return this.identityCode;
	}

	public void setAccountStatus(String accountStatus){
		this.accountStatus = accountStatus;
	}

	public String getAccountStatus(){
		return this.accountStatus;
	}

	public void setPackageType(String packageType){
		this.packageType = packageType;
	}

	public String getPackageType(){
		return this.packageType;
	}

	public void setAccountBalance(String accountBalance){
		this.accountBalance = accountBalance;
	}

	public String getAccountBalance(){
		return this.accountBalance;
	}

	public void setMobileNetTime(String mobileNetTime){
		this.mobileNetTime = mobileNetTime;
	}

	public String getMobileNetTime(){
		return this.mobileNetTime;
	}

	public void setMobileNetAge(String mobileNetAge){
		this.mobileNetAge = mobileNetAge;
	}

	public String getMobileNetAge(){
		return this.mobileNetAge;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setContactAddr(String contactAddr){
		this.contactAddr = contactAddr;
	}

	public String getContactAddr(){
		return this.contactAddr;
	}

	public void setRealNameCheckYys(String realNameCheckYys){
		this.realNameCheckYys = realNameCheckYys;
	}

	public String getRealNameCheckYys(){
		return this.realNameCheckYys;
	}

	public void setIdentityCodeCheckYys(String identityCodeCheckYys){
		this.identityCodeCheckYys = identityCodeCheckYys;
	}

	public String getIdentityCodeCheckYys(){
		return this.identityCodeCheckYys;
	}

	public void setHomeAddrCheckYys(String homeAddrCheckYys){
		this.homeAddrCheckYys = homeAddrCheckYys;
	}

	public String getHomeAddrCheckYys(){
		return this.homeAddrCheckYys;
	}

	public void setEmailCheckYys(String emailCheckYys){
		this.emailCheckYys = emailCheckYys;
	}

	public String getEmailCheckYys(){
		return this.emailCheckYys;
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
