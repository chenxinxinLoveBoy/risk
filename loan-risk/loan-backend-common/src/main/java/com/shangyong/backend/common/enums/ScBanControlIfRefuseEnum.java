package com.shangyong.backend.common.enums;

/**
 * 禁止项是否加入黑名单
 * @author caisheng
 * @date 2018-08-07
 */
public enum ScBanControlIfRefuseEnum {

    ADD("1", "添加拒绝名单"),
    NOT_ADD("0", "不添加拒绝名单")
    ;
    private String code;
    private String remark;

    ScBanControlIfRefuseEnum(String code, String remark) {
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
