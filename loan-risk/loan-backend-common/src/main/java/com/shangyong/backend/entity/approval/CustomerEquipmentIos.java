package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * IOS设备环境信息bean
 * @author hxf
 * @date Tue Aug 22 18:13:46 CST 2017
 **/
public class CustomerEquipmentIos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1647360486671408867L;

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


	public CustomerEquipmentIos() {
		super();
	}
	public CustomerEquipmentIos(String equipmentId,String customerId,Integer appName,String sdkEdition,String fingerprintTokenId,String deviceId,String idfa,String idfv,String uuid,String bootTime,String sumRamStorage,String availableRamStorage,String sumRomStorage,String availableRomStorage,String screenBrightness,String batteryState,String cellularNetworkIp,String wifiIp,String wifiMask,String vpnAddress,String vpnMask,String macAddress,String networkType,String wifiName,String wifiBssid,String agentType,String agentAddress,String dnsAddress,String isRoot,String equipmentType,String systemEdition,String equipmentName,String operator,String countryCode,String moveCountryCode,String moveNetworkCode,String bundleId,String language,String realIp,String ipPosition,String openUdid,Integer cpuNum,String cpuSubType,String ipAddress,String breakFlag,String cpuModel,String createTime,String modifyTime,String gprsIp) {
		super();
		this.equipmentId = equipmentId;
		this.customerId = customerId;
		this.appName = appName;
		this.sdkEdition = sdkEdition;
		this.fingerprintTokenId = fingerprintTokenId;
		this.deviceId = deviceId;
		this.idfa = idfa;
		this.idfv = idfv;
		this.uuid = uuid;
		this.bootTime = bootTime;
		this.sumRamStorage = sumRamStorage;
		this.availableRamStorage = availableRamStorage;
		this.sumRomStorage = sumRomStorage;
		this.availableRomStorage = availableRomStorage;
		this.screenBrightness = screenBrightness;
		this.batteryState = batteryState;
		this.cellularNetworkIp = cellularNetworkIp;
		this.wifiIp = wifiIp;
		this.wifiMask = wifiMask;
		this.vpnAddress = vpnAddress;
		this.vpnMask = vpnMask;
		this.macAddress = macAddress;
		this.networkType = networkType;
		this.wifiName = wifiName;
		this.wifiBssid = wifiBssid;
		this.agentType = agentType;
		this.agentAddress = agentAddress;
		this.dnsAddress = dnsAddress;
		this.isRoot = isRoot;
		this.equipmentType = equipmentType;
		this.systemEdition = systemEdition;
		this.equipmentName = equipmentName;
		this.operator = operator;
		this.countryCode = countryCode;
		this.moveCountryCode = moveCountryCode;
		this.moveNetworkCode = moveNetworkCode;
		this.bundleId = bundleId;
		this.language = language;
		this.realIp = realIp;
		this.ipPosition = ipPosition;
		this.openUdid = openUdid;
		this.cpuNum = cpuNum;
		this.cpuSubType = cpuSubType;
		this.ipAddress = ipAddress;
		this.breakFlag = breakFlag;
		this.cpuModel = cpuModel;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.gprsIp = gprsIp;
	}
	public void setEquipmentId(String equipmentId){
		this.equipmentId = equipmentId;
	}

	public String getEquipmentId(){
		return this.equipmentId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setSdkEdition(String sdkEdition){
		this.sdkEdition = sdkEdition;
	}

	public String getSdkEdition(){
		return this.sdkEdition;
	}

	public void setFingerprintTokenId(String fingerprintTokenId){
		this.fingerprintTokenId = fingerprintTokenId;
	}

	public String getFingerprintTokenId(){
		return this.fingerprintTokenId;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return this.deviceId;
	}

	public void setIdfa(String idfa){
		this.idfa = idfa;
	}

	public String getIdfa(){
		return this.idfa;
	}

	public void setIdfv(String idfv){
		this.idfv = idfv;
	}

	public String getIdfv(){
		return this.idfv;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setBootTime(String bootTime){
		this.bootTime = bootTime;
	}

	public String getBootTime(){
		return this.bootTime;
	}

	public void setSumRamStorage(String sumRamStorage){
		this.sumRamStorage = sumRamStorage;
	}

	public String getSumRamStorage(){
		return this.sumRamStorage;
	}

	public void setAvailableRamStorage(String availableRamStorage){
		this.availableRamStorage = availableRamStorage;
	}

	public String getAvailableRamStorage(){
		return this.availableRamStorage;
	}

	public void setSumRomStorage(String sumRomStorage){
		this.sumRomStorage = sumRomStorage;
	}

	public String getSumRomStorage(){
		return this.sumRomStorage;
	}

	public void setAvailableRomStorage(String availableRomStorage){
		this.availableRomStorage = availableRomStorage;
	}

	public String getAvailableRomStorage(){
		return this.availableRomStorage;
	}

	public void setScreenBrightness(String screenBrightness){
		this.screenBrightness = screenBrightness;
	}

	public String getScreenBrightness(){
		return this.screenBrightness;
	}

	public void setBatteryState(String batteryState){
		this.batteryState = batteryState;
	}

	public String getBatteryState(){
		return this.batteryState;
	}

	public void setCellularNetworkIp(String cellularNetworkIp){
		this.cellularNetworkIp = cellularNetworkIp;
	}

	public String getCellularNetworkIp(){
		return this.cellularNetworkIp;
	}

	public void setWifiIp(String wifiIp){
		this.wifiIp = wifiIp;
	}

	public String getWifiIp(){
		return this.wifiIp;
	}

	public void setWifiMask(String wifiMask){
		this.wifiMask = wifiMask;
	}

	public String getWifiMask(){
		return this.wifiMask;
	}

	public void setVpnAddress(String vpnAddress){
		this.vpnAddress = vpnAddress;
	}

	public String getVpnAddress(){
		return this.vpnAddress;
	}

	public void setVpnMask(String vpnMask){
		this.vpnMask = vpnMask;
	}

	public String getVpnMask(){
		return this.vpnMask;
	}

	public void setMacAddress(String macAddress){
		this.macAddress = macAddress;
	}

	public String getMacAddress(){
		return this.macAddress;
	}

	public void setNetworkType(String networkType){
		this.networkType = networkType;
	}

	public String getNetworkType(){
		return this.networkType;
	}

	public void setWifiName(String wifiName){
		this.wifiName = wifiName;
	}

	public String getWifiName(){
		return this.wifiName;
	}

	public void setWifiBssid(String wifiBssid){
		this.wifiBssid = wifiBssid;
	}

	public String getWifiBssid(){
		return this.wifiBssid;
	}

	public void setAgentType(String agentType){
		this.agentType = agentType;
	}

	public String getAgentType(){
		return this.agentType;
	}

	public void setAgentAddress(String agentAddress){
		this.agentAddress = agentAddress;
	}

	public String getAgentAddress(){
		return this.agentAddress;
	}

	public void setDnsAddress(String dnsAddress){
		this.dnsAddress = dnsAddress;
	}

	public String getDnsAddress(){
		return this.dnsAddress;
	}

	public void setIsRoot(String isRoot){
		this.isRoot = isRoot;
	}

	public String getIsRoot(){
		return this.isRoot;
	}

	public void setEquipmentType(String equipmentType){
		this.equipmentType = equipmentType;
	}

	public String getEquipmentType(){
		return this.equipmentType;
	}

	public void setSystemEdition(String systemEdition){
		this.systemEdition = systemEdition;
	}

	public String getSystemEdition(){
		return this.systemEdition;
	}

	public void setEquipmentName(String equipmentName){
		this.equipmentName = equipmentName;
	}

	public String getEquipmentName(){
		return this.equipmentName;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperator(){
		return this.operator;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return this.countryCode;
	}

	public void setMoveCountryCode(String moveCountryCode){
		this.moveCountryCode = moveCountryCode;
	}

	public String getMoveCountryCode(){
		return this.moveCountryCode;
	}

	public void setMoveNetworkCode(String moveNetworkCode){
		this.moveNetworkCode = moveNetworkCode;
	}

	public String getMoveNetworkCode(){
		return this.moveNetworkCode;
	}

	public void setBundleId(String bundleId){
		this.bundleId = bundleId;
	}

	public String getBundleId(){
		return this.bundleId;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return this.language;
	}

	public void setRealIp(String realIp){
		this.realIp = realIp;
	}

	public String getRealIp(){
		return this.realIp;
	}

	public void setIpPosition(String ipPosition){
		this.ipPosition = ipPosition;
	}

	public String getIpPosition(){
		return this.ipPosition;
	}

	public void setOpenUdid(String openUdid){
		this.openUdid = openUdid;
	}

	public String getOpenUdid(){
		return this.openUdid;
	}

	public void setCpuNum(Integer cpuNum){
		this.cpuNum = cpuNum;
	}

	public Integer getCpuNum(){
		return this.cpuNum;
	}

	public void setCpuSubType(String cpuSubType){
		this.cpuSubType = cpuSubType;
	}

	public String getCpuSubType(){
		return this.cpuSubType;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

	public String getIpAddress(){
		return this.ipAddress;
	}

	public void setBreakFlag(String breakFlag){
		this.breakFlag = breakFlag;
	}

	public String getBreakFlag(){
		return this.breakFlag;
	}

	public void setCpuModel(String cpuModel){
		this.cpuModel = cpuModel;
	}

	public String getCpuModel(){
		return this.cpuModel;
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

	public void setGprsIp(String gprsIp){
		this.gprsIp = gprsIp;
	}

	public String getGprsIp(){
		return this.gprsIp;
	}
	
	@Override
	public String toString() {
		return "CustomerEquipmentIos [equipmentId=" + equipmentId + ", customerId=" + customerId + ", appName="
				+ appName + ", sdkEdition=" + sdkEdition + ", fingerprintTokenId=" + fingerprintTokenId + ", deviceId="
				+ deviceId + ", idfa=" + idfa + ", idfv=" + idfv + ", uuid=" + uuid + ", bootTime=" + bootTime
				+ ", sumRamStorage=" + sumRamStorage + ", availableRamStorage=" + availableRamStorage
				+ ", sumRomStorage=" + sumRomStorage + ", availableRomStorage=" + availableRomStorage
				+ ", screenBrightness=" + screenBrightness + ", batteryState=" + batteryState + ", cellularNetworkIp="
				+ cellularNetworkIp + ", wifiIp=" + wifiIp + ", wifiMask=" + wifiMask + ", vpnAddress=" + vpnAddress
				+ ", vpnMask=" + vpnMask + ", macAddress=" + macAddress + ", networkType=" + networkType + ", wifiName="
				+ wifiName + ", wifiBssid=" + wifiBssid + ", agentType=" + agentType + ", agentAddress=" + agentAddress
				+ ", dnsAddress=" + dnsAddress + ", isRoot=" + isRoot + ", equipmentType=" + equipmentType
				+ ", systemEdition=" + systemEdition + ", equipmentName=" + equipmentName + ", operator=" + operator
				+ ", countryCode=" + countryCode + ", moveCountryCode=" + moveCountryCode + ", moveNetworkCode="
				+ moveNetworkCode + ", bundleId=" + bundleId + ", language=" + language + ", realIp=" + realIp
				+ ", ipPosition=" + ipPosition + ", openUdid=" + openUdid + ", cpuNum=" + cpuNum + ", cpuSubType="
				+ cpuSubType + ", ipAddress=" + ipAddress + ", breakFlag=" + breakFlag + ", cpuModel=" + cpuModel
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", gprsIp=" + gprsIp + "]";
	}

	 
}
