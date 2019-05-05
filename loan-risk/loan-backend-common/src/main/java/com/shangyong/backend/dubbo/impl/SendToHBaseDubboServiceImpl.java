package com.shangyong.backend.dubbo.impl;

import org.springframework.stereotype.Service;

import com.shangyong.backend.dubbo.SendToHBaseDubboService;
import com.shangyong.backend.dubbo.response.DubboBaseResponse;
import com.shangyong.backend.mq.XnMessage;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/15
 *  * Time: 17:14
 *  * PROJECT_NAME: risk-parent_2.3.3
 *  * PACKAGE_NAME: com.shangyong.rabbitmq.dubbo.service.impl
 *  * DESC:
 *  * Version: v1.0.0
 *  
 */
@Service
public class SendToHBaseDubboServiceImpl implements SendToHBaseDubboService {

//    private static Logger logger = LoggerFactory.getLogger("applicationDubboService");

    /**
     * 向大数据发送消息
     *
     * @param xnMessage xnMessage.exchangeName交换机名称
     *                  xnMessage.messageType 路由键key
     *                  xnMessage.messageBody Json格式的信息内容
     * @return DubboBaseResponse
     * DubboBaseResponse.state返回状态标识：成功：success/失败：failure
     * DubboBaseResponse.errorCode返回错误码
     * DubboBaseResponse.errorMsg返回错误信息
     */
    @Override
    public DubboBaseResponse sendMessageHbase(XnMessage xnMessage) {
        return  new DubboBaseResponse();

    }
    /**
     * 向大数据发送消息
     *
     * @param xnMessage xnMessage.exchangeName交换机名称
     *                  xnMessage.messageType 路由键key
     *                  xnMessage.messageBody Json格式的信息内容
     * @return DubboBaseResponse
     * DubboBaseResponse.state返回状态标识：成功：success/失败：failure
     * DubboBaseResponse.errorCode返回错误码
     * DubboBaseResponse.errorMsg返回错误信息
     */
    @Override
    public DubboBaseResponse sendMessageBackend(XnMessage xnMessage) {
        return  new DubboBaseResponse();
    }
}
