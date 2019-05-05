package com.shangyong.backend.entity.redis.fraud2_0;

import java.util.HashMap;

/**
 * 同盾报表  2.0 (运行商)
 * @author caisheng
 */
public class TongDunReport20Redis {

    /**
     * 通话次数: 近1月（从申请上月起）
     *      td_report_per_month --> call_count
     */
    private String carrierCallCount1m;

    /**
     * 夜间通话时长 23:30 ~ 05:59
     *      td_report_all_contact -->  call_time_late_night_6month
     * @return
     */
    private String carrierCallTimeNight6m;

    /**
     * 夜间通话次数 23:30 ~ 05:59
     *      td_report_all_contact --> call_count_late_night_6month
     */
    private String carrierCallCountNight6m;

    /**
     * 总呼出次数 6个月
     *      td_report_all_contact-->call_count_6month
     */
    private String carrierCallCount6m;

    /**
     * 通话次数:近3月（从申请上月起）
     *      td_report_per_month-->call_count
     */
    private String carrierCallCnt3mRatio;

    /**
     * 通话次数:近5月（从申请上月起）
     *      td_report_per_month-->call_count
     */
    private String carrierCallCnt6mRatio;

    /**
     * 呼入次数: 近5月（从申请上月起）
     *      td_report_per_month --> call_count_passive
     */
    private String carrierCallInCnt6mRatio;

    /**
     * 呼出次数: 近5月（从申请上月起）
     *      td_report_per_month --> call_count_active
     */
    private String carrierCallOutCnt6mRatio;

    /**
     * 近6月被叫号码数量
     * td_report_all_contact-->contact_count_passive_6month
     */
    private String carrierCallInCnt6m;

    /**
     * 近1月（从申请上月起）:呼入次数
     * td_report_per_month--> call_count_passive
     */
    private String carrierCallIn1mRatio;

    /**
     * 近1月（从申请上月起）:呼出次数
     * td_report_per_month-->call_count_active
     */
    private String carrierCallOutCnt1mRatio;

    /**
     * 呼入时长:近1月(从申请上月起)
     */
    private String carrierCallInTime1mRatio;

    /**
     * 呼入时长:近3月(从申请上月起)
     */
    private String carrierCallInTime3mRatio;

    /**
     * 呼入时长:近5月(从申请上月起)
     */
    private String carrierCallInTime5mRatio;

    /**
     * 近6月深夜主叫通话次数
     *      td_report_all_contact-->call_count_active_late_night_6month
     *
     */
    private String callCountActiveLateNight6Month;
    
    /**
     * 通话地区 去重计数
     */
    private String carrierContactAreaNum;

    /**
     * 近6月通话次数>=10的号码数量
     */
    private String contactCountCallCountOver106month;

    /**
     * 近6月互通号码数量
     **/
    private String contactCountMutual6month;

    /**
     * 近5月（从申请上月起）:最小呼入次数
     *  td_report_per_month-->call_count_passive
     */
    private String carrierMinCallInCnt6m;

    /**
     * 入网月数
     * td_report_mobile_info --> mobile_net_time
     */
    private String carrierPhoneUsedMonthNew;

    /**
     * 近6月无通话和发送短信静默天数
     * td_report_silence_stats --> silence_day_0call_0msg_send_6month
     */
    private String silenceDay0call0msgSend6month;

    /**
     * 近6月连续无通话和发送短信静默>=3天的次数
     * td_report_silence_stats --> continue_silence_day_over3_0call_0msg_send_6month
     */
    private String continueSilenceDayOver30call0msgSend6month;

    /**
     * 半年发送短信条数
     * td_report_per_month-->msg_count_send_mobile
     */
    private String carrierSmOutCnt6m;

    /**
     * 紧急联系人呼出次数
     * td_report_detail --> call_count_active_6month
     *  "table_type in ('紧急联系人明细1','紧急联系人明细2') GROUP BY a.risk_application_id;"
     */
    private String carrierIcePersonCallOutCnt6mNew;

    /**
     * 最频繁朋友圈通话时长占比
     *      td_report_contact_city--> call_time_6month(contact_area_seq_no=1)
     * 通话时长排名第一的 总时长
     */
    private String contactCityCallTime6month;

    /**
     * 最频繁朋友圈通话时长占比
     *      td_report_all_contact--> call_time_6month
     *      6个月通话总时长
     */
    private String allContactCallTime6month;
    
    /**
     * 拨出电话号码个数
     */
    private String carrierCallOutCnt6m;
    
    
    /**
     * 110通话情况：通话时长 被除数
     */
    private String carrier110AvgTime;
    
