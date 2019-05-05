package com.shangyong.backend.entity.tdReport;


/**
 * 金融机构联系人统计表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportFinanceContact {

	/**唯一标识**/
	private String financeContactId;

	/**申请单编号**/
	private String applicationId;

	/**机构分类**/
	private String contactType;

	/**近1月通话号码数量**/
	private String contactCount1month;

	/**近3月通话号码数量**/
	private String contactCount3month;

	/**近6月通话号码数量**/
	private String contactCount6month;

	/**近1月通话次数**/
	private String callCount1month;

	/**近3月通话次数**/
	private String callCount3month;

	/**近3月主叫通话次数**/
	private String callCountActive3month;

	/**近3月被叫通话次数**/
	private String callCountPassive3month;

	/**近6月通话次数**/
	private String callCount6month;

	/**近6月主叫通话次数**/
	private String callCountActive6month;

	/**近6月被叫通话次数**/
	private String callCountPassive6month;

	/**近1月通话时长**/
	private String callTime1month;

	/**近3月通话时长**/
	private String callTime3month;

	/**近3月主叫通话时长**/
	private String callTimeActive3month;

	/**近3月被叫通话时长**/
	private String callTimePassive3month;

	/**近6月通话时长**/
	private String callTime6month;

	/**近6月主叫通话时长**/
	private String callTimeActive6month;

	/**近6月被叫通话时长**/
	private String callTimePassive6month;

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


	public TdReportFinanceContact() {
		super();
	}
	public TdReportFinanceContact(String financeContactId,String applicationId,String contactType,String contactCount1month,String contactCount3month,String contactCount6month,String callCount1month,String callCount3month,String callCountActive3month,String callCountPassive3month,String callCount6month,String callCountActive6month,String callCountPassive6month,String callTime1month,String callTime3month,String callTimeActive3month,String callTimePassive3month,String callTime6month,String callTimeActive6month,String callTimePassive6month,String msgCount1month,String msgCount3month,String msgCount6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.financeContactId = financeContactId;
		this.applicationId = applicationId;
		this.contactType = contactType;
		this.contactCount1month = contactCount1month;
		this.contactCount3month = contactCount3month;
		this.contactCount6month = contactCount6month;
		this.callCount1month = callCount1month;
		this.callCount3month = callCount3month;
		this.callCountActive3month = callCountActive3month;
		this.callCountPassive3month = callCountPassive3month;
		this.callCount6month = callCount6month;
		this.callCountActive6month = callCountActive6month;
		this.callCountPassive6month = callCountPassive6month;
		this.callTime1month = callTime1month;
		this.callTime3month = callTime3month;
		this.callTimeActive3month = callTimeActive3month;
		this.callTimePassive3month = callTimePassive3month;
		this.callTime6month = callTime6month;
		this.callTimeActive6month = callTimeActive6month;
		this.callTimePassive6month = callTimePassive6month;
		this.msgCount1month = msgCount1month;
		this.msgCount3month = msgCount3month;
		this.msgCount6month = msgCount6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setFinanceContactId(String financeContactId){
		this.financeContactId = financeContactId;
	}

	public String getFinanceContactId(){
		return this.financeContactId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setContactType(String contactType){
		this.contactType = contactType;
	}

	public String getContactType(){
		return this.contactType;
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

	public void setContactCount6month(String contactCount6month){
		this.contactCount6month = contactCount6month;
	}

	public String getContactCount6month(){
		return this.contactCount6month;
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
