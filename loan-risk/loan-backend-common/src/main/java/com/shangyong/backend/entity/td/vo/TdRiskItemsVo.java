package com.shangyong.backend.entity.td.vo;

import java.util.List;

import com.shangyong.backend.entity.td.TdBlack;
import com.shangyong.backend.entity.td.TdCustomDetail;
import com.shangyong.backend.entity.td.TdDiscredit;
import com.shangyong.backend.entity.td.TdGrey;
import com.shangyong.backend.entity.td.TdSuspectTeam;

/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdRiskItemsVo {

	/****/
	private String tdRiskItemsId;

	/****/
	private String tdAntiFraudId;

	/**规则编号**/
	private String ruleId;

	/**风险分数**/
	private String score;

	/**决策结果**/
	private String decision;

	/**风险名称**/
	private String riskName;

	/**风险类型**/
	private String riskType;
	
	
	/**风险群体风险详情表**/
	private List<TdSuspectTeam> tdSuspectTeams;
	
	/**关注名单规则**/
	private List<TdGrey> tdGreys;
	
	/**信贷逾期统计**/
	private List<TdDiscredit> tdDiscredits;
	
	/**自定义列表规则**/
	private List<TdCustomDetail> tdCustomDetails;
	
	/**多平台风险详情**/
	private List<TdPlatformVo> tdPlatformVos;
	
	/**风险名单规则**/
	private List<TdBlack> tdBlacks;

	public String getTdRiskItemsId() {
		return tdRiskItemsId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId) {
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdAntiFraudId() {
		return tdAntiFraudId;
	}

	public void setTdAntiFraudId(String tdAntiFraudId) {
		this.tdAntiFraudId = tdAntiFraudId;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public List<TdSuspectTeam> getTdSuspectTeams() {
		return tdSuspectTeams;
	}

	public void setTdSuspectTeams(List<TdSuspectTeam> tdSuspectTeams) {
		this.tdSuspectTeams = tdSuspectTeams;
	}

	public List<TdGrey> getTdGreys() {
		return tdGreys;
	}

	public void setTdGreys(List<TdGrey> tdGreys) {
		this.tdGreys = tdGreys;
	}

	public List<TdDiscredit> getTdDiscredits() {
		return tdDiscredits;
	}

	public void setTdDiscredits(List<TdDiscredit> tdDiscredits) {
		this.tdDiscredits = tdDiscredits;
	}

	public List<TdCustomDetail> getTdCustomDetails() {
		return tdCustomDetails;
	}

	public void setTdCustomDetails(List<TdCustomDetail> tdCustomDetails) {
		this.tdCustomDetails = tdCustomDetails;
	}

	public List<TdPlatformVo> getTdPlatformVos() {
		return tdPlatformVos;
	}

	public void setTdPlatformVos(List<TdPlatformVo> tdPlatformVos) {
		this.tdPlatformVos = tdPlatformVos;
	}

	public List<TdBlack> getTdBlacks() {
		return tdBlacks;
	}

	public void setTdBlacks(List<TdBlack> tdBlacks) {
		this.tdBlacks = tdBlacks;
	}

	@Override
	public String toString() {
		return "TdRiskItemsVo [tdRiskItemsId=" + tdRiskItemsId + ", tdAntiFraudId=" + tdAntiFraudId + ", ruleId="
				+ ruleId + ", score=" + score + ", decision=" + decision + ", riskName=" + riskName + ", riskType="
				+ riskType + ", tdSuspectTeams=" + tdSuspectTeams + ", tdGreys=" + tdGreys + ", tdDiscredits="
				+ tdDiscredits + ", tdCustomDetails=" + tdCustomDetails + ", tdPlatformVos=" + tdPlatformVos
				+ ", tdBlacks=" + tdBlacks + "]";
	}

	public TdRiskItemsVo(String tdRiskItemsId, String tdAntiFraudId, String ruleId, String score, String decision,
			String riskName, String riskType, List<TdSuspectTeam> tdSuspectTeams, List<TdGrey> tdGreys,
			List<TdDiscredit> tdDiscredits, List<TdCustomDetail> tdCustomDetails, List<TdPlatformVo> tdPlatformVos,
			List<TdBlack> tdBlacks) {
		super();
		this.tdRiskItemsId = tdRiskItemsId;
		this.tdAntiFraudId = tdAntiFraudId;
		this.ruleId = ruleId;
		this.score = score;
		this.decision = decision;
		this.riskName = riskName;
		this.riskType = riskType;
		this.tdSuspectTeams = tdSuspectTeams;
		this.tdGreys = tdGreys;
		this.tdDiscredits = tdDiscredits;
		this.tdCustomDetails = tdCustomDetails;
		this.tdPlatformVos = tdPlatformVos;
		this.tdBlacks = tdBlacks;
	}

	public TdRiskItemsVo() {
		super();
	}

}
