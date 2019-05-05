package com.shangyong.backend.entity.sh;


/**
 * 上海资信主表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCredit {

	/**主键自增id**/
	private String shCreditId;

	/**申请单编号**/
	private String applicationId;

	/**查询原因**/
	private String queryReason;

	/**报告编号**/
	private String reportNumber;

	/**报告时间**/
	private String reportTime;

	/**名字**/
	private String name;

	/**证件类型**/
	private String certType;

	/**身份证号码**/
	private String certCode;

	/**性别**/
	private String sex;

	/**出生日期**/
	private String birthTime;

	/**婚姻明细**/
	private String marriageDetail;

	/**婚姻信息获取时间**/
	private String marriageTime;
	
	/**学历明细**/
	private String education;
	
	/**学历信息获取时间**/
	private String educationTime;
	
	/**职称明细**/
	private String titleDetail;

	/**职称信息获取时间**/
	private String titleTime;

	/**住宅电话**/
	private String residencePhone;

	/**住宅电话获取时间**/
	private String residenceTime;

	/**手机号码**/
	private String phoneNumber;

	/**手机号码获取时间**/
	private String phoneTime;

	/**电子邮箱**/
	private String emailAddress;

	/**电子邮箱获取时间**/
	private String emailTime;

	/**配偶姓名**/
	private String mateName;

	/**配偶证件类型**/
	private String mateCertType;

	/**配偶证件号码**/
	private String mateCertCode;

	/**配偶性别**/
	private String mateSex;

	/**配偶出生日期**/
	private String mateBirthTime;

	/**配偶工作单位明细**/
	private String mateWork;

	/**配偶工作单位获取时间**/
	private String mateWorkTime;

	/**配偶电话**/
	private String matePhone;

	/**配偶电话获取时间**/
	private String matePhoneTime;

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


	public ShCredit() {
		super();
	}
	
	public void setShCreditId(String shCreditId){
		this.shCreditId = shCreditId;
	}

	public String getShCreditId(){
		return this.shCreditId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setQueryReason(String queryReason){
		this.queryReason = queryReason;
	}

	public String getQueryReason(){
		return this.queryReason;
	}

	public void setReportNumber(String reportNumber){
		this.reportNumber = reportNumber;
	}

	public String getReportNumber(){
		return this.reportNumber;
	}

	public void setReportTime(String reportTime){
		this.reportTime = reportTime;
	}

	public String getReportTime(){
		return this.reportTime;
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

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return this.sex;
	}

	public void setBirthTime(String birthTime){
		this.birthTime = birthTime;
	}

	public String getBirthTime(){
		return this.birthTime;
	}

	public void setMarriageDetail(String marriageDetail){
		this.marriageDetail = marriageDetail;
	}

	public String getMarriageDetail(){
		return this.marriageDetail;
	}

	public void setMarriageTime(String marriageTime){
		this.marriageTime = marriageTime;
	}

	public String getMarriageTime(){
		return this.marriageTime;
	}

	public void setTitleDetail(String titleDetail){
		this.titleDetail = titleDetail;
	}

	public String getTitleDetail(){
		return this.titleDetail;
	}

	public void setTitleTime(String titleTime){
		this.titleTime = titleTime;
	}

	public String getTitleTime(){
		return this.titleTime;
	}

	public void setResidencePhone(String residencePhone){
		this.residencePhone = residencePhone;
	}

	public String getResidencePhone(){
		return this.residencePhone;
	}

	public void setResidenceTime(String residenceTime){
		this.residenceTime = residenceTime;
	}

	public String getResidenceTime(){
		return this.residenceTime;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	public void setPhoneTime(String phoneTime){
		this.phoneTime = phoneTime;
	}

	public String getPhoneTime(){
		return this.phoneTime;
	}

	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}

	public String getEmailAddress(){
		return this.emailAddress;
	}

	public void setEmailTime(String emailTime){
		this.emailTime = emailTime;
	}

	public String getEmailTime(){
		return this.emailTime;
	}

	public void setMateName(String mateName){
		this.mateName = mateName;
	}

	public String getMateName(){
		return this.mateName;
	}

	public void setMateCertType(String mateCertType){
		this.mateCertType = mateCertType;
	}

	public String getMateCertType(){
		return this.mateCertType;
	}

	public void setMateCertCode(String mateCertCode){
		this.mateCertCode = mateCertCode;
	}

	public String getMateCertCode(){
		return this.mateCertCode;
	}

	public void setMateSex(String mateSex){
		this.mateSex = mateSex;
	}

	public String getMateSex(){
		return this.mateSex;
	}

	public void setMateBirthTime(String mateBirthTime){
		this.mateBirthTime = mateBirthTime;
	}

	public String getMateBirthTime(){
		return this.mateBirthTime;
	}

	public void setMateWork(String mateWork){
		this.mateWork = mateWork;
	}

	public String getMateWork(){
		return this.mateWork;
	}

	public void setMateWorkTime(String mateWorkTime){
		this.mateWorkTime = mateWorkTime;
	}

	public String getMateWorkTime(){
		return this.mateWorkTime;
	}

	public void setMatePhone(String matePhone){
		this.matePhone = matePhone;
	}

	public String getMatePhone(){
		return this.matePhone;
	}

	public void setMatePhoneTime(String matePhoneTime){
		this.matePhoneTime = matePhoneTime;
	}

	public String getMatePhoneTime(){
		return this.matePhoneTime;
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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEducationTime() {
		return educationTime;
	}

	public void setEducationTime(String educationTime) {
		this.educationTime = educationTime;
	}

	public ShCredit(String shCreditId, String applicationId, String queryReason, String reportNumber, String reportTime,
			String name, String certType, String certCode, String sex, String birthTime, String marriageDetail,
			String marriageTime, String education, String educationTime, String titleDetail, String titleTime,
			String residencePhone, String residenceTime, String phoneNumber, String phoneTime, String emailAddress,
			String emailTime, String mateName, String mateCertType, String mateCertCode, String mateSex,
			String mateBirthTime, String mateWork, String mateWorkTime, String matePhone, String matePhoneTime,
			String createTime, String createMan, String modifyTime, String modifyMan, String remark) {
		super();
		this.shCreditId = shCreditId;
		this.applicationId = applicationId;
		this.queryReason = queryReason;
		this.reportNumber = reportNumber;
		this.reportTime = reportTime;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.sex = sex;
		this.birthTime = birthTime;
		this.marriageDetail = marriageDetail;
		this.marriageTime = marriageTime;
		this.education = education;
		this.educationTime = educationTime;
		this.titleDetail = titleDetail;
		this.titleTime = titleTime;
		this.residencePhone = residencePhone;
		this.residenceTime = residenceTime;
		this.phoneNumber = phoneNumber;
		this.phoneTime = phoneTime;
		this.emailAddress = emailAddress;
		this.emailTime = emailTime;
		this.mateName = mateName;
		this.mateCertType = mateCertType;
		this.mateCertCode = mateCertCode;
		this.mateSex = mateSex;
		this.mateBirthTime = mateBirthTime;
		this.mateWork = mateWork;
		this.mateWorkTime = mateWorkTime;
		this.matePhone = matePhone;
		this.matePhoneTime = matePhoneTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	
}
