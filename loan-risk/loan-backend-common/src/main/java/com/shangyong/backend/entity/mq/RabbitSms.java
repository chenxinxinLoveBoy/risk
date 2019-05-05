package com.shangyong.backend.entity.mq;

/**
 * 短信发送 消息体
 * @author caisheng
 * @date 2018-08-10
 */
public class RabbitSms {


    private String phoneNum;
    private String businessType;
    private String verCodel;
    private String appName;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getVerCodel() {
        return verCodel;
    }

    public void setVerCodel(String verCodel) {
        this.verCodel = verCodel;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
