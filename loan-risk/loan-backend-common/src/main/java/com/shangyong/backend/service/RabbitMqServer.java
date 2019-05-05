package com.shangyong.backend.service;

import com.shangyong.backend.entity.mq.RabbitSms;

/**
 * 发送消息
 * @author caisheng
 */
public interface RabbitMqServer {

    /**
     * 发送消息给 欺诈评分 队列
     * @param applicationId
     */
    public void sendMqForFraudScore(String applicationId);

    /**
     *  发送消息给 欺诈评分 队列 老用户 队列
     * @param applicationId
     */
    public void sendMqForFraudScoreOldUser(String applicationId);

    /**
     * 发送 报警 消息
     * @param count
     */
    public void sendMqForAlarmAppFailureTimes(long count);

    /**
     * 发送 手机短信
     *
     * @param rabbitSms
     */
    public void sendSms(RabbitSms rabbitSms);
}
