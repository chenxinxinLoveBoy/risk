package com.shangyong.backend.entity.tdReport;


/**
 * 同盾联系人黑名单多头分析表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportContactAnalysis {

	/**报告的唯一标识**/
	private String contactAnalysisId;

	/**申请单编号**/
	private String applicationId;

	/**前10联系人黑名单人数占比**/
	private String blackTop10ContactTotalCountRatio;

	/**前10联系人信贷逾期名单人数占比**/
	private String blackTop10ContactCreditcrackCountRatio;

	/**前10联系人游戏消费欠费人数占比**/
	private String blackTop10ContactPaymentfraudCountRatio;

	/**前10联系人信贷逾期后还款人数占比**/
	private String blackTop10ContactDiscreditrepayCountRati;

	/**前10联系人刷单人数占比**/
	private String blackTop10ContactScalpingCountRatio;

	/**前10联系人助学贷款逾期名单人数占比**/
	private String blackTop10ContactStudentloansOverdueCountRatio;

	/**前10联系人车贷黑名单人数占比**/
	private String blackTop10ContactCarloanBlacklistCountRatio;

	/**前10联系人虚假号码人数占比**/
	private String blackTop10ContactFakemobileCountRatio;

	/**前10联系人2种及以上黑名单人数占比**/
	private String blackTop10ContactOver2CountRatio;

	/**前10联系人近6月平均申请平台数**/
	private String manyheadsTop10ContactRecent6monthPartnercodeCountAvg;

	/**前10联系人近6月最大申请平台数**/
	private String manyheadsTop10ContactRecent6monthPartnercodeCountMax;

	/**前10联系人近6月有申请平台的人数**/
	private String manyheadsTop10ContactRecent6monthHavePartnercodeCount;

	/**前10联系人近6月申请2个及以上平台的人数**/
	private String manyheadsTop10ContactRecent6monthPartnercodeCountOver2;

	/**前10联系人近3月平均申请平台数**/
	private String manyheadsTop10ContactRecent3monthPartnercodeCountAvg;

	/**前10联系人近3月最大申请平台数**/
	private String manyheadsTop10ContactRecent3monthPartnercodeCountMax;

	/**前10联系人近3月有申请平台的人数**/
	private String manyheadsTop10ContactRecent3monthHavePartnercodeCount;

	/**前10联系人近3月申请2个及以上平台的人数**/
	private String manyheadsTop10ContactRecent3monthPartnercodeCountOver2;

	/**前10联系人近1月平均申请平台数**/
	private String manyheadsTop10ContactRecent1monthPartnercodeCountAvg;

	/**前10联系人近1月最大申请平台数**/
	private String manyheadsTop10ContactRecent1monthPartnercodeCountMax;

	/**前10联系人近1月有申请平台的人数**/
	private String manyheadsTop10ContactRecent1monthHavePartnercodeCount;

	/**前10联系人近1月申请2个及以上平台的人数**/
	private String manyheadsTop10ContactRecent1monthPartnercodeCountOver2;

	/**前10联系人平均智信分**/
	private String creditscoreTop10ContactAvg;

	/**前10联系人最大智信分**/
	private String creditscoreTop10ContactMax;

	/**前10联系人最小智信分**/
	private String creditscoreTop10ContactMin;

	/**前10联系人智信分中位数**/
	private String creditscoreTop10ContactMedian;

	/**前10联系人匹配智信分成功人数**/
	private String creditscoreTop10ContactCount;

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


	public TdReportContactAnalysis() {
		super();
	}
	public TdReportContactAnalysis(String contactAnalysisId,String applicationId,String blackTop10ContactTotalCountRatio,String blackTop10ContactCreditcrackCountRatio,String blackTop10ContactPaymentfraudCountRatio,String blackTop10ContactDiscreditrepayCountRati,String blackTop10ContactScalpingCountRatio,String blackTop10ContactStudentloansOverdueCountRatio,String blackTop10ContactCarloanBlacklistCountRatio,String blackTop10ContactFakemobileCountRatio,String blackTop10ContactOver2CountRatio,String manyheadsTop10ContactRecent6monthPartnercodeCountAvg,String manyheadsTop10ContactRecent6monthPartnercodeCountMax,String manyheadsTop10ContactRecent6monthHavePartnercodeCount,String manyheadsTop10ContactRecent6monthPartnercodeCountOver2,String manyheadsTop10ContactRecent3monthPartnercodeCountAvg,String manyheadsTop10ContactRecent3monthPartnercodeCountMax,String manyheadsTop10ContactRecent3monthHavePartnercodeCount,String manyheadsTop10ContactRecent3monthPartnercodeCountOver2,String manyheadsTop10ContactRecent1monthPartnercodeCountAvg,String manyheadsTop10ContactRecent1monthPartnercodeCountMax,String manyheadsTop10ContactRecent1monthHavePartnercodeCount,String manyheadsTop10ContactRecent1monthPartnercodeCountOver2,String creditscoreTop10ContactAvg,String creditscoreTop10ContactMax,String creditscoreTop10ContactMin,String creditscoreTop10ContactMedian,String creditscoreTop10ContactCount,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.contactAnalysisId = contactAnalysisId;
		this.applicationId = applicationId;
		this.blackTop10ContactTotalCountRatio = blackTop10ContactTotalCountRatio;
		this.blackTop10ContactCreditcrackCountRatio = blackTop10ContactCreditcrackCountRatio;
		this.blackTop10ContactPaymentfraudCountRatio = blackTop10ContactPaymentfraudCountRatio;
		this.blackTop10ContactDiscreditrepayCountRati = blackTop10ContactDiscreditrepayCountRati;
		this.blackTop10ContactScalpingCountRatio = blackTop10ContactScalpingCountRatio;
		this.blackTop10ContactStudentloansOverdueCountRatio = blackTop10ContactStudentloansOverdueCountRatio;
		this.blackTop10ContactCarloanBlacklistCountRatio = blackTop10ContactCarloanBlacklistCountRatio;
		this.blackTop10ContactFakemobileCountRatio = blackTop10ContactFakemobileCountRatio;
		this.blackTop10ContactOver2CountRatio = blackTop10ContactOver2CountRatio;
		this.manyheadsTop10ContactRecent6monthPartnercodeCountAvg = manyheadsTop10ContactRecent6monthPartnercodeCountAvg;
		this.manyheadsTop10ContactRecent6monthPartnercodeCountMax = manyheadsTop10ContactRecent6monthPartnercodeCountMax;
		this.manyheadsTop10ContactRecent6monthHavePartnercodeCount = manyheadsTop10ContactRecent6monthHavePartnercodeCount;
		this.manyheadsTop10ContactRecent6monthPartnercodeCountOver2 = manyheadsTop10ContactRecent6monthPartnercodeCountOver2;
		this.manyheadsTop10ContactRecent3monthPartnercodeCountAvg = manyheadsTop10ContactRecent3monthPartnercodeCountAvg;
		this.manyheadsTop10ContactRecent3monthPartnercodeCountMax = manyheadsTop10ContactRecent3monthPartnercodeCountMax;
		this.manyheadsTop10ContactRecent3monthHavePartnercodeCount = manyheadsTop10ContactRecent3monthHavePartnercodeCount;
		this.manyheadsTop10ContactRecent3monthPartnercodeCountOver2 = manyheadsTop10ContactRecent3monthPartnercodeCountOver2;
		this.manyheadsTop10ContactRecent1monthPartnercodeCountAvg = manyheadsTop10ContactRecent1monthPartnercodeCountAvg;
		this.manyheadsTop10ContactRecent1monthPartnercodeCountMax = manyheadsTop10ContactRecent1monthPartnercodeCountMax;
		this.manyheadsTop10ContactRecent1monthHavePartnercodeCount = manyheadsTop10ContactRecent1monthHavePartnercodeCount;
		this.manyheadsTop10ContactRecent1monthPartnercodeCountOver2 = manyheadsTop10ContactRecent1monthPartnercodeCountOver2;
		this.creditscoreTop10ContactAvg = creditscoreTop10ContactAvg;
		this.creditscoreTop10ContactMax = creditscoreTop10ContactMax;
		this.creditscoreTop10ContactMin = creditscoreTop10ContactMin;
		this.creditscoreTop10ContactMedian = creditscoreTop10ContactMedian;
		this.creditscoreTop10ContactCount = creditscoreTop10ContactCount;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setContactAnalysisId(String contactAnalysisId){
		this.contactAnalysisId = contactAnalysisId;
	}

	public String getContactAnalysisId(){
		return this.contactAnalysisId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setBlackTop10ContactTotalCountRatio(String blackTop10ContactTotalCountRatio){
		this.blackTop10ContactTotalCountRatio = blackTop10ContactTotalCountRatio;
	}

	public String getBlackTop10ContactTotalCountRatio(){
		return this.blackTop10ContactTotalCountRatio;
	}

	public void setBlackTop10ContactCreditcrackCountRatio(String blackTop10ContactCreditcrackCountRatio){
		this.blackTop10ContactCreditcrackCountRatio = blackTop10ContactCreditcrackCountRatio;
	}

	public String getBlackTop10ContactCreditcrackCountRatio(){
		return this.blackTop10ContactCreditcrackCountRatio;
	}

	public void setBlackTop10ContactPaymentfraudCountRatio(String blackTop10ContactPaymentfraudCountRatio){
		this.blackTop10ContactPaymentfraudCountRatio = blackTop10ContactPaymentfraudCountRatio;
	}

	public String getBlackTop10ContactPaymentfraudCountRatio(){
		return this.blackTop10ContactPaymentfraudCountRatio;
	}

	public void setBlackTop10ContactDiscreditrepayCountRati(String blackTop10ContactDiscreditrepayCountRati){
		this.blackTop10ContactDiscreditrepayCountRati = blackTop10ContactDiscreditrepayCountRati;
	}

	public String getBlackTop10ContactDiscreditrepayCountRati(){
		return this.blackTop10ContactDiscreditrepayCountRati;
	}

	public void setBlackTop10ContactScalpingCountRatio(String blackTop10ContactScalpingCountRatio){
		this.blackTop10ContactScalpingCountRatio = blackTop10ContactScalpingCountRatio;
	}

	public String getBlackTop10ContactScalpingCountRatio(){
		return this.blackTop10ContactScalpingCountRatio;
	}

	public void setBlackTop10ContactStudentloansOverdueCountRatio(String blackTop10ContactStudentloansOverdueCountRatio){
		this.blackTop10ContactStudentloansOverdueCountRatio = blackTop10ContactStudentloansOverdueCountRatio;
	}

	public String getBlackTop10ContactStudentloansOverdueCountRatio(){
		return this.blackTop10ContactStudentloansOverdueCountRatio;
	}

	public void setBlackTop10ContactCarloanBlacklistCountRatio(String blackTop10ContactCarloanBlacklistCountRatio){
		this.blackTop10ContactCarloanBlacklistCountRatio = blackTop10ContactCarloanBlacklistCountRatio;
	}

	public String getBlackTop10ContactCarloanBlacklistCountRatio(){
		return this.blackTop10ContactCarloanBlacklistCountRatio;
	}

	public void setBlackTop10ContactFakemobileCountRatio(String blackTop10ContactFakemobileCountRatio){
		this.blackTop10ContactFakemobileCountRatio = blackTop10ContactFakemobileCountRatio;
	}

	public String getBlackTop10ContactFakemobileCountRatio(){
		return this.blackTop10ContactFakemobileCountRatio;
	}

	public void setBlackTop10ContactOver2CountRatio(String blackTop10ContactOver2CountRatio){
		this.blackTop10ContactOver2CountRatio = blackTop10ContactOver2CountRatio;
	}

	public String getBlackTop10ContactOver2CountRatio(){
		return this.blackTop10ContactOver2CountRatio;
	}

	public void setManyheadsTop10ContactRecent6monthPartnercodeCountAvg(String manyheadsTop10ContactRecent6monthPartnercodeCountAvg){
		this.manyheadsTop10ContactRecent6monthPartnercodeCountAvg = manyheadsTop10ContactRecent6monthPartnercodeCountAvg;
	}

	public String getManyheadsTop10ContactRecent6monthPartnercodeCountAvg(){
		return this.manyheadsTop10ContactRecent6monthPartnercodeCountAvg;
	}

	public void setManyheadsTop10ContactRecent6monthPartnercodeCountMax(String manyheadsTop10ContactRecent6monthPartnercodeCountMax){
		this.manyheadsTop10ContactRecent6monthPartnercodeCountMax = manyheadsTop10ContactRecent6monthPartnercodeCountMax;
	}

	public String getManyheadsTop10ContactRecent6monthPartnercodeCountMax(){
		return this.manyheadsTop10ContactRecent6monthPartnercodeCountMax;
	}

	public void setManyheadsTop10ContactRecent6monthHavePartnercodeCount(String manyheadsTop10ContactRecent6monthHavePartnercodeCount){
		this.manyheadsTop10ContactRecent6monthHavePartnercodeCount = manyheadsTop10ContactRecent6monthHavePartnercodeCount;
	}

	public String getManyheadsTop10ContactRecent6monthHavePartnercodeCount(){
		return this.manyheadsTop10ContactRecent6monthHavePartnercodeCount;
	}

	public void setManyheadsTop10ContactRecent6monthPartnercodeCountOver2(String manyheadsTop10ContactRecent6monthPartnercodeCountOver2){
		this.manyheadsTop10ContactRecent6monthPartnercodeCountOver2 = manyheadsTop10ContactRecent6monthPartnercodeCountOver2;
	}

	public String getManyheadsTop10ContactRecent6monthPartnercodeCountOver2(){
		return this.manyheadsTop10ContactRecent6monthPartnercodeCountOver2;
	}

	public void setManyheadsTop10ContactRecent3monthPartnercodeCountAvg(String manyheadsTop10ContactRecent3monthPartnercodeCountAvg){
		this.manyheadsTop10ContactRecent3monthPartnercodeCountAvg = manyheadsTop10ContactRecent3monthPartnercodeCountAvg;
	}

	public String getManyheadsTop10ContactRecent3monthPartnercodeCountAvg(){
		return this.manyheadsTop10ContactRecent3monthPartnercodeCountAvg;
	}

	public void setManyheadsTop10ContactRecent3monthPartnercodeCountMax(String manyheadsTop10ContactRecent3monthPartnercodeCountMax){
		this.manyheadsTop10ContactRecent3monthPartnercodeCountMax = manyheadsTop10ContactRecent3monthPartnercodeCountMax;
	}

	public String getManyheadsTop10ContactRecent3monthPartnercodeCountMax(){
		return this.manyheadsTop10ContactRecent3monthPartnercodeCountMax;
	}

	public void setManyheadsTop10ContactRecent3monthHavePartnercodeCount(String manyheadsTop10ContactRecent3monthHavePartnercodeCount){
		this.manyheadsTop10ContactRecent3monthHavePartnercodeCount = manyheadsTop10ContactRecent3monthHavePartnercodeCount;
	}

	public String getManyheadsTop10ContactRecent3monthHavePartnercodeCount(){
		return this.manyheadsTop10ContactRecent3monthHavePartnercodeCount;
	}

	public void setManyheadsTop10ContactRecent3monthPartnercodeCountOver2(String manyheadsTop10ContactRecent3monthPartnercodeCountOver2){
		this.manyheadsTop10ContactRecent3monthPartnercodeCountOver2 = manyheadsTop10ContactRecent3monthPartnercodeCountOver2;
	}

	public String getManyheadsTop10ContactRecent3monthPartnercodeCountOver2(){
		return this.manyheadsTop10ContactRecent3monthPartnercodeCountOver2;
	}

	public void setManyheadsTop10ContactRecent1monthPartnercodeCountAvg(String manyheadsTop10ContactRecent1monthPartnercodeCountAvg){
		this.manyheadsTop10ContactRecent1monthPartnercodeCountAvg = manyheadsTop10ContactRecent1monthPartnercodeCountAvg;
	}

	public String getManyheadsTop10ContactRecent1monthPartnercodeCountAvg(){
		return this.manyheadsTop10ContactRecent1monthPartnercodeCountAvg;
	}

	public void setManyheadsTop10ContactRecent1monthPartnercodeCountMax(String manyheadsTop10ContactRecent1monthPartnercodeCountMax){
		this.manyheadsTop10ContactRecent1monthPartnercodeCountMax = manyheadsTop10ContactRecent1monthPartnercodeCountMax;
	}

	public String getManyheadsTop10ContactRecent1monthPartnercodeCountMax(){
		return this.manyheadsTop10ContactRecent1monthPartnercodeCountMax;
	}

	public void setManyheadsTop10ContactRecent1monthHavePartnercodeCount(String manyheadsTop10ContactRecent1monthHavePartnercodeCount){
		this.manyheadsTop10ContactRecent1monthHavePartnercodeCount = manyheadsTop10ContactRecent1monthHavePartnercodeCount;
	}

	public String getManyheadsTop10ContactRecent1monthHavePartnercodeCount(){
		return this.manyheadsTop10ContactRecent1monthHavePartnercodeCount;
	}

	public void setManyheadsTop10ContactRecent1monthPartnercodeCountOver2(String manyheadsTop10ContactRecent1monthPartnercodeCountOver2){
		this.manyheadsTop10ContactRecent1monthPartnercodeCountOver2 = manyheadsTop10ContactRecent1monthPartnercodeCountOver2;
	}

	public String getManyheadsTop10ContactRecent1monthPartnercodeCountOver2(){
		return this.manyheadsTop10ContactRecent1monthPartnercodeCountOver2;
	}

	public void setCreditscoreTop10ContactAvg(String creditscoreTop10ContactAvg){
		this.creditscoreTop10ContactAvg = creditscoreTop10ContactAvg;
	}

	public String getCreditscoreTop10ContactAvg(){
		return this.creditscoreTop10ContactAvg;
	}

	public void setCreditscoreTop10ContactMax(String creditscoreTop10ContactMax){
		this.creditscoreTop10ContactMax = creditscoreTop10ContactMax;
	}

	public String getCreditscoreTop10ContactMax(){
		return this.creditscoreTop10ContactMax;
	}

	public void setCreditscoreTop10ContactMin(String creditscoreTop10ContactMin){
		this.creditscoreTop10ContactMin = creditscoreTop10ContactMin;
	}

	public String getCreditscoreTop10ContactMin(){
		return this.creditscoreTop10ContactMin;
	}

	public void setCreditscoreTop10ContactMedian(String creditscoreTop10ContactMedian){
		this.creditscoreTop10ContactMedian = creditscoreTop10ContactMedian;
	}

	public String getCreditscoreTop10ContactMedian(){
		return this.creditscoreTop10ContactMedian;
	}

	public void setCreditscoreTop10ContactCount(String creditscoreTop10ContactCount){
		this.creditscoreTop10ContactCount = creditscoreTop10ContactCount;
	}

	public String getCreditscoreTop10ContactCount(){
		return this.creditscoreTop10ContactCount;
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
