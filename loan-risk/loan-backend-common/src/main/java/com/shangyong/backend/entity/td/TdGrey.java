package com.shangyong.backend.entity.td;


/**
 * 关注名单规则bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdGrey {

	/****/
	private String tdGreyId;

	/****/
	private String tdRiskItemsId;

	/**匹配类型**/
	private String hitTypeDisplayName;

	/**风险类型显示名**/
	private String fraudTypeDisplayName;

	/**证据时间戳形式**/
	private String evidenceTime;

	/**risk_level**/
	private String riskLevel;

	/**风险类型**/
	private String fraudType;

	/**命中关注名单的属性值**/
	private String value;

	/**描述**/
	private String description;


	public TdGrey() {
		super();
	}
	public TdGrey(String tdGreyId,String tdRiskItemsId,String hitTypeDisplayName,String fraudTypeDisplayName,String evidenceTime,String riskLevel,String fraudType,String value,String description) {
		super();
		this.tdGreyId = tdGreyId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.hitTypeDisplayName = hitTypeDisplayName;
		this.fraudTypeDisplayName = fraudTypeDisplayName;
		this.evidenceTime = evidenceTime;
		this.riskLevel = riskLevel;
		this.fraudType = fraudType;
		this.value = value;
		this.description = description;
	}
	public void setTdGreyId(String tdGreyId){
		this.tdGreyId = tdGreyId;
	}

	public String getTdGreyId(){
		return this.tdGreyId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setHitTypeDisplayName(String hitTypeDisplayName){
		this.hitTypeDisplayName = hitTypeDisplayName;
	}

	public String getHitTypeDisplayName(){
		return this.hitTypeDisplayName;
	}

	public void setFraudTypeDisplayName(String fraudTypeDisplayName){
		this.fraudTypeDisplayName = fraudTypeDisplayName;
	}

	public String getFraudTypeDisplayName(){
		return this.fraudTypeDisplayName;
	}

	public void setEvidenceTime(String evidenceTime){
		this.evidenceTime = evidenceTime;
	}

	public String getEvidenceTime(){
		return this.evidenceTime;
	}

	public void setRiskLevel(String riskLevel){
		this.riskLevel = riskLevel;
	}

	public String getRiskLevel(){
		return this.riskLevel;
	}

	public void setFraudType(String fraudType){
		this.fraudType = fraudType;
	}

	public String getFraudType(){
		return this.fraudType;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

}
