package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 身份证及手机号码归属地
 * @author Smk
 *
 */
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 身份证归属地**/
	private String sfzAddress;
	
	/** 手机号归属地**/
	private String phoneAddress;

	public String getSfzAddress() {
		return sfzAddress;
	}

	public void setSfzAddress(String sfzAddress) {
		this.sfzAddress = sfzAddress;
	}

	public String getPhoneAddress() {
		return phoneAddress;
	}

	public void setPhoneAddress(String phoneAddress) {
		this.phoneAddress = phoneAddress;
	}
	
	
}
