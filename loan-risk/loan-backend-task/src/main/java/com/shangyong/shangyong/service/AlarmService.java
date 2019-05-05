package com.shangyong.shangyong.service;

/**
 * 报警 业务
 *
 * @Author caisheng
 * @Date 2018年8月3日
 * @Description 申请单 失败次数 超过4次 报警
 *
 */
public interface AlarmService {

    /**
     * 单子失败错误次数 == 5 报警
     */
    public void alarmFailApplication();

}
