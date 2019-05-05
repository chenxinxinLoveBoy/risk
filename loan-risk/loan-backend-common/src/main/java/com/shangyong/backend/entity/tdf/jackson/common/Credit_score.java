/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.tdf.jackson.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2017-12-13 21:57:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Credit_score implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("id_number_mobile_score")
    private String idNumberMobileScore;
	@JsonProperty("mobile_score")
    private String mobileScore;
	@JsonProperty("id_number_score")
    private String idNumberScore;
	public String getIdNumberMobileScore() {
		return idNumberMobileScore;
	}
	public void setIdNumberMobileScore(String idNumberMobileScore) {
		this.idNumberMobileScore = idNumberMobileScore;
	}
	public String getMobileScore() {
		return mobileScore;
	}
	public void setMobileScore(String mobileScore) {
		this.mobileScore = mobileScore;
	}
	public String getIdNumberScore() {
		return idNumberScore;
	}
	public void setIdNumberScore(String idNumberScore) {
		this.idNumberScore = idNumberScore;
	}
	
}