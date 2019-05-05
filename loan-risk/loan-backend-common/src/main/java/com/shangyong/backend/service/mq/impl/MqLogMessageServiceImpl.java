package com.shangyong.backend.service.mq.impl;

import com.shangyong.backend.bo.mq.MqBatchSendBo;
import com.shangyong.backend.bo.mq.MqLogMessageBo;
import com.shangyong.backend.common.MQConstants;
import com.shangyong.backend.common.enums.mq.MessageChannelTypeEnum;
import com.shangyong.backend.common.enums.mq.MessageIsConsumptionEnum;
import com.shangyong.backend.common.enums.mq.MessageTypeEnum;
import com.shangyong.backend.common.enums.mq.MqResendTypeEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.mq.MqLogMessageDao;
import com.shangyong.backend.dao.mq.MqResendMessageDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.mq.MqLogMessage;
import com.shangyong.backend.entity.mq.MqResendMessage;
import com.shangyong.backend.service.mq.IMqLogMessageService;
import com.shangyong.backend.utils.RabbitUtil;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MqLogMessageServiceImpl implements IMqLogMessageService {

    private static Logger logger = Logger.getLogger("mqMsgManagementLog");

    @Autowired
    private MqLogMessageDao mqLogMessageDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private MqResendMessageDao mqResendMessageDao;

    /**
     * 添加mq发送记录
     *
     * @param message
     */
    @Override
    public void save(MqLogMessage message) {
        mqLogMessageDao.insertSelective(message);
    }

    /**
     * 根据主键修改消息
     *
     * @param message 需要修改的字段
     */
    @Override
    public void update(MqLogMessage message) {
        mqLogMessageDao.updateByPrimaryKeySelective(message);
    }

    /**
     * 删除消息
     *
     * @param hsid 主键id
     */
    @Override
    public void del(Long hsid) {
        mqLogMessageDao.deleteByPrimaryKey(hsid);
    }

    /**
     * 查询消息
     *
     * @param hsid 主键id
     * @return
     */
    @Override
    public MqLogMessage get(Long hsid) {
        return mqLogMessageDao.selectOne(new MqLogMessage().setHsid(hsid));
    }

    /**
     * 查询消息
     *
     * @param messagedId 消息id
     * @return
     */
    @Override
    public MqLogMessage get(String messagedId) {
        return mqLogMessageDao.selectOne(new MqLogMessage().setMessageId(messagedId));
    }

    /**
     * 查询
     *
     * @param message 条件
     * @return
     */
    @Override
    public List<MqLogMessage> list(MqLogMessage message) {
        return mqLogMessageDao.selectList(message);
    }

    /**
     * 查询异常单
     *
     * @param mqLogMessageBo
     * @return
     */
    @Override
    public List<MqLogMessageBo> findErrorApplication(MqLogMessageBo mqLogMessageBo) {
        return mqLogMessageDao.findErrorApplication(mqLogMessageBo);
    }

    /**
     * 查询异常单
     *
     * @param mqLogMessageBo
     * @return
     */
    @Override
    public int countErrorApplication(MqLogMessageBo mqLogMessageBo) {
        return mqLogMessageDao.countErrorApplication(mqLogMessageBo);
    }

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
    @Override
    public List<MqLogMessage> pageQuery(MqLogMessage message) {
        return mqLogMessageDao.pageQuery(message);
    }

    /**
     * 统计
     *
     * @param message
     * @return
     */
    @Override
    public int count(MqLogMessage message) {
        return mqLogMessageDao.count(message);
    }

//    /**
//     * 分页查询
//     *
//     * @param message
//     * @return
//     */
//    @Override
//    public List<MqLogMessageBo> pageQuery(MqLogMessageBo message) {
//
//        if (StringUtils.isNotEmpty(message.getOptTimeBigen()) && StringUtils.isNotEmpty(message.getOptTimeEnd())) {
//            return mqLogMessageDao.sendPageQuery(message);
//        }
//
//        if (StringUtils.isNotEmpty(message.getReceiveOptTimeBigen()) && StringUtils.isNotEmpty(message.getReceiveOptTimeEnd())) {
//            return mqLogMessageDao.receivePageQuery(message);
//        }
//
//        return null;
//    }

    /**
     * 统计
     *
     * @param message
     * @return
     */
//    @Override
//    public int count(MqLogMessageBo message) {
//
//        if (StringUtils.isNotEmpty(message.getOptTimeBigen()) && StringUtils.isNotEmpty(message.getOptTimeEnd())) {
//            return mqLogMessageDao.sendCount(message);
//        }
//
//        if (StringUtils.isNotEmpty(message.getReceiveOptTimeBigen()) && StringUtils.isNotEmpty(message.getReceiveOptTimeEnd())) {
//            return mqLogMessageDao.receiveCount(message);
//        }
//
//        return 0;
//    }

    /**
     * 添加mq发送记录
     *
     * @param message        消息
     * @param channelType    消息管道类型  0-发送  1-接收
     * @param messageService 消息所属业务
     * @param messageType    消息类型  1-转发 2-广播  3-模糊匹配
     * @param createMan      创建人
     * @param modifyMan      修改人
     * @return int 保存数量
     * @throws Exception
     */
    @Override
    public int saveByMessage(Message message, int channelType, String messageService, int messageType, String createMan, String modifyMan,MessageIsConsumptionEnum consumptionEnum) throws Exception {
        //非空校验
        if (message == null || null == message.getBody()) {
            return 0;
        }
        //将消息转换成字符串
        String body = new String(message.getBody());

        MessageProperties messageProperties = message.getMessageProperties();
        String applicationId = null;
        String messageId = null;
        try {

            Map<String, Object> bodyMap = ReadJsonUtils.fromJson(body, Map.class);
            applicationId = String.valueOf(bodyMap.get("applicationId"));
            if (StringUtils.isEmpty(applicationId) || StringUtils.equals(applicationId, "null"))
                applicationId = String.valueOf(bodyMap.get("application_id"));

        } catch (Exception ex) {
            //转换出错的不处理
        } finally {

            //messageId 设置为correlationIdString
            if (messageProperties != null) {
                messageId = messageProperties.getCorrelationIdString();
            }
            //若correlationIdString为空再获取messageId
            if (messageId == null && messageProperties != null) {
                messageId = messageProperties.getMessageId();
            }
            MqLogMessage mqLogMessage = new MqLogMessage();
            mqLogMessage.setApplicationId(applicationId);
            mqLogMessage.setMessageId(messageId);
            mqLogMessage.setMessageExchange(messageProperties.getReceivedExchange());
            mqLogMessage.setMessageRoutingkey(messageProperties.getReceivedRoutingKey());
            mqLogMessage.setMessageQueue(messageProperties.getConsumerQueue());
            mqLogMessage.setMessageChannelType(channelType);
            mqLogMessage.setMessageService(messageService);
            mqLogMessage.setMessageType(messageType);

            if (channelType == MessageChannelTypeEnum.RECEIVE.getCode()) {
                mqLogMessage.setIsConsumption(consumptionEnum.getCode());
            }

            InetAddress addr = InetAddress.getLocalHost();//获得本机IP  ;
            String ip = addr.getHostAddress();
            mqLogMessage.setIp(ip);
            mqLogMessage.setCreateTime(new Date());
            mqLogMessage.setCreateMan(createMan);
            mqLogMessage.setModifyMan(modifyMan);
            mqLogMessage.setModifyTime(new Date());
            mqLogMessage.setMessageBody(body);
            mqLogMessage.setMessageInfo(message.toString());

            return mqLogMessageDao.insertSelective(mqLogMessage);
        }
    }

    @Override
    public JSONObject mqReport(MqLogMessage mqLogMessage) {
        JSONObject object = new JSONObject();
        Application application = new Application();
        application.setIsHbaseSyn("1");
        application.setStartTime(mqLogMessage.getOptTimeBigen());
        application.setEndTime(mqLogMessage.getOptTimeEnd());
        //根据时间统计发送的总数
        object.put("sendCount", mqLogMessageDao.byApplicationIdDistinctCount(mqLogMessage.setMessageChannelType(MessageChannelTypeEnum.SEND.getCode())));
        //根据时间统计接收的总数
        object.put("receiveCount", mqLogMessageDao.byApplicationIdDistinctCount(mqLogMessage.setMessageChannelType(MessageChannelTypeEnum.RECEIVE.getCode())));
        //根据时间统计与大数据的交互总数
        object.put("hbaseMutualCount", applicationDao.countHbaseMutual(application));
        //根据时间统计待审批的总数
        application.setAuditingState("1");
        object.put("pendingCount", applicationDao.countHbaseMutual(application));
        //根据时间统计审批通过的总数
        application.setAuditingState("2");
        object.put("approvalOkCount", applicationDao.countHbaseMutual(application));
        //根据时间统计审批不通过的总数
        application.setAuditingState("3");
        object.put("approvalNoCount", applicationDao.countHbaseMutual(application));
        return object;
    }

    @Override
    public void updateByMessageIdIsConsumption(String messsageId, MessageIsConsumptionEnum isConsumption) {
        MqLogMessage mqLogMessage = new MqLogMessage();
        mqLogMessage.setMessageChannelType(MessageChannelTypeEnum.RECEIVE.getCode());
        mqLogMessage.setMessageId(messsageId);
        mqLogMessage.setIsConsumption(MessageIsConsumptionEnum.NOCONSUMPTION.getCode());
        MqLogMessage message = mqLogMessageDao.selectOne(mqLogMessage);
        if (null != message && message.getHsid() != null) {
            mqLogMessageDao.updateByPrimaryKeySelective(new MqLogMessage().setHsid(message.getHsid()).setIsConsumption(isConsumption.getCode()));
        }
//        mqLogMessageDao.updateIsConsumption(mqLogMessage);
    }

    /**
     * 根据条件批量发送消息至大数据
     *
     * @param application
     * @return
     */
    public int findApplicationIdByDate(Application application) throws Exception {
        List<Application> applicationIds = applicationDao.findApplicationIdByDate(application);
        if (null == applicationIds || applicationIds.size() == 0)
            return 0;

        List<String> jsons = new ArrayList<>();
        List<MqResendMessage> resends = new ArrayList<>();
        for (Application app : applicationIds) {
            MqResendMessage resend = new MqResendMessage();
            String json = ReadJsonUtils.toJson(app);
            resend.setMessageId(UUIDUtils.getUUID());
            resend.setApplicationId(app.getApplicationId());
            resend.setMessageId(UUIDUtils.getUUID());
            resend.setMessageExchange(MQConstants.MQ_EXCHANGE_QZQD);
            resend.setMessageRoutingkey(MQConstants.MQ_ROUTINGKEY_QZQD);
            resend.setMessageQueue(MQConstants.MQ_QUEUE_QZQD);
            resend.setMessageChannelType(MessageChannelTypeEnum.SEND.getCode());
            resend.setMessageService("Hbase");
            resend.setMessageType(MessageTypeEnum.LIKE.getCode());
            resend.setIp(InetAddress.getLocalHost().getHostAddress());
            resend.setCreateTime(new Date());
            resend.setCreateUserId(TokenManager.getUserId());
            resend.setCreateMan(TokenManager.getUserName());
            resend.setMessageBody(json);
            resend.setMessageInfo(json);
            resends.add(resend);
            jsons.add(json);
        }

        if (resends.size() == 0)
            return 0;

        String errorMessage = "";

        try {
            //tring[] array = applications.toArray(new String[applications.size()]);
            String json = ReadJsonUtils.toJson(jsons);
            //将需要重发的申请单号存放至redis，然后调用rabbitmq项目提供的api，将redis key传过去
            RedisUtils.setEx(RedisCons.MQ_MSG_RESEND_CODE, 12 * 60 * 60, json);
            /**
             * 组装消息结构发送给rabbitmq项目
             */
//            JSONObject object = new JSONObject();
//            object.put("messageList", RedisCons.MQ_MSG_RESEND_CODE);
//            object.put("messageSendType", "resendToHbase");

            MqBatchSendBo sendBo = new MqBatchSendBo();
            sendBo.setMessageList(RedisCons.MQ_MSG_RESEND_CODE);
            sendBo.setMessageSendType(MqResendTypeEnum.RESENDTOHBASE.toString());
//            RabbitUtil.sendBatchHbase(ReadJsonUtils.toJson(sendBo), MQConstants.MQ_EXCHANGE_QZQD, MQConstants.MQ_ROUTINGKEY_QZQD);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            //批量保存重复记录
            mqResendMessageDao.batchInsert(resends, errorMessage);
            logger.error(e.getMessage(), e);
            throw e;
        }
        //批量保存重复记录
        mqResendMessageDao.batchInsert(resends, errorMessage);
        return resends.size();
    }

    /**
     * 根据条件查询申请单进行批量消费
     *
     * @param application
     */
    @Override
    public int byApplicationBatchConsumption(Application application) throws Exception {

        List<Application> applicationIds = applicationDao.findApplicationIdByDate(application);
        if (null == applicationIds || applicationIds.size() == 0)
            return 0;

        List<String> jsons = new ArrayList<>();
        List<MqResendMessage> resends = new ArrayList<>();
        for (Application applicationId : applicationIds) {
            MqLogMessage logMessage = mqLogMessageDao.byApplicationIdAndIsConsumptionFind(applicationId.getApplicationId(), MessageIsConsumptionEnum.CONSUMPTIONFAIL.getCode());
            if (null == logMessage) {
                logger.error("根据申请单号(" + applicationId + ")查询消费记录为空");
                continue;
            }
            MqResendMessage resend = new MqResendMessage();
            resend.setMessageId(UUIDUtils.getUUID());
            resend.setApplicationId(applicationId.getApplicationId());
            resend.setMessageId(UUIDUtils.getUUID());
            resend.setMessageExchange(MQConstants.MQ_EXCHANGE_QZQD_BACK);
            resend.setMessageRoutingkey(MQConstants.MQ_ROUTINGKEY_QZQD_BACK);
            resend.setMessageQueue(MQConstants.MQ_QUEUE_QZQD_BACK);
            resend.setMessageChannelType(MessageChannelTypeEnum.RECEIVE.getCode());
            resend.setMessageService("Hbase");
            resend.setMessageType(MessageTypeEnum.LIKE.getCode());
            resend.setIp(InetAddress.getLocalHost().getHostAddress());
            resend.setCreateTime(new Date());
            resend.setCreateUserId(TokenManager.getUserId());
            resend.setCreateMan(TokenManager.getUserName());
            resend.setMessageBody(logMessage.getMessageBody());
            resend.setMessageInfo(logMessage.getMessageInfo());
            resends.add(resend);

            jsons.add(logMessage.getMessageBody());
        }

        if (resends.size() == 0)
            return 0;

        String errorMessage = "";

        try {
            //tring[] array = applications.toArray(new String[applications.size()]);
            String json = ReadJsonUtils.toJson(jsons);
            //将需要重发的申请单号存放至redis，然后调用rabbitmq项目提供的api，将redis key传过去
            RedisUtils.setEx(RedisCons.MQ_MSG_RESEND_BACKEND_CODE, 12 * 60 * 60, json);
            /**
             * 组装消息结构发送给rabbitmq项目
             */

            MqBatchSendBo sendBo = new MqBatchSendBo();
            sendBo.setMessageList(RedisCons.MQ_MSG_RESEND_BACKEND_CODE);
            sendBo.setMessageSendType(MqResendTypeEnum.RESENDTOBACKEND.toString());

//            RabbitUtil.sendBatchBackEnd(ReadJsonUtils.toJson(sendBo), MQConstants.MQ_EXCHANGE_QZQD_BACK, StringUtils.remove(MQConstants.MQ_ROUTINGKEY_QZQD_BACK, ".#"));
        } catch (Exception e) {
            errorMessage = e.getMessage();
            //批量保存重复记录
            mqResendMessageDao.batchInsert(resends, errorMessage);
            logger.error(e.getMessage(), e);
            throw e;
        }
        //批量保存重复记录

        mqResendMessageDao.batchInsert(resends, errorMessage);

        return jsons.size();
    }
    
    
    
    /**
     * 根据条件批量补发推送消息数据至APP
     *
     * @param application
     * @return
     */
    public int findPushAppStatusByDate(Application application) throws Exception {
        List<Application> applicationIds = applicationDao.findApplicationIdByDate(application);
        if (null == applicationIds || applicationIds.size() == 0)
            return 0;

        List<String> jsons = new ArrayList<>();
        List<MqResendMessage> resends = new ArrayList<>();
        for (Application app : applicationIds) {
            MqResendMessage resend = new MqResendMessage();
            String json = ReadJsonUtils.toJson(app);
            resend.setMessageId(UUIDUtils.getUUID());
            resend.setApplicationId(app.getApplicationId());
            resend.setMessageExchange(MQConstants.MQ_EXCHANG_PUSHAPP);
            resend.setMessageRoutingkey(MQConstants.MQ_ROUTINGKEY_PUSHAPP);
            resend.setMessageQueue(MQConstants.MQ_QUEUE_PUSHAPP);
            resend.setMessageChannelType(MessageChannelTypeEnum.SEND.getCode());
            resend.setMessageService("APP");
            resend.setMessageType(MessageTypeEnum.LIKE.getCode());
            resend.setIp(InetAddress.getLocalHost().getHostAddress());
            resend.setCreateTime(new Date());
            resend.setCreateUserId(TokenManager.getUserId());
            resend.setCreateMan(TokenManager.getUserName());
            resend.setMessageBody(json);
            resend.setMessageInfo(json);
            resends.add(resend);
            jsons.add(json);
        }

        if (resends.size() == 0)
            return 0;

        String errorMessage = "";

        try {
            //tring[] array = applications.toArray(new String[applications.size()]);
            String json = ReadJsonUtils.toJson(jsons);
            //将需要重发的申请单号存放至redis，然后调用rabbitmq项目提供的api，将redis key传过去
            RedisUtils.setEx(RedisCons.MQ_MSG_RESEND_APP_CODE, 12 * 60 * 60, json);
            /**
             * 组装消息结构发送给rabbitmq项目
             */
//            JSONObject object = new JSONObject();
//            object.put("messageList", RedisCons.MQ_MSG_RESEND_APP_CODE);
//            object.put("messageSendType", "resendToHbase");

            MqBatchSendBo sendBo = new MqBatchSendBo();
            sendBo.setMessageList(RedisCons.MQ_MSG_RESEND_APP_CODE);
            sendBo.setMessageSendType(MqResendTypeEnum.RESENDTOBACKEND.toString());
//            RabbitUtil.sendBatchBackEnd(ReadJsonUtils.toJson(sendBo), MQConstants.MQ_EXCHANG_PUSHAPP, MQConstants.MQ_ROUTINGKEY_PUSHAPP);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            //批量保存重复记录
            mqResendMessageDao.batchInsert(resends, errorMessage);
            logger.error(e.getMessage(), e);
            throw e;
        }
        //批量保存重复记录
        mqResendMessageDao.batchInsert(resends, errorMessage);
        return resends.size();
    }
    
}
