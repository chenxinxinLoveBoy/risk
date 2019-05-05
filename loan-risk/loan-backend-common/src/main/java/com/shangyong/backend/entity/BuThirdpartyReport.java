package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 客户借款申请扩展表bean
 * @author xiajiyun
 * @date Thu Jul 20 23:24:12 CST 2017
 **/
public class BuThirdpartyReport extends BaseBo{

	/**客户借款申请扩展表编号**/
	private String thirdpartyReportId;

	/**申请单编号**/
	private String buApplicationId;

	/**数据存储类型 （01001- 芝麻信用评分  02001- 芝麻信用欺诈清单  03001-同盾贷前审核 04001-聚信立蜜蜂  05001- 聚信立蜜罐 06001-魔盒运营商  06002-魔盒学信  06003-魔盒电商 06004-魔盒社保 06005-魔盒公积金  10001-禁止项模板业务编号、10002-信用评分小类模板业务编号、10003-欺诈评分模板业务编号）**/
	private String taskType;

	/**任务编号**/
	private String taskId;

	/**备用1**/
	private String backup1;

	/**创建人**/
	private String createMan;

	/**修改人**/
	private String updateMan;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String updateTime;

	/**备注**/
	private String remark;
	
	/**响应josn报文存储地址**/
	private String josnStoragePath;
	
	/**数据存储类型中文名称**/
	private String taskName;

	public String getJosnStoragePath() {
		return josnStoragePath;
	}
	public void setJosnStoragePath(String josnStoragePath) {
		this.josnStoragePath = josnStoragePath;
	}
	public BuThirdpartyReport() {
		super();
	}
	public BuThirdpartyReport(String thirdpartyReportId,String buApplicationId,String taskType,String taskId,String backup1,String createMan,String updateMan,String createTime,String updateTime,String remark,String josnStoragePath) {
		super();
		this.thirdpartyReportId = thirdpartyReportId;
		this.buApplicationId = buApplicationId;
		this.taskType = taskType;
		this.taskId = taskId;
		this.backup1 = backup1;
		this.createMan = createMan;
		this.updateMan = updateMan;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remark = remark;
		this.josnStoragePath = josnStoragePath;
	}
	public void setThirdpartyReportId(String thirdpartyReportId){
		this.thirdpartyReportId = thirdpartyReportId;
	}

	public String getThirdpartyReportId(){
		return this.thirdpartyReportId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setTaskType(String taskType){
		this.taskType = taskType;
	}

	public String getTaskType(){
		return this.taskType;
	}

	public void setTaskId(String taskId){
		this.taskId = taskId;
	}

	public String getTaskId(){
		return this.taskId;
	}

	public void setBackup1(String backup1){
		this.backup1 = backup1;
	}

	public String getBackup1(){
		return this.backup1;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setUpdateMan(String updateMan){
		this.updateMan = updateMan;
	}

	public String getUpdateMan(){
		return this.updateMan;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	public String getUpdateTime(){
		return this.updateTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
	
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@Override
	public String toString() {
		return "BuThirdpartyReport [thirdpartyReportId=" + thirdpartyReportId + ", buApplicationId=" + buApplicationId
				+ ", taskType=" + taskType + ", taskId=" + taskId + ", backup1=" + backup1 + ", createMan=" + createMan
				+ ", updateMan=" + updateMan + ", createTime=" + createTime + ", updateTime=" + updateTime + ", remark="
				+ remark + ", josnStoragePath=" + josnStoragePath + "]";
	}
	
}
