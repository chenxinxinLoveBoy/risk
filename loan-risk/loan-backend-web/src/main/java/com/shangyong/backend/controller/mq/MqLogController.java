package com.shangyong.backend.controller.mq;

import com.alibaba.fastjson.JSONObject;
import com.shangyong.backend.bo.mq.MqLogMessageBo;
import com.shangyong.backend.common.MQConstants;
import com.shangyong.backend.common.enums.mq.*;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.mq.MqLogMessage;
import com.shangyong.backend.entity.mq.MqResendMessage;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.mq.IMqLogMessageService;
import com.shangyong.backend.service.mq.IMqResendMessageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/mq/log/")
public class MqLogController {
    private static Logger logger = Logger.getLogger("mqMsgManagementLog");

    @Autowired
    private IMqLogMessageService mqLogMessageServiceImpl;

    @Autowired
    private IMqResendMessageService mqResendMessageServiceImpl;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @PostMapping("page")
    public void page(HttpServletResponse response, MqLogMessage mqLogMessage) {
        try {
            int count = mqLogMessageServiceImpl.count(mqLogMessage);// 统计
            List<MqLogMessage> mqLogMessages = mqLogMessageServiceImpl.pageQuery(mqLogMessage);
            JSONUtils.toListJSON(response, mqLogMessages, count);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 查询异常申请单消息
     *
     * @param response
     * @param mqLogMessage
     */
    @PostMapping("unusualList")
    public void unusualList(HttpServletResponse response, MqLogMessageBo mqLogMessage) {
        try {
            int count = mqLogMessageServiceImpl.countErrorApplication(mqLogMessage);// 统计
            List<MqLogMessageBo> mqLogMessages = mqLogMessageServiceImpl.findErrorApplication(mqLogMessage);
            JSONUtils.toListJSON(response, mqLogMessages, count);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 重发mq消息
     *
     * @param response
     * @param hsIds
     */
    @PostMapping("resend")
    public void resend(HttpServletResponse response, String[] hsIds) {
        MqResendMessage resend = null;
        try {
            for (String hsId : hsIds) {
                //MqLogMessage mqLogMessage = mqLogMessageServiceImpl.get(messageId);
                MqLogMessage mqLogMessage = mqLogMessageServiceImpl.get(Long.valueOf(hsId));
                //重复rabbitmq消息--待续
                if (null != mqLogMessage) {

                    String messageRoutingkey = mqLogMessage.getMessageRoutingkey();
                    String messageQueue = mqLogMessage.getMessageQueue();

                    messageRoutingkey = StringUtils.remove(messageRoutingkey, "_RESEND");
                    messageQueue = StringUtils.remove(messageQueue, "_RESEND");

                    resend = new MqResendMessage();
                    resend.setApplicationId(mqLogMessage.getApplicationId());
                    resend.setMessageId(UUIDUtils.getUUID());
                    resend.setMessageExchange(mqLogMessage.getMessageExchange());
                    resend.setMessageRoutingkey(messageRoutingkey);
                    resend.setMessageQueue(messageQueue);
                    resend.setMessageChannelType(mqLogMessage.getMessageChannelType());
                    resend.setMessageService(mqLogMessage.getMessageService());
                    resend.setMessageType(mqLogMessage.getMessageType());
                    resend.setIp(InetAddress.getLocalHost().getHostAddress());
                    resend.setCreateTime(new Date());
                    resend.setCreateUserId(TokenManager.getUserId());
                    resend.setCreateMan(TokenManager.getUserName());
                    resend.setMessageBody(mqLogMessage.getMessageBody());
                    resend.setMessageInfo(mqLogMessage.getMessageInfo());
//                    RabbitUtil.sendHbase(mqLogMessage.getMessageBody(), mqLogMessage.getMessageExchange(), messageRoutingkey);
                    mqResendMessageServiceImpl.save(resend);
                    resend = null;
                }
            }

            JSONUtils.toJSON(response, CodeUtils.SUCCESS);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage, e);
            if (resend != null)
                try {
                    resend.setMessageSendErrorInfo(errorMessage);
                    mqResendMessageServiceImpl.save(resend);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 发送消息至大数据MQ接口
     */
    @PostMapping("sendHbase")
    public void sendHbase(HttpServletResponse response, String[] applicationIds) {
        MqResendMessage resend = null;
        try {

            for (String applicationId : applicationIds) {

                Application application = applicationService.getObjectById(applicationId);

                if (null == application) {
                    logger.error("申请单号:" + applicationId + "未找到记录，消息发送失败");
                    continue;
                }
                String json = ReadJsonUtils.toJson(application);

                resend = new MqResendMessage();
                resend.setApplicationId(applicationId);
                resend.setMessageId(UUIDUtils.getUUID());
                resend.setMessageExchange(MQConstants.MQ_EXCHANGE_QZQD);
                resend.setMessageRoutingkey(MQConstants.MQ_ROUTINGKEY_QZQD);
                resend.setMessageQueue(MQConstants.MQ_QUEUE_QZQD);
                resend.setMessageChannelType(MessageChannelTypeEnum.SEND.getCode());
                resend.setMessageService("hbase");
                resend.setMessageType(MessageTypeEnum.LIKE.getCode());
                resend.setIp(InetAddress.getLocalHost().getHostAddress());
                resend.setCreateTime(new Date());
                resend.setCreateUserId(TokenManager.getUserId());
                resend.setCreateMan(TokenManager.getUserName());
                resend.setMessageBody(json);
                resend.setMessageInfo(json);
//                RabbitUtil.sendHbase(json, MQConstants.MQ_EXCHANGE_QZQD, StringUtils.remove(MQConstants.MQ_ROUTINGKEY_QZQD,".#"));
                mqResendMessageServiceImpl.save(resend);
                resend = null;
            }
            JSONUtils.toJSON(response, CodeUtils.SUCCESS);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage, e);
            if (resend != null)
                try {
                    resend.setMessageSendErrorInfo(errorMessage);
                    mqResendMessageServiceImpl.save(resend);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * mq 报表
     *
     * @param response
     * @param mqLogMessage
     */
    @GetMapping("mqReport.htm")
    public void countMq(HttpServletResponse response, MqLogMessage mqLogMessage) {
        try {
            net.sf.json.JSONObject jsonObject = mqLogMessageServiceImpl.mqReport(mqLogMessage);
            JSONUtils.toJSON(response, CodeUtils.SUCCESS, jsonObject, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @GetMapping("search-condition")
    public void getAlarmEnums(HttpServletResponse response) {
        try {
            Map<String, List<JSONObject>> map = new HashMap();
            map.put("flagDel", FlagDelEnum.returns());
            map.put("messageChannelType", MessageChannelTypeEnum.returns());
            map.put("messageLevel", MessageLevelEnum.returns());
            map.put("messageStatus", MessageStatusEnum.returns());
            map.put("messageType", MessageTypeEnum.returns());
            map.put("isConsumption", MessageIsConsumptionEnum.returns());
            JSONUtils.toJSON(response, CodeUtils.SUCCESS, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }
}