    /**
     *  carrierCallInCnt01VsTotal 被除数
     */
    private String carrierCallInCnt01VsTotal_callCountPassiveLateNight6month;
    
    /**
     * carrierCallInCnt01VsTotal 除数
     */
    private String carrierCallInCnt01VsTotal_callCountActive6month;
    

    public String getContactCityCallTime6month() {
        return contactCityCallTime6month;
    }

    public void setContactCityCallTime6month(String contactCityCallTime6month) {
        this.contactCityCallTime6month = contactCityCallTime6month;
    }

    public String getAllContactCallTime6month() {
        return allContactCallTime6month;
    }

    public void setAllContactCallTime6month(String allContactCallTime6month) {
        this.allContactCallTime6month = allContactCallTime6month;
    }

    public String getCarrierIcePersonCallOutCnt6mNew() {
        return carrierIcePersonCallOutCnt6mNew;
    }

    public void setCarrierIcePersonCallOutCnt6mNew(String carrierIcePersonCallOutCnt6mNew) {
        this.carrierIcePersonCallOutCnt6mNew = carrierIcePersonCallOutCnt6mNew;
    }

    public String getCarrierSmOutCnt6m() {
        return carrierSmOutCnt6m;
    }

    public void setCarrierSmOutCnt6m(String carrierSmOutCnt6m) {
        this.carrierSmOutCnt6m = carrierSmOutCnt6m;
    }

    public String getSilenceDay0call0msgSend6month() {
        return silenceDay0call0msgSend6month;
    }

    public void setSilenceDay0call0msgSend6month(String silenceDay0call0msgSend6month) {
        this.silenceDay0call0msgSend6month = silenceDay0call0msgSend6month;
    }

    public String getContinueSilenceDayOver30call0msgSend6month() {
        return continueSilenceDayOver30call0msgSend6month;
    }

    public void setContinueSilenceDayOver30call0msgSend6month(String continueSilenceDayOver30call0msgSend6month) {
        this.continueSilenceDayOver30call0msgSend6month = continueSilenceDayOver30call0msgSend6month;
    }

    public String getCarrierPhoneUsedMonthNew() {
        return carrierPhoneUsedMonthNew;
    }

    public void setCarrierPhoneUsedMonthNew(String carrierPhoneUsedMonthNew) {
        this.carrierPhoneUsedMonthNew = carrierPhoneUsedMonthNew;
    }

    public String getCarrierMinCallInCnt6m() {
        return carrierMinCallInCnt6m;
    }

    public void setCarrierMinCallInCnt6m(String carrierMinCallInCnt6m) {
        this.carrierMinCallInCnt6m = carrierMinCallInCnt6m;
    }

    public String getContactCountCallCountOver106month() {
        return contactCountCallCountOver106month;
    }

    public void setContactCountCallCountOver106month(String contactCountCallCountOver106month) {
        this.contactCountCallCountOver106month = contactCountCallCountOver106month;
    }

    public String getContactCountMutual6month() {
        return contactCountMutual6month;
    }

    public void setContactCountMutual6month(String contactCountMutual6month) {
        this.contactCountMutual6month = contactCountMutual6month;
    }

    public String getCarrierContactAreaNum() {
        return carrierContactAreaNum;
    }

    public void setCarrierContactAreaNum(String carrierContactAreaNum) {
        this.carrierContactAreaNum = carrierContactAreaNum;
    }

    public String getCallCountActiveLateNight6Month() {
        return callCountActiveLateNight6Month;
    }

    public void setCallCountActiveLateNight6Month(String callCountActiveLateNight6Month) {
        this.callCountActiveLateNight6Month = callCountActiveLateNight6Month;
    }

    public String getCarrierCallInTime1mRatio() {
        return carrierCallInTime1mRatio;
    }

    public void setCarrierCallInTime1mRatio(String carrierCallInTime1mRatio) {
        this.carrierCallInTime1mRatio = carrierCallInTime1mRatio;
    }

    public String getCarrierCallInTime3mRatio() {
        return carrierCallInTime3mRatio;
    }

    public void setCarrierCallInTime3mRatio(String carrierCallInTime3mRatio) {
        this.carrierCallInTime3mRatio = carrierCallInTime3mRatio;
    }

    public String getCarrierCallInTime5mRatio() {
        return carrierCallInTime5mRatio;
    }

    public void setCarrierCallInTime5mRatio(String carrierCallInTime5mRatio) {
        this.carrierCallInTime5mRatio = carrierCallInTime5mRatio;
    }

