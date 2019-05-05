package com.shangyong.backend.entity.tdf;


/**
 * 同盾设备指纹表-IOSbean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityDeviceIos {

	/**主键id**/
	private String tdFacilityDeviceIOSId;

	/**申请单编号**/
	private String buApplicationId;

	/**系统类型**/
	private String os;

	/**SDK版本**/
	private String version;

	/**IDFV**/
	private String idfv;

	/**IDFA**/
	private String idfa;

	/**系统版本**/
	private String osVersion;

	/**UUID**/
	private String uuid;

	/**开机时间**/
	private String bootTime;

	/**运行时间**/
	private String currentTime;

	/**待机时间**/
	private String upTime;

	/**总容量**/
	private String totalSpace;

	/**可用容量**/
	private String freeSpace;

	/**内存**/
	private String memory;

	/**蜂窝网络IP**/
	private String cellIp;

	/**Wifi IP**/
	private String wifiIp;

	/**wifi子网掩码**/
	private String wifiNetmask;

	/**MAC地址**/
	private String mac;

	/**无线网络**/
	private String ssid;

	/**无线BSSID**/
	private String bssid;

	/**VPN IP**/
	private String vpnIp;

	/**vpn子网掩码**/
	private String vpnNetmask;

	/**网络接口列表**/
	private String networkNames;

	/**网络类型**/
	private String networkType;

	/**代理类型**/
	private String proxyType;

	/**代理地址**/
	private String proxyUrl;

	/**DNS**/
	private String dns;

	/**是否越狱**/
	private String jailbreak;

	/**设备类型**/
	private String platform;

	/**设备名称**/
	private String deviceName;

	/**亮度**/
	private String brightness;

	/**运营商**/
	private String carrier;

	/**国家代码**/
	private String countryIso;

	/**移动国家码**/
	private String mcc;

	/**移动网络号**/
	private String mnc;

	/**网络制式**/
	private String radioType;

	/**BundleID**/
	private String bundleId;

	/**应用版本号**/
	private String appVersion;

	/**时区**/
	private String timeZone;

	/**签名MD5**/
	private String signMd5;

	/**采集耗时**/
	private String timeCost;

	/**语言列表**/
	private String languages;

	/**充电状态**/
	private String batteryStatus;

	/**电量**/
	private String batteryLevel;

	/** 内核版本**/
	private String kernelVersion;

	/** GPS定位**/
	private String gpsLocation;

	/**定位开关**/
	private String gpsSwitch;

	/**定位授权状态**/
	private String gpsAuthStatus;

	/**环境变量**/
	private String env;

	/**动态库**/
	private String attached;

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


	public TdFacilityDeviceIos() {
		super();
	}
	public TdFacilityDeviceIos(String tdFacilityDeviceIOSId,String buApplicationId,String os,String version,String idfv,String idfa,String osVersion,String uuid,String bootTime,String currentTime,String upTime,String totalSpace,String freeSpace,String memory,String cellIp,String wifiIp,String wifiNetmask,String mac,String ssid,String bssid,String vpnIp,String vpnNetmask,String networkNames,String networkType,String proxyType,String proxyUrl,String dns,String jailbreak,String platform,String deviceName,String brightness,String carrier,String countryIso,String mcc,String mnc,String radioType,String bundleId,String appVersion,String timeZone,String signMd5,String timeCost,String languages,String batteryStatus,String batteryLevel,String kernelVersion,String gpsLocation,String gpsSwitch,String gpsAuthStatus,String env,String attached,String deviceId,String trueIp,String tokenId,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.tdFacilityDeviceIOSId = tdFacilityDeviceIOSId;
		this.buApplicationId = buApplicationId;
		this.os = os;
		this.version = version;
		this.idfv = idfv;
		this.idfa = idfa;
		this.osVersion = osVersion;
		this.uuid = uuid;
		this.bootTime = bootTime;
		this.currentTime = currentTime;
		this.upTime = upTime;
		this.totalSpace = totalSpace;
		this.freeSpace = freeSpace;
		this.memory = memory;
		this.cellIp = cellIp;
		this.wifiIp = wifiIp;
		this.wifiNetmask = wifiNetmask;
		this.mac = mac;
		this.ssid = ssid;
		this.bssid = bssid;
		this.vpnIp = vpnIp;
		this.vpnNetmask = vpnNetmask;
		this.networkNames = networkNames;
		this.networkType = networkType;
		this.proxyType = proxyType;
		this.proxyUrl = proxyUrl;
		this.dns = dns;
		this.jailbreak = jailbreak;
		this.platform = platform;
		this.deviceName = deviceName;
		this.brightness = brightness;
		this.carrier = carrier;
		this.countryIso = countryIso;
		this.mcc = mcc;
		this.mnc = mnc;
		this.radioType = radioType;
		this.bundleId = bundleId;
		this.appVersion = appVersion;
		this.timeZone = timeZone;
		this.signMd5 = signMd5;
		this.timeCost = timeCost;
		this.languages = languages;
		this.batteryStatus = batteryStatus;
		this.batteryLevel = batteryLevel;
		this.kernelVersion = kernelVersion;
		this.gpsLocation = gpsLocation;
		this.gpsSwitch = gpsSwitch;
		this.gpsAuthStatus = gpsAuthStatus;
		this.env = env;
		this.attached = attached;
		this.deviceId = deviceId;
		this.trueIp = trueIp;
		this.tokenId = tokenId;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTdFacilityDeviceIOSId(String tdFacilityDeviceIOSId){
		this.tdFacilityDeviceIOSId = tdFacilityDeviceIOSId;
	}

	public String getTdFacilityDeviceIOSId(){
		return this.tdFacilityDeviceIOSId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setOs(String os){
		this.os = os;
	}

	public String getOs(){
		return this.os;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return this.version;
	}

	public void setIdfv(String idfv){
		this.idfv = idfv;
	}

	public String getIdfv(){
		return this.idfv;
	}

	public void setIdfa(String idfa){
		this.idfa = idfa;
	}

	public String getIdfa(){
		return this.idfa;
	}

	public void setOsVersion(String osVersion){
		this.osVersion = osVersion;
	}

	public String getOsVersion(){
		return this.osVersion;
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

	public void setTotalSpace(String totalSpace){
		this.totalSpace = totalSpace;
	}

	public String getTotalSpace(){
		return this.totalSpace;
	}

	public void setFreeSpace(String freeSpace){
		this.freeSpace = freeSpace;
	}

	public String getFreeSpace(){
		return this.freeSpace;
	}

	public void setMemory(String memory){
		this.memory = memory;
	}

	public String getMemory(){
		return this.memory;
	}

	public void setCellIp(String cellIp){
		this.cellIp = cellIp;
	}

	public String getCellIp(){
		return this.cellIp;
	}

	public void setWifiIp(String wifiIp){
		this.wifiIp = wifiIp;
	}

	public String getWifiIp(){
		return this.wifiIp;
	}

	public void setWifiNetmask(String wifiNetmask){
		this.wifiNetmask = wifiNetmask;
	}

	public String getWifiNetmask(){
		return this.wifiNetmask;
	}

	public void setMac(String mac){
		this.mac = mac;
	}

	public String getMac(){
		return this.mac;
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

	public void setNetworkNames(String networkNames){
		this.networkNames = networkNames;
	}

	public String getNetworkNames(){
		return this.networkNames;
	}

	public void setNetworkType(String networkType){
		this.networkType = networkType;
	}

	public String getNetworkType(){
		return this.networkType;
	}

	public void setProxyType(String proxyType){
		this.proxyType = proxyType;
	}

	public String getProxyType(){
		return this.proxyType;
	}

	public void setProxyUrl(String proxyUrl){
		this.proxyUrl = proxyUrl;
	}

	public String getProxyUrl(){
		return this.proxyUrl;
	}

	public void setDns(String dns){
		this.dns = dns;
	}

	public String getDns(){
		return this.dns;
	}

	public void setJailbreak(String jailbreak){
		this.jailbreak = jailbreak;
	}

	public String getJailbreak(){
		return this.jailbreak;
	}

	public void setPlatform(String platform){
		this.platform = platform;
	}

	public String getPlatform(){
		return this.platform;
	}

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getDeviceName(){
		return this.deviceName;
	}

	public void setBrightness(String brightness){
		this.brightness = brightness;
	}

	public String getBrightness(){
		return this.brightness;
	}

	public void setCarrier(String carrier){
		this.carrier = carrier;
	}

	public String getCarrier(){
		return this.carrier;
	}

	public void setCountryIso(String countryIso){
		this.countryIso = countryIso;
	}

	public String getCountryIso(){
		return this.countryIso;
	}

	public void setMcc(String mcc){
		this.mcc = mcc;
	}

	public String getMcc(){
		return this.mcc;
	}

	public void setMnc(String mnc){
		this.mnc = mnc;
	}

	public String getMnc(){
		return this.mnc;
	}

	public void setRadioType(String radioType){
		this.radioType = radioType;
	}

	public String getRadioType(){
		return this.radioType;
	}

	public void setBundleId(String bundleId){
		this.bundleId = bundleId;
	}

	public String getBundleId(){
		return this.bundleId;
	}

	public void setAppVersion(String appVersion){
		this.appVersion = appVersion;
	}

	public String getAppVersion(){
		return this.appVersion;
	}

	public void setTimeZone(String timeZone){
		this.timeZone = timeZone;
	}

	public String getTimeZone(){
		return this.timeZone;
	}

	public void setSignMd5(String signMd5){
		this.signMd5 = signMd5;
	}

	public String getSignMd5(){
		return this.signMd5;
	}

	public void setTimeCost(String timeCost){
		this.timeCost = timeCost;
	}

	public String getTimeCost(){
		return this.timeCost;
	}

	public void setLanguages(String languages){
		this.languages = languages;
	}

	public String getLanguages(){
		return this.languages;
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

	public void setGpsSwitch(String gpsSwitch){
		this.gpsSwitch = gpsSwitch;
	}

	public String getGpsSwitch(){
		return this.gpsSwitch;
	}

	public void setGpsAuthStatus(String gpsAuthStatus){
		this.gpsAuthStatus = gpsAuthStatus;
	}

	public String getGpsAuthStatus(){
		return this.gpsAuthStatus;
	}

	public void setEnv(String env){
		this.env = env;
	}

	public String getEnv(){
		return this.env;
	}

	public void setAttached(String attached){
		this.attached = attached;
	}

	public String getAttached(){
		return this.attached;
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

}
