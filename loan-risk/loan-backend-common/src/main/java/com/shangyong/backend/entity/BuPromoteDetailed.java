package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 客户提额申请明细表bean
 * @author kenzhao
 * @date Fri Sep 15 17:20:26 CST 2017
 **/
public class BuPromoteDetailed extends BaseBo{

	/**提额明细表编号**/
	private String promoteDetailedId;

	/**客户编号**/
	private String customerId;

	/**APP名称：1-闪贷；2-速贷**/
	private Integer appName;

	/**数据存储类型 06002- 聚信立学信 06003- 聚信立电商 06004- 聚信立社保 06005- 聚信立公积金 06006-聚信立人行征信**/
	private String taskType;

	/**任务编号**/
	private String taskId;

	/**app下载渠道**/
	private String appChannel;

	/**客户标识（0-新客户，1-老客户）**/
	private Integer appLevel;

	/**用户已还款次数**/
	private Integer refundConut;

	/**当天借当天还次数**/
	private Integer dayLoanCount;

	/**是否有公积金**/
	private Integer isGongjijin;

	/**是否有社保**/
	private Integer isShebao;

	/**响应josn报文存储地址**/
	private String josnStoragePath;

	/**客户已有额度**/
	private String existingMoney;

	/**客户新增额度**/
	private String increaseMoney;

	/**采集状态 0 未采集 1采集成功 2采集失败**/
	private String collectState;

	/**是否推送app 0 未推送 1已推送**/
	private String pushType;

	/**app请求流水号**/
	private String appSerialNumber;

	/**失败次数**/
	private Integer failureTimes;

	/**步骤异常信息描述**/
	private String errorDescription;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**备注**/
	private String remark;

	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;
	

	/**批量操作**/
	private String[] ids;

	/**
	 * 页面传值，非数据库表字段
	 */
	private String sendVal;
	/**
	 * 页面传值，非数据库表字段
	 */
	private String pushAppVal;
	
	/**
	 * 页面传值，非数据库表字段
	 */
	private Integer isException;
	
	
	/**
	 * 传给页面值，非数据库字段
	 */
	private String isRedisErroe;
	
	/**用户姓名**/
	private String userName;

	/**用户手机号**/
	private String phone;

	/**用户身份证号**/
	private String idCard;

