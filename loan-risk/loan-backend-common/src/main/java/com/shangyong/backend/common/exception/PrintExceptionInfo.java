package com.shangyong.backend.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

/**
 * 异常信息以字符串形式输出
 * 
 * @author xiajiyun
 *
 */
@Component
public class PrintExceptionInfo {
 
	/**
	 * 打印输出日志
	 * @param e
	 * @return
	 */
	public String getThrowableInfo(Throwable e) {
		return printExceptionInfo(e);
	}

	
	/**
	 * 打印输出日志
	 * @param e
	 * @return
	 */
	public String getExceptionInfo(Exception e) {
		return getThrowableInfo(e);
	}


	public String printExceptionInfo(Throwable e) {
		// 打印输出日志
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		e.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

}
