package com.shangyong.backend.entity;



/**
 * 黑名单-设备bean
 * @author 曾繁棋
 * @date Wed Aug 01 14:09:41 CST 2018
 **/
public class BuBlacklistDevice {

	/**拒绝编号**/
	private String blacklistId;

	/**APP应用客户编号**/
	private String customerId;

	/**客户姓名**/
	private String name;

	/**证件类型 ： 1.身份证 2.护照 3.其他**/
	private String certType;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**设备ID**/
	private String deviceId;

	/**MAC地址**/
	private String macAddress;

	/**真实IP地址**/
	private String ipAddress;

	/**是否失效（0-是，1-否）**/
	private String isFailure;

	/**创建时间**/
	private java.util.Date createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private java.util.Date modifyTime;

	/**修改人**/
	private String modifyMan;


	public BuBlacklistDevice() {
		super();
	}
	public BuBlacklistDevice(String blacklistId,String customerId,String name,String certType,String certCode,String phoneNum,String deviceId,String macAddress,String ipAddress,String isFailure,java.util.Date createTime,String createMan,java.util.Date modifyTime,String modifyMan) {
		super();
		this.blacklistId = blacklistId;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.deviceId = deviceId;
		this.macAddress = macAddress;
		this.ipAddress = ipAddress;
		this.isFailure = isFailure;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}
	public void setBlacklistId(String blacklistId){
		this.blacklistId = blacklistId;
	}

	public String getBlacklistId(){
		return this.blacklistId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertType(){
		return this.certType;
	}

	public void setCertCode(String certCode){
		this.certCode = certCode;
	}

	public String getCertCode(){
		return this.certCode;
	}

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return this.deviceId;
	}

	public void setMacAddress(String macAddress){
		this.macAddress = macAddress;
	}

	public String getMacAddress(){
		return this.macAddress;
	}

	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

	public String getIpAddress(){
		return this.ipAddress;
	}

	public void setIsFailure(String isFailure){
		this.isFailure = isFailure;
	}

	public String getIsFailure(){
		return this.isFailure;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

}
