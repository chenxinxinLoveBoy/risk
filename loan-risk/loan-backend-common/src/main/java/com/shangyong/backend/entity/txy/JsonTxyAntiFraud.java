package com.shangyong.backend.entity.txy;

import java.io.Serializable;
import java.util.List;

/**
 * 腾讯反欺诈表bean
 * @author mingke.shi
 * @date Sun Dec 10 17:19:38 CST 2017
 **/
public class JsonTxyAntiFraud{
	/**主键id**/
	private String txyAntiFraudId;

	/**申请单编号**/
	private String buApplicationId;

	/**公共错误码，0 表示成功，其他值表示失败**/
	private String code;

	/**业务侧错误码。成功时返回 Success，错误时返回具体业务错误原因**/
	private String codeDesc;

	/**模块错误信息描述，与接口相关**/
	private String message;

	/**表示该条记录中的身份证能否查到,1 为能查到，-1 为查不到**/
	private String idFound;

	/**表示该条记录能否查到,1 为能查到，-1 为查不到**/
	private String found;

	/**0-100：欺诈分值。值越高欺诈可能性越大；-1：查询不到数据**/
	private String riskScore;

	/**用户请求Ip**/
	private String userIp;

	/**是否命中,1命中0未命中**/
	private String state;

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
	
	/** 风险项**/
	private List<RiskInfo> riskInfo;

	public String getTxyAntiFraudId() {
		return txyAntiFraudId;
	}

	public void setTxyAntiFraudId(String txyAntiFraudId) {
		this.txyAntiFraudId = txyAntiFraudId;
	}

	public String getBuApplicationId() {
		return buApplicationId;
	}

	public void setBuApplicationId(String buApplicationId) {
		this.buApplicationId = buApplicationId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIdFound() {
		return idFound;
	}

	public void setIdFound(String idFound) {
		this.idFound = idFound;
	}

	public String getFound() {
		return found;
	}

	public void setFound(String found) {
		this.found = found;
	}

	public String getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<RiskInfo> getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(List<RiskInfo> riskInfo) {
		this.riskInfo = riskInfo;
	}

	@Override
	public String toString() {
		return "JsonTxyAntiFraud [txyAntiFraudId=" + txyAntiFraudId + ", buApplicationId=" + buApplicationId + ", code="
				+ code + ", codeDesc=" + codeDesc + ", message=" + message + ", idFound=" + idFound + ", found=" + found
				+ ", riskScore=" + riskScore + ", userIp=" + userIp + ", state=" + state + ", createTime=" + createTime
				+ ", createMan=" + createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", remark="
				+ remark + ", riskInfo=" + riskInfo + "]";
	}

	public JsonTxyAntiFraud(String txyAntiFraudId, String buApplicationId, String code, String codeDesc, String message,
			String idFound, String found, String riskScore, String userIp, String state, String createTime,
			String createMan, String modifyTime, String modifyMan, String remark, List<RiskInfo> riskInfo) {
		super();
		this.txyAntiFraudId = txyAntiFraudId;
		this.buApplicationId = buApplicationId;
		this.code = code;
		this.codeDesc = codeDesc;
		this.message = message;
		this.idFound = idFound;
		this.found = found;
		this.riskScore = riskScore;
		this.userIp = userIp;
		this.state = state;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.riskInfo = riskInfo;
	}

	public JsonTxyAntiFraud() {
		super();
	}

	
}
