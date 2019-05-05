package com.shangyong.backend.common.consts;

/**
 * 通讯录规则常量类
 * 
 * @author hxf
 *
 */
public class DirectoriesRuleConst {
	
	/**异类号码逻辑**/
	/**姓名为空**/
	public static final String DIRECTORIES_NAME_IS_NULL = "姓名为空";
	/**姓名为纯数字**/
	public static final String DIRECTORIES_NAME_IS_NUM = "姓名为纯数字";
	/**号码为空**/
	public static final String DIRECTORIES_PHONE_IS_NULL = "号码为空";
	/**同一姓名出现3次及以上**/
	public static final String DIRECTORIES_NAME_IS_MORE = "同一姓名出现3次及以上";
	/**号码超过12位和号码少与8位**/
	public static final String DIRECTORIES_PHONE_IS_IRRATIONAL = "号码超过12位和号码少与8位";

}
