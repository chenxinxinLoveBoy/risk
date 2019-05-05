package com.shangyong.backend.entity.redis;

/**
 * @Description  白骑士的报告数据的redis库
 * @Author  Corey
 * @Date  2018/4/11 16:23
 */

public class BqsReportMergeRedis {

    /**
     * 静默天数（分子）
     */
    private String carrierSilentAvgDays;

    /**
     * 静默次数（分母）
     */
    private String carrierSilentAvgTimes;

    /**
     * 入网月数
     */
    private String carrierPhoneUsedMonth;

    /**
     * 半年发送短信条数
     */
    private String carrierSmsOutCnt6m;

    /**
     * 最频繁朋友圈通话时间占比
     */
    private String carrierMaxContactAreaRatio;

    /**
     * 静默天数（分子）
     */
    private String carrierSilentDays;

    /**
     * 总天数（分母）
     */
    private String carrierSilentCntDays;

    /**
     *   08:30-11:59呼入时间(分子)
     */
    private String carrierCallInAvgTimeM;

    /**
     * 08:30-11:59呼入次数（分母）
     */
    private String carrierCallInAvgTimeD;

    /**
     * 近5月（从申请上月起），最大月话费金额
     */
    private String carrierMaxPhoneBill6m;

    /**
     * 联系人黑名单城市通话次数(分子)
     */
    private String carrierContactAreaBlackCityCallCnt;

    /**
     * 联系人通话总次数（分母）
     */
    private String carrierContactCallCnt;

    /**
     * 夜间呼入时间(分子)
     */
    private String carrierCallInAvgTimeNight;

    /**
     * 夜间呼入次数（分母）
     */
    private String carrierCallInAvgCntNight;

    /**
     * 近1月（从申请上月起）:通话时间(分子)
     */
    private String carrierCallTimeTotal1m;

    /**
     * 近1月（从申请上月起）:呼出时间（分母）
     */
    private String carrierCallTimeTotalOut1m;

    /**
     * 夜间通话时间（分子）
     */
    private String carrierCallTotalCntTimeNight;

    /**
     * 夜间通话次数（分母）
     */
    private String carrierCallTotalCntNight;

    /**
     * 夜间呼入时间
     */
    private String carrierCallInTimeNight;

    /**
     * 夜间通话时间
     */
    private String carrierCallTimeNight;

    /**
     * 紧急联系人呼出次数
     */
    private String carrierIcePersonCallOutCnt6m;

    /**
     * 近1月（从申请上月起）:呼入次数(分子)
     */
    private String carrierCallInCnt1m;

    /**
     * 近1月（从申请上月起）:呼出次数（分母）
     */
    private String carrierCallOutCnt1m;

    /**
     * 近3天呼出时间
     */
    private String carrierCallOutTime3d;

    /**
     * 紧急联系人近6个月呼出时间
     */
    private String carrierIcePersonCallOutTime6m;

    /**
     * 话费金额:近1月（从申请上月起）（分子）
     */
    private String carrierPhoneBill1mRatio;

    /**
     * 话费金额:近3月（从申请上月起）（分母）
     */
    private String carrierPhoneBill3mRatio;

    /**
     * 号码有通话时长
     */
    private String carrierPhoneRealUsedMonth;

    /**
     * 短信数:近3月（从申请上月起）（分子）
     */
    private String carrierSmsCnt3m;

    /**
     * 短信数:近5月（从申请上月起）（分母）
     */
    private String carrierSmsCnt6m;


    /**
     * 近5月（从申请上月起），最大平均呼出时间
     */
    private String carrierMaxCallOutAvgTime6m;

    /**
     * 互通电话号码个数
     */
    private String carrierCallInterflowNum;

    /**
     * 夜间通话次数
     */
    private String carrierCallCntNight;

    /**
     * 话费金额: 近3月（从申请上月起）（分子）
     */
    private String carrierPhoneBill3m;

    /**
     * 话费金额: 近5月（从申请上月起）（分母）
     */
    private String carrierPhoneBill6m;

    /**
     * 06:00-08:29,呼出时间（分子）
     */
    private String carrierCallOutAvgTime02;

    /**
     * 06:00-08:29,呼出次数（分母）
     */
    private String carrierCallOutAvgTimes02;

    /**
     * 紧急联系人近1个月通话时间
     */
    private String carrierIcePersonCallTime1m;