    public String getCarrierCallOutCnt6mRatio() {
        return carrierCallOutCnt6mRatio;
    }

    public void setCarrierCallOutCnt6mRatio(String carrierCallOutCnt6mRatio) {
        this.carrierCallOutCnt6mRatio = carrierCallOutCnt6mRatio;
    }

    public String getCarrierCallIn1mRatio() {
        return carrierCallIn1mRatio;
    }

    public void setCarrierCallIn1mRatio(String carrierCallIn1mRatio) {
        this.carrierCallIn1mRatio = carrierCallIn1mRatio;
    }

    public String getCarrierCallOutCnt1mRatio() {
        return carrierCallOutCnt1mRatio;
    }

    public void setCarrierCallOutCnt1mRatio(String carrierCallOutCnt1mRatio) {
        this.carrierCallOutCnt1mRatio = carrierCallOutCnt1mRatio;
    }

    public String getCarrierCallInCnt6m() {
        return carrierCallInCnt6m;
    }

    public void setCarrierCallInCnt6m(String carrierCallInCnt6m) {
        this.carrierCallInCnt6m = carrierCallInCnt6m;
    }

    public String getCarrierCallInCnt6mRatio() {
        return carrierCallInCnt6mRatio;
    }

    public void setCarrierCallInCnt6mRatio(String carrierCallInCnt6mRatio) {
        this.carrierCallInCnt6mRatio = carrierCallInCnt6mRatio;
    }

    public String getCarrierCallCnt3mRatio() {
        return carrierCallCnt3mRatio;
    }

    public void setCarrierCallCnt3mRatio(String carrierCallCnt3mRatio) {
        this.carrierCallCnt3mRatio = carrierCallCnt3mRatio;
    }

    public String getCarrierCallCnt6mRatio() {
        return carrierCallCnt6mRatio;
    }

    public void setCarrierCallCnt6mRatio(String carrierCallCnt6mRatio) {
        this.carrierCallCnt6mRatio = carrierCallCnt6mRatio;
    }

    public String getCarrierCallCount6m() {
        return carrierCallCount6m;
    }

    public void setCarrierCallCount6m(String carrierCallCount6m) {
        this.carrierCallCount6m = carrierCallCount6m;
    }

    public String getCarrierCallTimeNight6m() {
        return carrierCallTimeNight6m;
    }

    public void setCarrierCallTimeNight6m(String carrierCallTimeNight6m) {
        this.carrierCallTimeNight6m = carrierCallTimeNight6m;
    }

    public String getCarrierCallCountNight6m() {
        return carrierCallCountNight6m;
    }

    public void setCarrierCallCountNight6m(String carrierCallCountNight6m) {
        this.carrierCallCountNight6m = carrierCallCountNight6m;
    }

    public String getCarrierCallCount1m() {
        return carrierCallCount1m;
    }

    public void setCarrierCallCount1m(String carrierCallCount1m) {
        this.carrierCallCount1m = carrierCallCount1m;
    }

    public String getCarrierCallOutCnt6m() {
		return carrierCallOutCnt6m;
	}

	public void setCarrierCallOutCnt6m(String carrierCallOutCnt6m) {
		this.carrierCallOutCnt6m = carrierCallOutCnt6m;
	}

    public String getCarrier110AvgTime() {
		return carrier110AvgTime;
	}

	public void setCarrier110AvgTime(String carrier110AvgTime) {
		this.carrier110AvgTime = carrier110AvgTime;
	}

	public String getCarrierCallInCnt01VsTotal_callCountPassiveLateNight6month() {
		return carrierCallInCnt01VsTotal_callCountPassiveLateNight6month;
	}

	public void setCarrierCallInCnt01VsTotal_callCountPassiveLateNight6month(
			String carrierCallInCnt01VsTotal_callCountPassiveLateNight6month) {
		this.carrierCallInCnt01VsTotal_callCountPassiveLateNight6month = carrierCallInCnt01VsTotal_callCountPassiveLateNight6month;
	}

	public String getCarrierCallInCnt01VsTotal_callCountActive6month() {
		return carrierCallInCnt01VsTotal_callCountActive6month;
	}

	public void setCarrierCallInCnt01VsTotal_callCountActive6month(String carrierCallInCnt01VsTotal_callCountActive6month) {
		this.carrierCallInCnt01VsTotal_callCountActive6month = carrierCallInCnt01VsTotal_callCountActive6month;
	}

