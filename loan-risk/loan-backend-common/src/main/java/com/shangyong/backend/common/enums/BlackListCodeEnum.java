package com.shangyong.backend.common.enums;

/**
 * @Deprecated 黑名单联系人分类代码
 * 
 * @author zengfq
 *
 */
public enum  BlackListCodeEnum {

	NINE_ONE_CREDIT_BLACKLIST_CODE("1001" ,"91征信黑名单"),
	BAI_QI_SHI_BLACKLIST_CODE("1002", "白骑士黑名单"),
	REGION_BLACKLIST_CODE("1003", "地域黑名单"),
	JI_GUANG_BLACKLIST_CODE("1004", "极光黑名单"),
	MANUAL_REVIEW_BLACKLIST_CODE("1005", "人工审核黑名单"),
	SHANG_HAI_CREDIT_BLACKLIST_CODE("1006", "上海资信黑名单"),
	TENCENT_CLOUD_BLACKLIST_CODE("1007", "腾讯云黑名单"),
	TONG_DUN_BLACKLIST_CODE("1008", "同盾黑名单"),
	YI_JI_PAY_BLACKLIST_CODE("1009", "易极付黑名单"),
	ZHI_MA_SCORES("1010" ,"芝麻报告黑名单"),
	ZHI_MA_INDUSTRY("1011", "芝麻行业关注清单黑名单"),
	OVERDUE_BLACKLIST_CODE("1012", "逾期黑名单"),
	CONTACTS_BLACKLIST_CODE("1013", "联系人黑名单"),
	DEVICE_ID_BLACKLIST_CODE("1014" , "设备ID黑名单"),
	WIRELESS_MAC_BLACKLIST_CODE("1015", "无线mac地址黑名单"),
	REAL_IP_BLACKLIST_CODE("1016", "真实IP地址黑名单"),
	HONG_CHENG_ACCESS_CODE("1017", "网洪准入黑名单"),
	;
	private String code;
	private String remake;

	BlackListCodeEnum(String code, String remake) {
		this.code = code;
		this.remake = remake;
	}

	public String getCode() {
		return code;
	}

	public String getRemake() {
		return remake;
	}


	public static BlackListCodeEnum parse(String code){
		if(null == code){
			return null;
		}
		String codeTemp = code.trim();
		for(BlackListCodeEnum elem : values()){
			if(elem.getCode().equals(codeTemp)){
				return elem;
			}
		}
		return null;
	}
}
