package com.shangyong.utils;

import com.shangyong.common.Constants;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

public class JSONUtils {
	
	private JSONUtils() {
	}

	public static void toJSON(HttpServletResponse response, CodeUtils codeUtils) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		map.put(Constants.CODE, codeUtils.getCode());
		map.put(Constants.MESSAGE, codeUtils.getMessage());
		map2.put(Constants.SUCCESSED, codeUtils.getFlag());
		map.put(Constants.DATA, map2);
		SpringUtils.renderJson(response, map);
	}
	
	public static void toJSON(HttpServletResponse response, CodeUtils codeUtils, String str) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		map.put(Constants.CODE, codeUtils.getCode());
		map.put(Constants.MESSAGE, codeUtils.getMessage());
		map2.put(Constants.SUCCESSED, codeUtils.getFlag());
		map2.put("avatarPath", str);
		map.put(Constants.DATA, map2);
		SpringUtils.renderJson(response, map);
	}
	
//	public static void toJSON(HttpServletResponse response,CodeUtils codeUtils,Object obj) {
//		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
//		map.put(Constants.CODE, codeUtils.getCode());
//		map.put(Constants.MESSAGE, codeUtils.getMessage());
//		map2.put(Constants.SUCCESSED, codeUtils.getFlag());
//		map2.put("data", obj);
//		map.put(Constants.DATA, map2);
//		SpringUtils.renderJson(response, map);
//	}


	
	public static void toJSON(HttpServletResponse response, CodeUtils codeUtils, Object obj) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		map2.clear();
		map.put(Constants.CODE, codeUtils.getCode());
		map.put(Constants.MESSAGE, codeUtils.getMessage());
		map2.put(Constants.SUCCESSED, codeUtils.getFlag());
		map2.put("data", obj);
		map.put(Constants.DATA, map2);
		SpringUtils.renderJson(response, map);
	}
	
	
}
