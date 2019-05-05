package com.shangyong.backend.entity.redis.fraud1_1;

import java.util.HashMap;
/**
 * 同盾数据魔盒报告
 * @author Smk
 *
 */
public class TongDunReport11Redis {
	
	/**
	 * 静默天数
	 */
	private String carrierSilentAvgDays;
	
	/**
     * 静默次数
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
     * 获取6个月最大通话次数
     */
	private String carrierMaxContactAreaRatio;
	
	
	/**
	 * 近5月（从申请上月起），最大月话费金额
	 */
	private String carrierMaxPhoneBill6m;
	
	/**
	 * 联系人黑名单城市通话次数
	 */
	private String carrierContactAreaBlackCityRatio;
	/**
	 * 联系人通话总次数
	 */
	private String carrierContactCallCntRatio;
	
	/**
	 * 近1月（从申请上月起）:通话时间时长
	 */
	private String carrierCallTime1m;
	/**
	 * 近1月（从申请上月起）呼出时间时长
	 */
	private String carrierTotalOut1m;
	
	/**
	 * 夜间通话时长
	 */
	private String carrierCallTimeNight;
	
	/**
	 * 夜间通话次数
	 */
	private String carrierCallCountNight;
	
	/**
	 * 紧急联系人呼出次数
	 */
	private String carrierIcePersonCallOutCnt6m;
	
	/**
	 * 近1月（从申请上月起）:呼入次数
	 */
	private String carrierCallIn1mRatio;
	/**
	 * 近1月（从申请上月起）:呼出次数
	 */
	private String carrierCallOutCnt1mRatio;
	
	/**
	 * 紧急联系人近6个月呼出时长
	 */
	private String carrierIcePersonCallOutTime6m;
	
	/**
	 * 话费金额:近1月（从申请上月起）
	 */
	private String carrierPhoneBill1mRatio;
	
	/**
	 * 话费金额:近3月（从申请上月起）
	 */
	private String carrierPhoneBill3mRatio;
	
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
	 * 互通电话号码个数
	 */
	private String carrierCallInterflowNum;
	
	/**
	 * 话费金额:近5月（从申请上月起）
	 */
	private String carrierPhoneBill6mRatio;
	
	/**
	 * 紧急联系人近1个月通话时长
	 */
	private String carrierIcePersonCallTime1m;
	
	/**
	 * 呼入次数:近3月（从申请上月起）
	 */
	private String carrierCallInCnt3mRatio;
	
	/**
	 * 呼入次数:近5月（从申请上月起）
	 */
	private String carrierCallInCnt6mRatio;
	
	/**
	 * 近5月（从申请上月起）:呼出次数
	 */
	private String carrierCallOut6mRatio;
	
	/**
	 * 近5月（从申请上月起）通话次数
	 */
	private String carrierCallCnt6mRatio;
	
	/**
	 * 近5月（从申请上月起），最小月呼入时长
	 */
	private String carrierMinCallInTime6m;
	
	
	
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

	public String getCarrierMaxPhoneBill6m() {
		return carrierMaxPhoneBill6m;
	}



	public void setCarrierMaxPhoneBill6m(String carrierMaxPhoneBill6m) {
		this.carrierMaxPhoneBill6m = carrierMaxPhoneBill6m;
	}



	public String getCarrierContactAreaBlackCityRatio() {
		return carrierContactAreaBlackCityRatio;
	}



	public void setCarrierContactAreaBlackCityRatio(String carrierContactAreaBlackCityRatio) {
		this.carrierContactAreaBlackCityRatio = carrierContactAreaBlackCityRatio;
	}



	public String getCarrierContactCallCntRatio() {
		return carrierContactCallCntRatio;
	}



	public void setCarrierContactCallCntRatio(String carrierContactCallCntRatio) {
		this.carrierContactCallCntRatio = carrierContactCallCntRatio;
	}



	public String getCarrierCallTime1m() {
		return carrierCallTime1m;
	}



	public void setCarrierCallTime1m(String carrierCallTime1m) {
		this.carrierCallTime1m = carrierCallTime1m;
	}



	public String getCarrierTotalOut1m() {
		return carrierTotalOut1m;
	}



	public void setCarrierTotalOut1m(String carrierTotalOut1m) {
		this.carrierTotalOut1m = carrierTotalOut1m;
	}



	public String getCarrierCallTimeNight() {
		return carrierCallTimeNight;
	}



	public void setCarrierCallTimeNight(String carrierCallTimeNight) {
		this.carrierCallTimeNight = carrierCallTimeNight;
	}



	public String getCarrierCallCountNight() {
		return carrierCallCountNight;
	}



	public void setCarrierCallCountNight(String carrierCallCountNight) {
		this.carrierCallCountNight = carrierCallCountNight;
	}



	public String getCarrierIcePersonCallOutCnt6m() {
		return carrierIcePersonCallOutCnt6m;
	}



