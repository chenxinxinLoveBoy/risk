package com.shangyong.backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.shangyong.backend.entity.AlarmCallBackInfo;
import com.shangyong.backend.mq.XnMessage;
import com.shangyong.utils.PropertiesUtil;

/**
 * 通过rabbitmq发送报警信息
 */
public class AlarmUtil {

    private static Logger logger = LoggerFactory.getLogger(AlarmUtil.class);

    private static final String RABBITMQ_API = PropertiesUtil.get("rabbitmq.api");

    /**
     * 发送报警信息到rabbitmq
     * @param info 报警消息信息
     * @param exchangeName
     * @param messageType 消息类型
     */
    public static void sendRabbitMq(AlarmCallBackInfo info, String exchangeName, String messageType) {
        XnMessage message = new XnMessage();
        message.setExchangeName(exchangeName);
        message.setMessageType(messageType);
        message.setMessageBody(JSON.toJSONString(info));
        String json = JSON.toJSONString(message);
        logger.info("调用RabbitMq发送报警信息参数:" + json);
        String response = RiskHttpClientUtil.doPostJson(RABBITMQ_API, json);
        logger.info("调用RabbitMq发送报警信息response:" + response);
    }
}
