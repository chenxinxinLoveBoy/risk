package com.shangyong.shangyong.multThread;

import com.shangyong.shangyong.service.NineOneCreditApplicationProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NineOneCreditDataSynchronousServiceRun implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(NineOneCreditDataSynchronousServiceRun.class);
	
	@Autowired
	private NineOneCreditApplicationProcessService nineOneCreditApplicationProcessService;

	@Override
	public void run() {
		try {
			while (true) {
				logger.info("=====线程名：" + Thread.currentThread().getName() + "============== NineOneCreditDataSynchronousImpl--start");
				Object obj = null;//NineOneCreditDataSynchronousJob.XCZX_LINKED_QUEUE.poll();
				if (obj == null) {
					logger.info("=====线程名：" + Thread.currentThread().getName() + "============== NineOneCreditDataSynchronousImpl--队列为空退出");
					break;
				}
				Map<String, Object> mapObj = (Map<String, Object>) obj;

				List<Map<String,Object>> list = new ArrayList();
				list.add(mapObj);

				//执行91征信数据同步定时任务方法
				nineOneCreditApplicationProcessService.process(list);
				logger.info("=====++++++++++++++++++++++++++++++++++++++++++++++++++" + obj);
				logger.info("=====线程名：" + Thread.currentThread().getName() + "============== NineOneCreditDataSynchronousImpl--end");
			}
		} catch (Exception e) {
			logger.error("=====线程名：" + Thread.currentThread().getName() +",错误信息："+e.getMessage());
		}
	}


	public NineOneCreditApplicationProcessService getNineOneCreditApplicationProcessService() {
		return nineOneCreditApplicationProcessService;
	}


	public void setNineOneCreditApplicationProcessService(NineOneCreditApplicationProcessService nineOneCreditApplicationProcessService) {
		this.nineOneCreditApplicationProcessService = nineOneCreditApplicationProcessService;
	}
}