	public HashMap<String, String> toMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("carrierCallCount1m",null == this.carrierCallCount1m ? "unknow" : this.carrierCallCount1m);
        map.put("carrierCallTimeNight6m",null == this.carrierCallTimeNight6m ? "unknow" : this.carrierCallTimeNight6m);
        map.put("carrierCallCountNight6m",null == this.carrierCallCountNight6m ? "unknow" : this.carrierCallCountNight6m);
        map.put("carrierCallCount6m",null == this.carrierCallCount6m ? "unknow" : this.carrierCallCount6m);
        map.put("carrierCallCnt3mRatio",null == this.carrierCallCnt3mRatio ? "unknow" : this.carrierCallCnt3mRatio);
        map.put("carrierCallCnt6mRatio",null == this.carrierCallCnt6mRatio ? "unknow" : this.carrierCallCnt6mRatio);
        map.put("carrierCallInCnt6mRatio",null == this.carrierCallInCnt6mRatio ? "unknow" : this.carrierCallInCnt6mRatio);
        map.put("carrierCallOutCnt6mRatio",null == this.carrierCallOutCnt6mRatio ? "unknow" : this.carrierCallOutCnt6mRatio);
        map.put("carrierCallInCnt6m",null == this.carrierCallInCnt6m ? "unknow" : this.carrierCallInCnt6m);
        map.put("carrierCallIn1mRatio",null == this.carrierCallIn1mRatio ? "unknow" : this.carrierCallIn1mRatio);
        map.put("carrierCallOutCnt1mRatio",null == this.carrierCallOutCnt1mRatio ? "unknow" : this.carrierCallOutCnt1mRatio);
        map.put("carrierCallInTime1mRatio",null == this.carrierCallInTime1mRatio ? "unknow" : this.carrierCallInTime1mRatio);
        map.put("carrierCallInTime3mRatio",null == this.carrierCallInTime3mRatio ? "unknow" : this.carrierCallInTime3mRatio);
        map.put("carrierCallInTime5mRatio",null == this.carrierCallInTime5mRatio ? "unknow" : this.carrierCallInTime5mRatio);
        map.put("callCountActiveLateNight6Month",null == this.callCountActiveLateNight6Month ? "unknow" : this.callCountActiveLateNight6Month);
        map.put("carrierContactAreaNum",null == this.carrierContactAreaNum ? "unknow" : this.carrierContactAreaNum);
        map.put("contactCountCallCountOver106month",null == this.contactCountCallCountOver106month ? "unknow" : this.contactCountCallCountOver106month);
        map.put("contactCountMutual6month",null == this.contactCountMutual6month ? "unknow" : this.contactCountMutual6month);
        map.put("carrierMinCallInCnt6m",null == this.carrierMinCallInCnt6m ? "unknow" : this.carrierMinCallInCnt6m);
        map.put("carrierPhoneUsedMonthNew",null == this.carrierPhoneUsedMonthNew ? "unknow" : this.carrierPhoneUsedMonthNew);
        map.put("silenceDay0call0msgSend6month",null == this.silenceDay0call0msgSend6month ? "unknow" : this.silenceDay0call0msgSend6month);
        map.put("continueSilenceDayOver30call0msgSend6month",null == this.continueSilenceDayOver30call0msgSend6month ? "unknow" : this.continueSilenceDayOver30call0msgSend6month);
        map.put("carrierSmOutCnt6m",null == this.carrierSmOutCnt6m ? "unknow" : this.carrierSmOutCnt6m);
        map.put("carrierIcePersonCallOutCnt6mNew",null == this.carrierIcePersonCallOutCnt6mNew ? "unknow" : this.carrierIcePersonCallOutCnt6mNew);
        map.put("contactCityCallTime6month",null == this.contactCityCallTime6month ? "unknow" : this.contactCityCallTime6month);
        map.put("allContactCallTime6month",null == this.allContactCallTime6month ? "unknow" : this.allContactCallTime6month);
        map.put("carrierCallOutCnt6m",null == this.carrierCallOutCnt6m ? "unknow" : this.carrierCallOutCnt6m);
        map.put("carrier110AvgTime",null == this.carrier110AvgTime ? "unknow" : this.carrier110AvgTime);
        map.put("carrierCallInCnt01VsTotal_callCountPassiveLateNight6month",null == this.carrierCallInCnt01VsTotal_callCountPassiveLateNight6month ? "unknow" : this.carrierCallInCnt01VsTotal_callCountPassiveLateNight6month);
        map.put("carrierCallInCnt01VsTotal_callCountActive6month",null == this.carrierCallInCnt01VsTotal_callCountActive6month ? "unknow" : this.carrierCallInCnt01VsTotal_callCountActive6month);
        return map;
    }

	
}