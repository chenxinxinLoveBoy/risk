package com.shangyong.backend.bo.mq;

import java.util.Date;

import com.shangyong.backend.entity.mq.MqLogMessage;

/**
 * mq消息记录bo
 */
public class MqLogMessageBo extends MqLogMessage {

    private String optTimeBigen; // 查询条件，操作开始日期
    private String optTimeEnd;// 查询条件，操作结束日期
    private Long receiveHsid;
    private String receiveMessageId;//接收消息id
    private String receiveApplicationId; //申请单号
    private Date receiveCreateTime;//接收时间
    private Integer receiveMessageChannelType;//通道类型
    private String receiveMessageBody;//接收消息内容
    private String receiveMessageInfo;//接收消息信息
    private String receiveOptTimeBigen; // 查询条件，操作开始日期
    private String receiveOptTimeEnd;// 查询条件，操作结束日期

    public String getReceiveMessageId() {
        return receiveMessageId;
    }

    public void setReceiveMessageId(String receiveMessageId) {
        this.receiveMessageId = receiveMessageId;
    }

    public String getReceiveApplicationId() {
        return receiveApplicationId;
    }

    public void setReceiveApplicationId(String receiveApplicationId) {
        this.receiveApplicationId = receiveApplicationId;
    }

    public Date getReceiveCreateTime() {
        return receiveCreateTime;
    }

    public void setReceiveCreateTime(Date receiveCreateTime) {
        this.receiveCreateTime = receiveCreateTime;
    }

    public Integer getReceiveMessageChannelType() {
        return receiveMessageChannelType;
    }

    public void setReceiveMessageChannelType(Integer receiveMessageChannelType) {
        this.receiveMessageChannelType = receiveMessageChannelType;
    }

    public String getReceiveMessageBody() {
        return receiveMessageBody;
    }

    public void setReceiveMessageBody(String receiveMessageBody) {
        this.receiveMessageBody = receiveMessageBody;
    }

    public String getReceiveMessageInfo() {
        return receiveMessageInfo;
    }

    public void setReceiveMessageInfo(String receiveMessageInfo) {
        this.receiveMessageInfo = receiveMessageInfo;
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

    public String getReceiveOptTimeBigen() {
        return receiveOptTimeBigen;
    }

    public void setReceiveOptTimeBigen(String receiveOptTimeBigen) {
        this.receiveOptTimeBigen = receiveOptTimeBigen;
    }

    public String getReceiveOptTimeEnd() {
        return receiveOptTimeEnd;
    }

    public void setReceiveOptTimeEnd(String receiveOptTimeEnd) {
        this.receiveOptTimeEnd = receiveOptTimeEnd;
    }

    public Long getReceiveHsid() {
        return receiveHsid;
    }

    public void setReceiveHsid(Long receiveHsid) {
        this.receiveHsid = receiveHsid;
    }

    public MqLogMessageBo(String optTimeBigen, String optTimeEnd, Long receiveHsid, String receiveMessageId, String receiveApplicationId, Date receiveCreateTime, Integer receiveMessageChannelType, String receiveMessageBody, String receiveMessageInfo, String receiveOptTimeBigen, String receiveOptTimeEnd) {
        this.optTimeBigen = optTimeBigen;
        this.optTimeEnd = optTimeEnd;
        this.receiveHsid = receiveHsid;
        this.receiveMessageId = receiveMessageId;
        this.receiveApplicationId = receiveApplicationId;
        this.receiveCreateTime = receiveCreateTime;
        this.receiveMessageChannelType = receiveMessageChannelType;
        this.receiveMessageBody = receiveMessageBody;
        this.receiveMessageInfo = receiveMessageInfo;
        this.receiveOptTimeBigen = receiveOptTimeBigen;
        this.receiveOptTimeEnd = receiveOptTimeEnd;
    }

    public MqLogMessageBo() {
    }
}
