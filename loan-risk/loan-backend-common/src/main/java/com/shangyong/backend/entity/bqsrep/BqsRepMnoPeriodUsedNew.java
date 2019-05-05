package com.shangyong.backend.entity.bqsrep;


/**
 * 新版分时间段统计数据bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
public class BqsRepMnoPeriodUsedNew {

	/****/
	private String bqsMnoPeriodUsedNewId;

	/****/
	private String bqsPetitionerId;

	/**手机号 **/
	private String mobile;

	/**时间段类型（00:00 ~ 05:59, 06:00 ~ 11:59, 12:00 ~ 17:59, 18:00 ~ 23:59）**/
	private String periodType;

	/**运营商类型**/
	private String mnoType;

	/**手机号归属地	**/
	private String belongTo;

	/**主叫次数**/
	private String originatingCallCount;

	/**主叫时间(秒)**/
	private String originatingCallTime;

	/**被叫次数**/
	private String terminatingCallCount;

	/**被叫时间（秒）**/
	private String terminatingCallTime;

	/**短信数量**/
	private String msgCount;


	public BqsRepMnoPeriodUsedNew() {
		super();
	}
	public BqsRepMnoPeriodUsedNew(String bqsMnoPeriodUsedNewId,String bqsPetitionerId,String mobile,String periodType,String mnoType,String belongTo,String originatingCallCount,String originatingCallTime,String terminatingCallCount,String terminatingCallTime,String msgCount) {
		super();
		this.bqsMnoPeriodUsedNewId = bqsMnoPeriodUsedNewId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.mobile = mobile;
		this.periodType = periodType;
		this.mnoType = mnoType;
		this.belongTo = belongTo;
		this.originatingCallCount = originatingCallCount;
		this.originatingCallTime = originatingCallTime;
		this.terminatingCallCount = terminatingCallCount;
		this.terminatingCallTime = terminatingCallTime;
		this.msgCount = msgCount;
	}
	public void setBqsMnoPeriodUsedNewId(String bqsMnoPeriodUsedNewId){
		this.bqsMnoPeriodUsedNewId = bqsMnoPeriodUsedNewId;
	}

	public String getBqsMnoPeriodUsedNewId(){
		return this.bqsMnoPeriodUsedNewId;
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

	public void setPeriodType(String periodType){
		this.periodType = periodType;
	}

	public String getPeriodType(){
		return this.periodType;
	}

	public void setMnoType(String mnoType){
		this.mnoType = mnoType;
	}

	public String getMnoType(){
		return this.mnoType;
	}

	public void setBelongTo(String belongTo){
		this.belongTo = belongTo;
	}

	public String getBelongTo(){
		return this.belongTo;
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

	public void setMsgCount(String msgCount){
		this.msgCount = msgCount;
	}

	public String getMsgCount(){
		return this.msgCount;
	}

}
