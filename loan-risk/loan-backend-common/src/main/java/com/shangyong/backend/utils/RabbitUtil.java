package com.shangyong.backend.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.mq.XnMessage;
import com.shangyong.utils.RedisUtils;

import org.apache.commons.lang3.StringUtils;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过rabbitmq发送信息
 */
@Component
public class RabbitUtil {

    private static Logger logger = LoggerFactory.getLogger("sendMQLog");

    /**
     * 发送消息至大数据
     *
     * @param info
     * @param exchangeName
     * @param messageType
     * @return
     */
//    public static boolean sendHbase(String info, String exchangeName, String messageType) {
//        return send(info, Constants.BACKEND_MQ_SEND_MESSAGE_URL, exchangeName, messageType);
//    }

    /**
     * 发送消息给风控
     *
     * @param info
     * @param exchangeName
     * @param messageType
     * @return
     */
//    public static boolean send(String info, String exchangeName, String messageType) {
//        return send(info, Constants.MQ_SEND_BACKEND_MESSAGE_URL, exchangeName, messageType);
//    }

    /**
     * 批量发送消息给大数据
     *
     * @param info
     * @param exchangeName
     * @param messageType
     * @return
     */
//    public static boolean sendBatchHbase(String info, String exchangeName, String messageType) {
//        return send(info, Constants.BATCH_MQ_SEND_MESSAGE_TO_HBASE_URL, exchangeName, messageType);
//    }

    /**
     * 批量发送消息给风控
     *
     * @param info
     * @param exchangeName
     * @param messageType
     * @return
     */
//    public static boolean sendBatchBackEnd(String info, String exchangeName, String messageType) {
//        return send(info, Constants.BATCH_MQ_SEND_MESSAGE_TO_BACKEND_URL, exchangeName, messageType);
//    }

    /**
     * 发送报警信息到rabbitmq
     *
     * @param info         消息信息
     * @param exchangeName
     * @param messageType  消息类型
     * @return true ok，fasle fail
     */
    private static boolean send(String info, String url, String exchangeName, String messageType) {
        XnMessage message = new XnMessage();
        message.setExchangeName(exchangeName);
        message.setMessageType(messageType);
        message.setMessageBody(info);
        String json = JSON.toJSONString(message);
        // 获取参数值
        SysParam sysParamForMq = null;
//        logger.info("调用RabbitMq发送信息request:" + json);
        String value = RedisUtils.hget(RedisCons.RISK_SYS_PARAM_INFO, Constants.BACKEND_MQ_PROJECT);
        if (StringUtils.isNotBlank(value)) {
            try {
                // 转换redis中的json为参数实体
                sysParamForMq = (SysParam) SysParamUtils.JsonToBean(value, SysParam.class);
                if (Constants.STATE_FORBIDDEN.equals(sysParamForMq.getStatue())) {
                    logger.error("根据参数编号查询该参数状态为无效");
                    throw new RuntimeException("根据参数编号【" + Constants.BACKEND_MQ_PROJECT + "】查询该参数状态为无效");
                }
            } catch (Throwable e) {
                logger.error("querySysParamByParamValueRedis()String转Json服务异常：" + e.getMessage(), e);
                throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage());
            }
        }
        //MQ项目地址必须配置
        if (sysParamForMq == null || sysParamForMq.getParamValueOne() == null) {
//            logger.error("MQ项目系统参数：BACKEND_MQ_PROJECT-->地址未配置");
            return false;
        }

        String rabbitMqApi = sysParamForMq.getParamValueOne() + url;
        //String rabbitMqApi = "http://localhost:8081/backendMqCenter" + url;
        String response = RiskHttpClientUtil.doPostJson(rabbitMqApi, json);
        logger.info("调用RabbitMq发送信息response:" + response);
        if (StringUtils.isNotEmpty(response)) {
            JSONObject jsonObject = JSON.parseObject(response);
            if ("success".equals(jsonObject.getString("state")))
                return true;
        }
        return false;
    }
}
