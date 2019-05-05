package com.shangyong.backend.entity.tdReport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 同盾联系人明细表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
@JsonAutoDetect
public class TdReportDetail {

	/**唯一标识**/
	private String detailId;

	/**申请单编号**/
	private String applicationId;

	/**表属性（1.紧急联系人明细2.家庭号码明细3.工作号码明细4.全部联系人明细）**/
	private String tableType;

	/**联系人号码**/
	private String contactNumber;

	/**联系人关系**/
	private String contactRelation;

	/**联系人排名**/
	private String contactSeqNo;

	/**号码属性**/
	private String contactAttribute;

	/**号码分类**/
	private String contactType;

	/**号码标签**/
	private String contactName;

	/**号码归属地**/
	private String contactArea;

	/**号码是否小号**/
	private String isVirtualNumber;

	/**近1周通话次数**/
	private String callCount1week;

	/**近1月通话次数**/
	private String callCount1month;

	/**近3月通话次数**/
	private String callCount3month;

	/**近3月主叫通话次数**/
	private String callCountActive3month;

	/**近3月被叫通话次数**/
	private String callCountPassive3month;

	/**近3月深夜通话次数**/
	private String callCountLateNight3month;

	/**近3月工作时间通话次数**/
	private String callCountWorkTime3month;

	/**近3月非工作时间通话次数**/
	private String callCountOffworkTime3month;

	/**近3月工作日通话次数**/
	private String callCountWorkday3month;

	/**近3月节假日通话次数**/
	private String callCountHoliday3month;

	/**近6月通话次数**/
	private String callCount6month;

	/**近6月主叫通话次数**/
	private String callCountActive6month;

	/**近6月被叫通话次数**/
	private String callCountPassive6month;

	/**近6月深夜通话次数**/
	private String callCountLateNight6month;

	/**近6月工作时间通话次数**/
	private String callCountWorkTime6month;

	/**近6月非工作时间通话次数**/
	private String callCountOffworkTime6month;

	/**近6月工作日通话次数**/
	private String callCountWorkday6month;

	/**近6月节假日通话次数**/
	private String callCountHoliday6month;

	/**近1月通话时长**/
	private String callTime1month;

	/**近3月通话时长**/
	private String callTime3month;

	/**近3月主叫通话时长**/
	private String callTimeActive3month;

	/**近3月被叫通话时长**/
	private String callTimePassive3month;

	/**近3月深夜通话时长**/
	private String callTimeLateNight3month;

	/**近3月工作时间通话时长**/
	private String callTimeWorkTime3month;

	/**近3月非工作时间通话时长**/
	private String callTimeOffworkTime3month;

	/**近6月通话时长**/
	private String callTime6month;

	/**近6月主叫通话时长**/
	private String callTimeActive6month;

	/**近6月被叫通话时长**/
	private String callTimePassive6month;

	/**近6月深夜通话时长**/
	private String callTimeLateNight6month;

	/**近6月工作时间通话时长**/
	private String callTimeWorkTime6month;

	/**近6月非工作时间通话时长**/
	private String callTimeOffworkTime6month;

	/**近1月短信次数**/
	private String msgCount1month;

	/**近3月短信次数**/
	private String msgCount3month;

	/**近6月短信次数**/
	private String msgCount6month;

	/**近3个月是否有全天通话**/
	private String isWholeDayCall3month;

	/**近6个月是否有全天通话**/
	private String isWholeDayCall6month;

	/**近6月第一次通话时间**/
	private String firstTimeCall6month;

	/**近6月最后一次通话时间**/
	private String lastTimeCall6month;

	/**近6月最长通话间隔天数**/
	private String maxGapDayCall6month;

