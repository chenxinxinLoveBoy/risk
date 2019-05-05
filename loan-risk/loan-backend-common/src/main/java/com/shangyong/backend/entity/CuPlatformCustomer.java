package com.shangyong.backend.entity;

import java.io.Serializable;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 平台客户信息表bean
 * 
 * @author xk
 * @date Thu Jun 01 17:06:21 CST 2017
 **/
public class CuPlatformCustomer extends BaseBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 平台用户编号 **/
	private String platformCustomerId;

	/** APP应用客户编号 **/
	private String customerId;

	/** APP名称：1-闪贷；2-速贷 **/
	private String appName;

	/** 客户姓名 **/
	private String name;

	/** 手机号 **/
	private String phoneNum;

	/** 证件类型（1.身份证 2.护照 3.其他） **/
	private String certType;

	/** 证件号码 **/
	private String certCode;

	/** 性别（1-男，2 女） **/
	private String gender;

	/** 邮箱 **/
	private String email;

	/** 年龄 **/
	private Integer age;

	/** 学历（PRE_HIGH_SCHOOL-高中以下，HIGH_SCHOOL-高中/中专，JUNIOR_COLLEGE-大专，UNDER_GRADUATE-本科，POST_GRADUATE-研究生） **/
	private String educationId;

	/** 银行卡号 **/
	private String bankCard;

	/** SPINSTERHOOD-未婚、MARRIED-已婚、DIVORCED-离异、WIDOWED-丧偶、REMARRY-再婚、REMARRY_FORMER-复婚 **/
	private String ifMarriage;

	/** 家庭住址 **/
	private String homeAddress;

	/** 户籍地址 **/
	private String registeredAddress;

	/** 通讯地址 **/
	private String contactAddress;

	/** 银行卡号所属银行名称 **/
	private String bankName;

	/** 民族 **/
	private String nation;

	/** 出身年月日 **/
	private String birth;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 备注 **/
	private String remark;

	/**有效期限 **/
	private String expirationDate;
	
	/** 签发机构 **/
	private String issueInstitution;
	 
	/**居住时长**/
	private String liveTime;
	
	/**省份名称**/
	private String provinceName;
	
	/**注册时间**/
	private String registerTime;
	
	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;
	
	public CuPlatformCustomer() {
		super();
	}

	public CuPlatformCustomer(String platformCustomerId, String customerId, String appName, String name,
			String phoneNum, String certType, String certCode, String gender, String email, Integer age,
			String educationId, String bankCard, String ifMarriage, String homeAddress,
			String registeredAddress, String contactAddress, String bankName, String nation, String birth,
			String createTime, String createMan, String modifyTime,
			String modifyMan, String remark, String expirationDate, String issueInstitution, String liveTime,
			String provinceName, String registerTime) {
		super();
		this.platformCustomerId = platformCustomerId;
		this.customerId = customerId;
		this.appName = appName;
		this.name = name;
		this.phoneNum = phoneNum;
		this.certType = certType;
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
		this.bankName = bankName;
		this.nation = nation;
		this.birth = birth;
 		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.expirationDate = expirationDate;
		this.issueInstitution = issueInstitution;
		this.liveTime = liveTime;
		this.provinceName = provinceName;
		this.registerTime = registerTime;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(String liveTime) {
		this.liveTime = liveTime;
	}

	public String getProvinceName() {
		return provinceName;
	}


	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIssueInstitution() {
		return issueInstitution;
	}

	public void setIssueInstitution(String issueInstitution) {
		this.issueInstitution = issueInstitution;
	}

	public void setPlatformCustomerId(String platformCustomerId) {
		this.platformCustomerId = platformCustomerId;
	}

	public String getPlatformCustomerId() {
		return this.platformCustomerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertType() {
		return this.certType;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getCertCode() {
		return this.certCode;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	public String getEducationId() {
		return this.educationId;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankCard() {
		return this.bankCard;
	}

	public void setIfMarriage(String ifMarriage) {
		this.ifMarriage = ifMarriage;
	}

	public String getIfMarriage() {
		return this.ifMarriage;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getRegisteredAddress() {
		return this.registeredAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNation() {
		return this.nation;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getBirth() {
		return this.birth;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getCreateMan() {
		return this.createMan;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getModifyMan() {
		return this.modifyMan;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
}
