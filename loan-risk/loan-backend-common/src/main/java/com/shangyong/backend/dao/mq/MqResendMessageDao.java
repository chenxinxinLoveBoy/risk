package com.shangyong.backend.dao.mq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.mq.MqResendMessage;

@Mapper
public interface MqResendMessageDao {
    int insert(MqResendMessage record);

    int batchInsert(@Param("records") List<MqResendMessage> records, @Param("errorMsg") String errorMsg);

    int insertSelective(MqResendMessage record);

    int updateByPrimaryKey(MqResendMessage record);

    int updateByPrimaryKeySelective(MqResendMessage record);

    int deleteByPrimaryKey(@Param("hsid") Long hsid);

    MqResendMessage selectOne(MqResendMessage record);

    List<MqResendMessage> selectList(MqResendMessage record);

    List<MqResendMessage> pageQuery(MqResendMessage record);

    int count(MqResendMessage record);
    /**
     * 创建重发MQ信息
     * @param record
     * @return
     */
    int saveMqResendMessageEntity(MqResendMessage record);
}