    /**
     * 呼入次数:近3月（从申请上月起）（分子）
     */
    private String carrierCallInCnt3mRatio;

    /**
     * 呼入次数:近5月（从申请上月起）（分母）
     */
    private String carrierCallInCnt6mRatio;

    /**
     * 近5月（从申请上月起）:呼出次数（分子）
     */
    private String carrierCallOutCntCallOutTimes;

    /**
     * 近5月（从申请上月起）:通话次数（分母）
     */
    private String carrierCallOutCntCallTimes;

    /**
     * 呼入次数: 近1月（从申请上月起）（分子）
     */
    private String carrierCallInCnt1mCallOutTimes;

    /**
     * 呼入次数: 近5月（从申请上月起）（分母）
     */
    private String carrierCallInCnt6mCallOutTimes;

    /**
     * 近5月（从申请上月起），最小月呼入时间
     */
    private String carrierMinCallInTime6m;

    /**
     * 手机号星网模型大小（180天之内）白骑士特有
     */
    private String bqsStarnetCnt6m;
    
    /**
     * 节假日出行天数
     */
    private String bqsGoOutAreaCntJjr;
    
    /**
     * 
     */
    private String bqsMulti3m;
    
    /**
     * 
     */
    private String bqsPayCnt;

    
    /**
     * 23:30 ~ 05:59通话次数(分子)
     */
    private String carrierCallCnt01;
    
    /**
     * 总通话次数(分母)
     */
    private String carrierCallCntTotal;
    
    /**
     * 通话次数:近1月（从申请上月起）(分子)
     */
    private String carrierCallCnt1mRatio;
    
    /**
     * 通话次数:近3月（从申请上月起）(分母)
     */
    private String carrierCallCnt3mRatio;
    
    /**
     * 14:00 ~ 17:59呼入时长(分子)
     */
    private String carrierCallInAvgTime05CallTime;
    
    /**
     * 14:00 ~ 17:59呼入次数(分母)
     */
    private String carrierCallInAvgTime05CallCount;
    
    /**
     * 三天 求和
     */
    private String carrierCallInCnt3d;
    
    /**
     * 拨入电话号码个数
     */
    private String carrierCallInCnt6m;
    
    /**
     * 七天 通话次数求和
     */
    private String carrierCallInCnt7d;
    
    /**
     * 近3月（从申请上月起）:呼出次数(分母)
     */
    private String carrierCallInOutCnt3mRatioCallCountHc;   
    
    /**
     * period_type='23:30 ~ 05:59'
     */
    private String carrierCallInTime01CallTime;

    /**
     * 呼入时长:近1月（从申请上月起）
     */
    private String carrierCallInTime1mRatioCallTime;
    
    /**
     * 呼入时长:近3月（从申请上月起）
     */
    private String carrierCallInTime3mRatioCallTime;
    
    /**
     * 呼入时长:近5月（从申请上月起）
     */
    private String carrierCallInTime6mRatioCallTime;
    
    /**
     * 23:30 ~ 05:59呼出次数(分子)
     */
    private String carrierCallOutCnt01CallCount;
    
    /**
     * 总呼出次数
     */
    private String carrierCallOutCnt01CallCountAll;
    
    /**
     * inspectionItems='夜间通话次数(00:00 ~ 06:00)'夜间呼出次数
     */
    private String carrierCallOutCntNight;
    
    /**
     * 夜间通话(00:00 ~ 06:00)呼出时长
     */
    private String carrierCallOutTimeRatioNight;
    
    /**
     * (备注)去重计数
     */
    private String carrierContactAreaNum;
    
    /**
     * 朋友圈活跃度
     */
    private String carrierContactAvgCntHy;
    
    /**
     * 朋友圈大小
     */
    private String carrierContactNum;
    
    /**
     * 近5月（从申请上月起）:最小呼入次数
     */
    private String carrierMinCallInCnt6m;
    
    /**
     * period_type='23:30 ~ 05:59'
     */
    private String carrierSmsCnt01;
    
    /**
     * 拨出电话号码个数
     */
    private String carrierCallOutCnt6m;
    
    
    /**
     * 110通话情况：通话时长 被除数
     */
    private String carrier110AvgTimeEvidence;
    
    /**
     * 110通话情况：通话次数 除数
     */
    private String carrier110AvgTimeResult;
    
