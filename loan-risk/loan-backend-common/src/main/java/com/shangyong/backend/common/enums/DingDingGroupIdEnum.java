package com.shangyong.backend.common.enums;


/**
 *  钉钉群号 定义
 * @author caisheng
 * @date 2018-08-06
 */
public enum DingDingGroupIdEnum {

    GROUP_TECHNOLOGY(1, "chat67b5ebb9ee544468ba294e220544af68", "技术群"),
    GROUP_TECHNOLOGY_DEP(2, "chate993722614f47ab220039af5da05f7c4", "技术部"),
    GROUP_CONTRACT(3, "chatc8852de2cbc51ac98b80e68cebb91c4c",  "合同报警会话"),
    GROUP_APP(4, "chat1fdd0d0bbcb4fe159665db077a2c0199", "app后台")

    ;
    private int code;
    private String groupId;
    private String remark;

    DingDingGroupIdEnum(int code, String groupId, String remark) {
        this.code = code;
        this.groupId = groupId;
        this.remark = remark;
    }

    public int getCode() {
        return code;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getRemark() {
        return remark;
    }
}
