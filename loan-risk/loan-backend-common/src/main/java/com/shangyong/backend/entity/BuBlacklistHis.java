package com.shangyong.backend.entity;


/**
 * 征信平台拒绝客户历史表bean
 * @author xiajiyun
 * @date Fri Sep 01 22:35:56 CST 2017
 **/
public class BuBlacklistHis {

	/**历史拒绝编号**/
	private String blacklistHisId;

	/**拒绝编号**/
	private String blacklistId;

	/**APP名称：1-闪贷；2-速贷**/
	private Integer appName;

	/**平台用户账号**/
	private String platformId;

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

	/**被拒绝平台类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91征信、06-宜信、07-中智诚、08-催收监控、09-欺诈拒绝、10-白骑士、11-小视科技、12-葫芦、21-APP同步、22-人工审核）**/
	private String rejectType;

	/**被拒绝标志（0-是，1-否）**/
	private String rejectFlag;

	/**是否失效（0-是，1-否）**/
	private String isFailure;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	/**禁止项规则对应编号**/
	private String banCode;

	/**数据来源（01-闪贷贷前审核、02-闪贷贷后监控、03-APP同步、04-手工添加、05-大数据添加）**/
	private String dsSource;

	/**流水编号(借款申请编号、贷后推送流水号)**/
	private String sNumber;

	/**删除时间**/
	private String deleteTime;

	/**删除人**/
	private String deleteMan;


	public BuBlacklistHis() {
		super();
	}
	public BuBlacklistHis(String blacklistHisId,String blacklistId,Integer appName,String platformId,String customerId,String name,String certType,String certCode,String phoneNum,String deviceId,String rejectType,String rejectFlag,String isFailure,String createTime,String createMan,String modifyTime,String modifyMan,String remark,String banCode,String dsSource,String sNumber,String deleteTime,String deleteMan) {
		super();
		this.blacklistHisId = blacklistHisId;
		this.blacklistId = blacklistId;
		this.appName = appName;
		this.platformId = platformId;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.deviceId = deviceId;
		this.rejectType = rejectType;
		this.rejectFlag = rejectFlag;
		this.isFailure = isFailure;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.banCode = banCode;
		this.dsSource = dsSource;
		this.sNumber = sNumber;
		this.deleteTime = deleteTime;
		this.deleteMan = deleteMan;
	}
	public void setBlacklistHisId(String blacklistHisId){
		this.blacklistHisId = blacklistHisId;
	}

	public String getBlacklistHisId(){
		return this.blacklistHisId;
	}

	public void setBlacklistId(String blacklistId){
		this.blacklistId = blacklistId;
	}

	public String getBlacklistId(){
		return this.blacklistId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setPlatformId(String platformId){
		this.platformId = platformId;
	}

	public String getPlatformId(){
		return this.platformId;
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

	public void setRejectType(String rejectType){
		this.rejectType = rejectType;
	}

	public String getRejectType(){
		return this.rejectType;
	}

	public void setRejectFlag(String rejectFlag){
		this.rejectFlag = rejectFlag;
	}

	public String getRejectFlag(){
		return this.rejectFlag;
	}

	public void setIsFailure(String isFailure){
		this.isFailure = isFailure;
	}

	public String getIsFailure(){
		return this.isFailure;
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

	public void setBanCode(String banCode){
		this.banCode = banCode;
	}

	public String getBanCode(){
		return this.banCode;
	}

	public void setDsSource(String dsSource){
		this.dsSource = dsSource;
	}

	public String getDsSource(){
		return this.dsSource;
	}

	public void setSNumber(String sNumber){
		this.sNumber = sNumber;
	}

	public String getSNumber(){
		return this.sNumber;
	}

	public void setDeleteTime(String deleteTime){
		this.deleteTime = deleteTime;
	}

	public String getDeleteTime(){
		return this.deleteTime;
	}

	public void setDeleteMan(String deleteMan){
		this.deleteMan = deleteMan;
	}

	public String getDeleteMan(){
		return this.deleteMan;
	}

}
