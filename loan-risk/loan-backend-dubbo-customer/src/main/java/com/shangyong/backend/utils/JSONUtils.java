package com.shangyong.backend.utils;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import com.shangyong.backend.common.enums.CodeEnum;
import org.apache.commons.lang3.StringUtils;

import com.shangyong.backend.common.Constants;
import com.shangyong.utils.SpringUtils;

public class JSONUtils {

	private JSONUtils() {
	}

	public static void toJSON(HttpServletResponse response, CodeEnum codeEnum) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		map.put(Constants.CODE, codeEnum.getCode());
		map.put(Constants.MESSAGE, codeEnum.getMessage());
		map2.put(Constants.SUCCESSED, codeEnum.getFlag());
		map.put(Constants.DATA, map2);
		SpringUtils.renderJson(response, map);
	}

	/**
	 * 上传图片后返回路劲json
	 * 
	 * @param response
	 * @param codeEnum
	 * @param str
	 */
	public static void toJSON(HttpServletResponse response, CodeEnum codeEnum, String str) {
		// LinkedHashMap<String, Object> map = new LinkedHashMap<String,
		// Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		// map.put(Constants.CODE, codeUtils.getCode());
		// map.put(Constants.MESSAGE, codeUtils.getMessage());
		// map2.put(Constants.SUCCESSED, codeUtils.getFlag());
		map2.put("url", str);
		// map.put(Constants.DATA, map2);
		SpringUtils.renderJson(response, map2);
	}

	public static void toJSON(HttpServletResponse response, CodeEnum codeEnum, Object obj) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		map.put(Constants.CODE, codeEnum.getCode());
		map.put(Constants.MESSAGE, codeEnum.getMessage());
		map2.put(Constants.SUCCESSED, codeEnum.getFlag());
		map2.put("data", obj);
		map.put(Constants.DATA, map2);
		SpringUtils.renderJson(response, map);
	}

	/**
	 * list列表用
	 * 
	 * @param response
	 * @param obj
	 * @param count
	 */
	public static void toListJSON(HttpServletResponse response, Object obj, int count) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		// LinkedHashMap<String, Object> map2 = new LinkedHashMap<String,
		// Object>();
		map.put(Constants.MSG, "获取成功");
		map.put(Constants.REL, true);
		map.put("list", obj);
		map.put("count", count);// 分页用，查询的总条数
		SpringUtils.renderJson(response, map);
	}

	/**
	 * 返回json格式,key自定义
	 * 
	 * @param response
	 * @param codeEnum
	 * @param obj
	 *            Object类型
	 * @param key
	 *            key为空, 默认"data"作为key
	 */
	public static void toJSON(HttpServletResponse response, CodeEnum codeEnum, Object obj, String key) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
		map.put(Constants.CODE, codeEnum.getCode());
		map.put(Constants.MESSAGE, codeEnum.getMessage());
		map.put(Constants.SUCCESSED, codeEnum.getFlag());
		map2.put(StringUtils.isBlank(key) ? "data" : key, obj);
		map.put(Constants.DATAS, map2);
		SpringUtils.renderJson(response, map);
	}

}
