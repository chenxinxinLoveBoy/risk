package com.shangyong.backend.entity;


/**
 * 客户提额申请明细表bean
 * @author es_ai
 * @date Thu Sep 14 13:47:20 CST 2017
 **/
public class PromoteDetailed {

	/**提额明细表编号**/
	private String promoteDetailedId;

	/**客户编号**/
	private String customerId;

	/**APP名称：1-闪贷；2-速贷**/
	private String appName;

	/**数据存储类型 06002- 聚信立学信 06003- 聚信立电商 06004- 聚信立社保 06005- 聚信立公积金 06006-聚信立人行征信**/
	private String taskType;

	/**任务编号**/
	private String taskId;

	/**app下载渠道**/
	private String appChannel;

	/**客户标识（0-新客户，1-老客户）**/
	private String appLevel;

	/**用户已还款次数**/
	private String refundConut;

	/**当天借当天还次数**/
	private String dayLoanCount;

	/**响应josn报文存储地址**/
	private String josnStoragePath;

	/**采集状态 0 未采集 1采集成功 2采集失败**/
	private String collectState;

	/**是否推送app 0 未推送 1已推送**/
	private String pushType;

	/**app请求流水号**/
	private String appSerialNumber;

	/**客户已有额度**/
	private String existingMoney;

	/**客户新增额度**/
	private String increaseMoney;

	/**失败次数**/
	private String failureTimes;

	/**步骤异常信息描述**/
	private String errorDescription;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**备注**/
	private String remark;

	/**是否有社保**/
	private String isShebao;
	
	/**是否有公积金**/
	private String isGongjijin;
	
	//用户姓名
	private String userName;
	//用户身份证号
	private String idCard;
	//用户手机号
	private String phone;

	public PromoteDetailed() {
		super();
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

	public void setAppName(String appName){
		this.appName = appName;
	}

	public String getAppName(){
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

	public void setAppLevel(String appLevel){
		this.appLevel = appLevel;
	}

	public String getAppLevel(){
		return this.appLevel;
	}

	public void setRefundConut(String refundConut){
		this.refundConut = refundConut;
	}

	public String getRefundConut(){
		return this.refundConut;
	}

	public void setDayLoanCount(String dayLoanCount){
		this.dayLoanCount = dayLoanCount;
	}

	public String getDayLoanCount(){
		return this.dayLoanCount;
	}

	public void setJosnStoragePath(String josnStoragePath){
		this.josnStoragePath = josnStoragePath;
	}

	public String getJosnStoragePath(){
		return this.josnStoragePath;
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

	public void setFailureTimes(String failureTimes){
		this.failureTimes = failureTimes;
	}

	public String getFailureTimes(){
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
	public String getIsShebao() {
		return isShebao;
	}
	public void setIsShebao(String isShebao) {
		this.isShebao = isShebao;
	}
	public String getIsGongjijin() {
		return isGongjijin;
	}
	public void setIsGongjijin(String isGongjijin) {
		this.isGongjijin = isGongjijin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public PromoteDetailed(String promoteDetailedId, String customerId, String appName, String taskType, String taskId,
			String appChannel, String appLevel, String refundConut, String dayLoanCount, String josnStoragePath,
			String collectState, String pushType, String appSerialNumber, String existingMoney, String increaseMoney,
			String failureTimes, String errorDescription, String createTime, String modifyTime, String remark,
			String isShebao, String isGongjijin, String userName, String idCard, String phone) {
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
		this.josnStoragePath = josnStoragePath;
		this.collectState = collectState;
		this.pushType = pushType;
		this.appSerialNumber = appSerialNumber;
		this.existingMoney = existingMoney;
		this.increaseMoney = increaseMoney;
		this.failureTimes = failureTimes;
		this.errorDescription = errorDescription;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.remark = remark;
		this.isShebao = isShebao;
		this.isGongjijin = isGongjijin;
		this.userName = userName;
		this.idCard = idCard;
		this.phone = phone;
	}
}
