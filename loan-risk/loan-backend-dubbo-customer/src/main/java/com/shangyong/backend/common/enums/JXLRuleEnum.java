package com.shangyong.backend.common.enums;

/**
*	风控规则枚举
* @author xiangxianjin
*/
public enum JXLRuleEnum {
	COURT("0200001","","0202", new Object[]{""},"身份证在法院黑名单"),
	FINANCIAL("0200002","","0202",new Object[]{""},"身份证在金融机构黑名单"),
	REALNAME("0200003","","0205",new Object[]{"匹配成功"},"手机没有实名认证:姓名检查"),
	ID_CARD("0200004","","0205",new Object[]{"匹配成功"},"手机没有实名认证:身份证号检查"),
	TEMP_PHONE("0200005","","0204",new Object[]{"该联系人号码为临时小号"},"手机是临时小号"),
	OUT_CALL("0200006","澳门电话通话情况","0204",new Object[]{"偶尔通话(3次以内，包括3次)","多次通话(3次以上)"},"有澳门通话记录"),
	POLICE("0200007","110话通话情况","0204",new Object[]{"多次通话(3次以上)"},"110有3次以上通话记录"),
	HOSPITAL("0200008","120话通话情况","0204",new Object[]{"多次通话(3次以上)"},"120有3次以上通话记录"),
	LAWYER("0200009","律师号码通话情况","0204",new Object[]{"多次通话(3次以上)"},"律师号码有3次以上通话记录"),
	LOAN("0200010","贷款类号码联系情况","0204",new Object[]{"经常被联系(联系次数在5次以上，包含5次，且主动呼叫占比大于50%，包含50%)","偶尔被联系(联系次数在5次以上，包含5次，且主动呼叫占比 20%-50%之间，包含20%)"},"贷款类号码联系次数在5次以上且主动呼叫占比在20%-50%之间"),
	BANK("0200011","银行类号码联系情况","0204",new Object[]{"经常被联系(联系次数在5次以上，包含5次，且主动呼叫占比大于50%，包含50%)","偶尔被联系(联系次数在5次以上，包含5次，且主动呼叫占比 20%-50%之间，包含20%)"},"银行类号码联系次数在5次以上且主动呼叫占比在20%-50%之间");
		
	
	/**
	 * 检查项code
	 */
	private String check;
	
	/**
	 * 检查项名称
	 */
	private String checkName;
	
	/**
	 *技术校验规则 
	 *0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null
	 *0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致
	 */
	private String rule; 	

	/**
	 * 匹配规则
	 */
	private Object[] rules;

	/**
	 * 规则描述
	 */
	private String desc;

	/**
	 * 根据code获取枚举
	 * @param check  检查项code
	 * @return
	 */
	public static JXLRuleEnum getEnumByCheck(String check){
		JXLRuleEnum[] ruleEnums = JXLRuleEnum.values();
		for(JXLRuleEnum ruleEnum : ruleEnums){
			if(ruleEnum.getCheck().equals(check)){
				return ruleEnum;
			}
		}
		return null;
	}
	
	/**
	 * 根据名称获取枚举
	 * @param checkName  检查项名称
	 * @return
	 */
	public static JXLRuleEnum getEnumByCheckName(String checkName){
		JXLRuleEnum[] ruleEnums = JXLRuleEnum.values();
		for(JXLRuleEnum ruleEnum : ruleEnums){
			if(ruleEnum.getCheckName().equals(checkName)){
				return ruleEnum;
			}
		}
		return null;
	}
	
	private JXLRuleEnum(String check, String checkName, String rule, Object[] rules, String desc) {
		this.check = check;
		this.checkName = checkName;
		this.rule = rule;
		this.rules = rules;
		this.desc = desc;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object[] getRules() {
		return rules;
	}

	public void setRules(Object[] rules) {
		this.rules = rules;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}
