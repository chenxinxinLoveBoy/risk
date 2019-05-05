package com.shangyong.backend.service.mq.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.mq.MqResendMessageDao;
import com.shangyong.backend.entity.mq.MqResendMessage;
import com.shangyong.backend.service.mq.IMqResendMessageService;
import com.shangyong.backend.utils.ReadJsonUtils;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MqResendMessageServiceImpl implements IMqResendMessageService {

    @Autowired
    private MqResendMessageDao mqResendMessageDao;

    /**
     * 添加mq重发记录
     *
     * @param message
     */
    @Override
    public void save(MqResendMessage message) {
        mqResendMessageDao.insertSelective(message);
    }

    /**
     * 根据主键修改消息
     *
     * @param message 需要修改的字段
     */
    @Override
    public void update(MqResendMessage message) {
        mqResendMessageDao.updateByPrimaryKeySelective(message);
    }

    /**
     * 删除消息
     *
     * @param hsid 主键id
     */
    @Override
    public void del(Long hsid) {
        mqResendMessageDao.deleteByPrimaryKey(hsid);
    }

    /**
     * 查询消息
     *
     * @param hsid 主键id
     * @return
     */
    @Override
    public MqResendMessage get(Long hsid) {
        return mqResendMessageDao.selectOne(new MqResendMessage().setHsid(hsid));
    }

    /**
     * 查询
     *
     * @param message 条件
     * @return
     */
    @Override
    public List<MqResendMessage> list(MqResendMessage message) {
        return mqResendMessageDao.selectList(message);
    }

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
    @Override
    public List<MqResendMessage> pageQuery(MqResendMessage message) {
        return mqResendMessageDao.pageQuery(message);
    }

    /**
     * 统计
     *
     * @param message
     * @return
     */
    @Override
    public int count(MqResendMessage message) {
        return mqResendMessageDao.count(message);
    }

    /**
     * 添加mq发送记录
     *
     * @param message        消息
     * @param channelType    消息管道类型  0-发送  1-接收
     * @param messageService 消息所属业务
     * @param messageType    消息类型  1-转发 2-广播  3-模糊匹配
     * @param createMan      创建人
     * @param modifyMan      修改人
     * @return
     * @throws Exception
     */
    @Override
    public int saveByMessage(Message message, int channelType, String messageService, int messageType, String createMan, String modifyMan) throws Exception {
        //非空校验
        if(message == null || null == message.getBody()){
            return 0;
        }
        //将消息转换成字符串
        String body = new String(message.getBody());

        MessageProperties messageProperties =  message.getMessageProperties();
        Map<String, Object> bodyMap = ReadJsonUtils.fromJson(body, Map.class);
        String applicationId = String.valueOf(bodyMap.get("application_id"));

        MqResendMessage mqResendMessage = new MqResendMessage();
        mqResendMessage.setApplicationId(applicationId);
        mqResendMessage.setMessageId(messageProperties.getMessageId());
        mqResendMessage.setMessageExchange(messageProperties.getReceivedExchange());
        mqResendMessage.setMessageRoutingkey(messageProperties.getReceivedRoutingKey());
        mqResendMessage.setMessageQueue(messageProperties.getConsumerQueue());
        mqResendMessage.setMessageChannelType(channelType);
        mqResendMessage.setMessageService(messageService);
        mqResendMessage.setMessageType(messageType);

        InetAddress addr = InetAddress.getLocalHost();//获得本机IP  ;
        String ip = addr.getHostAddress();
        mqResendMessage.setIp(ip);
        mqResendMessage.setCreateTime(new Date());
        mqResendMessage.setCreateMan(createMan);
        mqResendMessage.setModifyMan(modifyMan);
        mqResendMessage.setModifyTime(new Date());
        mqResendMessage.setMessageBody(body);
        mqResendMessage.setMessageInfo(ReadJsonUtils.toJson(message));

        int result = mqResendMessageDao.insertSelective(mqResendMessage);
        return result;
    }
}