	/**近6月平均通话间隔天数**/
	private String averageGapDayCall6month;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public TdReportDetail() {
		super();
	}
	public TdReportDetail(String detailId,String applicationId,String tableType,String contactNumber,String contactRelation,String contactSeqNo,String contactAttribute,String contactType,String contactName,String contactArea,String isVirtualNumber,String callCount1week,String callCount1month,String callCount3month,String callCountActive3month,String callCountPassive3month,String callCountLateNight3month,String callCountWorkTime3month,String callCountOffworkTime3month,String callCountWorkday3month,String callCountHoliday3month,String callCount6month,String callCountActive6month,String callCountPassive6month,String callCountLateNight6month,String callCountWorkTime6month,String callCountOffworkTime6month,String callCountWorkday6month,String callCountHoliday6month,String callTime1month,String callTime3month,String callTimeActive3month,String callTimePassive3month,String callTimeLateNight3month,String callTimeWorkTime3month,String callTimeOffworkTime3month,String callTime6month,String callTimeActive6month,String callTimePassive6month,String callTimeLateNight6month,String callTimeWorkTime6month,String callTimeOffworkTime6month,String msgCount1month,String msgCount3month,String msgCount6month,String isWholeDayCall3month,String isWholeDayCall6month,String firstTimeCall6month,String lastTimeCall6month,String maxGapDayCall6month,String averageGapDayCall6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.detailId = detailId;
		this.applicationId = applicationId;
		this.tableType = tableType;
		this.contactNumber = contactNumber;
		this.contactRelation = contactRelation;
		this.contactSeqNo = contactSeqNo;
		this.contactAttribute = contactAttribute;
		this.contactType = contactType;
		this.contactName = contactName;
		this.contactArea = contactArea;
		this.isVirtualNumber = isVirtualNumber;
		this.callCount1week = callCount1week;
		this.callCount1month = callCount1month;
		this.callCount3month = callCount3month;
		this.callCountActive3month = callCountActive3month;
		this.callCountPassive3month = callCountPassive3month;
		this.callCountLateNight3month = callCountLateNight3month;
		this.callCountWorkTime3month = callCountWorkTime3month;
		this.callCountOffworkTime3month = callCountOffworkTime3month;
		this.callCountWorkday3month = callCountWorkday3month;
		this.callCountHoliday3month = callCountHoliday3month;
		this.callCount6month = callCount6month;
		this.callCountActive6month = callCountActive6month;
		this.callCountPassive6month = callCountPassive6month;
		this.callCountLateNight6month = callCountLateNight6month;
		this.callCountWorkTime6month = callCountWorkTime6month;
		this.callCountOffworkTime6month = callCountOffworkTime6month;
		this.callCountWorkday6month = callCountWorkday6month;
		this.callCountHoliday6month = callCountHoliday6month;
		this.callTime1month = callTime1month;
		this.callTime3month = callTime3month;
		this.callTimeActive3month = callTimeActive3month;
		this.callTimePassive3month = callTimePassive3month;
		this.callTimeLateNight3month = callTimeLateNight3month;
		this.callTimeWorkTime3month = callTimeWorkTime3month;
		this.callTimeOffworkTime3month = callTimeOffworkTime3month;
		this.callTime6month = callTime6month;
		this.callTimeActive6month = callTimeActive6month;
		this.callTimePassive6month = callTimePassive6month;
		this.callTimeLateNight6month = callTimeLateNight6month;
		this.callTimeWorkTime6month = callTimeWorkTime6month;
		this.callTimeOffworkTime6month = callTimeOffworkTime6month;
		this.msgCount1month = msgCount1month;
		this.msgCount3month = msgCount3month;
		this.msgCount6month = msgCount6month;
		this.isWholeDayCall3month = isWholeDayCall3month;
		this.isWholeDayCall6month = isWholeDayCall6month;
		this.firstTimeCall6month = firstTimeCall6month;
		this.lastTimeCall6month = lastTimeCall6month;
		this.maxGapDayCall6month = maxGapDayCall6month;
		this.averageGapDayCall6month = averageGapDayCall6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setDetailId(String detailId){
		this.detailId = detailId;
	}

	public String getDetailId(){
		return this.detailId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setTableType(String tableType){
		this.tableType = tableType;
	}

	public String getTableType(){
		return this.tableType;
	}

	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}

	public String getContactNumber(){
		return this.contactNumber;
	}

	public void setContactRelation(String contactRelation){
		this.contactRelation = contactRelation;
	}

	public String getContactRelation(){
		return this.contactRelation;
	}

	public void setContactSeqNo(String contactSeqNo){
		this.contactSeqNo = contactSeqNo;
	}

	public String getContactSeqNo(){
		return this.contactSeqNo;
	}

	public void setContactAttribute(String contactAttribute){
		this.contactAttribute = contactAttribute;
	}

	public String getContactAttribute(){
		return this.contactAttribute;
	}

	public void setContactType(String contactType){
		this.contactType = contactType;
	}

	public String getContactType(){
		return this.contactType;
	}

	public void setContactName(String contactName){
		this.contactName = contactName;
	}

	public String getContactName(){
		return this.contactName;
	}

	public void setContactArea(String contactArea){
		this.contactArea = contactArea;
	}

	public String getContactArea(){
		return this.contactArea;
	}

	public void setIsVirtualNumber(String isVirtualNumber){
		this.isVirtualNumber = isVirtualNumber;
	}

	public String getIsVirtualNumber(){
		return this.isVirtualNumber;
	}

	public void setCallCount1week(String callCount1week){
		this.callCount1week = callCount1week;
	}

	public String getCallCount1week(){
		return this.callCount1week;
	}

	public void setCallCount1month(String callCount1month){
		this.callCount1month = callCount1month;
	}

	public String getCallCount1month(){
		return this.callCount1month;
	}

	public void setCallCount3month(String callCount3month){
		this.callCount3month = callCount3month;
	}

	public String getCallCount3month(){
		return this.callCount3month;
	}

	public void setCallCountActive3month(String callCountActive3month){
		this.callCountActive3month = callCountActive3month;
	}

	public String getCallCountActive3month(){
		return this.callCountActive3month;
	}

	public void setCallCountPassive3month(String callCountPassive3month){
		this.callCountPassive3month = callCountPassive3month;
	}

	public String getCallCountPassive3month(){
		return this.callCountPassive3month;
	}

	public void setCallCountLateNight3month(String callCountLateNight3month){
		this.callCountLateNight3month = callCountLateNight3month;
	}

	public String getCallCountLateNight3month(){
		return this.callCountLateNight3month;
	}

	public void setCallCountWorkTime3month(String callCountWorkTime3month){
		this.callCountWorkTime3month = callCountWorkTime3month;
	}

	public String getCallCountWorkTime3month(){
		return this.callCountWorkTime3month;
	}

	public void setCallCountOffworkTime3month(String callCountOffworkTime3month){
		this.callCountOffworkTime3month = callCountOffworkTime3month;
	}

	public String getCallCountOffworkTime3month(){
		return this.callCountOffworkTime3month;
	}

	public void setCallCountWorkday3month(String callCountWorkday3month){
		this.callCountWorkday3month = callCountWorkday3month;
	}

	public String getCallCountWorkday3month(){
		return this.callCountWorkday3month;
	}

	public void setCallCountHoliday3month(String callCountHoliday3month){
		this.callCountHoliday3month = callCountHoliday3month;
	}

	public String getCallCountHoliday3month(){
		return this.callCountHoliday3month;
	}

	public void setCallCount6month(String callCount6month){
		this.callCount6month = callCount6month;
	}

	public String getCallCount6month(){
		return this.callCount6month;
	}

	public void setCallCountActive6month(String callCountActive6month){
		this.callCountActive6month = callCountActive6month;
	}

	public String getCallCountActive6month(){
		return this.callCountActive6month;
	}

	public void setCallCountPassive6month(String callCountPassive6month){
		this.callCountPassive6month = callCountPassive6month;
	}

	public String getCallCountPassive6month(){
		return this.callCountPassive6month;
	}

	public void setCallCountLateNight6month(String callCountLateNight6month){
		this.callCountLateNight6month = callCountLateNight6month;
	}

	public String getCallCountLateNight6month(){
		return this.callCountLateNight6month;
	}

	public void setCallCountWorkTime6month(String callCountWorkTime6month){
		this.callCountWorkTime6month = callCountWorkTime6month;
	}

	public String getCallCountWorkTime6month(){
		return this.callCountWorkTime6month;
	}

	public void setCallCountOffworkTime6month(String callCountOffworkTime6month){
		this.callCountOffworkTime6month = callCountOffworkTime6month;
	}

	public String getCallCountOffworkTime6month(){
		return this.callCountOffworkTime6month;
	}

	public void setCallCountWorkday6month(String callCountWorkday6month){
		this.callCountWorkday6month = callCountWorkday6month;
	}

	public String getCallCountWorkday6month(){
		return this.callCountWorkday6month;
	}

	public void setCallCountHoliday6month(String callCountHoliday6month){
		this.callCountHoliday6month = callCountHoliday6month;
	}

	public String getCallCountHoliday6month(){
		return this.callCountHoliday6month;
	}

	public void setCallTime1month(String callTime1month){
		this.callTime1month = callTime1month;
	}

	public String getCallTime1month(){
		return this.callTime1month;
	}

	public void setCallTime3month(String callTime3month){
		this.callTime3month = callTime3month;
	}

	public String getCallTime3month(){
		return this.callTime3month;
	}

	public void setCallTimeActive3month(String callTimeActive3month){
		this.callTimeActive3month = callTimeActive3month;
	}

	public String getCallTimeActive3month(){
		return this.callTimeActive3month;
	}

	public void setCallTimePassive3month(String callTimePassive3month){
		this.callTimePassive3month = callTimePassive3month;
	}

	public String getCallTimePassive3month(){
		return this.callTimePassive3month;
	}

	public void setCallTimeLateNight3month(String callTimeLateNight3month){
		this.callTimeLateNight3month = callTimeLateNight3month;
	}

	public String getCallTimeLateNight3month(){
		return this.callTimeLateNight3month;
	}

	public void setCallTimeWorkTime3month(String callTimeWorkTime3month){
		this.callTimeWorkTime3month = callTimeWorkTime3month;
	}

	public String getCallTimeWorkTime3month(){
		return this.callTimeWorkTime3month;
	}

	public void setCallTimeOffworkTime3month(String callTimeOffworkTime3month){
		this.callTimeOffworkTime3month = callTimeOffworkTime3month;
	}

	public String getCallTimeOffworkTime3month(){
		return this.callTimeOffworkTime3month;
	}

	public void setCallTime6month(String callTime6month){
		this.callTime6month = callTime6month;
	}

	public String getCallTime6month(){
		return this.callTime6month;
	}

	public void setCallTimeActive6month(String callTimeActive6month){
		this.callTimeActive6month = callTimeActive6month;
	}

	public String getCallTimeActive6month(){
		return this.callTimeActive6month;
	}

	public void setCallTimePassive6month(String callTimePassive6month){
		this.callTimePassive6month = callTimePassive6month;
	}

	public String getCallTimePassive6month(){
		return this.callTimePassive6month;
	}

	public void setCallTimeLateNight6month(String callTimeLateNight6month){
		this.callTimeLateNight6month = callTimeLateNight6month;
	}

	public String getCallTimeLateNight6month(){
		return this.callTimeLateNight6month;
	}

	public void setCallTimeWorkTime6month(String callTimeWorkTime6month){
		this.callTimeWorkTime6month = callTimeWorkTime6month;
	}

	public String getCallTimeWorkTime6month(){
		return this.callTimeWorkTime6month;
	}

	public void setCallTimeOffworkTime6month(String callTimeOffworkTime6month){
		this.callTimeOffworkTime6month = callTimeOffworkTime6month;
	}

	public String getCallTimeOffworkTime6month(){
		return this.callTimeOffworkTime6month;
	}

	public void setMsgCount1month(String msgCount1month){
		this.msgCount1month = msgCount1month;
	}

	public String getMsgCount1month(){
		return this.msgCount1month;
	}

	public void setMsgCount3month(String msgCount3month){
		this.msgCount3month = msgCount3month;
	}

	public String getMsgCount3month(){
		return this.msgCount3month;
	}

	public void setMsgCount6month(String msgCount6month){
		this.msgCount6month = msgCount6month;
	}

	public String getMsgCount6month(){
		return this.msgCount6month;
	}

	public void setIsWholeDayCall3month(String isWholeDayCall3month){
		this.isWholeDayCall3month = isWholeDayCall3month;
	}

	public String getIsWholeDayCall3month(){
		return this.isWholeDayCall3month;
	}

	public void setIsWholeDayCall6month(String isWholeDayCall6month){
		this.isWholeDayCall6month = isWholeDayCall6month;
	}

	public String getIsWholeDayCall6month(){
		return this.isWholeDayCall6month;
	}

	public void setFirstTimeCall6month(String firstTimeCall6month){
		this.firstTimeCall6month = firstTimeCall6month;
	}

	public String getFirstTimeCall6month(){
		return this.firstTimeCall6month;
	}

	public void setLastTimeCall6month(String lastTimeCall6month){
		this.lastTimeCall6month = lastTimeCall6month;
	}

	public String getLastTimeCall6month(){
		return this.lastTimeCall6month;
	}

	public void setMaxGapDayCall6month(String maxGapDayCall6month){
		this.maxGapDayCall6month = maxGapDayCall6month;
	}

	public String getMaxGapDayCall6month(){
		return this.maxGapDayCall6month;
	}

	public void setAverageGapDayCall6month(String averageGapDayCall6month){
		this.averageGapDayCall6month = averageGapDayCall6month;
	}

	public String getAverageGapDayCall6month(){
		return this.averageGapDayCall6month;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
