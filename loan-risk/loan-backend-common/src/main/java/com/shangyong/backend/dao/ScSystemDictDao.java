package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScSystemDict;

@Mapper
public interface ScSystemDictDao {
    int deleteByPrimaryKey(Integer dictionaryId);

    int insert(ScSystemDict record);

    int insertSelective(ScSystemDict record);

    ScSystemDict selectByPrimaryKey(Integer dictionaryId);

    int updateByPrimaryKeySelective(ScSystemDict record);

    int updateByPrimaryKey(ScSystemDict record);
    
    List<Map> getListByCodes(Map pa);
    
    List<Map> getByCodes(Map pa);
}