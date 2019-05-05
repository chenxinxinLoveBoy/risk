package com.shangyong.backend.entity.bqsrep;


/**
 * 月使用信息bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
public class BqsRepMonthUsed {

	/****/
	private String bqsRepMonthUsedId;

	/****/
	private String bqsPetitionerId;

	/**号码	**/
	private String mobile;

	/**归属地**/
	private String belongTo;

	/**月份**/
	private String month;

	/**运营商类型(中国移动、中国联通、中国电信)**/
	private String monType;

	/**通话总次数**/
	private String callCount;

	/**通话总时长**/
	private String callTime;

	/**主叫次数**/
	private String originatingCallCount;

	/**主叫时间**/
	private String originatingCallTime;

	/**被叫次数**/
	private String terminatingCallCount;

	/**被叫时间**/
	private String terminatingCallTime;

	/**当月使用流量(M)**/
	private String networkTraffic;

	/**短信数量**/
	private String msgCount;

	/**消费金额（元）**/
	private String costMoney;

	/**平均通话时长**/
	private String avgCallTime;

	/**平均呼出时长	**/
	private String avgCallOutTime;

	/**平均呼入时长**/
	private String avgCallInTime;


	public BqsRepMonthUsed() {
		super();
	}
	public BqsRepMonthUsed(String bqsRepMonthUsedId,String bqsPetitionerId,String mobile,String belongTo,String month,String monType,String callCount,String callTime,String originatingCallCount,String originatingCallTime,String terminatingCallCount,String terminatingCallTime,String networkTraffic,String msgCount,String costMoney,String avgCallTime,String avgCallOutTime,String avgCallInTime) {
		super();
		this.bqsRepMonthUsedId = bqsRepMonthUsedId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.mobile = mobile;
		this.belongTo = belongTo;
		this.month = month;
		this.monType = monType;
		this.callCount = callCount;
		this.callTime = callTime;
		this.originatingCallCount = originatingCallCount;
		this.originatingCallTime = originatingCallTime;
		this.terminatingCallCount = terminatingCallCount;
		this.terminatingCallTime = terminatingCallTime;
		this.networkTraffic = networkTraffic;
		this.msgCount = msgCount;
		this.costMoney = costMoney;
		this.avgCallTime = avgCallTime;
		this.avgCallOutTime = avgCallOutTime;
		this.avgCallInTime = avgCallInTime;
	}
	public void setBqsRepMonthUsedId(String bqsRepMonthUsedId){
		this.bqsRepMonthUsedId = bqsRepMonthUsedId;
	}

	public String getBqsRepMonthUsedId(){
		return this.bqsRepMonthUsedId;
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

	public void setBelongTo(String belongTo){
		this.belongTo = belongTo;
	}

	public String getBelongTo(){
		return this.belongTo;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return this.month;
	}

	public void setMonType(String monType){
		this.monType = monType;
	}

	public String getMonType(){
		return this.monType;
	}

	public void setCallCount(String callCount){
		this.callCount = callCount;
	}

	public String getCallCount(){
		return this.callCount;
	}

	public void setCallTime(String callTime){
		this.callTime = callTime;
	}

	public String getCallTime(){
		return this.callTime;
	}

	public void setOriginatingCallCount(String originatingCallCount){
		this.originatingCallCount = originatingCallCount;
	}

	public String getOriginatingCallCount(){
		return this.originatingCallCount;
	}

	public void setOriginatingCallTime(String originatingCallTime){
		this.originatingCallTime = originatingCallTime;
	}

	public String getOriginatingCallTime(){
		return this.originatingCallTime;
	}

	public void setTerminatingCallCount(String terminatingCallCount){
		this.terminatingCallCount = terminatingCallCount;
	}

	public String getTerminatingCallCount(){
		return this.terminatingCallCount;
	}

	public void setTerminatingCallTime(String terminatingCallTime){
		this.terminatingCallTime = terminatingCallTime;
	}

	public String getTerminatingCallTime(){
		return this.terminatingCallTime;
	}

	public void setNetworkTraffic(String networkTraffic){
		this.networkTraffic = networkTraffic;
	}

	public String getNetworkTraffic(){
		return this.networkTraffic;
	}

	public void setMsgCount(String msgCount){
		this.msgCount = msgCount;
	}

	public String getMsgCount(){
		return this.msgCount;
	}

	public void setCostMoney(String costMoney){
		this.costMoney = costMoney;
	}

	public String getCostMoney(){
		return this.costMoney;
	}

	public void setAvgCallTime(String avgCallTime){
		this.avgCallTime = avgCallTime;
	}

	public String getAvgCallTime(){
		return this.avgCallTime;
	}

	public void setAvgCallOutTime(String avgCallOutTime){
		this.avgCallOutTime = avgCallOutTime;
	}

	public String getAvgCallOutTime(){
		return this.avgCallOutTime;
	}

	public void setAvgCallInTime(String avgCallInTime){
		this.avgCallInTime = avgCallInTime;
	}

	public String getAvgCallInTime(){
		return this.avgCallInTime;
	}

}
