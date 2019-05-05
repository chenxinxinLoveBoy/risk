package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * IOS设备环境信息bean
 * @author hxf
 * @date Wed Aug 02 14:01:34 CST 2017
 **/
public class CuCustomerEquipmentIos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**记录序号**/
	private String equipmentId;

	/**APP客户编号**/
	private String customerId;

	/**平台 1闪贷 2速贷**/
	private Integer appName;

	/**SDK版本**/
	private String sdkEdition;

	/**指纹设备tokenId**/
	private String fingerprintTokenId;

	/****/
	private String deviceId;

	/****/
	private String idfa;

	/****/
	private String idfv;

	/****/
	private String uuid;

	/**开机时间**/
	private String bootTime;

	/**总运行内存(总容量)**/
	private String sumRamStorage;

	/**可用运行内存(可用容量)**/
	private String availableRamStorage;

	/**总存储空间**/
	private String sumRomStorage;

	/**可用存储空间**/
	private String availableRomStorage;

	/**屏幕亮度**/
	private String screenBrightness;

	/**电池状态**/
	private String batteryState;

	/**蜂窝网络IP**/
	private String cellularNetworkIp;

	/**wifi ip地址**/
	private String wifiIp;

	/**wifi掩码**/
	private String wifiMask;

	/**vpn地址**/
	private String vpnAddress;

	/**vpn掩码**/
	private String vpnMask;

	/**mac地址**/
	private String macAddress;

	/**网络类型**/
	private String networkType;

	/**无线网络名称**/
	private String wifiName;

	/**无线BSSID**/
	private String wifiBssid;

	/**代理类型**/
	private String agentType;

	/**代理地址**/
	private String agentAddress;

	/**dns地址**/
	private String dnsAddress;

	/**是否root/越狱**/
	private String isRoot;

	/**设备类型**/
	private String equipmentType;

	/**系统版本**/
	private String systemEdition;

	/**设备名称**/
	private String equipmentName;

	/**运营商**/
	private String operator;

	/**国家代码**/
	private String countryCode;

	/**移动国家码**/
	private String moveCountryCode;

	/**移动网络码**/
	private String moveNetworkCode;

	/****/
	private String bundleId;

	/**语言**/
	private String language;

	/**真实IP**/
	private String realIp;

	/**IP地理位置信息**/
	private String ipPosition;

	/****/
	private String openUdid;

	/**cpu数量**/
	private Integer cpuNum;

	/**cpu子类型**/
	private String cpuSubType;

	/**ip地址**/
	private String ipAddress;

	/****/
	private String breakFlag;

	/**cpu型号**/
	private String cpuModel;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;
	
	/**gprs Ip地址**/
	private String gprsIp;

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getAppName() {
		return appName;
	}

	public void setAppName(Integer appName) {
		this.appName = appName;
	}

	public String getSdkEdition() {
		return sdkEdition;
	}

	public void setSdkEdition(String sdkEdition) {
		this.sdkEdition = sdkEdition;
	}

	public String getFingerprintTokenId() {
		return fingerprintTokenId;
	}

	public void setFingerprintTokenId(String fingerprintTokenId) {
		this.fingerprintTokenId = fingerprintTokenId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getIdfv() {
		return idfv;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBootTime() {
		return bootTime;
	}

	public void setBootTime(String bootTime) {
		this.bootTime = bootTime;
	}

	public String getSumRamStorage() {
		return sumRamStorage;
	}

	public void setSumRamStorage(String sumRamStorage) {
		this.sumRamStorage = sumRamStorage;
	}

	public String getAvailableRamStorage() {
		return availableRamStorage;
	}

	public void setAvailableRamStorage(String availableRamStorage) {
		this.availableRamStorage = availableRamStorage;
	}

	public String getSumRomStorage() {
		return sumRomStorage;
	}

	public void setSumRomStorage(String sumRomStorage) {
		this.sumRomStorage = sumRomStorage;
	}

	public String getAvailableRomStorage() {
		return availableRomStorage;
	}

	public void setAvailableRomStorage(String availableRomStorage) {
		this.availableRomStorage = availableRomStorage;
	}

	public String getScreenBrightness() {
		return screenBrightness;
	}

	public void setScreenBrightness(String screenBrightness) {
		this.screenBrightness = screenBrightness;
	}

	public String getBatteryState() {
		return batteryState;
	}

	public void setBatteryState(String batteryState) {
		this.batteryState = batteryState;
	}

	public String getCellularNetworkIp() {
		return cellularNetworkIp;
	}

	public void setCellularNetworkIp(String cellularNetworkIp) {
		this.cellularNetworkIp = cellularNetworkIp;
	}

	public String getWifiIp() {
		return wifiIp;
	}

	public void setWifiIp(String wifiIp) {
		this.wifiIp = wifiIp;
	}

	public String getWifiMask() {
		return wifiMask;
	}

	public void setWifiMask(String wifiMask) {
		this.wifiMask = wifiMask;
	}

	public String getVpnAddress() {
		return vpnAddress;
	}

	public void setVpnAddress(String vpnAddress) {
		this.vpnAddress = vpnAddress;
	}

	public String getVpnMask() {
		return vpnMask;
	}

	public void setVpnMask(String vpnMask) {
		this.vpnMask = vpnMask;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getWifiName() {
		return wifiName;
	}

	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}

	public String getWifiBssid() {
		return wifiBssid;
	}

	public void setWifiBssid(String wifiBssid) {
		this.wifiBssid = wifiBssid;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getAgentAddress() {
		return agentAddress;
	}

	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	public String getDnsAddress() {
		return dnsAddress;
	}

	public void setDnsAddress(String dnsAddress) {
		this.dnsAddress = dnsAddress;
	}

	public String getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getSystemEdition() {
		return systemEdition;
	}

	public void setSystemEdition(String systemEdition) {
		this.systemEdition = systemEdition;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMoveCountryCode() {
		return moveCountryCode;
	}

	public void setMoveCountryCode(String moveCountryCode) {
		this.moveCountryCode = moveCountryCode;
	}

	public String getMoveNetworkCode() {
		return moveNetworkCode;
	}

	public void setMoveNetworkCode(String moveNetworkCode) {
		this.moveNetworkCode = moveNetworkCode;
	}

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRealIp() {
		return realIp;
	}

	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}

	public String getIpPosition() {
		return ipPosition;
	}

	public void setIpPosition(String ipPosition) {
		this.ipPosition = ipPosition;
	}

	public String getOpenUdid() {
		return openUdid;
	}

	public void setOpenUdid(String openUdid) {
		this.openUdid = openUdid;
	}

	public Integer getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(Integer cpuNum) {
		this.cpuNum = cpuNum;
	}

	public String getCpuSubType() {
		return cpuSubType;
	}

	public void setCpuSubType(String cpuSubType) {
		this.cpuSubType = cpuSubType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getBreakFlag() {
		return breakFlag;
	}

	public void setBreakFlag(String breakFlag) {
		this.breakFlag = breakFlag;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getGprsIp() {
		return gprsIp;
	}

	public void setGprsIp(String gprsIp) {
		this.gprsIp = gprsIp;
	}

}
