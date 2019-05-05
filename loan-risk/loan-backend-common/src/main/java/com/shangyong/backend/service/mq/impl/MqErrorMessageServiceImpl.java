package com.shangyong.backend.service.mq.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.mq.MqErrorMessageDao;
import com.shangyong.backend.entity.mq.MqErrorMessage;
import com.shangyong.backend.service.mq.IMqErrorMessageService;
import com.shangyong.backend.utils.ReadJsonUtils;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MqErrorMessageServiceImpl implements IMqErrorMessageService {

    @Autowired
    private MqErrorMessageDao mqErrorMessageDao;

    /**
     * 添加失败的mq消息
     *
     * @param message
     */
    @Override
    public void save(MqErrorMessage message) {
        mqErrorMessageDao.insertSelective(message);
    }

    /**
     * 根据主键修改error消息
     *
     * @param message 需要修改的字段
     */
    @Override
    public void update(MqErrorMessage message) {
        mqErrorMessageDao.updateByPrimaryKeySelective(message);
    }

    /**
     * 删除error消息
     *
     * @param hsid 主键id
     */
    @Override
    public void del(Long hsid) {
        mqErrorMessageDao.deleteByPrimaryKey(hsid);
    }

    /**
     * 查询error消息
     *
     * @param hsid 主键id
     * @return
     */
    @Override
    public MqErrorMessage get(Long hsid) {
        return mqErrorMessageDao.selectOne(new MqErrorMessage().setHsid(hsid));
    }

    /**
     * 查询
     *
     * @param message 条件
     * @return
     */
    @Override
    public List<MqErrorMessage> list(MqErrorMessage message) {
        return mqErrorMessageDao.selectList(message);
    }

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
    @Override
    public List<MqErrorMessage> pageQuery(MqErrorMessage message) {
        return mqErrorMessageDao.pageQuery(message);
    }

    /**
     * 统计
     *
     * @param message
     * @return
     */
    @Override
    public int count(MqErrorMessage message) {
        return mqErrorMessageDao.count(message);
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
    public int saveByMessage(Message message, int channelType, String messageService, int messageType, String createMan, String modifyMan,Integer messageLevel,String remark) throws Exception {
        //非空校验
        if(message == null || null == message.getBody()){
            return 0;
        }
        //将消息转换成字符串
        String body = new String(message.getBody());
        //获取消息传递进来的参数
        MessageProperties messageProperties =  message.getMessageProperties();
        String applicationId = null;
        String messageId = null;

        try{
            messageId = messageProperties.getCorrelationIdString();
            if(messageId == null){
                messageId = messageProperties.getMessageId();
            }
            //将消息内容获取并转换成MAP
            Map<String, Object> bodyMap = ReadJsonUtils.fromJson(body, Map.class);
            applicationId = String.valueOf(bodyMap.get("applicationId"));
            if (StringUtils.isEmpty(applicationId) || StringUtils.equals(applicationId, "null"))
                applicationId = String.valueOf(bodyMap.get("application_id"));
        }catch (Exception ex){
            //不做操作
        }finally {
            //组装错误信息
            MqErrorMessage mqErrorMessage = new MqErrorMessage();
            mqErrorMessage.setApplicationId(applicationId);
            mqErrorMessage.setMessageId(messageId);
            mqErrorMessage.setMessageExchange(messageProperties.getReceivedExchange());
            mqErrorMessage.setMessageRoutingkey(messageProperties.getReceivedRoutingKey());
            mqErrorMessage.setMessageQueue(messageProperties.getConsumerQueue());
            mqErrorMessage.setMessageChannelType(channelType);
            mqErrorMessage.setMessageService(messageService);
            mqErrorMessage.setMessageType(messageType);
            //获得IP
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            mqErrorMessage.setIp(ip);
            mqErrorMessage.setCreateTime(new Date());
            mqErrorMessage.setCreateMan(createMan);
            mqErrorMessage.setModifyMan(modifyMan);
            mqErrorMessage.setModifyTime(new Date());
            mqErrorMessage.setMessageBody(body);
            mqErrorMessage.setMessageInfo(message.toString());
            //错误级别 自定义
            mqErrorMessage.setMessageLevel(messageLevel);
            return mqErrorMessageDao.insertSelective(mqErrorMessage);
        }
    }
}
