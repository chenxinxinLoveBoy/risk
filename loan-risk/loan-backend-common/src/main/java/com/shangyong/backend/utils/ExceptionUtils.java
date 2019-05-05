package com.shangyong.backend.utils;

import com.shangyong.backend.common.enums.CalfResultEnum;
import com.shangyong.exception.CalfException;

public class ExceptionUtils {

	private ExceptionUtils() {}
	
	public static void initCalfException(CalfResultEnum resultEnum) {
		throw new CalfException(resultEnum.getCode(), resultEnum.getMessage());
	}
	
	public static void initCalfException(String code, String message) {
		throw new CalfException(code, message);
	}
}
