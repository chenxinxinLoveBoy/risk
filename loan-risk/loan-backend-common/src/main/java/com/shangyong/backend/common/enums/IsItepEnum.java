package com.shangyong.backend.common.enums;

/**
 * 单子审批流程
 */
public enum IsItepEnum {

    DCL(0, "待处理"),
    ZMXYPF(1, "芝麻信用评分"),
    ZMXYQZQD(2, "芝麻信用欺诈清单"),
    TDDQSH(3, "同盾贷前审核"),
    JXLMF(4, "聚信立蜜蜂"),
    JXLMG(5, "聚信立蜜罐"),
    ZMHYQD(6, "芝麻行业清单"),
    BQS(7, "白骑士"),
    YZX(8, "91征信"),
    JYZXHD(9, "91征信回调"),
    XSKJ(10, "小视科技"),
    HLSL(11, "葫芦索伦"),
    YX(20, "宜信"),
    GZYQMBFF(98, "规则引擎模板分发"),
    PDLJJMD(99, "潘多拉拒绝名单");




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

    IsItepEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (IsItepEnum platform : IsItepEnum.values()) {
            if (platform.code == code)
                return platform.name;
        }
        return "";
    }
}
