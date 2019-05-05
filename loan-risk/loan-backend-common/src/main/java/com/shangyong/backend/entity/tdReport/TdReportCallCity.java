package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告通话地区统计（城市）表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportCallCity {

	/**唯一标识**/
	private String callCityId;

	/**申请单编号**/
	private String applicationId;

	/**通话地城市**/
	private String callAreaCity;

	/**通话地排名**/
	private String callAreaSeqNo;

	/**近1月通话活跃天数**/
	private String activeDay1call1month;

	/**近1月主叫通话活跃天数**/
	private String activeDay1callActive1month;

	/**近3月通话活跃天数**/
	private String activeDay1call3month;

	/**近3月主叫通话活跃天数**/
	private String activeDay1callActive3month;

	/**近6月通话活跃天数**/
	private String activeDay1call6month;

	/**近6月主叫通话活跃天数**/
	private String activeDay1callActive6month;

	/**近1月通话次数**/
	private String callCount1month;

	/**近3月通话次数**/
	private String callCount3month;

	/**近3月主叫通话次数**/
	private String callCountActive3month;

	/**近3月被叫通话次数**/
	private String callCountPassive3month;

	/**近3月工作日通话次数**/
	private String callCountWorkday3month;

	/**近3月节假日通话次数**/
	private String callCountHoliday3month;

	/**近6月通话次数**/
	private String callCount6month;

	/**近6月主叫通话次数**/
	private String callCountActive6month;

	/**近6月被叫通话次数**/
	private String callCountPassive6month;

	/**近6月工作日通话次数**/
	private String callCountWorkday6month;

	/**近6月节假日通话次数**/
	private String callCountHoliday6month;

	/**近1月通话时长**/
	private String callTime1month;

	/**近3月通话时长**/
	private String callTime3month;

	/**近3月主叫通话时长**/
	private String callTimeActive3month;

	/**近3月被叫通话时长**/
	private String callTimePassive3month;

	/**近6月通话时长**/
	private String callTime6month;

	/**近6月主叫通话时长**/
	private String callTimeActive6month;

	/**近6月被叫通话时长**/
	private String callTimePassive6month;

	/**近1月连续通话活跃大于1天的次数**/
	private String continueActiveDayOver11call1month;

	/**近1月连续通话活跃大于3天的次数**/
	private String continueActiveDayOver31call1month;

	/**近3月连续通话活跃大于1天的次数**/
	private String continueActiveDayOver11call3month;

	/**近3月连续通话活跃大于3天的次数**/
	private String continueActiveDayOver31call3month;

	/**近6月连续通话活跃大于1天的次数**/
	private String continueActiveDayOver11call6month;

	/**近6月连续通话活跃大于3天的次数**/
	private String continueActiveDayOver31call6month;

	/**近1月最大连续通话活跃天数**/
	private String maxContinueActiveDay1call1month;

	/**近3月最大连续通话活跃天数**/
	private String maxContinueActiveDay1call3month;

	/**近6月最大连续通话活跃天数**/
	private String maxContinueActiveDay1call6month;

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


	public TdReportCallCity() {
		super();
	}
	public TdReportCallCity(String callCityId,String applicationId,String callAreaCity,String callAreaSeqNo,String activeDay1call1month,String activeDay1callActive1month,String activeDay1call3month,String activeDay1callActive3month,String activeDay1call6month,String activeDay1callActive6month,String callCount1month,String callCount3month,String callCountActive3month,String callCountPassive3month,String callCountWorkday3month,String callCountHoliday3month,String callCount6month,String callCountActive6month,String callCountPassive6month,String callCountWorkday6month,String callCountHoliday6month,String callTime1month,String callTime3month,String callTimeActive3month,String callTimePassive3month,String callTime6month,String callTimeActive6month,String callTimePassive6month,String continueActiveDayOver11call1month,String continueActiveDayOver31call1month,String continueActiveDayOver11call3month,String continueActiveDayOver31call3month,String continueActiveDayOver11call6month,String continueActiveDayOver31call6month,String maxContinueActiveDay1call1month,String maxContinueActiveDay1call3month,String maxContinueActiveDay1call6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.callCityId = callCityId;
		this.applicationId = applicationId;
		this.callAreaCity = callAreaCity;
		this.callAreaSeqNo = callAreaSeqNo;
		this.activeDay1call1month = activeDay1call1month;
		this.activeDay1callActive1month = activeDay1callActive1month;
		this.activeDay1call3month = activeDay1call3month;
		this.activeDay1callActive3month = activeDay1callActive3month;
		this.activeDay1call6month = activeDay1call6month;
		this.activeDay1callActive6month = activeDay1callActive6month;
		this.callCount1month = callCount1month;
		this.callCount3month = callCount3month;
		this.callCountActive3month = callCountActive3month;
		this.callCountPassive3month = callCountPassive3month;
		this.callCountWorkday3month = callCountWorkday3month;
		this.callCountHoliday3month = callCountHoliday3month;
		this.callCount6month = callCount6month;
		this.callCountActive6month = callCountActive6month;
		this.callCountPassive6month = callCountPassive6month;
		this.callCountWorkday6month = callCountWorkday6month;
		this.callCountHoliday6month = callCountHoliday6month;
		this.callTime1month = callTime1month;
		this.callTime3month = callTime3month;
		this.callTimeActive3month = callTimeActive3month;
		this.callTimePassive3month = callTimePassive3month;
		this.callTime6month = callTime6month;
		this.callTimeActive6month = callTimeActive6month;
		this.callTimePassive6month = callTimePassive6month;
		this.continueActiveDayOver11call1month = continueActiveDayOver11call1month;
		this.continueActiveDayOver31call1month = continueActiveDayOver31call1month;
		this.continueActiveDayOver11call3month = continueActiveDayOver11call3month;
		this.continueActiveDayOver31call3month = continueActiveDayOver31call3month;
		this.continueActiveDayOver11call6month = continueActiveDayOver11call6month;
		this.continueActiveDayOver31call6month = continueActiveDayOver31call6month;
		this.maxContinueActiveDay1call1month = maxContinueActiveDay1call1month;
		this.maxContinueActiveDay1call3month = maxContinueActiveDay1call3month;
		this.maxContinueActiveDay1call6month = maxContinueActiveDay1call6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCallCityId(String callCityId){
		this.callCityId = callCityId;
	}

	public String getCallCityId(){
		return this.callCityId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setCallAreaCity(String callAreaCity){
		this.callAreaCity = callAreaCity;
	}

	public String getCallAreaCity(){
		return this.callAreaCity;
	}

	public void setCallAreaSeqNo(String callAreaSeqNo){
		this.callAreaSeqNo = callAreaSeqNo;
	}

	public String getCallAreaSeqNo(){
		return this.callAreaSeqNo;
	}

	public void setActiveDay1call1month(String activeDay1call1month){
		this.activeDay1call1month = activeDay1call1month;
	}

	public String getActiveDay1call1month(){
		return this.activeDay1call1month;
	}

	public void setActiveDay1callActive1month(String activeDay1callActive1month){
		this.activeDay1callActive1month = activeDay1callActive1month;
	}

	public String getActiveDay1callActive1month(){
		return this.activeDay1callActive1month;
	}

	public void setActiveDay1call3month(String activeDay1call3month){
		this.activeDay1call3month = activeDay1call3month;
	}

	public String getActiveDay1call3month(){
		return this.activeDay1call3month;
	}

	public void setActiveDay1callActive3month(String activeDay1callActive3month){
		this.activeDay1callActive3month = activeDay1callActive3month;
	}

	public String getActiveDay1callActive3month(){
		return this.activeDay1callActive3month;
	}

	public void setActiveDay1call6month(String activeDay1call6month){
		this.activeDay1call6month = activeDay1call6month;
	}

	public String getActiveDay1call6month(){
		return this.activeDay1call6month;
	}

	public void setActiveDay1callActive6month(String activeDay1callActive6month){
		this.activeDay1callActive6month = activeDay1callActive6month;
	}

	public String getActiveDay1callActive6month(){
		return this.activeDay1callActive6month;
	}

	public void setCallCount1month(String callCount1month){
		this.callCount1month = callCount1month;
	}

	public String getCallCount1month(){
		return this.callCount1month;
	}

	public void setCallCount3month(String callCount3month){
		this.callCount3month = callCount3month;
	}

	public String getCallCount3month(){
		return this.callCount3month;
	}

	public void setCallCountActive3month(String callCountActive3month){
		this.callCountActive3month = callCountActive3month;
	}

	public String getCallCountActive3month(){
		return this.callCountActive3month;
	}

	public void setCallCountPassive3month(String callCountPassive3month){
		this.callCountPassive3month = callCountPassive3month;
	}

	public String getCallCountPassive3month(){
		return this.callCountPassive3month;
	}

	public void setCallCountWorkday3month(String callCountWorkday3month){
		this.callCountWorkday3month = callCountWorkday3month;
	}

	public String getCallCountWorkday3month(){
		return this.callCountWorkday3month;
	}

	public void setCallCountHoliday3month(String callCountHoliday3month){
		this.callCountHoliday3month = callCountHoliday3month;
	}

	public String getCallCountHoliday3month(){
		return this.callCountHoliday3month;
	}

	public void setCallCount6month(String callCount6month){
		this.callCount6month = callCount6month;
	}

	public String getCallCount6month(){
		return this.callCount6month;
	}

	public void setCallCountActive6month(String callCountActive6month){
		this.callCountActive6month = callCountActive6month;
	}

	public String getCallCountActive6month(){
		return this.callCountActive6month;
	}

	public void setCallCountPassive6month(String callCountPassive6month){
		this.callCountPassive6month = callCountPassive6month;
	}

	public String getCallCountPassive6month(){
		return this.callCountPassive6month;
	}

	public void setCallCountWorkday6month(String callCountWorkday6month){
		this.callCountWorkday6month = callCountWorkday6month;
	}

	public String getCallCountWorkday6month(){
		return this.callCountWorkday6month;
	}

	public void setCallCountHoliday6month(String callCountHoliday6month){
		this.callCountHoliday6month = callCountHoliday6month;
	}

	public String getCallCountHoliday6month(){
		return this.callCountHoliday6month;
	}

	public void setCallTime1month(String callTime1month){
		this.callTime1month = callTime1month;
	}

	public String getCallTime1month(){
		return this.callTime1month;
	}

	public void setCallTime3month(String callTime3month){
		this.callTime3month = callTime3month;
	}

	public String getCallTime3month(){
		return this.callTime3month;
	}

	public void setCallTimeActive3month(String callTimeActive3month){
		this.callTimeActive3month = callTimeActive3month;
	}

	public String getCallTimeActive3month(){
		return this.callTimeActive3month;
	}

	public void setCallTimePassive3month(String callTimePassive3month){
		this.callTimePassive3month = callTimePassive3month;
	}

	public String getCallTimePassive3month(){
		return this.callTimePassive3month;
	}

	public void setCallTime6month(String callTime6month){
		this.callTime6month = callTime6month;
	}

	public String getCallTime6month(){
		return this.callTime6month;
	}

	public void setCallTimeActive6month(String callTimeActive6month){
		this.callTimeActive6month = callTimeActive6month;
	}

	public String getCallTimeActive6month(){
		return this.callTimeActive6month;
	}

	public void setCallTimePassive6month(String callTimePassive6month){
		this.callTimePassive6month = callTimePassive6month;
	}

	public String getCallTimePassive6month(){
		return this.callTimePassive6month;
	}

	public void setContinueActiveDayOver11call1month(String continueActiveDayOver11call1month){
		this.continueActiveDayOver11call1month = continueActiveDayOver11call1month;
	}

	public String getContinueActiveDayOver11call1month(){
		return this.continueActiveDayOver11call1month;
	}

	public void setContinueActiveDayOver31call1month(String continueActiveDayOver31call1month){
		this.continueActiveDayOver31call1month = continueActiveDayOver31call1month;
	}

	public String getContinueActiveDayOver31call1month(){
		return this.continueActiveDayOver31call1month;
	}

	public void setContinueActiveDayOver11call3month(String continueActiveDayOver11call3month){
		this.continueActiveDayOver11call3month = continueActiveDayOver11call3month;
	}

	public String getContinueActiveDayOver11call3month(){
		return this.continueActiveDayOver11call3month;
	}

	public void setContinueActiveDayOver31call3month(String continueActiveDayOver31call3month){
		this.continueActiveDayOver31call3month = continueActiveDayOver31call3month;
	}

	public String getContinueActiveDayOver31call3month(){
		return this.continueActiveDayOver31call3month;
	}

	public void setContinueActiveDayOver11call6month(String continueActiveDayOver11call6month){
		this.continueActiveDayOver11call6month = continueActiveDayOver11call6month;
	}

	public String getContinueActiveDayOver11call6month(){
		return this.continueActiveDayOver11call6month;
	}

	public void setContinueActiveDayOver31call6month(String continueActiveDayOver31call6month){
		this.continueActiveDayOver31call6month = continueActiveDayOver31call6month;
	}

	public String getContinueActiveDayOver31call6month(){
		return this.continueActiveDayOver31call6month;
	}

	public void setMaxContinueActiveDay1call1month(String maxContinueActiveDay1call1month){
		this.maxContinueActiveDay1call1month = maxContinueActiveDay1call1month;
	}

	public String getMaxContinueActiveDay1call1month(){
		return this.maxContinueActiveDay1call1month;
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
