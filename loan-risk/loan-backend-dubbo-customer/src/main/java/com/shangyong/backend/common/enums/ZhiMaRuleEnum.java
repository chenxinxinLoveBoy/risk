package com.shangyong.backend.common.enums;

/**
*	风控规则枚举
* @author xiangxianjin
*/
public enum ZhiMaRuleEnum {
	ZHIMA_SCORE("0100001","01","0101","600","芝麻分数600分以下"),
	ZHIMA_VALIDATE("0100002","02","0201","yes","芝麻欺诈验证");
		
	/**
	 * 检查项code
	 */
	private String check;
	
	/**
	 * 技术比对值类型
	 * 01-数值、02-字符、03-集合
	 */
	private String checkType;
	
	/**
	 *技术校验规则 
	 *0101-（数值）小于、0102-（数值）小于等于、0103-（数值）等于、0104-（数值）大于、0105-（数值）大于等于）
	 *0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null
	 *0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致
	 *0301-（集合）size大于0、0302-（集合）集合为null或size小于1	
	 */
	private String rule; 	
	
	/**
	 * 技术校验标准值
	 */
	private String value; 	
	
	/**
	 * 规则描述
	 */
	private String desc;
	
	/**
	 * 根据code获取枚举
	 * @param check  检查项code
	 * @return
	 */
	public static ZhiMaRuleEnum getEnumByCheck(String check){
		ZhiMaRuleEnum[] ruleEnums = ZhiMaRuleEnum.values();
		for(ZhiMaRuleEnum ruleEnum : ruleEnums){
			if(ruleEnum.getCheck().equals(check)){
				return ruleEnum;
			}
		}
		return null;
	}
	
	private ZhiMaRuleEnum(String check, String checkType, String rule, String value, String desc) {
		this.check = check;
		this.checkType = checkType;
		this.rule = rule;
		this.value = value;
		this.desc = desc;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
