package com.shangyong.backend.common.enums.alarm;

import org.apache.commons.lang3.StringUtils;

/**
 * 报警code配置类
 */
public enum AlarmCodeEnum {

    ZMXYPF("1000001", "芝麻信用评分"),
    ZMXYQZQD("1000002", "芝麻信用欺诈清单"),
    TDDQSH("1000003", "同盾贷前审核"),
    JXLMF("1000004", "聚信立蜜蜂"),
    JXLMG("1000005", "聚信立蜜罐"),
    ZMHYQD("1000006", "芝麻行业清单"),
    BQS("1000007", "白骑士"),
    JYZX("1000008", "91征信"),
    JYZXHD("1000009", "91征信回调"),
    SL("1000010", "索伦"),
    YX("1000011", "宜信"),
    XSKJ("1000012", "小视科技"),
    SJMH("1000013", "数聚魔盒"),
    APPLICATION_ALARM("9999981", "申请单数量超过系统参数阀值"),
    ALI_MONGODB("9999982", "阿里云入库mongodb"),
    MQ_OTHER("9999983", "MQ其它"),
    JSON_FILE_UPLOAD("9999984", "JSON文件上传"),
    MQ_HBASE("9999985", "大数据"),
    CUSTOMER_MESSAGE("9999986", "客户短信"),
    CUSTOMER_APP_LIST("9999987", "客户app应用列表"),
    CUSTOMER_CONTACTS("9999988", "客户通讯录信息"),
    CUSTOMER_CALL_RECORD("9999989", "客户通话记录"),
    BACKEND_CONFIG("9999990", "风控配置项"),
    SYSTEM_CONFIG("9999991", "系统配置项"),
    TASK_CONFIG("9999992", "定时任务配置管理"),
    COLLECTION_DOCKING("9999993", "催收对接"),
    CUSTOMER_DOCKING("9999994", "客户信息对接"),
    APP_DOCKING("9999995", "app对接"),
    APPROVAL_STATUS_CHANGE("9999996", "审批状态变更"),
    APP_STATUS_SYNC("9999997", "APP状态同步告警"),
    BACKEND_WEB("9999998", "潘多拉后台管理报警"),
    CUSTOM("9999999", "其他报警");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    AlarmCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (AlarmCodeEnum alarm : AlarmCodeEnum.values()) {
            if (StringUtils.equals(code, alarm.code)) {
                return alarm.name;
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "AlarmCodeEnum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
