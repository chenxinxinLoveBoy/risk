package com.shangyong.backend.service.mq;

import java.util.List;

import org.springframework.amqp.core.Message;

import com.shangyong.backend.entity.mq.MqResendMessage;

/**
 * mq消息重发业务
 */
public interface IMqResendMessageService {
    /**
     * 添加mq重发记录
     *
     * @param message
     */
    void save(MqResendMessage message);

    /**
     * 根据主键修改消息
     *
     * @param message 需要修改的字段
     */
    void update(MqResendMessage message);

    /**
     * 删除消息
     *
     * @param hsid 主键id
     */
    void del(Long hsid);

    /**
     * 查询消息
     *
     * @param hsid 主键id
     * @return
     */
    MqResendMessage get(Long hsid);

    /**
     * 查询
     *
     * @param message 条件
     * @return
     */
    List<MqResendMessage> list(MqResendMessage message);

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
    List<MqResendMessage> pageQuery(MqResendMessage message);

    /**
     * 统计
     *
     * @param message
     * @return
     */
    int count(MqResendMessage message);

    /**
     * 添加mq发送记录
     * @param message 消息
     * @param channelType 消息管道类型  0-发送  1-接收
     * @param messageService 消息所属业务
     * @param messageType 消息类型  1-转发 2-广播  3-模糊匹配
     * @param createMan 创建人
     * @param modifyMan 修改人
     * @return
     * @throws Exception
     */
    int saveByMessage(Message message, int channelType, String messageService, int messageType, String createMan, String modifyMan) throws Exception;

}
