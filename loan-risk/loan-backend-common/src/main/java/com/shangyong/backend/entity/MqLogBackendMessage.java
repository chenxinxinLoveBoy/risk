package com.shangyong.backend.entity;


/**
 * backend mq 通信日志表bean
 * @author kenzhao
 * @date Tue Sep 12 11:45:04 CST 2017
 **/
public class MqLogBackendMessage {

	/**主键**/
	private Long hsid;

	/**消息编号**/
	private String messageId;

	/**消息交换机**/
	private String messageExchange;

	/**消息路由键**/
	private String messageRoutingkey;

	/**消息队列名称**/
	private String messageQueue;

	/**消息内容**/
	private String messageBody;

	/**消息全部信息**/
	private String messageInfo;

	/**消息所属业务模块(前三位算是模块编号)    100001 app提现**/
	private String messageService;

	/**业务标识单号，也可以用关键字，方便查询**/
	private String serviceId;

	/**消息类型  1-转发 2-广播  3-模糊匹配**/
	private Integer messageType;

	/**消息错误级别  1-业务警告 2-业务处理错误  3-业务处理异常**/
	private Integer messageLevel;

	/**错误信息的简要说明**/
	private String remark;

	/**IP地址**/
	private String ip;

	/**是否删除 0未删除  1已删除**/
	private Integer flagDel;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改人**/
	private String modifyMan;

	/**修改时间**/
	private String modifyTime;


	public MqLogBackendMessage() {
		super();
	}
	public MqLogBackendMessage(Long hsid,String messageId,String messageExchange,String messageRoutingkey,String messageQueue,String messageBody,String messageInfo,String messageService,String serviceId,Integer messageType,Integer messageLevel,String remark,String ip,Integer flagDel,String createTime,String createMan,String modifyMan,String modifyTime) {
		super();
		this.hsid = hsid;
		this.messageId = messageId;
		this.messageExchange = messageExchange;
		this.messageRoutingkey = messageRoutingkey;
		this.messageQueue = messageQueue;
		this.messageBody = messageBody;
		this.messageInfo = messageInfo;
		this.messageService = messageService;
		this.serviceId = serviceId;
		this.messageType = messageType;
		this.messageLevel = messageLevel;
		this.remark = remark;
		this.ip = ip;
		this.flagDel = flagDel;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.modifyTime = modifyTime;
	}
	public void setHsid(Long hsid){
		this.hsid = hsid;
	}

	public Long getHsid(){
		return this.hsid;
	}

	public void setMessageId(String messageId){
		this.messageId = messageId;
	}

	public String getMessageId(){
		return this.messageId;
	}

	public void setMessageExchange(String messageExchange){
		this.messageExchange = messageExchange;
	}

	public String getMessageExchange(){
		return this.messageExchange;
	}

	public void setMessageRoutingkey(String messageRoutingkey){
		this.messageRoutingkey = messageRoutingkey;
	}

	public String getMessageRoutingkey(){
		return this.messageRoutingkey;
	}

	public void setMessageQueue(String messageQueue){
		this.messageQueue = messageQueue;
	}

	public String getMessageQueue(){
		return this.messageQueue;
	}

	public void setMessageBody(String messageBody){
		this.messageBody = messageBody;
	}

	public String getMessageBody(){
		return this.messageBody;
	}

	public void setMessageInfo(String messageInfo){
		this.messageInfo = messageInfo;
	}

	public String getMessageInfo(){
		return this.messageInfo;
	}

	public void setMessageService(String messageService){
		this.messageService = messageService;
	}

	public String getMessageService(){
		return this.messageService;
	}

	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}

	public String getServiceId(){
		return this.serviceId;
	}

	public void setMessageType(Integer messageType){
		this.messageType = messageType;
	}

	public Integer getMessageType(){
		return this.messageType;
	}

	public void setMessageLevel(Integer messageLevel){
		this.messageLevel = messageLevel;
	}

	public Integer getMessageLevel(){
		return this.messageLevel;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return this.ip;
	}

	public void setFlagDel(Integer flagDel){
		this.flagDel = flagDel;
	}

	public Integer getFlagDel(){
		return this.flagDel;
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

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

}
