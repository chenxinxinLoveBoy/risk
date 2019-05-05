package com.shangyong.handler;

import com.shangyong.entity.BaseResult;
import com.shangyong.exception.CalfException;
import com.shangyong.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
	
	Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public BaseResult handle(Exception e) {
		if (e instanceof CalfException) {
			CalfException calfException = (CalfException) e;
			return ResultUtils.resultFail(calfException);
		} else {
			logger.error("[系统异常]{}", e);
			return ResultUtils.resultError();
		}
	}
}