	public void setCarrierIcePersonCallOutCnt6m(String carrierIcePersonCallOutCnt6m) {
		this.carrierIcePersonCallOutCnt6m = carrierIcePersonCallOutCnt6m;
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



	public String getCarrierCallInterflowNum() {
		return carrierCallInterflowNum;
	}



	public void setCarrierCallInterflowNum(String carrierCallInterflowNum) {
		this.carrierCallInterflowNum = carrierCallInterflowNum;
	}



	public String getCarrierPhoneBill6mRatio() {
		return carrierPhoneBill6mRatio;
	}



	public void setCarrierPhoneBill6mRatio(String carrierPhoneBill6mRatio) {
		this.carrierPhoneBill6mRatio = carrierPhoneBill6mRatio;
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



	public String getCarrierCallOut6mRatio() {
		return carrierCallOut6mRatio;
	}



	public void setCarrierCallOut6mRatio(String carrierCallOut6mRatio) {
		this.carrierCallOut6mRatio = carrierCallOut6mRatio;
	}



	public String getCarrierCallCnt6mRatio() {
		return carrierCallCnt6mRatio;
	}



	public void setCarrierCallCnt6mRatio(String carrierCallCnt6mRatio) {
		this.carrierCallCnt6mRatio = carrierCallCnt6mRatio;
	}



	public String getCarrierMinCallInTime6m() {
		return carrierMinCallInTime6m;
	}



	public void setCarrierMinCallInTime6m(String carrierMinCallInTime6m) {
		this.carrierMinCallInTime6m = carrierMinCallInTime6m;
	}



	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("carrierSilentAvgDays", this.carrierSilentAvgDays==null? "unknow" : carrierSilentAvgDays);
		map.put("carrierSilentAvgTimes", this.carrierSilentAvgTimes==null? "unknow" : carrierSilentAvgTimes);
		map.put("carrierPhoneUsedMonth", this.carrierPhoneUsedMonth==null? "unknow" : carrierPhoneUsedMonth);
		map.put("carrierSmsOutCnt6m", this.carrierSmsOutCnt6m==null? "unknow" : carrierSmsOutCnt6m);
		map.put("carrierMaxContactAreaRatio", this.carrierMaxContactAreaRatio==null? "unknow" : carrierMaxContactAreaRatio);
		map.put("carrierMaxPhoneBill6m", this.carrierMaxPhoneBill6m==null? "unknow" : carrierMaxPhoneBill6m);
		map.put("carrierContactAreaBlackCityRatio", this.carrierContactAreaBlackCityRatio==null? "unknow" : carrierContactAreaBlackCityRatio);
		map.put("carrierContactCallCntRatio", this.carrierContactCallCntRatio==null? "unknow" : carrierContactCallCntRatio);
		map.put("carrierCallTime1m", this.carrierCallTime1m==null? "unknow" : carrierCallTime1m);
		map.put("carrierTotalOut1m", this.carrierTotalOut1m==null? "unknow" : carrierTotalOut1m);
		map.put("carrierCallTimeNight", this.carrierCallTimeNight==null? "unknow" : carrierCallTimeNight);
		map.put("carrierCallCountNight", this.carrierCallCountNight==null? "unknow" : carrierCallCountNight);
		map.put("carrierIcePersonCallOutCnt6m", this.carrierIcePersonCallOutCnt6m==null? "unknow" : carrierIcePersonCallOutCnt6m);
		map.put("carrierCallIn1mRatio", this.carrierCallIn1mRatio==null? "unknow" : carrierCallIn1mRatio);
		map.put("carrierCallOutCnt1mRatio", this.carrierCallOutCnt1mRatio==null? "unknow" : carrierCallOutCnt1mRatio);
		map.put("carrierIcePersonCallOutTime6m", this.carrierIcePersonCallOutTime6m==null? "unknow" : carrierIcePersonCallOutTime6m);
		map.put("carrierPhoneBill1mRatio", this.carrierPhoneBill1mRatio==null? "unknow" : carrierPhoneBill1mRatio);
		map.put("carrierPhoneBill3mRatio", this.carrierPhoneBill3mRatio==null? "unknow" : carrierPhoneBill3mRatio);
		map.put("carrierSmsCnt3mRatio", this.carrierSmsCnt3mRatio==null? "unknow" : carrierSmsCnt3mRatio);
		map.put("carrierSmsCnt6mRatio", this.carrierSmsCnt6mRatio==null? "unknow" : carrierSmsCnt6mRatio);
		map.put("carrierMaxCallOutAvgTime6m", this.carrierMaxCallOutAvgTime6m==null? "unknow" : carrierMaxCallOutAvgTime6m);
		map.put("carrierCallInterflowNum", this.carrierCallInterflowNum==null? "unknow" : carrierCallInterflowNum);
		map.put("carrierPhoneBill6mRatio", this.carrierPhoneBill6mRatio==null? "unknow" : carrierPhoneBill6mRatio);
		map.put("carrierIcePersonCallTime1m", this.carrierIcePersonCallTime1m==null? "unknow" : carrierIcePersonCallTime1m);
		map.put("carrierCallInCnt3mRatio", this.carrierCallInCnt3mRatio==null? "unknow" : carrierCallInCnt3mRatio);
		map.put("carrierCallInCnt6mRatio", this.carrierCallInCnt6mRatio==null? "unknow" : carrierCallInCnt6mRatio);
		map.put("carrierCallOut6mRatio", this.carrierCallOut6mRatio==null? "unknow" : carrierCallOut6mRatio);
		map.put("carrierCallCnt6mRatio", this.carrierCallCnt6mRatio==null? "unknow" : carrierCallCnt6mRatio);
		map.put("carrierMinCallInTime6m", this.carrierMinCallInTime6m==null? "unknow" : carrierMinCallInTime6m);
		return map;
	}
	
	
	
	
}
