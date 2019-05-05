
package com.shangyong.shangyong.service.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.shangyong.service.ShangYongLoanRemindProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年7月25日  
 *
 */

@Service
public class ShangYongLoanRemindProcessServiceImpl implements ShangYongLoanRemindProcessService {
	
	private static Logger logger = LoggerFactory.getLogger("shangYongLoanRemindProcessServiceImpl");
	
	@Autowired
	private ApplicationServiceImpl applicationServiceImpl;
	
	@Override
	@Transactional
	public void process(List<Application> list) {

		logger.info("模板规则引擎定时任务更新审批单处理条数： " + list.size() + "，处理开始");

		for(Application application : list){
			application.setIsStep(Constants.INIT_STEP);
			application.setDistributionTime(DateUtils.parseToDateTimeStr(new Date()));
			applicationServiceImpl.updateEntity(application);
		}
		logger.info("模板规则引擎定时任务更新审批单处理条数： " + list.size() + "，处理结束");
	

	}

}