    /**
     * 话费单次缴存最大金额
     */
    private String bqsMaxPay;
    
    /**
     *  carrierCallInCnt01VsTotal 被除数
     */
    private String carrierCallInCnt01VsTotalSumTerminatingCallCount;
    
    /**
     * carrierCallInCnt01VsTotal 除数
     */
    private String carrierCallInCnt01VsTotalSumOriginatingCallCount;

    public String getBqsGoOutAreaCntJjr() {
		return bqsGoOutAreaCntJjr;
	}

	public void setBqsGoOutAreaCntJjr(String bqsGoOutAreaCntJjr) {
		this.bqsGoOutAreaCntJjr = bqsGoOutAreaCntJjr;
	}

	public String getBqsMulti3m() {
		return bqsMulti3m;
	}

	public void setBqsMulti3m(String bqsMulti3m) {
		this.bqsMulti3m = bqsMulti3m;
	}

	public String getBqsPayCnt() {
		return bqsPayCnt;
	}

	public void setBqsPayCnt(String bqsPayCnt) {
		this.bqsPayCnt = bqsPayCnt;
	}

	public String getCarrierCallCnt01() {
		return carrierCallCnt01;
	}

	public void setCarrierCallCnt01(String carrierCallCnt01) {
		this.carrierCallCnt01 = carrierCallCnt01;
	}

	public String getCarrierCallCntTotal() {
		return carrierCallCntTotal;
	}

	public void setCarrierCallCntTotal(String carrierCallCntTotal) {
		this.carrierCallCntTotal = carrierCallCntTotal;
	}

	public String getCarrierCallCnt1mRatio() {
		return carrierCallCnt1mRatio;
	}

	public void setCarrierCallCnt1mRatio(String carrierCallCnt1mRatio) {
		this.carrierCallCnt1mRatio = carrierCallCnt1mRatio;
	}

	public String getCarrierCallCnt3mRatio() {
		return carrierCallCnt3mRatio;
	}

	public void setCarrierCallCnt3mRatio(String carrierCallCnt3mRatio) {
		this.carrierCallCnt3mRatio = carrierCallCnt3mRatio;
	}

	public String getCarrierCallInAvgTime05CallTime() {
		return carrierCallInAvgTime05CallTime;
	}

	public void setCarrierCallInAvgTime05CallTime(String carrierCallInAvgTime05CallTime) {
		this.carrierCallInAvgTime05CallTime = carrierCallInAvgTime05CallTime;
	}

	public String getCarrierCallInAvgTime05CallCount() {
		return carrierCallInAvgTime05CallCount;
	}

	public void setCarrierCallInAvgTime05CallCount(String carrierCallInAvgTime05CallCount) {
		this.carrierCallInAvgTime05CallCount = carrierCallInAvgTime05CallCount;
	}

	public String getCarrierCallInCnt3d() {
		return carrierCallInCnt3d;
	}

	public void setCarrierCallInCnt3d(String carrierCallInCnt3d) {
		this.carrierCallInCnt3d = carrierCallInCnt3d;
	}

	public String getCarrierCallInCnt6m() {
		return carrierCallInCnt6m;
	}

	public void setCarrierCallInCnt6m(String carrierCallInCnt6m) {
		this.carrierCallInCnt6m = carrierCallInCnt6m;
	}

	public String getCarrierCallInCnt7d() {
		return carrierCallInCnt7d;
	}

	public void setCarrierCallInCnt7d(String carrierCallInCnt7d) {
		this.carrierCallInCnt7d = carrierCallInCnt7d;
	}

	public String getCarrierCallInOutCnt3mRatioCallCountHc() {
		return carrierCallInOutCnt3mRatioCallCountHc;
	}

	public void setCarrierCallInOutCnt3mRatioCallCountHc(String carrierCallInOutCnt3mRatioCallCountHc) {
		this.carrierCallInOutCnt3mRatioCallCountHc = carrierCallInOutCnt3mRatioCallCountHc;
	}

	public String getCarrierCallInTime01CallTime() {
		return carrierCallInTime01CallTime;
	}

	public void setCarrierCallInTime01CallTime(String carrierCallInTime01CallTime) {
		this.carrierCallInTime01CallTime = carrierCallInTime01CallTime;
	}

	public String getCarrierCallInTime1mRatioCallTime() {
		return carrierCallInTime1mRatioCallTime;
	}

