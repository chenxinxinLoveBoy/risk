package com.shangyong.backend.controller.mq;

import com.shangyong.backend.common.enums.mq.MessageChannelTypeEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.mq.MqErrorMessage;
import com.shangyong.backend.entity.mq.MqResendMessage;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.mq.IMqErrorMessageService;
import com.shangyong.backend.service.mq.IMqResendMessageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.utils.UUIDUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/backend/mq/errorlog/")
public class MqErrorController {
    private static Logger logger = Logger.getLogger("mqMsgManagementLog");

    @Autowired
    private IMqErrorMessageService mqErrorMessageServiceImpl;

    @Autowired
    private IMqResendMessageService mqResendMessageServiceImpl;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @PostMapping("page")
    public void page(HttpServletResponse response, MqErrorMessage mqErrorMessage) {
        try {
            int count = mqErrorMessageServiceImpl.count(mqErrorMessage);// 统计
            List<MqErrorMessage> mqErrorMessages = mqErrorMessageServiceImpl.pageQuery(mqErrorMessage);
            JSONUtils.toListJSON(response, mqErrorMessages, count);
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @PostMapping("resend")
    public void resend(HttpServletResponse response, long[] hsIds) {

        MqResendMessage resend = null;
        try {

            for (long hsId : hsIds) {
                MqErrorMessage message = mqErrorMessageServiceImpl.get(hsId);
                //重复rabbitmq消息--待续
                if (null != message) {
                    String json = ReadJsonUtils.toJson(applicationService.getObjectById(message.getApplicationId()));
                    resend = new MqResendMessage();
                    resend.setMessageId(UUIDUtils.getUUID());
                    resend.setApplicationId(message.getApplicationId());
                    resend.setMessageExchange(message.getMessageExchange());
                    resend.setMessageRoutingkey(message.getMessageRoutingkey());
                    resend.setMessageQueue(message.getMessageQueue());
                    resend.setMessageChannelType(message.getMessageChannelType());
                    resend.setMessageService(message.getMessageService());
                    resend.setMessageType(message.getMessageType());
                    resend.setIp(InetAddress.getLocalHost().getHostAddress());
                    resend.setCreateTime(new Date());
                    resend.setCreateUserId(TokenManager.getUserId());
                    resend.setCreateMan(TokenManager.getUserName());
                    resend.setMessageBody(json);
                    resend.setMessageInfo(json);
                    if (MessageChannelTypeEnum.SEND.getCode() == message.getMessageChannelType()) {
                        /**
                         * 发送类型消息重新发送给大数据
                         */

//                        RabbitUtil.sendHbase(json, MQConstants.MQ_EXCHANGE_QZQD, StringUtils.remove(MQConstants.MQ_ROUTINGKEY_QZQD, ".#"));
                    }
                    if (MessageChannelTypeEnum.RECEIVE.getCode() == message.getMessageChannelType()) {
                        /**
                         * 接受类型消息重新消费
                         */
//                        RabbitUtil.sendHbase(json, MQConstants.MQ_EXCHANGE_QZQD_BACK, StringUtils.remove(MQConstants.MQ_ROUTINGKEY_QZQD_BACK, ".#"));
                    }
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
}
