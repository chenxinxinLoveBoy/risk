package com.shangyong.backend.entity.bqsrep;


/**
 * 用户行为，活跃程度，通话行为，特殊通话检测bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
public class BqsRepCrossValidation {

	/****/
	private String bqsCrossValidationId;

	/****/
	private String bqsPetitionerId;

	/**证据**/
	private String evidence;

	/**检查项目**/
	private String inspectionItems;

	/**结果 **/
	private String result;

	/**通用表类型 参考api**/
	private String type;


	public BqsRepCrossValidation() {
		super();
	}
	public BqsRepCrossValidation(String bqsCrossValidationId,String bqsPetitionerId,String evidence,String inspectionItems,String result,String type) {
		super();
		this.bqsCrossValidationId = bqsCrossValidationId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.evidence = evidence;
		this.inspectionItems = inspectionItems;
		this.result = result;
		this.type = type;
	}
	public void setBqsCrossValidationId(String bqsCrossValidationId){
		this.bqsCrossValidationId = bqsCrossValidationId;
	}

	public String getBqsCrossValidationId(){
		return this.bqsCrossValidationId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setEvidence(String evidence){
		this.evidence = evidence;
	}

	public String getEvidence(){
		return this.evidence;
	}

	public void setInspectionItems(String inspectionItems){
		this.inspectionItems = inspectionItems;
	}

	public String getInspectionItems(){
		return this.inspectionItems;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return this.result;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

}
