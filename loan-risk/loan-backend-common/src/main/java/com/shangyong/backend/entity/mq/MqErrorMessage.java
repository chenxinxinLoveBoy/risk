package com.shangyong.backend.entity.mq;

import java.util.Date;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

public class MqErrorMessage extends BaseBo{

    /**
     * 主键 mq_error_message.hsid
     */
    private Long hsid;

    /**
     * 消息编号 mq_error_message.message_id
     */
    private String messageId;

    /**
     * 消息交换机 mq_error_message.message_exchange
     */
    private String messageExchange;

    /**
     * 消息路由键 mq_error_message.message_routingkey
     */
    private String messageRoutingkey;

    /**
     * 消息队列名称 mq_error_message.message_queue
     */
    private String messageQueue;

    /**
     * 消息管道类型  0-发送  1-接收 mq_error_message.message_channel_type
     */
    private Integer messageChannelType;

    /**
     * 消息所属业务    hbase/ mq_error_message.message_service
     */
    private String messageService;

    /**
     * 申请单号 mq_error_message.application_id
     */
    private String applicationId;

    /**
     * 消息状态  0-正常  1-未消费 2-消费处理失败 mq_error_message.message_status
     */
    private Integer messageStatus;

    /**
     * 消息类型  1-转发 2-广播  3-模糊匹配 mq_error_message.message_type
     */
    private Integer messageType;

    /**
     * 消息错误级别  1-警告 2-业务处理错误  3-消息丢失 mq_error_message.message_level
     */
    private Integer messageLevel;

    /**
     * IP地址 mq_error_message.ip
     */
    private String ip;

    /**
     * 是否删除 0未删除  1已删除 mq_error_message.flag_del
     */
    private Integer flagDel;

    /**
     * 创建时间 mq_error_message.create_time
     */
    private Date createTime;

    /**
     * 创建人 mq_error_message.create_man
     */
    private String createMan;

    /**
     * 修改人 mq_error_message.modify_man
     */
    private String modifyMan;

    /**
     * 修改时间 mq_error_message.modify_time
     */
    private Date modifyTime;

    /**
     * 消息内容 mq_error_message.message_body
     */
    private String messageBody;

    /**
     * 消息全部信息 mq_error_message.message_info
     */
    private String messageInfo;

    private String optTimeBigen; // 查询条件，操作开始日期

    private String optTimeEnd;// 查询条件，操作结束日期

    public MqErrorMessage() {
        super();

    }

    public MqErrorMessage(Long hsid, String messageId, String messageExchange, String messageRoutingkey, String messageQueue, Integer messageChannelType, String messageService, String applicationId, Integer messageStatus, Integer messageType, Integer messageLevel, String ip, Integer flagDel, Date createTime, String createMan, String modifyMan, Date modifyTime, String messageBody, String messageInfo) {
        this.hsid = hsid;
        this.messageId = messageId;
        this.messageExchange = messageExchange;
        this.messageRoutingkey = messageRoutingkey;
        this.messageQueue = messageQueue;
        this.messageChannelType = messageChannelType;
        this.messageService = messageService;
        this.applicationId = applicationId;
        this.messageStatus = messageStatus;
        this.messageType = messageType;
        this.messageLevel = messageLevel;
        this.ip = ip;
        this.flagDel = flagDel;
        this.createTime = createTime;
        this.createMan = createMan;
        this.modifyMan = modifyMan;
        this.modifyTime = modifyTime;
        this.messageBody = messageBody;
        this.messageInfo = messageInfo;
    }

    public Long getHsid() {
        return hsid;
    }

    public MqErrorMessage setHsid(Long hsid) {
        this.hsid = hsid;
        return this;
    }

    public String getMessageId() {
        return messageId;
    }

    public MqErrorMessage setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
        return this;
    }

    public String getMessageExchange() {
        return messageExchange;
    }

    public MqErrorMessage setMessageExchange(String messageExchange) {
        this.messageExchange = messageExchange == null ? null : messageExchange.trim();
        return this;
    }

    public String getMessageRoutingkey() {
        return messageRoutingkey;
    }

    public MqErrorMessage setMessageRoutingkey(String messageRoutingkey) {
        this.messageRoutingkey = messageRoutingkey == null ? null : messageRoutingkey.trim();
        return this;
    }

    public String getMessageQueue() {
        return messageQueue;
    }

    public MqErrorMessage setMessageQueue(String messageQueue) {
        this.messageQueue = messageQueue == null ? null : messageQueue.trim();
        return this;
    }

    public Integer getMessageChannelType() {
        return messageChannelType;
    }

    public MqErrorMessage setMessageChannelType(Integer messageChannelType) {
        this.messageChannelType = messageChannelType;
        return this;
    }

    public String getMessageService() {
        return messageService;
    }

    public MqErrorMessage setMessageService(String messageService) {
        this.messageService = messageService == null ? null : messageService.trim();
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public MqErrorMessage setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
        return this;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public MqErrorMessage setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public MqErrorMessage setMessageType(Integer messageType) {
        this.messageType = messageType;
        return this;
    }

    public Integer getMessageLevel() {
        return messageLevel;
    }

    public MqErrorMessage setMessageLevel(Integer messageLevel) {
        this.messageLevel = messageLevel;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public MqErrorMessage setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
        return this;
    }

    public Integer getFlagDel() {
        return flagDel;
    }

    public MqErrorMessage setFlagDel(Integer flagDel) {
        this.flagDel = flagDel;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MqErrorMessage setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateMan() {
        return createMan;
    }

    public MqErrorMessage setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
        return this;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public MqErrorMessage setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public MqErrorMessage setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public MqErrorMessage setMessageBody(String messageBody) {
        this.messageBody = messageBody == null ? null : messageBody.trim();
        return this;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public MqErrorMessage setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo == null ? null : messageInfo.trim();
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