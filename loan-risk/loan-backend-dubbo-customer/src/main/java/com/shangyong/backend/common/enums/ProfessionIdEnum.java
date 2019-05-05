package com.shangyong.backend.common.enums;

/**
 * 职位枚举类
 * @author xixinghui
 *
 */
public enum ProfessionIdEnum {
	ADVANCED("ADVANCED", "高级资深人员"),
	INTERMEDIATES("INTERMEDIATES", "中级技术人员"),
	BEGINNERS("BEGINNERS", "初级、助理人员"),
	PRACTICE("PRACTICE", "见习专员"),
	SENIOR("SENIOR", "高层管理人员"),
	MIDDLE("MIDDLE", "中层管理人员"),
	JUNIOR("JUNIOR", "基层管理人员"),
	NORMAL("NORMAL", "普通员工");
	
	/**
	 * 职位code
	 */
	private String professionCode;
	
	/**
	 * 职位名称
	 */
	private String professionName;

	private ProfessionIdEnum(String professionCode, String professionName) {
		this.professionCode = professionCode;
		this.professionName = professionName;
	}
	
	public static boolean contain(String code) {
		for (ProfessionIdEnum profession : ProfessionIdEnum.values()) {
			if (profession.getProfessionCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getProfessionCode() {
		return professionCode;
	}

	public void setProfessionCode(String professionCode) {
		this.professionCode = professionCode;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	
}
