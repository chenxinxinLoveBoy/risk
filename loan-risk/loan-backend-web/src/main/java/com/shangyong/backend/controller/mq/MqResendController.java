package com.shangyong.backend.controller.mq;

import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.mq.MqResendMessage;
import com.shangyong.backend.service.mq.IMqResendMessageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "/backend/mq/resendlog/")
public class MqResendController {
    private static Logger logger = Logger.getLogger("mqMsgManagementLog");

    @Autowired
    private IMqResendMessageService mqResendMessageServiceImpl;

    @PostMapping("page")
    public void page(HttpServletResponse response, MqResendMessage mqResendMessage) {
        try {
            int count = mqResendMessageServiceImpl.count(mqResendMessage);// 统计
            List<MqResendMessage> mqResendMessages = mqResendMessageServiceImpl.pageQuery(mqResendMessage);
            JSONUtils.toListJSON(response, mqResendMessages, count);
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @PostMapping("resend")
    public void resend(HttpServletResponse response, Long[] hsIds) {
        MqResendMessage resend = null;
        try {
            for (Long hsId : hsIds) {

                MqResendMessage mqResendMessage = mqResendMessageServiceImpl.get(hsId);
                //重复rabbitmq消息--待续
                if (null != mqResendMessage) {

                    String messageRoutingkey = mqResendMessage.getMessageRoutingkey();
                    String messageQueue = mqResendMessage.getMessageQueue();

                    messageRoutingkey = StringUtils.remove(messageRoutingkey, "_RESEND");
                    messageQueue = StringUtils.remove(messageQueue, "_RESEND");

                    resend = new MqResendMessage();
                    resend.setApplicationId(mqResendMessage.getApplicationId());
                    resend.setMessageId(UUIDUtils.getUUID());
                    resend.setMessageExchange(mqResendMessage.getMessageExchange());
                    resend.setMessageRoutingkey(messageRoutingkey);
                    resend.setMessageQueue(messageQueue);
                    resend.setMessageChannelType(mqResendMessage.getMessageChannelType());
                    resend.setMessageService(mqResendMessage.getMessageService());
                    resend.setMessageType(mqResendMessage.getMessageType());
                    resend.setIp(InetAddress.getLocalHost().getHostAddress());
                    resend.setCreateTime(new Date());
                    resend.setCreateUserId(TokenManager.getUserId());
                    resend.setCreateMan(TokenManager.getUserName());
                    resend.setMessageBody(mqResendMessage.getMessageBody());
                    resend.setMessageInfo(mqResendMessage.getMessageInfo());
//                    RabbitUtil.sendHbase(mqResendMessage.getMessageBody(), mqResendMessage.getMessageExchange(), messageRoutingkey);
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
