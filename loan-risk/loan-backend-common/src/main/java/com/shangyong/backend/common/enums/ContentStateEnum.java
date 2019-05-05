package com.shangyong.backend.common.enums;

/**
 * 规则命中状态
 * @author  caisheng
 */
public enum  ContentStateEnum {

    HIT(1, "命中"),
    MISS(0, "未命中");

    private int code;
    private String remark;

    ContentStateEnum(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public int getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
