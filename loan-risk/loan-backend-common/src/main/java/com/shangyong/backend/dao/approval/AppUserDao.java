package com.shangyong.backend.dao.approval;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * AppUserDao(app用户信息表)数据库操作接口类bean
 *
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
@Mapper
public interface AppUserDao {

    /**
     * 查询用户信息（根据userNo查询）
     **/
    int updateApplyState(@Param("applicationBuId") Long applicationBuId);

}