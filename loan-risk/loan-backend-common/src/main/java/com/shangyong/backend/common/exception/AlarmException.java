package com.shangyong.backend.common.exception;

import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;

/**
 * 此异常需要进行报警处理
 */
public class AlarmException extends Exception {

    /**
     * 错误码
     */
    private String alarmCode;

    /**
     * 第三方征信平台
     */
    private AlarmThirdPartyCreditInvestigationEnum alarmThirdPartyCreditInvestigationEnum;

    public AlarmException(String alarmCode, AlarmThirdPartyCreditInvestigationEnum alarmThirdPartyCreditInvestigationEnum) {
        this.alarmCode = alarmCode;
        this.alarmThirdPartyCreditInvestigationEnum = alarmThirdPartyCreditInvestigationEnum;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public AlarmThirdPartyCreditInvestigationEnum getAlarmThirdPartyCreditInvestigationEnum() {
        return alarmThirdPartyCreditInvestigationEnum;
    }

    public void setAlarmThirdPartyCreditInvestigationEnum(AlarmThirdPartyCreditInvestigationEnum alarmThirdPartyCreditInvestigationEnum) {
        this.alarmThirdPartyCreditInvestigationEnum = alarmThirdPartyCreditInvestigationEnum;
    }
}
