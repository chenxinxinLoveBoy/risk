package com.shangyong.backend.entity.approval;

import java.io.Serializable;

public class EquipmentInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6197041225242152399L;

	/**设备名称**/
	private String equipmentName;

	/**设备数**/
	private Integer equipmentNum;
	
	/**设备关联案件数**/
	private Integer applicationNum;
	
	/**gprs经度**/
	private String gprsjingIp;

	/**gprs纬度**/
	private String gprsWeiIp;

	/**设备号**/
	private String deviceId;
	
	/**设备id**/
	private String customerId;
	
	/**高德地址**/
	private String address;
	
	public EquipmentInformation() {
		super();
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Integer getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(Integer equipmentNum) {
		this.equipmentNum = equipmentNum;
	}

	public Integer getApplicationNum() {
		return applicationNum;
	}

	public void setApplicationNum(Integer applicationNum) {
		this.applicationNum = applicationNum;
	}

	public String getGprsjingIp() {
		return gprsjingIp;
	}

	public void setGprsjingIp(String gprsjingIp) {
		this.gprsjingIp = gprsjingIp;
	}

	public String getGprsWeiIp() {
		return gprsWeiIp;
	}

	public void setGprsWeiIp(String gprsWeiIp) {
		this.gprsWeiIp = gprsWeiIp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
