/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.bqsrep.jsonbean;

/**
 * Auto-generated: 2017-12-14 18:42:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class GoOutDatas {

    private String departure;
    private String destination;
    private String beginTime;
    private String arriveTime;
    private String endTime;
    private String period;
    public void setDeparture(String departure) {
         this.departure = departure;
     }
     public String getDeparture() {
         return departure;
     }

    public void setDestination(String destination) {
         this.destination = destination;
     }
     public String getDestination() {
         return destination;
     }

    public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setPeriod(String period) {
         this.period = period;
     }
     public String getPeriod() {
         return period;
     }

}