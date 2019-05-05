package com.shangyong.backend.entity.redis.fraud2_0;

import org.springframework.util.StringUtils;

import java.util.HashMap;

public class TencentCloud20Redis {
	//腾讯云欺诈分
	private String riskScoreTaf;
	private String txyHighRiskCnt;
	private String txyLowRiskCnt;
	private String txyX1;
	private String txyX12;
	private String txyX5;
	private String txyX503;
	private String txyX5031;
	private String txyX52;

	public TencentCloud20Redis() {
		super();
	}


	public String getRiskScoreTaf() {
		return riskScoreTaf;
	}


	public void setRiskScoreTaf(String riskScoreTaf) {
		this.riskScoreTaf = riskScoreTaf;
	}


	public String getTxyHighRiskCnt() {
		return txyHighRiskCnt;
	}


	public void setTxyHighRiskCnt(String txyHighRiskCnt) {
		this.txyHighRiskCnt = txyHighRiskCnt;
	}


	public String getTxyLowRiskCnt() {
		return txyLowRiskCnt;
	}


	public void setTxyLowRiskCnt(String txyLowRiskCnt) {
		this.txyLowRiskCnt = txyLowRiskCnt;
	}


	public String getTxyX1() {
		return txyX1;
	}


	public void setTxyX1(String txyX1) {
		this.txyX1 = txyX1;
	}


	public String getTxyX12() {
		return txyX12;
	}


	public void setTxyX12(String txyX12) {
		this.txyX12 = txyX12;
	}


	public String getTxyX5() {
		return txyX5;
	}


	public void setTxyX5(String txyX5) {
		this.txyX5 = txyX5;
	}


	public String getTxyX503() {
		return txyX503;
	}


	public void setTxyX503(String txyX503) {
		this.txyX503 = txyX503;
	}


	public String getTxyX5031() {
		return txyX5031;
	}


	public void setTxyX5031(String txyX5031) {
		this.txyX5031 = txyX5031;
	}


	public String getTxyX52() {
		return txyX52;
	}


	public void setTxyX52(String txyX52) {
		this.txyX52 = txyX52;
	}


	public HashMap<String,String> toMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("riskScoreTaf",!StringUtils.hasText(riskScoreTaf) ? "unknow" : this.riskScoreTaf);
		map.put("txyHighRiskCnt",!StringUtils.hasText(txyHighRiskCnt) ? "unknow" : this.txyHighRiskCnt);
		map.put("txyLowRiskCnt",!StringUtils.hasText(txyLowRiskCnt) ? "unknow" : this.txyLowRiskCnt);
		map.put("txyX1",!StringUtils.hasText(txyX1) ? "unknow" : this.txyX1);
		map.put("txyX12",!StringUtils.hasText(txyX12) ? "unknow" : this.txyX12);
		map.put("txyX5",!StringUtils.hasText(txyX5) ? "unknow" : this.txyX5);
		map.put("txyX503",!StringUtils.hasText(txyX503) ? "unknow" : this.txyX503);
		map.put("txyX5031",!StringUtils.hasText(txyX5031) ? "unknow" : this.txyX5031);
		map.put("txyX52",!StringUtils.hasText(txyX52) ? "unknow" : this.txyX52);
		
		return map;
	}
	
    /**
     * 该方法谨慎调用， 只有在腾讯云返回不为空的时候在才能执行
     */
    public void initZero(){
    	txyHighRiskCnt="0";
    	txyLowRiskCnt="0";
    	txyX1="0";
    	txyX12="0";
    	txyX5="0";
    	txyX503="0";
    	txyX5031="0";
    	txyX52="0";
    }
}
