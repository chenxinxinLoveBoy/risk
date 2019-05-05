package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.entity.ScAlarm;

public interface IScAlarm {
    boolean saveScAlarmBo(ScAlarm alarmBo);

    boolean updateScAlarmBo(ScAlarm alarmBo);

    boolean deleteScAlarmBo(Integer alarmId);

    ScAlarm getScAlarmBo(Integer alarmId);

    /**
     * 根据错误码判断是否存在并将消息发送至rabbitmq
     *
     * @param alarmCode
     * @param alarmMsg  需要报警的消息
     * @param thirdPartyCreditInvestigation 第三方征信平台
     * @return
     */
    void contains(AlarmCodeEnum alarmCode, String alarmMsg, AlarmThirdPartyCreditInvestigationEnum thirdPartyCreditInvestigation);

    /**
     * 根据错误码判断是否存在并将消息发送至rabbitmq
     * <p>此方法已过时</p>
     *
     * @param alarmCode
     * @param alarmMsg  需要报警的消息
     * @param thirdPartyCreditInvestigation 第三方征信平台
     * @return
     */
    @Deprecated
    ScAlarm contains(String alarmCode, String alarmMsg, int thirdPartyCreditInvestigation);

    /**
     * 根据错误信息判断是否存在并将消息发送至rabbitmq
     * <p>此方法已过时</p>
     *
     * @param alarmString 错误信息
     * @return
     */
    @Deprecated
    void contains(String alarmString);

    List<ScAlarm> listScAlarmBo(ScAlarm alarmBo);

    int count(ScAlarm alarmBo);
    
    /**
     * 新增钉钉报警
     * @param msgContent
     * @param msgType
     */
    void sendDingdingMsg(String msgContent, String msgType);

}