	public void setCarrierCallInTime1mRatioCallTime(String carrierCallInTime1mRatioCallTime) {
		this.carrierCallInTime1mRatioCallTime = carrierCallInTime1mRatioCallTime;
	}

	public String getCarrierCallInTime3mRatioCallTime() {
		return carrierCallInTime3mRatioCallTime;
	}

	public void setCarrierCallInTime3mRatioCallTime(String carrierCallInTime3mRatioCallTime) {
		this.carrierCallInTime3mRatioCallTime = carrierCallInTime3mRatioCallTime;
	}

	public String getCarrierCallInTime6mRatioCallTime() {
		return carrierCallInTime6mRatioCallTime;
	}

	public void setCarrierCallInTime6mRatioCallTime(String carrierCallInTime6mRatioCallTime) {
		this.carrierCallInTime6mRatioCallTime = carrierCallInTime6mRatioCallTime;
	}

	public String getCarrierCallOutCnt01CallCount() {
		return carrierCallOutCnt01CallCount;
	}

	public void setCarrierCallOutCnt01CallCount(String carrierCallOutCnt01CallCount) {
		this.carrierCallOutCnt01CallCount = carrierCallOutCnt01CallCount;
	}

	public String getCarrierCallOutCnt01CallCountAll() {
		return carrierCallOutCnt01CallCountAll;
	}

	public void setCarrierCallOutCnt01CallCountAll(String carrierCallOutCnt01CallCountAll) {
		this.carrierCallOutCnt01CallCountAll = carrierCallOutCnt01CallCountAll;
	}

	public String getCarrierCallOutCntNight() {
		return carrierCallOutCntNight;
	}

	public void setCarrierCallOutCntNight(String carrierCallOutCntNight) {
		this.carrierCallOutCntNight = carrierCallOutCntNight;
	}

	public String getCarrierCallOutTimeRatioNight() {
		return carrierCallOutTimeRatioNight;
	}

	public void setCarrierCallOutTimeRatioNight(String carrierCallOutTimeRatioNight) {
		this.carrierCallOutTimeRatioNight = carrierCallOutTimeRatioNight;
	}


	public String getCarrierContactAreaNum() {
		return carrierContactAreaNum;
	}

	public void setCarrierContactAreaNum(String carrierContactAreaNum) {
		this.carrierContactAreaNum = carrierContactAreaNum;
	}

	public String getCarrierContactAvgCntHy() {
		return carrierContactAvgCntHy;
	}

	public void setCarrierContactAvgCntHy(String carrierContactAvgCntHy) {
		this.carrierContactAvgCntHy = carrierContactAvgCntHy;
	}

	public String getCarrierContactNum() {
		return carrierContactNum;
	}

	public void setCarrierContactNum(String carrierContactNum) {
		this.carrierContactNum = carrierContactNum;
	}

	public String getCarrierMinCallInCnt6m() {
		return carrierMinCallInCnt6m;
	}

	public void setCarrierMinCallInCnt6m(String carrierMinCallInCnt6m) {
		this.carrierMinCallInCnt6m = carrierMinCallInCnt6m;
	}

	public String getCarrierSmsCnt01() {
		return carrierSmsCnt01;
	}

