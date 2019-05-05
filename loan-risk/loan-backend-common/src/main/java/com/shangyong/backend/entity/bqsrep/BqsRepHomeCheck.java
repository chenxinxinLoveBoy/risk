package com.shangyong.backend.entity.bqsrep;


/**
 * 家庭联系人信息bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
public class BqsRepHomeCheck {

	/****/
	private String bqsHomeCheckId;

	/****/
	private String bqsPetitionerId;

	/**家庭联系人电话**/
	private String mobile;

	/**家庭联系人电话归属地	**/
	private String belongTo;

	/**最近一次联系时间	**/
	private String latestConnectTime;

	/**最早一次联系时间**/
	private String firstConnectTime;

	/**近3天联系次数排序**/
	private String threeDaysConnectOrder;

	/**近3天联系次数	**/
	private String threeDaysConnectCount;

	/**近3天联系时长（单位：秒）**/
	private String threeDaysConnectDuration;

	/**近7天联系次数排序**/
	private String sevenDaysConnectOrder;

	/**近7天联系次数	**/
	private String sevenDaysConnectCount;

	/**近7天联系时长（单位：秒）**/
	private String sevenDaysConnectDuration;

	/**近30天联系次数排序**/
	private String thirtyDaysConnectOrder;

	/**近30天联系次数	**/
	private String thirtyDaysConnectCount;

	/**近30天联系时长（单位：秒）**/
	private String thirtyDaysConnectDuration;

	/**近半年联系次数排序**/
	private String connectOrder;

	/**近半年联系次数**/
	private String connectCount;

	/**近半年联系时长（单位：秒）**/
	private String connectDuration;

	/**近半年被叫次数**/
	private String callInCount;

	/**近半年被叫时长（单位：秒）**/
	private String callInTime;

	/**近半年主叫次数**/
	private String callOutCount;

	/**近半年主叫时长（单位：秒）**/
	private String callOutTime;

	/****/
	private String smsCount;


	public BqsRepHomeCheck() {
		super();
	}
	public BqsRepHomeCheck(String bqsHomeCheckId,String bqsPetitionerId,String mobile,String belongTo,String latestConnectTime,String firstConnectTime,String threeDaysConnectOrder,String threeDaysConnectCount,String threeDaysConnectDuration,String sevenDaysConnectOrder,String sevenDaysConnectCount,String sevenDaysConnectDuration,String thirtyDaysConnectOrder,String thirtyDaysConnectCount,String thirtyDaysConnectDuration,String connectOrder,String connectCount,String connectDuration,String callInCount,String callInTime,String callOutCount,String callOutTime,String smsCount) {
		super();
		this.bqsHomeCheckId = bqsHomeCheckId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.mobile = mobile;
		this.belongTo = belongTo;
		this.latestConnectTime = latestConnectTime;
		this.firstConnectTime = firstConnectTime;
		this.threeDaysConnectOrder = threeDaysConnectOrder;
		this.threeDaysConnectCount = threeDaysConnectCount;
		this.threeDaysConnectDuration = threeDaysConnectDuration;
		this.sevenDaysConnectOrder = sevenDaysConnectOrder;
		this.sevenDaysConnectCount = sevenDaysConnectCount;
		this.sevenDaysConnectDuration = sevenDaysConnectDuration;
		this.thirtyDaysConnectOrder = thirtyDaysConnectOrder;
		this.thirtyDaysConnectCount = thirtyDaysConnectCount;
		this.thirtyDaysConnectDuration = thirtyDaysConnectDuration;
		this.connectOrder = connectOrder;
		this.connectCount = connectCount;
		this.connectDuration = connectDuration;
		this.callInCount = callInCount;
		this.callInTime = callInTime;
		this.callOutCount = callOutCount;
		this.callOutTime = callOutTime;
		this.smsCount = smsCount;
	}
	public void setBqsHomeCheckId(String bqsHomeCheckId){
		this.bqsHomeCheckId = bqsHomeCheckId;
	}

	public String getBqsHomeCheckId(){
		return this.bqsHomeCheckId;
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

	public void setLatestConnectTime(String latestConnectTime){
		this.latestConnectTime = latestConnectTime;
	}

	public String getLatestConnectTime(){
		return this.latestConnectTime;
	}

	public void setFirstConnectTime(String firstConnectTime){
		this.firstConnectTime = firstConnectTime;
	}

	public String getFirstConnectTime(){
		return this.firstConnectTime;
	}

	public void setThreeDaysConnectOrder(String threeDaysConnectOrder){
		this.threeDaysConnectOrder = threeDaysConnectOrder;
	}

	public String getThreeDaysConnectOrder(){
		return this.threeDaysConnectOrder;
	}

	public void setThreeDaysConnectCount(String threeDaysConnectCount){
		this.threeDaysConnectCount = threeDaysConnectCount;
	}

	public String getThreeDaysConnectCount(){
		return this.threeDaysConnectCount;
	}

	public void setThreeDaysConnectDuration(String threeDaysConnectDuration){
		this.threeDaysConnectDuration = threeDaysConnectDuration;
	}

	public String getThreeDaysConnectDuration(){
		return this.threeDaysConnectDuration;
	}

	public void setSevenDaysConnectOrder(String sevenDaysConnectOrder){
		this.sevenDaysConnectOrder = sevenDaysConnectOrder;
	}

	public String getSevenDaysConnectOrder(){
		return this.sevenDaysConnectOrder;
	}

	public void setSevenDaysConnectCount(String sevenDaysConnectCount){
		this.sevenDaysConnectCount = sevenDaysConnectCount;
	}

	public String getSevenDaysConnectCount(){
		return this.sevenDaysConnectCount;
	}

	public void setSevenDaysConnectDuration(String sevenDaysConnectDuration){
		this.sevenDaysConnectDuration = sevenDaysConnectDuration;
	}

	public String getSevenDaysConnectDuration(){
		return this.sevenDaysConnectDuration;
	}

	public void setThirtyDaysConnectOrder(String thirtyDaysConnectOrder){
		this.thirtyDaysConnectOrder = thirtyDaysConnectOrder;
	}

	public String getThirtyDaysConnectOrder(){
		return this.thirtyDaysConnectOrder;
	}

	public void setThirtyDaysConnectCount(String thirtyDaysConnectCount){
		this.thirtyDaysConnectCount = thirtyDaysConnectCount;
	}

	public String getThirtyDaysConnectCount(){
		return this.thirtyDaysConnectCount;
	}

	public void setThirtyDaysConnectDuration(String thirtyDaysConnectDuration){
		this.thirtyDaysConnectDuration = thirtyDaysConnectDuration;
	}

	public String getThirtyDaysConnectDuration(){
		return this.thirtyDaysConnectDuration;
	}

	public void setConnectOrder(String connectOrder){
		this.connectOrder = connectOrder;
	}

	public String getConnectOrder(){
		return this.connectOrder;
	}

	public void setConnectCount(String connectCount){
		this.connectCount = connectCount;
	}

	public String getConnectCount(){
		return this.connectCount;
	}

	public void setConnectDuration(String connectDuration){
		this.connectDuration = connectDuration;
	}

	public String getConnectDuration(){
		return this.connectDuration;
	}

	public void setCallInCount(String callInCount){
		this.callInCount = callInCount;
	}

	public String getCallInCount(){
		return this.callInCount;
	}

	public void setCallInTime(String callInTime){
		this.callInTime = callInTime;
	}

	public String getCallInTime(){
		return this.callInTime;
	}

	public void setCallOutCount(String callOutCount){
		this.callOutCount = callOutCount;
	}

	public String getCallOutCount(){
		return this.callOutCount;
	}

	public void setCallOutTime(String callOutTime){
		this.callOutTime = callOutTime;
	}

	public String getCallOutTime(){
		return this.callOutTime;
	}

	public void setSmsCount(String smsCount){
		this.smsCount = smsCount;
	}

	public String getSmsCount(){
		return this.smsCount;
	}

}
