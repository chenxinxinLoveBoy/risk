package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告每个月数据完整性信息bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportCompletenessMonth {

	/**报告的唯一标识**/
	private String completenessMonthId;

	/**申请单编号**/
	private String applicationId;

	/**月份**/
	private String month;

	/**月通话数据是否完整**/
	private String isCallDataComplete;

	/**月短信数据是否完整**/
	private String isMsgDataComplete;

	/**月消费数据是否完整**/
	private String isConsumeDataComplete;

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


	public TdReportCompletenessMonth() {
		super();
	}
	public TdReportCompletenessMonth(String completenessMonthId,String applicationId,String month,String isCallDataComplete,String isMsgDataComplete,String isConsumeDataComplete,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.completenessMonthId = completenessMonthId;
		this.applicationId = applicationId;
		this.month = month;
		this.isCallDataComplete = isCallDataComplete;
		this.isMsgDataComplete = isMsgDataComplete;
		this.isConsumeDataComplete = isConsumeDataComplete;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCompletenessMonthId(String completenessMonthId){
		this.completenessMonthId = completenessMonthId;
	}

	public String getCompletenessMonthId(){
		return this.completenessMonthId;
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

	public void setIsCallDataComplete(String isCallDataComplete){
		this.isCallDataComplete = isCallDataComplete;
	}

	public String getIsCallDataComplete(){
		return this.isCallDataComplete;
	}

	public void setIsMsgDataComplete(String isMsgDataComplete){
		this.isMsgDataComplete = isMsgDataComplete;
	}

	public String getIsMsgDataComplete(){
		return this.isMsgDataComplete;
	}

	public void setIsConsumeDataComplete(String isConsumeDataComplete){
		this.isConsumeDataComplete = isConsumeDataComplete;
	}

	public String getIsConsumeDataComplete(){
		return this.isConsumeDataComplete;
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
