package com.shangyong.backend.entity.redis.fraud1_1;

import java.util.HashMap;

/**
 * 同盾多头 1.1
 */
public class TongDun11Redis {
	
	private String tdX10180;
	private String tdtotal30;
	private String tdX330;
	private String tdX1180;

	public String getTdX10180() {
		return tdX10180;
	}

	public void setTdX10180(String tdX10180) {
		this.tdX10180 = tdX10180;
	}

	public String getTdtotal30() {
		return tdtotal30;
	}

	public void setTdtotal30(String tdtotal30) {
		this.tdtotal30 = tdtotal30;
	}

	public String getTdX330() {
		return tdX330;
	}

	public void setTdX330(String tdX330) {
		this.tdX330 = tdX330;
	}

	public String getTdX1180() {
		return tdX1180;
	}

	public void setTdX1180(String tdX1180) {
		this.tdX1180 = tdX1180;
	}
	
	public HashMap<String, String> toMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("tdX10180", null == this.tdX10180 ? "unknow" : this.tdX10180);
		map.put("tdtotal30", null == this.tdtotal30 ? "unknow" : this.tdtotal30);
		map.put("tdX330", null == this.tdX330 ? "unknow" : this.tdX330);
		map.put("tdX1180", null == this.tdX1180 ? "unknow" : this.tdX1180);
		return map;
	}
	
}
