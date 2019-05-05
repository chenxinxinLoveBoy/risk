package com.shangyong.backend.entity.bqsrep.vo;

import java.util.List;

import com.shangyong.backend.entity.bqsrep.BqsRepAntiFraudCloud;
import com.shangyong.backend.entity.bqsrep.BqsRepContactsInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;
import com.shangyong.backend.entity.bqsrep.BqsRepDataSource;
import com.shangyong.backend.entity.bqsrep.BqsRepGoOut;
import com.shangyong.backend.entity.bqsrep.BqsRepHighRisk;
import com.shangyong.backend.entity.bqsrep.BqsRepHomeCheck;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepMnoPeriodUsedNew;

/**
 * 白骑士运营商报告申请人信息bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
public class BqsRepPetitionerVo {

	/****/
	private String bqsPetitionerId;

	/**申请单ID**/
	private String applicationId;

	/**报告编号**/
	private String reportSn;

	/**报告生成时间**/
	private String reportTime;

	/**姓名**/
	private String name;

	/**身份证**/
	private String certNo;

	/**手机号码**/
	private String mobile;

	/**手机号归属地**/
	private String mobileBelongTo;

	/**手机号运营商类型**/
	private String mobileMnoType;

	/**性别**/
	private String gender;

	/**星座**/
	private String constellation;

	/**年龄**/
	private String age;

	/**出生地**/
	private String birthAddress;

	/**备注**/
	private String bar;
	
	/**运营商基本信息**/
	private BqsRepMnoInfo bqsRepMnoInfo;
	
	/**数据来源**/
	private List<BqsRepDataSource> bqsRepDataSources;
	
	/**高风险名单**/
	private List<BqsRepHighRisk> bqsRepHighRisks;
	
	/**反欺诈云**/
	private BqsRepAntiFraudCloud bqsRepAntiFraudCloud;
	
	/**出行数据**/
	private List<BqsRepGoOut> bqsRepGoOuts;
	
	/**紧急联系人与常用联系人**/
	private List<BqsRepContactsInfo> bqsRepContactsInfos;
	
	/**分时间统计数据**/
	private List<BqsRepMnoPeriodUsedNew> bqsRepMnoPeriodUsedNews;
	
	/**家庭联系人**/
	private List<BqsRepHomeCheck> bqsRepHomeChecks;
	
	/**用户行为，活跃程度，通话行为，特殊通话检测**/
	private List<BqsRepCrossValidation> bqsRepCrossValidations;
	
	/****/
	/****/
	private String createTime;

	/****/
	private String modifyTime;

	/****/
	private String createMan;

	/****/
	private String modifyMan;

	public String getBqsPetitionerId() {
		return bqsPetitionerId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId) {
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getReportSn() {
		return reportSn;
	}

	public void setReportSn(String reportSn) {
		this.reportSn = reportSn;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobileBelongTo() {
		return mobileBelongTo;
	}

	public void setMobileBelongTo(String mobileBelongTo) {
		this.mobileBelongTo = mobileBelongTo;
	}

	public String getMobileMnoType() {
		return mobileMnoType;
	}

	public void setMobileMnoType(String mobileMnoType) {
		this.mobileMnoType = mobileMnoType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public BqsRepMnoInfo getBqsRepMnoInfo() {
		return bqsRepMnoInfo;
	}

	public void setBqsRepMnoInfo(BqsRepMnoInfo bqsRepMnoInfo) {
		this.bqsRepMnoInfo = bqsRepMnoInfo;
	}

	public List<BqsRepDataSource> getBqsRepDataSources() {
		return bqsRepDataSources;
	}

	public void setBqsRepDataSources(List<BqsRepDataSource> bqsRepDataSources) {
		this.bqsRepDataSources = bqsRepDataSources;
	}

	public List<BqsRepHighRisk> getBqsRepHighRisks() {
		return bqsRepHighRisks;
	}

	public void setBqsRepHighRisks(List<BqsRepHighRisk> bqsRepHighRisks) {
		this.bqsRepHighRisks = bqsRepHighRisks;
	}

	public BqsRepAntiFraudCloud getBqsRepAntiFraudCloud() {
		return bqsRepAntiFraudCloud;
	}

	public void setBqsRepAntiFraudCloud(BqsRepAntiFraudCloud bqsRepAntiFraudCloud) {
		this.bqsRepAntiFraudCloud = bqsRepAntiFraudCloud;
	}

	public List<BqsRepGoOut> getBqsRepGoOuts() {
		return bqsRepGoOuts;
	}

	public void setBqsRepGoOuts(List<BqsRepGoOut> bqsRepGoOuts) {
		this.bqsRepGoOuts = bqsRepGoOuts;
	}

	public List<BqsRepContactsInfo> getBqsRepContactsInfos() {
		return bqsRepContactsInfos;
	}

	public void setBqsRepContactsInfos(List<BqsRepContactsInfo> bqsRepContactsInfos) {
		this.bqsRepContactsInfos = bqsRepContactsInfos;
	}

	public List<BqsRepMnoPeriodUsedNew> getBqsRepMnoPeriodUsedNews() {
		return bqsRepMnoPeriodUsedNews;
	}

	public void setBqsRepMnoPeriodUsedNews(List<BqsRepMnoPeriodUsedNew> bqsRepMnoPeriodUsedNews) {
		this.bqsRepMnoPeriodUsedNews = bqsRepMnoPeriodUsedNews;
	}

	public List<BqsRepHomeCheck> getBqsRepHomeChecks() {
		return bqsRepHomeChecks;
	}

	public void setBqsRepHomeChecks(List<BqsRepHomeCheck> bqsRepHomeChecks) {
		this.bqsRepHomeChecks = bqsRepHomeChecks;
	}

	public List<BqsRepCrossValidation> getBqsRepCrossValidations() {
		return bqsRepCrossValidations;
	}

	public void setBqsRepCrossValidations(List<BqsRepCrossValidation> bqsRepCrossValidations) {
		this.bqsRepCrossValidations = bqsRepCrossValidations;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	@Override
	public String toString() {
		return "BqsRepPetitionerVo [bqsPetitionerId=" + bqsPetitionerId + ", applicationId=" + applicationId
				+ ", reportSn=" + reportSn + ", reportTime=" + reportTime + ", name=" + name + ", certNo=" + certNo
				+ ", mobile=" + mobile + ", mobileBelongTo=" + mobileBelongTo + ", mobileMnoType=" + mobileMnoType
				+ ", gender=" + gender + ", constellation=" + constellation + ", age=" + age + ", birthAddress="
				+ birthAddress + ", bar=" + bar + ", bqsRepMnoInfo=" + bqsRepMnoInfo + ", bqsRepDataSources="
				+ bqsRepDataSources + ", bqsRepHighRisks=" + bqsRepHighRisks + ", bqsRepAntiFraudCloud="
				+ bqsRepAntiFraudCloud + ", bqsRepGoOuts=" + bqsRepGoOuts + ", bqsRepContactsInfos="
				+ bqsRepContactsInfos + ", bqsRepMnoPeriodUsedNews=" + bqsRepMnoPeriodUsedNews + ", bqsRepHomeChecks="
				+ bqsRepHomeChecks + ", bqsRepCrossValidations=" + bqsRepCrossValidations + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", createMan=" + createMan + ", modifyMan=" + modifyMan + "]";
	}

	public BqsRepPetitionerVo(String bqsPetitionerId, String applicationId, String reportSn, String reportTime,
			String name, String certNo, String mobile, String mobileBelongTo, String mobileMnoType, String gender,
			String constellation, String age, String birthAddress, String bar, BqsRepMnoInfo bqsRepMnoInfo,
			List<BqsRepDataSource> bqsRepDataSources, List<BqsRepHighRisk> bqsRepHighRisks,
			BqsRepAntiFraudCloud bqsRepAntiFraudCloud, List<BqsRepGoOut> bqsRepGoOuts,
			List<BqsRepContactsInfo> bqsRepContactsInfos, List<BqsRepMnoPeriodUsedNew> bqsRepMnoPeriodUsedNews,
			List<BqsRepHomeCheck> bqsRepHomeChecks, List<BqsRepCrossValidation> bqsRepCrossValidations,
			String createTime, String modifyTime, String createMan, String modifyMan) {
		super();
		this.bqsPetitionerId = bqsPetitionerId;
		this.applicationId = applicationId;
		this.reportSn = reportSn;
		this.reportTime = reportTime;
		this.name = name;
		this.certNo = certNo;
		this.mobile = mobile;
		this.mobileBelongTo = mobileBelongTo;
		this.mobileMnoType = mobileMnoType;
		this.gender = gender;
		this.constellation = constellation;
		this.age = age;
		this.birthAddress = birthAddress;
		this.bar = bar;
		this.bqsRepMnoInfo = bqsRepMnoInfo;
		this.bqsRepDataSources = bqsRepDataSources;
		this.bqsRepHighRisks = bqsRepHighRisks;
		this.bqsRepAntiFraudCloud = bqsRepAntiFraudCloud;
		this.bqsRepGoOuts = bqsRepGoOuts;
		this.bqsRepContactsInfos = bqsRepContactsInfos;
		this.bqsRepMnoPeriodUsedNews = bqsRepMnoPeriodUsedNews;
		this.bqsRepHomeChecks = bqsRepHomeChecks;
		this.bqsRepCrossValidations = bqsRepCrossValidations;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}

	public BqsRepPetitionerVo() {
		super();
	}


}
