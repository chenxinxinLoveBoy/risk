package com.shangyong.backend.entity.mq;

import java.util.Date;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

public class MqLogMessage extends BaseBo {
    /**
     * 主键 mq_log_message.hsid
     */
    private Long hsid;

    /**
     * 消息编号 mq_log_message.message_id
     */
    private String messageId;

    /**
     * 消息交换机 mq_log_message.message_exchange
     */
    private String messageExchange;

    /**
     * 消息路由键 mq_log_message.message_routingkey
     */
    private String messageRoutingkey;

    /**
     * 消息队列名称 mq_log_message.message_queue
     */
    private String messageQueue;

    /**
     * 消息管道类型  0-发送  1-接收 mq_log_message.message_channel_type
     */
    private Integer messageChannelType;

    /**
     * 消息所属业务    hbase/ mq_log_message.message_service
     */
    private String messageService;

    /**
     * 消息类型  1-转发 2-广播  3-模糊匹配 mq_log_message.message_type
     */
    private Integer messageType;

    /**
     * 申请单号 mq_log_message.application_id
     */
    private String applicationId;

    /**
     * IP地址 mq_log_message.ip
     */
    private String ip;

    /**
     * 创建时间 mq_log_message.create_time
     */
    private Date createTime;

    /**
     * 创建人 mq_log_message.create_man
     */
    private String createMan;

    /**
     * 修改人 mq_log_message.modify_man
     */
    private String modifyMan;

    /**
     * 修改时间 mq_log_message.modify_time
     */
    private Date modifyTime;

    /**
     * 1-未消费,2-消费成功,3-消费失败 mq_log_message.is_consumption
     */
    private String isConsumption;

    /**
     * 消息内容 mq_log_message.message_body
     */
    private String messageBody;

    /**
     * 消息全部信息 mq_log_message.message_info
     */
    private String messageInfo;

    private String optTimeBigen; // 查询条件，操作开始日期

    private String optTimeEnd;// 查询条件，操作结束日期

    public MqLogMessage(Long hsid, String messageId, String messageExchange, String messageRoutingkey, String messageQueue, Integer messageChannelType, String messageService, Integer messageType, String applicationId, String ip, Date createTime, String createMan, String modifyMan, Date modifyTime, String isConsumption,  String messageBody, String messageInfo) {
        this.hsid = hsid;
        this.messageId = messageId;
        this.messageExchange = messageExchange;
        this.messageRoutingkey = messageRoutingkey;
        this.messageQueue = messageQueue;
        this.messageChannelType = messageChannelType;
        this.messageService = messageService;
        this.messageType = messageType;
        this.applicationId = applicationId;
        this.ip = ip;
        this.createTime = createTime;
        this.createMan = createMan;
        this.modifyMan = modifyMan;
        this.modifyTime = modifyTime;
        this.isConsumption = isConsumption;
        this.messageBody = messageBody;
        this.messageInfo = messageInfo;
        this.isConsumption = isConsumption;

    }

    public MqLogMessage() {
        super();

    }

    public Long getHsid() {
        return hsid;
    }

    public MqLogMessage setHsid(Long hsid) {
        this.hsid = hsid;
        return this;
    }

    public String getMessageId() {
        return messageId;
    }

    public MqLogMessage setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
        return this;
    }

    public String getMessageExchange() {
        return messageExchange;
    }

    public MqLogMessage setMessageExchange(String messageExchange) {
        this.messageExchange = messageExchange == null ? null : messageExchange.trim();
        return this;
    }

    public String getMessageRoutingkey() {
        return messageRoutingkey;
    }

    public MqLogMessage setMessageRoutingkey(String messageRoutingkey) {
        this.messageRoutingkey = messageRoutingkey == null ? null : messageRoutingkey.trim();
        return this;
    }

    public String getMessageQueue() {
        return messageQueue;
    }

    public MqLogMessage setMessageQueue(String messageQueue) {
        this.messageQueue = messageQueue == null ? null : messageQueue.trim();
        return this;
    }

    public Integer getMessageChannelType() {
        return messageChannelType;
    }

    public MqLogMessage setMessageChannelType(Integer messageChannelType) {
        this.messageChannelType = messageChannelType;
        return this;
    }

    public String getMessageService() {
        return messageService;
    }

    public MqLogMessage setMessageService(String messageService) {
        this.messageService = messageService == null ? null : messageService.trim();
        return this;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public MqLogMessage setMessageType(Integer messageType) {
        this.messageType = messageType;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public MqLogMessage setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
        return this;
    }

    public String getIp() {
        return ip;
    }

    public MqLogMessage setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MqLogMessage setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateMan() {
        return createMan;
    }

    public MqLogMessage setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
        return this;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public MqLogMessage setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public MqLogMessage setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public MqLogMessage setMessageBody(String messageBody) {
        this.messageBody = messageBody == null ? null : messageBody.trim();
        return this;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public MqLogMessage setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo == null ? null : messageInfo.trim();
        return this;
    }

    public String getIsConsumption() {
        return isConsumption;
    }

    public MqLogMessage setIsConsumption(String isConsumption) {
        this.isConsumption = isConsumption == null ? null : isConsumption.trim();
        return this;
    }

    public String getOptTimeBigen() {
        return optTimeBigen;
    }

    public void setOptTimeBigen(String optTimeBigen) {
        this.optTimeBigen = optTimeBigen;
    }

    public String getOptTimeEnd() {
        return optTimeEnd;
    }

    public void setOptTimeEnd(String optTimeEnd) {
        this.optTimeEnd = optTimeEnd;
    }
}