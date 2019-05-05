package com.shangyong.backend.entity;


/**
 * 欺诈总得分bean
 * @author kenzhao
 * @date Sun Apr 08 17:42:44 CST 2018
 **/
public class ScFraudScore {

	/**报告的唯一标识**/
	private String scFraudScoreId;

	/**申请单编号**/
	private String applicationId;

	/**欺诈总得分**/
	private String score;

	/**欺诈模板Id**/
	private String fraudTplId;

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
	
	/**用户编号Id**/
	private String customerId;


	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public ScFraudScore() {
		super();
	}
	public ScFraudScore(String scFraudScoreId, String applicationId, String score, String fraudTplId, String createTime,
			String createMan, String modifyTime, String modifyMan, String remark, String customerId) {
		super();
		this.scFraudScoreId = scFraudScoreId;
		this.applicationId = applicationId;
		this.score = score;
		this.fraudTplId = fraudTplId;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.customerId = customerId;
	}
	public void setScFraudScoreId(String scFraudScoreId){
		this.scFraudScoreId = scFraudScoreId;
	}

	public String getScFraudScoreId(){
		return this.scFraudScoreId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setScore(String score){
		this.score = score;
	}

	public String getScore(){
		return this.score;
	}

	public void setFraudTplId(String fraudTplId){
		this.fraudTplId = fraudTplId;
	}

	public String getFraudTplId(){
		return this.fraudTplId;
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
