package com.shangyong.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

	private ValidateUtils() {
	}

	private static String phone_rex = PropertiesUtil.get("phone.rex");

	/**
	 * 判断手机号码是否符合格式
	 * @param phoneNum
	 * @return
	 */
	public static boolean checkPhoneNumber(String phoneNum) {
		Pattern regex = Pattern.compile(phone_rex);
		Matcher matcher = regex.matcher(phoneNum);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(ValidateUtils.checkPhoneNumber("18121071521"));
	}
}
