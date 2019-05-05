package com.shangyong.backend.service.mq;

import java.util.List;

import net.sf.json.JSONObject;
import org.springframework.amqp.core.Message;

import com.shangyong.backend.bo.mq.MqLogMessageBo;
import com.shangyong.backend.common.enums.mq.MessageIsConsumptionEnum;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.mq.MqLogMessage;

/**
 * mq消息发送记录
 */
public interface IMqLogMessageService {
    /**
     * 添加mq发送记录
     *
     * @param message
     */
    void save(MqLogMessage message);

    /**
     * 根据主键修改消息
     *
     * @param message 需要修改的字段
     */
    void update(MqLogMessage message);

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
    MqLogMessage get(Long hsid);

    /**
     * 查询消息
     *
     * @param messagedId 消息id
     * @return
     */
    MqLogMessage get(String messagedId);

    /**
     * 查询
     *
     * @param message 条件
     * @return
     */
    List<MqLogMessage> list(MqLogMessage message);


    /**
     * 查询异常单
     *
     * @param mqLogMessageBo
     * @return
     */
    List<MqLogMessageBo> findErrorApplication(MqLogMessageBo mqLogMessageBo);

    /**
     * 查询异常单
     *
     * @param mqLogMessageBo
     * @return
     */
    int countErrorApplication(MqLogMessageBo mqLogMessageBo);

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
    List<MqLogMessage> pageQuery(MqLogMessage message);

    /**
     * 分页查询
     *
     * @param message
     * @return
     */
//    List<MqLogMessageBo> pageQuery(MqLogMessageBo message);

    /**
     * 统计
     *
     * @param message
     * @return
     */
    int count(MqLogMessage message);


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
    int saveByMessage(Message message, int channelType, String messageService, int messageType, String createMan, String modifyMan, MessageIsConsumptionEnum consumptionEnum) throws Exception;


    /**
     * mq报表数据
     *
     * @param mqLogMessage
     * @return
     */
    JSONObject mqReport(MqLogMessage mqLogMessage);

    void updateByMessageIdIsConsumption(String messsageId, MessageIsConsumptionEnum isConsumption);

    /**
     * 根据条件批量发送消息至大数据
     *
     * @param application
     * @return
     */
    int findApplicationIdByDate(Application application) throws Exception;

    /**
     * 根据条件查询申请单进行批量消费
     *
     * @param application
     */
    int byApplicationBatchConsumption(Application application) throws Exception;
    
    /**
     * 根据条件批量补发推送消息数据至APP
     *
     * @param application
     */
    int findPushAppStatusByDate(Application application) throws Exception;
}
