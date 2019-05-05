package com.shangyong.backend.entity.redis.fraud2_0;

import java.util.HashMap;

/**
 * Function: 白骑士的报告数据的redis库 (运营商) 2.0
 * date: 2018年6月25日 下午8:59:03
 * @author lz
 * @since JDK 1.8
 */
public class BqsReport20Redis {

    /**
     * 静默天数（分子）1
     */
    private String carrierSilentAvgDays;

    /**
     * 静默次数（分母）1
     */
    private String carrierSilentAvgTimes;
    
    /**
     * 入网月数1
     */
    private String carrierPhoneUsedMonth;

    /**
     * 半年发送短信条数1
     */
    private String carrierSmsOutCnt6m;

    /**
     * 最频繁朋友圈通话时间占比1
     */
    private String carrierMaxContactAreaRatio;
    
    /**
     *   08:30-11:59呼入时间(分子)1
     */
    private String carrierCallInAvgTimeM;

    /**
     * 08:30-11:59呼入次数（分母）1
     */
    private String carrierCallInAvgTimeD;

    /**
     * 夜间通话时间（分子）1
     */
    private String carrierCallTotalCntTimeNight;

    /**
     * 夜间通话次数（分母）1
     */
    private String carrierCallTotalCntNight;

    /**
     * 紧急联系人呼出次数1
     */
    private String carrierIcePersonCallOutCnt6m;

    /**
     * 近1月（从申请上月起）:呼入次数(分子)1
     */
    private String carrierCallInCnt1m;

    /**
     * 近1月（从申请上月起）:呼出次数（分母）1
     */
    private String carrierCallOutCnt1m;

    /**
     * 呼入次数:近5月（从申请上月起）（分母）1
     */
    private String carrierCallInCnt6mRatio;

    /**
     * 近5月（从申请上月起）:呼出次数（分子）1
     */
    private String carrierCallOutCntCallOutTimes;

    /**
     * 近5月（从申请上月起）:通话次数（分母）1
     */
    private String carrierCallOutCntCallTimes;

    /**
     * 手机号星网模型大小（180天之内）白骑士特有1
     */
    private String bqsStarnetCnt6m;
    
    /**
     * 节假日出行天数1
     */
    private String bqsGoOutAreaCntJjr;
    
    /**
     * 1
     */
    private String bqsMulti3m;
    
    /**
     * 话费存缴次数1
     */
    private String bqsPayCnt;

    /**
     * 23:30 ~ 05:59通话次数(分子)1
     */
    private String carrierCallCnt01;
    
    /**
     * 总通话次数(分母)1
     */
    private String carrierCallCntTotal;
    
    /**
     * 通话次数:近1月（从申请上月起）(分子)1
     */
    private String carrierCallCnt1mRatio;
    
    /**
     * 通话次数:近3月（从申请上月起）(分母)1
     */
    private String carrierCallCnt3mRatio;
    
    /**
     * 14:00 ~ 17:59呼入时长(分子)1
     */
    private String carrierCallInAvgTime05CallTime;
    
    /**
     * 14:00 ~ 17:59呼入次数(分母)1
     */
    private String carrierCallInAvgTime05CallCount;
    
    /**
     * 三天 求和1
     */
    private String carrierCallInCnt3d;
    
    /**
     * 拨入电话号码个数1
     */
    private String carrierCallInCnt6m;
    
    /**
     * 七天 通话次数求和1
     */
    private String carrierCallInCnt7d;
    
    /**
     * period_type='23:30 ~ 05:59'1
     */
    private String carrierCallInTime01CallTime;

    /**
     * 呼入时长:近1月（从申请上月起）1
     */
    private String carrierCallInTime1mRatioCallTime;
    
    /**
     * 呼入时长:近3月（从申请上月起）1
     */
    private String carrierCallInTime3mRatioCallTime;
    
