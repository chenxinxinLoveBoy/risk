package com.shangyong.backend.common.enums.alarm;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息报警配置(平台)
 */
public enum AlarmThirdPartyCreditInvestigationEnum {

    ZMXYPF(1, "芝麻信用评分"),
    ZMXYQZQD(2, "芝麻信用欺诈清单"),
    TDDQSH(3, "同盾贷前审核"),
    JXLMF(4, "聚信立蜜蜂"),
    JXLMG(5, "聚信立蜜罐"),
    ZMHYQD(6, "芝麻行业清单"),
    BQS(7, "白骑士"),
    JYZX(8, "91征信"),
    JYZXHD(9, "91征信回调"),
    SL(10, "索伦"),
    YX(11, "宜信"),
    XSKJ(12, "小视科技"),
    SJMH(13, "数聚魔盒"),
    APPLICATION_ALARM(81, "申请单数量超过系统参数阀值"),
    ALI_MONGODB(82, "阿里云入库mongodb"),
    MQ_OTHER(83, "MQ其它"),
    JSON_FILE_UPLOAD(84, "JSON文件上传"),
    MQ_HBASE(85, "大数据"),
    CUSTOMER_MESSAGE(86, "客户短信"),
    CUSTOMER_APP_LIST(87, "客户app应用列表"),
    CUSTOMER_CONTACTS(88, "客户通讯录信息"),
    CUSTOMER_CALL_RECORD(89, "客户通话记录"),
    BACKEND_CONFIG(90, "风控配置项"),
    SYSTEM_CONFIG(91, "系统配置项"),
    TASK_CONFIG(92, "定时任务配置管理"),
    COLLECTION_DOCKING(93, "催收对接"),
    CUSTOMER_DOCKING(94, "客户信息对接"),
    APP_DOCKING(95, "app对接"),
    APPROVAL_STATUS_CHANGE(96, "审批状态变更"),
    APP_STATUS_SYNC(97, "APP状态同步告警"),
    BACKEND_WEB(98, "潘多拉后台管理报警"),
    CUSTOM(99, "其他报警");

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    AlarmThirdPartyCreditInvestigationEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (AlarmThirdPartyCreditInvestigationEnum platform : AlarmThirdPartyCreditInvestigationEnum.values()) {
            if (platform.code == code) {
                return platform.name;
            }
        }
        return "";
    }

    public static List<JSONObject> returnCredits() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (AlarmThirdPartyCreditInvestigationEnum alarm : AlarmThirdPartyCreditInvestigationEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", alarm.getCode());
            object.put("name", alarm.getName());
            jsonObjects.add(object);
        }
        return jsonObjects;
    }

    @Override
    public String toString() {
        return "AlarmThirdPartyCreditInvestigationEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
