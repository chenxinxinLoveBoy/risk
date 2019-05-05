package com.shangyong.utils;

import com.shangyong.entity.BaseResult;
import com.shangyong.enums.BaseResultEnum;
import com.shangyong.exception.CalfException;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {

	private ResultUtils() {}
	
	/**
	 * 成功返回
	 * @return
	 */
	public static BaseResult resultSuccess() {
		return resultSuccess(null);
	}
	
	/**
	 * 成功返回
	 * @param data
	 * 			接口返回数据
	 * @return
	 */
	public static BaseResult resultSuccess(Object obj) {
		BaseResult result = new BaseResult();
		result.setCode(BaseResultEnum.SUCCESS.getCode());
		result.setMessage(BaseResultEnum.SUCCESS.getMessage());
		Map data = new HashMap();
		data.put("successed", BaseResultEnum.SUCCESS.getFlag());
		data.put("data", obj);
		result.setData(data);
		return result;
	}

	/**
	 * 成功返回
	 *
	 * @param data 接口返回数据
	 * @return
	 */
	public static BaseResult resultToSuccess(Object obj) {
		BaseResult<Object> result = new BaseResult<>();
		result.setCode(BaseResultEnum.SUCCESS.getCode());
		result.setMessage(BaseResultEnum.SUCCESS.getMessage());
		result.setData(obj);
		return result;
	}

	/**
	 * 成功返回
	 * @param data
	 * 			接口返回数据
	 * @return
	 */
	public static BaseResult resultSuccessed(Object obj) {
		BaseResult result = new BaseResult();
		result.setCode(BaseResultEnum.SUCCESS.getCode());
		result.setMessage(BaseResultEnum.SUCCESS.getMessage());
		result.setData(obj);
		return result;
	}
	
	/**
	 * 失败返回     断言使用
	 * @param obj
	 * @return
	 */
	public static BaseResult resultFail(String str) {
		BaseResult result = new BaseResult();
		result.setCode(BaseResultEnum.FAIL.getCode());
		result.setMessage(str);
		result.setData(new HashMap());
		return result;
	}
	
	public static BaseResult resultSuccessed(CodeUtils codeUtils) {
		BaseResult result = new BaseResult();
		result.setCode(codeUtils.getCode());
		result.setMessage(codeUtils.getMessage());
		result.setData(new HashMap());
		return result;
	}
	
	/**
	 * 失败返回
	 * @param e
	 * 			自定义异常
	 * @return
	 */
	public static BaseResult resultFailed(CodeUtils codeUtils) {
		BaseResult result = new BaseResult();
		result.setCode(codeUtils.getCode());
		result.setMessage(codeUtils.getMessage());
		result.setData(new HashMap());
		return result;
	}
	
	public static BaseResult resultSuccess(CodeUtils codeUtils) {
		BaseResult result = new BaseResult();
		result.setCode(codeUtils.getCode());
		result.setMessage(codeUtils.getMessage());
		Map data = new HashMap();
		data.put("successed",codeUtils.getFlag());
		result.setData(data);
		return result;
	}
	
	/**
	 * 失败返回
	 * @param e
	 * 			自定义异常
	 * @return
	 */
	public static BaseResult resultFail(CalfException e) {
		BaseResult result = new BaseResult();
		result.setCode(e.getCode());
		result.setMessage(e.getMessage());
		Map data = new HashMap();
		data.put("successed", false);
		result.setData(data);
		return result;
	}
	
	/**
	 * 失败返回
	 * @param e
	 * 			自定义异常
	 * @return
	 */
	public static BaseResult resultFail(CodeUtils codeUtils) {
		BaseResult result = new BaseResult();
		result.setCode(codeUtils.getCode());
		result.setMessage(codeUtils.getMessage());
		Map data = new HashMap();
		data.put("successed", codeUtils.getFlag());
		result.setData(data);
		return result;
	}
	
	/**
	 * 错误返回
	 * @return
	 */
	public static BaseResult resultError() {
		BaseResult result = new BaseResult();
		result.setCode(BaseResultEnum.ERROR.getCode());
		result.setMessage(BaseResultEnum.ERROR.getMessage());
		Map data = new HashMap();
		data.put("successed", BaseResultEnum.ERROR.getFlag());
		result.setData(data);
		return result;
	}
}
