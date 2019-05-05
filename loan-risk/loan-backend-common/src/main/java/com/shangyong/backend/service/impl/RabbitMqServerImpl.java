package com.shangyong.backend.service.impl;

import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.cons.QueueConstants;
import com.shangyong.backend.common.enums.DingDingGroupIdEnum;
import com.shangyong.backend.entity.mq.RabbitMqAlarm;
import com.shangyong.backend.entity.mq.RabbitMqApplicationId;
import com.shangyong.backend.entity.mq.RabbitSms;
import com.shangyong.backend.service.RabbitMqServer;
import com.shangyong.backend.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *  消息发送
 * @author caisheng
 * @date 2018-07-16
 */
@Service
public class RabbitMqServerImpl implements RabbitMqServer {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

//    @Value(value="${spring.rabbitmq.url}")
    private String rabbitmqUrl;

    @Override
    public void sendMqForFraudScore(String applicationId) {
        RabbitMqApplicationId rma = new RabbitMqApplicationId();
        rma.setApplicationId(applicationId);
        Map<String, String> param = QueueConstants.buildParam(QueueConstants.QUEUE.FRAUD_SCORES, JSON.toJSONString(rma));
        String temp = HttpClientUtil.doPost(rabbitmqUrl, param);
        logger.info("[发送消息]至rabbitmq, applicationId=" +applicationId + ",rabbitmqUrl=" + rabbitmqUrl + ", rmmJson=" + JSON.toJSONString(param)+",result=" + temp);
    }

    @Override
    public void sendMqForFraudScoreOldUser(String applicationId) {
        RabbitMqApplicationId rma = new RabbitMqApplicationId();
        rma.setApplicationId(applicationId);
        Map<String, String> param = QueueConstants.buildParam(QueueConstants.QUEUE.FRAUD_SCORES_OLD_USER, JSON.toJSONString(rma));
        String temp = HttpClientUtil.doPost(rabbitmqUrl, param);
        logger.info("[发送消息]至rabbitmq, applicationId=" +applicationId + ",rabbitmqUrl=" + rabbitmqUrl + ", rmmJson=" + JSON.toJSONString(param) +",result=" + temp);
    }

    @Override
    public void sendMqForAlarmAppFailureTimes(long count) {
        RabbitMqAlarm rabbitMqAlarm = new RabbitMqAlarm();
        rabbitMqAlarm.setMsgContent("[风控系统] 失败次数超过4次的单子，已有[" + count + "]单，请及时处理");
        rabbitMqAlarm.setMsgType(DingDingGroupIdEnum.GROUP_TECHNOLOGY_DEP.getCode());

        Map<String, String> param = QueueConstants.buildParam(QueueConstants.QUEUE.ALARM_DING_DING, JSON.toJSONString(rabbitMqAlarm));
        String temp = HttpClientUtil.doPost(rabbitmqUrl, param);
        logger.info("[发送消息]至rabbitmq, rabbitmqUrl=" + rabbitmqUrl + ", rmmJson=" + JSON.toJSONString(param) +", result=" + temp);
    }

    @Override
    public void sendSms(RabbitSms rabbitSms) {
        Map<String, String> param = QueueConstants.buildParam(QueueConstants.QUEUE.ALARM_SMS, JSON.toJSONString(rabbitSms));
        String temp = HttpClientUtil.doPost(rabbitmqUrl, param);
        logger.info("[发送消息]至rabbitmq, rabbitmqUrl=" + rabbitmqUrl + ", rmmJson=" + JSON.toJSONString(param) +", result=" + temp);
    }
}
