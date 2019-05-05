package com.shangyong.backend.common.enums;

/**
 * 开关参数 定义
 */
public enum ScParamSwitchEnum {

    ON("1", "开启" ),
    OFF("0", "关闭");

    private String code;
    private String remark;

    ScParamSwitchEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
