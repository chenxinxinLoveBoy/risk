package com.shangyong.backend.dao.mq;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.bo.mq.MqLogMessageBo;
import com.shangyong.backend.entity.mq.MqLogMessage;

import java.util.List;

@Mapper
public interface MqLogMessageDao {
    int insert(MqLogMessage record);

    /**
     * 创建日志信息
     *
     * @param record
     * @return
     */
    int insertSelective(MqLogMessage record);

    int updateByPrimaryKey(MqLogMessage record);

    int updateByPrimaryKeySelective(MqLogMessage record);

    int deleteByPrimaryKey(@Param("hsid") Long hsid);

    MqLogMessage selectOne(MqLogMessage record);

    List<MqLogMessage> selectList(MqLogMessage record);

    int count(MqLogMessage record);

    List<MqLogMessage> pageQuery(MqLogMessage record);

//    List<MqLogMessageBo> sendPageQuery(MqLogMessageBo record);

//    List<MqLogMessageBo> receivePageQuery(MqLogMessageBo record);

//    int sendCount(MqLogMessage record);

//    int receiveCount(MqLogMessage record);

    /**
     * 查询异常单
     *
     * @param record
     * @return
     */
    List<MqLogMessageBo> findErrorApplication(MqLogMessageBo record);

    /**
     * 统计异常单
     *
     * @param record
     * @returnc
     */
    int countErrorApplication(MqLogMessageBo record);


    /**
     * 申请单去重的发送数，去重的接受数
     *
     * @param mqLogMessage
     * @return
     */
    int byApplicationIdDistinctCount(MqLogMessage mqLogMessage);

    MqLogMessage byApplicationIdAndIsConsumptionFind(@Param("applicationId") String applicationId, @Param("isConsumption") String isConsumption);

    int updateIsConsumption(MqLogMessage mqLogMessage);
}