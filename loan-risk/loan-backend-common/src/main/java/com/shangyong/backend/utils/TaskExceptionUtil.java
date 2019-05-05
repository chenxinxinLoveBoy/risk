package com.shangyong.backend.utils;

import org.apache.commons.lang3.StringUtils;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;

public class TaskExceptionUtil {
	
	
	/**
	 * 判断错误信息是否是系统参数表里配置的错误状态
	 * @param sysParamRedisService
	 * @param errorMsg
	 * @return true：最终错误信息  false：不是最终错误信息
	 */
	public static boolean isEndState(String errorMsg){
		
		boolean flag = false;
		
		if(StringUtils.isNotBlank(errorMsg)){//对象不为空
			
			SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TASK_ANTIFRAUD_CODE);
			String sysParmStr = sysParam.getParamValueOne();
			
			if(StringUtils.isNotBlank(sysParmStr)){
				
				if(sysParmStr.indexOf("|||" + errorMsg + "|||") > -1){
					
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	
	
	/**
	 * add: xiajiyun, 提额表用
	 * 判断错误信息是否是系统参数表里配置的错误状态
	 * @param sysParamRedisService
	 * @param errorMsg
	 * @return true：最终错误信息  false：不是最终错误信息
	 */
	public static boolean isEndStateTop(String errorMsg){
		
		boolean flag = false;
		
		if(StringUtils.isNotBlank(errorMsg)){//对象不为空
			
			SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.PROMOTECONSUMER_ANTIFRAUD_CODE);
			String sysParmStr = sysParam.getParamValueOne();
			
			if(StringUtils.isNotBlank(sysParmStr)){
				
				if(sysParmStr.indexOf("|||" + errorMsg + "|||") > -1){
					
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	
	/**
	 * add: xiajiyun, 公共
	 * 判断错误信息是否是系统参数表里配置的错误状态
	 * @param errorMsg 异常错误信息
	 * @param code 系统参数code
	 * @return true：最终错误信息  false：不是最终错误信息
	 */
	public static boolean isEndStateMQ(String errorMsg, String code){
		
		boolean flag = false;
		
		if(StringUtils.isNotBlank(errorMsg)){//对象不为空
			
			SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(code);
			String sysParmStr = sysParam.getParamValueOne();
			
			if(StringUtils.isNotBlank(sysParmStr)){
				
				if(sysParmStr.indexOf("|||" + errorMsg + "|||") > -1){
					
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	
	
	
	// 判断一个字符是否是中文
	public static boolean isChinese(char c) {
	      return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
	}
	
	// 判断一个字符串是否为中文
	public static boolean isChinese(String str) {
	    if (str == null) return false;
	    for (char c : str.toCharArray()) {
	        if (!isChinese(c)) return false;// 有一个中文字符就返回
	    }
	    return true;
	}
}
