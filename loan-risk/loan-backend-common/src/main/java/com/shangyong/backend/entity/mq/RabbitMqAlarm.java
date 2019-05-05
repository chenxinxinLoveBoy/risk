package com.shangyong.backend.entity.mq;

import java.io.Serializable;

/**
 * rabbit mq 钉钉报警 实体
 * @author caisheng
 * @date 2018-08-06
 */
public class RabbitMqAlarm implements Serializable {

    /**
     * 发送的内容
     */
    private String msgContent;

    /**
     * 发送的钉钉群编号（int类型）
     * @see com.shangyong.backend.common.enums.DingDingGroupIdEnum
     */
    private int msgType;

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }


}
