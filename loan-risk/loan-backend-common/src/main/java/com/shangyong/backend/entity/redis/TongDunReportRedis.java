package com.shangyong.backend.entity.redis;

import java.util.HashMap;

/**
 * 同盾报表 1.8
 * @author caisheng
 */
public class TongDunReportRedis {

    /**
     * 通话时长: 近1月（从申请上月起）
     *      td_report_per_month --> call_time
     */
    private String carrierCallTime1m;

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
     * 呼入次数:近3月（从申请上月起）
     *      td_report_per_month --> call_count_passive
     */
    private String carrierCalInCnt3mRatio;

    /**
     * 呼出次数:近3月（从申请上月起）
     *      td_report_per_month-->call_count_active
     */
    private String carrierCallOutCnt3mRatio;

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
     * 近6月主叫通话次数
     *      td_report_all_contact-->call_count_active_6month
     */
    private String callCountActive6Month;

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
    private String carrierPhoneUsedMonth;
    
    /**
     * 入网月数  lz 1.1 与后来版本不一样
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
    private String carrierIcePersonCallOutCnt6m;
    
    
    /**
     * 紧急联系人呼出次数  lz 与1.1区分开
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
    
    /**
     * 紧急联系人近6个月呼出时间
     */
    private String carrierIcePersonCallOutTime6m;
    
    /**
     * 紧急联系人近1个月通话时间
     */
    private String carrierIcePersonCallTime1m;
    
	/**
	 * 静默天数
	 */
	private String carrierSilentAvgDays;
	
	/**
     * 静默次数
     */
    private String carrierSilentAvgTimes;
    
	/**
	 * 联系人通话总次数
	 */
	private String carrierContactCallCntRatio;
	
	/**
	 * 夜间通话时长
	 */
	private String carrierCallTimeNight;
	
	/**
	 * 互通电话号码个数
	 */
	private String carrierCallInterflowNum;
	
	/**
	 * 夜间通话次数
	 */
	private String carrierCallCountNight;
	
	/**
	 * 近5月（从申请上月起），最大月话费金额
	 */
	private String carrierMaxPhoneBill6m;
	
	/**
	 * 话费金额:近1月（从申请上月起）
	 */
	private String carrierPhoneBill1mRatio;
	
	/**
	 * 话费金额:近3月（从申请上月起）
	 */
	private String carrierPhoneBill3mRatio;
	
	/**
	 * 话费金额:近5月（从申请上月起）
	 */
	private String carrierPhoneBill6mRatio;
	
    /**
     * 最频繁朋友圈通话时间占比
     */
    private String carrierMaxContactAreaRatio;
    
	/**
	 * 联系人黑名单城市通话次数
	 */
	private String carrierContactAreaBlackCityRatio;
	
	/**
	 * 近1月（从申请上月起）呼出时间时长
	 */
	private String carrierTotalOut1m;
	
	/**
	 * 短信数:近3月（从申请上月起）
	 */
	private String carrierSmsCnt3mRatio;
	
	/**
	 * 短信数:近5月（从申请上月起）
	 */
	private String carrierSmsCnt6mRatio;
	
	/**
	 * 近5月（从申请上月起），最大平均呼出时长
	 */
	private String carrierMaxCallOutAvgTime6m;
	
	/**
     * 半年发送短信条数
     */
	private String carrierSmsOutCnt6m;
	
	/**
	 * 呼入次数:近3月（从申请上月起）
	 */
	private String carrierCallInCnt3mRatio;
	
	/**
	 * 近5月（从申请上月起）:呼出次数
	 */
	private String carrierCallOut6mRatio;

	/**
	 * 近5月（从申请上月起），最小月呼入时长
	 */
	private String carrierMinCallInTime6m;
	
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

    public String getCarrierIcePersonCallOutCnt6m() {
        return carrierIcePersonCallOutCnt6m;
    }