	public void setCarrierSmsCnt01(String carrierSmsCnt01) {
		this.carrierSmsCnt01 = carrierSmsCnt01;
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

    public String getCarrierPhoneUsedMonth() {
        return carrierPhoneUsedMonth;
    }

    public void setCarrierPhoneUsedMonth(String carrierPhoneUsedMonth) {
        this.carrierPhoneUsedMonth = carrierPhoneUsedMonth;
    }

    public String getCarrierSmsOutCnt6m() {
        return carrierSmsOutCnt6m;
    }

    public void setCarrierSmsOutCnt6m(String carrierSmsOutCnt6m) {
        this.carrierSmsOutCnt6m = carrierSmsOutCnt6m;
    }

    public String getCarrierMaxContactAreaRatio() {
        return carrierMaxContactAreaRatio;
    }

    public void setCarrierMaxContactAreaRatio(String carrierMaxContactAreaRatio) {
        this.carrierMaxContactAreaRatio = carrierMaxContactAreaRatio;
    }

    public String getCarrierSilentDays() {
        return carrierSilentDays;
    }

    public void setCarrierSilentDays(String carrierSilentDays) {
        this.carrierSilentDays = carrierSilentDays;
    }

    public String getCarrierSilentCntDays() {
        return carrierSilentCntDays;
    }

    public void setCarrierSilentCntDays(String carrierSilentCntDays) {
        this.carrierSilentCntDays = carrierSilentCntDays;
    }

    public String getCarrierCallInAvgTimeM() {
        return carrierCallInAvgTimeM;
    }

    public void setCarrierCallInAvgTimeM(String carrierCallInAvgTimeM) {
        this.carrierCallInAvgTimeM = carrierCallInAvgTimeM;
    }

    public String getCarrierCallInAvgTimeD() {
        return carrierCallInAvgTimeD;
    }

    public void setCarrierCallInAvgTimeD(String carrierCallInAvgTimeD) {
        this.carrierCallInAvgTimeD = carrierCallInAvgTimeD;
    }

    public String getCarrierMaxPhoneBill6m() {
        return carrierMaxPhoneBill6m;
    }

    public void setCarrierMaxPhoneBill6m(String carrierMaxPhoneBill6m) {
        this.carrierMaxPhoneBill6m = carrierMaxPhoneBill6m;
    }

    public String getCarrierContactAreaBlackCityCallCnt() {
        return carrierContactAreaBlackCityCallCnt;
    }

    public void setCarrierContactAreaBlackCityCallCnt(String carrierContactAreaBlackCityCallCnt) {
        this.carrierContactAreaBlackCityCallCnt = carrierContactAreaBlackCityCallCnt;
    }

    public String getCarrierContactCallCnt() {
        return carrierContactCallCnt;
    }

    public void setCarrierContactCallCnt(String carrierContactCallCnt) {
        this.carrierContactCallCnt = carrierContactCallCnt;
    }

    public String getCarrierCallInAvgTimeNight() {
        return carrierCallInAvgTimeNight;
    }

    public void setCarrierCallInAvgTimeNight(String carrierCallInAvgTimeNight) {
        this.carrierCallInAvgTimeNight = carrierCallInAvgTimeNight;
    }

    public String getCarrierCallInAvgCntNight() {
        return carrierCallInAvgCntNight;
    }

    public void setCarrierCallInAvgCntNight(String carrierCallInAvgCntNight) {
        this.carrierCallInAvgCntNight = carrierCallInAvgCntNight;
    }

    public String getCarrierCallTimeTotal1m() {
        return carrierCallTimeTotal1m;
    }

    public void setCarrierCallTimeTotal1m(String carrierCallTimeTotal1m) {
        this.carrierCallTimeTotal1m = carrierCallTimeTotal1m;
    }

    public String getCarrierCallTimeTotalOut1m() {
        return carrierCallTimeTotalOut1m;
    }

    public void setCarrierCallTimeTotalOut1m(String carrierCallTimeTotalOut1m) {
        this.carrierCallTimeTotalOut1m = carrierCallTimeTotalOut1m;
    }

    public String getCarrierCallTotalCntTimeNight() {
        return carrierCallTotalCntTimeNight;
    }

    public void setCarrierCallTotalCntTimeNight(String carrierCallTotalCntTimeNight) {
        this.carrierCallTotalCntTimeNight = carrierCallTotalCntTimeNight;
    }

    public String getCarrierCallTotalCntNight() {
        return carrierCallTotalCntNight;
    }

    public void setCarrierCallTotalCntNight(String carrierCallTotalCntNight) {
        this.carrierCallTotalCntNight = carrierCallTotalCntNight;
    }

    public String getCarrierCallInTimeNight() {
        return carrierCallInTimeNight;
    }

    public void setCarrierCallInTimeNight(String carrierCallInTimeNight) {
        this.carrierCallInTimeNight = carrierCallInTimeNight;
    }

    public String getCarrierCallTimeNight() {
        return carrierCallTimeNight;
    }

    public void setCarrierCallTimeNight(String carrierCallTimeNight) {
        this.carrierCallTimeNight = carrierCallTimeNight;
    }

    public String getCarrierIcePersonCallOutCnt6m() {
        return carrierIcePersonCallOutCnt6m;
    }

    public void setCarrierIcePersonCallOutCnt6m(String carrierIcePersonCallOutCnt6m) {
        this.carrierIcePersonCallOutCnt6m = carrierIcePersonCallOutCnt6m;
    }

    public String getCarrierCallInCnt1m() {
        return carrierCallInCnt1m;
    }

    public void setCarrierCallInCnt1m(String carrierCallInCnt1m) {
        this.carrierCallInCnt1m = carrierCallInCnt1m;
    }

    public String getCarrierCallOutCnt1m() {
        return carrierCallOutCnt1m;
    }

    public void setCarrierCallOutCnt1m(String carrierCallOutCnt1m) {
        this.carrierCallOutCnt1m = carrierCallOutCnt1m;
    }

    public String getCarrierCallOutTime3d() {
        return carrierCallOutTime3d;
    }

    public void setCarrierCallOutTime3d(String carrierCallOutTime3d) {
        this.carrierCallOutTime3d = carrierCallOutTime3d;
    }

    public String getCarrierIcePersonCallOutTime6m() {
        return carrierIcePersonCallOutTime6m;
    }

    public void setCarrierIcePersonCallOutTime6m(String carrierIcePersonCallOutTime6m) {
        this.carrierIcePersonCallOutTime6m = carrierIcePersonCallOutTime6m;
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

    public String getCarrierPhoneRealUsedMonth() {
        return carrierPhoneRealUsedMonth;
    }

    public void setCarrierPhoneRealUsedMonth(String carrierPhoneRealUsedMonth) {
        this.carrierPhoneRealUsedMonth = carrierPhoneRealUsedMonth;
    }

    public String getCarrierSmsCnt3m() {
        return carrierSmsCnt3m;
    }

    public void setCarrierSmsCnt3m(String carrierSmsCnt3m) {
        this.carrierSmsCnt3m = carrierSmsCnt3m;
    }

    public String getCarrierSmsCnt6m() {
        return carrierSmsCnt6m;
    }

    public void setCarrierSmsCnt6m(String carrierSmsCnt6m) {
        this.carrierSmsCnt6m = carrierSmsCnt6m;
    }

    public String getCarrierMaxCallOutAvgTime6m() {
        return carrierMaxCallOutAvgTime6m;
    }

    public void setCarrierMaxCallOutAvgTime6m(String carrierMaxCallOutAvgTime6m) {
        this.carrierMaxCallOutAvgTime6m = carrierMaxCallOutAvgTime6m;
    }

    public String getCarrierCallInterflowNum() {
        return carrierCallInterflowNum;
    }

    public void setCarrierCallInterflowNum(String carrierCallInterflowNum) {
        this.carrierCallInterflowNum = carrierCallInterflowNum;
    }

    public String getCarrierCallCntNight() {
        return carrierCallCntNight;
    }

    public void setCarrierCallCntNight(String carrierCallCntNight) {
        this.carrierCallCntNight = carrierCallCntNight;
    }

    public String getCarrierPhoneBill3m() {
        return carrierPhoneBill3m;
    }

    public void setCarrierPhoneBill3m(String carrierPhoneBill3m) {
        this.carrierPhoneBill3m = carrierPhoneBill3m;
    }

    public String getCarrierPhoneBill6m() {
        return carrierPhoneBill6m;
    }

    public void setCarrierPhoneBill6m(String carrierPhoneBill6m) {
        this.carrierPhoneBill6m = carrierPhoneBill6m;
    }

    public String getCarrierCallOutAvgTime02() {
        return carrierCallOutAvgTime02;
    }

    public void setCarrierCallOutAvgTime02(String carrierCallOutAvgTime02) {
        this.carrierCallOutAvgTime02 = carrierCallOutAvgTime02;
    }

    public String getCarrierCallOutAvgTimes02() {
        return carrierCallOutAvgTimes02;
    }

    public void setCarrierCallOutAvgTimes02(String carrierCallOutAvgTimes02) {
        this.carrierCallOutAvgTimes02 = carrierCallOutAvgTimes02;
    }

    public String getCarrierIcePersonCallTime1m() {
        return carrierIcePersonCallTime1m;
    }

    public void setCarrierIcePersonCallTime1m(String carrierIcePersonCallTime1m) {
        this.carrierIcePersonCallTime1m = carrierIcePersonCallTime1m;
    }

    public String getCarrierCallInCnt3mRatio() {
        return carrierCallInCnt3mRatio;
    }

    public void setCarrierCallInCnt3mRatio(String carrierCallInCnt3mRatio) {
        this.carrierCallInCnt3mRatio = carrierCallInCnt3mRatio;
    }

    public String getCarrierCallInCnt6mRatio() {
        return carrierCallInCnt6mRatio;
    }

    public void setCarrierCallInCnt6mRatio(String carrierCallInCnt6mRatio) {
        this.carrierCallInCnt6mRatio = carrierCallInCnt6mRatio;
    }

    public String getCarrierCallOutCntCallOutTimes() {
        return carrierCallOutCntCallOutTimes;
    }

    public void setCarrierCallOutCntCallOutTimes(String carrierCallOutCntCallOutTimes) {
        this.carrierCallOutCntCallOutTimes = carrierCallOutCntCallOutTimes;
    }

    public String getCarrierCallOutCntCallTimes() {
        return carrierCallOutCntCallTimes;
    }

    public void setCarrierCallOutCntCallTimes(String carrierCallOutCntCallTimes) {
        this.carrierCallOutCntCallTimes = carrierCallOutCntCallTimes;
    }

    public String getCarrierCallInCnt1mCallOutTimes() {
        return carrierCallInCnt1mCallOutTimes;
    }

    public void setCarrierCallInCnt1mCallOutTimes(String carrierCallInCnt1mCallOutTimes) {
        this.carrierCallInCnt1mCallOutTimes = carrierCallInCnt1mCallOutTimes;
    }

    public String getCarrierCallInCnt6mCallOutTimes() {
        return carrierCallInCnt6mCallOutTimes;
    }

    public void setCarrierCallInCnt6mCallOutTimes(String carrierCallInCnt6mCallOutTimes) {
        this.carrierCallInCnt6mCallOutTimes = carrierCallInCnt6mCallOutTimes;
    }

    public String getCarrierMinCallInTime6m() {
        return carrierMinCallInTime6m;
    }

    public void setCarrierMinCallInTime6m(String carrierMinCallInTime6m) {
        this.carrierMinCallInTime6m = carrierMinCallInTime6m;
    }

    public String getBqsStarnetCnt6m() {
        return bqsStarnetCnt6m;
    }

    public void setBqsStarnetCnt6m(String bqsStarnetCnt6m) {
        this.bqsStarnetCnt6m = bqsStarnetCnt6m;
    }

	public String getCarrierCallOutCnt6m() {
		return carrierCallOutCnt6m;
	}

	public void setCarrierCallOutCnt6m(String carrierCallOutCnt6m) {
		this.carrierCallOutCnt6m = carrierCallOutCnt6m;
	}

	public String getCarrier110AvgTimeEvidence() {
		return carrier110AvgTimeEvidence;
	}

	public void setCarrier110AvgTimeEvidence(String carrier110AvgTimeEvidence) {
		this.carrier110AvgTimeEvidence = carrier110AvgTimeEvidence;
	}

	public String getCarrier110AvgTimeResult() {
		return carrier110AvgTimeResult;
	}

	public void setCarrier110AvgTimeResult(String carrier110AvgTimeResult) {
		this.carrier110AvgTimeResult = carrier110AvgTimeResult;
	}

	public String getBqsMaxPay() {
		return bqsMaxPay;
	}

	public void setBqsMaxPay(String bqsMaxPay) {
		this.bqsMaxPay = bqsMaxPay;
	}

	public String getCarrierCallInCnt01VsTotalSumTerminatingCallCount() {
		return carrierCallInCnt01VsTotalSumTerminatingCallCount;
	}

	public void setCarrierCallInCnt01VsTotalSumTerminatingCallCount(
			String carrierCallInCnt01VsTotalSumTerminatingCallCount) {
		this.carrierCallInCnt01VsTotalSumTerminatingCallCount = carrierCallInCnt01VsTotalSumTerminatingCallCount;
	}

	public String getCarrierCallInCnt01VsTotalSumOriginatingCallCount() {
		return carrierCallInCnt01VsTotalSumOriginatingCallCount;
	}

	public void setCarrierCallInCnt01VsTotalSumOriginatingCallCount(
			String carrierCallInCnt01VsTotalSumOriginatingCallCount) {
		this.carrierCallInCnt01VsTotalSumOriginatingCallCount = carrierCallInCnt01VsTotalSumOriginatingCallCount;
	}
    
    
}
