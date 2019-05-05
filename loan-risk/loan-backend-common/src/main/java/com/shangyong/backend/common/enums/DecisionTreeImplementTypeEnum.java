package com.shangyong.backend.common.enums;

/**
 * 决策树 实施类型
 * @author caisheng
 * @date 2018-08-07
 */
public enum DecisionTreeImplementTypeEnum {


    BIG_DATA("01", "大数据");

    private String code;
    private String remake;

    DecisionTreeImplementTypeEnum(String code, String remake) {
        this.code = code;
        this.remake = remake;
    }

    public String getCode() {
        return code;
    }

    public String getRemake() {
        return remake;
    }
}
