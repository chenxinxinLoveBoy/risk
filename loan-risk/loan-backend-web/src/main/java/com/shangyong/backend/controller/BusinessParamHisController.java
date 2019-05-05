package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.impl.BussnessParamHisServiceImpl;
import com.shangyong.backend.service.impl.SysParamHisServiceImpl;
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
@RequestMapping("/backend/BusinessParamHis/")
public class BusinessParamHisController extends BaseController {
	private static Logger logger = Logger.getLogger(BusinessParamHisController.class);
	@Autowired
	private BussnessParamHisServiceImpl bussnessParamHisServiceImpl;
	
	
	@Autowired
	private SysParamHisServiceImpl sysParamHisServiceImpl;

	/**
	 * 历史参数配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllSysParamHis.do", method = RequestMethod.POST)
	public void getAllSysParamHis(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		try {
			int count = bussnessParamHisServiceImpl.listAllCount(sysParam);// 统计总条数
			List<SysParam> list = bussnessParamHisServiceImpl.findAll(sysParam);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 根据id获取对象信息
	 * 
	 * @param request
	 * @param response
	 * @param sysParam
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		try {
			SysParam sysParamHisBo = sysParamHisServiceImpl.getEntityById(sysParam.getParamHisId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, sysParamHisBo, "sysParamHisObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
