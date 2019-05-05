package com.shangyong.backend.entity.tdReport;


/**
 * 每个月联系人统计表bean
 * @author kenzhao
 * @date Fri Mar 16 15:27:16 CST 2018
 **/
public class TdReportPerMonth {

	/**唯一标识**/
	private String perMonthId;

	/**申请单编号**/
	private String applicationId;

	/**月份**/
	private String month;

	/**月通话号码数量**/
	private String contactCount;

	/**月主叫固话号码数量（新）**/
	private String contactCountActive;

	/**月主叫号码数量**/
	private String contactCountActiveTelephone;

	/**月被叫号码数量**/
	private String contactCountPassive;

	/**月互通号码数量**/
	private String contactCountMutual;

	/**月通话次数>=10的号码数量**/
	private String contactCountCallCountOver10;

	/**月通话次数**/
	private String callCount;

	/**月主叫通话次数**/
	private String callCountActive;

	/**月本地主叫通话次数（新）**/
	private String callCountActiveLocal;

	/**月漫游主叫通话次数（新）**/
	private String callCountActiveRoam;

	/**月被叫通话次数**/
	private String callCountPassive;

	/**月本地被叫通话次数（新）**/
	private String callCountPassiveLocal;

	/**月漫游被叫通话次数（新）**/
	private String callCountPassiveRoam;

	/**月深夜通话次数**/
	private String callCountLateNight;

	/**月工作时间通话次数**/
	private String callCountWorkTime;

	/**月非工作时间通话次数**/
	private String callCountOffworkTime;

	/**月通话时长<1分钟的通话次数**/
	private String callCountCallTimeLess1min;

	/**月通话时长1-5分钟的通话次数**/
	private String callCountCallTime1min5min;

	/**月通话时长5-10分钟的通话次数**/
	private String callCountCallTime5min10min;

	/**月通话时长>=10分钟的通话次数**/
	private String callCountCallTimeOver10min;

	/**月通话时长**/
	private String callTime;

	/**月主叫通话时长**/
	private String callTimeActive;

	/**月本地主叫通话时长（新）**/
	private String callTimeActiveLocal;

	/**月漫游主叫通话时长（新）**/
	private String callTimeActiveRoam;

	/**月被叫通话时长**/
	private String callTimePassive;

	/**月本地被叫通话时长（新）**/
	private String callTimePassiveLocal;

	/**月漫游被叫通话时长（新）**/
	private String callTimePassiveRoam;

	/**月深夜通话时长**/
	private String callTimeLateNight;

	/**月工作时间通话时长**/
	private String callTimeWorkTime;

	/**月非工作时间通话时长**/
	private String callTimeOffworkTime;

	/**月短信次数**/
	private String msgCount;

	/**月发送手机短信数量（新）**/
	private String msgCountSendMobile;

	/**月接受手机短信数量（新）**/
	private String msgCountReceiveMobile;

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


