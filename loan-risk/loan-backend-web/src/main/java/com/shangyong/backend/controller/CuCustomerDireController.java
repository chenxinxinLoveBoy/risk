package com.shangyong.backend.controller;

import com.shangyong.backend.entity.CuCustomerDirectories;
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
 * 用户手机通讯录记录Controller
 * @author hc
 *
 */
@RestController
@RequestMapping(value ="/backend/CuCustomerDire")
public class CuCustomerDireController {
 
	private static Logger logger = Logger.getLogger(CuCustomerDireController.class);
	
	
	@Autowired
	private com.shangyong.backend.service.impl.CuCustomerDirectoriesServiceImpl CuCustomerDirectoriesServiceImpl;
	
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, CuCustomerDirectories cuCustomerDirectories) {
		try {  
			int count = CuCustomerDirectoriesServiceImpl.listAllCount(cuCustomerDirectories);
			logger.info("获取用户手机通讯录记录count:"+count);
			List<CuCustomerDirectories> list = CuCustomerDirectoriesServiceImpl.getEntityById(cuCustomerDirectories);
			logger.info("获取用户手机通讯录记录信息"+list.toString()); 
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
