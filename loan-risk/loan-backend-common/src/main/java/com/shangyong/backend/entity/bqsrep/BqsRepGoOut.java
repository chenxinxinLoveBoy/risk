package com.shangyong.backend.entity.bqsrep;


/**
 * 出行数据bean
 * @author chengfeng.lu
 * @date Thu Dec 14 21:25:34 CST 2017
 **/
public class BqsRepGoOut {

	/****/
	private String bqsRepGoOutId;

	/****/
	private String bqsPetitionerId;

	/**出发地**/
	private String departure;

	/**目的地**/
	private String destination;

	/**出发时间**/
	private String beginTime;

	/**到达时间**/
	private String arriveTime;

	/**回程时间**/
	private String endTime;

	/**时间段**/
	private String period;


	public BqsRepGoOut() {
		super();
	}
	public BqsRepGoOut(String bqsRepGoOutId,String bqsPetitionerId,String departure,String destination,String beginTime,String arriveTime,String endTime,String period) {
		super();
		this.bqsRepGoOutId = bqsRepGoOutId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.departure = departure;
		this.destination = destination;
		this.beginTime = beginTime;
		this.arriveTime = arriveTime;
		this.endTime = endTime;
		this.period = period;
	}
	public void setBqsRepGoOutId(String bqsRepGoOutId){
		this.bqsRepGoOutId = bqsRepGoOutId;
	}

	public String getBqsRepGoOutId(){
		return this.bqsRepGoOutId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setDeparture(String departure){
		this.departure = departure;
	}

	public String getDeparture(){
		return this.departure;
	}

	public void setDestination(String destination){
		this.destination = destination;
	}

	public String getDestination(){
		return this.destination;
	}

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getBeginTime(){
		return this.beginTime;
	}

	public void setArriveTime(String arriveTime){
		this.arriveTime = arriveTime;
	}

	public String getArriveTime(){
		return this.arriveTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return this.endTime;
	}

	public void setPeriod(String period){
		this.period = period;
	}

	public String getPeriod(){
		return this.period;
	}

}
