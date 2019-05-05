/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.bqsrep.jsonbean;
import java.util.List;

/**
 * Auto-generated: 2017-12-14 18:42:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DataReport {

    private String reportSn;
    private String reportTime;
    private Petitioner petitioner;
    private MnoBaseInfo mnoBaseInfo;
    private List<WebDataSources> webDataSources;
    private BqsHighRiskList bqsHighRiskList;
    private BqsAntiFraudCloud bqsAntiFraudCloud;
    private CrossValidation crossValidation;
    private List<GoOutDatas> goOutDatas;
    private List<EmergencyContacts> emergencyContacts;
    private List<CommonlyContacts> commonlyContacts;
    private List<MnoCommonlyConnectMobiles> mnoCommonlyConnectMobiles;
    private List<MnoOneMonthCommonlyConnectMobiles> mnoOneMonthCommonlyConnectMobiles;
    private List<MnoThreeMonthCommonlyConnectMobiles> mnoThreeMonthCommonlyConnectMobiles;
    private List<Mno7daysCommonlyConnectMobiles> mno7daysCommonlyConnectMobiles;
    private List<Mno3daysCommonlyConnectMobiles> mno3daysCommonlyConnectMobiles;
    private List<MnoConnectMobilesExt> mnoConnectMobilesExt;
    private List<MnoCommonlyConnectAreas> mnoCommonlyConnectAreas;
    private List<MnoContactsCommonlyConnectAreas> mnoContactsCommonlyConnectAreas;
    private List<MnoPeriodUsedInfos> mnoPeriodUsedInfos;
    private List<MnoPeriodUsedInfosNew> mnoPeriodUsedInfosNew;
    private List<MnoMonthUsedInfos> mnoMonthUsedInfos;
    private UserInfoValidation userInfoValidation;
    private List<MnoCommonlyUsedServices> mnoCommonlyUsedServices;
    public void setReportSn(String reportSn) {
         this.reportSn = reportSn;
     }
     public String getReportSn() {
         return reportSn;
     }
    public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public void setPetitioner(Petitioner petitioner) {
         this.petitioner = petitioner;
     }
     public Petitioner getPetitioner() {
         return petitioner;
     }

    public void setMnoBaseInfo(MnoBaseInfo mnoBaseInfo) {
         this.mnoBaseInfo = mnoBaseInfo;
     }
     public MnoBaseInfo getMnoBaseInfo() {
         return mnoBaseInfo;
     }

    public void setWebDataSources(List<WebDataSources> webDataSources) {
         this.webDataSources = webDataSources;
     }
     public List<WebDataSources> getWebDataSources() {
         return webDataSources;
     }

    public void setBqsHighRiskList(BqsHighRiskList bqsHighRiskList) {
         this.bqsHighRiskList = bqsHighRiskList;
     }
     public BqsHighRiskList getBqsHighRiskList() {
         return bqsHighRiskList;
     }

    public void setBqsAntiFraudCloud(BqsAntiFraudCloud bqsAntiFraudCloud) {
         this.bqsAntiFraudCloud = bqsAntiFraudCloud;
     }
     public BqsAntiFraudCloud getBqsAntiFraudCloud() {
         return bqsAntiFraudCloud;
     }

    public void setCrossValidation(CrossValidation crossValidation) {
         this.crossValidation = crossValidation;
     }
     public CrossValidation getCrossValidation() {
         return crossValidation;
     }

    public void setGoOutDatas(List<GoOutDatas> goOutDatas) {
         this.goOutDatas = goOutDatas;
     }
     public List<GoOutDatas> getGoOutDatas() {
         return goOutDatas;
     }

    public void setEmergencyContacts(List<EmergencyContacts> emergencyContacts) {
         this.emergencyContacts = emergencyContacts;
     }
     public List<EmergencyContacts> getEmergencyContacts() {
         return emergencyContacts;
     }

    public void setCommonlyContacts(List<CommonlyContacts> commonlyContacts) {
         this.commonlyContacts = commonlyContacts;
     }
     public List<CommonlyContacts> getCommonlyContacts() {
         return commonlyContacts;
     }

    public void setMnoCommonlyConnectMobiles(List<MnoCommonlyConnectMobiles> mnoCommonlyConnectMobiles) {
         this.mnoCommonlyConnectMobiles = mnoCommonlyConnectMobiles;
     }
     public List<MnoCommonlyConnectMobiles> getMnoCommonlyConnectMobiles() {
         return mnoCommonlyConnectMobiles;
     }

    public void setMnoOneMonthCommonlyConnectMobiles(List<MnoOneMonthCommonlyConnectMobiles> mnoOneMonthCommonlyConnectMobiles) {
         this.mnoOneMonthCommonlyConnectMobiles = mnoOneMonthCommonlyConnectMobiles;
     }
     public List<MnoOneMonthCommonlyConnectMobiles> getMnoOneMonthCommonlyConnectMobiles() {
         return mnoOneMonthCommonlyConnectMobiles;
     }

    public void setMnoThreeMonthCommonlyConnectMobiles(List<MnoThreeMonthCommonlyConnectMobiles> mnoThreeMonthCommonlyConnectMobiles) {
         this.mnoThreeMonthCommonlyConnectMobiles = mnoThreeMonthCommonlyConnectMobiles;
     }
     public List<MnoThreeMonthCommonlyConnectMobiles> getMnoThreeMonthCommonlyConnectMobiles() {
         return mnoThreeMonthCommonlyConnectMobiles;
     }

    public void setMno7daysCommonlyConnectMobiles(List<Mno7daysCommonlyConnectMobiles> mno7daysCommonlyConnectMobiles) {
         this.mno7daysCommonlyConnectMobiles = mno7daysCommonlyConnectMobiles;
     }
     public List<Mno7daysCommonlyConnectMobiles> getMno7daysCommonlyConnectMobiles() {
         return mno7daysCommonlyConnectMobiles;
     }

    public void setMno3daysCommonlyConnectMobiles(List<Mno3daysCommonlyConnectMobiles> mno3daysCommonlyConnectMobiles) {
         this.mno3daysCommonlyConnectMobiles = mno3daysCommonlyConnectMobiles;
     }
     public List<Mno3daysCommonlyConnectMobiles> getMno3daysCommonlyConnectMobiles() {
         return mno3daysCommonlyConnectMobiles;
     }

    public void setMnoConnectMobilesExt(List<MnoConnectMobilesExt> mnoConnectMobilesExt) {
         this.mnoConnectMobilesExt = mnoConnectMobilesExt;
     }
     public List<MnoConnectMobilesExt> getMnoConnectMobilesExt() {
         return mnoConnectMobilesExt;
     }

    public void setMnoCommonlyConnectAreas(List<MnoCommonlyConnectAreas> mnoCommonlyConnectAreas) {
         this.mnoCommonlyConnectAreas = mnoCommonlyConnectAreas;
     }
     public List<MnoCommonlyConnectAreas> getMnoCommonlyConnectAreas() {
         return mnoCommonlyConnectAreas;
     }

    public void setMnoContactsCommonlyConnectAreas(List<MnoContactsCommonlyConnectAreas> mnoContactsCommonlyConnectAreas) {
         this.mnoContactsCommonlyConnectAreas = mnoContactsCommonlyConnectAreas;
     }
     public List<MnoContactsCommonlyConnectAreas> getMnoContactsCommonlyConnectAreas() {
         return mnoContactsCommonlyConnectAreas;
     }

    public void setMnoPeriodUsedInfos(List<MnoPeriodUsedInfos> mnoPeriodUsedInfos) {
         this.mnoPeriodUsedInfos = mnoPeriodUsedInfos;
     }
     public List<MnoPeriodUsedInfos> getMnoPeriodUsedInfos() {
         return mnoPeriodUsedInfos;
     }

    public void setMnoPeriodUsedInfosNew(List<MnoPeriodUsedInfosNew> mnoPeriodUsedInfosNew) {
         this.mnoPeriodUsedInfosNew = mnoPeriodUsedInfosNew;
     }
     public List<MnoPeriodUsedInfosNew> getMnoPeriodUsedInfosNew() {
         return mnoPeriodUsedInfosNew;
     }

    public void setMnoMonthUsedInfos(List<MnoMonthUsedInfos> mnoMonthUsedInfos) {
         this.mnoMonthUsedInfos = mnoMonthUsedInfos;
     }
     public List<MnoMonthUsedInfos> getMnoMonthUsedInfos() {
         return mnoMonthUsedInfos;
     }

    public void setUserInfoValidation(UserInfoValidation userInfoValidation) {
         this.userInfoValidation = userInfoValidation;
     }
     public UserInfoValidation getUserInfoValidation() {
         return userInfoValidation;
     }

    public void setMnoCommonlyUsedServices(List<MnoCommonlyUsedServices> mnoCommonlyUsedServices) {
         this.mnoCommonlyUsedServices = mnoCommonlyUsedServices;
     }
     public List<MnoCommonlyUsedServices> getMnoCommonlyUsedServices() {
         return mnoCommonlyUsedServices;
     }

}