package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 评分规则明细表统计类
 * 
 * @author xuke
 *
 */
public class ScoreDetailStatistics extends BaseBo {
	private Integer count;// 评分规则名称出现次数
	private String name;// 评分规则名称
	private String beginTime;// 查询用的开始时间
	private String endTime;// 查询用的结束时间
	private String scoreSmallCode;// 评分小类编号
	private String scoreName;// 评分大类名称
	private String scoreBigCode;// 评分大类编号
	private Integer value;// 评分规则名称出现次数(value字段用于饼状图)
	private String scoreSmallId;// 评分小类序号
	private String scoreTplId;// 模板id
	private String tplName;// 模板名称

	public ScoreDetailStatistics() {
		super();
	}

	public ScoreDetailStatistics(Integer count, String name, String beginTime, String endTime, String scoreSmallCode,
			String scoreName, String scoreBigCode, Integer value, String scoreSmallId, String scoreTplId,
			String tplName) {
		super();
		this.count = count;
		this.name = name;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.scoreSmallCode = scoreSmallCode;
		this.scoreName = scoreName;
		this.scoreBigCode = scoreBigCode;
		this.value = value;
		this.scoreSmallId = scoreSmallId;
		this.scoreTplId = scoreTplId;
		this.tplName = tplName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getScoreSmallCode() {
		return scoreSmallCode;
	}

	public void setScoreSmallCode(String scoreSmallCode) {
		this.scoreSmallCode = scoreSmallCode;
	}

	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}

	public String getScoreBigCode() {
		return scoreBigCode;
	}

	public void setScoreBigCode(String scoreBigCode) {
		this.scoreBigCode = scoreBigCode;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getScoreSmallId() {
		return scoreSmallId;
	}

	public void setScoreSmallId(String scoreSmallId) {
		this.scoreSmallId = scoreSmallId;
	}

	public String getScoreTplId() {
		return scoreTplId;
	}

	public void setScoreTplId(String scoreTplId) {
		this.scoreTplId = scoreTplId;
	}

	public String getTplName() {
		return tplName;
	}

	public void setTplName(String tplName) {
		this.tplName = tplName;
	}

}