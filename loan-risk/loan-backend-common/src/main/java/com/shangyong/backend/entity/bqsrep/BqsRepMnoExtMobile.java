package com.shangyong.backend.entity.bqsrep;


/**
 * 常用联系电话额外bean
 * @author chengfeng.lu
 * @date Thu Dec 14 21:25:35 CST 2017
 **/
public class BqsRepMnoExtMobile {

	/****/
	private String bqsMnoSevenDayInfoId;

	/****/
	private String bqsPetitionerId;

	/**号码**/
	private String mobile;

	/**互联网标识**/
	private String mobileTag;

	/**互联网标识类型**/
	private String mobileTagType;

	/**金融标签**/
	private String financeTag;

	/**运营商类型(中国移动、中国联通、中国电信)**/
	private String monType;

	/**归属地**/
	private String belongTo;

	/**联系次数**/
	private String connectCount;

	/**联系时间（秒）**/
	private String connectTime;

	/**主叫次数**/
	private String originatingCallCount;

	/**主叫时长（秒）**/
	private String originatingTime;

	/**被叫次数**/
	private String terminatingCallCount;

	/**被叫时长（秒）	**/
	private String terminatingTime;

	/**近7天联系次数**/
	private String last7daysConnectCount;

	/**近1个月联系次数	**/
	private String last1monthConnectCount;

	/**近3个月联系次数**/
	private String last3monthConnectCount;

	/**3-6个月联系次数**/
	private String beyond3monthConnectCount;

	/**凌晨联系次数**/
	private String earlyMorningConnectCount;

	/**上午联系次数**/
	private String morningConnectCount;

	/**中午联系次数**/
	private String noonConnectCount;

	/**下午联系次数**/
	private String afternoonConnectCount;

	/**夜间联系次数**/
	private String nightConnectCount;

	/**是否全天联系**/
	private String isConnectAllDay;

	/**周中联系次数**/
	private String weekDayConnectCount;

	/**周末联系次数	**/
	private String weekendConnectCount;

	/**节假日联系次数**/
	private String holidayConnectCount;


	public BqsRepMnoExtMobile() {
		super();
	}
	public BqsRepMnoExtMobile(String bqsMnoSevenDayInfoId,String bqsPetitionerId,String mobile,String mobileTag,String mobileTagType,String financeTag,String monType,String belongTo,String connectCount,String connectTime,String originatingCallCount,String originatingTime,String terminatingCallCount,String terminatingTime,String last7daysConnectCount,String last1monthConnectCount,String last3monthConnectCount,String beyond3monthConnectCount,String earlyMorningConnectCount,String morningConnectCount,String noonConnectCount,String afternoonConnectCount,String nightConnectCount,String isConnectAllDay,String weekDayConnectCount,String weekendConnectCount,String holidayConnectCount) {
		super();
		this.bqsMnoSevenDayInfoId = bqsMnoSevenDayInfoId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.mobile = mobile;
		this.mobileTag = mobileTag;
		this.mobileTagType = mobileTagType;
		this.financeTag = financeTag;
		this.monType = monType;
		this.belongTo = belongTo;
		this.connectCount = connectCount;
		this.connectTime = connectTime;
		this.originatingCallCount = originatingCallCount;
		this.originatingTime = originatingTime;
		this.terminatingCallCount = terminatingCallCount;
		this.terminatingTime = terminatingTime;
		this.last7daysConnectCount = last7daysConnectCount;
		this.last1monthConnectCount = last1monthConnectCount;
		this.last3monthConnectCount = last3monthConnectCount;
		this.beyond3monthConnectCount = beyond3monthConnectCount;
		this.earlyMorningConnectCount = earlyMorningConnectCount;
		this.morningConnectCount = morningConnectCount;
		this.noonConnectCount = noonConnectCount;
		this.afternoonConnectCount = afternoonConnectCount;
		this.nightConnectCount = nightConnectCount;
		this.isConnectAllDay = isConnectAllDay;
		this.weekDayConnectCount = weekDayConnectCount;
		this.weekendConnectCount = weekendConnectCount;
		this.holidayConnectCount = holidayConnectCount;
	}
	public void setBqsMnoSevenDayInfoId(String bqsMnoSevenDayInfoId){
		this.bqsMnoSevenDayInfoId = bqsMnoSevenDayInfoId;
	}

