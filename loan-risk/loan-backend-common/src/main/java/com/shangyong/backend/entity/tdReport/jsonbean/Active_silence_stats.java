/**
  * Copyright 2018 bejson.com 
  */
package com.shangyong.backend.entity.tdReport.jsonbean;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2018-03-16 11:24:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Active_silence_stats{
    private List<Continue_silence_day_over15_0call_3month_detail> continue_silence_day_over15_0call_3month_detail;
    @JsonProperty("active_day_1call_6month")
    private String activeDay1call6month;
    @JsonProperty("continue_silence_day_over15_0call_active_3month")
    private String continueSilenceDayOver150callActive3month;
    @JsonProperty("continue_silence_day_over15_0call_0msg_send_3month")
    private String continueSilenceDayOver150call0msgSend3month;
    @JsonProperty("active_day_1call_3month")
    private String activeDay1call3month;
    @JsonProperty("gap_day_last_silence_day_0call_active_6month")
    private String gapDayLastSilenceDay0callActive6month;
    private List<Continue_silence_day_over3_0call_6month_detail> continue_silence_day_over3_0call_6month_detail;
    @JsonProperty("max_continue_silence_day_0call_active_3month")
    private String maxContinueSilenceDay0callActive3month;
    @JsonProperty("max_continue_silence_day_0call_active_6month")
    private String maxContinueSilenceDay0callActive6month;
    @JsonProperty("continue_silence_day_over15_0call_0msg_send_6month")
    private String continueSilenceDayOver150call0msgSend6month;
    private List<Continue_silence_day_over15_0call_0msg_send_6month_detail> continue_silence_day_over15_0call_0msg_send_6month_detail;
    @JsonProperty("continue_silence_day_over15_0call_3month")
    private String continueSilenceDayOver150call3month;
    @JsonProperty("silence_day_0call_active_6month")
    private String silenceDay0callActive6month;
    private List<Continue_silence_day_over3_0call_3month_detail> continue_silence_day_over3_0call_3month_detail;
    @JsonProperty("continue_silence_day_over15_0call_6month")
    private String continueSilenceDayOver150call6month;
    @JsonProperty("silence_day_0call_active_3month")
    private String silenceDay0callActive3month;
    @JsonProperty("continue_silence_day_over3_0call_0msg_send_3month")
    private String continueSilenceDayOver30call0msgSend3month;
    private List<Continue_silence_day_over3_0call_active_3month_detail> continue_silence_day_over3_0call_active_3month_detail;
    private List<Continue_silence_day_over15_0call_6month_detail> continue_silence_day_over15_0call_6month_detail;
    @JsonProperty("continue_silence_day_over3_0call_active_6month")
    private String continueSilenceDayOver30callActive6month;
    private List<Continue_silence_day_over15_0call_0msg_send_3month_detail> continue_silence_day_over15_0call_0msg_send_3month_detail;
    private List<Continue_silence_day_over3_0call_active_6month_detail> continue_silence_day_over3_0call_active_6month_detail;
    @JsonProperty("silence_day_0call_3month")
    private String silenceDay0call3month;
    @JsonProperty("silence_day_0call_6month")
    private String silenceDay0call6month;
    @JsonProperty("continue_silence_day_over3_0call_active_3month")
    private String continueSilenceDayOver30callActive3month;
    private List<Continue_silence_day_over3_0call_0msg_send_3month_detail> continue_silence_day_over3_0call_0msg_send_3month_detail;
    private List<Continue_silence_day_over15_0call_active_6month_detail> continue_silence_day_over15_0call_active_6month_detail;
    @JsonProperty("max_continue_silence_day_0call_6month")
    private String maxContinueSilenceDay0call6month;
    @JsonProperty("gap_day_last_silence_day_0call_6month")
    private String gapDayLastSilenceDay0call6month;
    @JsonProperty("gap_day_last_silence_day_0call_0msg_send_6month")
    private String gapDayLastSilenceDay0call0msgSend6month;
    @JsonProperty("max_continue_silence_day_0call_3month")
    private String maxContinueSilenceDay0call3month;
    @JsonProperty("continue_silence_day_over3_0call_3month")
    private String continueSilenceDayOver30call3month;
    @JsonProperty("max_continue_active_day_1call_3month")
    private String maxContinueActiveDay1call3month;
    @JsonProperty("continue_silence_day_over3_0call_0msg_send_6month")
    private String continueSilenceDayOver30call0msgSend6month;
    private List<Continue_silence_day_over3_0call_0msg_send_6month_detail> continue_silence_day_over3_0call_0msg_send_6month_detail;
    @JsonProperty("max_continue_active_day_1call_6month")
    private String maxContinueActiveDay1call6month;
    @JsonProperty("continue_silence_day_over3_0call_6month")
    private String continueSilenceDayOver30call6month;
    @JsonProperty("max_continue_silence_day_0call_0msg_send_6month")
    private String maxContinueSilenceDay0call0msgSend6month;
    private List<Continue_silence_day_over15_0call_active_3month_detail> continue_silence_day_over15_0call_active_3month_detail;
    @JsonProperty("max_continue_silence_day_0call_0msg_send_3month")
    private String maxContinueSilenceDay0call0msgSend3month;
    @JsonProperty("silence_day_0call_0msg_send_3month")
    private String silenceDay0call0msgSend3month;
    @JsonProperty("silence_day_0call_0msg_send_6month")
    private String silenceDay0call0msgSend6month;
    @JsonProperty("continue_silence_day_over15_0call_active_6month")
    private String continueSilenceDayOver150callActive6month;
    public void setContinue_silence_day_over15_0call_3month_detail(List<Continue_silence_day_over15_0call_3month_detail> continue_silence_day_over15_0call_3month_detail) {
         this.continue_silence_day_over15_0call_3month_detail = continue_silence_day_over15_0call_3month_detail;
     }
     public List<Continue_silence_day_over15_0call_3month_detail> getContinue_silence_day_over15_0call_3month_detail() {
         return continue_silence_day_over15_0call_3month_detail;
     }
	public String getActiveDay1call6month() {
		return activeDay1call6month;
	}
	public void setActiveDay1call6month(String activeDay1call6month) {
		this.activeDay1call6month = activeDay1call6month;
	}
	public String getContinueSilenceDayOver150callActive3month() {
		return continueSilenceDayOver150callActive3month;
	}
	public void setContinueSilenceDayOver150callActive3month(String continueSilenceDayOver150callActive3month) {
		this.continueSilenceDayOver150callActive3month = continueSilenceDayOver150callActive3month;
	}
	public String getContinueSilenceDayOver150call0msgSend3month() {
		return continueSilenceDayOver150call0msgSend3month;
	}
	public void setContinueSilenceDayOver150call0msgSend3month(String continueSilenceDayOver150call0msgSend3month) {
		this.continueSilenceDayOver150call0msgSend3month = continueSilenceDayOver150call0msgSend3month;
	}
	public String getActiveDay1call3month() {
		return activeDay1call3month;
	}
	public void setActiveDay1call3month(String activeDay1call3month) {
		this.activeDay1call3month = activeDay1call3month;
	}
	public String getGapDayLastSilenceDay0callActive6month() {
		return gapDayLastSilenceDay0callActive6month;
	}
	public void setGapDayLastSilenceDay0callActive6month(String gapDayLastSilenceDay0callActive6month) {
		this.gapDayLastSilenceDay0callActive6month = gapDayLastSilenceDay0callActive6month;
	}
	public List<Continue_silence_day_over3_0call_6month_detail> getContinue_silence_day_over3_0call_6month_detail() {
		return continue_silence_day_over3_0call_6month_detail;
	}
	public void setContinue_silence_day_over3_0call_6month_detail(
			List<Continue_silence_day_over3_0call_6month_detail> continue_silence_day_over3_0call_6month_detail) {
		this.continue_silence_day_over3_0call_6month_detail = continue_silence_day_over3_0call_6month_detail;
	}
	public String getMaxContinueSilenceDay0callActive3month() {
		return maxContinueSilenceDay0callActive3month;
	}
	public void setMaxContinueSilenceDay0callActive3month(String maxContinueSilenceDay0callActive3month) {
		this.maxContinueSilenceDay0callActive3month = maxContinueSilenceDay0callActive3month;
	}
	public String getMaxContinueSilenceDay0callActive6month() {
		return maxContinueSilenceDay0callActive6month;
	}
	public void setMaxContinueSilenceDay0callActive6month(String maxContinueSilenceDay0callActive6month) {
		this.maxContinueSilenceDay0callActive6month = maxContinueSilenceDay0callActive6month;
	}
	public String getContinueSilenceDayOver150call0msgSend6month() {
		return continueSilenceDayOver150call0msgSend6month;
	}
	public void setContinueSilenceDayOver150call0msgSend6month(String continueSilenceDayOver150call0msgSend6month) {
		this.continueSilenceDayOver150call0msgSend6month = continueSilenceDayOver150call0msgSend6month;
	}
	public List<Continue_silence_day_over15_0call_0msg_send_6month_detail> getContinue_silence_day_over15_0call_0msg_send_6month_detail() {
		return continue_silence_day_over15_0call_0msg_send_6month_detail;
	}
	public void setContinue_silence_day_over15_0call_0msg_send_6month_detail(
			List<Continue_silence_day_over15_0call_0msg_send_6month_detail> continue_silence_day_over15_0call_0msg_send_6month_detail) {
		this.continue_silence_day_over15_0call_0msg_send_6month_detail = continue_silence_day_over15_0call_0msg_send_6month_detail;
	}
	public String getContinueSilenceDayOver150call3month() {
		return continueSilenceDayOver150call3month;
	}
	public void setContinueSilenceDayOver150call3month(String continueSilenceDayOver150call3month) {
		this.continueSilenceDayOver150call3month = continueSilenceDayOver150call3month;
	}
	public String getSilenceDay0callActive6month() {
		return silenceDay0callActive6month;
	}
	public void setSilenceDay0callActive6month(String silenceDay0callActive6month) {
		this.silenceDay0callActive6month = silenceDay0callActive6month;
	}
	public List<Continue_silence_day_over3_0call_3month_detail> getContinue_silence_day_over3_0call_3month_detail() {
		return continue_silence_day_over3_0call_3month_detail;
	}
	public void setContinue_silence_day_over3_0call_3month_detail(
			List<Continue_silence_day_over3_0call_3month_detail> continue_silence_day_over3_0call_3month_detail) {
		this.continue_silence_day_over3_0call_3month_detail = continue_silence_day_over3_0call_3month_detail;
	}
	public String getContinueSilenceDayOver150call6month() {
		return continueSilenceDayOver150call6month;
	}
	public void setContinueSilenceDayOver150call6month(String continueSilenceDayOver150call6month) {
		this.continueSilenceDayOver150call6month = continueSilenceDayOver150call6month;
	}
	public String getSilenceDay0callActive3month() {
		return silenceDay0callActive3month;
	}
	public void setSilenceDay0callActive3month(String silenceDay0callActive3month) {
		this.silenceDay0callActive3month = silenceDay0callActive3month;
	}
	public String getContinueSilenceDayOver30call0msgSend3month() {
		return continueSilenceDayOver30call0msgSend3month;
	}
	public void setContinueSilenceDayOver30call0msgSend3month(String continueSilenceDayOver30call0msgSend3month) {
		this.continueSilenceDayOver30call0msgSend3month = continueSilenceDayOver30call0msgSend3month;
	}
	public List<Continue_silence_day_over3_0call_active_3month_detail> getContinue_silence_day_over3_0call_active_3month_detail() {
		return continue_silence_day_over3_0call_active_3month_detail;
	}
	public void setContinue_silence_day_over3_0call_active_3month_detail(
			List<Continue_silence_day_over3_0call_active_3month_detail> continue_silence_day_over3_0call_active_3month_detail) {
		this.continue_silence_day_over3_0call_active_3month_detail = continue_silence_day_over3_0call_active_3month_detail;
	}

	public List<Continue_silence_day_over15_0call_6month_detail> getContinue_silence_day_over15_0call_6month_detail() {
		return continue_silence_day_over15_0call_6month_detail;
	}
	public void setContinue_silence_day_over15_0call_6month_detail(
			List<Continue_silence_day_over15_0call_6month_detail> continue_silence_day_over15_0call_6month_detail) {
		this.continue_silence_day_over15_0call_6month_detail = continue_silence_day_over15_0call_6month_detail;
	}
	public String getContinueSilenceDayOver30callActive6month() {
		return continueSilenceDayOver30callActive6month;
	}
	public void setContinueSilenceDayOver30callActive6month(String continueSilenceDayOver30callActive6month) {
		this.continueSilenceDayOver30callActive6month = continueSilenceDayOver30callActive6month;
	}
	
	public List<Continue_silence_day_over15_0call_0msg_send_3month_detail> getContinue_silence_day_over15_0call_0msg_send_3month_detail() {
		return continue_silence_day_over15_0call_0msg_send_3month_detail;
	}
	public void setContinue_silence_day_over15_0call_0msg_send_3month_detail(
			List<Continue_silence_day_over15_0call_0msg_send_3month_detail> continue_silence_day_over15_0call_0msg_send_3month_detail) {
		this.continue_silence_day_over15_0call_0msg_send_3month_detail = continue_silence_day_over15_0call_0msg_send_3month_detail;
	}
	public List<Continue_silence_day_over3_0call_active_6month_detail> getContinue_silence_day_over3_0call_active_6month_detail() {
		return continue_silence_day_over3_0call_active_6month_detail;
	}
	public void setContinue_silence_day_over3_0call_active_6month_detail(
			List<Continue_silence_day_over3_0call_active_6month_detail> continue_silence_day_over3_0call_active_6month_detail) {
		this.continue_silence_day_over3_0call_active_6month_detail = continue_silence_day_over3_0call_active_6month_detail;
	}
	public String getSilenceDay0call3month() {
		return silenceDay0call3month;
	}
	public void setSilenceDay0call3month(String silenceDay0call3month) {
		this.silenceDay0call3month = silenceDay0call3month;
	}
	public String getSilenceDay0call6month() {
		return silenceDay0call6month;
	}
	public void setSilenceDay0call6month(String silenceDay0call6month) {
		this.silenceDay0call6month = silenceDay0call6month;
	}
	public String getContinueSilenceDayOver30callActive3month() {
		return continueSilenceDayOver30callActive3month;
	}
	public void setContinueSilenceDayOver30callActive3month(String continueSilenceDayOver30callActive3month) {
		this.continueSilenceDayOver30callActive3month = continueSilenceDayOver30callActive3month;
	}
	public List<Continue_silence_day_over3_0call_0msg_send_3month_detail> getContinue_silence_day_over3_0call_0msg_send_3month_detail() {
		return continue_silence_day_over3_0call_0msg_send_3month_detail;
	}
	public void setContinue_silence_day_over3_0call_0msg_send_3month_detail(
			List<Continue_silence_day_over3_0call_0msg_send_3month_detail> continue_silence_day_over3_0call_0msg_send_3month_detail) {
		this.continue_silence_day_over3_0call_0msg_send_3month_detail = continue_silence_day_over3_0call_0msg_send_3month_detail;
	}
	public List<Continue_silence_day_over15_0call_active_6month_detail> getContinue_silence_day_over15_0call_active_6month_detail() {
		return continue_silence_day_over15_0call_active_6month_detail;
	}
	public void setContinue_silence_day_over15_0call_active_6month_detail(
			List<Continue_silence_day_over15_0call_active_6month_detail> continue_silence_day_over15_0call_active_6month_detail) {
		this.continue_silence_day_over15_0call_active_6month_detail = continue_silence_day_over15_0call_active_6month_detail;
	}
	public String getMaxContinueSilenceDay0call6month() {
		return maxContinueSilenceDay0call6month;
	}
	public void setMaxContinueSilenceDay0call6month(String maxContinueSilenceDay0call6month) {
		this.maxContinueSilenceDay0call6month = maxContinueSilenceDay0call6month;
	}
	public String getGapDayLastSilenceDay0call6month() {
		return gapDayLastSilenceDay0call6month;
	}
	public void setGapDayLastSilenceDay0call6month(String gapDayLastSilenceDay0call6month) {
		this.gapDayLastSilenceDay0call6month = gapDayLastSilenceDay0call6month;
	}
	public String getGapDayLastSilenceDay0call0msgSend6month() {
		return gapDayLastSilenceDay0call0msgSend6month;
	}
	public void setGapDayLastSilenceDay0call0msgSend6month(String gapDayLastSilenceDay0call0msgSend6month) {
		this.gapDayLastSilenceDay0call0msgSend6month = gapDayLastSilenceDay0call0msgSend6month;
	}
	public String getMaxContinueSilenceDay0call3month() {
		return maxContinueSilenceDay0call3month;
	}
	public void setMaxContinueSilenceDay0call3month(String maxContinueSilenceDay0call3month) {
		this.maxContinueSilenceDay0call3month = maxContinueSilenceDay0call3month;
	}
	public String getContinueSilenceDayOver30call3month() {
		return continueSilenceDayOver30call3month;
	}
	public void setContinueSilenceDayOver30call3month(String continueSilenceDayOver30call3month) {
		this.continueSilenceDayOver30call3month = continueSilenceDayOver30call3month;
	}
	public String getMaxContinueActiveDay1call3month() {
		return maxContinueActiveDay1call3month;
	}
	public void setMaxContinueActiveDay1call3month(String maxContinueActiveDay1call3month) {
		this.maxContinueActiveDay1call3month = maxContinueActiveDay1call3month;
	}
	public String getContinueSilenceDayOver30call0msgSend6month() {
		return continueSilenceDayOver30call0msgSend6month;
	}
	public void setContinueSilenceDayOver30call0msgSend6month(String continueSilenceDayOver30call0msgSend6month) {
		this.continueSilenceDayOver30call0msgSend6month = continueSilenceDayOver30call0msgSend6month;
	}
	public List<Continue_silence_day_over3_0call_0msg_send_6month_detail> getContinue_silence_day_over3_0call_0msg_send_6month_detail() {
		return continue_silence_day_over3_0call_0msg_send_6month_detail;
	}
	public void setContinue_silence_day_over3_0call_0msg_send_6month_detail(
			List<Continue_silence_day_over3_0call_0msg_send_6month_detail> continue_silence_day_over3_0call_0msg_send_6month_detail) {
		this.continue_silence_day_over3_0call_0msg_send_6month_detail = continue_silence_day_over3_0call_0msg_send_6month_detail;
	}
	public String getMaxContinueActiveDay1call6month() {
		return maxContinueActiveDay1call6month;
	}
	public void setMaxContinueActiveDay1call6month(String maxContinueActiveDay1call6month) {
		this.maxContinueActiveDay1call6month = maxContinueActiveDay1call6month;
	}
	public String getContinueSilenceDayOver30call6month() {
		return continueSilenceDayOver30call6month;
	}
	public void setContinueSilenceDayOver30call6month(String continueSilenceDayOver30call6month) {
		this.continueSilenceDayOver30call6month = continueSilenceDayOver30call6month;
	}
	public String getMaxContinueSilenceDay0call0msgSend6month() {
		return maxContinueSilenceDay0call0msgSend6month;
	}
	public void setMaxContinueSilenceDay0call0msgSend6month(String maxContinueSilenceDay0call0msgSend6month) {
		this.maxContinueSilenceDay0call0msgSend6month = maxContinueSilenceDay0call0msgSend6month;
	}
	public List<Continue_silence_day_over15_0call_active_3month_detail> getContinue_silence_day_over15_0call_active_3month_detail() {
		return continue_silence_day_over15_0call_active_3month_detail;
	}
	public void setContinue_silence_day_over15_0call_active_3month_detail(
			List<Continue_silence_day_over15_0call_active_3month_detail> continue_silence_day_over15_0call_active_3month_detail) {
		this.continue_silence_day_over15_0call_active_3month_detail = continue_silence_day_over15_0call_active_3month_detail;
	}
	public String getMaxContinueSilenceDay0call0msgSend3month() {
		return maxContinueSilenceDay0call0msgSend3month;
	}
	public void setMaxContinueSilenceDay0call0msgSend3month(String maxContinueSilenceDay0call0msgSend3month) {
		this.maxContinueSilenceDay0call0msgSend3month = maxContinueSilenceDay0call0msgSend3month;
	}
	public String getSilenceDay0call0msgSend3month() {
		return silenceDay0call0msgSend3month;
	}
	public void setSilenceDay0call0msgSend3month(String silenceDay0call0msgSend3month) {
		this.silenceDay0call0msgSend3month = silenceDay0call0msgSend3month;
	}
	public String getSilenceDay0call0msgSend6month() {
		return silenceDay0call0msgSend6month;
	}
	public void setSilenceDay0call0msgSend6month(String silenceDay0call0msgSend6month) {
		this.silenceDay0call0msgSend6month = silenceDay0call0msgSend6month;
	}
	public String getContinueSilenceDayOver150callActive6month() {
		return continueSilenceDayOver150callActive6month;
	}
	public void setContinueSilenceDayOver150callActive6month(String continueSilenceDayOver150callActive6month) {
		this.continueSilenceDayOver150callActive6month = continueSilenceDayOver150callActive6month;
	}

     

}