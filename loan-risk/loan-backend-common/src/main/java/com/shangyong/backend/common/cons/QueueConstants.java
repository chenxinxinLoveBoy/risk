package com.shangyong.backend.common.cons;


import java.util.HashMap;
import java.util.Map;

public class QueueConstants {

    /**
     * 队列 定义
     */
    public static enum QUEUE {
        FRAUD_SCORES("/", "ex.fraud.scores", null),
        FRAUD_SCORES_OLD_USER("/", "ex.fraud.scores.old.user", null),

        ALARM_DING_DING("/msg-send-host","ex.ding.consumer","routing.key.msg.consumer"),
        ALARM_SMS("/msg-send-host","ex.sms.consumer","routing.key.msg.consumer")
        ;

        /**
         * 虚拟机
         */
        private String virtualHost;

        /**
         * 交换机
         */
        private String exchange;

        /**
         * 路由键
         */
        private String routingKey;

        QUEUE(String virtualHost, String exchange, String routingKey) {
            this.virtualHost = virtualHost;
            this.exchange = exchange;
            this.routingKey = routingKey;
        }

        public String getVirtualHost() {
            return virtualHost;
        }

        public String getExchange() {
            return exchange;
        }

        public String getRoutingKey() {
            return routingKey;
        }
    }

    public static final Map<String, String> buildParam(QUEUE queue, String msg){
        Map<String, String> param = new HashMap<>();

        param.put("virtualHost", queue.getVirtualHost());
        param.put("exchange", queue.getExchange());
        param.put("routingKey", queue.getRoutingKey());
        param.put("msg", msg );
        return param;
    }

}