	public String getBqsMnoSevenDayInfoId(){
		return this.bqsMnoSevenDayInfoId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setMobileTag(String mobileTag){
		this.mobileTag = mobileTag;
	}

	public String getMobileTag(){
		return this.mobileTag;
	}

	public void setMobileTagType(String mobileTagType){
		this.mobileTagType = mobileTagType;
	}

	public String getMobileTagType(){
		return this.mobileTagType;
	}

	public void setFinanceTag(String financeTag){
		this.financeTag = financeTag;
	}

	public String getFinanceTag(){
		return this.financeTag;
	}

	public void setMonType(String monType){
		this.monType = monType;
	}

	public String getMonType(){
		return this.monType;
	}

	public void setBelongTo(String belongTo){
		this.belongTo = belongTo;
	}

	public String getBelongTo(){
		return this.belongTo;
	}

	public void setConnectCount(String connectCount){
		this.connectCount = connectCount;
	}

	public String getConnectCount(){
		return this.connectCount;
	}

	public void setConnectTime(String connectTime){
		this.connectTime = connectTime;
	}

	public String getConnectTime(){
		return this.connectTime;
	}

	public void setOriginatingCallCount(String originatingCallCount){
		this.originatingCallCount = originatingCallCount;
	}

	public String getOriginatingCallCount(){
		return this.originatingCallCount;
	}

	public void setOriginatingTime(String originatingTime){
		this.originatingTime = originatingTime;
	}

	public String getOriginatingTime(){
		return this.originatingTime;
	}

	public void setTerminatingCallCount(String terminatingCallCount){
		this.terminatingCallCount = terminatingCallCount;
	}

	public String getTerminatingCallCount(){
		return this.terminatingCallCount;
	}

	public void setTerminatingTime(String terminatingTime){
		this.terminatingTime = terminatingTime;
	}

	public String getTerminatingTime(){
		return this.terminatingTime;
	}

	public void setLast7daysConnectCount(String last7daysConnectCount){
		this.last7daysConnectCount = last7daysConnectCount;
	}

	public String getLast7daysConnectCount(){
		return this.last7daysConnectCount;
	}

	public void setLast1monthConnectCount(String last1monthConnectCount){
		this.last1monthConnectCount = last1monthConnectCount;
	}

	public String getLast1monthConnectCount(){
		return this.last1monthConnectCount;
	}

	public void setLast3monthConnectCount(String last3monthConnectCount){
		this.last3monthConnectCount = last3monthConnectCount;
	}

	public String getLast3monthConnectCount(){
		return this.last3monthConnectCount;
	}

	public void setBeyond3monthConnectCount(String beyond3monthConnectCount){
		this.beyond3monthConnectCount = beyond3monthConnectCount;
	}

	public String getBeyond3monthConnectCount(){
		return this.beyond3monthConnectCount;
	}

	public void setEarlyMorningConnectCount(String earlyMorningConnectCount){
		this.earlyMorningConnectCount = earlyMorningConnectCount;
	}

	public String getEarlyMorningConnectCount(){
		return this.earlyMorningConnectCount;
	}

	public void setMorningConnectCount(String morningConnectCount){
		this.morningConnectCount = morningConnectCount;
	}

	public String getMorningConnectCount(){
		return this.morningConnectCount;
	}

	public void setNoonConnectCount(String noonConnectCount){
		this.noonConnectCount = noonConnectCount;
	}

	public String getNoonConnectCount(){
		return this.noonConnectCount;
	}

	public void setAfternoonConnectCount(String afternoonConnectCount){
		this.afternoonConnectCount = afternoonConnectCount;
	}

	public String getAfternoonConnectCount(){
		return this.afternoonConnectCount;
	}

	public void setNightConnectCount(String nightConnectCount){
		this.nightConnectCount = nightConnectCount;
	}

	public String getNightConnectCount(){
		return this.nightConnectCount;
	}

	public void setIsConnectAllDay(String isConnectAllDay){
		this.isConnectAllDay = isConnectAllDay;
	}

	public String getIsConnectAllDay(){
		return this.isConnectAllDay;
	}

	public void setWeekDayConnectCount(String weekDayConnectCount){
		this.weekDayConnectCount = weekDayConnectCount;
	}

	public String getWeekDayConnectCount(){
		return this.weekDayConnectCount;
	}

	public void setWeekendConnectCount(String weekendConnectCount){
		this.weekendConnectCount = weekendConnectCount;
	}

	public String getWeekendConnectCount(){
		return this.weekendConnectCount;
	}

	public void setHolidayConnectCount(String holidayConnectCount){
		this.holidayConnectCount = holidayConnectCount;
	}

	public String getHolidayConnectCount(){
		return this.holidayConnectCount;
	}

}
