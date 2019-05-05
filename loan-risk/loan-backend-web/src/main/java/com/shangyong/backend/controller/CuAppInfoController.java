package com.shangyong.backend.controller;

import com.shangyong.backend.entity.CuAppInfo;
import com.shangyong.backend.service.impl.CuAppInfoServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客户手机应用列表记录Controler
 * @author hc
 *
 */
@RestController
@RequestMapping(value ="/backend/cuAppInfo")
public class CuAppInfoController {
	
	private static Logger logger = Logger.getLogger(CuAppInfoController.class);
	
	@Autowired
	private CuAppInfoServiceImpl cuAppInfoServiceImpl;
	
	/**
	 * 客户手机应用列表信息
	 * @param request
	 * @param response
	 * @param cuAppInfo
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, CuAppInfo cuAppInfo) {
		try {  
			int count = cuAppInfoServiceImpl.listAllCount(cuAppInfo);
			logger.info("客户手机应用列表记录count" + count + '\t');
			List<CuAppInfo> info = cuAppInfoServiceImpl.getEntityById(cuAppInfo);
			logger.info("客户手机应用列表记录info" + info.size()+ '\t');
			JSONUtils.toListJSON(response, info, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}


}
