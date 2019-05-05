package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 安卓设备环境信息bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
public class CustomerEquipmentAndroid implements Serializable {

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

	/**当前连接基站信息**/
	private String baseStationInfo;

	/**总运行内存**/
	private String sumRamStorage;

	/**可用运行内存**/
	private String availableRamStorage;

	/**总存储空间**/
	private String sumRomStorage;

	/**可用存储空间**/
	private String availableRomStorage;

	/**网关**/
	private String gateway;

	/****/
	private String host;

	/****/
	private String imei;

	/****/
	private String display;

	/****/
	private String sessionId;

	/****/
	private String roName;

	/**cpu bogoMips值**/
	private String cpuBogoMips;

	/****/
	private String roDevice;

	/****/
	private String deviceId;

	/****/
	private String brand;

	/****/
	private String carrier;

	/**gprs Ip地址**/
	private String gprsIp;

	/****/
	private String deviceName;

	/****/
	private String hardware;

	/****/
	private String model;

	/****/
	private String product;

	/****/
	private String mcc;

	/****/
	private String mnc;

	/****/
	private String tags;

	/**wifi mac地址**/
	private String wifiMac;

	/****/
	private String tdid;

	/****/
	private String roModel;

	/**wifi ip地址**/
	private String wifiIp;

	/**电话号码**/
	private String phoneNum;

	/**语言**/
	private String language;

	/**是否Root**/
	private Boolean isRoot;

	/****/
	private String udid;

	/**dns地址**/
	private String dnsAddress;

	/**时区**/
	private String timeZone;

	/**真实IP**/
	private String realIp;

	/**IP地理位置信息**/
	private String ipPosition;

	/**vpn地址**/
	private String vpnAddress;

	/**开机时间**/
	private String bootTime;

	/**proxyAdress:port**/
	private String proxyAdressPort;

	/**SDK版本**/
	private String sdkEdition;

	/**fm版本**/
	private String fmEdition;

	/**电量**/
	private String electricity;

	/**音乐hash**/
	private String musicHash;

	/**网络提供商编号**/
	private String networkProviderCode;

	/**iso标准的国家代码**/
	private String isoCountryCode;

	/**firm版本**/
	private String firmEdition;

	/**cpu型号**/
	private String cpuModel;

	/**cpu序列号**/
	private String cpuSerialNum;

	/**sim序列号**/
	private String simSerialNum;

	/**cpu硬件**/
	private String cpuHardware;

	/**手机型号**/
	private String phoneModel;

	/**蓝牙mac地址**/
	private String bluetoothMac;

	/**语音信箱号码**/
	private String voiceMailboxNum;

	/**设备配置参数**/
	private String deviceParam;

	/**电池状态**/
	private String batteryStatus;

	/**设备序列号**/
	private String equipmentSerialNum;

	/**基带版本号**/
	private String basebandEdition;

	/**浏览器ua**/
	private String browserUa;

	/**电量信息是否可用**/
	private Boolean electricityAvailable;

	/**SIM移动国家码**/
	private String moveCountryCode;

	/**SIM移动网络码**/
	private String moveNetworkCode;

	/**从开机到目前的时间包括休眠时间(s)**/
	private Integer bootReachCurrent;

	/**从开机到目前的时间不包括休眠时间**/
	private Integer bootNoDormancy;

	/**屏幕长度**/
	private Integer screenLong;

	/**屏幕宽度**/
	private Integer screenWide;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public CustomerEquipmentAndroid() {
		super();
	}

