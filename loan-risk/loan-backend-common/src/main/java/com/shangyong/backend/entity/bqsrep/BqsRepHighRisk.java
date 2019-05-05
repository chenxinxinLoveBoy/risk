package com.shangyong.backend.entity.bqsrep;


/**
 * 高风险名单bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
public class BqsRepHighRisk {

	/****/
	private String bqsHighRiskId;

	/****/
	private String bqsPetitionerId;

	/**风险级别(高/中风险) **/
	private String riskGrade;

	/**击中方式（身份证加姓名/身份证/手机号）   **/
	private String riskIdType;

	/**第一级分类 **/
	private String firstType;

	/**第二级分类	**/
	private String secondType;


	public BqsRepHighRisk() {
		super();
	}
	public BqsRepHighRisk(String bqsHighRiskId,String bqsPetitionerId,String riskGrade,String riskIdType,String firstType,String secondType) {
		super();
		this.bqsHighRiskId = bqsHighRiskId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.riskGrade = riskGrade;
		this.riskIdType = riskIdType;
		this.firstType = firstType;
		this.secondType = secondType;
	}
	public void setBqsHighRiskId(String bqsHighRiskId){
		this.bqsHighRiskId = bqsHighRiskId;
	}

	public String getBqsHighRiskId(){
		return this.bqsHighRiskId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setRiskGrade(String riskGrade){
		this.riskGrade = riskGrade;
	}

	public String getRiskGrade(){
		return this.riskGrade;
	}

	public void setRiskIdType(String riskIdType){
		this.riskIdType = riskIdType;
	}

	public String getRiskIdType(){
		return this.riskIdType;
	}

	public void setFirstType(String firstType){
		this.firstType = firstType;
	}

	public String getFirstType(){
		return this.firstType;
	}

	public void setSecondType(String secondType){
		this.secondType = secondType;
	}

	public String getSecondType(){
		return this.secondType;
	}

}