    public void setCarrierIcePersonCallOutCnt6m(String carrierIcePersonCallOutCnt6m) {
        this.carrierIcePersonCallOutCnt6m = carrierIcePersonCallOutCnt6m;
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

    public String getCarrierPhoneUsedMonth() {
        return carrierPhoneUsedMonth;
    }

    public void setCarrierPhoneUsedMonth(String carrierPhoneUsedMonth) {
        this.carrierPhoneUsedMonth = carrierPhoneUsedMonth;
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

    public String getCallCountActive6Month() {
        return callCountActive6Month;
    }

    public void setCallCountActive6Month(String callCountActive6Month) {
        this.callCountActive6Month = callCountActive6Month;
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

    public String getCarrierCallOutCnt3mRatio() {
        return carrierCallOutCnt3mRatio;
    }

    public void setCarrierCallOutCnt3mRatio(String carrierCallOutCnt3mRatio) {
        this.carrierCallOutCnt3mRatio = carrierCallOutCnt3mRatio;
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

    public String getCarrierCalInCnt3mRatio() {
        return carrierCalInCnt3mRatio;
    }

    public void setCarrierCalInCnt3mRatio(String carrierCalInCnt3mRatio) {
        this.carrierCalInCnt3mRatio = carrierCalInCnt3mRatio;
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

    public String getCarrierCallTime1m() {
        return carrierCallTime1m;
    }

    public void setCarrierCallTime1m(String carrierCallTime1m) {
        this.carrierCallTime1m = carrierCallTime1m;
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

	public String getCarrierIcePersonCallOutTime6m() {
		return carrierIcePersonCallOutTime6m;
	}

	public void setCarrierIcePersonCallOutTime6m(String carrierIcePersonCallOutTime6m) {
		this.carrierIcePersonCallOutTime6m = carrierIcePersonCallOutTime6m;
	}

	public String getCarrierIcePersonCallTime1m() {
		return carrierIcePersonCallTime1m;
	}

	public void setCarrierIcePersonCallTime1m(String carrierIcePersonCallTime1m) {
		this.carrierIcePersonCallTime1m = carrierIcePersonCallTime1m;
	}

	public String getCarrierSilentAvgDays() {
		return carrierSilentAvgDays;
	}

	public void setCarrierSilentAvgDays(String carrierSilentAvgDays) {
		this.carrierSilentAvgDays = carrierSilentAvgDays;
	}
	
	public String getCarrierSilentAvgTimes() {
		return carrierSilentAvgTimes;
	}

	public void setCarrierSilentAvgTimes(String carrierSilentAvgTimes) {
		this.carrierSilentAvgTimes = carrierSilentAvgTimes;
	}

	public String getCarrierContactCallCntRatio() {
		return carrierContactCallCntRatio;
	}

	public void setCarrierContactCallCntRatio(String carrierContactCallCntRatio) {
		this.carrierContactCallCntRatio = carrierContactCallCntRatio;
	}
	
	public String getCarrierCallTimeNight() {
		return carrierCallTimeNight;
	}

	public void setCarrierCallTimeNight(String carrierCallTimeNight) {
		this.carrierCallTimeNight = carrierCallTimeNight;
	}

	public String getCarrierCallInterflowNum() {
		return carrierCallInterflowNum;
	}

	public void setCarrierCallInterflowNum(String carrierCallInterflowNum) {
		this.carrierCallInterflowNum = carrierCallInterflowNum;
	}

	public String getCarrierCallCountNight() {
		return carrierCallCountNight;
	}

	public void setCarrierCallCountNight(String carrierCallCountNight) {
		this.carrierCallCountNight = carrierCallCountNight;
	}

	public String getCarrierMaxPhoneBill6m() {
		return carrierMaxPhoneBill6m;
	}

	public void setCarrierMaxPhoneBill6m(String carrierMaxPhoneBill6m) {
		this.carrierMaxPhoneBill6m = carrierMaxPhoneBill6m;
	}

	public String getCarrierPhoneBill1mRatio() {
		return carrierPhoneBill1mRatio;
	}

	public void setCarrierPhoneBill1mRatio(String carrierPhoneBill1mRatio) {
		this.carrierPhoneBill1mRatio = carrierPhoneBill1mRatio;
	}
	
	public String getCarrierPhoneBill3mRatio() {
		return carrierPhoneBill3mRatio;
	}

	public void setCarrierPhoneBill3mRatio(String carrierPhoneBill3mRatio) {
		this.carrierPhoneBill3mRatio = carrierPhoneBill3mRatio;
	}

	public String getCarrierPhoneBill6mRatio() {
		return carrierPhoneBill6mRatio;
	}

	public void setCarrierPhoneBill6mRatio(String carrierPhoneBill6mRatio) {
		this.carrierPhoneBill6mRatio = carrierPhoneBill6mRatio;
	}

	public String getCarrierMaxContactAreaRatio() {
		return carrierMaxContactAreaRatio;
	}

	public void setCarrierMaxContactAreaRatio(String carrierMaxContactAreaRatio) {
		this.carrierMaxContactAreaRatio = carrierMaxContactAreaRatio;
	}

	public String getCarrierContactAreaBlackCityRatio() {
		return carrierContactAreaBlackCityRatio;
	}

	public void setCarrierContactAreaBlackCityRatio(String carrierContactAreaBlackCityRatio) {
		this.carrierContactAreaBlackCityRatio = carrierContactAreaBlackCityRatio;
	}

	public String getCarrierTotalOut1m() {
		return carrierTotalOut1m;
	}

	public void setCarrierTotalOut1m(String carrierTotalOut1m) {
		this.carrierTotalOut1m = carrierTotalOut1m;
	}

	public String getCarrierSmsCnt3mRatio() {
		return carrierSmsCnt3mRatio;
	}

	public void setCarrierSmsCnt3mRatio(String carrierSmsCnt3mRatio) {
		this.carrierSmsCnt3mRatio = carrierSmsCnt3mRatio;
	}

	public String getCarrierSmsCnt6mRatio() {
		return carrierSmsCnt6mRatio;
	}

	public void setCarrierSmsCnt6mRatio(String carrierSmsCnt6mRatio) {
		this.carrierSmsCnt6mRatio = carrierSmsCnt6mRatio;
	}

	public String getCarrierMaxCallOutAvgTime6m() {
		return carrierMaxCallOutAvgTime6m;
	}

	public void setCarrierMaxCallOutAvgTime6m(String carrierMaxCallOutAvgTime6m) {
		this.carrierMaxCallOutAvgTime6m = carrierMaxCallOutAvgTime6m;
	}

	public String getCarrierSmsOutCnt6m() {
		return carrierSmsOutCnt6m;
	}

	public void setCarrierSmsOutCnt6m(String carrierSmsOutCnt6m) {
		this.carrierSmsOutCnt6m = carrierSmsOutCnt6m;
	}

	public String getCarrierCallInCnt3mRatio() {
		return carrierCallInCnt3mRatio;
	}

	public void setCarrierCallInCnt3mRatio(String carrierCallInCnt3mRatio) {
		this.carrierCallInCnt3mRatio = carrierCallInCnt3mRatio;
	}

	public String getCarrierCallOut6mRatio() {
		return carrierCallOut6mRatio;
	}

	public void setCarrierCallOut6mRatio(String carrierCallOut6mRatio) {
		this.carrierCallOut6mRatio = carrierCallOut6mRatio;
	}

	public String getCarrierMinCallInTime6m() {
		return carrierMinCallInTime6m;
	}

	public void setCarrierMinCallInTime6m(String carrierMinCallInTime6m) {
		this.carrierMinCallInTime6m = carrierMinCallInTime6m;
	}

	public String getCarrierPhoneUsedMonthNew() {
		return carrierPhoneUsedMonthNew;
	}

	public void setCarrierPhoneUsedMonthNew(String carrierPhoneUsedMonthNew) {
		this.carrierPhoneUsedMonthNew = carrierPhoneUsedMonthNew;
	}

	public String getCarrierIcePersonCallOutCnt6mNew() {
		return carrierIcePersonCallOutCnt6mNew;
	}

	public void setCarrierIcePersonCallOutCnt6mNew(String carrierIcePersonCallOutCnt6mNew) {
		this.carrierIcePersonCallOutCnt6mNew = carrierIcePersonCallOutCnt6mNew;
	}

	public HashMap<String, String> toMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("carrierCallTime1m",null == this.carrierCallTime1m ? "unknow" : this.carrierCallTime1m);
        map.put("carrierCallCount1m",null == this.carrierCallCount1m ? "unknow" : this.carrierCallCount1m);
        map.put("carrierCallTimeNight6m",null == this.carrierCallTimeNight6m ? "unknow" : this.carrierCallTimeNight6m);
        map.put("carrierCallCountNight6m",null == this.carrierCallCountNight6m ? "unknow" : this.carrierCallCountNight6m);
        map.put("carrierCallCount6m",null == this.carrierCallCount6m ? "unknow" : this.carrierCallCount6m);
        map.put("carrierCallCnt3mRatio",null == this.carrierCallCnt3mRatio ? "unknow" : this.carrierCallCnt3mRatio);
        map.put("carrierCallCnt6mRatio",null == this.carrierCallCnt6mRatio ? "unknow" : this.carrierCallCnt6mRatio);
        map.put("carrierCalInCnt3mRatio",null == this.carrierCalInCnt3mRatio ? "unknow" : this.carrierCalInCnt3mRatio);
        map.put("carrierCallOutCnt3mRatio",null == this.carrierCallOutCnt3mRatio ? "unknow" : this.carrierCallOutCnt3mRatio);
        map.put("carrierCallInCnt6mRatio",null == this.carrierCallInCnt6mRatio ? "unknow" : this.carrierCallInCnt6mRatio);
        map.put("carrierCallOutCnt6mRatio",null == this.carrierCallOutCnt6mRatio ? "unknow" : this.carrierCallOutCnt6mRatio);
        map.put("carrierCallInCnt6m",null == this.carrierCallInCnt6m ? "unknow" : this.carrierCallInCnt6m);
        map.put("carrierCallIn1mRatio",null == this.carrierCallIn1mRatio ? "unknow" : this.carrierCallIn1mRatio);
        map.put("carrierCallOutCnt1mRatio",null == this.carrierCallOutCnt1mRatio ? "unknow" : this.carrierCallOutCnt1mRatio);
        map.put("carrierCallInTime1mRatio",null == this.carrierCallInTime1mRatio ? "unknow" : this.carrierCallInTime1mRatio);
        map.put("carrierCallInTime3mRatio",null == this.carrierCallInTime3mRatio ? "unknow" : this.carrierCallInTime3mRatio);
        map.put("carrierCallInTime5mRatio",null == this.carrierCallInTime5mRatio ? "unknow" : this.carrierCallInTime5mRatio);
        map.put("callCountActiveLateNight6Month",null == this.callCountActiveLateNight6Month ? "unknow" : this.callCountActiveLateNight6Month);
        map.put("callCountActive6Month",null == this.callCountActive6Month ? "unknow" : this.callCountActive6Month);
        map.put("carrierContactAreaNum",null == this.carrierContactAreaNum ? "unknow" : this.carrierContactAreaNum);
        map.put("contactCountCallCountOver106month",null == this.contactCountCallCountOver106month ? "unknow" : this.contactCountCallCountOver106month);
        map.put("contactCountMutual6month",null == this.contactCountMutual6month ? "unknow" : this.contactCountMutual6month);
        map.put("carrierMinCallInCnt6m",null == this.carrierMinCallInCnt6m ? "unknow" : this.carrierMinCallInCnt6m);
        map.put("carrierPhoneUsedMonth",null == this.carrierPhoneUsedMonth ? "unknow" : this.carrierPhoneUsedMonth);
        map.put("silenceDay0call0msgSend6month",null == this.silenceDay0call0msgSend6month ? "unknow" : this.silenceDay0call0msgSend6month);
        map.put("continueSilenceDayOver30call0msgSend6month",null == this.continueSilenceDayOver30call0msgSend6month ? "unknow" : this.continueSilenceDayOver30call0msgSend6month);
        map.put("carrierSmOutCnt6m",null == this.carrierSmOutCnt6m ? "unknow" : this.carrierSmOutCnt6m);
        map.put("carrierIcePersonCallOutCnt6m",null == this.carrierIcePersonCallOutCnt6m ? "unknow" : this.carrierIcePersonCallOutCnt6m);
        map.put("contactCityCallTime6month",null == this.contactCityCallTime6month ? "unknow" : this.contactCityCallTime6month);
        map.put("allContactCallTime6month",null == this.allContactCallTime6month ? "unknow" : this.allContactCallTime6month);
        map.put("carrierCallOutCnt6m",null == this.carrierCallOutCnt6m ? "unknow" : this.carrierCallOutCnt6m);
        map.put("carrier110AvgTime",null == this.carrier110AvgTime ? "unknow" : this.carrier110AvgTime);
        map.put("carrierCallInCnt01VsTotal_callCountPassiveLateNight6month",null == this.carrierCallInCnt01VsTotal_callCountPassiveLateNight6month ? "unknow" : this.carrierCallInCnt01VsTotal_callCountPassiveLateNight6month);
        map.put("carrierCallInCnt01VsTotal_callCountActive6month",null == this.carrierCallInCnt01VsTotal_callCountActive6month ? "unknow" : this.carrierCallInCnt01VsTotal_callCountActive6month);
        map.put("carrierIcePersonCallOutTime6m",null == this.carrierIcePersonCallOutTime6m ? "unknow" : this.carrierIcePersonCallOutTime6m);
        map.put("carrierIcePersonCallTime1m",null == this.carrierIcePersonCallTime1m ? "unknow" : this.carrierIcePersonCallTime1m);
        map.put("carrierSilentAvgDays",null == this.carrierSilentAvgDays ? "unknow" : this.carrierSilentAvgDays);
        map.put("carrierSilentAvgTimes",null == this.carrierSilentAvgTimes ? "unknow" : this.carrierSilentAvgTimes);
        map.put("carrierContactCallCntRatio",null == this.carrierContactCallCntRatio ? "unknow" : this.carrierContactCallCntRatio);
        map.put("carrierCallTimeNight",null == this.carrierCallTimeNight ? "unknow" : this.carrierCallTimeNight);
        map.put("carrierCallInterflowNum",null == this.carrierCallInterflowNum ? "unknow" : this.carrierCallInterflowNum);
        map.put("carrierCallCountNight",null == this.carrierCallCountNight ? "unknow" : this.carrierCallCountNight);
        map.put("carrierMaxPhoneBill6m",null == this.carrierMaxPhoneBill6m ? "unknow" : this.carrierMaxPhoneBill6m);
        map.put("carrierPhoneBill1mRatio",null == this.carrierPhoneBill1mRatio ? "unknow" : this.carrierPhoneBill1mRatio);
        map.put("carrierPhoneBill3mRatio",null == this.carrierPhoneBill3mRatio ? "unknow" : this.carrierPhoneBill3mRatio);
        map.put("carrierPhoneBill6mRatio",null == this.carrierPhoneBill6mRatio ? "unknow" : this.carrierPhoneBill6mRatio);
        map.put("carrierMaxContactAreaRatio",null == this.carrierMaxContactAreaRatio ? "unknow" : this.carrierMaxContactAreaRatio);
        map.put("carrierContactAreaBlackCityRatio",null == this.carrierContactAreaBlackCityRatio ? "unknow" : this.carrierContactAreaBlackCityRatio);
        map.put("carrierTotalOut1m",null == this.carrierTotalOut1m ? "unknow" : this.carrierTotalOut1m);
        map.put("carrierSmsCnt3mRatio",null == this.carrierSmsCnt3mRatio ? "unknow" : this.carrierSmsCnt3mRatio);
        map.put("carrierSmsCnt6mRatio",null == this.carrierSmsCnt6mRatio ? "unknow" : this.carrierSmsCnt6mRatio);
        map.put("carrierMaxCallOutAvgTime6m",null == this.carrierMaxCallOutAvgTime6m ? "unknow" : this.carrierMaxCallOutAvgTime6m);
        map.put("carrierSmsOutCnt6m",null == this.carrierSmsOutCnt6m ? "unknow" : this.carrierSmsOutCnt6m);
        map.put("carrierCallInCnt3mRatio",null == this.carrierCallInCnt3mRatio ? "unknow" : this.carrierCallInCnt3mRatio);
        map.put("carrierCallOut6mRatio",null == this.carrierCallOut6mRatio ? "unknow" : this.carrierCallOut6mRatio);
        map.put("carrierMinCallInTime6m",null == this.carrierMinCallInTime6m ? "unknow" : this.carrierMinCallInTime6m);
        map.put("carrierPhoneUsedMonthNew",null == this.carrierPhoneUsedMonthNew ? "unknow" : this.carrierPhoneUsedMonthNew);
        map.put("carrierIcePersonCallOutCnt6mNew",null == this.carrierIcePersonCallOutCnt6mNew ? "unknow" : this.carrierIcePersonCallOutCnt6mNew);
        return map;
    }

	
}