package com.shangyong.backend.entity.mq;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * Rabbitmq队列信息表bean
 * @author kenzhao
 * @date Thu Apr 19 17:01:35 CST 2018
 **/
public class MqQueueInfo extends BaseBo {

	/**主键**/
	private Integer id;

	/**业务代码**/
	private String businessCode;

	/**征信代码**/
	private String creditCode;

	/**app名称**/
	private String appCode;

	/**父队列**/
	private Integer parentQueue;

	/**队列名称**/
	private String queue;

	/**交换机**/
	private String exchanges;

	/**路由键**/
	private String routingKey;

	/**队列负责人**/
	private String queueLeader;

	/**报警阀值**/
	private Integer alarmThreshold;

	/**超时时间(ms)**/
	private Integer timeOut;

	/**是否持久化(1-是,2-否)**/
	private Integer cache;

	/**队列最大长度**/
	private Integer maxLength;

	/**队列类型(1-业务队列,2-死信队列,3-备份队列)**/
	private Integer type;

	/**队列状态(1-运行中,2-准备中,3-停止中)**/
	private Integer status;

	/**备注**/
	private String remarks;

	/**修改时间**/
	private String modifyTime;

	/**创建时间**/
	private String createTime;

	/**绑定到rabbitmq(1-未绑定，2-已绑定)**/
	private Integer bind;

	/**功能编号**/
	private String featuresCode;

	/**队列睡眠时间**/
	private Integer sleepTime;


	public MqQueueInfo() {
		super();
	}
	public MqQueueInfo(Integer id,String businessCode,String creditCode,String appCode,Integer parentQueue,String queue,String exchanges,String routingKey,String queueLeader,Integer alarmThreshold,Integer timeOut,Integer cache,Integer maxLength,Integer type,Integer status,String remarks,String modifyTime,String createTime,Integer bind,String featuresCode,Integer sleepTime) {
		super();
		this.id = id;
		this.businessCode = businessCode;
		this.creditCode = creditCode;
		this.appCode = appCode;
		this.parentQueue = parentQueue;
		this.queue = queue;
		this.exchanges = exchanges;
		this.routingKey = routingKey;
		this.queueLeader = queueLeader;
		this.alarmThreshold = alarmThreshold;
		this.timeOut = timeOut;
		this.cache = cache;
		this.maxLength = maxLength;
		this.type = type;
		this.status = status;
		this.remarks = remarks;
		this.modifyTime = modifyTime;
		this.createTime = createTime;
		this.bind = bind;
		this.featuresCode = featuresCode;
		this.sleepTime = sleepTime;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setBusinessCode(String businessCode){
		this.businessCode = businessCode;
	}

	public String getBusinessCode(){
		return this.businessCode;
	}

	public void setCreditCode(String creditCode){
		this.creditCode = creditCode;
	}

	public String getCreditCode(){
		return this.creditCode;
	}

	public void setAppCode(String appCode){
		this.appCode = appCode;
	}

	public String getAppCode(){
		return this.appCode;
	}

	public void setParentQueue(Integer parentQueue){
		this.parentQueue = parentQueue;
	}

	public Integer getParentQueue(){
		return this.parentQueue;
	}

	public void setQueue(String queue){
		this.queue = queue;
	}

	public String getQueue(){
		return this.queue;
	}

	public void setExchanges(String exchanges){
		this.exchanges = exchanges;
	}

	public String getExchanges(){
		return this.exchanges;
	}

	public void setRoutingKey(String routingKey){
		this.routingKey = routingKey;
	}

	public String getRoutingKey(){
		return this.routingKey;
	}

	public void setQueueLeader(String queueLeader){
		this.queueLeader = queueLeader;
	}

	public String getQueueLeader(){
		return this.queueLeader;
	}

	public void setAlarmThreshold(Integer alarmThreshold){
		this.alarmThreshold = alarmThreshold;
	}

	public Integer getAlarmThreshold(){
		return this.alarmThreshold;
	}

	public void setTimeOut(Integer timeOut){
		this.timeOut = timeOut;
	}

	public Integer getTimeOut(){
		return this.timeOut;
	}

	public void setCache(Integer cache){
		this.cache = cache;
	}

	public Integer getCache(){
		return this.cache;
	}

	public void setMaxLength(Integer maxLength){
		this.maxLength = maxLength;
	}

	public Integer getMaxLength(){
		return this.maxLength;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public String getRemarks(){
		return this.remarks;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setBind(Integer bind){
		this.bind = bind;
	}

	public Integer getBind(){
		return this.bind;
	}

	public void setFeaturesCode(String featuresCode){
		this.featuresCode = featuresCode;
	}

	public String getFeaturesCode(){
		return this.featuresCode;
	}

	public void setSleepTime(Integer sleepTime){
		this.sleepTime = sleepTime;
	}

	public Integer getSleepTime(){
		return this.sleepTime;
	}

}
