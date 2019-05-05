package com.shangyong.backend.service;

/**
 * rabbitmq回调
 */
public interface IAlarmCallBackService {

    /**
     * 接收rabbitmq回调的数据
     *
     * @param json
     * @return
     */
    public boolean callBack(String json);
}
