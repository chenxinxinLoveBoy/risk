package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScAlarm;

import java.util.List;

@Mapper
public interface ScAlarmDao {
    boolean insert(ScAlarm record);

    boolean insertSelective(ScAlarm record);

    boolean updateByPrimaryKey(ScAlarm record);

    boolean updateByPrimaryKeySelective(ScAlarm record);

    boolean deleteByPrimaryKey(@Param("alarmId") Integer alarmId);

    ScAlarm selectOne(ScAlarm record);

    List<ScAlarm> selectList(ScAlarm record);

    int count(ScAlarm record);
}