    /**
     * 呼入时长:近5月（从申请上月起）1
     */
    private String carrierCallInTime6mRatioCallTime;
    
    
    /**
     * inspectionItems='夜间通话次数(00:00 ~ 06:00)'夜间呼出次数1
     */
    private String carrierCallOutCntNight;
    
    /**
     * 夜间通话(00:00 ~ 06:00)呼出时长1
     */
    private String carrierCallOutTimeRatioNight;
    
    /**
     * (备注)去重计数1
     */
    private String carrierContactAreaNum;
    
    /**
     * 朋友圈活跃度1
     */
    private String carrierContactAvgCntHy;
    
    /**
     * 朋友圈大小1
     */
    private String carrierContactNum;
    
    /**
     * 近5月（从申请上月起）:最小呼入次数1
     */
    private String carrierMinCallInCnt6m;
    
    /**
     * period_type='23:30 ~ 05:59'1
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

    /**
     * 对象转map的方法
     * @return
     */
    public HashMap<String,String> toMap(){
        HashMap<String,String> map = new HashMap<String,String>();

        map.put("carrierSilentAvgDays", ("null".equals(this.carrierSilentAvgDays) || null == this.carrierSilentAvgDays) ? "unknow" : this.carrierSilentAvgDays);
        map.put("carrierSilentAvgTimes", ("null".equals(this.carrierSilentAvgTimes) || null == this.carrierSilentAvgTimes) ? "unknow" : this.carrierSilentAvgTimes);
        map.put("carrierPhoneUsedMonth", ("null".equals(this.carrierPhoneUsedMonth) || null == this.carrierPhoneUsedMonth) ? "unknow" : this.carrierPhoneUsedMonth);
        map.put("carrierSmsOutCnt6m", ("null".equals(this.carrierSmsOutCnt6m) || null == this.carrierSmsOutCnt6m) ? "unknow" : this.carrierSmsOutCnt6m);
        map.put("carrierMaxContactAreaRatio", ("null".equals(this.carrierMaxContactAreaRatio) || null == this.carrierMaxContactAreaRatio) ? "unknow" : this.carrierMaxContactAreaRatio);
        map.put("carrierCallInAvgTimeM", ("null".equals(this.carrierCallInAvgTimeM) || null == this.carrierCallInAvgTimeM) ? "unknow" : this.carrierCallInAvgTimeM);
        map.put("carrierCallInAvgTimeD", ("null".equals(this.carrierCallInAvgTimeD) || null == this.carrierCallInAvgTimeD) ? "unknow" : this.carrierCallInAvgTimeD);
        map.put("carrierCallTotalCntTimeNight", ("null".equals(this.carrierCallTotalCntTimeNight) || null == this.carrierCallTotalCntTimeNight) ? "unknow" : this.carrierCallTotalCntTimeNight);
        map.put("carrierCallTotalCntNight", ("null".equals(this.carrierCallTotalCntNight) || null == this.carrierCallTotalCntNight) ? "unknow" : this.carrierCallTotalCntNight);
        map.put("carrierIcePersonCallOutCnt6m", ("null".equals(this.carrierIcePersonCallOutCnt6m) || null == this.carrierIcePersonCallOutCnt6m) ? "unknow" : this.carrierIcePersonCallOutCnt6m);
        map.put("carrierCallInCnt1m", ("null".equals(this.carrierCallInCnt1m) || null == this.carrierCallInCnt1m) ? "unknow" : this.carrierCallInCnt1m);
        map.put("carrierCallOutCnt1m", ("null".equals(this.carrierCallOutCnt1m) || null == this.carrierCallOutCnt1m) ? "unknow" : this.carrierCallOutCnt1m);
        map.put("carrierCallInCnt6mRatio", ("null".equals(this.carrierCallInCnt6mRatio) || null == this.carrierCallInCnt6mRatio) ? "unknow" : this.carrierCallInCnt6mRatio);
        map.put("carrierCallOutCntCallOutTimes", ("null".equals(this.carrierCallOutCntCallOutTimes) || null == this.carrierCallOutCntCallOutTimes) ? "unknow" : this.carrierCallOutCntCallOutTimes);
        map.put("carrierCallOutCntCallTimes", ("null".equals(this.carrierCallOutCntCallTimes) || null == this.carrierCallOutCntCallTimes) ? "unknow" : this.carrierCallOutCntCallTimes);
        map.put("bqsStarnetCnt6m", ("null".equals(this.bqsStarnetCnt6m) || null == this.bqsStarnetCnt6m) ? "unknow" : this.bqsStarnetCnt6m);
        map.put("bqsGoOutAreaCntJjr", ("null".equals(this.bqsGoOutAreaCntJjr) || null == this.bqsGoOutAreaCntJjr) ? "unknow" : this.bqsGoOutAreaCntJjr);
        map.put("bqsMulti3m", ("null".equals(this.bqsMulti3m) || null == this.bqsMulti3m) ? "unknow" : this.bqsMulti3m);
        map.put("bqsPayCnt", ("null".equals(this.bqsPayCnt) || null == this.bqsPayCnt) ? "unknow" : this.bqsPayCnt);
        map.put("carrierCallCnt01", ("null".equals(this.carrierCallCnt01) || null == this.carrierCallCnt01) ? "unknow" : this.carrierCallCnt01);
        map.put("carrierCallCntTotal", ("null".equals(this.carrierCallCntTotal) || null == this.carrierCallCntTotal) ? "unknow" : this.carrierCallCntTotal);
        map.put("carrierCallCnt1mRatio", ("null".equals(this.carrierCallCnt1mRatio) || null == this.carrierCallCnt1mRatio) ? "unknow" : this.carrierCallCnt1mRatio);
        map.put("carrierCallCnt3mRatio", ("null".equals(this.carrierCallCnt3mRatio) || null == this.carrierCallCnt3mRatio) ? "unknow" : this.carrierCallCnt3mRatio);
        map.put("carrierCallInAvgTime05CallTime", ("null".equals(this.carrierCallInAvgTime05CallTime) || null == this.carrierCallInAvgTime05CallTime) ? "unknow" : this.carrierCallInAvgTime05CallTime);
        map.put("carrierCallInAvgTime05CallCount", ("null".equals(this.carrierCallInAvgTime05CallCount) || null == this.carrierCallInAvgTime05CallCount) ? "unknow" : this.carrierCallInAvgTime05CallCount);
        map.put("carrierCallInCnt3d", ("null".equals(this.carrierCallInCnt3d) || null == this.carrierCallInCnt3d) ? "unknow" : this.carrierCallInCnt3d);
        map.put("carrierCallInCnt6m", ("null".equals(this.carrierCallInCnt6m) || null == this.carrierCallInCnt6m) ? "unknow" : this.carrierCallInCnt6m);
        map.put("carrierCallInCnt7d", ("null".equals(this.carrierCallInCnt7d) || null == this.carrierCallInCnt7d) ? "unknow" : this.carrierCallInCnt7d);
        map.put("carrierCallInTime01CallTime", ("null".equals(this.carrierCallInTime01CallTime) || null == this.carrierCallInTime01CallTime) ? "unknow" : this.carrierCallInTime01CallTime);
        map.put("carrierCallInTime3mRatioCallTime", ("null".equals(this.carrierCallInTime3mRatioCallTime) || null == this.carrierCallInTime3mRatioCallTime) ? "unknow" : this.carrierCallInTime3mRatioCallTime);
        map.put("carrierCallInTime6mRatioCallTime", ("null".equals(this.carrierCallInTime6mRatioCallTime) || null == this.carrierCallInTime6mRatioCallTime) ? "unknow" : this.carrierCallInTime6mRatioCallTime);
        map.put("carrierCallOutCntNight", ("null".equals(this.carrierCallOutCntNight) || null == this.carrierCallOutCntNight) ? "unknow" : this.carrierCallOutCntNight);
        map.put("carrierCallOutTimeRatioNight", ("null".equals(this.carrierCallOutTimeRatioNight) || null == this.carrierCallOutTimeRatioNight) ? "unknow" : this.carrierCallOutTimeRatioNight);
        map.put("carrierContactAreaNum", ("null".equals(this.carrierContactAreaNum) || null == this.carrierContactAreaNum) ? "unknow" : this.carrierContactAreaNum);
        map.put("carrierContactAvgCntHy", ("null".equals(this.carrierContactAvgCntHy) || null == this.carrierContactAvgCntHy) ? "unknow" : this.carrierContactAvgCntHy);
        map.put("carrierContactNum", ("null".equals(this.carrierContactNum) || null == this.carrierContactNum) ? "unknow" : this.carrierContactNum);
        map.put("carrierMinCallInCnt6m", ("null".equals(this.carrierMinCallInCnt6m) || null == this.carrierMinCallInCnt6m) ? "unknow" : this.carrierMinCallInCnt6m);
        map.put("carrierSmsCnt01", ("null".equals(this.carrierSmsCnt01) || null == this.carrierSmsCnt01) ? "unknow" : this.carrierSmsCnt01);
        map.put("carrierCallOutCnt6m", ("null".equals(this.carrierCallOutCnt6m) || null == this.carrierCallOutCnt6m) ? "unknow" : this.carrierCallOutCnt6m);
        map.put("carrier110AvgTimeEvidence", ("null".equals(this.carrier110AvgTimeEvidence) || null == this.carrier110AvgTimeEvidence) ? "unknow" : this.carrier110AvgTimeEvidence);
        map.put("carrier110AvgTimeResult", ("null".equals(this.carrier110AvgTimeResult) || null == this.carrier110AvgTimeResult) ? "unknow" : this.carrier110AvgTimeResult);
        map.put("bqsMaxPay", ("null".equals(this.bqsMaxPay) || null == this.bqsMaxPay) ? "unknow" : this.bqsMaxPay);
        map.put("carrierCallInCnt01VsTotalSumTerminatingCallCount", ("null".equals(this.carrierCallInCnt01VsTotalSumTerminatingCallCount) || null == this.carrierCallInCnt01VsTotalSumTerminatingCallCount) ? "unknow" : this.carrierCallInCnt01VsTotalSumTerminatingCallCount);
        map.put("carrierCallInCnt01VsTotalSumOriginatingCallCount", ("null".equals(this.carrierCallInCnt01VsTotalSumOriginatingCallCount) || null == this.carrierCallInCnt01VsTotalSumOriginatingCallCount) ? "unknow" : this.carrierCallInCnt01VsTotalSumOriginatingCallCount);
        map.put("carrierCallInTime1mRatioCallTime", ("null".equals(this.carrierCallInTime1mRatioCallTime) || null == this.carrierCallInTime1mRatioCallTime) ? "unknow" : this.carrierCallInTime1mRatioCallTime);
        return map;
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

	public String getBqsStarnetCnt6m() {
		return bqsStarnetCnt6m;
	}

	public void setBqsStarnetCnt6m(String bqsStarnetCnt6m) {
		this.bqsStarnetCnt6m = bqsStarnetCnt6m;
	}

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

	public void setCarrierCallInCnt01VsTotalSumTerminatingCallCount(String carrierCallInCnt01VsTotalSumTerminatingCallCount) {
		this.carrierCallInCnt01VsTotalSumTerminatingCallCount = carrierCallInCnt01VsTotalSumTerminatingCallCount;
	}

	public String getCarrierCallInCnt01VsTotalSumOriginatingCallCount() {
		return carrierCallInCnt01VsTotalSumOriginatingCallCount;
	}

	public void setCarrierCallInCnt01VsTotalSumOriginatingCallCount(String carrierCallInCnt01VsTotalSumOriginatingCallCount) {
		this.carrierCallInCnt01VsTotalSumOriginatingCallCount = carrierCallInCnt01VsTotalSumOriginatingCallCount;
	}
}