	public TdReportPerMonth() {
		super();
	}
	public TdReportPerMonth(String perMonthId,String applicationId,String month,String contactCount,String contactCountActive,String contactCountActiveTelephone,String contactCountPassive,String contactCountMutual,String contactCountCallCountOver10,String callCount,String callCountActive,String callCountActiveLocal,String callCountActiveRoam,String callCountPassive,String callCountPassiveLocal,String callCountPassiveRoam,String callCountLateNight,String callCountWorkTime,String callCountOffworkTime,String callCountCallTimeLess1min,String callCountCallTime1min5min,String callCountCallTime5min10min,String callCountCallTimeOver10min,String callTime,String callTimeActive,String callTimeActiveLocal,String callTimeActiveRoam,String callTimePassive,String callTimePassiveLocal,String callTimePassiveRoam,String callTimeLateNight,String callTimeWorkTime,String callTimeOffworkTime,String msgCount,String msgCountSendMobile,String msgCountReceiveMobile,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.perMonthId = perMonthId;
		this.applicationId = applicationId;
		this.month = month;
		this.contactCount = contactCount;
		this.contactCountActive = contactCountActive;
		this.contactCountActiveTelephone = contactCountActiveTelephone;
		this.contactCountPassive = contactCountPassive;
		this.contactCountMutual = contactCountMutual;
		this.contactCountCallCountOver10 = contactCountCallCountOver10;
		this.callCount = callCount;
		this.callCountActive = callCountActive;
		this.callCountActiveLocal = callCountActiveLocal;
		this.callCountActiveRoam = callCountActiveRoam;
		this.callCountPassive = callCountPassive;
		this.callCountPassiveLocal = callCountPassiveLocal;
		this.callCountPassiveRoam = callCountPassiveRoam;
		this.callCountLateNight = callCountLateNight;
		this.callCountWorkTime = callCountWorkTime;
		this.callCountOffworkTime = callCountOffworkTime;
		this.callCountCallTimeLess1min = callCountCallTimeLess1min;
		this.callCountCallTime1min5min = callCountCallTime1min5min;
		this.callCountCallTime5min10min = callCountCallTime5min10min;
		this.callCountCallTimeOver10min = callCountCallTimeOver10min;
		this.callTime = callTime;
		this.callTimeActive = callTimeActive;
		this.callTimeActiveLocal = callTimeActiveLocal;
		this.callTimeActiveRoam = callTimeActiveRoam;
		this.callTimePassive = callTimePassive;
		this.callTimePassiveLocal = callTimePassiveLocal;
		this.callTimePassiveRoam = callTimePassiveRoam;
		this.callTimeLateNight = callTimeLateNight;
		this.callTimeWorkTime = callTimeWorkTime;
		this.callTimeOffworkTime = callTimeOffworkTime;
		this.msgCount = msgCount;
		this.msgCountSendMobile = msgCountSendMobile;
		this.msgCountReceiveMobile = msgCountReceiveMobile;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setPerMonthId(String perMonthId){
		this.perMonthId = perMonthId;
	}

	public String getPerMonthId(){
		return this.perMonthId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return this.month;
	}

	public void setContactCount(String contactCount){
		this.contactCount = contactCount;
	}

	public String getContactCount(){
		return this.contactCount;
	}

	public void setContactCountActive(String contactCountActive){
		this.contactCountActive = contactCountActive;
	}

	public String getContactCountActive(){
		return this.contactCountActive;
	}

	public void setContactCountActiveTelephone(String contactCountActiveTelephone){
		this.contactCountActiveTelephone = contactCountActiveTelephone;
	}

	public String getContactCountActiveTelephone(){
		return this.contactCountActiveTelephone;
	}

	public void setContactCountPassive(String contactCountPassive){
		this.contactCountPassive = contactCountPassive;
	}

	public String getContactCountPassive(){
		return this.contactCountPassive;
	}

	public void setContactCountMutual(String contactCountMutual){
		this.contactCountMutual = contactCountMutual;
	}

	public String getContactCountMutual(){
		return this.contactCountMutual;
	}

	public void setContactCountCallCountOver10(String contactCountCallCountOver10){
		this.contactCountCallCountOver10 = contactCountCallCountOver10;
	}

	public String getContactCountCallCountOver10(){
		return this.contactCountCallCountOver10;
	}

	public void setCallCount(String callCount){
		this.callCount = callCount;
	}

	public String getCallCount(){
		return this.callCount;
	}

	public void setCallCountActive(String callCountActive){
		this.callCountActive = callCountActive;
	}

	public String getCallCountActive(){
		return this.callCountActive;
	}

	public void setCallCountActiveLocal(String callCountActiveLocal){
		this.callCountActiveLocal = callCountActiveLocal;
	}

	public String getCallCountActiveLocal(){
		return this.callCountActiveLocal;
	}

	public void setCallCountActiveRoam(String callCountActiveRoam){
		this.callCountActiveRoam = callCountActiveRoam;
	}

	public String getCallCountActiveRoam(){
		return this.callCountActiveRoam;
	}

	public void setCallCountPassive(String callCountPassive){
		this.callCountPassive = callCountPassive;
	}

	public String getCallCountPassive(){
		return this.callCountPassive;
	}

	public void setCallCountPassiveLocal(String callCountPassiveLocal){
		this.callCountPassiveLocal = callCountPassiveLocal;
	}

	public String getCallCountPassiveLocal(){
		return this.callCountPassiveLocal;
	}

	public void setCallCountPassiveRoam(String callCountPassiveRoam){
		this.callCountPassiveRoam = callCountPassiveRoam;
	}

	public String getCallCountPassiveRoam(){
		return this.callCountPassiveRoam;
	}

	public void setCallCountLateNight(String callCountLateNight){
		this.callCountLateNight = callCountLateNight;
	}

	public String getCallCountLateNight(){
		return this.callCountLateNight;
	}

	public void setCallCountWorkTime(String callCountWorkTime){
		this.callCountWorkTime = callCountWorkTime;
	}

	public String getCallCountWorkTime(){
		return this.callCountWorkTime;
	}

	public void setCallCountOffworkTime(String callCountOffworkTime){
		this.callCountOffworkTime = callCountOffworkTime;
	}

	public String getCallCountOffworkTime(){
		return this.callCountOffworkTime;
	}

	public void setCallCountCallTimeLess1min(String callCountCallTimeLess1min){
		this.callCountCallTimeLess1min = callCountCallTimeLess1min;
	}

	public String getCallCountCallTimeLess1min(){
		return this.callCountCallTimeLess1min;
	}

	public void setCallCountCallTime1min5min(String callCountCallTime1min5min){
		this.callCountCallTime1min5min = callCountCallTime1min5min;
	}

	public String getCallCountCallTime1min5min(){
		return this.callCountCallTime1min5min;
	}

	public void setCallCountCallTime5min10min(String callCountCallTime5min10min){
		this.callCountCallTime5min10min = callCountCallTime5min10min;
	}

	public String getCallCountCallTime5min10min(){
		return this.callCountCallTime5min10min;
	}

	public void setCallCountCallTimeOver10min(String callCountCallTimeOver10min){
		this.callCountCallTimeOver10min = callCountCallTimeOver10min;
	}

	public String getCallCountCallTimeOver10min(){
		return this.callCountCallTimeOver10min;
	}

	public void setCallTime(String callTime){
		this.callTime = callTime;
	}

	public String getCallTime(){
		return this.callTime;
	}

	public void setCallTimeActive(String callTimeActive){
		this.callTimeActive = callTimeActive;
	}

	public String getCallTimeActive(){
		return this.callTimeActive;
	}

	public void setCallTimeActiveLocal(String callTimeActiveLocal){
		this.callTimeActiveLocal = callTimeActiveLocal;
	}

	public String getCallTimeActiveLocal(){
		return this.callTimeActiveLocal;
	}

	public void setCallTimeActiveRoam(String callTimeActiveRoam){
		this.callTimeActiveRoam = callTimeActiveRoam;
	}

	public String getCallTimeActiveRoam(){
		return this.callTimeActiveRoam;
	}

	public void setCallTimePassive(String callTimePassive){
		this.callTimePassive = callTimePassive;
	}

	public String getCallTimePassive(){
		return this.callTimePassive;
	}

	public void setCallTimePassiveLocal(String callTimePassiveLocal){
		this.callTimePassiveLocal = callTimePassiveLocal;
	}

	public String getCallTimePassiveLocal(){
		return this.callTimePassiveLocal;
	}

	public void setCallTimePassiveRoam(String callTimePassiveRoam){
		this.callTimePassiveRoam = callTimePassiveRoam;
	}

	public String getCallTimePassiveRoam(){
		return this.callTimePassiveRoam;
	}

	public void setCallTimeLateNight(String callTimeLateNight){
		this.callTimeLateNight = callTimeLateNight;
	}

	public String getCallTimeLateNight(){
		return this.callTimeLateNight;
	}

	public void setCallTimeWorkTime(String callTimeWorkTime){
		this.callTimeWorkTime = callTimeWorkTime;
	}

	public String getCallTimeWorkTime(){
		return this.callTimeWorkTime;
	}

	public void setCallTimeOffworkTime(String callTimeOffworkTime){
		this.callTimeOffworkTime = callTimeOffworkTime;
	}

	public String getCallTimeOffworkTime(){
		return this.callTimeOffworkTime;
	}

	public void setMsgCount(String msgCount){
		this.msgCount = msgCount;
	}

	public String getMsgCount(){
		return this.msgCount;
	}

	public void setMsgCountSendMobile(String msgCountSendMobile){
		this.msgCountSendMobile = msgCountSendMobile;
	}

	public String getMsgCountSendMobile(){
		return this.msgCountSendMobile;
	}

	public void setMsgCountReceiveMobile(String msgCountReceiveMobile){
		this.msgCountReceiveMobile = msgCountReceiveMobile;
	}

	public String getMsgCountReceiveMobile(){
		return this.msgCountReceiveMobile;
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
