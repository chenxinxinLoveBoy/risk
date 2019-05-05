package com.shangyong.backend.entity.bqsrep;


/**
 * 常用联系电话（近3个月)bean
 * @author chengfeng.lu
 * @date Thu Dec 14 21:25:35 CST 2017
 **/
public class BqsRepMnoThreeMobile {

	/****/
	private String bqsMnoThreeInfoId;

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

	/**开始时间(时间戳类型)**/
	private String beginTime;

	/**第一次联系时间(时间戳类型)**/
	private String endTime;

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


	public BqsRepMnoThreeMobile() {
		super();
	}
	public BqsRepMnoThreeMobile(String bqsMnoThreeInfoId,String bqsPetitionerId,String mobile,String mobileTag,String mobileTagType,String financeTag,String beginTime,String endTime,String monType,String belongTo,String connectCount,String connectTime,String originatingCallCount,String originatingTime,String terminatingCallCount,String terminatingTime) {
		super();
		this.bqsMnoThreeInfoId = bqsMnoThreeInfoId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.mobile = mobile;
		this.mobileTag = mobileTag;
		this.mobileTagType = mobileTagType;
		this.financeTag = financeTag;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.monType = monType;
		this.belongTo = belongTo;
		this.connectCount = connectCount;
		this.connectTime = connectTime;
		this.originatingCallCount = originatingCallCount;
		this.originatingTime = originatingTime;
		this.terminatingCallCount = terminatingCallCount;
		this.terminatingTime = terminatingTime;
	}
	public void setBqsMnoThreeInfoId(String bqsMnoThreeInfoId){
		this.bqsMnoThreeInfoId = bqsMnoThreeInfoId;
	}

	public String getBqsMnoThreeInfoId(){
		return this.bqsMnoThreeInfoId;
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

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getBeginTime(){
		return this.beginTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return this.endTime;
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

}
