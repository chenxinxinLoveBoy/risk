package com.shangyong.backend.dao.mq;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.mq.MqErrorMessage;
import com.shangyong.backend.entity.mq.MqLogMessage;

import java.util.List;

@Mapper
public interface MqErrorMessageDao {
    int insert(MqErrorMessage record);

    int insertSelective(MqErrorMessage record);

    int updateByPrimaryKey(MqErrorMessage record);

    int updateByPrimaryKeySelective(MqErrorMessage record);

    int deleteByPrimaryKey(@Param("hsid") Long hsid);

    MqErrorMessage selectOne(MqErrorMessage record);

    List<MqErrorMessage> selectList(MqErrorMessage record);

    List<MqErrorMessage> pageQuery(MqErrorMessage record);

    int count(MqErrorMessage record);
    /**
     * 创建错误MQ日志信息
     * @param record
     * @return
     */
    int saveMqLogMessageEntity(MqLogMessage record);
}