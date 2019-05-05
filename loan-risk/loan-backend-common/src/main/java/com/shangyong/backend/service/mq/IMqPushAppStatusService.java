package com.shangyong.backend.service.mq;

import java.util.Map;

/**
 * mq消息重发业务
 */
public interface IMqPushAppStatusService {
    
    /**
     * 添加mq发送记录
     * @param message 消息
     * @return
     * @throws Exception
     */
    void pushByMessage(Map<String, Object> bodyMap) throws Exception;
    /**
     * 添加mq推送提额至app记录
     * @param message 消息
     * @return
     * @throws Exception
     */
    void pushByTeMessage(Map<String, Object> bodyMap) throws Exception;

}
