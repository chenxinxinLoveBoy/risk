package com.shangyong.backend.entity.redis.fraud1_1;

import java.util.HashMap;

/**
 * @Description  白骑士的报告数据的redis库
 * @Author  Corey
 * @Date  2018/4/11 16:23
 */

public class BqsReport11Redis {

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
        map.put("carrierSilentDays", ("null".equals(this.carrierSilentDays) || null == this.carrierSilentDays) ? "unknow" : this.carrierSilentDays);
        map.put("carrierSilentCntDays", ("null".equals(this.carrierSilentCntDays) || null == this.carrierSilentCntDays) ? "unknow" : this.carrierSilentCntDays);
        map.put("carrierCallInAvgTimeM", ("null".equals(this.carrierCallInAvgTimeM) || null == this.carrierCallInAvgTimeM) ? "unknow" : this.carrierCallInAvgTimeM);
        map.put("carrierCallInAvgTimeD", ("null".equals(this.carrierCallInAvgTimeD) || null == this.carrierCallInAvgTimeD) ? "unknow" : this.carrierCallInAvgTimeD);
        map.put("carrierMaxPhoneBill6m", ("null".equals(this.carrierMaxPhoneBill6m) || null == this.carrierMaxPhoneBill6m) ? "unknow" : this.carrierMaxPhoneBill6m);
        map.put("carrierContactAreaBlackCityCallCnt", ("null".equals(this.carrierContactAreaBlackCityCallCnt) || null == this.carrierContactAreaBlackCityCallCnt) ? "unknow" : this.carrierContactAreaBlackCityCallCnt);
        map.put("carrierContactCallCnt", ("null".equals(this.carrierContactCallCnt) || null == this.carrierContactCallCnt) ? "unknow" : this.carrierContactCallCnt);
        map.put("carrierCallInAvgTimeNight", ("null".equals(this.carrierCallInAvgTimeNight) || null == this.carrierCallInAvgTimeNight) ? "unknow" : this.carrierCallInAvgTimeNight);
        map.put("carrierCallInAvgCntNight", ("null".equals(this.carrierCallInAvgCntNight) || null == this.carrierCallInAvgCntNight) ? "unknow" : this.carrierCallInAvgCntNight);
        map.put("carrierCallTimeTotal1m", ("null".equals(this.carrierCallTimeTotal1m) || null == this.carrierCallTimeTotal1m) ? "unknow" : this.carrierCallTimeTotal1m);
        map.put("carrierCallTimeTotalOut1m", ("null".equals(this.carrierCallTimeTotalOut1m) || null == this.carrierCallTimeTotalOut1m) ? "unknow" : this.carrierCallTimeTotalOut1m);
        map.put("carrierCallTotalCntTimeNight", ("null".equals(this.carrierCallTotalCntTimeNight) || null == this.carrierCallTotalCntTimeNight) ? "unknow" : this.carrierCallTotalCntTimeNight);
        map.put("carrierCallTotalCntNight", ("null".equals(this.carrierCallTotalCntNight) || null == this.carrierCallTotalCntNight) ? "unknow" : this.carrierCallTotalCntNight);
        map.put("carrierCallInTimeNight", ("null".equals(this.carrierCallInTimeNight) || null == this.carrierCallInTimeNight) ? "unknow" : this.carrierCallInTimeNight);
        map.put("carrierCallTimeNight", ("null".equals(this.carrierCallTimeNight) || null == this.carrierCallTimeNight) ? "unknow" : this.carrierCallTimeNight);
        map.put("carrierIcePersonCallOutCnt6m", ("null".equals(this.carrierIcePersonCallOutCnt6m) || null == this.carrierIcePersonCallOutCnt6m) ? "unknow" : this.carrierIcePersonCallOutCnt6m);
        map.put("carrierCallInCnt1m", ("null".equals(this.carrierCallInCnt1m) || null == this.carrierCallInCnt1m) ? "unknow" : this.carrierCallInCnt1m);
        map.put("carrierCallOutCnt1m", ("null".equals(this.carrierCallOutCnt1m) || null == this.carrierCallOutCnt1m) ? "unknow" : this.carrierCallOutCnt1m);
        map.put("carrierCallOutTime3d", ("null".equals(this.carrierCallOutTime3d) || null == this.carrierCallOutTime3d) ? "unknow" : this.carrierCallOutTime3d);
        map.put("carrierIcePersonCallOutTime6m", ("null".equals(this.carrierIcePersonCallOutTime6m) || null == this.carrierIcePersonCallOutTime6m) ? "unknow" : this.carrierIcePersonCallOutTime6m);
        map.put("carrierPhoneBill1mRatio", ("null".equals(this.carrierPhoneBill1mRatio) || null == this.carrierPhoneBill1mRatio) ? "unknow" : this.carrierPhoneBill1mRatio);
        map.put("carrierPhoneBill3mRatio", ("null".equals(this.carrierPhoneBill3mRatio) || null == this.carrierPhoneBill3mRatio) ? "unknow" : this.carrierPhoneBill3mRatio);
        map.put("carrierPhoneRealUsedMonth", ("null".equals(this.carrierPhoneRealUsedMonth) || null == this.carrierPhoneRealUsedMonth) ? "unknow" : this.carrierPhoneRealUsedMonth);
        map.put("carrierSmsCnt3m", ("null".equals(this.carrierSmsCnt3m) || null == this.carrierSmsCnt3m) ? "unknow" : this.carrierSmsCnt3m);
        map.put("carrierSmsCnt6m", ("null".equals(this.carrierSmsCnt6m) || null == this.carrierSmsCnt6m) ? "unknow" : this.carrierSmsCnt6m);
        map.put("carrierMaxCallOutAvgTime6m", ("null".equals(this.carrierMaxCallOutAvgTime6m) || null == this.carrierMaxCallOutAvgTime6m) ? "unknow" : this.carrierMaxCallOutAvgTime6m);
        map.put("carrierCallInterflowNum", ("null".equals(this.carrierCallInterflowNum) || null == this.carrierCallInterflowNum) ? "unknow" : this.carrierCallInterflowNum);
        map.put("carrierCallCntNight", ("null".equals(this.carrierCallCntNight) || null == this.carrierCallCntNight) ? "unknow" : this.carrierCallCntNight);
        map.put("carrierPhoneBill3m", ("null".equals(this.carrierPhoneBill3m) || null == this.carrierPhoneBill3m) ? "unknow" : this.carrierPhoneBill3m);
        map.put("carrierPhoneBill6m", ("null".equals(this.carrierPhoneBill6m) || null == this.carrierPhoneBill6m) ? "unknow" : this.carrierPhoneBill6m);
        map.put("carrierCallOutAvgTime02", ("null".equals(this.carrierCallOutAvgTime02) || null == this.carrierCallOutAvgTime02) ? "unknow" : this.carrierCallOutAvgTime02);
        map.put("carrierCallOutAvgTimes02", ("null".equals(this.carrierCallOutAvgTimes02) || null == this.carrierCallOutAvgTimes02) ? "unknow" : this.carrierCallOutAvgTimes02);
        map.put("carrierIcePersonCallTime1m", ("null".equals(this.carrierIcePersonCallTime1m) || null == this.carrierIcePersonCallTime1m) ? "unknow" : this.carrierIcePersonCallTime1m);
        map.put("carrierCallInCnt3mRatio", ("null".equals(this.carrierCallInCnt3mRatio) || null == this.carrierCallInCnt3mRatio) ? "unknow" : this.carrierCallInCnt3mRatio);
        map.put("carrierCallInCnt6mRatio", ("null".equals(this.carrierCallInCnt6mRatio) || null == this.carrierCallInCnt6mRatio) ? "unknow" : this.carrierCallInCnt6mRatio);
        map.put("carrierCallOutCntCallOutTimes", ("null".equals(this.carrierCallOutCntCallOutTimes) || null == this.carrierCallOutCntCallOutTimes) ? "unknow" : this.carrierCallOutCntCallOutTimes);
        map.put("carrierCallOutCntCallTimes", ("null".equals(this.carrierCallOutCntCallTimes) || null == this.carrierCallOutCntCallTimes) ? "unknow" : this.carrierCallOutCntCallTimes);
        map.put("carrierCallInCnt1mCallOutTimes", ("null".equals(this.carrierCallInCnt1mCallOutTimes) || null == this.carrierCallInCnt1mCallOutTimes) ? "unknow" : this.carrierCallInCnt1mCallOutTimes);
        map.put("carrierCallInCnt6mCallOutTimes", ("null".equals(this.carrierCallInCnt6mCallOutTimes) || null == this.carrierCallInCnt6mCallOutTimes) ? "unknow" : this.carrierCallInCnt6mCallOutTimes);
        map.put("carrierMinCallInTime6m", ("null".equals(this.carrierMinCallInTime6m) || null == this.carrierMinCallInTime6m) ? "unknow" : this.carrierMinCallInTime6m);
        map.put("bqsStarnetCnt6m", ("null".equals(this.bqsStarnetCnt6m) || null == this.bqsStarnetCnt6m) ? "unknow" : this.bqsStarnetCnt6m);
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
}