	public BuPromoteDetailed() {
		super();
	}
	public BuPromoteDetailed(String promoteDetailedId,String customerId,Integer appName,String taskType,String taskId,String appChannel,Integer appLevel,Integer refundConut,Integer dayLoanCount,Integer isGongjijin,Integer isShebao,String josnStoragePath,String existingMoney,String increaseMoney,String collectState,String pushType,String appSerialNumber,Integer failureTimes,String errorDescription,String createTime,String modifyTime,String remark) {
		super();
		this.promoteDetailedId = promoteDetailedId;
		this.customerId = customerId;
		this.appName = appName;
		this.taskType = taskType;
		this.taskId = taskId;
		this.appChannel = appChannel;
		this.appLevel = appLevel;
		this.refundConut = refundConut;
		this.dayLoanCount = dayLoanCount;
		this.isGongjijin = isGongjijin;
		this.isShebao = isShebao;
		this.josnStoragePath = josnStoragePath;
		this.existingMoney = existingMoney;
		this.increaseMoney = increaseMoney;
		this.collectState = collectState;
		this.pushType = pushType;
		this.appSerialNumber = appSerialNumber;
		this.failureTimes = failureTimes;
		this.errorDescription = errorDescription;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.remark = remark;
	}
	
	 
	public Integer getIsException() {
		return isException;
	}
	public void setIsException(Integer isException) {
		this.isException = isException;
	}
	public String getSendVal() {
		return sendVal;
	}
	public void setSendVal(String sendVal) {
		this.sendVal = sendVal;
	}
	public String getPushAppVal() {
		return pushAppVal;
	}
	public void setPushAppVal(String pushAppVal) {
		this.pushAppVal = pushAppVal;
	}
	public void setPromoteDetailedId(String promoteDetailedId){
		this.promoteDetailedId = promoteDetailedId;
	}
	public String getPromoteDetailedId(){
		return this.promoteDetailedId;
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

	public void setTaskType(String taskType){
		this.taskType = taskType;
	}

	public String getTaskType(){
		return this.taskType;
	}

	public void setTaskId(String taskId){
		this.taskId = taskId;
	}

	public String getTaskId(){
		return this.taskId;
	}

	public void setAppChannel(String appChannel){
		this.appChannel = appChannel;
	}

	public String getAppChannel(){
		return this.appChannel;
	}

	public void setAppLevel(Integer appLevel){
		this.appLevel = appLevel;
	}

	public Integer getAppLevel(){
		return this.appLevel;
	}

	public void setRefundConut(Integer refundConut){
		this.refundConut = refundConut;
	}

	public Integer getRefundConut(){
		return this.refundConut;
	}

	public void setDayLoanCount(Integer dayLoanCount){
		this.dayLoanCount = dayLoanCount;
	}

	public Integer getDayLoanCount(){
		return this.dayLoanCount;
	}

	public void setIsGongjijin(Integer isGongjijin){
		this.isGongjijin = isGongjijin;
	}

	public Integer getIsGongjijin(){
		return this.isGongjijin;
	}

	public void setIsShebao(Integer isShebao){
		this.isShebao = isShebao;
	}

	public Integer getIsShebao(){
		return this.isShebao;
	}

	public void setJosnStoragePath(String josnStoragePath){
		this.josnStoragePath = josnStoragePath;
	}

	public String getJosnStoragePath(){
		return this.josnStoragePath;
	}

	public void setExistingMoney(String existingMoney){
		this.existingMoney = existingMoney;
	}

	public String getExistingMoney(){
		return this.existingMoney;
	}

	public void setIncreaseMoney(String increaseMoney){
		this.increaseMoney = increaseMoney;
	}

	public String getIncreaseMoney(){
		return this.increaseMoney;
	}

	public void setCollectState(String collectState){
		this.collectState = collectState;
	}

	public String getCollectState(){
		return this.collectState;
	}

	public void setPushType(String pushType){
		this.pushType = pushType;
	}

	public String getPushType(){
		return this.pushType;
	}

	public void setAppSerialNumber(String appSerialNumber){
		this.appSerialNumber = appSerialNumber;
	}

	public String getAppSerialNumber(){
		return this.appSerialNumber;
	}

	public void setFailureTimes(Integer failureTimes){
		this.failureTimes = failureTimes;
	}

	public Integer getFailureTimes(){
		return this.failureTimes;
	}

	public void setErrorDescription(String errorDescription){
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription(){
		return this.errorDescription;
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

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	 
	public String getIsRedisErroe() {
		return isRedisErroe;
	}
	public void setIsRedisErroe(String isRedisErroe) {
		this.isRedisErroe = isRedisErroe;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@Override
	public String toString() {
		return "BuPromoteDetailed [promoteDetailedId=" + promoteDetailedId + ", customerId=" + customerId + ", appName="
				+ appName + ", taskType=" + taskType + ", taskId=" + taskId + ", appChannel=" + appChannel
				+ ", appLevel=" + appLevel + ", refundConut=" + refundConut + ", dayLoanCount=" + dayLoanCount
				+ ", isGongjijin=" + isGongjijin + ", isShebao=" + isShebao + ", josnStoragePath=" + josnStoragePath
				+ ", existingMoney=" + existingMoney + ", increaseMoney=" + increaseMoney + ", collectState="
				+ collectState + ", pushType=" + pushType + ", appSerialNumber=" + appSerialNumber + ", failureTimes="
				+ failureTimes + ", errorDescription=" + errorDescription + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", remark=" + remark + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	
	
	
}