	public CustomerEquipmentAndroid(String equipmentId, String customerId, Integer appName, String baseStationInfo,
			String sumRamStorage, String availableRamStorage, String sumRomStorage, String availableRomStorage,
			String gateway, String host, String imei, String display, String sessionId, String roName,
			String cpuBogoMips, String roDevice, String deviceId, String brand, String carrier, String gprsIp,
			String deviceName, String hardware, String model, String product, String mcc, String mnc, String tags,
			String wifiMac, String tdid, String roModel, String wifiIp, String phoneNum, String language,
			Boolean isRoot, String udid, String dnsAddress, String timeZone, String realIp, String ipPosition,
			String vpnAddress, String bootTime, String proxyAdressPort, String sdkEdition, String fmEdition,
			String electricity, String musicHash, String networkProviderCode, String isoCountryCode, String firmEdition,
			String cpuModel, String cpuSerialNum, String simSerialNum, String cpuHardware, String phoneModel,
			String bluetoothMac, String voiceMailboxNum, String deviceParam, String batteryStatus,
			String equipmentSerialNum, String basebandEdition, String browserUa, Boolean electricityAvailable,
			String moveCountryCode, String moveNetworkCode, Integer bootReachCurrent, Integer bootNoDormancy,
			Integer screenLong, Integer screenWide, String createTime, String modifyTime) {
		super();
		this.equipmentId = equipmentId;
		this.customerId = customerId;
		this.appName = appName;
		this.baseStationInfo = baseStationInfo;
		this.sumRamStorage = sumRamStorage;
		this.availableRamStorage = availableRamStorage;
		this.sumRomStorage = sumRomStorage;
		this.availableRomStorage = availableRomStorage;
		this.gateway = gateway;
		this.host = host;
		this.imei = imei;
		this.display = display;
		this.sessionId = sessionId;
		this.roName = roName;
		this.cpuBogoMips = cpuBogoMips;
		this.roDevice = roDevice;
		this.deviceId = deviceId;
		this.brand = brand;
		this.carrier = carrier;
		this.gprsIp = gprsIp;
		this.deviceName = deviceName;
		this.hardware = hardware;
		this.model = model;
		this.product = product;
		this.mcc = mcc;
		this.mnc = mnc;
		this.tags = tags;
		this.wifiMac = wifiMac;
		this.tdid = tdid;
		this.roModel = roModel;
		this.wifiIp = wifiIp;
		this.phoneNum = phoneNum;
		this.language = language;
		this.isRoot = isRoot;
		this.udid = udid;
		this.dnsAddress = dnsAddress;
		this.timeZone = timeZone;
		this.realIp = realIp;
		this.ipPosition = ipPosition;
		this.vpnAddress = vpnAddress;
		this.bootTime = bootTime;
		this.proxyAdressPort = proxyAdressPort;
		this.sdkEdition = sdkEdition;
		this.fmEdition = fmEdition;
		this.electricity = electricity;
		this.musicHash = musicHash;
		this.networkProviderCode = networkProviderCode;
		this.isoCountryCode = isoCountryCode;
		this.firmEdition = firmEdition;
		this.cpuModel = cpuModel;
		this.cpuSerialNum = cpuSerialNum;
		this.simSerialNum = simSerialNum;
		this.cpuHardware = cpuHardware;
		this.phoneModel = phoneModel;
		this.bluetoothMac = bluetoothMac;
		this.voiceMailboxNum = voiceMailboxNum;
		this.deviceParam = deviceParam;
		this.batteryStatus = batteryStatus;
		this.equipmentSerialNum = equipmentSerialNum;
		this.basebandEdition = basebandEdition;
		this.browserUa = browserUa;
		this.electricityAvailable = electricityAvailable;
		this.moveCountryCode = moveCountryCode;
		this.moveNetworkCode = moveNetworkCode;
		this.bootReachCurrent = bootReachCurrent;
		this.bootNoDormancy = bootNoDormancy;
		this.screenLong = screenLong;
		this.screenWide = screenWide;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

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

	public String getBaseStationInfo() {
		return baseStationInfo;
	}

	public void setBaseStationInfo(String baseStationInfo) {
		this.baseStationInfo = baseStationInfo;
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

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRoName() {
		return roName;
	}

	public void setRoName(String roName) {
		this.roName = roName;
	}

	public String getCpuBogoMips() {
		return cpuBogoMips;
	}

	public void setCpuBogoMips(String cpuBogoMips) {
		this.cpuBogoMips = cpuBogoMips;
	}

	public String getRoDevice() {
		return roDevice;
	}

	public void setRoDevice(String roDevice) {
		this.roDevice = roDevice;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getGprsIp() {
		return gprsIp;
	}

	public void setGprsIp(String gprsIp) {
		this.gprsIp = gprsIp;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getWifiMac() {
		return wifiMac;
	}

	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}

	public String getTdid() {
		return tdid;
	}

	public void setTdid(String tdid) {
		this.tdid = tdid;
	}

	public String getRoModel() {
		return roModel;
	}

	public void setRoModel(String roModel) {
		this.roModel = roModel;
	}

	public String getWifiIp() {
		return wifiIp;
	}

	public void setWifiIp(String wifiIp) {
		this.wifiIp = wifiIp;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getDnsAddress() {
		return dnsAddress;
	}

	public void setDnsAddress(String dnsAddress) {
		this.dnsAddress = dnsAddress;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
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

	public String getVpnAddress() {
		return vpnAddress;
	}

	public void setVpnAddress(String vpnAddress) {
		this.vpnAddress = vpnAddress;
	}

	public String getBootTime() {
		return bootTime;
	}

	public void setBootTime(String bootTime) {
		this.bootTime = bootTime;
	}

	public String getProxyAdressPort() {
		return proxyAdressPort;
	}

	public void setProxyAdressPort(String proxyAdressPort) {
		this.proxyAdressPort = proxyAdressPort;
	}

	public String getSdkEdition() {
		return sdkEdition;
	}

	public void setSdkEdition(String sdkEdition) {
		this.sdkEdition = sdkEdition;
	}

	public String getFmEdition() {
		return fmEdition;
	}

	public void setFmEdition(String fmEdition) {
		this.fmEdition = fmEdition;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	public String getMusicHash() {
		return musicHash;
	}

	public void setMusicHash(String musicHash) {
		this.musicHash = musicHash;
	}

	public String getNetworkProviderCode() {
		return networkProviderCode;
	}

	public void setNetworkProviderCode(String networkProviderCode) {
		this.networkProviderCode = networkProviderCode;
	}

	public String getIsoCountryCode() {
		return isoCountryCode;
	}

	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}

	public String getFirmEdition() {
		return firmEdition;
	}

	public void setFirmEdition(String firmEdition) {
		this.firmEdition = firmEdition;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public String getCpuSerialNum() {
		return cpuSerialNum;
	}

	public void setCpuSerialNum(String cpuSerialNum) {
		this.cpuSerialNum = cpuSerialNum;
	}

	public String getSimSerialNum() {
		return simSerialNum;
	}

	public void setSimSerialNum(String simSerialNum) {
		this.simSerialNum = simSerialNum;
	}

	public String getCpuHardware() {
		return cpuHardware;
	}

	public void setCpuHardware(String cpuHardware) {
		this.cpuHardware = cpuHardware;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getBluetoothMac() {
		return bluetoothMac;
	}

	public void setBluetoothMac(String bluetoothMac) {
		this.bluetoothMac = bluetoothMac;
	}

	public String getVoiceMailboxNum() {
		return voiceMailboxNum;
	}

	public void setVoiceMailboxNum(String voiceMailboxNum) {
		this.voiceMailboxNum = voiceMailboxNum;
	}

	public String getDeviceParam() {
		return deviceParam;
	}

	public void setDeviceParam(String deviceParam) {
		this.deviceParam = deviceParam;
	}

	public String getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(String batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	public String getEquipmentSerialNum() {
		return equipmentSerialNum;
	}

	public void setEquipmentSerialNum(String equipmentSerialNum) {
		this.equipmentSerialNum = equipmentSerialNum;
	}

	public String getBasebandEdition() {
		return basebandEdition;
	}

	public void setBasebandEdition(String basebandEdition) {
		this.basebandEdition = basebandEdition;
	}

	public String getBrowserUa() {
		return browserUa;
	}

	public void setBrowserUa(String browserUa) {
		this.browserUa = browserUa;
	}

	public Boolean getElectricityAvailable() {
		return electricityAvailable;
	}

	public void setElectricityAvailable(Boolean electricityAvailable) {
		this.electricityAvailable = electricityAvailable;
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

	public Integer getBootReachCurrent() {
		return bootReachCurrent;
	}

	public void setBootReachCurrent(Integer bootReachCurrent) {
		this.bootReachCurrent = bootReachCurrent;
	}

	public Integer getBootNoDormancy() {
		return bootNoDormancy;
	}

	public void setBootNoDormancy(Integer bootNoDormancy) {
		this.bootNoDormancy = bootNoDormancy;
	}

	public Integer getScreenLong() {
		return screenLong;
	}

	public void setScreenLong(Integer screenLong) {
		this.screenLong = screenLong;
	}

	public Integer getScreenWide() {
		return screenWide;
	}

	public void setScreenWide(Integer screenWide) {
		this.screenWide = screenWide;
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

	@Override
	public String toString() {
		return "CustomerEquipmentAndroid [equipmentId=" + equipmentId + ", customerId=" + customerId + ", appName="
				+ appName + ", baseStationInfo=" + baseStationInfo + ", sumRamStorage=" + sumRamStorage
				+ ", availableRamStorage=" + availableRamStorage + ", sumRomStorage=" + sumRomStorage
				+ ", availableRomStorage=" + availableRomStorage + ", gateway=" + gateway + ", host=" + host + ", imei="
				+ imei + ", display=" + display + ", sessionId=" + sessionId + ", roName=" + roName + ", cpuBogoMips="
				+ cpuBogoMips + ", roDevice=" + roDevice + ", deviceId=" + deviceId + ", brand=" + brand + ", carrier="
				+ carrier + ", gprsIp=" + gprsIp + ", deviceName=" + deviceName + ", hardware=" + hardware + ", model="
				+ model + ", product=" + product + ", mcc=" + mcc + ", mnc=" + mnc + ", tags=" + tags + ", wifiMac="
				+ wifiMac + ", tdid=" + tdid + ", roModel=" + roModel + ", wifiIp=" + wifiIp + ", phoneNum=" + phoneNum
				+ ", language=" + language + ", isRoot=" + isRoot + ", udid=" + udid + ", dnsAddress=" + dnsAddress
				+ ", timeZone=" + timeZone + ", realIp=" + realIp + ", ipPosition=" + ipPosition + ", vpnAddress="
				+ vpnAddress + ", bootTime=" + bootTime + ", proxyAdressPort=" + proxyAdressPort + ", sdkEdition="
				+ sdkEdition + ", fmEdition=" + fmEdition + ", electricity=" + electricity + ", musicHash=" + musicHash
				+ ", networkProviderCode=" + networkProviderCode + ", isoCountryCode=" + isoCountryCode
				+ ", firmEdition=" + firmEdition + ", cpuModel=" + cpuModel + ", cpuSerialNum=" + cpuSerialNum
				+ ", simSerialNum=" + simSerialNum + ", cpuHardware=" + cpuHardware + ", phoneModel=" + phoneModel
				+ ", bluetoothMac=" + bluetoothMac + ", voiceMailboxNum=" + voiceMailboxNum + ", deviceParam="
				+ deviceParam + ", batteryStatus=" + batteryStatus + ", equipmentSerialNum=" + equipmentSerialNum
				+ ", basebandEdition=" + basebandEdition + ", browserUa=" + browserUa + ", electricityAvailable="
				+ electricityAvailable + ", moveCountryCode=" + moveCountryCode + ", moveNetworkCode=" + moveNetworkCode
				+ ", bootReachCurrent=" + bootReachCurrent + ", bootNoDormancy=" + bootNoDormancy + ", screenLong="
				+ screenLong + ", screenWide=" + screenWide + ", createTime=" + createTime + ", modifyTime="
				+ modifyTime + "]";
	}

	
	
}
