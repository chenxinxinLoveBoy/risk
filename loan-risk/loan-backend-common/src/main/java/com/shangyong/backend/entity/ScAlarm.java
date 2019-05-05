package com.shangyong.backend.entity;

import java.io.Serializable;
import java.util.Date;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

public class ScAlarm extends BaseBo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4008038715737534661L;

	/**
     * 自增主键 sc_alarm.alarm_id
     */

    private Integer alarmId;

    /**
     * 错误码 sc_alarm.alarm_code
     */
    private String alarmCode;

    /**
     * 错误信息 sc_alarm.alarm_msg
     */
    private String alarmMsg;

    /**
     * 0-非常严重,1-严重,2-普通 sc_alarm.priority
     */
    private Integer priority;

    /**
     * 0-钉钉,1-短信，2-邮件，3-钉钉和短信 sc_alarm.alarm_type
     */
    private Integer alarmType;

    /**
     * 第三方征信平台（1：芝麻信用评分、2：芝麻信用欺诈清单、3：同盾贷前审核、4：聚信立蜜蜂、5：聚信立蜜罐、6：芝麻行业清单、7：白骑士、8：91征信、9：91征信回调） sc_alarm.third_party_credit_investigation
     */
    private Integer thirdPartyCreditInvestigation;

    /**
     * 创建日期 sc_alarm.create_time
     */
    private Date createTime;

    /**
     * 修改日期 sc_alarm.modify_time
     */
    private Date modifyTime;

    /**
     * 报警参数 sc_alarm.alarm_param
     */
    private String alarmParam;

    /**
     * 手机号 sc_alarm.phone
     */
    private String phone;

    /**
     * 邮箱账号 sc_alarm.email
     */
    private String email;

    /**
     * 钉钉链接 sc_alarm.dingding_url
     */
    private String dingdingUrl;

    public ScAlarm(Integer alarmId, String alarmCode, String alarmMsg, Integer priority, Integer alarmType, Integer thirdPartyCreditInvestigation, Date createTime, Date modifyTime, String alarmParam, String phone, String email, String dingdingUrl) {
        this.alarmId = alarmId;
        this.alarmCode = alarmCode;
        this.alarmMsg = alarmMsg;
        this.priority = priority;
        this.alarmType = alarmType;
        this.thirdPartyCreditInvestigation = thirdPartyCreditInvestigation;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.alarmParam = alarmParam;
        this.phone = phone;
        this.email = email;
        this.dingdingUrl = dingdingUrl;
        
    }

    public ScAlarm() {
        super();

    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public ScAlarm setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
        return this;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public ScAlarm setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode == null ? null : alarmCode.trim();
        return this;
    }

    public String getAlarmMsg() {
        return alarmMsg;
    }

    public ScAlarm setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg == null ? null : alarmMsg.trim();
        return this;
    }

    public Integer getPriority() {
        return priority;
    }

    public ScAlarm setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public ScAlarm setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
        return this;
    }

    public Integer getThirdPartyCreditInvestigation() {
        return thirdPartyCreditInvestigation;
    }

    public ScAlarm setThirdPartyCreditInvestigation(Integer thirdPartyCreditInvestigation) {
        this.thirdPartyCreditInvestigation = thirdPartyCreditInvestigation;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ScAlarm setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public ScAlarm setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getAlarmParam() {
        return alarmParam;
    }

    public ScAlarm setAlarmParam(String alarmParam) {
        this.alarmParam = alarmParam == null ? null : alarmParam.trim();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ScAlarm setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ScAlarm setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public String getDingdingUrl() {
        return dingdingUrl;
    }

    public ScAlarm setDingdingUrl(String dingdingUrl) {
        this.dingdingUrl = dingdingUrl == null ? null : dingdingUrl.trim();
        return this;
    }

    @Override
    public String toString() {
        return "ScAlarm{" +
                "alarmId=" + alarmId +
                ", alarmCode='" + alarmCode + '\'' +
                ", alarmMsg='" + alarmMsg + '\'' +
                ", priority=" + priority +
                ", alarmType=" + alarmType +
                ", thirdPartyCreditInvestigation=" + thirdPartyCreditInvestigation +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", alarmParam='" + alarmParam + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dingdingUrl='" + dingdingUrl + '\'' +
                '}';
    }
}