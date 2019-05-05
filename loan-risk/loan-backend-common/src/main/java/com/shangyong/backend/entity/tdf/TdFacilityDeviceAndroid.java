package com.shangyong.backend.entity.tdf;


/**
 * 同盾设备指纹表-androidbean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityDeviceAndroid {

	/**主键id**/
	private String tdFacilityDeviceAndroidId;

	/**申请单编号**/
	private String buApplicationId;

	/**设备指纹版本**/
	private String fmVersion;

	/**系统类型**/
	private String os;

	/**SDK版本**/
	private String sdkVersion;

	/**Android版本**/
	private String releaseVersion;

	/**设备型号**/
	private String model;

	/**产品代号**/
	private String product;

	/**手机品牌**/
	private String brand;

	/**设备序列号**/
	private String serialNo;

	/**ROM编号**/
	private String display;

	/**HOST**/
	private String host;

	/**设备名称**/
	private String deviceName;

	/**硬件**/
	private String hardware;

	/**ROM标签**/
	private String tags;

	/**IMSI**/
	private String imsi;

	/**电话号码**/
	private String phoneNumber;

	/**IMEI**/
	private String imei;

	/**语音信箱号码**/
	private String voiceMail;

	/**SIM卡序列号**/
	private String simSerial;

	/**国家代码**/
	private String countryIso;

	/**移动运营商**/
	private String carrier;

	/**MNC**/
	private String mnc;

	/**MCC**/
	private String mcc;

	/**SIM卡运营商**/
	private String simOperator;

	/**手机制式**/
	private String phoneType;

	/**网络制式**/
	private String radioType;

	/**基站信息**/
	private String cellLocation;

	/**设备软件版本号**/
	private String deviceSVN;

	/**无线IP地址**/
	private String wifiIp;

	/**无线Mac地址**/
	private String wifiMac;

	/**无线网络名称**/
	private String ssid;

	/**无线BSSID**/
	private String bssid;

	/**网关地址**/
	private String gateway;

	/**WIFI子网掩码**/
	private String wifiNetmask;

	/**代理配置**/
	private String proxyInfo;

	/**DNS地址**/
	private String dnsAddress;

	/**VPN IP地址**/
	private String vpnIp;

	/**VPN子网掩码**/
	private String vpnNetmask;

	/**CELL IP地址**/
	private String cellIp;

	/**网络类型**/
	private String networkType;

	/**当前时间**/
	private String currentTime;

	/**待机时间**/
	private String upTime;

	/**开机时间**/
	private String bootTime;

	/**运行时间**/
	private String activeTime;

	/**是否ROOT**/
	private String root;

	/**应用包名**/
	private String packageName;

	/**应用版本号**/
	private String apkVersion;

	/**SDK MD5**/
	private String sdkMD5;

	/**签名 MD5**/
	private String signMD5;

	/**APK MD5**/
	private String apkMD5;

	/**时区**/
	private String timeZone;

	/**语言**/
	private String language;

	/**屏幕亮度**/
	private String brightness;

	/**充电状态**/
	private String batteryStatus;

	/**电量**/
	private String batteryLevel;

	/**电池温度**/
	private String batteryTemp;

	/** 屏幕分辨率**/
	private String screenRes;

	/** 字体列表HASH**/
	private String fontHash;

	/**蓝牙MAC地址**/
	private String blueMac;

	/**Android ID**/
	private String androidId;

	/**CPU主频**/
	private String cpuFrequency;

	/**CPU硬件**/
	private String cpuHardware;

	/**CPU型号**/
	private String cpuType;

	/**内存大小**/
	private String totalMemory;

	/**可用内存**/
	private String availableMemory;

	/**存储空间大小**/
	private String totalStorage;

	/**可用存储空间**/
	private String availableStorage;

	/**基带版本**/
	private String basebandVersion;

	/**内核版本**/
	private String kernelVersion;

	/**GPS定位**/
	private String gpsLocation;

	/**允许位置模拟**/
	private String allowMockLocation;

	/** 采集耗时**/
	private String initTime;

	/**设备ID**/
	private String deviceId;

	/**真实IP**/
	private String trueIp;

	/**tokenId**/
	private String tokenId;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public TdFacilityDeviceAndroid() {
		super();
	}
	public TdFacilityDeviceAndroid(String tdFacilityDeviceAndroidId,String buApplicationId,String fmVersion,String os,String sdkVersion,String releaseVersion,String model,String product,String brand,String serialNo,String display,String host,String deviceName,String hardware,String tags,String imsi,String phoneNumber,String imei,String voiceMail,String simSerial,String countryIso,String carrier,String mnc,String mcc,String simOperator,String phoneType,String radioType,String cellLocation,String deviceSVN,String wifiIp,String wifiMac,String ssid,String bssid,String gateway,String wifiNetmask,String proxyInfo,String dnsAddress,String vpnIp,String vpnNetmask,String cellIp,String networkType,String currentTime,String upTime,String bootTime,String activeTime,String root,String packageName,String apkVersion,String sdkMD5,String signMD5,String apkMD5,String timeZone,String language,String brightness,String batteryStatus,String batteryLevel,String batteryTemp,String screenRes,String fontHash,String blueMac,String androidId,String cpuFrequency,String cpuHardware,String cpuType,String totalMemory,String availableMemory,String totalStorage,String availableStorage,String basebandVersion,String kernelVersion,String gpsLocation,String allowMockLocation,String initTime,String deviceId,String trueIp,String tokenId,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.tdFacilityDeviceAndroidId = tdFacilityDeviceAndroidId;
		this.buApplicationId = buApplicationId;
		this.fmVersion = fmVersion;
		this.os = os;
		this.sdkVersion = sdkVersion;
		this.releaseVersion = releaseVersion;
		this.model = model;
		this.product = product;
		this.brand = brand;
		this.serialNo = serialNo;
		this.display = display;
		this.host = host;
		this.deviceName = deviceName;
		this.hardware = hardware;
		this.tags = tags;
		this.imsi = imsi;
		this.phoneNumber = phoneNumber;
		this.imei = imei;
		this.voiceMail = voiceMail;
		this.simSerial = simSerial;
		this.countryIso = countryIso;
		this.carrier = carrier;
		this.mnc = mnc;
		this.mcc = mcc;
		this.simOperator = simOperator;
		this.phoneType = phoneType;
		this.radioType = radioType;
		this.cellLocation = cellLocation;
		this.deviceSVN = deviceSVN;
		this.wifiIp = wifiIp;
		this.wifiMac = wifiMac;
		this.ssid = ssid;
		this.bssid = bssid;
		this.gateway = gateway;
		this.wifiNetmask = wifiNetmask;
		this.proxyInfo = proxyInfo;
		this.dnsAddress = dnsAddress;
		this.vpnIp = vpnIp;
		this.vpnNetmask = vpnNetmask;
		this.cellIp = cellIp;
		this.networkType = networkType;
		this.currentTime = currentTime;
		this.upTime = upTime;
		this.bootTime = bootTime;
		this.activeTime = activeTime;
		this.root = root;
		this.packageName = packageName;
		this.apkVersion = apkVersion;
		this.sdkMD5 = sdkMD5;
		this.signMD5 = signMD5;
		this.apkMD5 = apkMD5;
		this.timeZone = timeZone;
		this.language = language;
		this.brightness = brightness;
		this.batteryStatus = batteryStatus;
		this.batteryLevel = batteryLevel;
		this.batteryTemp = batteryTemp;
		this.screenRes = screenRes;
		this.fontHash = fontHash;
		this.blueMac = blueMac;
		this.androidId = androidId;
		this.cpuFrequency = cpuFrequency;
		this.cpuHardware = cpuHardware;
		this.cpuType = cpuType;
		this.totalMemory = totalMemory;
		this.availableMemory = availableMemory;
		this.totalStorage = totalStorage;
		this.availableStorage = availableStorage;
		this.basebandVersion = basebandVersion;
		this.kernelVersion = kernelVersion;
		this.gpsLocation = gpsLocation;
		this.allowMockLocation = allowMockLocation;
		this.initTime = initTime;
		this.deviceId = deviceId;
		this.trueIp = trueIp;
		this.tokenId = tokenId;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTdFacilityDeviceAndroidId(String tdFacilityDeviceAndroidId){
		this.tdFacilityDeviceAndroidId = tdFacilityDeviceAndroidId;
	}

	public String getTdFacilityDeviceAndroidId(){
		return this.tdFacilityDeviceAndroidId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setFmVersion(String fmVersion){
		this.fmVersion = fmVersion;
	}

	public String getFmVersion(){
		return this.fmVersion;
	}

	public void setOs(String os){
		this.os = os;
	}

	public String getOs(){
		return this.os;
	}

	public void setSdkVersion(String sdkVersion){
		this.sdkVersion = sdkVersion;
	}

	public String getSdkVersion(){
		return this.sdkVersion;
	}

	public void setReleaseVersion(String releaseVersion){
		this.releaseVersion = releaseVersion;
	}

	public String getReleaseVersion(){
		return this.releaseVersion;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return this.model;
	}

	public void setProduct(String product){
		this.product = product;
	}

	public String getProduct(){
		return this.product;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return this.brand;
	}

	public void setSerialNo(String serialNo){
		this.serialNo = serialNo;
	}

	public String getSerialNo(){
		return this.serialNo;
	}

	public void setDisplay(String display){
		this.display = display;
	}

	public String getDisplay(){
		return this.display;
	}

	public void setHost(String host){
		this.host = host;
	}

	public String getHost(){
		return this.host;
	}

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getDeviceName(){
		return this.deviceName;
	}

	public void setHardware(String hardware){
		this.hardware = hardware;
	}

	public String getHardware(){
		return this.hardware;
	}

	public void setTags(String tags){
		this.tags = tags;
	}

	public String getTags(){
		return this.tags;
	}

	public void setImsi(String imsi){
		this.imsi = imsi;
	}

	public String getImsi(){
		return this.imsi;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	public void setImei(String imei){
		this.imei = imei;
	}

	public String getImei(){
		return this.imei;
	}

	public void setVoiceMail(String voiceMail){
		this.voiceMail = voiceMail;
	}

	public String getVoiceMail(){
		return this.voiceMail;
	}

	public void setSimSerial(String simSerial){
		this.simSerial = simSerial;
	}

	public String getSimSerial(){
		return this.simSerial;
	}

	public void setCountryIso(String countryIso){
		this.countryIso = countryIso;
	}

	public String getCountryIso(){
		return this.countryIso;
	}

	public void setCarrier(String carrier){
		this.carrier = carrier;
	}

	public String getCarrier(){
		return this.carrier;
	}

	public void setMnc(String mnc){
		this.mnc = mnc;
	}

	public String getMnc(){
		return this.mnc;
	}

	public void setMcc(String mcc){
		this.mcc = mcc;
	}

	public String getMcc(){
		return this.mcc;
	}

	public void setSimOperator(String simOperator){
		this.simOperator = simOperator;
	}

	public String getSimOperator(){
		return this.simOperator;
	}

	public void setPhoneType(String phoneType){
		this.phoneType = phoneType;
	}

	public String getPhoneType(){
		return this.phoneType;
	}

	public void setRadioType(String radioType){
		this.radioType = radioType;
	}

	public String getRadioType(){
		return this.radioType;
	}

	public void setCellLocation(String cellLocation){
		this.cellLocation = cellLocation;
	}

	public String getCellLocation(){
		return this.cellLocation;
	}

	public void setDeviceSVN(String deviceSVN){
		this.deviceSVN = deviceSVN;
	}

	public String getDeviceSVN(){
		return this.deviceSVN;
	}

	public void setWifiIp(String wifiIp){
		this.wifiIp = wifiIp;
	}

	public String getWifiIp(){
		return this.wifiIp;
	}

	public void setWifiMac(String wifiMac){
		this.wifiMac = wifiMac;
	}

	public String getWifiMac(){
		return this.wifiMac;
	}

	public void setSsid(String ssid){
		this.ssid = ssid;
	}

	public String getSsid(){
		return this.ssid;
	}

	public void setBssid(String bssid){
		this.bssid = bssid;
	}

	public String getBssid(){
		return this.bssid;
	}

	public void setGateway(String gateway){
		this.gateway = gateway;
	}

	public String getGateway(){
		return this.gateway;
	}

	public void setWifiNetmask(String wifiNetmask){
		this.wifiNetmask = wifiNetmask;
	}

	public String getWifiNetmask(){
		return this.wifiNetmask;
	}

	public void setProxyInfo(String proxyInfo){
		this.proxyInfo = proxyInfo;
	}

	public String getProxyInfo(){
		return this.proxyInfo;
	}

	public void setDnsAddress(String dnsAddress){
		this.dnsAddress = dnsAddress;
	}

	public String getDnsAddress(){
		return this.dnsAddress;
	}

	public void setVpnIp(String vpnIp){
		this.vpnIp = vpnIp;
	}

	public String getVpnIp(){
		return this.vpnIp;
	}

	public void setVpnNetmask(String vpnNetmask){
		this.vpnNetmask = vpnNetmask;
	}

	public String getVpnNetmask(){
		return this.vpnNetmask;
	}

	public void setCellIp(String cellIp){
		this.cellIp = cellIp;
	}

	public String getCellIp(){
		return this.cellIp;
	}

	public void setNetworkType(String networkType){
		this.networkType = networkType;
	}

	public String getNetworkType(){
		return this.networkType;
	}

	public void setCurrentTime(String currentTime){
		this.currentTime = currentTime;
	}

	public String getCurrentTime(){
		return this.currentTime;
	}

	public void setUpTime(String upTime){
		this.upTime = upTime;
	}

	public String getUpTime(){
		return this.upTime;
	}

	public void setBootTime(String bootTime){
		this.bootTime = bootTime;
	}

	public String getBootTime(){
		return this.bootTime;
	}

	public void setActiveTime(String activeTime){
		this.activeTime = activeTime;
	}

	public String getActiveTime(){
		return this.activeTime;
	}

	public void setRoot(String root){
		this.root = root;
	}

	public String getRoot(){
		return this.root;
	}

	public void setPackageName(String packageName){
		this.packageName = packageName;
	}

	public String getPackageName(){
		return this.packageName;
	}

	public void setApkVersion(String apkVersion){
		this.apkVersion = apkVersion;
	}

	public String getApkVersion(){
		return this.apkVersion;
	}

	public void setSdkMD5(String sdkMD5){
		this.sdkMD5 = sdkMD5;
	}

	public String getSdkMD5(){
		return this.sdkMD5;
	}

	public void setSignMD5(String signMD5){
		this.signMD5 = signMD5;
	}

	public String getSignMD5(){
		return this.signMD5;
	}

	public void setApkMD5(String apkMD5){
		this.apkMD5 = apkMD5;
	}

	public String getApkMD5(){
		return this.apkMD5;
	}

	public void setTimeZone(String timeZone){
		this.timeZone = timeZone;
	}

	public String getTimeZone(){
		return this.timeZone;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return this.language;
	}

	public void setBrightness(String brightness){
		this.brightness = brightness;
	}

	public String getBrightness(){
		return this.brightness;
	}

	public void setBatteryStatus(String batteryStatus){
		this.batteryStatus = batteryStatus;
	}

	public String getBatteryStatus(){
		return this.batteryStatus;
	}

	public void setBatteryLevel(String batteryLevel){
		this.batteryLevel = batteryLevel;
	}

	public String getBatteryLevel(){
		return this.batteryLevel;
	}

	public void setBatteryTemp(String batteryTemp){
		this.batteryTemp = batteryTemp;
	}

	public String getBatteryTemp(){
		return this.batteryTemp;
	}

	public void setScreenRes(String screenRes){
		this.screenRes = screenRes;
	}

	public String getScreenRes(){
		return this.screenRes;
	}

	public void setFontHash(String fontHash){
		this.fontHash = fontHash;
	}

	public String getFontHash(){
		return this.fontHash;
	}

	public void setBlueMac(String blueMac){
		this.blueMac = blueMac;
	}

	public String getBlueMac(){
		return this.blueMac;
	}

	public void setAndroidId(String androidId){
		this.androidId = androidId;
	}

	public String getAndroidId(){
		return this.androidId;
	}

	public void setCpuFrequency(String cpuFrequency){
		this.cpuFrequency = cpuFrequency;
	}

	public String getCpuFrequency(){
		return this.cpuFrequency;
	}

	public void setCpuHardware(String cpuHardware){
		this.cpuHardware = cpuHardware;
	}

	public String getCpuHardware(){
		return this.cpuHardware;
	}

	public void setCpuType(String cpuType){
		this.cpuType = cpuType;
	}

	public String getCpuType(){
		return this.cpuType;
	}

	public void setTotalMemory(String totalMemory){
		this.totalMemory = totalMemory;
	}

	public String getTotalMemory(){
		return this.totalMemory;
	}

	public void setAvailableMemory(String availableMemory){
		this.availableMemory = availableMemory;
	}

	public String getAvailableMemory(){
		return this.availableMemory;
	}

	public void setTotalStorage(String totalStorage){
		this.totalStorage = totalStorage;
	}

	public String getTotalStorage(){
		return this.totalStorage;
	}

	public void setAvailableStorage(String availableStorage){
		this.availableStorage = availableStorage;
	}

	public String getAvailableStorage(){
		return this.availableStorage;
	}

	public void setBasebandVersion(String basebandVersion){
		this.basebandVersion = basebandVersion;
	}

	public String getBasebandVersion(){
		return this.basebandVersion;
	}

	public void setKernelVersion(String kernelVersion){
		this.kernelVersion = kernelVersion;
	}

	public String getKernelVersion(){
		return this.kernelVersion;
	}

	public void setGpsLocation(String gpsLocation){
		this.gpsLocation = gpsLocation;
	}

	public String getGpsLocation(){
		return this.gpsLocation;
	}

	public void setAllowMockLocation(String allowMockLocation){
		this.allowMockLocation = allowMockLocation;
	}

	public String getAllowMockLocation(){
		return this.allowMockLocation;
	}

	public void setInitTime(String initTime){
		this.initTime = initTime;
	}

	public String getInitTime(){
		return this.initTime;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return this.deviceId;
	}

	public void setTrueIp(String trueIp){
		this.trueIp = trueIp;
	}

	public String getTrueIp(){
		return this.trueIp;
	}

	public void setTokenId(String tokenId){
		this.tokenId = tokenId;
	}

	public String getTokenId(){
		return this.tokenId;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
	@Override
	public String toString() {
		return "TdFacilityDeviceAndroid [tdFacilityDeviceAndroidId=" + tdFacilityDeviceAndroidId + ", buApplicationId="
				+ buApplicationId + ", fmVersion=" + fmVersion + ", os=" + os + ", sdkVersion=" + sdkVersion
				+ ", releaseVersion=" + releaseVersion + ", model=" + model + ", product=" + product + ", brand="
				+ brand + ", serialNo=" + serialNo + ", display=" + display + ", host=" + host + ", deviceName="
				+ deviceName + ", hardware=" + hardware + ", tags=" + tags + ", imsi=" + imsi + ", phoneNumber="
				+ phoneNumber + ", imei=" + imei + ", voiceMail=" + voiceMail + ", simSerial=" + simSerial
				+ ", countryIso=" + countryIso + ", carrier=" + carrier + ", mnc=" + mnc + ", mcc=" + mcc
				+ ", simOperator=" + simOperator + ", phoneType=" + phoneType + ", radioType=" + radioType
				+ ", cellLocation=" + cellLocation + ", deviceSVN=" + deviceSVN + ", wifiIp=" + wifiIp + ", wifiMac="
				+ wifiMac + ", ssid=" + ssid + ", bssid=" + bssid + ", gateway=" + gateway + ", wifiNetmask="
				+ wifiNetmask + ", proxyInfo=" + proxyInfo + ", dnsAddress=" + dnsAddress + ", vpnIp=" + vpnIp
				+ ", vpnNetmask=" + vpnNetmask + ", cellIp=" + cellIp + ", networkType=" + networkType
				+ ", currentTime=" + currentTime + ", upTime=" + upTime + ", bootTime=" + bootTime + ", activeTime="
				+ activeTime + ", root=" + root + ", packageName=" + packageName + ", apkVersion=" + apkVersion
				+ ", sdkMD5=" + sdkMD5 + ", signMD5=" + signMD5 + ", apkMD5=" + apkMD5 + ", timeZone=" + timeZone
				+ ", language=" + language + ", brightness=" + brightness + ", batteryStatus=" + batteryStatus
				+ ", batteryLevel=" + batteryLevel + ", batteryTemp=" + batteryTemp + ", screenRes=" + screenRes
				+ ", fontHash=" + fontHash + ", blueMac=" + blueMac + ", androidId=" + androidId + ", cpuFrequency="
				+ cpuFrequency + ", cpuHardware=" + cpuHardware + ", cpuType=" + cpuType + ", totalMemory="
				+ totalMemory + ", availableMemory=" + availableMemory + ", totalStorage=" + totalStorage
				+ ", availableStorage=" + availableStorage + ", basebandVersion=" + basebandVersion + ", kernelVersion="
				+ kernelVersion + ", gpsLocation=" + gpsLocation + ", allowMockLocation=" + allowMockLocation
				+ ", initTime=" + initTime + ", deviceId=" + deviceId + ", trueIp=" + trueIp + ", tokenId=" + tokenId
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}

}
