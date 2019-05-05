package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告静默活跃时间记录信息表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportContinueSilence {

	/**报告的唯一标识**/
	private String continueSilenceId;

	/**申请单编号**/
	private String applicationId;

	/**记录**/
	private String continueSilenceType;

	/**开始日期**/
	private String startDate;

	/**结束日期**/
	private String endDate;

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


	public TdReportContinueSilence() {
		super();
	}
	public TdReportContinueSilence(String continueSilenceId,String applicationId,String continueSilenceType,String startDate,String endDate,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.continueSilenceId = continueSilenceId;
		this.applicationId = applicationId;
		this.continueSilenceType = continueSilenceType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setContinueSilenceId(String continueSilenceId){
		this.continueSilenceId = continueSilenceId;
	}

	public String getContinueSilenceId(){
		return this.continueSilenceId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setContinueSilenceType(String continueSilenceType){
		this.continueSilenceType = continueSilenceType;
	}

	public String getContinueSilenceType(){
		return this.continueSilenceType;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return this.startDate;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return this.endDate;
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
