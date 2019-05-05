/**
  * Copyright 2018 bejson.com 
  */
package com.shangyong.backend.entity.tdReport.jsonbean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Auto-generated: 2018-03-16 11:24:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Call_duration_stats_2hour {

    private Call_duration_workday_6month call_duration_workday_6month;
    private Call_duration_holiday_6month call_duration_holiday_6month;
    private Call_duration_holiday_3month call_duration_holiday_3month;
    private Call_duration_workday_3month call_duration_workday_3month;
    public void setCall_duration_workday_6month(Call_duration_workday_6month call_duration_workday_6month) {
         this.call_duration_workday_6month = call_duration_workday_6month;
     }
     public Call_duration_workday_6month getCall_duration_workday_6month() {
         return call_duration_workday_6month;
     }

    public void setCall_duration_holiday_6month(Call_duration_holiday_6month call_duration_holiday_6month) {
         this.call_duration_holiday_6month = call_duration_holiday_6month;
     }
     public Call_duration_holiday_6month getCall_duration_holiday_6month() {
         return call_duration_holiday_6month;
     }

    public void setCall_duration_holiday_3month(Call_duration_holiday_3month call_duration_holiday_3month) {
         this.call_duration_holiday_3month = call_duration_holiday_3month;
     }
     public Call_duration_holiday_3month getCall_duration_holiday_3month() {
         return call_duration_holiday_3month;
     }

    public void setCall_duration_workday_3month(Call_duration_workday_3month call_duration_workday_3month) {
         this.call_duration_workday_3month = call_duration_workday_3month;
     }
     public Call_duration_workday_3month getCall_duration_workday_3month() {
         return call_duration_workday_3month;
     }

}