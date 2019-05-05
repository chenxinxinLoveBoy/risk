
package com.shangyong.backend.entity.redis;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年8月9日  
 *
 */
public class BlacklistValueRedis implements Serializable{

	private static final long serialVersionUID = -7928185354975483805L;
	/** 黑名单来源*/
	private String dsSource;
	/** 黑名单备注*/
	private String remark;
	/** 黑名单分来*/
	private String classCode;
	
	public String getDsSource() {
		return dsSource;
	}
	public void setDsSource(String dsSource) {
		this.dsSource = dsSource;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	@Override
	public String toString() {
		return "BlacklistValueRedis [dsSource=" + dsSource + ", remark=" + remark + ", classCode=" + classCode + "]";
	}


	public static void main(String[] args) {
		BlacklistValueRedis redis = new BlacklistValueRedis();
		redis.setDsSource("04");
		redis.setClassCode("");
		redis.setRemark("");
		System.out.println(JSON.toJSONString(redis));
	}
}

