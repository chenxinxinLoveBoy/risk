
package com.shangyong.shangyong.jobs;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.service.TdLoanInterfaceService;
import com.shangyong.shangyong.multThread.ExecutorServiceForTask;
import com.shangyong.shangyong.multThread.NineOneCreditDataSynchronousServiceRun;
import com.shangyong.shangyong.service.NineOneCreditApplicationProcessService;
import com.shangyong.shangyong.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年7月25日  
 *
 */

@Component
public class NineOneCreditDataSynchronousJob implements Job {

	private static Logger logger = LoggerFactory.getLogger("XczxDataSynchronousTask");

	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;

	@Autowired
	private ExecutorServiceForTask executorServiceForTask;

	@Autowired
	private NineOneCreditApplicationProcessService nineOneCreditApplicationProcessService;

	/**
	 * 91征信定时同步数据处理队列
	 */
	public static ConcurrentLinkedQueue<Map<String, Object>> XCZX_LINKED_QUEUE = new ConcurrentLinkedQueue<Map<String, Object>>();

//	@Override
//	public SaturnJobReturn handleJavaJob(String jobName, Integer shardItem, String shardParam,
//                                         SaturnJobExecutionContext shardingContext) throws InterruptedException {
//		scheduler();
//		return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//	}

	public void scheduler() {
		ExecutorService exeService = null;
		try {
			logger.info("[91征信-数据同步] 已经存在" + XCZX_LINKED_QUEUE);
			logger.info("[91征信-数据同步] 定时任务获取待处理数据，当前任务处理心跳正常");

			String isStep = Constants.XC_91ZX_STEP;

			//查询步骤号为6的申请单id（我们已经发起过查询的订单，但是未返回报告数据的状态）
			List<Map<String, Object>> dataList = tdLoanInterfaceService.getAppLicationList(isStep);

			if (CollectionUtils.isNotEmpty(dataList)) {
				//开启自己的线程池 采用多线程执行
				exeService = Executors.newFixedThreadPool(50);

				NineOneCreditDataSynchronousServiceRun taskRunnable = new NineOneCreditDataSynchronousServiceRun();
				taskRunnable.setNineOneCreditApplicationProcessService(nineOneCreditApplicationProcessService);

				executorServiceForTask.exeTaskList(dataList, taskRunnable, null, XCZX_LINKED_QUEUE, exeService);
				logger.info("[91征信-数据同步] 定时任务处理结束，条数：" + dataList.size() + "，当前任务处理心跳正常");
			}else{
				logger.info("[91征信-数据同步] 定时任务获取待处理数据0条，当前定时任务处理结束");
			}
		} catch (Throwable e) {
			logger.error("[91征信-数据同步] 查询91征信待处理数据返回错误信息："+e.getMessage(), e);
		}finally {
			if(exeService != null && !exeService.isShutdown()){
				logger.info("[91征信-数据同步] XczxDataSynchronousTask.exeService.shutdown() finally 执行");
				exeService.shutdown();
			}else{
				logger.info("[91征信-数据同步] XczxDataSynchronousTask.exeService.shutdown() finally 不执行");
			}
		}
	}

	/**
	 * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
	 */
	public static Object getObject() {
		NineOneCreditDataSynchronousJob instance = (NineOneCreditDataSynchronousJob) SpringContextUtils.getBean(NineOneCreditDataSynchronousJob.class);
		return instance;
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}

