package com.shangyong.backend.entity.sh;


/**
 * 上海资信提示表bean
 * @author kenzhao
 * @date Tue Mar 13 17:10:35 CST 2018
 **/
public class ShCreditPrompt {

	/**主键自增id**/
	private String shCreditPromptId;

	/**申请单编号**/
	private String applicationId;

	/**项目**/
	private String promptProject;

	/**提示内容**/
	private String promptMessage;

	/**提示时间**/
	private String promptTime;

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


	public ShCreditPrompt() {
		super();
	}
	public ShCreditPrompt(String shCreditPromptId,String applicationId,String promptProject,String promptMessage,String promptTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.shCreditPromptId = shCreditPromptId;
		this.applicationId = applicationId;
		this.promptProject = promptProject;
		this.promptMessage = promptMessage;
		this.promptTime = promptTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setShCreditPromptId(String shCreditPromptId){
		this.shCreditPromptId = shCreditPromptId;
	}

	public String getShCreditPromptId(){
		return this.shCreditPromptId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setPromptProject(String promptProject){
		this.promptProject = promptProject;
	}

	public String getPromptProject(){
		return this.promptProject;
	}

	public void setPromptMessage(String promptMessage){
		this.promptMessage = promptMessage;
	}

	public String getPromptMessage(){
		return this.promptMessage;
	}

	public void setPromptTime(String promptTime){
		this.promptTime = promptTime;
	}

	public String getPromptTime(){
		return this.promptTime;
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
