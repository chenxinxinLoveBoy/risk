package com.shangyong.backend.bo.mq;

/**
 * mq批量发送
 */
public class MqBatchSendBo {
    /**
     * redis key
     */
    private String messageList;

    /**
     * 消息类型 重发或重新消费
     */
    private String messageSendType;

    public String getMessageList() {
        return messageList;
    }

    public void setMessageList(String messageList) {
        this.messageList = messageList;
    }

    public String getMessageSendType() {
        return messageSendType;
    }

    public void setMessageSendType(String messageSendType) {
        this.messageSendType = messageSendType;
    }

    public MqBatchSendBo(String messageList, String messageSendType) {
        this.messageList = messageList;
        this.messageSendType = messageSendType;
    }

    public MqBatchSendBo() {
    }
}
