package com.shangyong.backend.common.enums;

/**
 *
 *
 *   黑名单数据来源（01-洪澄贷前审核、02-洪澄贷后监控、03-APP同步、04-手工添加）
 *
 *   01-洪澄贷前审核
 *   public static final String BLACK_RISK = "01";
 *
 *   02-洪澄贷后监控
 *   public static final String BLACK_OBSERVER = "02";
 *
 *   03-APP同步
 *   public static final String BLACK_APP = "03";
 *
 *   04-手工添加
 *   public static final String BLACK_SYS = "04";
 *
 *  黑名单数据来源
 * @author caisheng
 * @date 2018-08-07
 */
public enum BlackListDsSourceEnum {

    HONG_CHENG_BEFORE_AUDIT("01", "洪澄贷前审核"),
    HONG_CHENG_AFTER_MONITORING("02", "洪澄贷后监控"),
    APP_SYNC("03", "APP同步"),
    MANUAL_ADD("04", "手工添加"),
    BIG_DATA("05", "大数据添加"),
    WHITE_CARD("06", "现金白卡")
    ;
    private String code;
    private String remark;

    BlackListDsSourceEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public static BlackListDsSourceEnum parse(String code){
        if(null == code){
            return null;
        }
        String codeTemp = code.trim();
        for(BlackListDsSourceEnum elem : values()){
            if(elem.getCode().equals(codeTemp)){
                return elem;
            }
        }
        return null;
    }

}
