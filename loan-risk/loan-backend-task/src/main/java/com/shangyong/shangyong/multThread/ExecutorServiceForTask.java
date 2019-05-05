package com.shangyong.shangyong.multThread;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JsonUtil;
import com.shangyong.common.ServiceResult;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Component
public class ExecutorServiceForTask {
	
	private Logger logger = LoggerFactory.getLogger(ExecutorServiceForTask.class);
	
	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	private Integer maxThread = 10;

    /**
     *
     * @param dataList 需要启动threadSize处理的总集合
     * @param _runnable 实现了runable接口的类
     * @param threadSize  [可选]
     * @param queue 数据存储队列名称 （队列对像千万别传错）
     * @param exeService
     * @return 任务执行结果
     */
	public void exeTaskList(List dataList, Runnable _runnable, Integer threadSize, ConcurrentLinkedQueue queue, ExecutorService exeService) {
		logger.info("-->>--------exeTaskList start---");
		try {
			List<Future> futureList = this.exeTaskListForFutures(dataList, _runnable, threadSize, queue, exeService);

			if (null != futureList) {
				for(Future future : futureList) {
					try {
						future.get();
					} catch (Throwable e) {
						logger.info("-->>exeTaskList future.get 方法执行异常，异常信息：" + e.getMessage());
					}
				}
			}
		} catch (Throwable e) {
			logger.info("-->>exeTaskList方法执行异常，异常信息：" + e.getMessage());
		} finally { 
			//启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
//			logger.info("执行线程的shutdown,停止当前线程");
//			exeService.shutdown();
		} 
		
		logger.info("-->>--------exeTaskList end---");
	}

    /**
     *
     * @param dataList 需要启动threadSize处理的总集合
     * @param run 实现了runable接口的类
     * @param threadSize  [可选]
     * @param queue 数据存储队列名称 （队列对像千万别传错）
     * @param exeService
     * @return 任务执行结果
     */
	public List<Future> exeTaskListForFutures(List dataList, Runnable run, Integer threadSize, ConcurrentLinkedQueue queue, ExecutorService exeService) {
		ServiceResult serviceResult = new ServiceResult();
		if (run == null || queue == null) {
			serviceResult.setCode(CodeUtils.BACKEND_OPT_ERROR.getCode());
			serviceResult.setCode("参数必填【dataList _runnable queue】");
			throw new RuntimeException(JsonUtil.toJson(serviceResult));
		}
		
		if (CollectionUtils.isNotEmpty(dataList)) {
			Integer process_recored_size_per_thread = 10; //单个线程处理条数
			try {
				SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.PER_TASK_MAX_THREAD);
				if (sysParam!=null) {
					String paramValueOne = sysParam.getParamValueOne();
					String paramValueTwo = sysParam.getParamValueTwo();
					logger.info("-->>exeTaskListForFutures sysParam paramValueOne:" + paramValueOne + ", paramValueTwo:" + paramValueTwo);
					maxThread = Integer.valueOf(sysParam.getParamValueOne());
					process_recored_size_per_thread = Integer.valueOf(sysParam.getParamValueTwo());
				}
			} catch(Exception e) {
				logger.info("-->>获得【每次单次开启最大线程个数】参数异常", e);
			}
			
			queue.clear();
			queue.addAll(dataList);
			if (threadSize != null && threadSize < maxThread){
				 maxThread = threadSize;
			} 
			Integer totalRecordLen = dataList.size();
			int totalNum = (totalRecordLen  +  process_recored_size_per_thread - 1) / process_recored_size_per_thread;
			if(totalNum < maxThread) {
				maxThread = totalNum;
			}
			logger.info("-->>本次启动线程数maxThread：" + maxThread + ", 处理总记录数totalRecordLen：" + totalRecordLen);
			List<Future> resultList = new ArrayList<Future>();
			for (int i=0;i<maxThread;i++) {
				resultList.add(exeService.submit(run));
			}
			
			return resultList;
		}
		return null;
	}

}
