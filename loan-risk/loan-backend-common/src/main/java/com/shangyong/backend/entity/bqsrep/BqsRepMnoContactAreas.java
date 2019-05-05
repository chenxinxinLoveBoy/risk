package com.shangyong.backend.entity.bqsrep;


/**
 * 联系人通话活动地区		bean
 * @author chengfeng.lu
 * @date Sat Dec 16 14:57:05 CST 2017
 **/
public class BqsRepMnoContactAreas {

	/****/
	private String bqsRepMnoAreasId;

	/****/
	private String bqsPetitionerId;

	/**地区**/
	private String area;

	/**通话次数**/
	private String numberCount;

	/**通话号码个数**/
	private String callCount;

	/**呼出次数**/
	private String originatingCallCount;

	/**呼出时间(秒)**/
	private String originatingCallTime;

	/**呼入次数**/
	private String terminatingCallCount;

	/**呼入时间(秒)**/
	private String terminatingCallTime;

	/**呼入次数百分比**/
	private String callInCountPercentage;

	/**呼入时间百分比**/
	private String callInTimePercentage;

	/**呼出次数百分比**/
	private String callOutCountPercentage;

	/**呼出时间百分比**/
	private String callOutTimePercentage;

	/**平均通话时长（秒）**/
	private String avgCallTime;

	/**平均呼入时长（秒）**/
	private String avgCallInTime;

	/**通话呼出时长（秒）**/
	private String avgCallOutTime;


	public BqsRepMnoContactAreas() {
		super();
	}
	public BqsRepMnoContactAreas(String bqsRepMnoAreasId,String bqsPetitionerId,String area,String numberCount,String callCount,String originatingCallCount,String originatingCallTime,String terminatingCallCount,String terminatingCallTime,String callInCountPercentage,String callInTimePercentage,String callOutCountPercentage,String callOutTimePercentage,String avgCallTime,String avgCallInTime,String avgCallOutTime) {
		super();
		this.bqsRepMnoAreasId = bqsRepMnoAreasId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.area = area;
		this.numberCount = numberCount;
		this.callCount = callCount;
		this.originatingCallCount = originatingCallCount;
		this.originatingCallTime = originatingCallTime;
		this.terminatingCallCount = terminatingCallCount;
		this.terminatingCallTime = terminatingCallTime;
		this.callInCountPercentage = callInCountPercentage;
		this.callInTimePercentage = callInTimePercentage;
		this.callOutCountPercentage = callOutCountPercentage;
		this.callOutTimePercentage = callOutTimePercentage;
		this.avgCallTime = avgCallTime;
		this.avgCallInTime = avgCallInTime;
		this.avgCallOutTime = avgCallOutTime;
	}
	public void setBqsRepMnoAreasId(String bqsRepMnoAreasId){
		this.bqsRepMnoAreasId = bqsRepMnoAreasId;
	}

	public String getBqsRepMnoAreasId(){
		return this.bqsRepMnoAreasId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return this.area;
	}

	public void setNumberCount(String numberCount){
		this.numberCount = numberCount;
	}

	public String getNumberCount(){
		return this.numberCount;
	}

	public void setCallCount(String callCount){
		this.callCount = callCount;
	}

	public String getCallCount(){
		return this.callCount;
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

	public void setCallInCountPercentage(String callInCountPercentage){
		this.callInCountPercentage = callInCountPercentage;
	}

	public String getCallInCountPercentage(){
		return this.callInCountPercentage;
	}

	public void setCallInTimePercentage(String callInTimePercentage){
		this.callInTimePercentage = callInTimePercentage;
	}

	public String getCallInTimePercentage(){
		return this.callInTimePercentage;
	}

	public void setCallOutCountPercentage(String callOutCountPercentage){
		this.callOutCountPercentage = callOutCountPercentage;
	}

	public String getCallOutCountPercentage(){
		return this.callOutCountPercentage;
	}

	public void setCallOutTimePercentage(String callOutTimePercentage){
		this.callOutTimePercentage = callOutTimePercentage;
	}

	public String getCallOutTimePercentage(){
		return this.callOutTimePercentage;
	}

	public void setAvgCallTime(String avgCallTime){
		this.avgCallTime = avgCallTime;
	}

	public String getAvgCallTime(){
		return this.avgCallTime;
	}

	public void setAvgCallInTime(String avgCallInTime){
		this.avgCallInTime = avgCallInTime;
	}

	public String getAvgCallInTime(){
		return this.avgCallInTime;
	}

	public void setAvgCallOutTime(String avgCallOutTime){
		this.avgCallOutTime = avgCallOutTime;
	}

	public String getAvgCallOutTime(){
		return this.avgCallOutTime;
	}

}
