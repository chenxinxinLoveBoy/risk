package com.shangyong.backend.entity.txy;

import java.io.Serializable;

/**
 * 腾讯反欺诈风险详情表bean
 * @author mingke.shi
 * @date Sun Dec 10 17:19:38 CST 2017
 **/
public class TxyRiskInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String txyRiskInfoId;

	/**腾讯反欺诈id**/
	private String txyAntiFraudId;

	/**风险码**/
	private String riskCode;

	/**风险详情**/
	private String riskCodeValue;

	/**备注**/
	private String remark;

	/****/
	private String createTime;

	/****/
	private String modifyTime;

	/****/
	private String createMan;

	/****/
	private String modifyMan;


	public TxyRiskInfo() {
		super();
	}
	public TxyRiskInfo(String txyRiskInfoId,String txyAntiFraudId,String riskCode,String riskCodeValue,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.txyRiskInfoId = txyRiskInfoId;
		this.txyAntiFraudId = txyAntiFraudId;
		this.riskCode = riskCode;
		this.riskCodeValue = riskCodeValue;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setTxyRiskInfoId(String txyRiskInfoId){
		this.txyRiskInfoId = txyRiskInfoId;
	}

	public String getTxyRiskInfoId(){
		return this.txyRiskInfoId;
	}

	public void setTxyAntiFraudId(String txyAntiFraudId){
		this.txyAntiFraudId = txyAntiFraudId;
	}

	public String getTxyAntiFraudId(){
		return this.txyAntiFraudId;
	}

	public void setRiskCode(String riskCode){
		this.riskCode = riskCode;
	}

	public String getRiskCode(){
		return this.riskCode;
	}

	public void setRiskCodeValue(String riskCodeValue){
		this.riskCodeValue = riskCodeValue;
	}

	public String getRiskCodeValue(){
		return this.riskCodeValue;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}
	
	@Override
	public String toString() {
		return "TxyRiskInfo [txyRiskInfoId=" + txyRiskInfoId + ", txyAntiFraudId=" + txyAntiFraudId + ", riskCode="
				+ riskCode + ", riskCodeValue=" + riskCodeValue + ", remark=" + remark + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", createMan=" + createMan + ", modifyMan=" + modifyMan + "]";
	}
}
