package com.shangyong.backend.utils;

import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.BlackListCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 禁止项 和 classcode 关系
 * @author caisheng
 * @date 2018-08-08
 */
public class BanCodeRelUtil {

    /**
     * 禁止项 和 黑名单 分类关系
     */
    public static Map<String, String> REL_BAN_CLASS_CODE = new HashMap<String, String>(){{

        put(BanCodeEnum.NINE_ONE_CREDIT_M1_ABOVE.getCode(), BlackListCodeEnum.NINE_ONE_CREDIT_BLACKLIST_CODE.getCode());

        put(BanCodeEnum.BAI_QI_SHI_CREDIT_HIGH_RISK.getCode(), BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.BAI_QI_SHI_CREDIT_FAYUAN_BLACK.getCode(), BlackListCodeEnum.BAI_QI_SHI_BLACKLIST_CODE.getCode());

        put(BanCodeEnum.CENSUS_REGISTER_AREA.getCode(),  BlackListCodeEnum.REGION_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.HONG_CHANG_CARD_UNIT.getCode(),  BlackListCodeEnum.REGION_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_REGION.getCode(),  BlackListCodeEnum.REGION_BLACKLIST_CODE.getCode());

        put(BanCodeEnum.JI_GUANG_RISK_ALLOCATION_AURORA_RELATION_CIRCLE.getCode(), BlackListCodeEnum.JI_GUANG_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.JI_GUANG_AURORA_OVERDUE_ALLOCATION.getCode(), BlackListCodeEnum.JI_GUANG_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.JI_GUANG_AURORA_DEFAULT_ALLOCATION.getCode(), BlackListCodeEnum.JI_GUANG_BLACKLIST_CODE.getCode());


        put(BanCodeEnum.SHANG_HAI_CREDIT_ACCOUNT_ABNORMAL.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_IS_OVERDUE.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_OVERDUE_SINGLE_COUNT.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_OVERDUE_SINGLE_TOTAL.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_3_MONTH_HAVE_2_M1.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_6_MONTH_HAVE_1_M2.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_6_MONTH_3_M1.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_12_MONTH_4_M1.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_OVERDUE_TOTAL.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_TARDY.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_INDIVIDUAL_GUARANTEE_INDEMNITY.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHANG_HAI_CREDIT_SINGLE_MAX_OVERDE.getCode(), BlackListCodeEnum.SHANG_HAI_CREDIT_BLACKLIST_CODE.getCode());


        put(BanCodeEnum.TENCENT_CLOUD_FRAUD_ALLOCATION.getCode(), BlackListCodeEnum.TENCENT_CLOUD_BLACKLIST_CODE.getCode());

        put(BanCodeEnum.TONG_DUN_CONFIGURATION_SAME_SHIELD_EQUIPMENT.getCode(), BlackListCodeEnum.TONG_DUN_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.TONG_DUN_TOTAL_RISK_SCORE_SAME_SHIELD_AGAINST_BULLYING.getCode(), BlackListCodeEnum.TONG_DUN_BLACKLIST_CODE.getCode());


        put(BanCodeEnum.YI_JI_PAY_BLACKLIST.getCode(), BlackListCodeEnum.YI_JI_PAY_BLACKLIST_CODE.getCode());


        put(BanCodeEnum.ZHI_MA_SCORES.getCode(), BlackListCodeEnum.ZHI_MA_SCORES.getCode());
        put(BanCodeEnum.ZHI_MA_INDUSTRY.getCode(), BlackListCodeEnum.ZHI_MA_INDUSTRY.getCode());

        put(BanCodeEnum.BLACKLIST_EMERGENCY_CONTACT.getCode(), BlackListCodeEnum.CONTACTS_BLACKLIST_CODE.getCode());

        put(BanCodeEnum.SHIELD_FINGERPRINT_EQUIPMENT_TRUE_IP.getCode(), BlackListCodeEnum.REAL_IP_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHIELD_FINGERPRINT_EQUIPMENT_DEVICE_ID.getCode(), BlackListCodeEnum.DEVICE_ID_BLACKLIST_CODE.getCode());
        put(BanCodeEnum.SHIELD_FINGERPRINT_EQUIPMENT_WIFI_MAC.getCode(), BlackListCodeEnum.WIRELESS_MAC_BLACKLIST_CODE.getCode());


        put(BanCodeEnum.BLACKLIST_USER.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_AGE_CEILING.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_AGE_FLOOR.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.HONG_CHANG_COMPANY_UNIT.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_VALID.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_UNIT.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_UNIT_NUM.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.XIAO_NIU_CARD_UNIT.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
        put(BanCodeEnum.HONG_CHENG_CHARACTER_WU.getCode(), BlackListCodeEnum.HONG_CHENG_ACCESS_CODE.getCode());
    }};
}
