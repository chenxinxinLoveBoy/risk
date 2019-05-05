package com.shangyong.backend.entity.td;


/**
 * 模糊证据库规则bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdFuzzyBlack {

	/****/
	private String tdFuzzyBlackId;

	/****/
	private String tdRiskItemsId;

	/**规则描述**/
	private String description;

	/**风险类型显示名**/
	private String fraudTypeDisplayName;

	/**模糊身份证**/
	private String fuzzyIdNumber;

	/**风险类型**/
	private String fraudType;

	/**模糊姓名**/
	private String fuzzyName;


	public TdFuzzyBlack() {
		super();
	}
	public TdFuzzyBlack(String tdFuzzyBlackId,String tdRiskItemsId,String description,String fraudTypeDisplayName,String fuzzyIdNumber,String fraudType,String fuzzyName) {
		super();
		this.tdFuzzyBlackId = tdFuzzyBlackId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.description = description;
		this.fraudTypeDisplayName = fraudTypeDisplayName;
		this.fuzzyIdNumber = fuzzyIdNumber;
		this.fraudType = fraudType;
		this.fuzzyName = fuzzyName;
	}
	public void setTdFuzzyBlackId(String tdFuzzyBlackId){
		this.tdFuzzyBlackId = tdFuzzyBlackId;
	}

	public String getTdFuzzyBlackId(){
		return this.tdFuzzyBlackId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setFraudTypeDisplayName(String fraudTypeDisplayName){
		this.fraudTypeDisplayName = fraudTypeDisplayName;
	}

	public String getFraudTypeDisplayName(){
		return this.fraudTypeDisplayName;
	}

	public void setFuzzyIdNumber(String fuzzyIdNumber){
		this.fuzzyIdNumber = fuzzyIdNumber;
	}

	public String getFuzzyIdNumber(){
		return this.fuzzyIdNumber;
	}

	public void setFraudType(String fraudType){
		this.fraudType = fraudType;
	}

	public String getFraudType(){
		return this.fraudType;
	}

	public void setFuzzyName(String fuzzyName){
		this.fuzzyName = fuzzyName;
	}

	public String getFuzzyName(){
		return this.fuzzyName;
	}

}
