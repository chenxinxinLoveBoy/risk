package com.shangyong.backend.entity;


/**
 * 借款申请客户评分明细表bean
 * @author xxj
 * @date Sat Jun 17 17:43:29 CST 2017
 **/
public class ScScoreDetail {

	/**借款申请客户评分明细编号**/
	private String scoreDetailId;

	/**申请单编号**/
	private String applicationId;

	/**评分卡大类序号**/
	private String scoreBigId;

	/**评分卡小类序号**/
	private String scoreSmallId;

	/**小类评分项规则名称**/
	private String scoreRuleName;

	/**分数**/
	private String score;

	/**创建时间**/
	private String createTime;

	/**备注**/
	private String remark;


	public ScScoreDetail() {
		super();
	}
	public ScScoreDetail(String scoreDetailId,String applicationId,String scoreBigId,String scoreSmallId,String scoreRuleName,String score,String createTime,String remark) {
		super();
		this.scoreDetailId = scoreDetailId;
		this.applicationId = applicationId;
		this.scoreBigId = scoreBigId;
		this.scoreSmallId = scoreSmallId;
		this.scoreRuleName = scoreRuleName;
		this.score = score;
		this.createTime = createTime;
		this.remark = remark;
	}
	public void setScoreDetailId(String scoreDetailId){
		this.scoreDetailId = scoreDetailId;
	}

	public String getScoreDetailId(){
		return this.scoreDetailId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setScoreBigId(String scoreBigId){
		this.scoreBigId = scoreBigId;
	}

	public String getScoreBigId(){
		return this.scoreBigId;
	}

	public void setScoreSmallId(String scoreSmallId){
		this.scoreSmallId = scoreSmallId;
	}

	public String getScoreSmallId(){
		return this.scoreSmallId;
	}

	public void setScoreRuleName(String scoreRuleName){
		this.scoreRuleName = scoreRuleName;
	}

	public String getScoreRuleName(){
		return this.scoreRuleName;
	}

	public void setScore(String score){
		this.score = score;
	}

	public String getScore(){
		return this.score;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
