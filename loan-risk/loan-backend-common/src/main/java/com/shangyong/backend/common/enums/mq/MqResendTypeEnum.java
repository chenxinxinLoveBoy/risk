package com.shangyong.backend.common.enums.mq;

/**
 * 消息重发类型
 * resendToBackEnd 重发至风控
 * resendToHbase 重发至大数据
 */
public enum MqResendTypeEnum {

    RESENDTOBACKEND, RESENDTOHBASE
}
