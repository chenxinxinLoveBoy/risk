package com.shangyong.backend.controller;

import com.shangyong.backend.entity.BoOrderform;
import com.shangyong.backend.service.impl.BoOrderformServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单类controller
 * @author xixinghui
 *
 */
@Controller
@RequestMapping(value = "/backend/boOrderform")
public class BoOrderformController {
	
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(BoOrderformController.class);
	
	@Autowired
	private BoOrderformServiceImpl boOrderformservice;
	
	/**
	 * 查询所有订单信息
	 * @param request
	 * @param response
	 * @param boOrderform    订单信息entity对象
	 */
	@RequestMapping(value="/findAll.do", method= RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response, BoOrderform boOrderform) {
		try{
			List<BoOrderform> list = new ArrayList<BoOrderform>();
			String appName = boOrderform.getAppName();
			Integer days = boOrderform.getDays();
			int count = 0;
			// 洪澄订单信息查询
			if(("".equals(appName) || "1".equals(appName))
					&& days.compareTo(4) < 0) {
				int countBo = boOrderformservice.findAllCount(boOrderform);
				List<BoOrderform> listBo = boOrderformservice.findAll(boOrderform);
				list.addAll(listBo);
				count = count + countBo;
			}
			// 速贷订单信息查询
			if(("".equals(appName) || ("2".equals(appName))
					&& (days.compareTo(3) > 0) || days == 0)) {
				
			}
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
