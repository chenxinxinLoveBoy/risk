package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告静默活跃统计表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportSilenceStats {

	/**唯一标识**/
	private String silenceStatsId;

	/**申请单编号**/
	private String applicationId;

	/**近3月通话活跃天数**/
	private String activeDay1call3month;

	/**近6月通话活跃天数**/
	private String activeDay1call6month;

	/**近3月最大连续通话活跃天数**/
	private String maxContinueActiveDay1call3month;

	/**近6月最大连续通话活跃天数**/
	private String maxContinueActiveDay1call6month;

	/**近3月无通话静默天数**/
	private String silenceDay0call3month;

	/**近3月无主叫通话静默天数**/
	private String silenceDay0callActive3month;

	/**近3月无通话和发送短信静默天数**/
	private String silenceDay0call0msgSend3month;

	/**近6月无通话静默天数**/
	private String silenceDay0call6month;

	/**近6月无主叫通话静默天数**/
	private String silenceDay0callActive6month;

	/**近6月无通话和发送短信静默天数**/
	private String silenceDay0call0msgSend6month;

	/**近3月连续无通话静默>=3天的次数**/
	private String continueSilenceDayOver30call3month;

	/**近3月连续无通话静默>=15天的次数**/
	private String continueSilenceDayOver150call3month;

	/**近3月连续无主叫通话静默>=3天的次数**/
	private String continueSilenceDayOver30callActive3month;

	/**近3月连续无主叫通话静默>=15天的次数**/
	private String continueSilenceDayOver150callActive3month;

	/**近3月连续无通话和发送短信静默>=3天的次数**/
	private String continueSilenceDayOver30call0msgSend3month;

	/**近3月连续无通话和发送短信静默>=15天的次数**/
	private String continueSilenceDayOver150call0msgSend3month;

	/**近6月连续无通话静默>=3天的次数**/
	private String continueSilenceDayOver30call6month;

	/**近6月连续无通话静默>=15天的次数**/
	private String continueSilenceDayOver150call6month;

	/**近6月连续无主叫通话静默>=3天的次数**/
	private String continueSilenceDayOver30callActive6month;

	/**近6月连续无主叫通话静默>=15天的次数**/
	private String continueSilenceDayOver150callActive6month;

	/**近6月连续无通话和发送短信静默>=3天的次数**/
	private String continueSilenceDayOver30call0msgSend6month;

	/**近6月连续无通话和发送短信静默>=15天的次数**/
	private String continueSilenceDayOver150call0msgSend6month;

	/**近3月最大连续无通话静默天数**/
	private String maxContinueSilenceDay0call3month;

	/**近3月最大连续无主叫通话静默天数**/
	private String maxContinueSilenceDay0callActive3month;

	/**近3月最大连续无通话和发送短信静默天数**/
	private String maxContinueSilenceDay0call0msgSend3month;

	/**近6月最大连续无通话静默天数**/
	private String maxContinueSilenceDay0call6month;

	/**近6月最大连续无主叫通话静默天数**/
	private String maxContinueSilenceDay0callActive6month;

	/**近6月最大连续无通话和发送短信静默天数**/
	private String maxContinueSilenceDay0call0msgSend6month;

	/**近6月最后一次无通话静默间隔天数**/
	private String gapDayLastSilenceDay0call6month;

	/**近6月最后一次无主叫通话静默间隔天数**/
	private String gapDayLastSilenceDay0callActive6month;

	/**近6月最后一次无通话和发送短信静默间隔天数**/
	private String gapDayLastSilenceDay0call0msgSend6month;

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


	public TdReportSilenceStats() {
		super();
	}
	public TdReportSilenceStats(String silenceStatsId,String applicationId,String activeDay1call3month,String activeDay1call6month,String maxContinueActiveDay1call3month,String maxContinueActiveDay1call6month,String silenceDay0call3month,String silenceDay0callActive3month,String silenceDay0call0msgSend3month,String silenceDay0call6month,String silenceDay0callActive6month,String silenceDay0call0msgSend6month,String continueSilenceDayOver30call3month,String continueSilenceDayOver150call3month,String continueSilenceDayOver30callActive3month,String continueSilenceDayOver150callActive3month,String continueSilenceDayOver30call0msgSend3month,String continueSilenceDayOver150call0msgSend3month,String continueSilenceDayOver30call6month,String continueSilenceDayOver150call6month,String continueSilenceDayOver30callActive6month,String continueSilenceDayOver150callActive6month,String continueSilenceDayOver30call0msgSend6month,String continueSilenceDayOver150call0msgSend6month,String maxContinueSilenceDay0call3month,String maxContinueSilenceDay0callActive3month,String maxContinueSilenceDay0call0msgSend3month,String maxContinueSilenceDay0call6month,String maxContinueSilenceDay0callActive6month,String maxContinueSilenceDay0call0msgSend6month,String gapDayLastSilenceDay0call6month,String gapDayLastSilenceDay0callActive6month,String gapDayLastSilenceDay0call0msgSend6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.silenceStatsId = silenceStatsId;
		this.applicationId = applicationId;
		this.activeDay1call3month = activeDay1call3month;
		this.activeDay1call6month = activeDay1call6month;
		this.maxContinueActiveDay1call3month = maxContinueActiveDay1call3month;
		this.maxContinueActiveDay1call6month = maxContinueActiveDay1call6month;
		this.silenceDay0call3month = silenceDay0call3month;
		this.silenceDay0callActive3month = silenceDay0callActive3month;
		this.silenceDay0call0msgSend3month = silenceDay0call0msgSend3month;
		this.silenceDay0call6month = silenceDay0call6month;
		this.silenceDay0callActive6month = silenceDay0callActive6month;
		this.silenceDay0call0msgSend6month = silenceDay0call0msgSend6month;
		this.continueSilenceDayOver30call3month = continueSilenceDayOver30call3month;
		this.continueSilenceDayOver150call3month = continueSilenceDayOver150call3month;
		this.continueSilenceDayOver30callActive3month = continueSilenceDayOver30callActive3month;
		this.continueSilenceDayOver150callActive3month = continueSilenceDayOver150callActive3month;
		this.continueSilenceDayOver30call0msgSend3month = continueSilenceDayOver30call0msgSend3month;
		this.continueSilenceDayOver150call0msgSend3month = continueSilenceDayOver150call0msgSend3month;
		this.continueSilenceDayOver30call6month = continueSilenceDayOver30call6month;
		this.continueSilenceDayOver150call6month = continueSilenceDayOver150call6month;
		this.continueSilenceDayOver30callActive6month = continueSilenceDayOver30callActive6month;
		this.continueSilenceDayOver150callActive6month = continueSilenceDayOver150callActive6month;
		this.continueSilenceDayOver30call0msgSend6month = continueSilenceDayOver30call0msgSend6month;
		this.continueSilenceDayOver150call0msgSend6month = continueSilenceDayOver150call0msgSend6month;
		this.maxContinueSilenceDay0call3month = maxContinueSilenceDay0call3month;
		this.maxContinueSilenceDay0callActive3month = maxContinueSilenceDay0callActive3month;
		this.maxContinueSilenceDay0call0msgSend3month = maxContinueSilenceDay0call0msgSend3month;
		this.maxContinueSilenceDay0call6month = maxContinueSilenceDay0call6month;
		this.maxContinueSilenceDay0callActive6month = maxContinueSilenceDay0callActive6month;
		this.maxContinueSilenceDay0call0msgSend6month = maxContinueSilenceDay0call0msgSend6month;
		this.gapDayLastSilenceDay0call6month = gapDayLastSilenceDay0call6month;
		this.gapDayLastSilenceDay0callActive6month = gapDayLastSilenceDay0callActive6month;
		this.gapDayLastSilenceDay0call0msgSend6month = gapDayLastSilenceDay0call0msgSend6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setSilenceStatsId(String silenceStatsId){
		this.silenceStatsId = silenceStatsId;
	}

	public String getSilenceStatsId(){
		return this.silenceStatsId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setActiveDay1call3month(String activeDay1call3month){
		this.activeDay1call3month = activeDay1call3month;
	}

	public String getActiveDay1call3month(){
		return this.activeDay1call3month;
	}

	public void setActiveDay1call6month(String activeDay1call6month){
		this.activeDay1call6month = activeDay1call6month;
	}

	public String getActiveDay1call6month(){
		return this.activeDay1call6month;
	}

	public void setMaxContinueActiveDay1call3month(String maxContinueActiveDay1call3month){
		this.maxContinueActiveDay1call3month = maxContinueActiveDay1call3month;
	}

	public String getMaxContinueActiveDay1call3month(){
		return this.maxContinueActiveDay1call3month;
	}

	public void setMaxContinueActiveDay1call6month(String maxContinueActiveDay1call6month){
		this.maxContinueActiveDay1call6month = maxContinueActiveDay1call6month;
	}

	public String getMaxContinueActiveDay1call6month(){
		return this.maxContinueActiveDay1call6month;
	}

	public void setSilenceDay0call3month(String silenceDay0call3month){
		this.silenceDay0call3month = silenceDay0call3month;
	}

	public String getSilenceDay0call3month(){
		return this.silenceDay0call3month;
	}

	public void setSilenceDay0callActive3month(String silenceDay0callActive3month){
		this.silenceDay0callActive3month = silenceDay0callActive3month;
	}

	public String getSilenceDay0callActive3month(){
		return this.silenceDay0callActive3month;
	}

	public void setSilenceDay0call0msgSend3month(String silenceDay0call0msgSend3month){
		this.silenceDay0call0msgSend3month = silenceDay0call0msgSend3month;
	}

	public String getSilenceDay0call0msgSend3month(){
		return this.silenceDay0call0msgSend3month;
	}

	public void setSilenceDay0call6month(String silenceDay0call6month){
		this.silenceDay0call6month = silenceDay0call6month;
	}

	public String getSilenceDay0call6month(){
		return this.silenceDay0call6month;
	}

	public void setSilenceDay0callActive6month(String silenceDay0callActive6month){
		this.silenceDay0callActive6month = silenceDay0callActive6month;
	}

	public String getSilenceDay0callActive6month(){
		return this.silenceDay0callActive6month;
	}

	public void setSilenceDay0call0msgSend6month(String silenceDay0call0msgSend6month){
		this.silenceDay0call0msgSend6month = silenceDay0call0msgSend6month;
	}

	public String getSilenceDay0call0msgSend6month(){
		return this.silenceDay0call0msgSend6month;
	}

	public void setContinueSilenceDayOver30call3month(String continueSilenceDayOver30call3month){
		this.continueSilenceDayOver30call3month = continueSilenceDayOver30call3month;
	}

	public String getContinueSilenceDayOver30call3month(){
		return this.continueSilenceDayOver30call3month;
	}

	public void setContinueSilenceDayOver150call3month(String continueSilenceDayOver150call3month){
		this.continueSilenceDayOver150call3month = continueSilenceDayOver150call3month;
	}

	public String getContinueSilenceDayOver150call3month(){
		return this.continueSilenceDayOver150call3month;
	}

	public void setContinueSilenceDayOver30callActive3month(String continueSilenceDayOver30callActive3month){
		this.continueSilenceDayOver30callActive3month = continueSilenceDayOver30callActive3month;
	}

	public String getContinueSilenceDayOver30callActive3month(){
		return this.continueSilenceDayOver30callActive3month;
	}

	public void setContinueSilenceDayOver150callActive3month(String continueSilenceDayOver150callActive3month){
		this.continueSilenceDayOver150callActive3month = continueSilenceDayOver150callActive3month;
	}

	public String getContinueSilenceDayOver150callActive3month(){
		return this.continueSilenceDayOver150callActive3month;
	}

	public void setContinueSilenceDayOver30call0msgSend3month(String continueSilenceDayOver30call0msgSend3month){
		this.continueSilenceDayOver30call0msgSend3month = continueSilenceDayOver30call0msgSend3month;
	}

	public String getContinueSilenceDayOver30call0msgSend3month(){
		return this.continueSilenceDayOver30call0msgSend3month;
	}

	public void setContinueSilenceDayOver150call0msgSend3month(String continueSilenceDayOver150call0msgSend3month){
		this.continueSilenceDayOver150call0msgSend3month = continueSilenceDayOver150call0msgSend3month;
	}

	public String getContinueSilenceDayOver150call0msgSend3month(){
		return this.continueSilenceDayOver150call0msgSend3month;
	}

	public void setContinueSilenceDayOver30call6month(String continueSilenceDayOver30call6month){
		this.continueSilenceDayOver30call6month = continueSilenceDayOver30call6month;
	}

	public String getContinueSilenceDayOver30call6month(){
		return this.continueSilenceDayOver30call6month;
	}

	public void setContinueSilenceDayOver150call6month(String continueSilenceDayOver150call6month){
		this.continueSilenceDayOver150call6month = continueSilenceDayOver150call6month;
	}

	public String getContinueSilenceDayOver150call6month(){
		return this.continueSilenceDayOver150call6month;
	}

	public void setContinueSilenceDayOver30callActive6month(String continueSilenceDayOver30callActive6month){
		this.continueSilenceDayOver30callActive6month = continueSilenceDayOver30callActive6month;
	}

	public String getContinueSilenceDayOver30callActive6month(){
		return this.continueSilenceDayOver30callActive6month;
	}

	public void setContinueSilenceDayOver150callActive6month(String continueSilenceDayOver150callActive6month){
		this.continueSilenceDayOver150callActive6month = continueSilenceDayOver150callActive6month;
	}

	public String getContinueSilenceDayOver150callActive6month(){
		return this.continueSilenceDayOver150callActive6month;
	}

	public void setContinueSilenceDayOver30call0msgSend6month(String continueSilenceDayOver30call0msgSend6month){
		this.continueSilenceDayOver30call0msgSend6month = continueSilenceDayOver30call0msgSend6month;
	}

	public String getContinueSilenceDayOver30call0msgSend6month(){
		return this.continueSilenceDayOver30call0msgSend6month;
	}

	public void setContinueSilenceDayOver150call0msgSend6month(String continueSilenceDayOver150call0msgSend6month){
		this.continueSilenceDayOver150call0msgSend6month = continueSilenceDayOver150call0msgSend6month;
	}

	public String getContinueSilenceDayOver150call0msgSend6month(){
		return this.continueSilenceDayOver150call0msgSend6month;
	}

	public void setMaxContinueSilenceDay0call3month(String maxContinueSilenceDay0call3month){
		this.maxContinueSilenceDay0call3month = maxContinueSilenceDay0call3month;
	}

	public String getMaxContinueSilenceDay0call3month(){
		return this.maxContinueSilenceDay0call3month;
	}

	public void setMaxContinueSilenceDay0callActive3month(String maxContinueSilenceDay0callActive3month){
		this.maxContinueSilenceDay0callActive3month = maxContinueSilenceDay0callActive3month;
	}

	public String getMaxContinueSilenceDay0callActive3month(){
		return this.maxContinueSilenceDay0callActive3month;
	}

	public void setMaxContinueSilenceDay0call0msgSend3month(String maxContinueSilenceDay0call0msgSend3month){
		this.maxContinueSilenceDay0call0msgSend3month = maxContinueSilenceDay0call0msgSend3month;
	}

	public String getMaxContinueSilenceDay0call0msgSend3month(){
		return this.maxContinueSilenceDay0call0msgSend3month;
	}

	public void setMaxContinueSilenceDay0call6month(String maxContinueSilenceDay0call6month){
		this.maxContinueSilenceDay0call6month = maxContinueSilenceDay0call6month;
	}

	public String getMaxContinueSilenceDay0call6month(){
		return this.maxContinueSilenceDay0call6month;
	}

	public void setMaxContinueSilenceDay0callActive6month(String maxContinueSilenceDay0callActive6month){
		this.maxContinueSilenceDay0callActive6month = maxContinueSilenceDay0callActive6month;
	}

	public String getMaxContinueSilenceDay0callActive6month(){
		return this.maxContinueSilenceDay0callActive6month;
	}

	public void setMaxContinueSilenceDay0call0msgSend6month(String maxContinueSilenceDay0call0msgSend6month){
		this.maxContinueSilenceDay0call0msgSend6month = maxContinueSilenceDay0call0msgSend6month;
	}

	public String getMaxContinueSilenceDay0call0msgSend6month(){
		return this.maxContinueSilenceDay0call0msgSend6month;
	}

	public void setGapDayLastSilenceDay0call6month(String gapDayLastSilenceDay0call6month){
		this.gapDayLastSilenceDay0call6month = gapDayLastSilenceDay0call6month;
	}

	public String getGapDayLastSilenceDay0call6month(){
		return this.gapDayLastSilenceDay0call6month;
	}

	public void setGapDayLastSilenceDay0callActive6month(String gapDayLastSilenceDay0callActive6month){
		this.gapDayLastSilenceDay0callActive6month = gapDayLastSilenceDay0callActive6month;
	}

	public String getGapDayLastSilenceDay0callActive6month(){
		return this.gapDayLastSilenceDay0callActive6month;
	}

	public void setGapDayLastSilenceDay0call0msgSend6month(String gapDayLastSilenceDay0call0msgSend6month){
		this.gapDayLastSilenceDay0call0msgSend6month = gapDayLastSilenceDay0call0msgSend6month;
	}

	public String getGapDayLastSilenceDay0call0msgSend6month(){
		return this.gapDayLastSilenceDay0call0msgSend6month;
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
