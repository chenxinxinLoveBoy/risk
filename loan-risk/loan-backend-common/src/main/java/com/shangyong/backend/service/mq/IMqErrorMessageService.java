package com.shangyong.backend.service.mq;

import org.springframework.amqp.core.Message;

import com.shangyong.backend.entity.mq.MqErrorMessage;

import java.util.List;

/**
 * mq与大数据错误
 */
public interface IMqErrorMessageService {

    /**
     * 添加失败的mq消息
     *
     * @param message
     */
    void save(MqErrorMessage message);

    /**
     * 根据主键修改error消息
     *
     * @param message 需要修改的字段
     */
    void update(MqErrorMessage message);

    /**
     * 删除error消息
     *
     * @param hsid 主键id
     */
    void del(Long hsid);

    /**
     * 查询error消息
     *
     * @param hsid 主键id
     * @return
     */
    MqErrorMessage get(Long hsid);

    /**
     * 查询
     *
     * @param message 条件
     * @return
     */
    List<MqErrorMessage> list(MqErrorMessage message);

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
    List<MqErrorMessage> pageQuery(MqErrorMessage message);

    /**
     * 统计
     *
     * @param message
     * @return
     */
    int count(MqErrorMessage message);

    /**
     *
     * 添加mq发送记录
     * @param message 消息
     * @param channelType 消息管道类型  0-发送  1-接收
     * @param messageService 消息所属业务
     * @param messageType 消息类型  1-转发 2-广播  3-模糊匹配
     * @param createMan 创建人
     * @param modifyMan 修改人
     * @param messageLevel 错误级别  1-警告 2-业务处理错误  3-消息丢失
     * @param remark 错误说明
     * @return
     * @throws Exception
     */
    int saveByMessage(Message message, int channelType, String messageService, int messageType, String createMan, String modifyMan, Integer messageLevel, String remark) throws Exception;

}
