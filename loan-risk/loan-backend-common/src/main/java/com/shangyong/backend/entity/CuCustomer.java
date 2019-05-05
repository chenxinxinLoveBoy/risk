package com.shangyong.backend.entity;


/**
 * 客户信息表bean
 * @author cg
 * @date Wed May 31 20:57:47 CST 2017
 **/
public class CuCustomer {

	/**客户编号**/
	private String customerId;

	/**客户姓名**/
	private String name;

	/**手机号**/
	private String phoneNum;

	/**身份证**/
	private String idCardNo;

	/**用户token**/
	private String tokenCode;

	/**性别**/
	private Integer gender;

	/**邮箱**/
	private String email;

	/**年龄**/
	private Integer age;

	/**学历**/
	private String educationId;

	/**职业编号**/
	private String professionId;

	/**银行卡号**/
	private String bankCard;

	/**婚姻状况**/
	private String ifMarriage;

	/**QQ**/
	private String qq;

	/**公司电话**/
	private String companyTel;

	/**公司地址**/
	private String companyAddress;

	/**公司名称**/
	private String companyName;

	/**公司行业**/
	private String companyIndustry;

	/**工作时长**/
	private String workingHours;

	/**是否通过实名认证**/
	private String isIdChecked;

	/**家庭地址**/
	private String homeAddress;

	/**户籍地址**/
	private String registeredAddress;

	/**通讯地址**/
	private String contactAddress;

	/**IP 地址**/
	private String ipAddress;

	/**同盾设备指纹网页端**/
	private String tokenId;

	/**同盾设备指纹移动端**/
	private String blackBox;

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


	public CuCustomer() {
		super();
	}
	public CuCustomer(String customerId,String name,String phoneNum,String idCardNo,String tokenCode,Integer gender,String email,Integer age,String educationId,String professionId,String bankCard,String ifMarriage,String qq,String companyTel,String companyAddress,String companyName,String companyIndustry,String workingHours,String isIdChecked,String homeAddress,String registeredAddress,String contactAddress,String ipAddress,String tokenId,String blackBox,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.phoneNum = phoneNum;
		this.idCardNo = idCardNo;
		this.tokenCode = tokenCode;
		this.gender = gender;
		this.email = email;
		this.age = age;
		this.educationId = educationId;
		this.professionId = professionId;
		this.bankCard = bankCard;
		this.ifMarriage = ifMarriage;
		this.qq = qq;
		this.companyTel = companyTel;
		this.companyAddress = companyAddress;
		this.companyName = companyName;
		this.companyIndustry = companyIndustry;
		this.workingHours = workingHours;
		this.isIdChecked = isIdChecked;
		this.homeAddress = homeAddress;
		this.registeredAddress = registeredAddress;
		this.contactAddress = contactAddress;
		this.ipAddress = ipAddress;
		this.tokenId = tokenId;
		this.blackBox = blackBox;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
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

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setIdCardNo(String idCardNo){
		this.idCardNo = idCardNo;
	}

	public String getIdCardNo(){
		return this.idCardNo;
	}

	public void setTokenCode(String tokenCode){
		this.tokenCode = tokenCode;
	}

	public String getTokenCode(){
		return this.tokenCode;
	}

	public void setGender(Integer gender){
		this.gender = gender;
	}

	public Integer getGender(){
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

	public void setProfessionId(String professionId){
		this.professionId = professionId;
	}

	public String getProfessionId(){
		return this.professionId;
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

	public void setQq(String qq){
		this.qq = qq;
	}

	public String getQq(){
		return this.qq;
	}

	public void setCompanyTel(String companyTel){
		this.companyTel = companyTel;
	}

	public String getCompanyTel(){
		return this.companyTel;
	}

	public void setCompanyAddress(String companyAddress){
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress(){
		return this.companyAddress;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return this.companyName;
	}

	public void setCompanyIndustry(String companyIndustry){
		this.companyIndustry = companyIndustry;
	}

	public String getCompanyIndustry(){
		return this.companyIndustry;
	}

	public void setWorkingHours(String workingHours){
		this.workingHours = workingHours;
	}

	public String getWorkingHours(){
		return this.workingHours;
	}

	public void setIsIdChecked(String isIdChecked){
		this.isIdChecked = isIdChecked;
	}

	public String getIsIdChecked(){
		return this.isIdChecked;
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

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

	public String getIpAddress(){
		return this.ipAddress;
	}

	public void setTokenId(String tokenId){
		this.tokenId = tokenId;
	}

	public String getTokenId(){
		return this.tokenId;
	}

	public void setBlackBox(String blackBox){
		this.blackBox = blackBox;
	}

	public String getBlackBox(){
		return this.blackBox;
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
