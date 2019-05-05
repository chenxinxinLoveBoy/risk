package com.shangyong.backend.entity;


public class BlacklistResult{
	

	/** 命中数量 **/
	private int count;

	/**身份证号码是否命中**/
	private boolean idCard;

	/** 手机号码是否命中 **/
	private boolean phone;

	/**设备ID是否命中 **/
	private boolean deviceId;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isIdCard() {
		return idCard;
	}

	public void setIdCard(boolean idCard) {
		this.idCard = idCard;
	}

	public boolean isPhone() {
		return phone;
	}

	public void setPhone(boolean phone) {
		this.phone = phone;
	}

	public boolean isDeviceId() {
		return deviceId;
	}

	public void setDeviceId(boolean deviceId) {
		this.deviceId = deviceId;
	}
	
}
