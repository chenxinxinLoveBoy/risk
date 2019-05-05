package com.shangyong.backend.utils;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangyong.backend.entity.ScScheduleJob;

public class TaskUtils {
	
	private TaskUtils() {
		
	}

	public final static Logger logger = LoggerFactory.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokeMethod(ScScheduleJob scheduleJob) {
		Object object = null;
		Class clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringIocUtils.getBean(scheduleJob.getSpringId());
		}
		
		if (object == null) {
			logger.info("-->>任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		
		clazz = object.getClass();
		
		Method method = null;
		
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			logger.info("-->>任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			logger.info("-->>任务名称 = [" + scheduleJob.getJobName() + "]异常", e);
		}

		if (method != null) {
			try {
				method.invoke(object);
			} catch (Exception e) {
				logger.info("-->>任务名称 TaskUtils invokeMethod" + e.getMessage(), e);
			} 
		}

		logger.info("-->>任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
	}

	/**
	 * 通过反射调用springBeanId中定义的methodName方法
	 * @param springBeanId
	 * @param methodName
	 */
	public void invokeMethod(String springBeanId, String methodName) {
		Object object = null;
		Class clazz = null;
		if (StringUtils.isNotBlank(springBeanId)) {
			object = SpringIocUtils.getBean(springBeanId);
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			logger.error("-->>任务名称 = [" + methodName + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			logger.error("-->>任务名称 invokeMethod" + e.getMessage(), e);
		}
		
		if (method != null) {
			try {
				method.invoke(object);
			} catch (Exception e) {
				logger.info("-->>任务名称 TaskUtils invokeMethod" + e.getMessage(), e);
			}
		}
	}
}
