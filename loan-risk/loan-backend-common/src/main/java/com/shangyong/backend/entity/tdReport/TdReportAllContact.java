package com.shangyong.backend.entity.tdReport;


/**
 * 全部联系人统计表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportAllContact {

	/**唯一标识**/
	private String allContactId;

	/**申请单编号**/
	private String applicationId;

	/**近1月通话号码数量**/
	private String contactCount1month;

	/**近3月通话号码数量**/
	private String contactCount3month;

	/**近3月主叫号码数量**/
	private String contactCountActive3month;

	/**近3月被叫号码数量**/
	private String contactCountPassive3month;

	/**近3月互通号码数量**/
	private String contactCountMutual3month;

	/**近3月通话次数>=10的号码数量**/
	private String contactCountCallCountOver103month;

	/**近6月通话号码数量**/
	private String contactCount6month;

	/**近6月主叫号码数量**/
	private String contactCountActive6month;

	/**近6月被叫号码数量**/
	private String contactCountPassive6month;

	/**近6月互通号码数量**/
	private String contactCountMutual6month;

	/**近6月通话次数>=10的号码数量**/
	private String contactCountCallCountOver106month;

	/**近6月手机通话号码数量**/
	private String contactCountMobile6month;

	/**近6月固话通话号码数量**/
	private String contactCountTelephone6month;

	/**近6月非手机固话通话号码数量**/
	private String contactCountNotMobileTelephone6month;

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

	/**近6月深夜主叫通话次数**/
	private String callCountActiveLateNight6month;

	/**近6月深夜被叫通话次数**/
	private String callCountPassiveLateNight6month;

	/**近6月工作时间通话次数**/
	private String callCountWorkTime6month;

	/**近6月非工作时间通话次数**/
	private String callCountOffworkTime6month;

	/**近6月工作日通话次数**/
	private String callCountWorkday6month;

	/**近6月节假日通话次数**/
	private String callCountHoliday6month;

	/**近6月通话时长<1分钟的通话次数**/
	private String callCountCallTimeLess1min6month;

	/**近6月通话时长1-5分钟的通话次数**/
	private String callCountCallTime1min5min6month;

	/**近6月通话时长5-10分钟的通话次数**/
	private String callCountCallTime5min10min6month;

	/**近6月通话时长>=10分钟的通话次数**/
	private String callCountCallTimeOver10min6month;

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

	/**近6月手机主叫通话时长**/
	private String callTimeActiveMobile6month;

	/**近6月手机被叫通话时长**/
	private String callTimePassiveMobile6month;

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


	public TdReportAllContact() {
		super();
	}
	public TdReportAllContact(String allContactId,String applicationId,String contactCount1month,String contactCount3month,String contactCountActive3month,String contactCountPassive3month,String contactCountMutual3month,String contactCountCallCountOver103month,String contactCount6month,String contactCountActive6month,String contactCountPassive6month,String contactCountMutual6month,String contactCountCallCountOver106month,String contactCountMobile6month,String contactCountTelephone6month,String contactCountNotMobileTelephone6month,String callCount1month,String callCount3month,String callCountActive3month,String callCountPassive3month,String callCountLateNight3month,String callCountWorkTime3month,String callCountOffworkTime3month,String callCountWorkday3month,String callCountHoliday3month,String callCount6month,String callCountActive6month,String callCountPassive6month,String callCountLateNight6month,String callCountActiveLateNight6month,String callCountPassiveLateNight6month,String callCountWorkTime6month,String callCountOffworkTime6month,String callCountWorkday6month,String callCountHoliday6month,String callCountCallTimeLess1min6month,String callCountCallTime1min5min6month,String callCountCallTime5min10min6month,String callCountCallTimeOver10min6month,String callTime1month,String callTime3month,String callTimeActive3month,String callTimePassive3month,String callTimeLateNight3month,String callTimeWorkTime3month,String callTimeOffworkTime3month,String callTime6month,String callTimeActive6month,String callTimePassive6month,String callTimeActiveMobile6month,String callTimePassiveMobile6month,String callTimeLateNight6month,String callTimeWorkTime6month,String callTimeOffworkTime6month,String msgCount1month,String msgCount3month,String msgCount6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.allContactId = allContactId;
		this.applicationId = applicationId;
		this.contactCount1month = contactCount1month;
		this.contactCount3month = contactCount3month;
		this.contactCountActive3month = contactCountActive3month;
		this.contactCountPassive3month = contactCountPassive3month;
		this.contactCountMutual3month = contactCountMutual3month;
		this.contactCountCallCountOver103month = contactCountCallCountOver103month;
		this.contactCount6month = contactCount6month;
		this.contactCountActive6month = contactCountActive6month;
		this.contactCountPassive6month = contactCountPassive6month;
		this.contactCountMutual6month = contactCountMutual6month;
		this.contactCountCallCountOver106month = contactCountCallCountOver106month;
		this.contactCountMobile6month = contactCountMobile6month;
		this.contactCountTelephone6month = contactCountTelephone6month;
		this.contactCountNotMobileTelephone6month = contactCountNotMobileTelephone6month;
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
		this.callCountActiveLateNight6month = callCountActiveLateNight6month;
		this.callCountPassiveLateNight6month = callCountPassiveLateNight6month;
		this.callCountWorkTime6month = callCountWorkTime6month;
		this.callCountOffworkTime6month = callCountOffworkTime6month;
		this.callCountWorkday6month = callCountWorkday6month;
		this.callCountHoliday6month = callCountHoliday6month;
		this.callCountCallTimeLess1min6month = callCountCallTimeLess1min6month;
		this.callCountCallTime1min5min6month = callCountCallTime1min5min6month;
		this.callCountCallTime5min10min6month = callCountCallTime5min10min6month;
		this.callCountCallTimeOver10min6month = callCountCallTimeOver10min6month;
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
		this.callTimeActiveMobile6month = callTimeActiveMobile6month;
		this.callTimePassiveMobile6month = callTimePassiveMobile6month;
		this.callTimeLateNight6month = callTimeLateNight6month;
		this.callTimeWorkTime6month = callTimeWorkTime6month;
		this.callTimeOffworkTime6month = callTimeOffworkTime6month;
		this.msgCount1month = msgCount1month;
		this.msgCount3month = msgCount3month;
		this.msgCount6month = msgCount6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setAllContactId(String allContactId){
		this.allContactId = allContactId;
	}

	public String getAllContactId(){
		return this.allContactId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setContactCount1month(String contactCount1month){
		this.contactCount1month = contactCount1month;
	}

	public String getContactCount1month(){
		return this.contactCount1month;
	}

	public void setContactCount3month(String contactCount3month){
		this.contactCount3month = contactCount3month;
	}

	public String getContactCount3month(){
		return this.contactCount3month;
	}

	public void setContactCountActive3month(String contactCountActive3month){
		this.contactCountActive3month = contactCountActive3month;
	}

	public String getContactCountActive3month(){
		return this.contactCountActive3month;
	}

	public void setContactCountPassive3month(String contactCountPassive3month){
		this.contactCountPassive3month = contactCountPassive3month;
	}

	public String getContactCountPassive3month(){
		return this.contactCountPassive3month;
	}

	public void setContactCountMutual3month(String contactCountMutual3month){
		this.contactCountMutual3month = contactCountMutual3month;
	}

	public String getContactCountMutual3month(){
		return this.contactCountMutual3month;
	}

	public void setContactCountCallCountOver103month(String contactCountCallCountOver103month){
		this.contactCountCallCountOver103month = contactCountCallCountOver103month;
	}

	public String getContactCountCallCountOver103month(){
		return this.contactCountCallCountOver103month;
	}

	public void setContactCount6month(String contactCount6month){
		this.contactCount6month = contactCount6month;
	}

	public String getContactCount6month(){
		return this.contactCount6month;
	}

	public void setContactCountActive6month(String contactCountActive6month){
		this.contactCountActive6month = contactCountActive6month;
	}

	public String getContactCountActive6month(){
		return this.contactCountActive6month;
	}

	public void setContactCountPassive6month(String contactCountPassive6month){
		this.contactCountPassive6month = contactCountPassive6month;
	}

	public String getContactCountPassive6month(){
		return this.contactCountPassive6month;
	}

	public void setContactCountMutual6month(String contactCountMutual6month){
		this.contactCountMutual6month = contactCountMutual6month;
	}

	public String getContactCountMutual6month(){
		return this.contactCountMutual6month;
	}

	public void setContactCountCallCountOver106month(String contactCountCallCountOver106month){
		this.contactCountCallCountOver106month = contactCountCallCountOver106month;
	}

	public String getContactCountCallCountOver106month(){
		return this.contactCountCallCountOver106month;
	}

	public void setContactCountMobile6month(String contactCountMobile6month){
		this.contactCountMobile6month = contactCountMobile6month;
	}

	public String getContactCountMobile6month(){
		return this.contactCountMobile6month;
	}

	public void setContactCountTelephone6month(String contactCountTelephone6month){
		this.contactCountTelephone6month = contactCountTelephone6month;
	}

	public String getContactCountTelephone6month(){
		return this.contactCountTelephone6month;
	}

	public void setContactCountNotMobileTelephone6month(String contactCountNotMobileTelephone6month){
		this.contactCountNotMobileTelephone6month = contactCountNotMobileTelephone6month;
	}

	public String getContactCountNotMobileTelephone6month(){
		return this.contactCountNotMobileTelephone6month;
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

	public void setCallCountActiveLateNight6month(String callCountActiveLateNight6month){
		this.callCountActiveLateNight6month = callCountActiveLateNight6month;
	}

	public String getCallCountActiveLateNight6month(){
		return this.callCountActiveLateNight6month;
	}

	public void setCallCountPassiveLateNight6month(String callCountPassiveLateNight6month){
		this.callCountPassiveLateNight6month = callCountPassiveLateNight6month;
	}

	public String getCallCountPassiveLateNight6month(){
		return this.callCountPassiveLateNight6month;
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

	public void setCallCountCallTimeLess1min6month(String callCountCallTimeLess1min6month){
		this.callCountCallTimeLess1min6month = callCountCallTimeLess1min6month;
	}

	public String getCallCountCallTimeLess1min6month(){
		return this.callCountCallTimeLess1min6month;
	}

	public void setCallCountCallTime1min5min6month(String callCountCallTime1min5min6month){
		this.callCountCallTime1min5min6month = callCountCallTime1min5min6month;
	}

	public String getCallCountCallTime1min5min6month(){
		return this.callCountCallTime1min5min6month;
	}

	public void setCallCountCallTime5min10min6month(String callCountCallTime5min10min6month){
		this.callCountCallTime5min10min6month = callCountCallTime5min10min6month;
	}

	public String getCallCountCallTime5min10min6month(){
		return this.callCountCallTime5min10min6month;
	}

	public void setCallCountCallTimeOver10min6month(String callCountCallTimeOver10min6month){
		this.callCountCallTimeOver10min6month = callCountCallTimeOver10min6month;
	}

	public String getCallCountCallTimeOver10min6month(){
		return this.callCountCallTimeOver10min6month;
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

	public void setCallTimeActiveMobile6month(String callTimeActiveMobile6month){
		this.callTimeActiveMobile6month = callTimeActiveMobile6month;
	}

	public String getCallTimeActiveMobile6month(){
		return this.callTimeActiveMobile6month;
	}

	public void setCallTimePassiveMobile6month(String callTimePassiveMobile6month){
		this.callTimePassiveMobile6month = callTimePassiveMobile6month;
	}

	public String getCallTimePassiveMobile6month(){
		return this.callTimePassiveMobile6month;
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
