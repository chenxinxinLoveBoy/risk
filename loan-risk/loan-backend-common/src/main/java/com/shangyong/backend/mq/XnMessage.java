package com.shangyong.backend.mq;

import java.io.Serializable;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/15
 *  * Time: 3:48
 *  * PROJECT_NAME: risk2.3.3
 *  * PACKAGE_NAME: com.shangyong.rabbitmq.base
 *  * DESC: 小牛信息对象
 *  * Version: v1.0.0
 *  
 */
public class XnMessage implements Serializable {

    private static final long serialVersionUID = -955848178701129240L;

    public XnMessage() {
    }

    public XnMessage(String exchangeName, String messageType, String messageBody) {
        this.exchangeName = exchangeName;
        this.messageType = messageType;
        this.messageBody = messageBody;
    }

    /**
     * 交换机名称
     */
    private String exchangeName;

    /**
     * 路由键key
     */
    private String messageType;

    /**
     * 信息内容
     */
    private String messageBody;


    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
