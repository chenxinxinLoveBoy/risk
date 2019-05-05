package com.shangyong.backend.dubbo;

import com.shangyong.backend.dubbo.response.DubboBaseResponse;
import com.shangyong.backend.mq.XnMessage;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/15
 *  * Time: 17:12
 *  * PROJECT_NAME: risk-parent_2.3.3
 *  * PACKAGE_NAME: com.shangyong.rabbitmq.dubbo.service
 *  * DESC: 向大数据发送消息
 *  * Version: v1.0.0
 *  
 */
public interface SendToHBaseDubboService {

    /**
     * 向大数据发送消息
     * @param xnMessage
     * xnMessage.exchangeName交换机名称
     * xnMessage.messageType 路由键key
     * xnMessage.messageBody Json格式的信息内容
     * @return DubboBaseResponse
     * 	DubboBaseResponse.state返回状态标识：成功：success/失败：failure
     * 	DubboBaseResponse.errorCode返回错误码
     * 	DubboBaseResponse.errorMsg返回错误信息
     */
    DubboBaseResponse sendMessageHbase(XnMessage xnMessage);

    /**
     * 向大数据发送消息
     * @param xnMessage
     * xnMessage.exchangeName交换机名称
     * xnMessage.messageType 路由键key
     * xnMessage.messageBody Json格式的信息内容
     * @return DubboBaseResponse
     * 	DubboBaseResponse.state返回状态标识：成功：success/失败：failure
     * 	DubboBaseResponse.errorCode返回错误码
     * 	DubboBaseResponse.errorMsg返回错误信息
     */
    DubboBaseResponse sendMessageBackend(XnMessage xnMessage);
}
