package com.shangyong.backend.entity;


/**
 * 平台客户信息表bean
 * @author kenzhao
 * @date Mon Sep 25 16:48:38 CST 2017
 **/
public class CuPlatformCustomerWeb {

	/**平台用户编号**/
	private String platformCustomerId;

	/**APP应用客户编号**/
	private String customerId;

	/**APP名称：1-闪贷；2-速贷；3-贷款管家**/
	private Integer appName;

	/**客户姓名**/
	private String name;

	/**手机号**/
	private String phoneNum;

	/**证件类型（1.身份证 2.护照 3.其他）**/
	private String certType;

	/**有效期限**/
	private String expirationDate;

	/**签发机构**/
	private String issueInstitution;

	/**证件号码**/
	private String certCode;

	/**性别（1-男，2 女）**/
	private String gender;

	/**邮箱**/
	private String email;

	/**年龄**/
	private Integer age;

	/**学历（PRE_HIGH_SCHOOL-高中以下，HIGH_SCHOOL-高中/中专，JUNIOR_COLLEGE-大专，UNDER_GRADUATE-本科，POST_GRADUATE-研究生）**/
	private String educationId;

	/**银行卡号**/
	private String bankCard;

	/** 婚姻状况：SPINSTERHOOD-未婚、MARRIED-已婚、DIVORCED-离异、WIDOWED-丧偶、REMARRY-再婚、REMARRY_FORMER-复婚**/
	private String ifMarriage;

	/**家庭住址（xx省xx市xx区xx苑xx幢xx单元xx室）**/
	private String homeAddress;

	/**户籍地址（xx省xx市xx县xx镇xx村xx组xx号）**/
	private String registeredAddress;

	/**通讯地址（xx省xx市xx区xx苑xx幢xx单元xx室）**/
	private String contactAddress;

	/**用户居住省份名称**/
	private String provinceName;

	/**用户居住时长（1-半年以内，2-半年到1年，3-1年以上）**/
	private String liveTime;

	/**银行卡号所属银行名称**/
	private String bankName;

	/**民族**/
	private String nation;

	/**出生年月日**/
	private String birth;

	/**备注**/
	private String remark;

	/**用户注册时间**/
	private String registerTime;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;


	public CuPlatformCustomerWeb() {
		super();
	}
	public CuPlatformCustomerWeb(String platformCustomerId,String customerId,Integer appName,String name,String phoneNum,String certType,String expirationDate,String issueInstitution,String certCode,String gender,String email,Integer age,String educationId,String bankCard,String ifMarriage,String homeAddress,String registeredAddress,String contactAddress,String provinceName,String liveTime,String bankName,String nation,String birth,String remark,String registerTime,String createTime,String createMan,String modifyTime,String modifyMan) {
		super();
		this.platformCustomerId = platformCustomerId;
		this.customerId = customerId;
		this.appName = appName;
		this.name = name;
		this.phoneNum = phoneNum;
		this.certType = certType;
		this.expirationDate = expirationDate;
		this.issueInstitution = issueInstitution;
		this.certCode = certCode;
		this.gender = gender;
		this.email = email;
		this.age = age;
		this.educationId = educationId;
		this.bankCard = bankCard;
		this.ifMarriage = ifMarriage;
		this.homeAddress = homeAddress;
		this.registeredAddress = registeredAddress;
		this.contactAddress = contactAddress;
		this.provinceName = provinceName;
		this.liveTime = liveTime;
		this.bankName = bankName;
		this.nation = nation;
		this.birth = birth;
		this.remark = remark;
		this.registerTime = registerTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}
	public void setPlatformCustomerId(String platformCustomerId){
		this.platformCustomerId = platformCustomerId;
	}

	public String getPlatformCustomerId(){
		return this.platformCustomerId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertType(){
		return this.certType;
	}

	public void setExpirationDate(String expirationDate){
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate(){
		return this.expirationDate;
	}

	public void setIssueInstitution(String issueInstitution){
		this.issueInstitution = issueInstitution;
	}

	public String getIssueInstitution(){
		return this.issueInstitution;
	}

	public void setCertCode(String certCode){
		this.certCode = certCode;
	}

	public String getCertCode(){
		return this.certCode;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return this.gender;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setAge(Integer age){
		this.age = age;
	}

	public Integer getAge(){
		return this.age;
	}

	public void setEducationId(String educationId){
		this.educationId = educationId;
	}

	public String getEducationId(){
		return this.educationId;
	}

	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}

	public String getBankCard(){
		return this.bankCard;
	}

	public void setIfMarriage(String ifMarriage){
		this.ifMarriage = ifMarriage;
	}

	public String getIfMarriage(){
		return this.ifMarriage;
	}

	public void setHomeAddress(String homeAddress){
		this.homeAddress = homeAddress;
	}

	public String getHomeAddress(){
		return this.homeAddress;
	}

	public void setRegisteredAddress(String registeredAddress){
		this.registeredAddress = registeredAddress;
	}

	public String getRegisteredAddress(){
		return this.registeredAddress;
	}

	public void setContactAddress(String contactAddress){
		this.contactAddress = contactAddress;
	}

	public String getContactAddress(){
		return this.contactAddress;
	}

	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}

	public String getProvinceName(){
		return this.provinceName;
	}

	public void setLiveTime(String liveTime){
		this.liveTime = liveTime;
	}

	public String getLiveTime(){
		return this.liveTime;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return this.bankName;
	}

	public void setNation(String nation){
		this.nation = nation;
	}

	public String getNation(){
		return this.nation;
	}

	public void setBirth(String birth){
		this.birth = birth;
	}

	public String getBirth(){
		return this.birth;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setRegisterTime(String registerTime){
		this.registerTime = registerTime;
	}

	public String getRegisterTime(){
		return this.registerTime;
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
