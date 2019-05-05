package com.shangyong.backend.entity;

/**
 * 异常信息
 * 与rabbitmq交互的实体
 */
public class AlarmCallBackInfo {

    private String msgId;
    private String ip;
    private String sendTime;
    private ScAlarm scAlarm;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public ScAlarm getScAlarm() {
        return scAlarm;
    }

    public void setScAlarm(ScAlarm scAlarm) {
        this.scAlarm = scAlarm;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public AlarmCallBackInfo() {
    }

    @Override
    public String toString() {
        return "AlarmCallBackInfo{" +
                "msgId='" + msgId + '\'' +
                ", ip='" + ip + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", scAlarm=" + scAlarm +
                '}';
    }
}
