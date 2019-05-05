package com.shangyong.backend.controller;

import com.shangyong.backend.entity.mq.MqQueueInfo;
import com.shangyong.backend.service.impl.MqQueueInfoServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/backend/mq/")
public class MqCheckController {

	private static Logger logger = Logger.getLogger(MqCheckController.class);
	
	@Autowired
	private MqQueueInfoServiceImpl mqQueueInfoService;
	
	/**
	 * MQ配置查询
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "queryByexchanges.do", method = RequestMethod.POST)
	public void queryByexchanges(HttpServletRequest request, HttpServletResponse response, MqQueueInfo mqQueueInfo) {
		try {
			int count = mqQueueInfoService.listAllCount(mqQueueInfo);
			List<MqQueueInfo> list = mqQueueInfoService.findAll(mqQueueInfo);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	
	}
}
